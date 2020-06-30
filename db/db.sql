/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : tumo

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 29/06/2020 18:23:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(400) DEFAULT NULL COMMENT '标题',
  `author` varchar(100) NOT NULL COMMENT '作者',
  `des` mediumtext COMMENT '文章描述',
  `content` mediumtext COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of tb_article
-- ----------------------------
BEGIN;
INSERT INTO `tb_article` VALUES (1, 'How to write an article?', 'tycoding', '<h1 id=\"h1-how-to-write-an-article-\" style=\"font-family: Roboto, sans-serif;\">How to write an article?</h1><h2 id=\"h2-markdown-rules\" style=\"font-family: Roboto, sans-serif;\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><span style=\"font-weight: bolder;\">For example</span></p><p><a href=\"https://tycoding.cn/\">https://tycoding.cn</a></p>', '<h1 id=\"h1-how-to-write-an-article-\"><a name=\"How to write an article?\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>How to write an article?</h1><h2 id=\"h2-markdown-rules\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><strong>For example</strong></p>\n<p><a href=\"https://tycoding.cn\">https://tycoding.cn</a></p>\n<pre><code class=\"lang-java\">public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n}\n</code></pre>\n<p><img src=\"http://img.api.tycoding.cn/1568958650973.jpeg\" alt=\"\">\n<table>\n<thead>\n<tr>\n<th>Name</th>\n<th>Link</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>Github</td>\n<td><a href=\"https://github.com/TyCoding\">https://github.com/TyCoding</a></td>\n</tr>\n<tr>\n<td>Blog</td>\n<td><a href=\"https://tycoding.cn\">https://tycoding.cn</a></td>\n</tr>\n</tbody>\n</table>\n<ul>\n<li>list one</li><li>list two</li><li>list there</li></ul>\n<h1 id=\"h1-contact\"><a name=\"Contact\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Contact</h1><ul>\n<li><a href=\"http://www.tycoding.cn\">Blog@TyCoding’s blog</a></li><li><a href=\"https://github.com/TyCoding\">GitHub@TyCoding</a></li><li><a href=\"https://www.zhihu.com/people/tomo-83-82/activities\">ZhiHu@TyCoding</a></li><li>QQ Group: 671017003</li></ul>\n', '2019-09-22 14:57:51');
INSERT INTO `tb_article` VALUES (2, 'How to write an article? --2', 'tycoding', '<h1 id=\"h1-how-to-write-an-article-\" style=\"font-family: Roboto, sans-serif;\">How to write an article?</h1><h2 id=\"h2-markdown-rules\" style=\"font-family: Roboto, sans-serif;\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><h1><p style=\"color: rgb(96, 98, 102); font-size: 14px;\"><span style=\"font-weight: bolder;\">For example</span></p><p style=\"color: rgb(96, 98, 102); font-size: 14px;\"><a href=\"https://tycoding.cn/\">https://tycoding.cn</a></p></h1>', '<h1 id=\"h1-how-to-write-an-article-\"><a name=\"How to write an article?\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>How to write an article?</h1><h2 id=\"h2-markdown-rules\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><strong>For example</strong></p>\n<p><a href=\"https://tycoding.cn\">https://tycoding.cn</a></p>\n<pre><code class=\"lang-java\">public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n}\n</code></pre>\n<p><img src=\"http://img.api.tycoding.cn/1568958650973.jpeg\" alt=\"\">\n<table>\n<thead>\n<tr>\n<th>Name</th>\n<th>Link</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>Github</td>\n<td><a href=\"https://github.com/TyCoding\">https://github.com/TyCoding</a></td>\n</tr>\n<tr>\n<td>Blog</td>\n<td><a href=\"https://tycoding.cn\">https://tycoding.cn</a></td>\n</tr>\n</tbody>\n</table>\n<ul>\n<li>list one</li><li>list two</li><li>list there</li></ul>\n<h1 id=\"h1-contact\"><a name=\"Contact\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Contact</h1><ul>\n<li><a href=\"http://www.tycoding.cn\">Blog@TyCoding’s blog</a></li><li><a href=\"https://github.com/TyCoding\">GitHub@TyCoding</a></li><li><a href=\"https://www.zhihu.com/people/tomo-83-82/activities\">ZhiHu@TyCoding</a></li><li>QQ Group: 671017003</li></ul>\n', '2019-09-22 14:58:31');
INSERT INTO `tb_article` VALUES (3, 'How to write an article? --3', 'tycoding', '<h1 id=\"h1-how-to-write-an-article-\" style=\"font-family: Roboto, sans-serif;\">How to write an article?</h1><h2 id=\"h2-markdown-rules\" style=\"font-family: Roboto, sans-serif;\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><span style=\"font-weight: bolder;\">For example</span></p><p><a href=\"https://tycoding.cn/\">https://tycoding.cn</a></p>', '<h1 id=\"h1-how-to-write-an-article-\"><a name=\"How to write an article?\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>How to write an article?</h1><h2 id=\"h2-markdown-rules\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><strong>For example</strong></p>\n<p><a href=\"https://tycoding.cn\">https://tycoding.cn</a></p>\n<pre><code class=\"lang-java\">public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n}\n</code></pre>\n<p><img src=\"http://img.api.tycoding.cn/1568958650973.jpeg\" alt=\"\">\n<table>\n<thead>\n<tr>\n<th>Name</th>\n<th>Link</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>Github</td>\n<td><a href=\"https://github.com/TyCoding\">https://github.com/TyCoding</a></td>\n</tr>\n<tr>\n<td>Blog</td>\n<td><a href=\"https://tycoding.cn\">https://tycoding.cn</a></td>\n</tr>\n</tbody>\n</table>\n<ul>\n<li>list one</li><li>list two</li><li>list there</li></ul>\n<h1 id=\"h1-contact\"><a name=\"Contact\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Contact</h1><ul>\n<li><a href=\"http://www.tycoding.cn\">Blog@TyCoding’s blog</a></li><li><a href=\"https://github.com/TyCoding\">GitHub@TyCoding</a></li><li><a href=\"https://www.zhihu.com/people/tomo-83-82/activities\">ZhiHu@TyCoding</a></li><li>QQ Group: 671017003</li></ul>\n', '2019-09-22 14:58:54');
COMMIT;

-- ----------------------------
-- Table structure for tb_article_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_category`;
CREATE TABLE `tb_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='文章&&分类关联表';

-- ----------------------------
-- Records of tb_article_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_category` VALUES (1, 1, 1);
INSERT INTO `tb_article_category` VALUES (2, 2, 1);
INSERT INTO `tb_article_category` VALUES (3, 3, 1);
INSERT INTO `tb_article_category` VALUES (4, 2, 1);
INSERT INTO `tb_article_category` VALUES (5, 1, 4);
COMMIT;

-- ----------------------------
-- Table structure for tb_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_tag`;
CREATE TABLE `tb_article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='文章&&标签关联表';

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_tag` VALUES (1, 1, 4);
INSERT INTO `tb_article_tag` VALUES (2, 2, 4);
INSERT INTO `tb_article_tag` VALUES (3, 3, 1);
INSERT INTO `tb_article_tag` VALUES (4, 3, 4);
INSERT INTO `tb_article_tag` VALUES (5, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of tb_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_category` VALUES (1, '测试');
INSERT INTO `tb_category` VALUES (2, '随笔');
INSERT INTO `tb_category` VALUES (3, '心情');
INSERT INTO `tb_category` VALUES (4, 'springboot');
COMMIT;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `nickname` varchar(20) DEFAULT NULL COMMENT '给谁留言',
  `content` text COMMENT '留言内容',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `device` varchar(100) DEFAULT NULL COMMENT '设备',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '留言时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
BEGIN;
INSERT INTO `tb_comment` VALUES (1, 13, 'tycoding', '测试留言', 'tytumo@163.com', '172.0.0.1', 'Mac OS', '北京市-海淀区', '2020-06-27 16:25:42');
INSERT INTO `tb_comment` VALUES (2, 13, 'test1', '测试留言', 'test@163.com', '172.0.0.1', 'Mac OS', '北京市-海淀区', '2020-06-27 16:56:19');
INSERT INTO `tb_comment` VALUES (4, 13, '1', 'sd', '2', '127.0.0.1', 'Chrome 8,Mac OS X', '内网IP|0|0|内网IP|内网IP', '2020-06-28 18:35:23');
INSERT INTO `tb_comment` VALUES (5, 13, '1', '1', '1', '127.0.0.1', 'Chrome 8,Mac OS X', '内网IP|0|0|内网IP|内网IP', '2020-06-28 18:58:29');
COMMIT;

-- ----------------------------
-- Table structure for tb_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_link`;
CREATE TABLE `tb_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '连接名称',
  `url` varchar(200) DEFAULT NULL COMMENT '连接URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='友链表';

-- ----------------------------
-- Records of tb_link
-- ----------------------------
BEGIN;
INSERT INTO `tb_link` VALUES (1, 'Blog', 'http://tycoding.cn');
INSERT INTO `tb_link` VALUES (2, 'Github', 'https://github.com/tycoding');
COMMIT;

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(20) DEFAULT NULL COMMENT '操作描述',
  `time` bigint(20) DEFAULT NULL COMMENT '耗时(毫秒)',
  `method` varchar(100) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `location` varchar(20) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of tb_log
-- ----------------------------
BEGIN;
INSERT INTO `tb_log` VALUES (87, 'tycoding', '新增文章', 1, 'cn.tycoding.biz.controller.ArticleController.add()', ' sysArticle\"SysArticle(id=14, title=1, author=tycoding, des=1, content=1, create...', '127.0.0.1', '2020-06-28 23:07:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (88, 'tycoding', '更新文章', 1, 'cn.tycoding.biz.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=14, title=123, author=tycoding, des=1, content=1, crea...', '127.0.0.1', '2020-06-28 23:07:55', '内网IP|0|0|内网IP|内网IP');
COMMIT;

-- ----------------------------
-- Table structure for tb_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_log`;
CREATE TABLE `tb_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `device` varchar(255) DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_login_log
-- ----------------------------
BEGIN;
INSERT INTO `tb_login_log` VALUES (1, 'tycoding', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-06-28 21:13:18', 'Chrome 8 -- Mac OS X');
INSERT INTO `tb_login_log` VALUES (2, 'tycoding', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2020-06-28 21:17:13', 'Chrome 8 -- Mac OS X');
COMMIT;

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
BEGIN;
INSERT INTO `tb_tag` VALUES (1, '随笔');
INSERT INTO `tb_tag` VALUES (4, '测试');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `des` varchar(100) DEFAULT NULL COMMENT '介绍',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'tycoding', 'c82a27d50dd5f3a536c85211cc7f2346', '/img/avatar/20180414165815.jpg', 'tytumo@163.com', '兴趣使然的Coder', '2020-06-27 16:55:05');
INSERT INTO `tb_user` VALUES (2, 'test', 'd256d670b614b1f54cecfb3874c025f1', '/img/avatar/20180414165815.jpg', NULL, NULL, '2020-06-26 16:55:08');
INSERT INTO `tb_user` VALUES (3, 'test2', 'd256d670b614b1f54cecfb3874c025f1', '/img/avatar/20180414165815.jpg', '12tycoding@11.com', NULL, '2020-06-27 16:55:13');
INSERT INTO `tb_user` VALUES (4, '123', 'd256d670b614b1f54cecfb3874c025f1', '/img/avatar/20180414165815.jpg', '123@11.com', NULL, '2020-06-27 16:55:13');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
