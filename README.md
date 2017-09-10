# best-practice
基于Maven的Java Web项目，整合常用框架与插件的最佳实践。

<br>技术点：

- jetty-maven-plugin
- SpringMVC
- Spring
- Log4j 2
- Mybatis
- MySQL
- PageHelper
- Jackson
- Commons (DBCP, Lang, Codec, IO, FileUpload,)
- Hibernate Validator
- AspectJ
- JQuery
- JQuery Form
- Quartz
- Redis
- ...

<br>克隆地址：

    $ git clone https://github.com/spldeolin/best-practice.git

<br>运行这个项目：

    $ cd <project location>
    $ mvn -Djetty.port=<port> jetty:run

<br>调试这个项目：

    Eclipse

	File - Import... >> Maven - Existing Maven Project >> Root Directory: <project location>
    best-practice >> Debug AS - Maven build >> Goals: jetty:run