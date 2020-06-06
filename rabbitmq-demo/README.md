# 此demo是java对rabbitmq的操作学习 

# ip
````
amqp        5672   内部通讯端口
http        15672   
clustering  25672
````
# centos7 安装 erlang
````
vim /etc/yum.repos.d/rabbitmq-erlang.repo
[rabbitmq-erlang]
name=rabbitmq-erlang
baseurl=https://dl.bintray.com/rabbitmq/rpm/erlang/20/el/7
gpgcheck=1
gpgkey=https://dl.bintray.com/rabbitmq/Keys/rabbitmq-release-signing-key.asc
repo_gpgcheck=0
enabled=1


yum install erlang -y
````
# 安装 rabbitmq的rpm包
````
rpm -ivh rabbitmq-server-3.8.4-1.el7.noarch.rpm
````
#启动RabbitMQ服务
````
service rabbitmq-server start
service rabbitmq-server stop
systemctl start rabbitmq-server.service
systemctl stop rabbitmq-server.service
````
#状态查看
````
 rabbitmqctl status
 ````
#启用插件
````
rabbitmq-plugins enable rabbitmq_management
````
#重启服务
````
service rabbitmq-server restart
systemctl restart rabbitmq-server.service
````
#添加帐号:name 密码:passwd
````
rabbitmqctl add_user name passwd
````
#赋予其administrator角色
````
rabbitmqctl set_user_tags name administrator
````
#设置权限
````
rabbitmqctl set_permissions -p / name ".*" ".*" ".*"
````
#查看所有插件
````
rabbitmq-plugins list
````
