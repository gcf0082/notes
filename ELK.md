bin/kibana-plugin install <package name or URL>
bin/kibana-plugin install x-pack
 bin/kibana-plugin install https://artifacts.elastic.co/downloads/packs/x-pack/x-pack-7.3.0.zip
bin/kibana-plugin install file:///some/local/path/my-plugin.zip -d path/to/directoryun
bin/kibana-plugin remove x-pack
bin/kibana --<plugin ID>.enabled=false

kibana插件开发指导helloworld中文教程
https://kibana.gitbook.io/kibana-plugin-development-tutorial/kuai-su-ru-men/hello-world

kibana插件官方指导
https://www.elastic.co/guide/en/kibana/current/kibana-plugins.html

beats社区
https://www.elastic.co/guide/en/beats/libbeat/current/community-beats.html

es参考
https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html

https://github.com/searchkit/searchkit

https://github.com/dzharii/awesome-elasticsearch

kibana demo
https://demo.elastic.co/app/kibana

logstash插件
https://github.com/logstash-plugins

全文搜索引擎 Elasticsearch 
http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html

elasticsearch awesome
https://github.com/dzharii/awesome-elasticsearch

自动处理json格式的数据
filter {
    json {
         source => "message"
    }
}
或者file块中增加codec => "json"

自动加载配置
--config.reload.automatic 或者 
kill -1 pid

Filebeat

递归全部子目录
/path/to/**/*.log

logstash时间处理
https://blog.csdn.net/zhaoyangjian724/article/details/52894844

kibana插件列表
https://github.com/robcowart/kibana_plugins_list

elktail
https://github.com/knes1/elktail

 Filebeat将数据发送给logstash
https://www.cloudcrossing.xyz/post/55/

写Kibana插件——自定义应用程序
https://www.cnblogs.com/benjiming/p/727783
