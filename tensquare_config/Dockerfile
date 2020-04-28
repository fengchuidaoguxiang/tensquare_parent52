##基础镜像，如果本地没有，会从远程仓库拉取。
#FROM openjdk:8-jdk-alpine
#
##镜像的制作人
#MAINTAINER xzxiaoshan/365384722@qq.com
#
##工作目录
#WORKDIR /app/
#
##在容器中创建挂载点，可以多个
#VOLUME ["/tmp"]
#
##声明了容器应该打开的端口并没有实际上将它打开
#EXPOSE 8080
#
##定义参数
#ARG JAR_FILE
#
##拷贝本地文件到镜像中
#COPY ${JAR_FILE} app.jar
#
##指定容器启动时要执行的命令，但如果存在CMD指令，CMD中的参数会被附加到ENTRYPOINT指令的后面
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]


依赖镜像名称和ID
FROM centos:7
#指定镜像创建者信息
MAINTAINER ITCAST
#切换工作目录
WORKDIR /usr
RUN mkdir /usr/local/java
#ADD 是相对路径jar,把java添加到容器中
ADD jdk-8u171-linux-x64.tar.gz /usr/local/java/
#配置java环境变量
ENV JAVA_HOME /usr/local/java/jdk1.8.0_171
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH