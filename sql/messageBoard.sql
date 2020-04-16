/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : wqzproject

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-04-16 18:31:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for messageboard
-- ----------------------------
DROP TABLE IF EXISTS `messageboard`;
CREATE TABLE `messageboard` (
  `nid` int(255) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messageboard
-- ----------------------------
INSERT INTO `messageboard` VALUES ('11', '欢迎来到广东技术师范大学', '2016010001', '2020-04-15 01:43:45', '2016010003');
INSERT INTO `messageboard` VALUES ('13', '我来自电子与信息学院', '2016010002', '2020-04-15 01:43:59', '2016010005');
INSERT INTO `messageboard` VALUES ('16', '我来自16通信中兴2班', '2016010001', '2020-04-15 02:45:02', '2016010004');
INSERT INTO `messageboard` VALUES ('25', '我是通信工程的', '2017010001', '2020-04-15 23:29:22', '2016010001');
INSERT INTO `messageboard` VALUES ('26', '你好学弟', '2017010002', '2020-04-15 23:29:25', '2016010001');
INSERT INTO `messageboard` VALUES ('27', '呵呵', '2017010003', '2020-04-16 00:13:09', '2016010001');
INSERT INTO `messageboard` VALUES ('28', '啦啦啦', '2017010003', '2020-04-16 00:14:47', '2016010002');
