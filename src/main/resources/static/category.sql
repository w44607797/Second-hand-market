/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : localhost:3306
 Source Schema         : mar

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 06/04/2022 20:50:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` bigint(11) NOT NULL AUTO_INCREMENT,
  `category_Id` int(11) NULL DEFAULT NULL,
  `category_Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_Id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 1, '图书、音像、电子书刊', 0);
INSERT INTO `category` VALUES (2, 2, '手机', 0);
INSERT INTO `category` VALUES (3, 1, '电子书刊', 1);
INSERT INTO `category` VALUES (4, 2, '音像', 1);
INSERT INTO `category` VALUES (5, 1, '电子书', 3);
INSERT INTO `category` VALUES (6, 2, '网络原创', 3);
INSERT INTO `category` VALUES (7, 1, '音乐', 4);
INSERT INTO `category` VALUES (8, 2, '影视', 4);

SET FOREIGN_KEY_CHECKS = 1;
