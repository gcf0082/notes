# 常用linux命令

## 包管理

###### rpm

查看rpm包里面有哪些文件

rpm -qpl xxx.rpm

安装rpm包

rpm -ivh xxx.rpm   #其中i表示安装，v表示显示安装过程，h表示显示进度

升级rpm包

rpm -Uvh xxx.rpm

列出所有已经安装的rpm包

rpm -qa

查看某个文件属于哪个rpm包

rpm -qf /usr/sbin/chroot

列出某个包包含哪些文件

rpm -ql coreutils

rpm -ql coreutils-8.22-23.el7.x86_64

列出包的标准详细信息

rpm -qi coreutils

删除软件包 

rpm -e PACKAGE_NAME

rpm -e –nodeps PACKAGE_NAME    #不考虑依赖包

rpm -e –allmatches PACKAGE_NAME    #删除所有跟PACKAGE_NAME匹配的所有版本的包

解压rpm包到当前目录

rpm2cpio xxx.rpm | cpio -div 

test