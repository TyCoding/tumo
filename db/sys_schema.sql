/*
 *  mysql-v: 5.7.22
 */

-- 创建数据库
-- CREATE DATABASE tumo DEFAULT CHARACTER SET utf8;

-- 文章表
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE tb_article(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` VARCHAR(200) DEFAULT NULL COMMENT '标题',
  `title_pic` VARCHAR(200) DEFAULT NULL COMMENT '封面图片',
  `category` VARCHAR(100) DEFAULT NULL COMMENT '分类',
  `author` varchar(100) NOT NULL COMMENT '作者',
  `content` MEDIUMTEXT DEFAULT NULL COMMENT '内容',
  `origin` VARCHAR(100) DEFAULT NULL COMMENT '来源',
  `state` VARCHAR(100) NOT NULL DEFAULT '0' COMMENT '状态,-1:失效，0:存入草稿，1:发布',
  `view_cou` bigint DEFAULT 0 COMMENT '浏览次数',
  `com_cou` bigint DEFAULT 0 COMMENT '评论次数',
  `publish_time` timestamp NOT NULL DEFAULT '1970-02-01 00:00:01' COMMENT '发布时间',
  `edit_time` timestamp NOT NULL DEFAULT '1970-02-01 00:00:01' COMMENT '上次修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '文章表';

-- 分类表
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE tb_category(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `c_name` VARCHAR(100) COMMENT '分类名称',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '分类表';

-- 标签表
DROP TABLE IF EXISTS `tb_tags`;
CREATE TABLE tb_tags(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `t_name` VARCHAR(100) COMMENT '标签名称',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '标签表';

-- 评论表
DROP TABLE IF EXISTS `tb_comments`;
CREATE TABLE tb_comments(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `p_id` bigint DEFAULT 0 COMMENT '父级ID，给哪个留言进行回复',
  `c_id` bigint DEFAULT 0 COMMENT '子级ID，给哪个留言下的回复进行评论',
  `article_title` VARCHAR(200) DEFAULT NULL COMMENT '文章标题',
  `article_id` bigint DEFAULT NULL COMMENT '文章ID',
  `author` VARCHAR(200) DEFAULT NULL COMMENT '留言人',
  `author_id` VARCHAR(200) DEFAULT NULL COMMENT '给谁留言',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '留言邮箱',
  `content` TEXT DEFAULT NULL COMMENT '留言内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
  `url` VARCHAR(200) DEFAULT NULL COMMENT '链接',
  `state` VARCHAR(100) DEFAULT '正常' COMMENT '状态',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '评论表';

-- 友链表
DROP TABLE IF EXISTS `tb_links`;
CREATE TABLE tb_links(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `l_name` VARCHAR(100) COMMENT '连接名称',
  `url` VARCHAR(200) COMMENT '连接URL',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '友链表';

-- 用户表
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE tb_user(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` VARCHAR(100) NOT NULL COMMENT '用户名',
  `nickname` VARCHAR(100) NOT NULL COMMENT '昵称',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `salt` VARCHAR(200) NOT NULL COMMENT '盐值',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '标签表';

-- 文章&&分类关联表
DROP TABLE IF EXISTS `tb_article_category`;
CREATE TABLE tb_article_category(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `category_id` bigint NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '文章&&分类关联表';

-- 文章&&标签关联表
DROP TABLE IF EXISTS `tb_article_tags`;
CREATE TABLE `tb_article_tags`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `tags_id` bigint NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) CHARSET=utf8 ENGINE=InnoDB COMMENT '文章&&标签关联表';




