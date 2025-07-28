FROM harbor01.data-tech.com.cn/system_base/openjdk:8u322-jdk-oraclelinux7-fonts
MAINTAINER zhengqiang

ARG JAR_FILE
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone && mkdir /home/notice && mkdir /home/logs
COPY ${JAR_FILE} /home/notice/notice.jar

WORKDIR /home/notice
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "notice.jar"]