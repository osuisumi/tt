/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.2
Source Server Version : 50614
Source Host           : 192.168.0.2:3306
Source Database       : nts

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-02-18 09:59:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `train`
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `ID` varchar(64) NOT NULL,
  `PROJECT_ID` varchar(64) DEFAULT NULL,
  `NAME` varchar(128) DEFAULT NULL,
  `DESCRIPTION` varchar(4000) DEFAULT NULL,
  `STATE` varchar(16) DEFAULT NULL,
  `TYPE` varchar(16) DEFAULT NULL,
  `TRAINING_TIME_START` date DEFAULT NULL,
  `TRAINING_TIME_END` date DEFAULT NULL,
  `REGISTER_TIME_START` date DEFAULT NULL,
  `REGISTER_TIME_END` date DEFAULT NULL,
  `ELECTIVES_TIME_START` date DEFAULT NULL,
  `ELECTIVES_TIME_END` date DEFAULT NULL,
  `REGISTER_NUM` smallint(6) DEFAULT NULL,
  `ELECTIVES_NUM` smallint(6) DEFAULT NULL,
  `CREATOR` varchar(64) NOT NULL,
  `CREATE_TIME` bigint(20) DEFAULT NULL,
  `UPDATEDBY` varchar(64) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `VERSION` smallint(6) DEFAULT NULL,
  `IS_DELETED` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train
-- ----------------------------

-- ----------------------------
-- Table structure for `train_register`
-- ----------------------------
DROP TABLE IF EXISTS `train_register`;
CREATE TABLE `train_register` (
  `ID` varchar(64) NOT NULL,
  `TRAIN_ID` varchar(64) DEFAULT NULL,
  `USER_ID` varchar(64) DEFAULT NULL,
  `STATE` varchar(16) DEFAULT NULL,
  `CREATOR` varchar(64) NOT NULL,
  `CREATE_TIME` bigint(20) DEFAULT NULL,
  `UPDATEDBY` varchar(64) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `VERSION` smallint(6) DEFAULT NULL,
  `IS_DELETED` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train_register
-- ----------------------------

-- ----------------------------
-- Table structure for `train_register_audit`
-- ----------------------------
DROP TABLE IF EXISTS `train_register_audit`;
CREATE TABLE `train_register_audit` (
  `ID` varchar(64) NOT NULL,
  `TRAIN_REGISTER_ID` varchar(64) DEFAULT NULL,
  `AUDIT_RESULT` varchar(16) DEFAULT NULL,
  `AUDIT_OPIONION` varchar(1000) DEFAULT NULL,
  `AUDIT_USER_ID` varchar(64) DEFAULT NULL,
  `CREATOR` varchar(64) NOT NULL,
  `CREATE_TIME` bigint(20) DEFAULT NULL,
  `UPDATEDBY` varchar(64) DEFAULT NULL,
  `UPDATE_TIME` bigint(20) DEFAULT NULL,
  `VERSION` smallint(6) DEFAULT NULL,
  `IS_DELETED` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train_register_audit
-- ----------------------------
