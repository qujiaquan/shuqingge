/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 80022 (8.0.22)
 Source Host           : localhost:3306
 Source Schema         : material

 Target Server Type    : MySQL
 Target Server Version : 80022 (8.0.22)
 File Encoding         : 65001

 Date: 11/06/2024 20:10:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for categorie
-- ----------------------------
DROP TABLE IF EXISTS `categorie`;
CREATE TABLE `categorie`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `format_id` int NULL DEFAULT NULL COMMENT '素材格式',
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of categorie
-- ----------------------------
INSERT INTO `categorie` VALUES (1, '动物', 1, 'admin', '2024-05-03 19:31:57');
INSERT INTO `categorie` VALUES (2, '植物', 1, 'admin', '2024-05-03 20:01:01');
INSERT INTO `categorie` VALUES (3, '其他', 1, 'admin', '2024-05-03 20:01:26');
INSERT INTO `categorie` VALUES (4, '动物', 2, 'admin', '2024-05-03 20:01:41');
INSERT INTO `categorie` VALUES (6, '植物', 2, 'admin', '2024-05-03 20:02:03');
INSERT INTO `categorie` VALUES (7, '其他', 2, 'admin', '2024-05-03 20:11:49');
INSERT INTO `categorie` VALUES (8, '其他', 4, 'admin', '2024-05-04 10:31:24');
INSERT INTO `categorie` VALUES (9, '其他', 3, 'admin', '2024-05-04 10:31:32');
INSERT INTO `categorie` VALUES (10, '其他', 5, 'admin', '2024-05-04 10:31:38');
INSERT INTO `categorie` VALUES (11, '风景', 1, 'admin', '2024-05-09 18:54:21');
INSERT INTO `categorie` VALUES (12, '风景', 2, 'admin', '2024-05-09 18:54:30');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `material_id` int NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (1, NULL, 6, 7, '2024-05-03 20:32:43', NULL, NULL);
INSERT INTO `collection` VALUES (7, NULL, 6, 11, '2024-05-28 00:12:47', NULL, NULL);
INSERT INTO `collection` VALUES (9, NULL, 6, 18, '2024-05-31 09:49:48', NULL, NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `material_id` int NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, '这是测试评论', 6, 17, '2024-05-26 18:20:58');
INSERT INTO `comment` VALUES (3, '这是第二条评论', 6, 17, '2024-05-26 18:42:34');
INSERT INTO `comment` VALUES (4, '这个素材也太好用了吧', 6, 12, '2024-05-26 18:43:15');
INSERT INTO `comment` VALUES (5, '这个简历模块非常好用，支持！\n\n', 6, 18, '2024-05-30 18:20:27');

-- ----------------------------
-- Table structure for downloads
-- ----------------------------
DROP TABLE IF EXISTS `downloads`;
CREATE TABLE `downloads`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `material_id` int NULL DEFAULT NULL COMMENT '素材',
  `user_id` int NULL DEFAULT NULL,
  `download_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of downloads
-- ----------------------------
INSERT INTO `downloads` VALUES (1, 6, 6, '2024-05-03 21:08:52');
INSERT INTO `downloads` VALUES (2, 17, 6, '2024-05-27 00:52:10');
INSERT INTO `downloads` VALUES (3, 16, 6, '2024-05-27 00:53:57');
INSERT INTO `downloads` VALUES (4, 17, 6, '2024-05-29 00:17:26');
INSERT INTO `downloads` VALUES (5, 18, 6, '2024-05-30 18:19:42');
INSERT INTO `downloads` VALUES (6, 18, 6, '2024-05-30 18:20:01');
INSERT INTO `downloads` VALUES (7, 26, 6, '2024-05-31 09:05:04');
INSERT INTO `downloads` VALUES (8, 18, 6, '2024-05-31 09:49:42');
INSERT INTO `downloads` VALUES (9, 18, 6, '2024-05-31 09:55:12');
INSERT INTO `downloads` VALUES (10, 18, 6, '2024-05-31 10:06:11');

-- ----------------------------
-- Table structure for format
-- ----------------------------
DROP TABLE IF EXISTS `format`;
CREATE TABLE `format`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of format
-- ----------------------------
INSERT INTO `format` VALUES (1, '图片', 'admin', '2024-05-02 13:45:01');
INSERT INTO `format` VALUES (2, '视频', 'admin', '2024-05-02 13:45:22');
INSERT INTO `format` VALUES (3, 'PPT', 'admin', '2024-05-03 18:51:26');
INSERT INTO `format` VALUES (4, '简历', 'admin', '2024-05-03 20:12:08');
INSERT INTO `format` VALUES (5, '其他', 'admin', '2024-05-04 10:27:54');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `categorie_id` int NULL DEFAULT NULL COMMENT '素材分类',
  `upload_time` datetime NULL DEFAULT NULL,
  `flag` int NULL DEFAULT 0,
  `format_id` int NULL DEFAULT NULL COMMENT '素材格式',
  `file_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_free` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '否',
  `price` float(10, 2) NULL DEFAULT 0.00,
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES (4, '这是一只伤心的小猫', '这个小猫好伤心', 'materialFile/admin/admin/1714795484557123.png', 1, 1, '2024-05-04 12:04:57', 1, 1, '', '是', 0.00, 'admin', '2024-05-04 13:18:29');
INSERT INTO `material` VALUES (5, '风景，神秘主义，神秘主义，情绪，幻想，作曲，光，童话', '本素材作品名称为风景，神秘主义，神秘主义，情绪，幻想，作曲，光，童话,素材编号是3333606， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该风景，神秘主义，神秘主义，情绪，幻想，作曲，光，童话素材模板的大小是 1.89M ， 作品尺寸是6642x4480px(5K)像素', 'materialFile/admin/admin/17152624719501af45f6d7b4034b7af86a3ecfa389a3.jpg', 1, 11, '2024-05-09 21:49:20', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/17152625587731af45f6d7b4034b7af86a3ecfa389a3.jpg', '否', 5.00, 'admin', '2024-05-09 21:49:20');
INSERT INTO `material` VALUES (6, '光，黎明，风景，自然，孤独，背光，海滩，云朵', '本素材作品名称为光，黎明，风景，自然，孤独，背光，海滩，云朵,素材编号是3352161， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该光，黎明，风景，自然，孤独，背光，海滩，云朵素材模板的大小是 1.86M ， 作品尺寸是5978x3985px(5K)像素', 'materialFile/admin/admin/17152626667703af518d872c986aa429243fceb4398e.jpg', 1, 11, '2024-05-09 21:51:37', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/17152626961723af518d872c986aa429243fceb4398e.jpg', '是', 5.00, 'admin', '2024-05-09 21:51:37');
INSERT INTO `material` VALUES (7, '早晨，雾，雾，中国，山，山，四姐妹，风景', '本素材作品名称为早晨，雾，雾，中国，山，山，四姐妹，风景,素材编号是3349773， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该早晨，雾，雾，中国，山，山，四姐妹，风景素材模板的大小是 1.29M ， 作品尺寸是5472x3648px(5K)像素', 'materialFile/admin/admin/17152627761033e3b9cc65e28cb43771b51ba0e12345.jpg', 1, 11, '2024-05-09 21:53:07', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/17152627860293e3b9cc65e28cb43771b51ba0e12345.jpg', '是', 5.00, 'admin', '2024-05-09 21:53:07');
INSERT INTO `material` VALUES (8, '风景摄影', '本素材作品名称为风景摄影,素材编号是3356475， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该风景摄影素材模板的大小是 1.41M ， 作品尺寸是6000x4000px(5K)像素', 'materialFile/admin/admin/17152628340098bae8af111410a82299803675423024.jpg', 1, 11, '2024-05-09 21:54:09', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/17152628489188bae8af111410a82299803675423024.jpg', '是', 5.00, 'admin', '2024-05-09 21:54:09');
INSERT INTO `material` VALUES (9, '狗尾巴草植物素材图片', '本素材作品名称为狗尾巴草植物素材图片,素材编号是2426632， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该狗尾巴草植物素材图片素材模板的大小是 4.63M ， 作品尺寸是4608x3072像素', 'materialFile/admin/admin/171526299257428af83297bc348492072c4662d4358b4.jpg', 1, 2, '2024-05-09 21:56:43', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/171526300257728af83297bc348492072c4662d4358b4.jpg', '否', 0.00, 'admin', '2024-05-09 21:56:43');
INSERT INTO `material` VALUES (10, '野生绿色植物摄影图片', '本素材作品名称为野生绿色植物摄影图片,素材编号是2426553， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该野生绿色植物摄影图片素材模板的大小是 1.25M ， 作品尺寸是4000x5630像素', 'materialFile/admin/admin/1715263030605616b84b9186768c1207d1016b036f.jpg', 1, 2, '2024-05-09 21:57:22', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/1715263041610616b84b9186768c1207d1016b036f.jpg', '否', 0.00, 'admin', '2024-05-09 21:57:22');
INSERT INTO `material` VALUES (11, '冬日野生植物摄影图片', '本素材作品名称为冬日野生植物摄影图片,素材编号是2426313， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该冬日野生植物摄影图片素材模板的大小是 0.88M ， 作品尺寸是3434x4649像素', 'materialFile/admin/admin/1715263064176949edcfa1be214b1d12897c52e6a2a.jpg', 1, 1, '2024-05-09 21:57:56', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/1715263075758949edcfa1be214b1d12897c52e6a2a.jpg', '是', 10.00, 'admin', '2024-05-09 21:57:56');
INSERT INTO `material` VALUES (12, '多汁肉质植物盆栽图片', '本素材作品名称为多汁肉质植物盆栽图片,素材编号是2426045， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该多汁肉质植物盆栽图片素材模板的大小是 2.85M ， 作品尺寸是5184x2920像素', 'materialFile/admin/admin/17152630995075c2c1ca9f819628cc666159d38924a6.jpg', 1, 2, '2024-05-09 21:58:48', 1, 1, 'http://127.0.0.1:8080/materialFile/admin/admin/17152631277325c2c1ca9f819628cc666159d38924a6.jpg', '是', 7.00, 'admin', '2024-05-09 21:58:48');
INSERT INTO `material` VALUES (13, '士兵们在战场夕阳下的剪影', '本素材作品名称为士兵们在战场夕阳下的剪影。,素材编号是3333325， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该士兵们在战场夕阳下的剪影。素材模板的大小是 0.16M ， 作品尺寸是3020x1770px(2K)像素', 'materialFile/admin/admin/1715263868961efa1f09b489062a347ad66777e65d63c.jpg', 1, 7, '2024-05-09 22:11:21', 1, 2, 'http://127.0.0.1:8080/materialFile/admin/admin/1715263881126efa1f09b489062a347ad66777e65d63c.jpg', '否', 0.00, 'admin', '2024-05-09 22:11:21');
INSERT INTO `material` VALUES (14, '灰色鸽子', '本素材作品名称为灰色鸽子图片,素材编号是3941416， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该灰色鸽子图片素材模板的大小是 5.57M ', 'materialFile/admin/admin/1715263956524cac82fc3abe87f28e7a661e578ca8af.jpg', 1, 4, '2024-05-09 22:12:49', 1, 2, 'http://127.0.0.1:8080/materialFile/admin/admin/1715263967878cac82fc3abe87f28e7a661e578ca8af.jpg', '否', 0.00, 'admin', '2024-05-09 22:12:49');
INSERT INTO `material` VALUES (15, '牛奶牛牧场牛奶草原原生态', '本素材作品名称为牛奶牛牧场牛奶草原原生态,素材编号是3880306， 是一张格式为:jpg ， 可以使用 常用图片编辑 等软件打开，颜色模式为RGB的作品。 该牛奶牛牧场牛奶草原原生态素材模板的大小是 2.24M', 'materialFile/admin/admin/17152639909363e7fe07adc2d5087f2e9ad55257449ef.jpg', 6, 4, '2024-05-09 22:13:25', 1, 2, 'http://127.0.0.1:8080/materialFile/admin/admin/17152640043653e7fe07adc2d5087f2e9ad55257449ef.jpg', '否', 0.00, 'admin', '2024-05-09 22:13:25');
INSERT INTO `material` VALUES (16, '工作总结述职计划报告PPT模板', '当前作品类型是PPT模板，使用场景是汇报|报告PPT，也可用于工作总结PPT、工作PPT、总结PPT、述职PPT、计划PPT、报告PPT，作品编号为zspzS2，作品格式为pptx / ppt，该PPT模板文件大小是11.70 MB。源文件下载后，文字、图片等主体内容皆可修改', 'materialFile/admin/admin/1715311491021true.png', 1, 8, '2024-05-10 11:25:18', 1, 3, '', '否', 0.00, 'admin', '2024-05-10 11:25:18');
INSERT INTO `material` VALUES (17, '时尚线条风格市场营销求职简历WORD简历模板', '当前作品类型是简历模板，使用场景是word简历模板，也可用于市场营销简历、市场简历、营销简历、求职简历，作品编号为HDb5V2，作品格式为docx / doc，该简历模板文件大小是170.67 KB。源文件下载后，文字、图片等主体内容皆可修改', 'materialFile/admin/admin/1715311759922true.jpg', 1, 8, '2024-05-10 11:29:37', 1, 4, 'http://127.0.0.1:8080/materialFile/admin/admin/1715311776393V5PPT-10060959-HDb5V2.zip', '否', 0.00, 'admin', '2024-05-10 11:29:37');
INSERT INTO `material` VALUES (18, '极简简约人事行政个人简历下载', '当前作品类型是简历模板，该作品极简、简约风格设计，使用场景是简历模板，也可用于人事行政简历、人事简历、行政简历、个人简历、网站简历，作品编号为EJYkT3，作品格式为docx / doc，该简历模板文件大小是1.44 MB。源文件下载后，文字、图片等主体内容皆可修改', 'materialFile/admin/admin/1717064354658true.jpg', 6, 8, '2024-05-30 18:18:05', 1, 4, 'http://127.0.0.1:8080/materialFile/admin/admin/1717064394078V5PPT-10060920-EJYkT3.zip', '否', 0.00, 'admin', '2024-05-30 18:19:55');
INSERT INTO `material` VALUES (25, '我这有一个好看的PPT模板', '给你免费，点赞收藏吧', 'materialFile/user/xiaoqu111/1717079576460640x500a0a0.jpg', 6, 9, NULL, 0, 3, '', '否', 0.00, NULL, NULL);
INSERT INTO `material` VALUES (29, '图片', '这是一个图片素材', 'materialFile/user/xiaoqu111/1717120463133test2.jpg', 6, 11, NULL, 0, 1, '', '否', 0.00, NULL, NULL);

-- ----------------------------
-- Table structure for notice_req
-- ----------------------------
DROP TABLE IF EXISTS `notice_req`;
CREATE TABLE `notice_req`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `flag` int NULL DEFAULT (0),
  `req_time` datetime NULL DEFAULT NULL,
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice_req
-- ----------------------------
INSERT INTO `notice_req` VALUES (1, '测试素材请求', '我需要一个小猫的照片', 6, 1, '2024-05-03 21:27:24', NULL, NULL);
INSERT INTO `notice_req` VALUES (3, '我需要一个照片', '测试信息', 6, 1, '2024-05-29 00:13:03', NULL, NULL);
INSERT INTO `notice_req` VALUES (4, '我需要一个好看的ppt模板', '谁能给我这个模板，我会给出相应的补偿', 6, 1, '2024-05-30 21:25:24', NULL, NULL);
INSERT INTO `notice_req` VALUES (5, '我需要一个图片素材', '我需要一个图片素材', 6, 1, '2024-05-31 09:53:54', NULL, NULL);

-- ----------------------------
-- Table structure for notice_res
-- ----------------------------
DROP TABLE IF EXISTS `notice_res`;
CREATE TABLE `notice_res`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `notice_req_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `material_id` int NULL DEFAULT NULL,
  `res_time` datetime NULL DEFAULT NULL,
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice_res
-- ----------------------------
INSERT INTO `notice_res` VALUES (1, '请求响应标题', '请求响应内容', 1, 6, 6, '2024-05-03 21:43:56', NULL, NULL);
INSERT INTO `notice_res` VALUES (2, '我这有一个好看的PPT模板', '给你免费，点赞收藏吧', 4, 6, 25, '2024-05-30 22:36:17', NULL, NULL);
INSERT INTO `notice_res` VALUES (3, '图片', '这是一个图片素材', 5, 6, 29, '2024-05-31 09:54:33', NULL, NULL);

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `material_id` int NULL DEFAULT NULL COMMENT '素材',
  `user_id` int NULL DEFAULT NULL,
  `amount` float(10, 2) NULL DEFAULT NULL,
  `payment_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (1, 6, 6, 7.00, '2024-05-03 21:18:14');
INSERT INTO `payments` VALUES (2, 17, 6, NULL, '2024-05-27 00:52:10');
INSERT INTO `payments` VALUES (3, 16, 6, 0.00, '2024-05-27 00:53:57');
INSERT INTO `payments` VALUES (4, 17, 6, 0.00, '2024-05-29 00:17:26');
INSERT INTO `payments` VALUES (5, 18, 6, 0.00, '2024-05-30 18:19:42');
INSERT INTO `payments` VALUES (6, 18, 6, 0.00, '2024-05-30 18:20:01');
INSERT INTO `payments` VALUES (7, 26, 6, 0.00, '2024-05-31 09:05:04');
INSERT INTO `payments` VALUES (8, 18, 6, 0.00, '2024-05-31 09:49:42');
INSERT INTO `payments` VALUES (9, 18, 6, 0.00, '2024-05-31 09:55:12');
INSERT INTO `payments` VALUES (10, 18, 6, 0.00, '2024-05-31 10:06:11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'materialFile/defaultphoto/2.gif',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int NULL DEFAULT (2),
  `flag` int NULL DEFAULT 1,
  `time` datetime NULL DEFAULT NULL,
  `balance` float NULL DEFAULT 0,
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '超级管理员', 'admin', '698d51a19d8a121ce581499d7b701668', '男', '13488499617', 'materialFile/admin/admin/17147321932602.gif', '2933503769@qq.com', 0, 1, '2024-05-04 13:59:05', 1000000000, 'admin', '2024-05-04 13:56:52');
INSERT INTO `user` VALUES (3, '次级管理员', 'admin111', '2c62d47bf39ba81cf24a4ddb52f3e312', '男', '15935874564', 'materialFile\\defaultphoto\\1.gif', 'test@qq.com', 1, 1, '2024-05-03 16:21:35', 0, 'admin', '2024-05-03 16:21:43');
INSERT INTO `user` VALUES (4, '用户', 'user1', '2c62d47bf39ba81cf24a4ddb52f3e312', '女', '18654896542', 'materialFile\\defaultphoto\\1.gif', 'test@qq.com', 2, 1, '2024-05-03 16:22:31', 0, 'admin', '2024-05-03 16:22:40');
INSERT INTO `user` VALUES (5, '小刘管理员', 'xiaoliu111', '698d51a19d8a121ce581499d7b701668', '男', '16549862587', 'materialFile\\defaultphoto\\1.gif', '16549862587@139.com', 1, 1, NULL, 0, 'admin', '2024-05-03 17:27:07');
INSERT INTO `user` VALUES (6, '小屈用户1111', 'xiaoqu111', '698d51a19d8a121ce581499d7b701668', '男', '13488499617', 'materialFile/user/xiaoqu111/1717117853862123.png', '2933503769@qq.com', 2, 1, NULL, 12541, 'xiaoqu111', '2024-05-31 09:53:14');
INSERT INTO `user` VALUES (7, '屈boss++', 'quboss', '698d51a19d8a121ce581499d7b701668', '男', '13488499617', 'materialFile/admin/admin/17152425683424.gif', '2933503769@qq.com', 2, 1, NULL, 0, 'admin', '2024-05-09 16:16:09');
INSERT INTO `user` VALUES (8, 'zhaojunqi', 'zhaojunqizuichou', '698d51a19d8a121ce581499d7b701668', '男', '12312312312', 'materialFile/defaultphoto/2.gif', '238947238@qq.com', 2, 1, NULL, 0, 'zhaojunqizuichou', '2024-05-30 22:48:29');
INSERT INTO `user` VALUES (9, '小刘', 'liu', '698d51a19d8a121ce581499d7b701668', '男', '24372898442', 'materialFile/defaultphoto/2.gif', '2432342432@qq.com', 2, 1, NULL, 100, 'liu', '2024-05-30 22:53:00');

SET FOREIGN_KEY_CHECKS = 1;
