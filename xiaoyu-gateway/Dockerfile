# FROM 镜像地址，网易镜像库的8-alpine java版本
FROM hub.c.163.com/library/java:8-alpine

# 维护者信息
MAINTAINER zxy

ADD xiaoyu-gateway-1.0.1.jar app.jar

#指定于外界交互的端口
EXPOSE 8887

#运行对应的jar包
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

