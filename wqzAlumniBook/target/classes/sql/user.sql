/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : wqzproject

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-04-16 18:31:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_admin` int(1) DEFAULT '-1',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('14', '2016010001', 'efddc032eccbd28709d934adf351ca67', '1', '15914752059', '1348364590@qq.com');
INSERT INTO `user` VALUES ('15', '2016010002', '1d90419a102293ead9bfa6bab0c94eee', '-1', '15914752059', 'wuaong@163.com');
