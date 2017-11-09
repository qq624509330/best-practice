/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : best_practice

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-11-09 14:03:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `insert_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `update_time` datetime DEFAULT NULL,
  `remove_time` datetime DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0000000001', '1970-01-01 00:00:00', null, null, 'aa', '1234');
INSERT INTO `user` VALUES ('0000000002', '1970-01-01 00:00:00', null, null, 'bb', '1234');
INSERT INTO `user` VALUES ('0000000003', '1970-01-01 00:00:00', null, null, 'cc', '1234');
