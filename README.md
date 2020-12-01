# SkyTower监控平台服务端代码仓库

## 开发环境配置
- jdk 1.8: https://juejin.cn/post/6844903878694010893 
- Maven 3.6.1
    - https://juejin.cn/post/6844903801082609671 
    - 下载旧版本Maven：https://blog.csdn.net/jishuxiaoniuniu/article/details/69257468
- SpringBoot最新版本2.4.0
    - https://start.spring.io/ （也可以直接用IDEA创建springboot项目）
- MySQL 5.7.32

## 技术栈
- Springboot
- MyBaties
- 微服务架构

## 接口
- sky-tower-jssdk
    - http://localhost:8765/emit/action_event
    - http://localhost:8765/emit/count_event
    - http://localhost:8765/emit/req_event
    - http://localhost:8765/emit/resp_event
- sky-tower-监控平台前端
    - 后续补充...
    
    
## 已创建的数据库、表、MOCK数据
```sql
create DATABASE EVENT_DATABASE;

CREATE TABLE event_table (
    `event_id` INT NOT NULL AUTO_INCREMENT,
    `event` VARCHAR(25) NOT NULL,
    `project_id` VARCHAR(25) NOT NULL,
    `uid` VARCHAR(25) NOT NULL,
    `type` VARCHAR(25) NOT NULL,
    `time` BIGINT,
    `location` VARCHAR(25),
    `device_brand` VARCHAR(25),
    `app_version` VARCHAR(25),
    `system_version` VARCHAR(25),
    `client` VARCHAR(25),
    `net_type` VARCHAR(25),
    `ip_address` VARCHAR(25),
    `extra` VARCHAR(1000),
    `api` VARCHAR(100),
    `query` VARCHAR(100),
    `request_body` VARCHAR(1000),
    `resp` VARCHAR(1000),
    `is_success` VARCHAR(25),
    PRIMARY KEY (`event_id`)
);  


INSERT  INTO `event_table`(`event_id`,`event`,`project_id`,`uid`,`type`) VALUES (10001,'image_upload','5612300','897889789','count');  
```




