CREATE DATABASE `web` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE web;

DROP TABLE IF EXISTS `web`.`user`;
CREATE TABLE `web`.`user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(300) DEFAULT NULL,
    `password` varchar(300) DEFAULT NULL,
    `role` varchar(300) DEFAULT NULL,
    `salt` varchar(300) DEFAULT NULL,
    `avatar` varchar(300) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `web`.`message`;
CREATE TABLE `web`.`message`
(
    `author`  varchar(100) NOT NULL,
    `content` varchar(100) NOT NULL
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `web`.`session`;
CREATE TABLE `web`.`session`
(
    `username`  varchar(100) NOT NULL,
    `sessionId` varchar(100) NOT NULL
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `web`.`todo`;
CREATE TABLE `web`.`todo`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `content` varchar(45) NOT NULL,
    `userId`  int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `web`.`weibo`;
CREATE TABLE `web`.`weibo`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `content` varchar(45) NOT NULL,
    `userId`  int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `web`.`comment`;
CREATE TABLE `web`.`comment`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `content` varchar(45) NOT NULL,
    `weiboId`  int(11) NOT NULL,
    `userId`  int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `web`.`follow`;
CREATE TABLE `web`.`follow`
(
    `followerId` int(11) NOT NULL,
    `followeeId` int(11) NOT NULL,
    PRIMARY KEY (`followerId`, `followeeId`)
) ENGINE=InnoDB;