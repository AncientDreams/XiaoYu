# XiaoYu
XiaoYu 基于 Spring Cloud Alibaba的分布式微服务快速搭建案例，具有统一授权、认证，包含用户管理、资源权限管理（Spring Security + oauth2）
、网关API(Gateway)、分布式事务(seata)、分布式任务调度（xxl-job），采用 Sentinel服务限流熔断，Nacos做注册中心和分布式配置中心。


#### 模块说明

```lua

XiaoYu
├── xiaoyu-auth -- 统一授权服务[8000]
└── xiaoyu-common -- 系统公共模块 
     ├── xiaoyu-common-core -- 公共核心包
     ├── xiaoyu-common-job -- 分布式任务调度整合包
     ├── xiaoyu-common-redis -- redis 公共整合包
     └── xiaoyu-common-security -- 微服务资源服务安全
├── xiaoyu-gateway -- Spring Cloud Gateway网关[8887]
└── xiaoyu-manage -- 服务监控管理运行模块
     └── xiaoyu-admin -- 微服务健康监控平台[9999]
     └── xxl-job-admin -- 分布式任务调度平台[9898]
└── xiaoyu-service -- 微服务集合
     └── xiaoyu-user-service -- 用户服务 [8888]
     └── xiaoyu-system-service -- 系统服务 [9000]		 
└── xiaoyu-service-api -- 微服务API集合
     └── xiaoyu-user-api -- 用户api
```

### 技术点介绍

#### 1.服务注册&发现与调用：
基于Nacos来实现的服务注册与发现，服务配置的实时刷新，在项目运行中更改配置无需重启项目，使用Feign来实现服务之间相互调用。

#### 2.网关统一的认证与微服务安全：
所有业务请求经过网关，网关做统一的认证，请求响应日志的记录。如果需要对一些公共资源放行，只需在nacos配置中增加白名单路径即可。
在service包下的微服务都是资源服务，都需要对权限进行验证拦截， xiaoyu-common-security包提供了支持。您也可以根据自己的需求定制
不同的权限拦截和校验。

#### 3.微服务服务健康监控：
Spring Boot Admin 监控各个独立Service的运行状态.

#### 4.分布式任务调度平台 XXL-JOB：
xiaoyu-common-job提供支持，只需要在微服务中引入次包，然后再配置中导入配置即可使用。

#### 5.分布式事务：
集成了阿里的分布式事务中间件：seata，解决微服务景下面临的分布式事务问题，配置相对繁琐，data目录下有配置可以参考。

#### 6.服务限流和熔断:
Sentinel 是面向分布式服务架构的高可用流量防护组件，主要以流量为切入点，从限流、流量整形、熔断降级、系统负载保护、热点防护等多个维度来帮助开发者保障微服务的稳定性。

#### 7.在线api
由于原生swagger-ui某些功能支持不够友好，故采用了国内开源的`knife4j`，在微服务中只需照常配置swagger2即可。<br><br>


### 一些建议
有一说一，说说微服务带了的一些问题<br>运维成本： 考虑到服务的稳定性和提高服务并发能力，服务的集群部署，带来的运维和服务器资源成本。<br>
开发成本：服务众多，前后端的分离，服务与服务之间的关联与搭建。<br>微服务给我们带来好处的同时也在为我们带来问题，所以我们在选择的时候
需要根据自身的情况去选择，如果您觉得微服务项目不合适您，嘻嘻嘻，可以考虑下作者的另一款单体项目框架 [quickstart](https://github.com/AncientDreams/quickstart)，
一个丰富且好看的后台管理系统的脚手架，会让您降低不少开发成本！如有任何建议可以通过登录后提建议，我将尽我所能完善您的意愿。

### 如何部署启动

1.下载代码，data包下包含压缩包，时间可能比较久。<br>
2.安装启动 nacos,sentinel,seata,redis, 执行 mysql建表语句。 <br>
3.nacos 导入配置。<br>
4.启动各个微服务<br>

### 联系作者
如果您对此项目有任何的问题与建议可以邮件作者，一起学习改进，联系方式在源码的类头上有作者的邮箱。<br>
