shell
����ֻ������ 
name=Tom 
readonly name

���ļ����ɵ�����(solaris)
ls -e 

linux����ʾ����
stat core.imapsvcd.27166

�ݹ�Ŀ¼�µ������ļ���������Ŀ¼
ls -Rl /opt/gcf

echo * �����ǰĿ¼������Ŀ¼���ļ�

echo -e "abc\n ddd" ���԰ѻس������

echo `ls` �����������лس���echo�Ժ�س����Զ����ɿո�

command > filename 2>&1 �Ѱѱ�׼����ͱ�׼����һ���ض���һ���ļ���

windows�ϻ�ȡ��ǰĿ¼ echo %cd%

if�����ֱȽ�
if [ $str -gt 100 ] 
-gt ����
-ge ���ڵ���
-lt С��
-le С�ڵ���
-eq ����
-ne ������

�ж���������
if [ str1 = "aa" ] && [ str2 = "bb" ]
if [ str1 = "aa" ] || [ str2 = "bb" ] 
if [ ! $? -eq 0 ] ȡ��

��ȡ��������ֵ
func
ret=$?
������ ret=`func`

Ϊ���ظ����ô��룬���Խ�����д�ɿ⺯����Ȼ���ڽű���ʹ��
. libraryfile

�źŴ���
trap "echo ___ $0 interrupted ___; exit 1" 2

��whileѭ�����ļ��ж�ȡ����
while read LINE
do
echo $LINE
done < name.txt

�ű�����Ҫ��ȡ���ú����ķ���ֵʹ�� $?

ɾ��shell����
unset function_name

awk��=�ָ�
echo "a=b" | awk -F= '{print $1}'

echo "abc kkk haha"|awk '{if($1~/abc/) print $2}' --�����һ������"abc"
echo "abcd kkk haha"|awk '{if($1=="abc") print $0}' --��ȫƥ��abc
echo "abdcd kkk haha"|awk '{if($1!~/abc/) print $0}' --�ǣ�������abc��
echo "abdcd kkk haha"|awk '{if($1!="abc") print $0}' --������
echo "abdcd 60 70"|awk '{if($2<$3) print $0}' --С��
echo "Gcf 60 70"|awk '/[Gg]cf/'
echo "Gcf 60 70"|awk '$1 ~/^.c/' --��ͷ��2���ַ���c
echo "gcf 60 70"|awk '$1 ~/gcf|gechengfeng/' --ƥ��gcf����gechengfeng
echo "gcf bantian huawei" |awk '/^gcf/' --gcf��ͷ
echo "gcf bantian huawei" |awk '{if ($1=="gcf"&&$2=="bantian") print $0}' --���ж�
echo "gcf bantian huawei" |awk '{print NF}' --��ʾ��������
echo "gcf bantian huawei" |awk '{if(NF>2)print $0}' --�����¼��������2
echo "/opt/iMAP/etc/conf/imap.cfg"|awk -F/ '{print $NF}' --��ʾ�ļ���
echo "gcf bantian huawei" |awk '{name=$1;gongsi=$3; print gongsi}' --������������
echo "gcf bantian huawei" |awk '{if($1=="gcf") ($1="gechengfeng"); print $0}' --�������¸�ֵ
echo "gcf bantian huawei" |awk '{if($1=="gcf") {$1="gechengfeng"; print $0};}' --ע�����ŵ�λ��
echo "gcf 60 80" |awk '{$4=$2+$3; print $0}' --�����µ���
ls -l |awk '/^[^d]/ {print $9"\t"$5} {tol+=$5} END {print "total KB:" tol}' --ͳ���ļ���С
echo "gechengfeng 02868 " |awk 'gsub(/02868/,65017) {print $0}' --ʹ�ú���gsub�滻����65017�滻02868
echo "gechengfeng" | awk '{print index($1,"feng")}' --����8
echo "gechengfeng" | awk '{print length($1)" " $1}' --��ȡ�ַ����ĳ���
awk 'BEGIN {print match("abcd","c")}' --ƥ���ҵ����ַ�λ�ã��Ҳ�������0
echo "gcf 02868"|awk 'sub(/02868/,"65017",$0) {print $0}' --sub�������滻
echo "gechengfeng"|awk '{print substr($1,3,99)}' --substr��ȡ�ַ���
echo "gechengfeng"|awk '{print substr($1,3)}' --ͬ��
echo "65"|awk '{printf "%c\n",$0}' --ʹ��printf��ʽ��
echo "gcf 1981"|awk '{if ($2 > Year) print $0}' Year=1980 --ʹ�ñ���

AAA="<databasename>imap_db</databasename>";expr $AAA : '<databasename>\(.*\)</databasename>'
--����xml������imap_db ע��ð������Ҫ�пո�

awk�ű��ļ�
[root@femgwei]# cat test.awk 
!/bin/awk -f ---���ﲻ����#��ǰ�棡��
#sample 
echo "gcf 1981"|awk '{if ($2 > Year) print $0}' Year=1980


�ַ���ת�����������㣨\*ǰ��Ҫ�пո�
$(expr $DBSIZE \* 512)

export gcf=/opt/iMAP/etc/conf/imap.cfg
echo ${gcf##/*/} -- imap.cfg
echo ${gcf#/*/} -- iMAP/etc/conf/imap.cfg
echo ${gcf%/*} -- /opt/iMAP/etc/conf
echo ${gcf%%/*} -- ��
echo ${gcf//opt/kkkk} -- /kkkk/iMAP/etc/conf/imap.cfg

����������
func()
{
echo $1
}
func abcd

��дת��ΪСд
echo "ABCddd"|tr A-Z a-z

�������м��ļ�ֱ���޸��ļ� -i
sed -i 's/gcf/gechengfeng/g' gcf.test


awk��sed������
Linux.O'Reilly.SED and AWK.chm
O'Reilly - SED and AWK.examples
OReilly - Effective awk Programming 3rd Edition.

����ʾ��־���������ض��򼴿�
>/dev/null 2>&1


&>filename --��stdout��stderr���ض���filename
2>filename --��stderr�ض���
2>&1 --�ض���stderr��stdout
ls /opt/gcf > /tmp/cmd.out 2>&1 --ע��2>&1�����

echo 1234567890 > File # д�ַ�����"File".
exec 3<> File # ��"File"���Ҹ�������fd 3.
read -n 4 <&3 # ֻ��4���ַ�.
echo -n . >&3 # дһ��С����.
exec 3>&- # �ر�fd 3.
cat File # ==> 1234.67890
# ����洢.

$RANDOM --�����

echo -e "b\n\a\nc"|sort -f --����-f��ʾ���Դ�Сд
echo -e "111\n2n\88\nc"|sort -fn --����-n��ʾ����������
sort -u --ȥ���ظ���

echo -e "gechengfeng \n 65017" --��ʾ�س�
echo -e "\a" --��ʾ����

echo ${#NAME} --���ر������ַ�����
expr length $NAME --Ҳ�Ƿ��ر������ַ�����
stringZ=abcABC123ABCabc; echo `expr match "$stringZ" 'abc[A-Z]*.2' 
--ƥ���ַ�����ͷ���Ӵ����ȷ���8
str=test.doc;echo `expr index "$str" doc` --���ص�һ�γ��ֵ�����λ��
str=test.doc;echo ${str:5} --�Ӵ���ȡ������ doc
str=test.doc;echo ${str:0:4} --����test ����4���Ӵ��ĳ���
str=test.doc;echo ${str:(-3)} --�Ӻ�߽�ȡ��С���ſ���ȥ��
str=test.doc;echo `expr substr $str 1 4` --����test������4���Ӵ��ĳ���
str=test.doc; expr $str : '\(.*\).doc' --����test��\(�ǿ�ͷ����˼��\(�ǽ�β����˼��ð������Ҫ�пո�
stringZ=abcABC123ABCabc; echo `expr match "$stringZ" '\(.[b-c]*[A-Z]..[0-9]\)'` --����abcABC1��
stringZ=abcABC123ABCabc;echo `expr match "$stringZ" '.*\([A-C][A-C][A-C][a-c]*\)'` 
--�ӽ�β��ʼ��ȡ������ABCabc
stringZ=abcABC123ABCabc; echo ${stringZ#a*C} --��ȥ���ƥ����Ӵ�������123ABCabc
stringZ=abcABC123ABCabc; echo ${stringZ##a*C} --��ȥ�ƥ����Ӵ�������abc
stringZ=abcABC123ABCabc;echo ${stringZ%b*c} --��β����ʼĨȥ���ƥ����Ӵ�������abcABC123ABCa
stringZ=abcABC123ABCabc;echo ${stringZ%%b*c} --��β����ʼĨȥ�ƥ����Ӵ�������a
NAME=gcf; echo ${NAME/f/feng} 
--��feng�滻f��ֻ�滻��һ��ƥ���
NAME=gcff; echo ${NAME//f/feng} --��feng�滻���е�f��ע����//
stringZ=abcABC123ABCabc;echo ${stringZ/#abc/XYZ} --��'XYZ'�滻ǰ�˵�'abc' ������XYZABC123ABCabc
stringZ=abcABC123ABCabc;echo ${stringZ/%abc/XYZ} --��'XYZ'�滻��˵�'abc'������abcABC123ABCXYZ

shell if �Ƚ�
[ -a FILE ] ��� FILE ������Ϊ�档
[ -b FILE ] ��� FILE ��������һ���������ļ���Ϊ�档
[ -c FILE ] ��� FILE ��������һ���������ļ���Ϊ�档
[ -d FILE ] ��� FILE ��������һ��Ŀ¼��Ϊ�档
[ -e FILE ] ��� FILE ������Ϊ�档
[ -f FILE ] ��� FILE ��������һ����ͨ�ļ���Ϊ�档
[ -g FILE ] ��� FILE �������Ѿ�������SGID��Ϊ�档
[ -h FILE ] ��� FILE ��������һ������������Ϊ�档
[ -k FILE ] ��� FILE �������Ѿ�������ճ��λ��Ϊ�档
[ -p FILE ] ��� FILE ��������һ�����ֹܵ�(F���O)��Ϊ�档
[ -r FILE ] ��� FILE �������ǿɶ�����Ϊ�档
[ -s FILE ] ��� FILE �����Ҵ�С��Ϊ0��Ϊ�档
[ -t FD ] ����ļ������� FD ����ָ��һ���ն���Ϊ�档
[ -u FILE ] ��� FILE ������������SUID (set user ID)��Ϊ�档
[ -w FILE ] ��� FILE ��� FILE �������ǿ�д����Ϊ�档
[ -x FILE ] ��� FILE �������ǿ�ִ�е���Ϊ�档
[ -O FILE ] ��� FILE ����������Ч�û�ID��Ϊ�档
[ -G FILE ] ��� FILE ����������Ч�û�����Ϊ�档
[ -L FILE ] ��� FILE ��������һ������������Ϊ�档
[ -N FILE ] ��� FILE ���� and has been mod���ied since it was last read��Ϊ�档
[ -S FILE ] ��� FILE ��������һ���׽�����Ϊ�档
[ FILE1 -nt FILE2 ] ��� FILE1 has been changed more recently than FILE2, or ��� FILE1 exists and FILE2 does not��Ϊ�档
[ FILE1 -ot FILE2 ] ��� FILE1 �� FILE2 Ҫ��, ���� FILE2 ������ FILE1 ��������Ϊ�档
[ FILE1 -ef FILE2 ] ��� FILE1 �� FILE2 ָ����ͬ���豸�ͽڵ����Ϊ�档
[ -o OPTIONNAME ] ��� shellѡ�� ��OPTIONNAME�� ������Ϊ�档
[ -z STRING ] ��STRING�� �ĳ���Ϊ����Ϊ�档
[ -n STRING ] or [ STRING ] ��STRING�� �ĳ���Ϊ���� non-zero��Ϊ�档
[ STRING1 == STRING2 ] ���2���ַ�����ͬ�� ��=�� may be used instead of ��==�� for strict POSIX compliance��Ϊ�档
[ STRING1 != STRING2 ] ����ַ����������Ϊ�档
[ STRING1 < STRING2 ] ��� ��STRING1�� sorts before ��STRING2�� lexicographically in the current locale��Ϊ�档
[ STRING1 > STRING2 ] ��� ��STRING1�� sorts after ��STRING2�� lexicographically in the current locale��Ϊ�档
[ ARG1 -eq ARG2 ] ���� 
[ ARG1 -ne ARG2 ] ������ 
[ ARG1 -gt ARG2 ] ���� 
[ ARG1 -lt ARG2 ] С��
[ ARG1 -le ARG2 ] С�ڵ���
[ ARG1 -ge ARG2 ] ���ڵ���

if [ -f /opt/gcf/main.cpp -a -f /opt/gcf/a.out ] --�߼��� -a
-o --�߼���
! --�߼���

�ַ����Ƚ�
= �����ַ�����ȣ����������Ƚ�ʱ��ü������� if [ "$STR1" = "$STR2" ]
!= �����ַ��������
-z �մ�
-n --�ǿմ�

��ֵ�Ƚ�
if [ "$NUM" -eq "1330" ] --�Ƿ����
-eq --���
-ne --����
-gt --����
-lt --С��
-le --С�ڵ���
-ge --���ڵ���

expr 30 \* 3 --�˺ű����\

�����ļ��Ƿ���дȨ��
if [ ! -w test.txt ] ---����ļ�����д

�����ű��Ƿ�Ϊ����ʽģʽ���ǽ���ģʽ�� cron��at
if [ -t ]

���Ի��������Ƿ�������
if [ -z $EDITOR ] ---���û�����û�������

��¼�û����� LOGNAME�����������ִ�нű����û���˭

null����
if [ "`ls`" = "" ]
then
echo "is empty"
else : #do nothing
fi

case���:
read ANS
case $ANS in
1) echo "you select 1"
;;
2) echo "you select 2"
*) echo "this is not between 1 and 2"
exit 1
;;
esac

ʹ��caseʱ������ָ�� ��|��������Ϊ�������
case $TERMINAL in
vt100|vt102) TERM=vt100
;;

�򵥵�forѭ��
for loop in 1 2 3
do
echo $loop
done

IFS �ָ������ֶ�
Ĭ��Ϊ���ո��Ʊ�������з��� IFS=' \t\n'


���õ�grepѡ���У�
-c ֻ���ƥ���еļ�����
-i �����ִ�Сд��ֻ�����ڵ��ַ�����
-h ��ѯ���ļ�ʱ����ʾ�ļ�����
-l ��ѯ���ļ�ʱֻ�������ƥ���ַ����ļ�����
-n ��ʾƥ���м��кš�
-s ����ʾ�����ڻ���ƥ���ı��Ĵ�����Ϣ��
-v ��ʾ������ƥ���ı��������С�
-E ������ʽ
-o ֻ����ƥ�����
-P ����perl��������ʽ

���ļ��в����ַ���
grep "string" file.txt

��ȷƥ��
grep '\<48\>' file.txt

ƥ��K��ͷ��D��β��5���ַ����ַ���
grep 'K...D' data.f

ʹ��grepƥ�䡰�롱���ߡ���ģʽ
grep -E '219|216' data.f

echo -e "abc \n aab"|grep "a\{2,\}" --ƥ�����ٳ���2��a�ģ�����aab��ע�ⲻ��-E����

echo "loveinglove"|grep "\(love\)ing\1" --\1����ǰ��ƥ���love


ƥ�����
grep '^$' myfile

ƥ�������ַ� ��$ . ' " * [] ^ | \ + ? ,�������ض��ַ�ǰ��\

egrep��һ�����������ǿ�����һ���ļ���Ϊ������ַ�������
$pg grepstrings
484
47
$egrep -f grepstrings data.f

uniq��������ã���ʾΨһ���У�������Щ�����ظ�����ֻ��ʾһ�Σ� 
uniq file.txt


����ʾ�����ظ�����
uniq -d file.txt

��ʾ�ļ���û���������ֵ��С� 
uniq -u file.txt

wait����ȴ������˳�

��������typeset��ksh�������������ñ������Եġ��磺
$ typeset -u NAME
$ NAME="Abc"
$ echo $NAME
ABC
ͬ��typeset -l ��������Сд��ĸ����

ִ��awk�ű�
siteA:/opt/gcf # cat ./test.awk 
#!/bin/awk -f
BEGIN{
record="123#456";
split(record, myarray, "#")}

END{for (i in myarray) {print myarray[i]}}

siteA:/opt/gcf # ./test.awk /dev/null ----����Ҫ��/dev/null�ſ�������
123
456
siteA:/opt/gcf # 

echo "i==1"|awk -F"==" '{print $2}' ---�ַ�����Ϊ�ָ���
echo "gcf=1981|65017"|awk -F"[=|]" '{print $1"\t"$2"\t"$3}' -- =��|��Ϊ�ָ���
echo "gcf,1981,65017"|awk -F"," '{i=1;while(i<=NF) {print $i;i++}}' --whileѭ��
echo "gcf,1981,65017"|awk -F"," '{for(i=1;i<=NF;i++) {print $i}}' --forѭ��
ps -eaf|awk '{system("pwdx "$2"")}' --ִ��system --����ֻ��suse֧�֣�solaris��������
awk 'BEGIN {"date"|getline d; print d}' --ͨ���ܵ�ִ������
awk '{if($1~/gcf/) print $0}' file.txt --�����1�а���gcf
awk '{if($1!~/gcf/) print $0}' file.txt --�����1�в�����gcf
awk '$1=="gcf" {print $0}' file.txt --��ȫƥ��gcf
awk '$1!="gcf" {print $0}' file.txt --��ƥ��gcf
awk '{if($2>$3) print $0}' --��2�д��ڵ�3��
awk '{if($2>$3 && $1~/gcf/) print $0}' --������
awk '{if($2>$3 || $1~/gcf/) print $0}' --������
awk '{if($1~/gcf/) {$1="gechengfeng";print $0}}' --�滻��ֵ
awk '{if($1~/gcf/) {$1="gechengfeng";$4="test";print $0}}' --������
awk 'tol+=$2;END{print tol}' --�����
STR="gcf";echo $STR|awk '{print $STR}' --��������������awk

awkʹ�û�������
ls -tl "$NAME"*gz |awk 'BEGIN{tol=0} {tol+=$5} END{print tol" "NAME}' 
--�����Ǵ��
ls -tl "$NAME"*gz |awk 'BEGIN{tol=0} {tol+=$5} END{print tol" "NAME2}' NAME2=$NAME --����ǶԵ�

awk����
gsub(/35/,25) --����Ǹ���¼��35�滻Ϊ25
index("gcf","cf") --����2
length("gcf") --����3
match("gcf",/c/) --����2������ʹ��������ʽ
split("123#245", my, "#") --my[1]="123" , my[2]="245",����ֵ��2
str=gcf;sub(/f/,"feng",str) --str��Ϊgcfeng
substr("gcf",2,1) --����c����2���ַ���ʼ��1���ַ�
substr("gcf",2,1) --����c����2���ַ���ʼ����������ַ�

sed
sed -n '2p' --��ʾ��2�У�һ��Ҫ��-n
sed -n '1,5p' --��ʾ1��5��
sed -n '6q;1,5p' --�ٶȸ���
sed -n '/main/'p --��ѯmain���У�Ҫ��p
sed -n '/[Ll]ove/'p --ƥ��Love����love
sed -n '4,/main/'p --�ڴӵ�4�п�ʼ����һ��ƥ����֮�������
sed '1,$p' --��ʾ�����ļ�
sed -n '/main/=' -��ӡ�к�
sed -e '/main/=' --��ӡ�кţ�����ʾ�����ļ�
sed '1,5d' --ɾ����1��5��
sed '/main/d' --ɾ��ƥ�����
sed '$d' --ɾ�����һ��
sed 's/int/long/g' --��int�滻Ϊlong�����û��gֻ�滻���еĵ�һ��ƥ���int
sed '/int/ s/int/long/g' --�ٶȸ���
sed 's/int/long/w file.out' --���滻���������һ���ļ���
sed 's/printf/ACE_OS::&/p' --��ƥ��ģʽǰ���ַ���
sed 's/main/&_i::/p' --��ƥ��ģʽ������ַ���
echo -e "love"|sed -n 's/love/***&***/'p ---����***love***
sed -n 's/\(love\)able/\1rs/p' 
--love�����Ϊ1������loveable�ᱻ�滻��lovers�������滻���лᱻ��ӡ������
sed '/int/q' --ƥ�䵽��1�����˳���
sed '/^$/d' --ɾ������
sed '/./!d' --ͬ��
echo "file"|sed 's/$/.tar.gz/g' --�����ļ���չ��
echo -e "6000"|sed -n '/0\{3,\}/'p --ƥ���������3��0����
echo -e "60000"|sed -n '/0\{3,9\}/'p --ƥ�����3��9��0����
REP=long; sed "s/int/$REP/g" main.cpp --��shell�л�ȡ������ע����˫����
echo -e "111\n222\n333\n444"|sed -n '/222/,/444/p' --��ʾ222��444֮�������
echo -e " gcf"|sed 's/[ ]*//g' --ɾ���ո�
sed 's/[ \t]*$//' --��ÿһ����β�ġ��հ��ַ������ո��Ʊ����ɾ��
sed 's/^[ \t]*//;s/[ \t]*$//' --��ÿһ���е�ǰ������β�Ŀհ��ַ�ɾ��
sed 's/^/ /' --��ÿһ�п�ͷ������5���ո�ʹȫ�������ƶ�5���ַ���λ�ã�
sed -n '/regexp/!p' --ֻ��ʾ������ƥ��������ʽ���� 
sed '/regexp/d' --��!p���� 
sed -n '/regexp/{g;1!p;};h' -- ���ҡ�regexp������ƥ���е���һ����ʾ��������������ʾƥ����
sed -n '/regexp/{n;p;}' --���ҡ�regexp������ƥ���е���һ����ʾ��������������ʾƥ���� 
sed '/AAA/!d; /BBB/!d; /CCC/!d' --��ʾ������AAA������BBB����CCC�����У��������
sed -n '/^.\{65\}/!p' --��ʾ����65�������ַ�����
sed '/^.\{65\}/d' --ͬ��
sed '52q;d' --��ʾ��52�У�������ı���Ч�ʸ��ߣ�
sed '$!N; /^\(.*\)\n\1$/!P; D' --ɾ���ļ������ڵ��ظ��У�ģ�⡰uniq���� 
sed -n 'G; s/\n/&&/; /^\([ -~]*\n\).*\n\1/d; s/\n//; h; P' --ɾ���ظ���
sed '$!N; s/^\(.*\)\n\1$/\1/; t; D' --ɾ�����ظ�����������У�ģ�⡰uniq -d���� 
sed '/./,$!d' --ɾ���ļ����������п��� 
sed -e :a -e '/^\n*$/{$d;N;ba' -e '}' --ɾ���ļ�β�������п���

sed '/test/,/check/s/$/sed test/p' example
--����ģ��test��west֮����У�ÿ�е�ĩβ���ַ���sed test�滻

sed -i '1,1s/10/8/' entries --�滻��һ�е�10Ϊ8

linux�Ͻ��svn�汾�ϵ�����
find ./ -name "entries"|grep ".svn"|xargs sed -i '1,1s/10/8/'

���find��������ļ��������ո�����⣺
find ./ -name "*.java" -print0|xargs -0 grep "iemp.instance"

find�ڵ�ǰĿ¼������
find ./ -maxdepth 1 -name "*.txt"

find�ļ��������ִ�Сд: -iname

find�ų�ָ����Ŀ¼��
find ./ -path conf -prune -o -name bme.web_en.properties

�ų����Ŀ¼
find . \( -path "./ab" -o -path "./cd" \) -prune -o -name "datafile*" -print

�����������ַ������ļ�
find ./ -name "*.sh"|xargs grep -L "#\!"

��������ĳ���
echo "01234567890123452789"|xargs -s 31 -t -x

sed -e '1,5d' -e 's/test/check/' example-
--(-e)ѡ��������ͬһ����ִ�ж��������������ʾ��
��һ������ɾ��1��5�У��ڶ���������check�滻test��
�����ִ ��˳��Խ����Ӱ�졣�������������滻���
��ô��һ���滻���Ӱ��ڶ����滻����Ľ����

sed --expression='s/test/check/' --expression='/love/d' example
--һ����-e���õ�������--expression�����ܸ�sed���ʽ��ֵ��

sed '/test/r file' example
--file������ݱ�����������ʾ����testƥ����к��棬
���ƥ����У���file�����ݽ���ʾ������ƥ���е����档

$ sed '/^test/a\\--->this is a example' example
--'this is a example'��׷�ӵ���test��ͷ���к��棬sedҪ������a������һ����б�ܡ�

echo "test"|sed '/^test/a\this is a example'
--׷������a����test��ͷ���к������� this is a example

echo "test"|sed '/^test/i\this is a example'
--��test֮ǰ���� this is example

��һ����n���� 
$ sed '/test/{ n; s/aa/bb/; }' example
---���test��ƥ�䣬���ƶ���ƥ���е���һ�У�
�滻��һ�е�aa����Ϊbb������ӡ���У�Ȼ�������

echo -e "testgcf\ngcf"|sed -e '/test/{n; s/gcf/gechengfeng/;}'
--�ѵ�2�е�gcf�滻Ϊgechengfeng

echo -e "testgcf\ngcf"|sed -e '/test/{N; s/gcf/gechengfeng/;}'
--�ѵ�1�е�gcf�滻Ϊgechengfeng

���Σ�y���� 
$ sed '1,10y/abcde/ABCDE/' example
--��1--10��������abcdeת��Ϊ��д��ע�⣬������ʽԪ�ַ�����ʹ��������

sed G file --��ÿ�к������ӿ���

sed '/^$/d;G' 
--��ԭ�������п���ɾ������ÿһ�к�������һ���С�
������������ı���ÿһ�к��潫����ֻ��һ���С�

sed 'G;G' --��ÿһ�к����������п���

sed '/regex/{x;p;x;}' -- ��ƥ��ʽ����regex������֮ǰ����һ����

sed '/regex/G' --��ƥ��ʽ����regex������֮�����һ����

sed '/regex/{x;p;x;G;}' --��ƥ��ʽ����regex������֮ǰ��֮�������һ���� 

echo -e '1\n2\n3\n4' | sed -e 'N; s/\n/ /'
--�ϲ���

sed -e 's/yellow/blue/g' white.dat red.dat black.dat 
--ͬʱ���������ļ�

echo "akkbgc"|sed -e 'y/abc../xyz../'
--��a�滻��x��b�滻��y��c�滻��z

sed -e 'y/abc/ABC' filename
--��Сд��abcת���ɴ�д��ABC

sed -e '/zhengxh/c hhhh' filename
--��ʾ�Ѻ����ַ���zhengxh���У��ó�hhhh

sed -i '1 i\gcf' main.cpp
--���ļ���һ��ǰ����gcf������һ�У���-i���޸��ļ�

echo "gcf ZHONGGUO" | sed '/gcf/ s/.*/\L&/g' --��дת����Сд

echo "gcf zhongguo" | sed '/gcf/ s/.*/\U&/g' --Сдת���ɴ�д

��������
��s / \ . $ / / g�� ɾ���Ծ���β��
��-e /abcd/d�� ɾ������a b c d����
��s / [ ] [ ] [ ] * / [ ] / g�� ɾ��һ�����Ͽո���һ���ո����
��s / ^ [ ] [ ] * / / g�� ɾ�����׿ո�
��s / \ . [ ] [ ] * / [ ] / g�� ɾ����������������ո񣬴�֮��һ���ո�
��/ ^ $ / d�� ɾ������
��s / ^ . / / g�� ɾ����һ���ַ�
��s /CO L \ ( . . . \ ) / / g�� ɾ������C O L�ĺ�������ĸ
��s / ^ \ / / / g�� ��·����ɾ����һ��\
��s / [ ] / [ ] / / g�� ɾ�����пո���t a b�����
��S / ^ [ ] / / g�� ɾ����������t a b��
��s / [ ] * / / g�� ɾ������t a b��

����
������ֵ
func()
{
return 0
}
ret=$?
if [ $ret = 0 ];then
echo "ok"
fi

�滻ϵͳ��ls�������дһ����Ϊls�ĺ���
ls()
{
echo "my ls"
}
ret=`ls`
echo $ret


�������ṹ��
if grep "main" main.cpp > /dev/null 2>&1 ---�ж�һ������ĳɹ����
then
echo "ok"
else
echo "not"
fi

if [ $# -lt 3 ] --���ű��Ĳ������Ƿ�С��3

if [ -d "/opt/gcf" ] --�����Ŀ¼

cd /opt/gcf
if [ $? = 0 ] --���cd�����0

ARGC=$#
if [ $ARGC = 1 ]; then ---�жϲ���������ע�� = ����һ��Ҫ�пո�
echo "1"
elif [ $ARGC = 2 ]; then
echo "2"
else
echo "other"
fi

case����
read ANS
case $ANS in
1)
echo "1"
;;
2|5) --2����5
echo "2 or 5"
;;
10);; --ʲôҲ����
*)
echo "other"
;;
esac ---case���ʷ�����д

for params --ѭ��ʹ�ýű�������params����д������������
do
echo $params
done

for loop in 1 2 3
do
echo $loop
done

for loop in "orange red blue"
do 
echo $loop
done

for loop in "gechengfeng 65017" "qinxiaoling 00000" --ÿ��Ԫ�ش���������
do
echo $loop
done

for loop in `ls`
do
echo $loop
done


�򵥵�until
LOCK_FILE=/tmp/process.LCK
until [ ! -f $LOCK_FILE ] --����ļ���ɾ��������ѭ����������������Ӳ�̿ռ�
do
sleep
done
echo "file deleted"

�򵥵�whileѭ��
COUNTER=0
while [ $COUNTER -lt 5 ]
do
COUNTER=`expr $COUNTER + 1`
echo $COUNTER
done

while read FILM --ѭ������Ӽ��̶������Ϣ
do
echo $FILM
done

��whileѭ�����ļ��ж�ȡ����
while read LINE
do
echo $LINE
done < names.txt

SAVEDIFS=$IFS
IFS=,
while read NAME YEAR ID --���ļ��˶�ȡ����������Զ��ŷָ�
do
echo "name:" $NAME "year:"$YEAR "ID:"$ID
done < file.in
IFS=$SAVEDIFS --һ��Ҫ�ָ�IFS

while : --����һ����ѭ����Ӧ�ÿ�������Զ������


��sh scriptname����һ��Bash�ű������ֹ����Bash����չ���ԡ���˽ű����Ի���˶�����ʧ��

#!/bin/rm --ִ�нű��ű��ᱻɾ��

(a=hello; echo $a) --�����飬���¿�һ��shell��ִ��

{ echo "a";echo "b" } > file.out 
---{} ��һ�������������뵽һ���ļ��С�{}������shell��
---{} Ҫ�пո�


command &>filename ���ض�������command��׼�����stdout���ͱ�׼����stderr�����ļ�filename��
command >&2 ������command�ı�׼�����stdout���ض��򵽱�׼����stderr��


�ܵ����ӽ���������, ��˲�����������ĸı�
variable="initial_value"
echo "new_value" | read variable
echo "variable = $variable" # variable = initial_value

echo "-n" --ʲôҲ������������п�����-��ͷ�ģ�Ҫ���

R=$(ls) --�൱��R=`ls`

���壺
[ [ $foo =bar ] ] ---Ϊ�˽��foo��-��ͷ[ bar = "$foo" ]

cd `dirname "$f"` --��ȱ�ݵģ��ո�
cd "$(dirname "$f")" --����ո�����
["$foo" = bar && "$bar" = foo ] --wrong
[ bar = "$foo" -a foo = "$bar" ] --right
[ bar = "$foo" ] && [ foo = "$bar" ] --right
[[ $foo = bar && $bar = foo ]] --right
[[ $foo > 7 ]] --ֻ���ж��ַ����������ж���ֵ
[ $foo -gt 7 ] --�����ж���ֵ���������$foo������ֵ�ͻ����
if [grep foo myfile] --���
if grep foo myfile > /dev/null; then --�Ե�
if [bar="$foo"] --���,û�пո�
if [ bar = "$foo" ] --�Ե�
cat file | sed s/foo/bar/ > file --������ͬһ�ܵ�������ͬʱ��дһ���ļ�
sed 's/foo/bar/g' file > tmpfile && mv tmpfile file --�Ե�,ʹ����ʱ�ļ�

echo <<EOF --���
Hello world
EOF

cat <<EOF --�Ե�,��ӡHello word
Hello world
EOF 

cd /foo; bar --cd���ܻ����,bar�����벻����Ŀ¼ִ��
cd /foo && bar --�Ե�,Ӧ���жϷ���ֵ

find ... -type d | while read subdir; do
cd "$subdir" && whatever && ... && cd -
done 
��������д��
find ... -type d | while read subdir; do
(cd "$subdir" && whatever && ...) ---�Ͳ���cd -��,��Ϊ���ŻῪһ���µ�shellִ��
done 

find�����ɾ��
find ./ -name test.sh -exec rm -rf {} \;

ͬ����cpp��h�ļ�
find -name "*.cpp" -o -name "*.h"

[ bar == "$foo" ] --���
[ bar = "$foo" ] --�Ե�
[[ bar == $foo ]] --�Ե�

for i in {1..10}; do ./something &; done --���,&���治Ӧ���ٷ�;
for i in {1..10}; do ./something & done --�Ե�

echo "Hello World!" --��Ϊ��!,�ᱨ��-bash: !": event not found
echo 'Hello World!' --����ʹ�õ�����
set +H --����ʹ���������ȡ����������ʷ�滻
echo "Hello World!"

for x in $*; do --�������пո��������,�� ./test.sh 'arg 1' arg2
echo "$x"
done

for x in "$@"; do --�Ե�
echo "$x"
done

��������ʱ $* �� $@ ����ͬ�ģ���"$*" �ᱻ��չ��һ���ַ������� "$@" �ᱻ��չ��ÿһ��������

i=1; i=$i+1; echo $i --���صĲ���2������1+1
i=1; i=`expr $i + 1`; echo $i --�Եģ�����2
i=$(($i+1)) --˫����Ҳ�ɽ������ i=$((($i+2)/2324%123*($j+34)))

trap���Խű�

trap 'echo $LINENO' ERR --��һ������ط���״̬ʱ��ӡ�к�
DEBUG --�ű���ÿһ������ִ��֮ǰ
EXIT --��һ���������˳��������ű�ִ�����

trap 'echo $BASH_COMMAND' DEBUG --��ʾ���� --man bash ���Կ�����ϸ����Ϣ
BASH_ARGC --�ű��Ĳ�������
BASH_ARGV --�ű��Ĳ���

���Ե�ʱ�������tee�����ʱ���ļ����磺
ipaddr=`/sbin/ifconfig | grep 'inet addr:' | grep -v '127.0.0.1'
| tee temp.txt | cut -d : -f3 | awk '{print $1}'`
echo $ipaddr

bashdb --�����������Խű��Ĺ���

set -e --�ڽű��������ã��������ط��㣬�����˳�
-a

echo "AAbb" | tr A-Z a-z -- ��д��Сд
echo "ggcf" | tr -s "a-z" --ɾ���ظ����ֵ��ַ�
echo -e "gcf\n\ngechengfeng" | tr -s "\n" --ɾ������Ŀ���
echo "abkkkcd" |tr -d "abcd" -- ɾ��a b c d�ַ�
echo $PATH | tr -s ":" "\n" --��:��ɻس�

echo "a:b:c"|cut -d: -f2 --�ֺŷָ���ȡ�ڶ����ֶ�
cut -d: -f 1 /etc/passwd > /tmp/users
cut -c3-5 /etc/passwd --ȡ��3-5�ַ�
echo "abcd"|cut -c3 --ȡ��3�� 
echo "abcd"|cut -c3- --ȡ��3�������
echo "abcd"|cut -c-3 --ȡ��1������3��

ȥ���ַ���ǰ��ո�ķ���
VAR=" gcf ";VAR=`echo $VAR`;echo "$VAR"

����Ŀ¼���˳�
[ -d /events ] || exit 0

exec������ִ��ʱ��ѵ�ǰ��shell process�رգ�Ȼ�󻻵�������������ִ��
��ִ�� exec ls ��ѵ�ǰ���ڹص�

���ü�PATH��ʽִ������
alias FvwmCommand='/usr/X11R6/bin/FvwmCommand'

shift����ÿִ��һ�Σ������ĸ���($#)��һ��������ֵ��ǰһλ��
until [ $# -eq 0 ]
do
echo "��һ������Ϊ: $1 ��������Ϊ: $#"
shift
done
ִ�����ϳ���x_shift.sh��
$./x_shift.sh 1 2 3 4
�����ʾ���£�
��һ������Ϊ: 1 ��������Ϊ: 3
��һ������Ϊ: 2 ��������Ϊ: 2
��һ������Ϊ: 3 ��������Ϊ: 1
��һ������Ϊ: 4 ��������Ϊ: 0

��shift 9�����$10�Ƶ�$1

bash����
-e�����һ������ʧ�ܾ������˳�
-n����������ǲ�ִ������
-u���û�ʱ��δ���õı�����������
-v��������shell������ʱ��������ʾ����
-x��ִ������ʱ����������ǵĲ�����ʾ����

�������
$*: �������в���������ΪIFS�ڶ������ĵ�һ����Ԫ 
$@: ��*�Ǻ���ͬ����֮ͬ����춲�����IFS
$!: ִ����һ������ָ���PID,��./a.out &
$_: ��ʾ������һ��ִ�е�����
$0 �����ʽ��ִ������ 
$n �����ʽ�ĵ�n������ֵ��n=1..9 
$# �����ʽ�Ĳ������� 
$$ �����ʽ��PID 
$? ִ����һ��ָ��ķ���ֵ

�����ס�����˳��İ취����format
1���½�һ���ļ� 1.txt ����Ϊ ^C
2��format < 1.txt
����һ�ַ�����
cat file.sh
format << EOF
EOF
ִ�� ./file.sh

�鿴solaris�϶˿�ռ��
pfiles `ls /proc`|grep 35095

pid=`ps -ef|grep swm_agent|grep -v grep |awk '{print $2}'`;gdb -p $pid

��ͼ�ν�������ʾ����ķ��������е�ip���Ƿ�����ip���Ǳ���ip
export DISPLAY=10.85.60.225:0


�ظ�ִ��һ������ 
while [ 1 ]; do ls; done

��ʾ��ʷ�����ʱ��
export HISTTIMEFORMAT='%F %T '
history | more

��ȡ��ǰ�û�ip
who -m

ѹ��ָ��Ŀ¼�µ��ļ������ڵ�ǰĿ¼�����ģ�
/opt/gcf # tar cvf a.tar -C /opt/gcf/test/ main.cpp

ls|grep 7z|xargs -t -I{} 7za x {}

ͨ�����ֻ�ȡpid��Ϊ����
pgrep imapsysd|xargs dbx -

��һ�������е� foo �滻Ϊ bar����ִ�С�
^foo^bar

�����ļ�
ls | xargs -t -i mv {} {}.bak

������ʽ��ʾ�Ƿ�ִ�и�����(��y�س���ִ�и�����)
ls | xargs -p -t -i mv {} {}.bak


��ȫ�ӹ̻����������������ֹ�����
export HISTSIZE=9999

��ʾhistory�����ʱ��
export HISTTIMEFORMAT='%F %T '

����˿ڵķ�Χ��
/proc/sys/net/ipv4/ip_local_port_range

�����´���
Exception in thread "main" java.lang.InternalError: Can't connect to X11 window server using '10.66.90.53:0.0' as the value of the DISPLAY variable.
�Ľ���취��
export DISPLAY=10.66.90.53:0.0 ---10.66.90.53�ǿͻ���IP
xhost + 10.66.90.53

��������\���ķ�����
find ./ -name ".properties" |xargs grep "\\\\u518D\\\\u767B\\\\u5F55\\\\u3002"


linux����ɾ�������ɾ���� linux_installdisk.zip֮����ļ���
ls |grep -v linux_installdisk.zip|xargs -i rm -rf {}


xargsʹ��:
ls *.tar.gz|xargs -n1 tar tvfz

echo "1 2 3 4"|xargs -n1
1
2
3
4

echo "1 2 3 4"|xargs -n2
1 2
3 4

find�����ո��ļ�����xargs����ļ���:
find��һ������-print0����Ĭ�ϵ�-print��ȣ���������в����Կո�ָ���������null�ַ��ָ�����xargsҲ��һ������-0�����Խ�����null���ǿո�����������
find ./ -name "*.txt" -print0|xargs -0 grep abc

-r���������find�޷���ֱ���˳�����ִ��xargs���������

-t --��ʾxargs�����������ڵ���

-d ָ���ָ�����echo /opt/gcf/kkk |xargs -d / -t -n1 echo

-a ����ͨ���ļ���ȡ�� xargs -a gcf.txt echo

-E ������ָ���ַ�������ֹ xargs -a gcf.txt -t -n1 -E 'kkkk' echo

��perl�����ļ��滻��
perl -pi -e "s/gcf/gechengfeng/g" a.txt 

������־��
find ./ -name "*.java"|grep -v test|xargs grep -P '\.(error|info|debug|warn)\([\x21-\x80\s\n]*?\);'

�����ַ����󣬲鿴���漸�еķ���������Ҫ�Ľ�����һ���ԣ���
cat /opt/gcf/temp/a.txt |grep -P '[x21-x80]*iEMP/iEMP([x21-x80\S]*[\n]*[x21-x80\S]*){0,4}'


�鿴�����еĽ��̻���������
cat /proc/$PID/environ | tr '\0' '\n'


ɾ��ѹ�����е��ļ�
zip -d aaa.zip */oms.header.xml

nessus���ԣ�
nasl -t 10.67.164.170 /tmp/my.nasl 

�������з����������ļ�������������Ŀ¼�������Ŀ¼�ṹ���䣩���磺
find /opt/iEMP/iEMP -name "*.jar"|xargs -I{} cp --parent {} /opt/gcf/newdir

�ݹ����zip�ļ�unzip -C -p linux_installdisk.zip data/topo-sdk.zip|jar -t

xargsִ��shell�����ķ�����
#!/bin/sh
Mytest()
{
echo $1
}
if [ "$1" == "Mytest" ]; then
shift
Mytest $*
exit 0
fi
find /opt/gcf/bin -name "*.sh" | xargs -n 1 -i $0 Mytest {}

��ʾƥ���е�ǰ��5�У�-Bǰ�� -A��
find ./kernel-* -name "*.java"|grep -v test|xargs grep -C 5 "\.decode("

grep��Binary file (standard input) matches����Ľ���취
grep -a 

linux�Ͽ��ٱȽ�����Ŀ¼�µ�����
diff -q -r /opt/oss /opt/oss_bak

������ʾ����PS1


�滻SMTP_WORD��ֵ
echo "SMTP_WORD=kkkkkk;"|sed "s#\(SMTP\_WORD\=\).*\(;\)#\1xxxxxxxxxx\2#"

���������ַ���
echo "����"|grep --color -P '[һ-��]'

��K��M��λ��ʾ�ļ���С
ls -hl

lsֻ��ʾĿ¼��Ϣ������ʾĿ¼�µ��ļ�
ls -dl

echo�������еķ���
echo -e "xxx\c"
����
echo -n "xxx"

����$�ַ�
grep "\\$"

ÿ�������ַ���һ���ַ�
echo '12345678901234567890' | awk 'BEGIN{N=3;OFS=FS=""}{for(n=N;n<=NF;n+=N)$n="\\x"}1'

���ɶ������ļ���ʮ�������ı�
xxd -i gcf.tar.gz |grep "0x"|xargs |sed -e 's/0x/\\x/g' -e 's/, //g'