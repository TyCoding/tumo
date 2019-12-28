# Tumo Blog

![](http://cdn.tycoding.cn/tumo.png)

Tumo Blog 是一个非常漂亮的博客系统，基于SpringBoot2.1.3 + LayUI。

苦于技术太菜，之前的版本中，采用SpringBoot + Vue留下了许多坑，非常抱歉；

花了几天时间对项目重构，前端采用全新的LayUI，相对于Vue，LayUI更适合前后端不分离的项目。



演示站点: [http://tumo.tycoding.cn](http://tumo.tycoding.cn)

后台入口：[http://tumo.tycoding.cn/login](http://tumo.tycoding.cn/login)

[![License](https://img.shields.io/badge/SpringBoot-v2.1.3.RELEASE-green.svg)](https://github.com/TyCoding/tumo)
[![License](https://img.shields.io/badge/Mysql-v5.7.22-blue.svg)](https://github.com/TyCoding/tumo)

[快速开始](https://github.com/TyCoding/tumo/wiki/%E5%A6%82%E4%BD%95%E9%83%A8%E7%BD%B2Tumo-Blog)

## Vue版本

本博客也有基于Vue组件开发的前后端完全分离的版本，如果你已经熟悉的传统的SSM框架开发模式，或许你可以尝试一下前后端分离的开发模式，那么 [https://github.com/TyCoding/tumo-vue](https://github.com/TyCoding/tumo-vue)就是一个不错的选择。

同时也欢迎大家借此项目进行二次开发。详细介绍请看：[https://github.com/TyCoding/tumo-vue](https://github.com/TyCoding/tumo-vue)

如果大家喜欢、或是对大家的学习有所帮助，请点击右上角star、fork 给作者一些鼓励。

## 技术选型

### 写在前面

请按照以下流程运行项目：

1. 检查自己本地的开发环境是否与我的一致
2. 创建本地Mysql数据库：`tumo`，并导入项目目录下 `/db/db.sql`
3. 保证Maven已经完全加载了项目所需的依赖
4. 运行项目下的：`/src/main/java/cn/tycoding/TumoApplication.java`
5. 默认用户名和密码：`username: tycoding;   passsword: 123456`

### 后端

* 基础框架：Spring Boot 2.1.3.RELEASE

* 持久层框架：Mybatis 1.3.1

* 安全框架：Shiro

* 模板引擎：Thymeleaf 3.0.11.RELEASE

### 前端

* 基础框架：LayUI

### 开发环境

* 语言： JDK1.8

* IDE： IDEA 2018.3

* 依赖管理： Maven

* 数据库： Mysql 5.7.24

## 联系我

- [Blog@TyCoding's blog](http://www.tycoding.cn)

- [GitHub@TyCoding](https://github.com/TyCoding)

- [ZhiHu@TyCoding](https://www.zhihu.com/people/tomo-83-82/activities)

- QQ Group: 671017003

- QQGroup2：490303233

