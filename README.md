Spring Boot Jfinal Quickstart Maven Archetype
=========================================

[![Build Status](https://travis-ci.org/suzel/spring-boot-quickstart-archetype.svg?branch=master)](https://travis-ci.org/suzel/spring-boot-quickstart-archetype)
[![License Status](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/suzel/spring-boot-quickstart-archetype/master/LICENSE)

Summary
-------
The project is a Maven archetype for Spring Boot web application.

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
     -DarchetypeGroupId=com.github.jfinal-spring-boot-archetypes \
     -DarchetypeArtifactId=jfinal-spring-boot-quickstart \
     -DarchetypeVersion=2.0.2 \
     -DgroupId=xxxxx \
     -Dpackage=com.company \
     -DartifactId=xxxxxx \
     -Dversion=1.0.0-SNAPSHOT \
     -DinteractiveMode=false
```

Run the project
----------------

```sh
$ mvn test spring-boot:run
```

Test on the browser
-------------------

```sh
http://localhost:8080/
```

Note: No additional services are required in order to start the application.

Demo
---------
github:<a href="https://github.com/wxcmyx/best-practice-java/tree/master/springboot-jfinal">jfinal-springboot</a>