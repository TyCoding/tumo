/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : tumo

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 22/09/2019 16:25:32
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
  `cover` varchar(400) DEFAULT NULL COMMENT '封面图片',
  `author` varchar(100) NOT NULL COMMENT '作者',
  `content` mediumtext COMMENT '内容',
  `content_md` mediumtext COMMENT '内容-Markdown',
  `category` varchar(20) DEFAULT NULL COMMENT '分类',
  `origin` varchar(100) DEFAULT NULL COMMENT '来源',
  `state` varchar(100) NOT NULL COMMENT '状态',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `edit_time` datetime NOT NULL COMMENT '上次修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `type` int(11) DEFAULT '0' COMMENT '类型， 0原创 1转载',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of tb_article
-- ----------------------------
BEGIN;
INSERT INTO `tb_article` VALUES (8, 'How to write an article?', 'http://img.api.tycoding.cn/1569140673305.jpeg', 'tycoding', '<h1 id=\"h1-how-to-write-an-article-\"><a name=\"How to write an article?\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>How to write an article?</h1><h2 id=\"h2-markdown-rules\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><strong>For example</strong></p>\n<p><a href=\"https://tycoding.cn\">https://tycoding.cn</a></p>\n<pre><code class=\"lang-java\">public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n}\n</code></pre>\n<p><img src=\"http://img.api.tycoding.cn/1568958650973.jpeg\" alt=\"\">\n<table>\n<thead>\n<tr>\n<th>Name</th>\n<th>Link</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>Github</td>\n<td><a href=\"https://github.com/TyCoding\">https://github.com/TyCoding</a></td>\n</tr>\n<tr>\n<td>Blog</td>\n<td><a href=\"https://tycoding.cn\">https://tycoding.cn</a></td>\n</tr>\n</tbody>\n</table>\n<ul>\n<li>list one</li><li>list two</li><li>list there</li></ul>\n<h1 id=\"h1-contact\"><a name=\"Contact\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Contact</h1><ul>\n<li><a href=\"http://www.tycoding.cn\">Blog@TyCoding’s blog</a></li><li><a href=\"https://github.com/TyCoding\">GitHub@TyCoding</a></li><li><a href=\"https://www.zhihu.com/people/tomo-83-82/activities\">ZhiHu@TyCoding</a></li><li>QQ Group: 671017003</li></ul>\n', '# How to write an article?\n\n## Markdown rules\n\n**For example**\n\n[https://tycoding.cn](https://tycoding.cn)\n\n```java\npublic static void main(String[] args) {\n		System.out.println(\"Hello World\");\n}\n```\n\n![](http://img.api.tycoding.cn/1568958650973.jpeg)\n\n| Name | Link |\n| -- | -- |\n| Github | https://github.com/TyCoding |\n| Blog | https://tycoding.cn |\n\n* list one\n* list two\n* list there\n\n# Contact \n\n- [Blog@TyCoding\'s blog](http://www.tycoding.cn)\n- [GitHub@TyCoding](https://github.com/TyCoding)\n- [ZhiHu@TyCoding](https://www.zhihu.com/people/tomo-83-82/activities)\n- QQ Group: 671017003', '1', NULL, '1', '2019-09-22 16:24:35', '2019-09-22 14:57:51', '2019-09-22 14:57:51', 0);
INSERT INTO `tb_article` VALUES (9, 'How to write an article? --2', 'http://img.api.tycoding.cn/1569140659555.jpeg', 'tycoding', '<h1 id=\"h1-how-to-write-an-article-\"><a name=\"How to write an article?\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>How to write an article?</h1><h2 id=\"h2-markdown-rules\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><strong>For example</strong></p>\n<p><a href=\"https://tycoding.cn\">https://tycoding.cn</a></p>\n<pre><code class=\"lang-java\">public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n}\n</code></pre>\n<p><img src=\"http://img.api.tycoding.cn/1568958650973.jpeg\" alt=\"\">\n<table>\n<thead>\n<tr>\n<th>Name</th>\n<th>Link</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>Github</td>\n<td><a href=\"https://github.com/TyCoding\">https://github.com/TyCoding</a></td>\n</tr>\n<tr>\n<td>Blog</td>\n<td><a href=\"https://tycoding.cn\">https://tycoding.cn</a></td>\n</tr>\n</tbody>\n</table>\n<ul>\n<li>list one</li><li>list two</li><li>list there</li></ul>\n<h1 id=\"h1-contact\"><a name=\"Contact\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Contact</h1><ul>\n<li><a href=\"http://www.tycoding.cn\">Blog@TyCoding’s blog</a></li><li><a href=\"https://github.com/TyCoding\">GitHub@TyCoding</a></li><li><a href=\"https://www.zhihu.com/people/tomo-83-82/activities\">ZhiHu@TyCoding</a></li><li>QQ Group: 671017003</li></ul>\n', '# How to write an article?\n\n## Markdown rules\n\n**For example**\n\n[https://tycoding.cn](https://tycoding.cn)\n\n```java\npublic static void main(String[] args) {\n		System.out.println(\"Hello World\");\n}\n```\n\n![](http://img.api.tycoding.cn/1568958650973.jpeg)\n\n| Name | Link |\n| -- | -- |\n| Github | https://github.com/TyCoding |\n| Blog | https://tycoding.cn |\n\n* list one\n* list two\n* list there\n\n# Contact \n\n- [Blog@TyCoding\'s blog](http://www.tycoding.cn)\n- [GitHub@TyCoding](https://github.com/TyCoding)\n- [ZhiHu@TyCoding](https://www.zhihu.com/people/tomo-83-82/activities)\n- QQ Group: 671017003', '1', NULL, '1', '2019-09-22 16:24:22', '2019-09-22 14:58:31', '2019-09-22 14:58:31', 0);
INSERT INTO `tb_article` VALUES (10, 'How to write an article? --3', 'http://img.api.tycoding.cn/1569140641446.jpg', 'tycoding', '<h1 id=\"h1-how-to-write-an-article-\"><a name=\"How to write an article?\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>How to write an article?</h1><h2 id=\"h2-markdown-rules\"><a name=\"Markdown rules\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Markdown rules</h2><p><strong>For example</strong></p>\n<p><a href=\"https://tycoding.cn\">https://tycoding.cn</a></p>\n<pre><code class=\"lang-java\">public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n}\n</code></pre>\n<p><img src=\"http://img.api.tycoding.cn/1568958650973.jpeg\" alt=\"\">\n<table>\n<thead>\n<tr>\n<th>Name</th>\n<th>Link</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>Github</td>\n<td><a href=\"https://github.com/TyCoding\">https://github.com/TyCoding</a></td>\n</tr>\n<tr>\n<td>Blog</td>\n<td><a href=\"https://tycoding.cn\">https://tycoding.cn</a></td>\n</tr>\n</tbody>\n</table>\n<ul>\n<li>list one</li><li>list two</li><li>list there</li></ul>\n<h1 id=\"h1-contact\"><a name=\"Contact\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Contact</h1><ul>\n<li><a href=\"http://www.tycoding.cn\">Blog@TyCoding’s blog</a></li><li><a href=\"https://github.com/TyCoding\">GitHub@TyCoding</a></li><li><a href=\"https://www.zhihu.com/people/tomo-83-82/activities\">ZhiHu@TyCoding</a></li><li>QQ Group: 671017003</li></ul>\n', '# How to write an article?\n\n## Markdown rules\n\n**For example**\n\n[https://tycoding.cn](https://tycoding.cn)\n\n```java\npublic static void main(String[] args) {\n		System.out.println(\"Hello World\");\n}\n```\n\n![](http://img.api.tycoding.cn/1568958650973.jpeg)\n\n| Name | Link |\n| -- | -- |\n| Github | https://github.com/TyCoding |\n| Blog | https://tycoding.cn |\n\n* list one\n* list two\n* list there\n\n# Contact \n\n- [Blog@TyCoding\'s blog](http://www.tycoding.cn)\n- [GitHub@TyCoding](https://github.com/TyCoding)\n- [ZhiHu@TyCoding](https://www.zhihu.com/people/tomo-83-82/activities)\n- QQ Group: 671017003', '1', NULL, '1', '2019-09-22 16:24:08', '2019-09-22 14:58:54', '2019-09-22 14:58:54', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='文章&&分类关联表';

-- ----------------------------
-- Records of tb_article_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_category` VALUES (16, 10, 1);
INSERT INTO `tb_article_category` VALUES (17, 9, 1);
INSERT INTO `tb_article_category` VALUES (18, 8, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='文章&&标签关联表';

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------
BEGIN;
INSERT INTO `tb_article_tag` VALUES (29, 10, 5);
INSERT INTO `tb_article_tag` VALUES (30, 9, 5);
INSERT INTO `tb_article_tag` VALUES (31, 8, 1);
INSERT INTO `tb_article_tag` VALUES (32, 8, 4);
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
  `p_id` bigint(20) DEFAULT '0' COMMENT '父级ID，给哪个留言进行回复',
  `c_id` bigint(20) DEFAULT '0' COMMENT '子级ID，给哪个留言下的回复进行评论',
  `article_title` varchar(200) DEFAULT NULL COMMENT '文章标题',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `c_name` varchar(20) DEFAULT NULL COMMENT '给谁留言',
  `time` datetime NOT NULL COMMENT '留言时间',
  `content` text COMMENT '留言内容',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `url` varchar(200) DEFAULT NULL COMMENT '网址',
  `sort` bigint(20) DEFAULT '0' COMMENT '分类：0:默认，文章详情页，1:友链页，2:关于页',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `device` varchar(100) DEFAULT NULL COMMENT '设备',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for tb_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_link`;
CREATE TABLE `tb_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '连接名称',
  `url` varchar(200) DEFAULT NULL COMMENT '连接URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='友链表';

-- ----------------------------
-- Records of tb_link
-- ----------------------------
BEGIN;
INSERT INTO `tb_link` VALUES (1, 'Tycoding\'s blog', 'http://tycoding.cn');
INSERT INTO `tb_link` VALUES (3, 'TyCodingAdvantage', 'http://study.tycoding.cn');
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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of tb_log
-- ----------------------------
BEGIN;
INSERT INTO `tb_log` VALUES (47, 'tycoding', '新增文章', 33, 'cn.tycoding.system.controller.ArticleController.save()', ' sysArticle\"SysArticle(id=8, title=How to write an article?, cover=, author=tyco...', '127.0.0.1', '2019-09-22 14:57:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (48, 'tycoding', '新增文章', 21, 'cn.tycoding.system.controller.ArticleController.save()', ' sysArticle\"SysArticle(id=9, title=How to write an article? --2, cover=, author=...', '127.0.0.1', '2019-09-22 14:58:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (49, 'tycoding', '新增文章', 25, 'cn.tycoding.system.controller.ArticleController.save()', ' sysArticle\"SysArticle(id=10, title=How to write an article? --3, cover=, author...', '127.0.0.1', '2019-09-22 14:58:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (50, 'tycoding', '更新文章', 124, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=10, title=How to write an article? --3, cover=http://l...', '127.0.0.1', '2019-09-22 16:11:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (51, 'tycoding', '更新文章', 117, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=10, title=How to write an article? --3, cover=http://l...', '127.0.0.1', '2019-09-22 16:13:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (52, 'tycoding', '更新文章', 47, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=9, title=How to write an article? --2, cover=http://lo...', '127.0.0.1', '2019-09-22 16:14:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (53, 'tycoding', '更新文章', 67, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=8, title=How to write an article?, cover=http://localh...', '127.0.0.1', '2019-09-22 16:14:14', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (54, 'tycoding', '更新文章', 75, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=10, title=How to write an article? --3, cover=http://l...', '127.0.0.1', '2019-09-22 16:15:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (55, 'tycoding', '更新文章', 42027, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=10, title=How to write an article? --3, cover=http://l...', '127.0.0.1', '2019-09-22 16:16:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (56, 'tycoding', '更新文章', 7166, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=10, title=How to write an article? --3, cover=http://l...', '127.0.0.1', '2019-09-22 16:17:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (57, 'tycoding', '更新文章', 337, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=10, title=How to write an article? --3, cover=http://i...', '127.0.0.1', '2019-09-22 16:24:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (58, 'tycoding', '更新文章', 45, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=9, title=How to write an article? --2, cover=http://im...', '127.0.0.1', '2019-09-22 16:24:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `tb_log` VALUES (59, 'tycoding', '更新文章', 48, 'cn.tycoding.system.controller.ArticleController.update()', ' sysArticle\"SysArticle(id=8, title=How to write an article?, cover=http://img.ap...', '127.0.0.1', '2019-09-22 16:24:35', '内网IP|0|0|内网IP|内网IP');
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_login_log
-- ----------------------------
BEGIN;
INSERT INTO `tb_login_log` VALUES (33, 'tycoding', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-09-22 15:57:29', 'Unknown -- Unknown');
COMMIT;

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
BEGIN;
INSERT INTO `tb_tag` VALUES (1, '随笔');
INSERT INTO `tb_tag` VALUES (4, '测试');
INSERT INTO `tb_tag` VALUES (5, '博客日志');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(200) NOT NULL COMMENT '盐值',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `introduce` varchar(100) DEFAULT NULL COMMENT '介绍',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'tycoding', '5f9059b3feff398c928c7c1239e64975', 'afbe4bd05b55b755d2a3e7df3bc25586', 'http://img.api.tycoding.cn/1568958650973.jpeg', '兴趣使然的Coder', '银河街角，时光路口');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
