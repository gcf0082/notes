suse
suse�ʼ�

top�ڴ�����
ִ��top�Ժ��ڿ���ִ̨��shift + M


��ѹtar.gz�ļ�
tar xzvf aa.tar.gz

ѹ��tar.gz�ļ�
tar czvf aa.tar.gz *

nm��-o�������԰��ļ�����ʾ����
nm -o *|grep iMAP

�鿴��������
env

�鿴cpu��Ϣ
cat /proc/cpuinfo

�鿴�ڴ�����
grep MemTotal /proc/meminfo

�鿴�����ڴ���
grep MemFree /proc/meminfo 

iptables -L # �鿴����ǽ����

netstat -s # �鿴����ͳ����Ϣ

netstat -antp # �鿴�����Ѿ�����������

netstat -lntp # �鿴���м����˿�

chkconfig --list # �г�����ϵͳ����

rpm -qa # �鿴���а�װ�������

pushd .
����ǰĿ¼ѹ��ջ���Ժ������ʹ��popd�ص���Ŀ¼

tar -c dir/ | bzip2 > dir.tar.bz2
��Ŀ¼dir/ѹ�����

rsync --bwlimit=1000 fromfile tofile
���ٶ����Ƶı��ؿ�������I/O����

ethtool --change eth0 autoneg off speed 100 duplex full
�ֶ����������ٶ�


ip route show
��ʾ·���б�

ip route add default via 1.2.3.254
����Ĭ������1.2.3.254

netstat -tup
�г���Ծ������

ls -lSr
���ļ���С������ʾ�ļ�

=====
�鿴ĳ���˿ڱ��ĸ�����ռ����
lsof -i :31007

��ʾָ��Ŀ¼�µ��ļ�����Щ���̴���
lsof +d /opt/iMAP/lib/
lsof +D /opt/iMAP/lib/ --��������

��ʾʹ��fdΪ4�Ľ���
lsof -d 4

�����̺�Ϊ12�Ľ��̴�����Щ�ļ� 
lsof -p 12 

���1��鿴���̵Ĵ򿪵��ļ�
lsof +r 1 -c imapsysd

�г����ļ��Ĵ�С
lsof -s -c imapsysd

-a��������÷���ipv4������imapsysd
lsof -i 4 -a -c imapsysd

�г��򿪵���������˿���Ϣ
lsof -i -U

�鿴������̵���Ϣ
lsof -p 456,123,789 

lsof -p $$

lsof -p 34654|grep 31050
=====/

ps -e -o pid,args --forest
����״�ṹ��ʾ����

ps -e -o pcpu,cpu,nice,state,cputime,args --sort pcpu | sed '/^ 0.0 /d'
��CPUռ����Ϊ����ʾ����

ps -e -orss=,pid=,args= | sort -b -k1,1n | pr -TW$COLUMNS
���ڴ�ʹ����Ϊ����ʾ����

touch -c -t 0304050607 file
�ı��ļ���ʱ���ǩ (YYMMDDhhmm)



strace -e open ls

linux��������errno
cat /usr/include/asm-generic/errno.h

�鿴֧����Щ�ַ���
locale -a

linuxԴ����
/usr/src/linux-2.6.16.46-0.12

suse����ssh������
yast2->�û�����->����ssh����
���ܻ�Ҫ�رշ���ǽ

suseҪ��ʹ�þ�̬ip��������Ҫ��������������ip��·��ip����192.168.1.1
��Ҫ�����ǵķ���ǽ�����ص�

����samba��yast2��ѡ��Ҫsmaba��Ŀ¼����

objdump -d a.out --�����
objdump -x obj ��ĳ�ַ�����Ϣ����ʽ��Ŀ���ļ���������֯������Ϊ����飩���
objdump -t obj ���Ŀ���ļ��ķ��ű�

=====
��gcov�����Դ��븲���� 
1�������������� -fprofile-arcs -ftest-coverage
2������
3��gcov main.c
4���鿴�ļ� main.c.gcov
(ע�⣺������������˳��ſ��ԣ������;coredump����exit�ǲ������ɱ����)

���ӣ�
cd /opt/gcf/test/gcov/src
mwc
mke

lcov -d ./ -c -o ../temp/test_baseline.info -I

./testall

lcov -d ./ -c -o ../temp/test_current.info

lcov -a ../temp/test_baseline.info -a ../temp/test_current.info -o ../temp/test.info

genhtml --legend -k -s -o ../report/ ../temp/test.info
=====

stat file�鿴�ļ���״̬����������ʱ��


md5sum ��ȡ�ļ���md5ֵ---md5�������Ϊ�ļ���ָ�ƣ���Ψһ��

cut -d: -f1 /etc/passwd > /tmp/zieckey_usr.txt 

��fileָ����ǵ��Ա�ʶ���ļ�������

paste names places dates > npd

split�и��ļ�

ʹ��touchָ��ɸ����ļ���Ŀ¼������ʱ�䣬������ȡʱ��͸���ʱ��

uniq�ɼ���ı��ļ����ظ����ֵ����У����Դ�ӡ�ظ��Ĵ���

netcat���ܺ�ǿ��

vlock���������ն�

�Ѹ�Ŀ¼����ָ����Ŀ��Ŀ¼

vi���ļ�������һ����ʱ���ļ�
��/etc/.passwd.swp

���cpu�ͺ� cat /proc/cpuinfo

gprof��linux��solaris���ɽ������ܷ����������������ù�ϵ����������Ӷ�Ҫ��-pg
Ȼ��gprof a.out ע�����Ҫ��ִ�У�������ִ�������
gprof -b ./a.out ./gmon.out
gprof -bC -f _Z5funcav ./a.out ./gmon.out ֻ��עfunca����
����-E Name ������ʱ���ų���Name�����ֺ������õ�ʱ��

gprofֻ�ܲ鿴�û�������
�����鿴�⺯������Ϣ����Ҫ�ڱ������ټ��롰-lc_p������������桰-lc���������

ϣ��findʱ����ĳ��Ŀ¼
find /apps -name "/apps/bin" -prune -o -print

ָ��ʱ�䴴���ļ�(��find�����ʱ������õ�)
touch -t 08050102 dstamp
find . -newer dstamp -print

���ҳ�Ŀ¼��Ϊ���������͵��ļ�
find . ! -type d -print

�����ļ���ɾ��
find logs -type f -mtime +5 -exec rm {} \;

ִ������ǰ����ʾ
find ./ -name "*.o" -mtime +5 -ok rm{} \;

����1�����޸Ĺ����ļ�
find . -mtime -1

����1��ǰ�޸Ĺ����ļ�
find . -mtime +1

�ҳ���Ŀ¼
find /path -depth -type d -empty

���ֽ�Ϊ0���ļ�
find /path -depth -type f -empty

�ҳ���ɾ���ļ�
find /path -name "core.*" -type f -print | xargs /bin/rm -f

��xml��conf��׺�ļ��б�������������ļ���
find ./ -name "*.xml" -fprintf xmlfiles.txt "%p\n" , -name "*.conf" -fprintf cfg.txt "%p\n"

��ƥ�䵽���ļ���ls -l����ʽ��ʾ���� ��-fls������ļ���
find ./ -name "*.c" -ls

ֻ������2����ļ�
find ./ -maxdepth 2 -mindepth 2 -name "*.xml"

���Ҵ���10M���ļ�
find ./ -size +10M

-amin n
����ϵͳ�����N���ӷ��ʵ��ļ�

-atime n
����ϵͳ�����n*24Сʱ���ʵ��ļ�

-cmin n
����ϵͳ�����N���ӱ��ı��ļ�״̬���ļ�

-ctime n
����ϵͳ�����n*24Сʱ���ı��ļ�״̬���ļ�

-mmin n
����ϵͳ�����N���ӱ��ı��ļ����ݵ��ļ�

-mtime n
����ϵͳ�����n*24Сʱ���ı��ļ����ݵ��ļ�

man��ʱ����԰�P �ص���ʼ

Linux�ں�Դ�����о��󲿷����豸��������

�ػ�����
shutdown -h 0

ctrl + c�����˳������Գ���ctrl + z

shell��ݼ�
atl + f ��ǰ��ת����һ���ֵĵ�һ���ַ�
atl + b �����ת����һ���ֵĵ�һ���ַ�
atl + d ɾ���ӹ�굱ǰλ�ã�����ǰ�ֵĽ�β�ַ�
ctrl + k �ӹ�굱ǰλ��ɾ�������ַ�����β
ctrl + w ���ɾ��һ���֣������Ը��ո�����Ĵ����ֺ�����
atl u ���ʱ��д
alt t ��������

bind -P �鿴���еļ��̰�

lsof
�鿴˭��ʹ���ļ�ϵͳ
lsof /opt/gcf

������н���ʹ���ļ�����ʹ����ļ�����ɾ�ˣ�Ҳ���Խ���ָ�
# lsof |grep /var/log/messages
syslogd 1283 root 2w REG 3,3 5381017 1773647 /var/log/messages (deleted)
cat /proc/1283/fd/2 > /var/log/messages 

lsof -i :21 �鿴21�˿���������ʲô����

lsof +d /usr/local/ >��ʾĿ¼�±����̿������ļ�


lsof -c vi ��ʾ����vi�򿪵��ļ�

lsof -d 4 ��ʾʹ��fdΪ4�Ľ���

lsof -r 2 /opt/gcf ÿ2��ִ��һ��

lsof -p 12 �����̺�Ϊ12�Ľ��̴�����Щ�ļ�

strace���ã�
-s strsize --ָ������ַ�����󳤶�

ltrace
ltrace -e fclose -l/lib/libc.so.6 ls

��װtelnet����
yast2 inetd
ѡ��Enable ��ѡ��telnet��ѡ��Edit
��������ǽ23�˿�
yast2 firewall
ѡ��Allowed Services
ѡ��Remote Access to Display Manager ��ѡ��Advanced
��TCP Ports������23
��������

���telnet�Ƿ�װ
chkconfig -list|grep telnet

telnet�����Ӧ�Ľ���
/usr/sbin/xinetd

telnetʹroot����ֱ�ӵ�¼��ע����
cat /etc/pam.d/login
#auth required pam_securetty.so

nm -l a.out --�г����Ŷ�Ӧ����

tarָ����ѹĿ¼
tar xvfz file.tar.gz -C /tmp

suse��
gunzip -dc gcf.tar.gz |tar -xv --һ����ѹtar.gz��ʽ
tar xzvf gcf.tar.gz --�����������
tar -ztvf gcf.tar.gz --�鿴tar.gz��ʲô�ļ�
grep "abc" `tar tf gcf.tar` --��tar������������abc���ļ�
grep "abc" `tar tzf gcf.tar.gz` ----��tar.gz������������abc���ļ�
tar xvf gcf.tar gcf/aa.txt --ֻ��ѹһ���ļ�
gunzip -dc gcf.tar.gz | tar xf - gcf/aa.txt --ֻ��ѹһ���ļ�
tar -zxvpf /tmp/etc.tar.gz /etc -- pѡ���ԭ�ļ�������
tar zcvf gcf.tar.gz gcf --һ����ѹ����tar.gz��ʽ
tar -cvf gcf.tar gcf/*.cpp --ѹ�����е�cpp�ļ�
tar -xvf gcf.tar *.txt �����е�txt�ļ���ѹ����
tar -cvf gcf.tar --exclude gcf/*.txt gcf --�ų�����*.txt�ļ�
tar -rvf gcf.tar gcf/aa.txt --���ļ�׷�ӵ�ѹ������
tar -cvf gcf.tar -T filelist --ѹ��filelist�����õ��ļ��������п���

solaris��
gtar zxvf gcf.tar.gz --һ����ѹtar.gz��ʽ
gtar ztvf gcf.tar.gz --�鿴tar.gz��ʲô�ļ�
gtar zxvf gcf.tar.gz gcf/aa.txt --ֻ��ѹһ���ļ�
grep "abc" `gtar zxvf gcf.tar.gz` ----��tar.gz������������abc���ļ�
gtar zcvf gcf.tar.gz gcf --һ����ѹ����tar.gz��ʽ

ls���ļ���С����
ls -Sl

��ȡ�����н��̵Ļ�������
vi /proc/2826/environ

��ȡ��ǰshell�����л�������
env

Linuxϵͳȫ�־������
cat /proc/sys/fs/file-max

����һ��100M�Ŀ��ļ�
dd if=/dev/zero of=hello.txt bs=100M count=1

=====
����smba����
>> smbd
>> nmbd

ֹͣ����
>> pkill smbd
>> pkill nmbd

���samba�û���
>> smbpasswd -a [USERNAME]
ע��ʹ���޸����������������SAMBA�û�
=====/

����sftp����
cd /etc/rc.d/
./pure-ftpd start
ssh-keygen -t rsa
cd /root/.ssh
cat id_rsa.pub > authorized_keys

sftp�����ļ�
/etc/pure-ftpd/pure-ftpd.conf

valgrind�ڴ��鹤��


�ڰ�װ�������޸�/etc/security/limits.conf,����imapuser hard nofile 10240��
��ҪĿ���ǷŴ�imapuser��Ӳ���Ƶľ���������ڰ�װ������ִ�иĽű�
echo -ne "\nimapuser hard nofile 10240" >> /etc/security/limits.conf

Ҳ������ʾ�˿ڱ��ĸ�����ռ��
lsof -i:31032

ץ��,ץָ���˿ڣ�ָ��ip
tcpdump port 5222 and host 10.71.158.51 -w tcpdump.cap

tcpdump����ץȡ���ط������ص��������������
tcpdump -i lo


�����ļ��������ҵ�һ��Ŀ¼��
#����һ��10M���ļ���������foobar: 
# dd if=/dev/zero of=/home/foobar bs=1K count=10000 
(2)������ļ���ʽ��: 
# mke2fs -vFm0 /home/foobar 100000000 //(1K * 10000) 
mkdir /mnt/mountpoint
(3)������ļ�mount��֮��Ϳ���������ļ���д�����ˣ��ͺñ���һ��Ŀ¼��д����һ���� 
# mount -o loop home/foobar /mnt/mountpoint 


zgrep��tar.gz�ļ��������ı�(solaris��gzcat����)
zgrep insert iMAP.swm_agent.trace.*

�����η�ʽ�鿴ϵͳ����
pstree -aclp


scp CdnCMService*.xml 10.71.158.101:/opt/iMAP/etc/conf/

�ο��ڴ�ռ��free -m|-g|-k


��������rpm -q 'xxx' ���� rpm -qf 'xxx/bin/xxxx.xx' ����ѯһ��������rpm�������֡�
Ȼ����rpm -e 'xxxxxx' ��ɾ֮��

suseͨ��yast2�޸�����Ϊ֧��ipv6�Ĳ���
yast2->�����豸->����->ͨ��ifup�Ĵ�ͳ����->��һ��->ѡ������->�༭->��ַ->�߼�->����ipv6->��һ��

linux����2G��swap����.
dd if=/dev/zero of=/ex_swap bs=1024 count=2000000 ����2G��һ���豸�ļ�
mkswap /ex_swap ������������
swapon /ex_swap ���������

��ʱ��
date 061717152008 ����ʱ����

linux������core 
/sbin/sysctl -w kernel.core_pattern=$IMAP_ROOT/var/logs/core.%e.%p
ulimit -c unlimited

linux�����ڴ溯�� shmget

�鿴ϵͳ�Ĺ����ڴ���Ϣ
ipcs -l

���ø���ip
ifconfig bond0:0 10.75.125.238 netmask 255.255.255.0

���� Python �һ���򵥵� Web ����������ͨ�� http://$HOSTNAME:8000 ���ʡ� 
python -m SimpleHTTPServer

linux����������˿ڵ��ļ�����Ӱ������˿ڱ�ռ�ã�
/etc/sysctl.conf
net.ipv4.ip_local_port_range=32768 65000

���ƽ�����������Ӱ��spawn�̣߳�
/etc/security/limits.conf
* hard nproc 1000

LD_LIBRARY_PATH��������ļ�·�����������·���������ԣ��ᵼ�¿��Ҳ�������solaris�޴�����

strace�����ļ���д
strace -o trc -e write=16 -e read=16 -ff eam_initdata

��ȡԴ��������Ķ�Ӧ��ϵ
# g++ -g -o test test.cpp
# objdump -Sd test

LD_PRELOAD�Ĺ��������ȼ����Զ���Ŀ⣬
Ŀ����ʹ���Զ���Ŀ�����滻ԭ����

LD_DEBUG���Կ�������ʱ������Ϣ
export LD_DEBUG=help ls --��ʾ��æ

pmap���һ��MAPPING�б�־˵����
a) [heap]��ʶ������ʹ��new/malloc�����С��128�ֽڵ��ڴ�ռ䣬һ������ֻ��һ��START��ַ��Ӧ
b) [anon]��ʶ������ʹ��new/malloc����Ĵ���128ֱ�ӵ��ڴ�ռ䣬һ�������ж��SRART��ַ��Ӧ 
c) [heap]��[anon]��ͬ[stack][vdso]��ַ�����ڽ���˽���ڴ档
d��/SYSV******** ��ʶ���ǹ����ڴ�,8λ*�ǹ����ڴ��basekey��
e������Ϊ����ӳ����ļ�����

root��profile�ļ���
/etc/profile

��ʱִ������۲�����
watch daem_ps

�Ѽ��ں˶�ջ��
echo t > /proc/sysrq-trigger 
Ȼ����Ϣ������
/var/log/messages


��Ӳ�̿ռ�ռ����
du --max-depth=1 -hl

linux ���ý�ѹ������������־ѹ���ļ��ļ������
find ./ -name "*.zip"|xargs -t -I{} unzip -p {}|grep ProductCacheUptTask

��jar����ָ����׺�������ؼ���
find ./ -name "*.jar"|xargs -t -I{} unzip -C -p {} "*.xml"|grep plugins

����ĳ���ļ����ĸ�zip����
find ./ -name "*zip"|xargs -t -I{} unzip -l {}|grep pagenation.ftl


��ѹָ����ϰ�ߵ��ļ���ָ��Ŀ¼
unzip /media//music.zip "*/*.mp3" -d /home/myhome/tmp


linux����svn
export LANG=zh_CN.UTF-8 �����޷����������ļ�


xshell�Զ���������ķ�����
File->Properties->Connection->Authentication
Method:Public Key
User Name: root
User Key: Browse->Generate->Next->Next
Passphrase: huawei
Confirmation: huawei
->Next->Save as a file...
���ղ����ɵ��ļ�����׷�ӵ�����ļ����棺
/root/.ssh/authorized_keys �����û������ļ����ȴ���һ���յ��ļ���

�鿴�ļ����ĸ�����ʹ�ã�
lsof|grep iEMP

Ӳ�̶�д����
hdparm -tT /dev/sda

���ӻ��ȽϹ���
vimdiff ---�˳����� :qa

svnʹ��vmdiff�ȽϹ���Ҫ��װһ��vimdiff��
#!/bin/sh
shift 5
vimdiff "$@"

svnָ���ȽϹ��ߣ�
svn di --diff-cmd /opt/gcf/tool/diffwrap.sh -r 85236:85235 http://10.72.16.78:6801/svn/CRDU_iEMP_SRS_SVN/iemp/branches/br_iEMPV1R1C00_CP5201/src/iemp-pkg/kernel-app/org.eclipse.jetty.osgi.boot/src/main/java/com/huawei/oms/osgi/jetty/internal/webapp/HostWarWebAppContext.java

��ʱ��������ȡ�����ջ����jstack_1381282039.txt��
jstack 14127 > jstack_`date +%s`.txt


�鿴�û���ǰʹ����Դ��
ps -efL|grep -w ossuser|wc -l

ulimit -uֻ��ĳ���û���������


linux ��windows�����ļ�������
mount -t cifs -o username=g00247299 //10.66.90.53/kkkkk /mnt/gcf

�鿴suse�汾��
cat /etc/SuSE-release 

������������172.19.6.47��proxy.huawei.com��IP��
export http_proxy=http://china\\g00247299:gcf.imap9@172.19.6.47:8080

������
export https_proxy= http://domain\\user:pwd@proxy_addr:port 
export ftp_proxy= http://domain\\user:pwd@proxy_addr:port 
export no_proxy=proxy_addr:port

�Ƚ���̨����ʱ��
#!/bin/bash
ssh -l root 10.67.164.170 "date '+%m/%d/%y %H:%M:%S'"
date '+%m/%d/%y %H:%M:%S'

xmlwf���xml�ļ���ʽ�Ƿ���ȷ
xmlwf file.xml


������ʾ�ӽ���
pstree -pa 2442

�����в��������Ƿ��¼�ɹ�
curl -k -d "username=admin&https://10.67.152.144:31943/authenticate.action

���������ַ�����ִ�е�����command ����exec -c
cmd="iemp ps"
command $cmd

linux��̨�Ƚ�����Ŀ¼����
diff -q -r /opt/oss /opt/oss_bak

iptable����ת��
iptables -A PREROUTING -t nat -i $HYPERFOX_IFACE -p tcp --destination-port 31945 -j REDIRECT --to-port 10043

iptablesɾ��ת��
iptables -D PREROUTING -t nat -i $HYPERFOX_IFACE -p tcp --destination-port 31945 -j REDIRECT --to-port 10043

ȡ����ʱ�˳�
export TMOUT=0

openssl���������
openssl s_server -accept 2009 -key server.pem -cert server.pem

��ȡ�ļ���չ��
ind ./ -type f|awk -F'.' '{print $NF}'

����ļ�
inotifywait -rm --timefmt '%d/%m/%y %H:%M:%S' --format '%T %e %w%f' -e create /opt/oss
������http://linux.die.net/man/1/inotifywait

������ͳ���ļ�ʹ�����
inotifywatch -v -e access -e modify -t 120 -r /opt/oss

�������ļ��仯��ִ������
inoticoming --foreground /opt/oss/envs/Product-OMPApp/OMPApp-2.2.50.001/temp/ cp /opt/oss/envs/Product-OMPApp/OMPApp-2.2.50.001/temp/{} /opt/gcf/temp/kkk \;

xtail������Դ����������tail -f ����ļ���ɾ��������

����swatch����tail�����ǹ��ܸ�ǿ�󣬿��Զ�ƥ����ַ���ִ������
swatch -c ./gcf.conf --tail-file=/opt/gcf/temp/gcf.txt
������http://www.dillonhale.com/blog/linux-tutorials/swatch-tutorial-beginners/

�鿴ϵͳglibc֧�ֵİ汾
strings /lib64/libc.so.6 |grep GLIBC_

��װglibc��
��glibcԴ��Ŀ¼��������Ŀ¼����cd���빹��Ŀ¼
mkdir build
cd build 
��LD_LIBRARY_PATH�������ܰ���./��ǰĿ¼��
../configure --prefix=/opt/glibc-2.14 
export LD_LIBRARY_PATH=/opt/glibc-2.14/lib:$LD_LIBRARY_PATH 
���ο���http://blog.csdn.net/cpplang/article/details/8462768��
���ص�ַ��http://mirror.bjtu.edu.cnglibc-2.14.tar.xz

chattr +i file.txt --ʹ�ļ������޸�,rootҲ�����޸�
chattr -i file.txt --ȥ������ı�־
lsattr�鿴�ļ�����

�鿴�������ι�ϵ
ps axjf

�鿴��ʱ�ļ��ķ�����
while [ 1 ]; do cat /tmp/dbusertmpscript-3001-execsql-redis.sh >
/tmp/watchme 2>>/dev/null; if [ -s /tmp/watchme ]; then break; fi;
done; cat /tmp/watchme; echo; rm /tmp/watchme

����ʱ�ű�ע������
while [ 1 ]; do cat /tmp/dbusertmpscript-3001-execsql-redis.sh
>/tmp/watchme 2>>/dev/null; if [ -s /tmp/watchme ]; then echo
";whoami > /tmp/ioactive_flag;chmod 666 /tmp/ioactive_flag" >>
/tmp/dbusertmpscript-3001-execsql-redis.sh; rm /tmp/watchme;
break; fi; done; sleep 1; cat /tmp/ioactive_flag


screenʹ��
1��ֱ������screen����һ��screen���ڣ�Ҫ��ͣ��������ctrl+a d,�˳�exit
2��screen -list �鿴����Щscreen
3��screen -r xxx�л���screen session

sshfs����Զ���ļ�
sshfs root@10.67.252.200:/opt/gcf /mnt
fusermount -u /mnt --���

shc���Խ�shellת���ɿ�ִ���ļ�

multitail��ͬʱtail����ļ������ܱ�tailǿ��
http://www.oschina.net/p/multitail/similar_projects
http://www.ubuntugeek.com/multitail-view-multiple-logfiles-windowed-on-console.html
--retry-all ����������ļ���ɾ��Ҳ���Լ���
--mergeall �����ļ��ϲ�һ���ļ�

tmux #����tmux
tmux ls #�г��Ự(tmux list-session )
tmux attach #Tmux�������ӵ�֮ǰ�Ĵ��ڿ�ʹ��
tmux attach -t session #����ĳ���Ự
tmux -r #�����ϴζϿ���session
tmux kill-session #�ر��ϴ�tmux�򿪴���
tmux kill-server #�ر�����tmux�򿪴���
http://blog.jobbole.com/87278/
http://blog.jobbole.com/87584/

���������ں˼����ߣ���������extrace
https://github.com/brendangregg/perf-tools

�����ظ�������
extrace -d |grep -v /opt/gcf/temp |sh my.sh
my.sh������Ϊ��
#!/bin/sh
if [ $# -gt 0 ];then
exec 0<$1;
#�ж��Ƿ���������ļ�����������룬�����ļ��󶨵���׼����
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
#ͨ����׼����ѭ����ȡ����
exec 0&-;
#�����׼�����

�½�һ��swap����
dd if=/dev/zero of=/home/swap-fs bs=1M count=512
mkswap /home/swap-fs
swapon /home/swap-fs
��ʱ��ʹ��д��/etc/fstab�ļ�������
/home/swap-fs swap swap defaults 0 0



linux���ֹ���Դ�룬����bash
https://ftp.gnu.org/gnu/

Ŀ¼�������û���д���У�
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

help�鿴��������İ���
help set

ps afx --�������ӽ���չʾ

��ָ������·��
updatedb --localpaths='/opt/gcf/unjar/deploy' --output=/opt/gcf/locate.db
locate -d /opt/gcf/locate.db Main.java

locateҪ������������������Ҫ���������ļ�(updatedb�ǽű������ԸĽű������·��)
/usr/bin/locate
/usr/bin/updatedb
/usr/lib64/find/bigram
/usr/lib64/find/code
/usr/lib64/find/frcode

����һ��α���http�����
socat TCP-LISTEN:80,reuseaddr stdout

���ض˿�ת����windows���ã�
socat tcp-listen:5656,fork tcp:localhost:1337

socat windows�汾
https://github.com/StudioEtrange/socat-windows

inotifywaitִ�б��������Ľ���취
sysctl -w fs.inotify.max_user_watches="99999999"

python websockt�ͻ���
https://pypi.python.org/pypi/websocket-client

�����û�
pam_tally2 -u root -r
���ࣺ��������
����
˵����Ĺ۵㣬�������۰�!