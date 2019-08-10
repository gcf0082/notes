shell
定义只读变量 
name=Tom 
readonly name

看文件生成的秒数(solaris)
ls -e 

linux下显示秒数
stat core.imapsvcd.27166

递归目录下的所有文件，包括子目录
ls -Rl /opt/gcf

echo * 输出当前目录的所有目录与文件

echo -e "abc\n ddd" 可以把回车输出来

echo `ls` 如果输出本来有回车，echo以后回车会自动换成空格

command > filename 2>&1 把把标准输出和标准错误一起重定向到一个文件中

windows上获取当前目录 echo %cd%

if中数字比较
if [ $str -gt 100 ] 
-gt 大于
-ge 大于等于
-lt 小于
-le 小于等于
-eq 等于
-ne 不等于

判断条件连接
if [ str1 = "aa" ] && [ str2 = "bb" ]
if [ str1 = "aa" ] || [ str2 = "bb" ] 
if [ ! $? -eq 0 ] 取反

获取函数返回值
func
ret=$?
不能用 ret=`func`

为了重复利用代码，可以将函数写成库函数，然后在脚本中使用
. libraryfile

信号处理
trap "echo ___ $0 interrupted ___; exit 1" 2

用while循环从文件中读取数据
while read LINE
do
echo $LINE
done < name.txt

脚本中需要获取调用函数的返回值使用 $?

删除shell函数
unset function_name

awk以=分割
echo "a=b" | awk -F= '{print $1}'

echo "abc kkk haha"|awk '{if($1~/abc/) print $2}' --如果第一个域是"abc"
echo "abcd kkk haha"|awk '{if($1=="abc") print $0}' --完全匹配abc
echo "abdcd kkk haha"|awk '{if($1!~/abc/) print $0}' --非，不包含abc的
echo "abdcd kkk haha"|awk '{if($1!="abc") print $0}' --不等于
echo "abdcd 60 70"|awk '{if($2<$3) print $0}' --小于
echo "Gcf 60 70"|awk '/[Gg]cf/'
echo "Gcf 60 70"|awk '$1 ~/^.c/' --开头第2个字符是c
echo "gcf 60 70"|awk '$1 ~/gcf|gechengfeng/' --匹配gcf或者gechengfeng
echo "gcf bantian huawei" |awk '/^gcf/' --gcf开头
echo "gcf bantian huawei" |awk '{if ($1=="gcf"&&$2=="bantian") print $0}' --与判断
echo "gcf bantian huawei" |awk '{print NF}' --显示参数个数
echo "gcf bantian huawei" |awk '{if(NF>2)print $0}' --如果记录个数大于2
echo "/opt/iMAP/etc/conf/imap.cfg"|awk -F/ '{print $NF}' --显示文件名
echo "gcf bantian huawei" |awk '{name=$1;gongsi=$3; print gongsi}' --给域设置名字
echo "gcf bantian huawei" |awk '{if($1=="gcf") ($1="gechengfeng"); print $0}' --给域重新赋值
echo "gcf bantian huawei" |awk '{if($1=="gcf") {$1="gechengfeng"; print $0};}' --注意括号的位置
echo "gcf 60 80" |awk '{$4=$2+$3; print $0}' --定义新的域
ls -l |awk '/^[^d]/ {print $9"\t"$5} {tol+=$5} END {print "total KB:" tol}' --统计文件大小
echo "gechengfeng 02868 " |awk 'gsub(/02868/,65017) {print $0}' --使用函数gsub替换，用65017替换02868
echo "gechengfeng" | awk '{print index($1,"feng")}' --返回8
echo "gechengfeng" | awk '{print length($1)" " $1}' --获取字符串的长度
awk 'BEGIN {print match("abcd","c")}' --匹配找到的字符位置，找不到返回0
echo "gcf 02868"|awk 'sub(/02868/,"65017",$0) {print $0}' --sub函数的替换
echo "gechengfeng"|awk '{print substr($1,3,99)}' --substr截取字符串
echo "gechengfeng"|awk '{print substr($1,3)}' --同上
echo "65"|awk '{printf "%c\n",$0}' --使用printf格式化
echo "gcf 1981"|awk '{if ($2 > Year) print $0}' Year=1980 --使用变量

AAA="<databasename>imap_db</databasename>";expr $AAA : '<databasename>\(.*\)</databasename>'
--解析xml，返回imap_db 注意冒号两边要有空格

awk脚本文件
[root@femgwei]# cat test.awk 
!/bin/awk -f ---这里不能有#在前面！！
#sample 
echo "gcf 1981"|awk '{if ($2 > Year) print $0}' Year=1980


字符串转化成数字运算（\*前后要有空格）
$(expr $DBSIZE \* 512)

export gcf=/opt/iMAP/etc/conf/imap.cfg
echo ${gcf##/*/} -- imap.cfg
echo ${gcf#/*/} -- iMAP/etc/conf/imap.cfg
echo ${gcf%/*} -- /opt/iMAP/etc/conf
echo ${gcf%%/*} -- 空
echo ${gcf//opt/kkkk} -- /kkkk/iMAP/etc/conf/imap.cfg

向函数传参数
func()
{
echo $1
}
func abcd

大写转化为小写
echo "ABCddd"|tr A-Z a-z

不生成中间文件直接修改文件 -i
sed -i 's/gcf/gechengfeng/g' gcf.test


awk和sed电子书
Linux.O'Reilly.SED and AWK.chm
O'Reilly - SED and AWK.examples
OReilly - Effective awk Programming 3rd Edition.

不显示标志错误和输出重定向即可
>/dev/null 2>&1


&>filename --将stdout和stderr都重定向到filename
2>filename --将stderr重定向
2>&1 --重定向stderr到stdout
ls /opt/gcf > /tmp/cmd.out 2>&1 --注意2>&1在最后

echo 1234567890 > File # 写字符串到"File".
exec 3<> File # 打开"File"并且给它分配fd 3.
read -n 4 <&3 # 只读4个字符.
echo -n . >&3 # 写一个小数点.
exec 3>&- # 关闭fd 3.
cat File # ==> 1234.67890
# 随机存储.

$RANDOM --随机数

echo -e "b\n\a\nc"|sort -f --排序，-f表示忽略大小写
echo -e "111\n2n\88\nc"|sort -fn --排序，-n表示按数字排序
sort -u --去除重复行

echo -e "gechengfeng \n 65017" --显示回车
echo -e "\a" --显示响铃

echo ${#NAME} --返回变量的字符个数
expr length $NAME --也是返回变量的字符个数
stringZ=abcABC123ABCabc; echo `expr match "$stringZ" 'abc[A-Z]*.2' 
--匹配字符串开头的子串长度返回8
str=test.doc;echo `expr index "$str" doc` --返回第一次出现的数字位置
str=test.doc;echo ${str:5} --子串提取，返回 doc
str=test.doc;echo ${str:0:4} --返回test 其中4是子串的长度
str=test.doc;echo ${str:(-3)} --从后边截取，小括号可以去掉
str=test.doc;echo `expr substr $str 1 4` --返回test，其中4是子串的长度
str=test.doc; expr $str : '\(.*\).doc' --返回test，\(是开头的意思，\(是结尾的意思，冒号两边要有空格
stringZ=abcABC123ABCabc; echo `expr match "$stringZ" '\(.[b-c]*[A-Z]..[0-9]\)'` --返回abcABC1】
stringZ=abcABC123ABCabc;echo `expr match "$stringZ" '.*\([A-C][A-C][A-C][a-c]*\)'` 
--从结尾开始提取，返回ABCabc
stringZ=abcABC123ABCabc; echo ${stringZ#a*C} --剥去最短匹配的子串，返回123ABCabc
stringZ=abcABC123ABCabc; echo ${stringZ##a*C} --剥去最长匹配的子串，返回abc
stringZ=abcABC123ABCabc;echo ${stringZ%b*c} --从尾部开始抹去最短匹配的子串，返回abcABC123ABCa
stringZ=abcABC123ABCabc;echo ${stringZ%%b*c} --从尾部开始抹去最长匹配的子串，返回a
NAME=gcf; echo ${NAME/f/feng} 
--用feng替换f，只替换第一个匹配的
NAME=gcff; echo ${NAME//f/feng} --用feng替换所有的f，注意是//
stringZ=abcABC123ABCabc;echo ${stringZ/#abc/XYZ} --用'XYZ'替换前端的'abc' ，返回XYZABC123ABCabc
stringZ=abcABC123ABCabc;echo ${stringZ/%abc/XYZ} --用'XYZ'替换后端的'abc'，返回abcABC123ABCXYZ

shell if 比较
[ -a FILE ] 如果 FILE 存在则为真。
[ -b FILE ] 如果 FILE 存在且是一个块特殊文件则为真。
[ -c FILE ] 如果 FILE 存在且是一个字特殊文件则为真。
[ -d FILE ] 如果 FILE 存在且是一个目录则为真。
[ -e FILE ] 如果 FILE 存在则为真。
[ -f FILE ] 如果 FILE 存在且是一个普通文件则为真。
[ -g FILE ] 如果 FILE 存在且已经设置了SGID则为真。
[ -h FILE ] 如果 FILE 存在且是一个符号连接则为真。
[ -k FILE ] 如果 FILE 存在且已经设置了粘制位则为真。
[ -p FILE ] 如果 FILE 存在且是一个名字管道(F如果O)则为真。
[ -r FILE ] 如果 FILE 存在且是可读的则为真。
[ -s FILE ] 如果 FILE 存在且大小不为0则为真。
[ -t FD ] 如果文件描述符 FD 打开且指向一个终端则为真。
[ -u FILE ] 如果 FILE 存在且设置了SUID (set user ID)则为真。
[ -w FILE ] 如果 FILE 如果 FILE 存在且是可写的则为真。
[ -x FILE ] 如果 FILE 存在且是可执行的则为真。
[ -O FILE ] 如果 FILE 存在且属有效用户ID则为真。
[ -G FILE ] 如果 FILE 存在且属有效用户组则为真。
[ -L FILE ] 如果 FILE 存在且是一个符号连接则为真。
[ -N FILE ] 如果 FILE 存在 and has been mod如果ied since it was last read则为真。
[ -S FILE ] 如果 FILE 存在且是一个套接字则为真。
[ FILE1 -nt FILE2 ] 如果 FILE1 has been changed more recently than FILE2, or 如果 FILE1 exists and FILE2 does not则为真。
[ FILE1 -ot FILE2 ] 如果 FILE1 比 FILE2 要老, 或者 FILE2 存在且 FILE1 不存在则为真。
[ FILE1 -ef FILE2 ] 如果 FILE1 和 FILE2 指向相同的设备和节点号则为真。
[ -o OPTIONNAME ] 如果 shell选项 “OPTIONNAME” 开启则为真。
[ -z STRING ] “STRING” 的长度为零则为真。
[ -n STRING ] or [ STRING ] “STRING” 的长度为非零 non-zero则为真。
[ STRING1 == STRING2 ] 如果2个字符串相同。 “=” may be used instead of “==” for strict POSIX compliance则为真。
[ STRING1 != STRING2 ] 如果字符串不相等则为真。
[ STRING1 < STRING2 ] 如果 “STRING1” sorts before “STRING2” lexicographically in the current locale则为真。
[ STRING1 > STRING2 ] 如果 “STRING1” sorts after “STRING2” lexicographically in the current locale则为真。
[ ARG1 -eq ARG2 ] 等于 
[ ARG1 -ne ARG2 ] 不等于 
[ ARG1 -gt ARG2 ] 大于 
[ ARG1 -lt ARG2 ] 小于
[ ARG1 -le ARG2 ] 小于等于
[ ARG1 -ge ARG2 ] 大于等于

if [ -f /opt/gcf/main.cpp -a -f /opt/gcf/a.out ] --逻辑与 -a
-o --逻辑或
! --逻辑否

字符串比较
= 两个字符串相等，两个变量比较时最好加引号如 if [ "$STR1" = "$STR2" ]
!= 两个字符串不相等
-z 空串
-n --非空串

数值比较
if [ "$NUM" -eq "1330" ] --是否相等
-eq --相等
-ne --不等
-gt --大于
-lt --小于
-le --小于等于
-ge --大于等于

expr 30 \* 3 --乘号必须加\

测试文件是否有写权限
if [ ! -w test.txt ] ---如果文件不可写

决定脚本是否为交互式模式，非交互模式如 cron或at
if [ -t ]

测试环境变量是否已设置
if [ -z $EDITOR ] ---如果没有设置环境变量

登录用户变量 LOGNAME，可用来检查执行脚本的用户是谁

null命令
if [ "`ls`" = "" ]
then
echo "is empty"
else : #do nothing
fi

case语句:
read ANS
case $ANS in
1) echo "you select 1"
;;
2) echo "you select 2"
*) echo "this is not between 1 and 2"
exit 1
;;
esac

使用case时，可以指定 “|”符号作为或命令，如
case $TERMINAL in
vt100|vt102) TERM=vt100
;;

简单的for循环
for loop in 1 2 3
do
echo $loop
done

IFS 分割输入字段
默认为“空格，制表符，换行符” IFS=' \t\n'


常用的grep选项有：
-c 只输出匹配行的计数。
-i 不区分大小写（只适用于单字符）。
-h 查询多文件时不显示文件名。
-l 查询多文件时只输出包含匹配字符的文件名。
-n 显示匹配行及行号。
-s 不显示不存在或无匹配文本的错误信息。
-v 显示不包含匹配文本的所有行。
-E 正则表达式
-o 只返回匹配的项
-P 兼容perl的正则表达式

在文件中查找字符串
grep "string" file.txt

精确匹配
grep '\<48\>' file.txt

匹配K开头，D结尾的5个字符的字符串
grep 'K...D' data.f

使用grep匹配“与”或者“或”模式
grep -E '219|216' data.f

echo -e "abc \n aab"|grep "a\{2,\}" --匹配至少出现2次a的，返回aab，注意不用-E参数

echo "loveinglove"|grep "\(love\)ing\1" --\1代码前面匹配的love


匹配空行
grep '^$' myfile

匹配特殊字符 如$ . ' " * [] ^ | \ + ? ,必须在特定字符前加\

egrep的一个显著特性是可以以一个文件作为保存的字符串，如
$pg grepstrings
484
47
$egrep -f grepstrings data.f

uniq命令的作用：显示唯一的行，对于那些连续重复的行只显示一次！ 
uniq file.txt


仅显示连续重复的行
uniq -d file.txt

显示文件中没有连续出现的行。 
uniq -u file.txt

wait命令等待进程退出

内置命令typeset（ksh）就是用来设置变量属性的。如：
$ typeset -u NAME
$ NAME="Abc"
$ echo $NAME
ABC
同样typeset -l 就是设置小写字母属性

执行awk脚本
siteA:/opt/gcf # cat ./test.awk 
#!/bin/awk -f
BEGIN{
record="123#456";
split(record, myarray, "#")}

END{for (i in myarray) {print myarray[i]}}

siteA:/opt/gcf # ./test.awk /dev/null ----这里要加/dev/null才可以运行
123
456
siteA:/opt/gcf # 

echo "i==1"|awk -F"==" '{print $2}' ---字符串作为分隔符
echo "gcf=1981|65017"|awk -F"[=|]" '{print $1"\t"$2"\t"$3}' -- =和|作为分隔符
echo "gcf,1981,65017"|awk -F"," '{i=1;while(i<=NF) {print $i;i++}}' --while循环
echo "gcf,1981,65017"|awk -F"," '{for(i=1;i<=NF;i++) {print $i}}' --for循环
ps -eaf|awk '{system("pwdx "$2"")}' --执行system --好像只是suse支持，solaris还有问题
awk 'BEGIN {"date"|getline d; print d}' --通过管道执行命令
awk '{if($1~/gcf/) print $0}' file.txt --如果第1列包含gcf
awk '{if($1!~/gcf/) print $0}' file.txt --如果第1列不包含gcf
awk '$1=="gcf" {print $0}' file.txt --完全匹配gcf
awk '$1!="gcf" {print $0}' file.txt --不匹配gcf
awk '{if($2>$3) print $0}' --第2列大于第3列
awk '{if($2>$3 && $1~/gcf/) print $0}' --与运算
awk '{if($2>$3 || $1~/gcf/) print $0}' --或运算
awk '{if($1~/gcf/) {$1="gechengfeng";print $0}}' --替换域值
awk '{if($1~/gcf/) {$1="gechengfeng";$4="test";print $0}}' --增加域
awk 'tol+=$2;END{print tol}' --列相加
STR="gcf";echo $STR|awk '{print $STR}' --将环境变量传给awk

awk使用环境变量
ls -tl "$NAME"*gz |awk 'BEGIN{tol=0} {tol+=$5} END{print tol" "NAME}' 
--这种是错的
ls -tl "$NAME"*gz |awk 'BEGIN{tol=0} {tol+=$5} END{print tol" "NAME2}' NAME2=$NAME --这才是对的

awk函数
gsub(/35/,25) --这个那个记录，35替换为25
index("gcf","cf") --返回2
length("gcf") --返回3
match("gcf",/c/) --返回2，可以使用正则表达式
split("123#245", my, "#") --my[1]="123" , my[2]="245",返回值是2
str=gcf;sub(/f/,"feng",str) --str变为gcfeng
substr("gcf",2,1) --返回c，第2个字符开始，1个字符
substr("gcf",2,1) --返回c，第2个字符开始至最后所有字符

sed
sed -n '2p' --显示第2行，一定要有-n
sed -n '1,5p' --显示1到5行
sed -n '6q;1,5p' --速度更快
sed -n '/main/'p --查询main的行，要加p
sed -n '/[Ll]ove/'p --匹配Love或者love
sed -n '4,/main/'p --在从第4行开始到第一个匹配行之间的内容
sed '1,$p' --显示整个文件
sed -n '/main/=' -打印行号
sed -e '/main/=' --打印行号，并显示整个文件
sed '1,5d' --删除第1到5行
sed '/main/d' --删除匹配的行
sed '$d' --删除最后一行
sed 's/int/long/g' --将int替换为long，如果没有g只替换整行的第一个匹配的int
sed '/int/ s/int/long/g' --速度更快
sed 's/int/long/w file.out' --将替换结果保存在一个文件中
sed 's/printf/ACE_OS::&/p' --在匹配模式前加字符串
sed 's/main/&_i::/p' --在匹配模式后面加字符串
echo -e "love"|sed -n 's/love/***&***/'p ---返回***love***
sed -n 's/\(love\)able/\1rs/p' 
--love被标记为1，所有loveable会被替换成lovers，而且替换的行会被打印出来。
sed '/int/q' --匹配到第1个就退出了
sed '/^$/d' --删除空行
sed '/./!d' --同上
echo "file"|sed 's/$/.tar.gz/g' --增加文件扩展名
echo -e "6000"|sed -n '/0\{3,\}/'p --匹配包含至少3个0的行
echo -e "60000"|sed -n '/0\{3,9\}/'p --匹配包含3至9个0的行
REP=long; sed "s/int/$REP/g" main.cpp --从shell中获取变量，注意是双引号
echo -e "111\n222\n333\n444"|sed -n '/222/,/444/p' --显示222到444之间的内容
echo -e " gcf"|sed 's/[ ]*//g' --删除空格
sed 's/[ \t]*$//' --将每一行拖尾的“空白字符”（空格，制表符）删除
sed 's/^[ \t]*//;s/[ \t]*$//' --将每一行中的前导和拖尾的空白字符删除
sed 's/^/ /' --在每一行开头处插入5个空格（使全文向右移动5个字符的位置）
sed -n '/regexp/!p' --只显示“不”匹配正则表达式的行 
sed '/regexp/d' --与!p类似 
sed -n '/regexp/{g;1!p;};h' -- 查找“regexp”并将匹配行的上一行显示出来，但并不显示匹配行
sed -n '/regexp/{n;p;}' --查找“regexp”并将匹配行的下一行显示出来，但并不显示匹配行 
sed '/AAA/!d; /BBB/!d; /CCC/!d' --显示包含“AAA”、“BBB”或“CCC”的行（任意次序）
sed -n '/^.\{65\}/!p' --显示包含65个以下字符的行
sed '/^.\{65\}/d' --同上
sed '52q;d' --显示第52行（处理大文本是效率更高）
sed '$!N; /^\(.*\)\n\1$/!P; D' --删除文件中相邻的重复行（模拟“uniq”） 
sed -n 'G; s/\n/&&/; /^\([ -~]*\n\).*\n\1/d; s/\n//; h; P' --删除重复行
sed '$!N; s/^\(.*\)\n\1$/\1/; t; D' --删除除重复行外的所有行（模拟“uniq -d”） 
sed '/./,$!d' --删除文件顶部的所有空行 
sed -e :a -e '/^\n*$/{$d;N;ba' -e '}' --删除文件尾部的所有空行

sed '/test/,/check/s/$/sed test/p' example
--对于模板test和west之间的行，每行的末尾用字符串sed test替换

sed -i '1,1s/10/8/' entries --替换第一行的10为8

linux上解决svn版本老的问题
find ./ -name "entries"|grep ".svn"|xargs sed -i '1,1s/10/8/'

解决find的输出的文件名包含空格的问题：
find ./ -name "*.java" -print0|xargs -0 grep "iemp.instance"

find在当前目录搜索：
find ./ -maxdepth 1 -name "*.txt"

find文件名不区分大小写: -iname

find排除指定的目录：
find ./ -path conf -prune -o -name bme.web_en.properties

排除多个目录
find . \( -path "./ab" -o -path "./cd" \) -prune -o -name "datafile*" -print

搜索不存在字符串的文件
find ./ -name "*.sh"|xargs grep -L "#\!"

限制命令的长度
echo "01234567890123452789"|xargs -s 31 -t -x

sed -e '1,5d' -e 's/test/check/' example-
--(-e)选项允许在同一行里执行多条命令。如例子所示，
第一条命令删除1至5行，第二条命令用check替换test。
命令的执 行顺序对结果有影响。如果两个命令都是替换命令，
那么第一个替换命令将影响第二个替换命令的结果。

sed --expression='s/test/check/' --expression='/love/d' example
--一个比-e更好的命令是--expression。它能给sed表达式赋值。

sed '/test/r file' example
--file里的内容被读进来，显示在与test匹配的行后面，
如果匹配多行，则file的内容将显示在所有匹配行的下面。

$ sed '/^test/a\\--->this is a example' example
--'this is a example'被追加到以test开头的行后面，sed要求命令a后面有一个反斜杠。

echo "test"|sed '/^test/a\this is a example'
--追加命令a，在test开头的行后面增加 this is a example

echo "test"|sed '/^test/i\this is a example'
--在test之前增加 this is example

下一个：n命令 
$ sed '/test/{ n; s/aa/bb/; }' example
---如果test被匹配，则移动到匹配行的下一行，
替换这一行的aa，变为bb，并打印该行，然后继续。

echo -e "testgcf\ngcf"|sed -e '/test/{n; s/gcf/gechengfeng/;}'
--把第2行的gcf替换为gechengfeng

echo -e "testgcf\ngcf"|sed -e '/test/{N; s/gcf/gechengfeng/;}'
--把第1行的gcf替换为gechengfeng

变形：y命令 
$ sed '1,10y/abcde/ABCDE/' example
--把1--10行内所有abcde转变为大写，注意，正则表达式元字符不能使用这个命令。

sed G file --在每行后面增加空行

sed '/^$/d;G' 
--将原来的所有空行删除并在每一行后面增加一空行。
这样在输出的文本中每一行后面将有且只有一空行。

sed 'G;G' --在每一行后面增加两行空行

sed '/regex/{x;p;x;}' -- 在匹配式样“regex”的行之前插入一空行

sed '/regex/G' --在匹配式样“regex”的行之后插入一空行

sed '/regex/{x;p;x;G;}' --在匹配式样“regex”的行之前和之后各插入一空行 

echo -e '1\n2\n3\n4' | sed -e 'N; s/\n/ /'
--合并行

sed -e 's/yellow/blue/g' white.dat red.dat black.dat 
--同时操作几个文件

echo "akkbgc"|sed -e 'y/abc../xyz../'
--把a替换成x，b替换成y，c替换成z

sed -e 'y/abc/ABC' filename
--把小写的abc转换成大写的ABC

sed -e '/zhengxh/c hhhh' filename
--表示把含有字符串zhengxh的行，该成hhhh

sed -i '1 i\gcf' main.cpp
--在文件第一行前插入gcf（新增一行），-i会修改文件

echo "gcf ZHONGGUO" | sed '/gcf/ s/.*/\L&/g' --大写转换成小写

echo "gcf zhongguo" | sed '/gcf/ s/.*/\U&/g' --小写转换成大写

快速命令
‘s / \ . $ / / g’ 删除以句点结尾行
‘-e /abcd/d’ 删除包含a b c d的行
‘s / [ ] [ ] [ ] * / [ ] / g’ 删除一个以上空格，用一个空格代替
‘s / ^ [ ] [ ] * / / g’ 删除行首空格
‘s / \ . [ ] [ ] * / [ ] / g’ 删除句点后跟两个或更多空格，代之以一个空格
‘/ ^ $ / d’ 删除空行
‘s / ^ . / / g’ 删除第一个字符
‘s /CO L \ ( . . . \ ) / / g’ 删除紧跟C O L的后三个字母
‘s / ^ \ / / / g’ 从路径中删除第一个\
‘s / [ ] / [ ] / / g’ 删除所有空格并用t a b键替代
‘S / ^ [ ] / / g’ 删除行首所有t a b键
‘s / [ ] * / / g’ 删除所有t a b键

函数
处理返回值
func()
{
return 0
}
ret=$?
if [ $ret = 0 ];then
echo "ok"
fi

替换系统的ls命令，可以写一个名为ls的函数
ls()
{
echo "my ls"
}
ret=`ls`
echo $ret


控制流结构：
if grep "main" main.cpp > /dev/null 2>&1 ---判断一条命令的成功与否
then
echo "ok"
else
echo "not"
fi

if [ $# -lt 3 ] --检查脚本的参数个是否小于3

if [ -d "/opt/gcf" ] --如果是目录

cd /opt/gcf
if [ $? = 0 ] --如果cd命令返回0

ARGC=$#
if [ $ARGC = 1 ]; then ---判断参数个数，注意 = 两边一定要有空格
echo "1"
elif [ $ARGC = 2 ]; then
echo "2"
else
echo "other"
fi

case例子
read ANS
case $ANS in
1)
echo "1"
;;
2|5) --2或者5
echo "2 or 5"
;;
10);; --什么也不做
*)
echo "other"
;;
esac ---case单词反过来写

for params --循环使用脚本变量，params可以写成其他变量名
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

for loop in "gechengfeng 65017" "qinxiaoling 00000" --每个元素带两个参数
do
echo $loop
done

for loop in `ls`
do
echo $loop
done


简单的until
LOCK_FILE=/tmp/process.LCK
until [ ! -f $LOCK_FILE ] --如果文件被删除则跳出循环。还可用来监视硬盘空间
do
sleep
done
echo "file deleted"

简单的while循环
COUNTER=0
while [ $COUNTER -lt 5 ]
do
COUNTER=`expr $COUNTER + 1`
echo $COUNTER
done

while read FILM --循环处理从键盘读入的信息
do
echo $FILM
done

用while循环从文件中读取数据
while read LINE
do
echo $LINE
done < names.txt

SAVEDIFS=$IFS
IFS=,
while read NAME YEAR ID --从文件了读取多个变量，以逗号分隔
do
echo "name:" $NAME "year:"$YEAR "ID:"$ID
done < file.in
IFS=$SAVEDIFS --一般要恢复IFS

while : --这是一个死循环，应该空命令永远返回真


以sh scriptname运行一个Bash脚本将会禁止所有Bash的扩展特性。因此脚本可以会因此而运行失败

#!/bin/rm --执行脚本脚本会被删除

(a=hello; echo $a) --命令组，会新开一个shell来执行

{ echo "a";echo "b" } > file.out 
---{} 把一组命令的输出导入到一个文件中。{}不开新shell。
---{} 要有空格


command &>filename 会重定向命令command标准输出（stdout）和标准错误（stderr）到文件filename中
command >&2 把命令command的标准输出（stdout）重定向到标准错误（stderr）


管道以子进程来运行, 因此不能引起变量的改变
variable="initial_value"
echo "new_value" | read variable
echo "variable = $variable" # variable = initial_value

echo "-n" --什么也不输出。变量有可能有-开头的，要检测

R=$(ls) --相当于R=`ls`

陷阱：
[ [ $foo =bar ] ] ---为了解决foo以-开头[ bar = "$foo" ]

cd `dirname "$f"` --有缺陷的，空格
cd "$(dirname "$f")" --解决空格问题
["$foo" = bar && "$bar" = foo ] --wrong
[ bar = "$foo" -a foo = "$bar" ] --right
[ bar = "$foo" ] && [ foo = "$bar" ] --right
[[ $foo = bar && $bar = foo ]] --right
[[ $foo > 7 ]] --只能判断字符串，不能判断数值
[ $foo -gt 7 ] --可以判断数值，但是如果$foo不是数值就会出错
if [grep foo myfile] --错的
if grep foo myfile > /dev/null; then --对的
if [bar="$foo"] --错的,没有空格
if [ bar = "$foo" ] --对的
cat file | sed s/foo/bar/ > file --不能在同一管道操作中同时读写一个文件
sed 's/foo/bar/g' file > tmpfile && mv tmpfile file --对的,使用临时文件

echo <<EOF --错的
Hello world
EOF

cat <<EOF --对的,打印Hello word
Hello world
EOF 

cd /foo; bar --cd可能会出错,bar会在想不到的目录执行
cd /foo && bar --对的,应该判断返回值

find ... -type d | while read subdir; do
cd "$subdir" && whatever && ... && cd -
done 
不如这样写：
find ... -type d | while read subdir; do
(cd "$subdir" && whatever && ...) ---就不用cd -了,因为括号会开一个新的shell执行
done 

find命令后删除
find ./ -name test.sh -exec rm -rf {} \;

同搜索cpp和h文件
find -name "*.cpp" -o -name "*.h"

[ bar == "$foo" ] --错的
[ bar = "$foo" ] --对的
[[ bar == $foo ]] --对的

for i in {1..10}; do ./something &; done --错的,&后面不应该再放;
for i in {1..10}; do ./something & done --对的

echo "Hello World!" --因为有!,会报错-bash: !": event not found
echo 'Hello World!' --可以使用单引号
set +H --或者使用这个命令取消命令行历史替换
echo "Hello World!"

for x in $*; do --当参数有空格就有问题,如 ./test.sh 'arg 1' arg2
echo "$x"
done

for x in "$@"; do --对的
echo "$x"
done

不加引号时 $* 和 $@ 是相同的，但"$*" 会被扩展成一个字符串，而 "$@" 会被扩展成每一个参数。

i=1; i=$i+1; echo $i --返回的不是2，而是1+1
i=1; i=`expr $i + 1`; echo $i --对的，返回2
i=$(($i+1)) --双括号也可解决，如 i=$((($i+2)/2324%123*($j+34)))

trap调试脚本

trap 'echo $LINENO' ERR --当一条命令返回非零状态时打印行号
DEBUG --脚本中每一条命令执行之前
EXIT --从一个函数中退出或整个脚本执行完毕

trap 'echo $BASH_COMMAND' DEBUG --显示命令 --man bash 可以看更详细的信息
BASH_ARGC --脚本的参数个数
BASH_ARGV --脚本的参数

调试的时候可以用tee输出临时的文件，如：
ipaddr=`/sbin/ifconfig | grep 'inet addr:' | grep -v '127.0.0.1'
| tee temp.txt | cut -d : -f3 | awk '{print $1}'`
echo $ipaddr

bashdb --可以用来调试脚本的工具

set -e --在脚本里面设置，如果命令返回非零，立即退出
-a

echo "AAbb" | tr A-Z a-z -- 大写变小写
echo "ggcf" | tr -s "a-z" --删除重复出现的字符
echo -e "gcf\n\ngechengfeng" | tr -s "\n" --删除多余的空行
echo "abkkkcd" |tr -d "abcd" -- 删除a b c d字符
echo $PATH | tr -s ":" "\n" --把:变成回车

echo "a:b:c"|cut -d: -f2 --分号分隔，取第二个字段
cut -d: -f 1 /etc/passwd > /tmp/users
cut -c3-5 /etc/passwd --取第3-5字符
echo "abcd"|cut -c3 --取第3个 
echo "abcd"|cut -c3- --取第3个到最后
echo "abcd"|cut -c-3 --取第1个到第3个

去除字符串前后空格的方法
VAR=" gcf ";VAR=`echo $VAR`;echo "$VAR"

不是目录则退出
[ -d /events ] || exit 0

exec命令在执行时会把当前的shell process关闭，然后换到后面的命令继续执行
如执行 exec ls 会把当前窗口关掉

不用加PATH方式执行命令
alias FvwmCommand='/usr/X11R6/bin/FvwmCommand'

shift命令每执行一次，变量的个数($#)减一，而变量值提前一位：
until [ $# -eq 0 ]
do
echo "第一个参数为: $1 参数个数为: $#"
shift
done
执行以上程序x_shift.sh：
$./x_shift.sh 1 2 3 4
结果显示如下：
第一个参数为: 1 参数个数为: 3
第一个参数为: 2 参数个数为: 2
第一个参数为: 3 参数个数为: 1
第一个参数为: 4 参数个数为: 0

用shift 9命令把$10移到$1

bash参数
-e：如果一个命令失败就立即退出
-n：读入命令但是不执行它们
-u：置换时把未设置的变量看作出错
-v：当读入shell输入行时把它们显示出来
-x：执行命令时把命令和它们的参数显示出来

特殊参数
$*: 代表所有参数，其间隔为IFS内定参数的第一个字元 
$@: 与*星号类同。不同之处在於不参照IFS
$!: 执行上一个背景指令的PID,即./a.out &
$_: 显示出最後一个执行的命令
$0 这个程式的执行名字 
$n 这个程式的第n个参数值，n=1..9 
$# 这个程式的参数个数 
$$ 这个程式的PID 
$? 执行上一个指令的返回值

命令挂住让其退出的办法，如format
1、新建一个文件 1.txt 内容为 ^C
2、format < 1.txt
还有一种方法：
cat file.sh
format << EOF
EOF
执行 ./file.sh

查看solaris上端口占用
pfiles `ls /proc`|grep 35095

pid=`ps -ef|grep swm_agent|grep -v grep |awk '{print $2}'`;gdb -p $pid

非图形界面下显示界面的方法，其中的ip不是服务器ip，是本机ip
export DISPLAY=10.85.60.225:0


重复执行一个命令 
while [ 1 ]; do ls; done

显示历史命令的时间
export HISTTIMEFORMAT='%F %T '
history | more

获取当前用户ip
who -m

压缩指定目录下的文件（不在当前目录操作的）
/opt/gcf # tar cvf a.tar -C /opt/gcf/test/ main.cpp

ls|grep 7z|xargs -t -I{} 7za x {}

通过名字获取pid作为参数
pgrep imapsysd|xargs dbx -

上一条命令中的 foo 替换为 bar，并执行。
^foo^bar

备份文件
ls | xargs -t -i mv {} {}.bak

交互方式提示是否执行改命令(按y回车，执行该命令)
ls | xargs -p -t -i mv {} {}.bak


安全加固环境命令不能联想可以手工设置
export HISTSIZE=9999

显示history命令的时间
export HISTTIMEFORMAT='%F %T '

随机端口的范围：
/proc/sys/net/ipv4/ip_local_port_range

报如下错误：
Exception in thread "main" java.lang.InternalError: Can't connect to X11 window server using '10.66.90.53:0.0' as the value of the DISPLAY variable.
的解决办法：
export DISPLAY=10.66.90.53:0.0 ---10.66.90.53是客户端IP
xhost + 10.66.90.53

搜索带“\”的方法：
find ./ -name ".properties" |xargs grep "\\\\u518D\\\\u767B\\\\u5F55\\\\u3002"


linux反向删除命令：（删除除 linux_installdisk.zip之外的文件）
ls |grep -v linux_installdisk.zip|xargs -i rm -rf {}


xargs使用:
ls *.tar.gz|xargs -n1 tar tvfz

echo "1 2 3 4"|xargs -n1
1
2
3
4

echo "1 2 3 4"|xargs -n2
1 2
3 4

find到带空格文件名用xargs处理的技巧:
find有一个参数-print0，于默认的-print相比，输出的序列不是以空格分隔，而是以null字符分隔。而xargs也有一个参数-0，可以接受以null而非空格间隔的输入流
find ./ -name "*.txt" -print0|xargs -0 grep abc

-r参数，如果find无返回直接退出，不执行xargs后面的命令

-t --显示xargs后的命令，可用于调试

-d 指定分隔符，echo /opt/gcf/kkk |xargs -d / -t -n1 echo

-a 输入通过文件获取： xargs -a gcf.txt echo

-E 解析到指定字符串则终止 xargs -a gcf.txt -t -n1 -E 'kkkk' echo

用perl来做文件替换：
perl -pi -e "s/gcf/gechengfeng/g" a.txt 

搜索日志：
find ./ -name "*.java"|grep -v test|xargs grep -P '\.(error|info|debug|warn)\([\x21-\x80\s\n]*?\);'

搜索字符串后，查看后面几行的方法（还需要改进，不一定对）：
cat /opt/gcf/temp/a.txt |grep -P '[x21-x80]*iEMP/iEMP([x21-x80\S]*[\n]*[x21-x80\S]*){0,4}'


查看运行中的进程环境变量：
cat /proc/$PID/environ | tr '\0' '\n'


删除压缩包中的文件
zip -d aaa.zip */oms.header.xml

nessus测试：
nasl -t 10.67.164.170 /tmp/my.nasl 

搜索所有符合条件的文件，拷贝到其他目录的命令（且目录结构不变），如：
find /opt/iEMP/iEMP -name "*.jar"|xargs -I{} cp --parent {} /opt/gcf/newdir

递归查找zip文件unzip -C -p linux_installdisk.zip data/topo-sdk.zip|jar -t

xargs执行shell函数的方法：
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

显示匹配行的前后5行（-B前， -A后）
find ./kernel-* -name "*.java"|grep -v test|xargs grep -C 5 "\.decode("

grep报Binary file (standard input) matches错误的解决办法
grep -a 

linux上快速比较两个目录下的命令
diff -q -r /opt/oss /opt/oss_bak

命令提示变量PS1


替换SMTP_WORD的值
echo "SMTP_WORD=kkkkkk;"|sed "s#\(SMTP\_WORD\=\).*\(;\)#\1xxxxxxxxxx\2#"

查找中文字符串
echo "中文"|grep --color -P '[一-龥]'

以K，M单位显示文件大小
ls -hl

ls只显示目录信息，不显示目录下的文件
ls -dl

echo不带换行的方法
echo -e "xxx\c"
或者
echo -n "xxx"

搜索$字符
grep "\\$"

每隔两个字符插一个字符
echo '12345678901234567890' | awk 'BEGIN{N=3;OFS=FS=""}{for(n=N;n<=NF;n+=N)$n="\\x"}1'

生成二进制文件的十六进制文本
xxd -i gcf.tar.gz |grep "0x"|xargs |sed -e 's/0x/\\x/g' -e 's/, //g'