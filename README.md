#  Readme

网站：[www.xiongsansu.com](www.xiongsansu.com)

用户名：**test1**

密码：**test1**

![](演示.gif)

## web_weibo

- [使用说明](#使用说明)

## 使用说明

```bash
# 安装软件
apt install -y openjdk-11-jdk
apt install -y mysql-server
apt install redis-server
redis-cli
```

```bash
# 使用项目运行
scp -r ..\spring_web\ root@ip:~
bash gradlew bootRun
nohup bash gradlew bootRun &

```

```bash
# 打包命令，或者在 ide 里面点击 bootWar
./gradlw bootWar
# 复制文件
scp -r .\build\libs\spring.war root@ip:~
scp -r .\spring_web\springWeb.nginx root@ip:~
ssh root@ip

# 安装两个服务器
apt install tomcat9 nginx
# 部署程序到 tomcat 
rm -rf /var/lib/tomcat9/webapps/ROOT/
cp ~/spring.war /var/lib/tomcat9/webapps/ROOT.war
# 配置 nginx
rm /etc/nginx/sites-enabled/*
rm /etc/nginx/sites-available/*
cp ~/springWeb.nginx /etc/nginx/sites-enabled

# 重启
systemctl restart nginx
systemctl restart tomcat9

# 看日志
journalctl -u tomcat9
journalctl -u tomcat9 >> tomcat9.txt
ls /var/log/tomcat9
journalctl -u nginx
journalctl -u nginx >> nginx.txt
ls /var/log/nginx
```