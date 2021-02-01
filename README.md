# SkyTower监控平台服务端代码仓库

## 开发环境配置
- jdk 1.8: https://juejin.cn/post/6844903878694010893 
- Maven 3.6.1
    - https://juejin.cn/post/6844903801082609671 
    - 下载旧版本Maven：https://blog.csdn.net/jishuxiaoniuniu/article/details/69257468
- SpringBoot最新版本2.4.0
    - https://start.spring.io/ （也可以直接用IDEA创建springboot项目）
- MySQL 5.7.32
- MyBaties 2.0.0

## 技术栈
- Springboot
- MyBaties
- jwt
- 微服务架构

## 接口
- sky-tower-jssdk
    - 发射用户行为信号：http://101.200.197.197:8765/emit/action_event
    - 发射技术信号：http://101.200.197.197:8765/emit/count_event
    - 上报请求数据：http://101.200.197.197:8765/emit/req_event
    - 上报响应数据：http://101.200.197.197:8765/emit/resp_event
- sky-tower-监控平台前端
    - 用户登陆、判断用户的登陆状态：http://101.200.197.197:8765/check_permission
    - 用户注册：http://101.200.197.197:8765/create/new_user
    - 获取用户信息：http://101.200.197.197:8765/get/user_info
    - 更改用户信息：http://101.200.197.197:8765/update/user_info
    - 提交体验反馈：http://101.200.197.197:8765/report_feedback
    - 获取用户的项目列表：http://101.200.197.197:8765/get/user_list
    - 创建新项目：http://101.200.197.197:8765/create/new_project
    - 获取项目详情信息：http://101.200.197.197:8765/get/project_detail
    - 更改项目信息：http://101.200.197.197:8765/update/project_info
    - 删除项目（停止监控）：http://101.200.197.197:8765/delete/project
    - 获取计数事件：http://101.200.197.197:8765/get/list/count_event
    - 获取请求与响应事件：http://101.200.197.197:8765/get/list/http_event
    - 其他接口开发中...（上线后补充）
    
## 已创建的数据库、表、MOCK数据
```sql
/* 事件表 */
create DATABASE EVENT_DATABASE;

use EVENT_DATABASE;

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

alter table EVENT_DATABASE.event_table convert to character set utf8;

INSERT INTO `event_table`(`event_id`,`event`,`project_id`,`uid`,`type`) VALUES (10001,'image_upload','5612300','897889789','count');  

/* 用户表 */
CREATE TABLE user_table (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(25) NOT NULL,
    `avatar` VARCHAR(1000),
    `password` VARCHAR(25) NOT NULL,
    `phone_number` VARCHAR(25) NOT NULL,
    `email` VARCHAR(25) NOT NULL,
    `user_create_time`BIGINT NOT NULL,
    PRIMARY KEY (`user_id`)
);  

use user_table;

alter table user_table convert to character set utf8;

INSERT INTO `user_table`(`user_id`,`username`,`password`,`phone_number`,`email`, `user_create_time`) VALUES (10088888,
'skytower测试账号','88888888','897889789','test@skytower.com', 1611562074950);

/*
    【优化】event表和user表的主键类型改为bigint需求
    需求文档： https://y5cu4pfrwh.feishu.cn/docs/doccndKWQzm0OHrx5W2LwMFrmTh
*/
alter table event_table modify event_id INT UNSIGNED NOT NULL AUTO_INCREMENT;
alter table user_table modify user_id INT UNSIGNED NOT NULL AUTO_INCREMENT;

/* 反馈信息表 */
CREATE TABLE feedback_table (
    `feedback_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` VARCHAR(25),
    `feedback_rate` VARCHAR(5),
    `feedback_time` BIGINT NOT NULL,
    `feedback_content` VARCHAR(500) NOT NULL,
    PRIMARY KEY (`feedback_id`)
);  

alter table feedback_table convert to character set utf8;

INSERT INTO `feedback_table`(`feedback_id`,`user_id`,`feedback_rate`,`feedback_time`,`feedback_content`) VALUES (1001,
'10008800', '5', 1611562074950, '请问在哪里可以看到发出去的用户反馈呢？');

/* 项目信息表 */
CREATE TABLE project_table (
    `project_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `project_name` VARCHAR(25) NOT NULL,
    `description` VARCHAR(1000),
    `url_online` VARCHAR(500) NOT NULL,
    `is_monitoring` BOOLEAN NOT NULL,
    `create_time` BIGINT NOT NULL,
    `user_id` VARCHAR(25) NOT NULL,
    PRIMARY KEY (`project_id`)
); 

alter table project_table convert to character set utf8;

INSERT INTO `project_table`(`project_id`,`project_name`,`description`,`url_online`,`is_monitoring`, `create_time`, `user_id`) VALUES (100111, 'cat的个人博客', 'cat的个人心情随笔', 'https://www.hahaha123456.com', 1, 1611562074950, '10008800');

/* 项目访问记录 */
CREATE TABLE project_view_table (
    `record_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `project_id` VARCHAR(25) NOT NULL,
    `uid` VARCHAR(25) NOT NULL,
    `view_time` BIGINT NOT NULL,
    PRIMARY KEY (`record_id`)
); 

alter table project_view_table convert to character set utf8;

INSERT INTO `project_view_table`(`record_id`, `project_id`, `uid`, `view_time`) 
VALUES (0, '100112','0', 1611562074950);
```

