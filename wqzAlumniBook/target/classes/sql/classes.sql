/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : wqzproject

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-04-16 18:10:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classes_id` int(11) NOT NULL AUTO_INCREMENT,
  `classes_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classes_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `header_URI` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/img/header01.jpg',
  `monitor_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`classes_id`,`classes_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', '1601', '16通信1', '/img/schoolbadge2.png', '2016010001');
INSERT INTO `classes` VALUES ('2', '1602', '16通信2', '/img/schoolbadge2.png', '2016020001');
INSERT INTO `classes` VALUES ('3', '1603', '16通信中兴1', '/img/schoolbadge2.png', '2016030001');
INSERT INTO `classes` VALUES ('4', '1604', '16通信中兴2', '/img/schoolbadge2.png', '2016040001');
INSERT INTO `classes` VALUES ('5', '1605', '16网络1', '/img/schoolbadge2.png', '2016050001');
INSERT INTO `classes` VALUES ('6', '1606', '16网络2', '/img/schoolbadge2.png', '2016060001');
INSERT INTO `classes` VALUES ('7', '1701', '17通信1', '/img/schoolbadge2.png', '2017010001');
INSERT INTO `classes` VALUES ('8', '1702', '17通信2', '/img/schoolbadge2.png', '2017020001');
INSERT INTO `classes` VALUES ('9', '1703', '17通信中兴1', '/img/schoolbadge2.png', '2017030001');
INSERT INTO `classes` VALUES ('10', '1704', '17通信中兴2', '/img/schoolbadge2.png', '2017040001');
INSERT INTO `classes` VALUES ('11', '1705', '17网络1', '/img/schoolbadge2.png', '2017050001');
INSERT INTO `classes` VALUES ('14', '1706', '17网络1', '/img/schoolbadge2.png', '2017060001');
