Jfinal Spring Boot Quickstart Maven Archetype
=========================================

[![Build Status](https://travis-ci.org/suzel/spring-boot-quickstart-archetype.svg?branch=master)](https://travis-ci.org/suzel/spring-boot-quickstart-archetype)
[![License Status](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/suzel/spring-boot-quickstart-archetype/master/LICENSE)

Summary
-------
The project is a Maven archetype for Spring Boot web application.

后续更新分拆成两个版本：<br>
专注rest服务的<br>
jfinal-spring-boot-quickstart-archetype<br>
整和了layui的<br>
jfinal-cms-spring-boot-quickstart-archetype

Descript
-------
整合框架：<br>

Spring-boot 2.0.3 <br>
Jfinal 3.4<br>
Jfinal-ext 3.0.6<br>
Shiro - Shiro-spring-boot-strator-1.4.0<br>
Redis - Lettuce-5.0.4<br>
Mongo-java-driver-3.6.4<br>
Mysql-connector-java-5.1.20<br>
Mysql - HikariCP-2.7.8<br>
Swagger - Swagger2-spring-boot-strater-2.0.0<br>
Javamelody - javamelody-spring-boot-starter-1.73.1<br>

Update log
-------------------
v2.1.2 <br>
1.专注rest微服务架构，专注最佳实践<br>
2.加入shiro认证<br>
3.加入session缓存

Installation
------------

To install the archetype in your local repository execute following commands:

```sh
$ git clone https://github.com/wxcmyx/jfinal-spring-boot-quickstart-archetype.git
$ cd jfinal-spring-boot-quickstart-archetype
$ mvn clean install
```

Create a project
----------------

```sh
$ mvn archetype:generate \
     -DarchetypeGroupId=com.wixct \
     -DarchetypeArtifactId=jfinal-spring-boot-quickstart \
     -DarchetypeVersion=2.1.3 \
     -DgroupId=xxxxx \
     -Dpackage=com.company \
     -DartifactId=xxxxxx \
     -Dversion=1.0.0-SNAPSHOT \
     -DinteractiveMode=false
```
DgroupId 项目名称

Run the project
----------------

```sh
$ mvn test spring-boot:run
```

Test on the browser
-------------------

```sh
http://localhost:8080/api/hello
```

Note: No additional services are required in order to start the application.

Document
---------
修改shiro权限认证方式，可能需要修改的几个包：<br>
xx.config<br>
xx.domain<br>
xx.security<br>
xx.service
