/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.2
Source Server Version : 50614
Source Host           : 192.168.0.2:3306
Source Database       : nts

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-02-18 09:59:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `ID` varchar(64) NOT NULL,
  `NAME` varchar(64) DEFAULT '',
  `DESCRIPTION` text,
  `RELATION_ID` varchar(64) DEFAULT NULL,
  `STATE` varchar(16) DEFAULT NULL,
  `TYPE` varchar(16) DEFAULT NULL,
  `INFO_IMAGE` varchar(256) DEFAULT NULL COMMENT '项目资料图',
  `INTRO_VIDEO` varchar(256) DEFAULT NULL COMMENT '项目介绍视频',
  `SITE` varchar(256) DEFAULT NULL COMMENT '项目',
  `CREATOR` varchar(64) NOT NULL,
  `CREATE_TIME` bigint(20) DEFAULT NULL,
  `UPDATEDBY` varchar(64) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `VERSION` smallint(6) DEFAULT NULL,
  `IS_DELETED` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
