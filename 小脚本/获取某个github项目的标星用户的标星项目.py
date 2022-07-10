import requests
import sys
from parsel import Selector
from bs4 import BeautifulSoup
import sqlite3
from sqlalchemy import create_engine
from sqlalchemy import Column, String,INTEGER,create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.sql import exists
Base = declarative_base()
 
session = None
class Github_Repo(Base):
    __tablename__ = 'repo'
    repo_name = Column(String(100), primary_key=True)
    desc = Column(String(4096))
    lang = Column(String(32))
    stars = Column(INTEGER)
    time = Column(String(50))

import urllib3
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)
headers = {
    'Accept-Language': "zh-CN,zh;q=0.8",
    'Accept-Encoding': "gzip, deflate",
    'Content-Type': "application/x-www-form-urlencoded",
    'Connection': "keep-alive",
    'Referer': "http://localhost/login",
    'User-Agent': "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36"
}


proxies = { "http": "http://127.0.0.1:7890", "https": "http://127.0.0.1:7890", }
handled_users=[]
handled_urls=[]

def create_table():
    engine = create_engine('sqlite:///github_star.db', echo=True)
    Base.metadata.drop_all(engine) 
    Base.metadata.create_all(engine)     

def initDb():
    engine = create_engine('sqlite:///github_star.db', echo=True)        
    DBSession = sessionmaker(bind=engine)   
    global session 
    session = DBSession()

def write2Db(repo, desc, lang, stars, time):
    global session
    exist_flag = session.query(exists().where(Github_Repo.repo_name== repo)).scalar()
    if exist_flag:
        print('exist')
        return;
    repo = Github_Repo(repo_name=repo, desc=desc, lang=lang, stars=stars, time=time)
    session.add(repo)

def commitDb():
    global session    
    session.commit()

def getTextByUrl(url):
    page_text = requests.get(url, headers=headers, verify=False, timeout=30).text    
    return page_text

def getMapXpathItemsByUrl(url, xpath_map):
    page_text = getTextByUrl(url)   

def getXpathItemsByUrl(url, xpath):
    page_text = requests.get(url, headers=headers, verify=False, timeout=30).text 
    return getXpathItems(page_text, xpath)   

def getXpathItems(text, xpath):
    results = []
    selector = Selector(text)
    items = selector.xpath(xpath)
    for index, item in enumerate(items):
        results.append(item.get())
    return results

def getXpathItem(text, xpath):
    selector = Selector(text)
    items = selector.xpath(xpath)
    for index, item in enumerate(items):
        return item.get()   


def getStargazers(url):
    users = []
    for i in range(1, 100):
        newUrl = url + '/stargazers?page=%d'%i
        i = i + 1
        page_text = requests.get(newUrl, headers=headers, verify=False, timeout=30).text
        #print(page_text)
        if 'You’ve reached the end of' in page_text:
            print('end')
            break
        selector = Selector(page_text)
        items = selector.xpath("//span/span/a[@data-octo-click='hovercard-link-click']/text()")
        for index, item in enumerate(items):
            users.append(item.get())
    return users   

def getItemXpath(item, xpath,useSoup=False):
        item_value = item.xpath(xpath)
        value = ''
        if useSoup:
            if item_value != None and item_value.get() != None:
                value = BeautifulSoup(item_value.get()).get_text().strip()
        else:            
            if item_value != None and item_value.get() != None:
                value = item_value.get().strip()    
        return value

def getUserStarRepo(user):
    url = 'https://github.com/%s?tab=stars'%user
    for i in range(1, 100):
        if has_handled_url(url):
            continue
        text = getTextByUrl(url)
        selector = Selector(text)
        items = selector.xpath("//div[contains(@class,'position-relative')]//div[contains(@class,'border-bottom')]")
        for index, item in enumerate(items):
            repo = getItemXpath(item, './/a/@href')
            desc = getItemXpath(item, './/p', True)
            relative_time = getItemXpath(item, './/relative-time/@datetime')
            lang = getItemXpath(item, ".//span[@itemprop='programmingLanguage']/text()")
            stars = getItemXpath(item, ".//a[contains(@href,'/stargazers')]", True).replace(',','')  
            stars_num = 0
            if stars == "":
                stars_num = 0
            else:
                stars_num = int(stars)       
            write2Db(repo, desc, lang, int(stars_num), relative_time)
            print(repo)
            print(desc)
            print(lang)
            print(relative_time)
            print(stars)
        commitDb()
        record_url_handled(url)
        url = getXpathItem(text, "//a[contains(@href, '?after=')]/@href")
        print(url)
        if url==None or url == '':
            break
    

def main():
    initDb()
    #create_table()
    load_users_handled()
    load_urls_handled()  
    print(handled_users)  
    url = 'https://github.com/Adrninistrator/java-all-call-graph' #sys.argv[1]
    users = getStargazers(url)
    #users = ['vpday']
    #users = ['jingminglang']
    for user in users:
        print(user)
        if has_handled_user(user):
            continue
        print('handled '+ user)
        getUserStarRepo(user)        
        record_users_handled(user)
    print(users)

def test():
    #record_users_handled('user2')
    load_users_handled()
    print(handled_users)
    ret = has_handled_user('vpday')
    print(ret)

def record_users_handled(user):
    file = 'users.txt'
    with open(file, 'a+') as f:
        f.write(user + '\n')

def load_users_handled():
    global handled_users
    f = open("users.txt","r") 
    lines = f.readlines()
    for line in lines:
       handled_users.append(line.strip())

def has_handled_user(user): 
    global handled_users
    if user in handled_users:
        return True
    else:
        return False 

def record_url_handled(url):
    file = 'urls.txt'
    with open(file, 'a+') as f:
        f.write(url + '\n')

def load_urls_handled():
    global handled_users
    f = open("urls.txt","r") 
    lines = f.readlines()
    for line in lines:
       handled_urls.append(line.strip())

def has_handled_url(url): 
    global handled_urls
    if url in handled_urls:
        return True
    else:
        return False         
            
#test()
main()   
#initDb 
#create_table

#write2Db('12', '12', '12', 1, 'xxx')
