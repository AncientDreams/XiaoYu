# FROM 镜像地址，网易镜像库的8-alpine java版本
FROM hub.c.163.com/library/java:8-alpine

# 维护者信息
MAINTAINER zxy

ADD xiaoyu-system-service-0.0.1-SNAPSHOT.jar app.jar

#指定于外界交互的端口
EXPOSE 9000

#运行对应的jar包
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

