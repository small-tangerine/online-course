/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 19/03/2022 12:10:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bills
-- ----------------------------
DROP TABLE IF EXISTS `bills`;
CREATE TABLE `bills`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `order_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程名称',
  `cost` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '消费金额',
  `pay_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式 0余额支付 1支付宝 2微信',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bills
-- ----------------------------
INSERT INTO `bills` VALUES (10, 1, '20220318647551890860752', 39, '混合开发之DSBridge实现短视频通信', 298.00, 2, '2022-03-18 05:18:14', 1);

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '用户ID',
  `course_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '课程ID',
  `price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '价格',
  `is_discount` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否折扣 1是 0否',
  `discount_price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '折扣价格',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '封面图片',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '标题',
  `created_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建人',
  `updated_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级ID',
  `title` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `display_order` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 0, '后端开发', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (2, 0, '移动端开发', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (3, 0, '计算机基础', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (4, 0, '前沿技术', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (5, 0, '前端开发', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (6, 0, '云计算&大数据', 5, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (7, 0, '运维&测试', 6, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (8, 0, '数据库', 7, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (9, 0, 'UI设计&多媒体', 8, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (10, 5, 'HTML/CSS', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (11, 5, 'JavaScript', 0, '2022-03-04 23:17:23', 1, '2022-03-09 16:29:40', 1);
INSERT INTO `category` VALUES (12, 5, 'TypeScript', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (13, 5, 'Vue.js', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (14, 5, 'React.js', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (15, 5, 'Angular', 5, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (16, 5, 'Node.js', 6, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (17, 5, 'jQuery', 7, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (18, 5, 'Sass/Less', 8, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (19, 5, 'WebApp', 9, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (20, 5, '小程序', 10, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (21, 5, '前端工具', 11, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (22, 1, 'Java', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (23, 1, 'SpringBoot', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (24, 1, 'Spring Cloud', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (25, 1, 'SSM', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (26, 1, 'Python', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (27, 1, '爬虫', 5, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (28, 1, 'Django', 6, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (29, 1, 'Flask', 7, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (30, 1, 'Tornado', 8, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (31, 1, 'Go', 9, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (32, 1, 'PHP', 10, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (33, 1, 'Swoole', 11, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (34, 1, 'C', 12, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (35, 1, 'C++', 13, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (36, 2, 'Android', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (37, 2, 'iOS', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (38, 2, 'React native', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (39, 2, 'Ionic', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (40, 2, 'Flutter', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (41, 2, 'Weex', 5, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (42, 3, '计算机网络', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (43, 3, '算法与数据结构', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (44, 3, '数学', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (45, 4, '微服务', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (46, 4, '区块链', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (47, 4, '机器学习', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (48, 4, '深度学习', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (49, 4, '计算机视觉', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (50, 4, '自然语言处理', 5, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (51, 4, '数据分析&挖掘', 6, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (52, 6, '大数据', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (53, 6, 'Hadoop', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (54, 6, 'Spark', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (55, 6, 'Hbase', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (56, 6, 'Flink', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (57, 6, 'Storm', 5, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (58, 6, '阿里云', 6, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (59, 6, 'Docker', 7, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (60, 6, 'Kubernetes', 8, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (61, 7, '运维', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (62, 7, '自动化运维', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (63, 7, '中间件', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (64, 7, 'Linux', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (65, 7, '测试', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (66, 7, '功能测试', 5, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (67, 7, '性能测试', 6, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (68, 7, '自动化测试', 7, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (69, 7, '接口测试', 8, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (70, 8, 'MySQL', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (71, 8, 'Redis', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (72, 8, 'MongoDB', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (73, 9, '动效动画', 0, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (74, 9, '设计基础', 1, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (75, 9, '设计工具', 2, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (76, 9, 'APPUI设计', 3, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (77, 9, '产品交互', 4, '2022-03-04 23:17:23', 1, '2022-03-04 23:17:19', 1);
INSERT INTO `category` VALUES (80, 0, '其他', 99, '2022-03-09 16:35:09', 1, '2022-03-09 16:35:09', 1);
INSERT INTO `category` VALUES (81, 80, '其他', 0, '2022-03-09 16:35:25', 1, '2022-03-09 16:35:25', 1);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `alias` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '别名',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `introduction` varchar(2056) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  `bg_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '背景图',
  `banner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面图',
  `tip` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程须知',
  `learn_what` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '能学到什么',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '课程类型 1免费 2付费',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `is_discount` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否折扣 1是 -否',
  `discount_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '折扣价格',
  `is_finish` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否完结 1是 0否',
  `learn_persons` int(10) NOT NULL DEFAULT 0 COMMENT '学习人数',
  `audit_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核状态 0未审核 1审核通过 2审核不通过',
  `audit_notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '审核备注',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_alias`(`alias`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '460b4e3f37bc102afb4170e553c49478', '1小时带你写出亮眼的前端简历', '简介：Hello~各位同学大家好！本课程是一门免费课，带领大家写出亮眼的前端简历。\r\n简历包含哪些内容？有哪些注意事项？专业技能和工作经验该如何表达？如何体现你的亮点？—— 这些课程中都会详细讲到。\r\n简历是面试的敲门砖。好的简历将助你快速得到 hr 和面试官的青睐，而不好的简历可能会埋没你的技术能力和经验。\r\n', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/6212fe63099c7a3305400304.png', ' 基本的前端基础知识 Html Js Css\r; 了解 Vue 或 React 框架', '简历必备的模块和内容\r;专业技能如何表达\r;项目经验如何表达（重要）\r;如何在简历中体现自己的亮点\r;写简历时的注意事项', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (2, '2a00d5b93e42b435c001ec0cba7f3426', '如何从0到1，写出一份完美的PRD文档', '简介：写好产品文档，是每个产品经理都要掌握的基本功。，但产品新人总会遇到各种问题，比如：\n不清楚文档的内容，不知如何下笔；\n文档内容不规范，逻辑混乱；\n盲目套用模板，分不清使用场景，耗时且无用。\n\n本课程主要讲解PRD的组成及写作技巧，从理论到案例，帮助产品新人实现从0到1，学会用产品思维思考问题，输出敏捷的PRD文档，快速上手日常产品工作。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/616f846d0953660705400304.png', '掌握原型工具，如Axure；产品小白；', '系统掌握规范的文档写作方法，提高日常工作效率；;学会用产品思维思考，结合案例拆解文档；;巩固并提升原型工具的使用；;了解产品开发流程；', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (3, 'feac552385073649c60d375aa7fc2576', '玩转组件库搭建全流程', '简介：Hello~各位同学大家好～本课程是一个免费的实战项目，带大家从零实现一个Vue的组件库。\n组件库是软件开源协同的一个典型。利用文档完善，代码健全的高质量组件库，前端工程师们可以快速搭建UI，从而大大提升工作效率，节约人力成本。\n\n', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/618b31b7098858d305400304.png', '由于本门课程算是大家学习Vue路上的一个进阶，所以在开始前，希望同学们能掌握：; 基本的前端基础知识 Html Js Css; 基本的 Vue 知识;', '在课程中你能学到什么？;	组件的设计与实现。;	组件库的搭建，打包及发布。;	组件文档的编写，以及组件库文档站点的搭建与发布。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (4, 'd5db97b8b2fcf4ec87d79d7c3a06cdf7', 'Swagger接口文档神器', '简介：本课程主要讲解Swagger的在线接口文档自动生成和接口功能测试。\n课程遵循由点到面的整体思路进行讲解，让读者学习后顺利上手并在日常工作中进行实践。\n课程围绕Swagger是什么、Swagger实战、Swagger扩展方面进行讲解，理论和实践相结合。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/613af77b097382e705400304.png', '学习本课之前，需要大家对Java开发有一定的基础，对基于Sprinboot2框架开发有一定的经验，这样学习本课程会很容易上手。', '了解Swagger是什么，在实际项目中解决了哪些问题。;掌握Swagger中提供的注解。;如何搭建Swagger。;如何将Springboot与Swagger进行整合。;掌握Swagger的权限管控。;掌握Swagger多环境使用方法。;', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (5, '34614bfdbc7f85b11b8bf4a36d5c3217', 'Vite零基础快速入门', '简介：从零开始手敲代码，带你一步步了解Vite，Vite 初识，了解基本概念以及和其他常用打包工具相比，具有哪些优劣势；通过 Vite 带你了解整个前端打包构建体系，让你能够使用 Vite 搭建生产可用项目，完成开发、打包、部署、上线全流程。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/61304ac209236f2a05400304.png', ' 基本的前端基础知识 Html Js Css; 基本的 React 知识', ' 了解社区常用的打包工具都有哪些; 能够使用 Vite 搭建生产可用项目; 能够自己开发 Vite 插件; 对 Vite 实现原理有基本了解，能够自己实现一个 Mini Vite', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (6, '0b19644f9b0ecccb427a80f84c0c3c99', 'SpringBoot 2.× 手把手零基础入门与进阶', '简介：Java开发者必学前菜，微服务基础框架，基于最新版2.x从零讲解SpringBoot，从入门到进阶，再到中间件整合， 涵盖主流应用各项技术点。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/612c4b7809e2ec9a05400304.png', '课前技术储备：;;熟悉Maven、Java基Spring、Mybatis、Mysql/Mariadb、Linux基本命令', 'Springboot入门；;基本构建项目配置 ;核心功能 ;数据层操作 ;模板静态化 ;运维监控 ', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (7, '0a6c3c262e86339fa97d759aeb0bd4f7', '自然语言处理（NLP）文本分类实战', '简介：本此课程主要讲解自然语言处理中的文本分类任务，先带着大家了解文本表征方法、词向量技术、激活函数的使用、然后对英文文本分类、中文文本分类和不同分类任务（二分类、多分类和多标签分类）进行实战讲解、最后讲解模型评估方法，并且进行知识点扩展。带领大家系统的了解一个完整的文本分类任务所需具备的基础技能。从前到后，帮助大家熟悉文本分类项目的流程及工作内容。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/60f15f2d09e2415800000000.png', '建议大家对 Python、Pytorch、Numpy、基本的深度学习概念有了解再来进修，学习效果更佳', '文本表征的基础知识;词向量训练和实用方法;中英文文本分类的区别;二分多分类和多标签分类的区别;不平衡样本的评估方法;前沿知识点的扩展', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (8, '61890bbe456159d0a42297698fa4c584', '2021Android从零入门到实战(Kotlin版)', '简介：课程从零完整的讲解了google力推且备受千万开发者喜爱的的Kotlin语言。并以此为基础，以通俗易懂，难易循序渐进的方式、小白的视角讲解了AndroidUI、Android网络编程、Android四大组件等必知必会的技能，每章标配一节案例实战，融会贯通本章所学。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/60d94088094f25c900000000.png', '课程从零完整的讲解了Google力推且备受千万开发者喜爱的的Kotlin语言。并以此为基础，以通俗易懂，难易循序渐进的方小白的视角讲解了Androidui、Android网络编Android四大组件等必知必会的技能，每章标配一节案例实战，融会贯通本章所学。', 'Android环境搭建，开发语言Kotlin、Androidui开发的初中高级控Android中的网络编Android 四大组类似慕课App的开发实Android进阶中高级的学习路线。\r;', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (9, 'f91f0ccad06461f7b8923575773c5e9b', '趣味 C++ 进阶', '简介：本课程是《趣味 C++ 入门》的后续进阶课程，在学习过 C++ 的基础之后，你一定十分渴望学习 C++ 更多的高级知识。本课程就满足你的求知心与好奇心，带你探索更多更有趣的 C++ 知识。本课程将为你介绍 C++ 语言的高级部分，包括计算机中的数据原理、指针的进阶、面向对象编程、内存管理技巧等，并运用所学知识带领大家进行项目实战，实现五子棋游戏的开发。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/60cc4b0a09ea152600000000.png', '你需要具备基础的 C++ 语法知识，在学习本课程之前，建议先学习《趣味 C++ 入门》，快速认识 C++，熟悉 C++ 基本语法，更加快速入手进阶课程！', '在本门课程中，你将学习到：计算机存储数据的原指针的进面向对象编内存管理技巧等 C++ 高级语法。在课程的最后，将带领大家使用 C++ 编写一个五子棋游戏，通过实践，加深理解，巩固学习成果。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (10, '20e5dd897baa9c11405f3ea41b6ea37d', '墨刀快速入门到精通', '简介：本课程从0开始，通过操作结合案例方式，帮助学生快速入手墨刀并熟悉掌握墨刀的交互效果制作，适合产品新手、小白基础入门。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/60c1b47109e070d905400304.png', '本课程结合电商项目原型交互设计，为您详细系统讲解墨刀工具的使用方法;;;', ' ;全面系统掌握墨刀工具，学会通过墨刀原生组件设计产品低保高保真原型，通过状事件设置完成原型交互效果制作，墨刀项目的分享与发布；学会通过墨刀工作流工具设计页面交互图。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (11, 'b588da32214094540725a85131e9c35d', '探秘 MySQL 多版本并发控制原理', '简介：在本次课程种，不仅会带领大家，认识MySQL数据库隔离级别、脏读、不可重复读、幻读等基本概念；还会结合 sql 模拟并发事务，帮助大家体会不同隔离级别下，数据库中事务之间的隔离性；通过深入分析，以及常见面试题梳理，帮助大家对MySQL数据库多版本并发控制原理有更深入得认识与理解。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/60b6144609a8bb6505400304.png', '如果同学们熟悉Inndb索引结构；熟悉Mysql事务隔离级别，对于不同隔离级别之间的区别和使用场景有一定的认识；对事务的本质有一定理解，学习效果更佳！', 'Mysql数据库中常说的脏不可重复幻读到底是什么意思，我们应该怎么选择数据库隔离级别。;Mysql是如何实现的多版本并发控制;索锁的使用技巧和应用场景;Spring事务传播机制和Mysql数据库隔离级别是什么关系？ ;Mysql常见面试题 ', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (12, 'e729125ed6500196a470eefe4cd73a3f', '人工智能--语音入门 ', '简介：本此课程主要是讲解语音的基础知识，语音的特征工程，并会基于前面的学习知识，带领大家完成一个语音相关的项目，帮助同学们熟悉开展语音相关项目的流程及工作内容，轻松步入语音识别得大门~~ 【另，课程中涉及数据源参见 链接：https://pan.baidu.com/s/1BCU9PLwR0qssbq7lLkl_SA  提取码：vcsl】', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/608a96d90975fb8d05400304.png', '如果同学们有一定的 Python 语法基础，懂一些 Pytorch 的基础应用，了解基本的深度学习概念，学习本课将会更加得心应手。', '语音的基本原理和基础知识;语音的基本代软件操作;语音特征工程;语音相关基本神经网络组件;语音算法模型的构建;语音项目的开展流程', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (13, 'c70a675e30cdb7d2641ead00d264be57', '2小时极速入门 TypeScript', '简介：\nTypeScript 为什么被视为“前端的未来”？\n\nStackoverflow 2020年度开发者调查研究显示，Typescript在最受开发者喜爱语言排行榜跃居第二名，依靠着微软和Google的背书，Typescript社区逐渐壮大，日趋完善，为越来越多前端开发者提供服务。\n\n\n为什么是 Typescript 而非 JavaScript ？\n\n—更可靠：类型的定义和编译器的引入，可以避免JavaScript大多数runtime错误，更可靠，易维护；\n—更清晰：显式类型声明提升代码可读性，代码校验可以全部交给编译器负责；\n—更广泛：TypeScript是JavaScript的超集，可以在TypeScript代码中混合使用任何JavaScript库和代码。\n\n\n如果你是前端开发工作者，或希望从事前端工作，那就一定不要错过这门课程。\n\n在本课程中，主讲老师会采用理论与代码结合的讲解方式，助你2小时极速入门Typescript，短时间内形成详尽的知识网络。\n\n\n课程中你可以学到什么？\n—研究TypeScript编译流程、工作环境\n—学习TypeScript 12个基本类型\n—深入理解TypeScript 重要高级类型\n—掌握 TypeScript 面对对象的特性\n—学习TypeScript 的 Generics 泛型\n', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/607fc1a4097d454805400304.png', '学习中需要用到这些工具：;—Visual Studio Code 代码编辑器;—Tsc Typescript编译器;—Nodejs Sdk;;你需要有这些基础：;—可以使用原生Javascript，有Es6的基础知识更佳', '学习Typescript基础知识;了解Typescriptg工作流程以及编译原理;掌握Typescript开发配置以及各种常用工具;掌握前端静态类型思想 ;深入了解前端面对对象概念 ', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (14, 'f9448c4d8a5ec7e529c4af81bc804263', '趣味 C++ 入门', '简介：C++ 是一门偏向底层的编程语言，应用广，高性能，小到嵌入式，大到分布式服务器，随处可以见到 C++ 的身影。同时很多人对 C++ 又有另外一种认知，复杂、难学，令人望而生畏，事实真的如此吗？你是否深入思考过到底什么样的学习方式才最适合 C++？本课程将带你开启 C++ 的趣味学习之旅，解决难学难理解的问题。本课程将为你介绍 C++ 语言的基础部分，包括环境搭建，C++的基础语法等。除了这些，本课程还将介绍一些计算机的底层原理，例如，计算机中内存的布局、内存管理方式等。对于一些比较抽象的概念，课程中采用动画的形式，形象的展示这些晦涩的内容，揭开 C++ 的神秘面纱。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/606c41a60914530f05400304.png', '本门课程使用 Windows 平台下的 Visual Studio 作为开发工具，所以你要准备一台 Windows 系统的电脑。当然，本门课程中介绍的所有代码，并不是只能在 Windows 下运行，你同样可以尝试使用 Mac Os X 或者 Linux 或者其他的平台进行学习，我们也鼓励你进行这样的尝试。', '掌握在 Windows 下搭建 C++ 开发环境，了解 C++ 的基础概念，例如变量，指针等。了解计算机的一些基础知识，内存布局等。除此之外，本课程还将介绍一些编程小技巧，以及编程的代码规范。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (15, 'c3b03faf55da2924b18a0296a5385c7b', '直面JavaScript中的30个疑难杂症', '简介：该课程是对JavaScript的深入理解，主要涵盖了面试中常见的面试题，一些比较难以理解的知识点以及开发中常见的问题，帮助我们更好的理解和掌握这门编程语言。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/6020a85e09228d8905400304.png', '有一定的前端Javascript基础的用户\r;想对Javascript有更深层次的了解，或者是对常见Javascript面试题难以理解的用户\r;技术储备：Javascript、Html、Css', 'Javascript中比较重要的知识点，例如数据类型检测；面试中常见的闭作用域和作用域执行上下文等等；还有Javascript中的原原型链，面向对象问题，以及实战开发的运用。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (16, 'b988d18b92419038f7cf1bf132aaa59a', '领略Rust之美，挑战双高语言', '简介：Rust连续五年（2016~2020）在Stack Overflow开发者调查的“最受喜爱编程语言”评选项目中蝉联桂冠，而Rust又以高难度，高门槛，学习曲线陡峭和概念抽象而驰名。本课从Rust语言基础出发，关注Rust语言本身特点，使用最简程序，排除使用场景少、概念理解困难的分支内容干扰，力求在最短的时间帮助小伙伴们上手Rust开发，领略Rust编程之美。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/601112b30995261a05400304.png', '需要小伙伴们对初级的编程知识有一定的了解（最好具备C或其它语言基础）', 'Rust开发环境配置;Rust中的变量与可变性，不可变变量的优势是什么？;Rust 的基础语法;Rust中如何使用结构体？;泛型类型基础;初探Rust的内存安全模型;Rust翻转二叉树思想和基础实践;', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (17, 'ac0a2c9cf3ae53bfca07bdca188f5476', 'vue3.0实现todolist', '简介：vue3.0已经发布正式版本,必将成为未来的前端趋势和必备技能,本课程会通过一个小而全的todolist案例,带同学们全方位的入门vue3.0,让同学们能够把握先机,快人一步。\r\n', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/600ebd8b08a2013605400304.jpg', '需要有前端基础，会Es6\r;需要熟悉并会用Vuex\r;适用想要学习Vue0的同学', 'Vue0环境搭建\r;Vue0核心知识\r;Todolist实战案例', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (18, '2b5003e763cfef6a016fcecf25c1fa15', '高性能 FastAPI 框架入门精讲', '简介：体验新一代高性能 Python Web 框架，本课程将从 Hellow World 开始引导同学们学习 FastAPI 框架的所有知识点。从框架特性一览，到 ASGI 服务介绍，以全球新冠病毒感染数据查询为功能场景，依次讲解 FastAPI 的 API 交互文档使用，如何使用 Pydantic 定义和规范数据格式、类型，各种请求参数和验证，Jinja2 模板渲染和 Static 静态文件配置，FastAPI 的响应处理和配置，通过代码示例讲解依赖注入系统的所有知识，数据库配置与 SQLAlchemy 的使用，大型工程应该如何设计目录结构。\n\n框架的安全、认证、授权，中间件开发，跨域资源共享的实现，后台任务和测试用例的编写。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/600a8570097f897e05400304.png', '任何想学习Python开发的同学，尤其是需要高效率完成高并高性能项目的同学都可以学习', 'Fastapi 框架特性及性能优势;如何定义各种请求参数和验证;模板渲染和静态文件配置;Fastapi 的表单数据处理;全面学习 Fastapi 依赖注入系统;Fastapi 的安认证和授权;大型工程应该如何目录结构设计;Fastapi 的中间件开发方法和规范;跨域资源共享的原理和实现方式', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (19, '3aea9cfa485028c8ab7899a85fb9ea25', '数据中台之数据汇聚整合，消除数据孤岛', '简介：本课程会依次向大家介绍中台的诞生背景、数据中台的架构以及如何进行合理得技术选型，并结合一个通用的关系型数据库同步至HDFS的工具套件实现，带大家拓展了基于Spark的多源异构数据同步工具的设计思路。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5ff529550925a7bd05400304.png', '了解Hadoop的基本使用，了解Spark的简单操作', '中台的诞生背景;数据中台的架构;数据中台的技术选型;数据同步套件的架构;通用的关系型数据同步至Hdfs的工具实现;如何基于插件的机制进行功能的扩展;基于Spark的多源异构数据同步工具的设计思路', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (20, 'f0a01e6bd1e3df50fc1a0c1434a8abe3', '元旦贺卡', '简介：本课程结合cavas、动画、JS完成一个新年祝福的H5微场景。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5feddf40097ffb6505400304.png', '具备Html5+Css3;具备Javascript;了解Canvas绘图基础', '了解H5微场景制作过程。;深入理解Canvas高级特性：动粒像素级操作。;学习到如何合理的组织代码和异步代码。;了解如何制作烟花甩尾和爆炸算法等。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (21, '2d5dc1541d747e9f2144d6d6b5ad71eb', 'MyBatis-Plus + SpringBoot实现简单权限管理', '简介：通过Spring Boot与MyBatis-Plus的整合来完成权限系统实现，重点是通过案例学习更多MyBatis-Plus应用技巧！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe851f509ad68eb05400306.png', '需要对Spring Boot和Mybatis-Plus有初步的了解\r;建议学习《Mybatis-Plus入门》课程，链接：Https://Www.Imooc.Com/Learn/1130\r;建议学习《Mybatis-Plus进阶》课程，链接：Https://Www.Imooc.Com/Learn/1171', '使用Spring Boot整合Mybatis-Plus进行开发\r;Mybatis-Plus代码生成器的使用\r;Mybatis-Plus单表及多表增删改查操作的运用\r;Mybatis-Plus自动填充的实战应用\r;', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (22, '74d91afa64f9ec121d789e7fb061d928', 'Phaser从0到1实战微信2D小游戏【钢琴方块】', '简介：本课程将带领大家结合一个关于钢琴的微信小游戏，认识2D游戏引擎Phaser的核心应用。从H5环境搭建到核心API的学习；从微信环境的适配，到能实战开发一个关于微信小游戏。从基础入门到实战开发，将会逐步带你揭开Phaser的神秘面纱，体验他的强大之美。在学习技术的同时，老师还会分享一些钢琴乐理知识，希望能和大家一起快乐敲代码！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fcf2b970987c8a905400304.png', '熟悉Es6的基础语法', 'Phaser的基础概念与原理;Phaser如何适配微信小游戏;Phaser如何与Webpack配合使用;Phaser中列表与物理引擎的使用;如何使用Pahser从0到1构建一个小游戏产品;如何将钢琴乐理知识与技术的结合', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (23, '4c094284daaaa50d33d31ff4752c99d3', 'Linux速成班', '简介：对Linux感兴趣，对Linux运维感兴趣，想入门Linux运维的人员， 为找工作增加经验而学习', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe443100001f2a805400304.jpg', '只需要有电脑的基本操作即可学习。', '一个从Linux完全不懂的小白，到熟悉Linux常用操作，命令，文件结构的合格使用者。满足你工作中的基本使用需求。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (24, '25f6eb63d48d98ce64146f97b4bc7b23', 'Ajax实战案例之列表渲染', '简介：本课程通过一个简单的例子，由浅入深，循序渐进的介绍了Ajax的相关概念、原理、实现方式和应用方法，了解Ajax开发模式与动态网站是如何制作的。感受Ajax对传统交互带来的优势，学习如何提升用户体验并完成复杂应用交互。课程涉及前端、前端模板、express、JSON、axios库等相关内容，是非常适合Ajax学习的入门课程。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe443100001f91605400304.jpg', '必备知识：Javascript基Html和Css基础', ' 了解全栈开发模式与动态网站如何制作; 深度理解Ajax的各种交互方式及应用场景; 学习Axios框架的使用，高效开发; Ajax实战案例，真实后端环境', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (25, '304dc4e37b3a9a16e45d22f7dd35cd55', '吃透面向对象【Golang实现版】', '简介：面向对象是高级编程语言的最基础、最核心、最关键的理论基石。但是你真的吃透了面向对象么？', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fb503530967d2ac05400304.png', '基本的Go语法基础;', '面向对象官方定义与通俗理解;面向对象三大特征及其实践;面向对象设计思想剖析;面向对象综合运用', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (26, '907f5e043c32cd691f66f1a7453d7fc3', '六个案例学会响应式布局', '简介：主要学习媒体查询、flex弹性布局及伸缩比例的计算，通过rem作为度量单位进行弹性布局的方法', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe44310000167df05400304.jpg', '有前端基础的用户;想要深入学习响应式布局的用户', '掌握利用媒体查询并在移动端设计中使用;掌握Flex及其自定义伸缩比例的方法;Px,%,Em,Rem的区别和使用;掌握常见布局的设计方法', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (27, '08e3976ccf13b653265e829f8ce3b66d', '【首发】零基础快速上手HarmonyOS（鸿蒙）开发', '简介：2020年9月10日，华为在HDC开发者大会正式上发布鸿蒙2.0，这个属于国人自己的系统，对开发者有何意义？让我们跟着CrazyCodeBoy老师一起探究来自底层的轮子HarmonyOS。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4431000014bc405400304.jpg', '了解Java或Js基础，具备一定自学能力', 'Harmonyos架构;Harmonyos开发环境搭建及工具使用技巧;Harmonyos开发基础（生命周页面跳转传参等）;基于Harmonyos Layout系统和Ui组件构建界面;Harmonyos多线程与并发技术;Harmonyos网络操作及网络层框架封装;实战Harmonyos应用开发', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (28, '7e8037253746c8ef3fd1e4e25fe2361b', 'JAVA 函数式编程', '简介：本课程以 Java 11 为编译环境，讲解了 Java 对函数式编程支持，以及用实战小例子演示如何使用函数式简洁优雅的直击问题核心逻辑。另，老师新作 《Spring Security+OAuth2 精讲 多场景打造企业级认证与授权》https://coding.imooc.com/class/455.html 也上线啦，课程中将结合前后端分离的权限管理应用，基于从单体到微服务的演进，精讲主流安全框架 Spring Security5.x 的核心技术，一站式覆盖目前企业主流认证授权的方方面面，感兴趣的同学，可以关注一下，欢迎撒花拍砖~~', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe443100001f6af05400304.jpg', '熟悉Java基础语法及面向对象编程思想', '利用常见操作福简化逻辑;函数式编程的特点和Java中的支持;从面向对象到函数式编程的认知改变', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (29, 'ab27c6fa1faf341f7d26ca5b72321aaf', 'MySQL8.0零基础入门之从青铜到钻石', '简介：\n想做一名合格的后端工程师，数据处理能力必不可少，无论使用哪种编程语言，都要以扎实的数据库知识为基础；甚至到如今，很多大厂在招聘前端工程师时，也会对数据处理能做出要求，可见，数据库学习已成为 IT圈的主流。\n\n小白程序员入行第一课，5小时快速入门MySQL！\n\n课程紧随技术发展，以当前主流的MySQL8.0展开学习，从最基础的数据库概念讲起，内容囊括了操作数据库的SQL语句语法、数据库的安装与卸载等。重点知识与操作方法全面覆盖，帮助零基础的同学顺利入门上手。\n\n课程中讲师采用Markdown形式的知识笔记，便于同学么理清只是脉络，加深知识点记忆，课后复习也更加方便。\n\n\n课程中你可以学到哪些知识？\n—MySQL数据库的安装卸载\n—SQL基础语法\n—DDL语句--对数据库及表的增删改查\n—DML语句--对数据的增删改\n—DQL语句--对数据的查询\n—数据库表的约束\n\n通过5小时的学习，你将满足后端项目开发对MySQL数据库的基本需求，对应岗位面试中的基础MySQL问题也可应对自如。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe443100001d27905400304.jpg', '学习中需要用到这些工具：;—Mysql0  ;—Navicat连接工具;—Dos窗口;;你需要有这些基础：;—会使用Dos窗口', '什么是数据库;数据库的安装与卸载;Sql基础语法;Ddl语句--对数据库及表的增删改查;Dml语句--对数据的增删改;Dql语句--对数据的查询;数据库表的约束', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (30, 'ba8044f910edf1e58b3b205307fc75d6', 'Maven项目依赖管理', '简介：本套课程主要学习Maven的使用方式，从理念到实践、基础到应用，深入浅出的讲解配合贯穿全程的练习，让你深入掌握基于Maven管理Java项目的方式', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe443100001fef005400304.jpg', '了解Java基础语法;创建并开发过Javase项目', 'Maven环境的构建;Maven项目的创建;Maven中远程仓私有仓库和本地仓库的管理和使用;Maven的常见操作命令和生命周期;项目依赖的作用范围设置和管理;项目继承关系实现依赖的复用;聚合项目完成多项目的统一管理;解决依赖直接冲突和传递冲突', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (31, 'ad63c02f1fca3158f6107f467eb17ca9', '全方位入门git', '简介：git现在已经成为面试和工作中必备必会的一项技能，本次课程会通过多个维度来全方位入门git，让同学们能够在面试和工作中游刃有余，没有阻碍。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe443100001717705400304.jpg', '本次课程适用于:;Git零基础的同学;有Git基础，但是想要系统学习的同学', ' 在不同操作系统安装Git; Github的基本使用及拓展; Git常用操作命令; 单人使用Git的常见场景及问题解决; 多人协作使用Git的常见场景及问题解决', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (32, '78676d013d0a899a869256a387b2dd6b', 'Tensorflow.js 实现垃圾分类', '简介：本课程以 Tensorflow.js 作为主要框架，覆盖了实现垃圾分类 App 所需要的样本准备、模型构建、模型训练、模型预测、H5 应用编写等全链路知识的讲解。带你在快速完成应用的同时，进一步加强对机器学习的认识！另，对机器学习以及Tensorflow.js感兴趣的同学，可以关注一下老师的实战《JavaScript玩转机器学习 打造你人生中的第一个AI项目》https://coding.imooc.com/class/408.html 专为前端人打造的人工智能课程！！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4431000012a0905400304.jpg', '需要大家有一定的 Javascript 基础，如熟悉机器学习更佳', '用 Node.Js 版 Tensorflow 训练垃圾分类模型;用 Tensorflow.Js + React 编写垃圾分类 H5 App;认识迁移学习', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (33, '1d42a10c7a43a77662cab976c1d27493', 'Python数据分析挖掘实战', '简介：掌握Python数据读取、预处理、分析、挖掘与模型搭建的全流程必备知识。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4430f00014b1105400304.jpg', '如果您掌握Python基础知统计学基础知识，那么对本门课程学习会有很大帮助，让您学起来得心应手，快速进入数据分析世界。', '数据科学库Pandas、Numpy，数据挖掘与科学算法库Scikit-Learn，数据可视化库Matplotlib、Seaborn、Pyecharts等在实际业务中的应用。', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (34, 'e3a486ba48d96bac40e8ce7342aa2021', 'Django REST framework前后端分离框架实践', '简介：API接口开发无需费时费力，本课程将从零开始引导同学们快速开发自己的RESTful API接口，从Django项目环境搭建、API接口生成数据、Postman接口测试到DRF认证方式的讲解，通过一个典型的课程信息接口（含增删改查），给同学们讲解完DRF中的序列化（serializers）、视图集（viewsets）、路由（routers）、认证（authentication）、权限（permission），为将来前后端分离项目的开发打下基础。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4431000019f8805400304.jpg', '熟练Python语法;有Django项目基础', '深入理解Restful Api;Django Rest Framework组件介绍;Drf中的序列化Serializers;Django的Views开发Api接口;Drf的多种视图Api_View/Apiview…;Django的Urls与Drf的Routers;使用Drf的Api接口文档;Api测试神器Postman;Drf的认证和权限', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (35, '8d4943c196a8ec68beb16904a1dd37bf', '数据挖掘中的两大经典算法：K-Means聚类算法和决策树算法', '简介：本课程主要讲解数据挖掘中的两大经典算法：K-Means聚类和决策树算法，每个算法会通过理论讲解结合编程实战的形式让大家不仅了解算法的本质还学会算法的应用。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4430f00010eeb05400304.jpg', '熟悉基本Python语法;', '数据挖掘领域两大经典算法;良好的编程习惯和计算思维;看待和分析问题的全新视觉', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (36, '0f353d5d3ba93b50d5d621f247a77b2c', 'Python3 进阶教程（新版）', '简介：本课程详细介绍Python强大的函数式编程和面向对象编程，以及Python高级程序设计的方法。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/61af14e40984c6f605400304.png', '本课程是Python入门的后续课程\r;掌握Python编程的基础知识\r;掌握Python函数的编写\r;对面向对象编程有所了解更佳', '什么是函数式编程\r;Python的函数式编程特点\r;Python的模块\r;Python面向对象编程\r;Python强大的定制类', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (37, '3b8e4b133bb6c6236f0a39b715ae7ac8', '基于WebAR实现3D任务书桌上跳舞', '简介：零基础入门3D模型动画基本概念，实现3D模型动画在Web页面的加载和播放，生成简单的VR和Web AR应用。\n', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4430f00014a7905400304.jpg', '轻松掌握3D领域最重要的概念，用通俗直观的描述，让小伙伴轻松理解3D模型动画基本概念，实现3D模型动画在Web页面的加载和播放，生成简单的Vr和Web Ar应用等。;', ' 基于Web技术开发简单的3D模型Vr和Ar应用; 计算机3D模型和动画的基本概念; Threejs库基础; Aframe库基础; Web应用开发基础;', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (38, '399feaf9f78fe961c7cb78216230bf8b', 'AI小白入学&求职指南', '简介：本课程主要面向刚毕业高中生、大学生、硕士生等对AI行业充满向往的同学们。课程围绕着“大学小白入学&求职”这个主题，从AI行业的发展现状，职业规划等不同角度，以老司机的视角，帮助大家快速梳理并明确，如何填报志愿、如果进行大学学习规划、如果成为一名合格的算法工程师、读硕士期间如何有效地阅读论文和写作、工作以后如何有效提高个人能力等不同问题，帮助大家提前做好职业规划，助力快速成长，早日成为一名优秀的算法工程师！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4430f0001f2f205400304.jpg', ' 从自身的实际经历来讨论一些行业小白经常会遇到的坑; 会介绍从学习规划到职业规划不同阶段面临的多个问题; 针对比较接地气的问题，比如：如何发表Sci/Ei文如何进行文献调研等进行经验分享', '', 1, 0.00, 0, 0.00, 0, 0, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (39, '72466ed1e790707985c2342894a1ace2', '混合开发之DSBridge实现短视频通信', '简介：混合开发是趋势，本次通过短视频的案例，了解并掌握混合开发流行的数据通信框架DS-bridge。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5fe4430f0001b99b05400304.jpg', '学习此课程需要有Android基础，并对前端技术Html，Js等有简单了解。以及想了解或对混合开发有需求的人群。', '首先对比混合开发是否使用框架的优势；然后通过Ds-Bridge框架+Html、Js代码+Android Native实现视频通信', 2, 298.00, 0, 0.00, 1, 1, 1, '', '2022-03-04 10:10:08', 1, '2022-03-18 04:36:26', 1);
INSERT INTO `course` VALUES (40, '1ada46557a2d996e7bb5eba91fe03223', 'Python3 入门教程（新版）', '简介：Python如何“火”到出圈？\r\n\r\n对比其他程序语言，Python近些年的火爆程度，已经不止于程序员的圈子了。\r\n\r\nPython语法简洁高效，入门门槛低，且应用广泛。Web、爬虫、人工智能大数据、机器学习、测试运维、数据分析等工作，都需要Python基础。\r\n\r\nPython相比于其他编程语言，更接近自然语言，对小白学员十分友好，是转行程序员的上佳选择。\r\n\r\n\r\n本课程为Python入门阶段的学员准备，零基础学起来也完全没有压力。课程从Python环境搭建讲起，由浅入深，带你学习以函数为基础编写完整Python代码、Python的基本数据类型以及list和dict的操作，灵活使用流程控制语句。\r\n\r\n在课程设计上，讲师选择了学练结合的教学方法，慕课网在线编辑器支持知识点随学随练，加深课堂记忆，巩固学习成果，提升入门学习效率。\r\n\r\n通过本课程的学习，你可以了解Python基本语法，具备进一步项目实践的基础语法能力，完成Python相关的数据分析统计，或做一个爬虫项目都不在话下！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/61af150009cfe0a705400304.png', '如果您了解程序设计的基本概念，会简单使用命令行，了解中学数学函数的概念，那么对课程学习会有很大的帮助，让您学起来得心应手，快速进入Python世界。', '通过本课程的学习，您将学会搭建基本的Python开发环境，以函数为基础编写完整的Python代码，熟练掌握Python的基本数据类型以及List和Dict的操作，灵活使用流程控制语句。', 1, 0.00, 0, 0.00, 0, 1, 1, '', '2022-03-04 10:10:08', 1, '2022-03-18 01:35:24', 1);

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类二级ID',
  `title` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类标题',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_category_course`(`category_id`, `course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程分类关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_category
-- ----------------------------
INSERT INTO `course_category` VALUES (8, 26, 'Python', 40, '2022-03-18 01:34:18', 1);
INSERT INTO `course_category` VALUES (9, 36, 'Android', 39, '2022-03-18 04:36:09', 1);
INSERT INTO `course_category` VALUES (10, 38, 'React native', 39, '2022-03-18 04:36:09', 1);

-- ----------------------------
-- Table structure for course_video
-- ----------------------------
DROP TABLE IF EXISTS `course_video`;
CREATE TABLE `course_video`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '视频名称',
  `file_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件路径',
  `file_size` int(10) NOT NULL COMMENT '视频大小',
  `thumb_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面图片',
  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '播放地址',
  `video_length` int(10) NOT NULL COMMENT '视频长度',
  `display_order` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `audit_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核状态 0未审核 1审核通过 2审核不通过',
  `audit_notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '审核备注',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) NOT NULL COMMENT '创建人',
  `updated_by` int(10) NOT NULL DEFAULT 0 COMMENT '更新人',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程视频关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_video
-- ----------------------------
INSERT INTO `course_video` VALUES (10, 40, '[1.1]--Python简介', 'C:\\nginx-1.16.1\\upload\\video\\4f1edce51163eb495257a0631cf447c2.mp4', 16184766, 'http://localhost/image/default.png', 'http://localhost/video/4f1edce51163eb495257a0631cf447c2.mp4', 221, 1, 1, '', '2022-03-18 04:28:43', 1, 1, '2022-03-18 04:34:46');
INSERT INTO `course_video` VALUES (11, 39, '1-1 课程介绍', 'C:\\nginx-1.16.1\\upload\\video\\4f1edce51163eb495257a0631cf447c2.mp4', 16184766, 'http://localhost/image/default.png', 'http://localhost/video/4f1edce51163eb495257a0631cf447c2.mp4', 221, 1, 1, '', '2022-03-18 04:56:05', 1, 1, '2022-03-18 04:56:08');

-- ----------------------------
-- Table structure for home_recommend
-- ----------------------------
DROP TABLE IF EXISTS `home_recommend`;
CREATE TABLE `home_recommend`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '1实战推荐 2新上好课',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页推荐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_recommend
-- ----------------------------
INSERT INTO `home_recommend` VALUES (82, 40, 2, '2022-03-18 01:35:15', 1);
INSERT INTO `home_recommend` VALUES (83, 39, 2, '2022-03-18 04:36:09', 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `pay_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付状态 0未支付 1已完成 2已过期',
  `pay_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式 1余额支付 2支付宝 3微信',
  `cost` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '订单金额',
  `expired_at` datetime NOT NULL COMMENT '失效时间',
  `cancel_at` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `pay_at` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (15, '20220318647551890860752', 1, 1, 2, 298.00, '2022-03-18 17:18:11', NULL, '2022-03-18 05:18:14', '2022-03-18 05:18:11', 1, '2022-03-18 05:18:14', 1);

-- ----------------------------
-- Table structure for orders_detail
-- ----------------------------
DROP TABLE IF EXISTS `orders_detail`;
CREATE TABLE `orders_detail`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单ID',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '价格',
  `is_discount` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否折扣 1是 0否',
  `discount_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '折扣价格',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders_detail
-- ----------------------------
INSERT INTO `orders_detail` VALUES (17, 15, 39, 'http://localhost/image/20220307/5fe4430f0001b99b05400304.jpg', '混合开发之DSBridge实现短视频通信', 298.00, 0, 0.00, '2022-03-18 05:18:11', 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `parent_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级编号',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `type_id` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '菜单类型 1目录 2菜单 3按钮',
  `display_order` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT '菜单排序',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '前端路由',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路由主键地址',
  `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路由重定向地址',
  `en_title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单英文名称',
  `icon` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `is_hidden` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否隐藏 1是 0否',
  `permission_tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限标志',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单描述',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人ID',
  `updated_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 247 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, '系统管理', 1, 1, '/system', 'Layout', '/system/user-list', 'system', 'system', 0, '', '', '2022-03-08 09:38:43', 1, '2022-03-16 18:30:52', 1);
INSERT INTO `permission` VALUES (2, 0, '课程管理', 1, 2, '/course', 'Layout', '/course/course-list', 'course', 'article', 0, '', '', '2022-03-08 09:38:43', 1, '2022-03-08 09:40:22', 1);
INSERT INTO `permission` VALUES (3, 1, '用户列表', 2, 1, '/system/user-list', 'system/user/index', '', 'userList', 'list', 0, '', '', '2022-03-08 10:17:31', 1, '2022-03-08 11:22:15', 1);
INSERT INTO `permission` VALUES (4, 1, '教师信息', 2, 2, '/system/user/teacher', 'system/user/teacher/index', '', 'teacher', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-08 10:54:01', 1);
INSERT INTO `permission` VALUES (5, 2, '课程列表', 2, 0, '/course/course-list', 'course/course-list/index', '', 'courseList', 'list', 0, '', '', '2022-03-08 10:17:31', 1, '2022-03-12 10:25:40', 1);
INSERT INTO `permission` VALUES (6, 2, '课程分类', 2, 4, '/course/category-list', 'course/category-list/index', '', 'categoryList', 'tree-table', 0, '', '', '2022-03-08 10:17:31', 1, '2022-03-17 10:30:22', 1);
INSERT INTO `permission` VALUES (7, 0, '在线课程', 1, 6, 'http://localhost', 'Layout', '', 'platformLink', 'link', 0, '', '', '2022-03-08 13:16:32', 1, '2022-03-08 13:25:10', 1);
INSERT INTO `permission` VALUES (8, 1, '角色管理', 2, 2, '/system/role', 'system/role/role-list/index', '', 'role', 'role', 0, '', '', '2022-03-08 13:30:44', 1, '2022-03-08 13:30:53', 1);
INSERT INTO `permission` VALUES (9, 8, '查询角色列表', 3, 1, '/role/list', '', '', 'roleQuery', '', 0, 'sys:role:query', '', '2022-03-08 14:01:34', 1, '2022-03-08 14:02:15', 1);
INSERT INTO `permission` VALUES (10, 8, '修改角色信息', 3, 2, '/role/update', '', '', 'roleEdit', '', 0, 'sys:role:update', '', '2022-03-08 14:33:30', 1, '2022-03-08 14:33:28', 1);
INSERT INTO `permission` VALUES (11, 8, '添加角色信息', 3, 3, '/role/create', '', '', 'roleAdd', '', 0, 'sys:role:create', '', '2022-03-08 14:34:53', 1, '2022-03-08 14:34:56', 1);
INSERT INTO `permission` VALUES (12, 8, '删除角色信息', 3, 4, '/role/delete', '', '', 'roleDel', '', 0, 'sys:role:delete', '', '2022-03-08 14:50:56', 1, '2022-03-08 14:50:53', 1);
INSERT INTO `permission` VALUES (13, 8, '分配角色权限', 3, 5, '/role/permission-scope', '', '', 'rolePermission', '', 0, 'sys:role:permission', '1', '2022-03-08 14:52:25', 1, '2022-03-08 14:52:28', 1);
INSERT INTO `permission` VALUES (14, 8, '获取权限菜单', 3, 6, '/role/menu-list', '', '', 'roleMenu', '', 0, 'sys:role:menu:list', '1', '2022-03-08 14:54:35', 1, '2022-03-08 14:59:56', 1);
INSERT INTO `permission` VALUES (15, 3, '查询用户列表', 3, 1, '/user/list', '', '', 'userList', 'list', 0, 'sys:user:query', '', '2022-03-08 10:17:31', 1, '2022-03-09 10:59:34', 1);
INSERT INTO `permission` VALUES (16, 3, '创建用户', 3, 2, '/user/create', '', '', 'userList', 'list', 0, 'sys:user:create', '', '2022-03-08 10:17:31', 1, '2022-03-09 10:59:40', 1);
INSERT INTO `permission` VALUES (17, 3, '修改用户', 3, 3, '/user/update', '', '', 'userList', 'list', 0, 'sys:user:update', '', '2022-03-08 10:17:31', 1, '2022-03-09 10:59:44', 1);
INSERT INTO `permission` VALUES (18, 3, '用户详情', 3, 4, '/user/view', '', '', 'userList', 'list', 0, 'sys:user:view', '', '2022-03-08 10:17:31', 1, '2022-03-09 10:59:47', 1);
INSERT INTO `permission` VALUES (19, 3, '删除用户', 3, 5, '/user/delete', '', '', 'userList', 'list', 0, 'sys:user:delete', '', '2022-03-08 10:17:31', 1, '2022-03-09 10:59:51', 1);
INSERT INTO `permission` VALUES (20, 3, '分配用户角色', 3, 6, '/user/role-scope', '', '', 'userList', 'list', 0, 'sys:user:assign:role', '', '2022-03-08 10:17:31', 1, '2022-03-09 11:08:01', 1);
INSERT INTO `permission` VALUES (21, 4, '获取教师信息', 3, 7, '/user/teacher-info', '', '', 'teacherInfo', 'list', 0, 'sys:user:teacher-info', '', '2022-03-08 10:17:31', 1, '2022-03-17 10:15:34', 1);
INSERT INTO `permission` VALUES (22, 4, '修改教师信息', 3, 8, '/user/teacher-update', '', '', 'updateTeacherInfo', 'list', 0, 'sys:user:teacher:update', '', '2022-03-08 10:17:31', 1, '2022-03-17 10:15:25', 1);
INSERT INTO `permission` VALUES (24, 6, '创建分类', 3, 2, '/category/create', '', '', 'categoryList', 'list', 0, 'sys:category:create', '', '2022-03-08 10:17:31', 1, '2022-03-09 15:24:43', 1);
INSERT INTO `permission` VALUES (25, 6, '修改分类', 3, 3, '/category/update', '', '', 'categoryList', 'list', 0, 'sys:category:update', '', '2022-03-08 10:17:31', 1, '2022-03-09 15:24:48', 1);
INSERT INTO `permission` VALUES (26, 6, '删除分类', 3, 4, '/category/delete', '', '', 'categoryList', 'list', 0, 'sys:category:delete', '', '2022-03-08 10:17:31', 1, '2022-03-09 15:24:53', 1);
INSERT INTO `permission` VALUES (27, 6, '获取二级分类列表', 3, 5, '/category/level-list', '', '', 'categoryList', 'list', 0, 'sys:category:level:query', '', '2022-03-08 10:17:31', 1, '2022-03-09 15:25:01', 1);
INSERT INTO `permission` VALUES (28, 6, '获取分类列表', 3, 5, '/category/list', '', '', 'categoryList', 'list', 0, 'sys:category:query', '', '2022-03-08 10:17:31', 1, '2022-03-09 15:25:01', 1);
INSERT INTO `permission` VALUES (29, 0, '订单管理', 1, 3, '/order', 'Layout', '/order/order-list', 'order', 'shopping', 0, '', '', '2022-03-08 09:38:43', 1, '2022-03-09 18:23:29', 1);
INSERT INTO `permission` VALUES (30, 29, '订单列表', 2, 1, '/order/order-list', 'order/order-list/index', '', 'orderList', 'list', 0, '', '', '2022-03-08 10:17:31', 1, '2022-03-08 13:08:31', 1);
INSERT INTO `permission` VALUES (31, 29, '订单详情列表', 2, 2, '/order/detail-list', 'order/detail-list/index', '', 'detailList', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-10 09:07:02', 1);
INSERT INTO `permission` VALUES (33, 2, '课程详情', 2, 1, '/course/detail', 'course/detail/index', '', 'courseDetail', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-12 10:25:33', 1);
INSERT INTO `permission` VALUES (34, 2, '课程编辑', 2, 2, '/course/detail/edit', 'course/detail/edit/index', '', 'courseDetailEdit', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-12 10:24:43', 1);
INSERT INTO `permission` VALUES (35, 2, '课程添加', 2, 3, '/course/detail/add', 'course/detail/add/index', '', 'courseDetailAdd', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-12 10:24:49', 1);
INSERT INTO `permission` VALUES (36, 30, '查询订单列表', 3, 1, '/order/list', '', '', 'orderQuery', '', 0, 'sys:order:query', '', '2022-03-08 14:01:34', 1, '2022-03-12 10:54:35', 1);
INSERT INTO `permission` VALUES (39, 31, '查询订单详情列表', 3, 1, '/order/detail-list', '', '', 'orderDetail', '', 0, 'sys:order:detail:query', '', '2022-03-08 14:01:34', 1, '2022-03-08 14:02:15', 1);
INSERT INTO `permission` VALUES (40, 5, '查询课程列表', 3, 1, '/course/list', '', '', 'courseQuery', '', 0, 'sys:course:query', '', '2022-03-08 14:01:34', 1, '2022-03-08 14:02:15', 1);
INSERT INTO `permission` VALUES (41, 5, '删除课程', 3, 1, '/course/delete', '', '', 'courseDelete', '', 0, 'sys:course:delete', '', '2022-03-08 14:01:34', 1, '2022-03-08 14:02:15', 1);
INSERT INTO `permission` VALUES (42, 33, '查询课程详情', 3, 1, '/course/detail', '', '', 'courseDetail', '', 0, 'sys:course:detail:view', '', '2022-03-08 14:01:34', 1, '2022-03-08 14:02:15', 1);
INSERT INTO `permission` VALUES (43, 35, '添加课程', 3, 1, '/course/create', '', '', 'courseCreate', '', 0, 'sys:course:create', '', '2022-03-08 14:01:34', 1, '2022-03-12 10:42:53', 1);
INSERT INTO `permission` VALUES (44, 34, '编辑课程', 3, 1, '/course/update', '', '', 'courseUpdate', '', 0, 'sys:course:update', '', '2022-03-08 14:01:34', 1, '2022-03-12 21:32:58', 1);
INSERT INTO `permission` VALUES (45, 5, '审核课程', 3, 1, '/course/audit', '', '', 'courseAudit', '', 0, 'sys:course:audit', '', '2022-03-08 14:01:34', 1, '2022-03-16 17:23:35', 1);
INSERT INTO `permission` VALUES (47, 2, '课程学生列表', 2, 2, '/course/student-list', 'course/student-list/index', '', 'studentList', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-12 21:30:45', 1);
INSERT INTO `permission` VALUES (48, 2, '课程视频列表', 2, 2, '/course/video-list', 'course/video-list/index', '', 'videoList', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-12 21:30:45', 1);
INSERT INTO `permission` VALUES (49, 2, '视频学生列表', 2, 2, '/course/video-student-list', 'course/video-student-list/index', '', 'videoStudentList', 'list', 1, '', '', '2022-03-08 10:17:31', 1, '2022-03-12 21:30:45', 1);
INSERT INTO `permission` VALUES (50, 1, '权限菜单', 2, 3, '/system/permission', 'system/permission/index', '', 'permission', 'permission', 0, '', '', '2022-03-08 13:30:44', 1, '2022-03-08 13:30:53', 1);
INSERT INTO `permission` VALUES (234, 3, '重置密码', 3, 999, '/user/reset-password', '', '', 'resetPassword', '', 0, 'sys:user:reset:password', '', '2022-03-17 10:09:06', 1, '2022-03-17 10:09:06', 1);
INSERT INTO `permission` VALUES (235, 50, '获取权限菜单列表', 3, 1, '/permission/list', '', '', 'permissionList', '', 0, 'sys:permission:query', '', '2022-03-17 10:17:26', 1, '2022-03-17 10:19:16', 1);
INSERT INTO `permission` VALUES (236, 50, '修改权限菜单', 3, 2, '/permission/update', '', '', 'permissionUpdate', '', 0, 'sys:permission:update', '', '2022-03-17 10:18:42', 1, '2022-03-17 10:19:19', 1);
INSERT INTO `permission` VALUES (237, 50, '新增权限菜单', 3, 3, '/permission/create', '', '', 'permissionCreate', '', 0, 'sys:permission:create', '', '2022-03-17 10:20:35', 1, '2022-03-17 10:20:35', 1);
INSERT INTO `permission` VALUES (238, 50, '删除权限菜单', 3, 4, '/permission/delete', '', '', 'permissionDelete', '', 0, 'sys:permission:delete', '', '2022-03-17 10:22:09', 1, '2022-03-17 10:22:09', 1);
INSERT INTO `permission` VALUES (239, 50, '隐藏权限菜单', 3, 5, '/permission/hidden-status', '', '', 'permissionHidden', '', 0, 'sys:permission:hidden', '', '2022-03-17 10:22:56', 1, '2022-03-17 10:22:56', 1);
INSERT INTO `permission` VALUES (240, 48, '获取课程视频列表', 3, 1, '/course-video/list', '', '', 'courseVideoList', '', 0, 'sys:course:video:query', '', '2022-03-17 10:36:39', 1, '2022-03-17 10:36:39', 1);
INSERT INTO `permission` VALUES (241, 47, '获取课程学生列表', 3, 1, '/course/student-list', '', '', 'courseStudentList', '', 0, 'sys:course:student:query', '', '2022-03-17 10:39:08', 1, '2022-03-17 10:39:08', 1);
INSERT INTO `permission` VALUES (242, 48, '新增课程视频', 3, 2, '/course-video/create', '', '', 'courseVideoCreate', '', 0, 'sys:course:video:create', '', '2022-03-17 10:41:00', 1, '2022-03-17 10:41:00', 1);
INSERT INTO `permission` VALUES (243, 48, '修改课程视频', 3, 3, '/course-video/update', '', '', 'courseVideoUpdate', '', 0, 'sys:course:video:update', '', '2022-03-17 10:45:06', 1, '2022-03-17 10:45:06', 1);
INSERT INTO `permission` VALUES (244, 48, '删除课程视频', 3, 4, '/course-video/delete', '', '', 'courseVideoDelete', '', 0, 'sys:course:video:delete', '', '2022-03-17 10:45:43', 1, '2022-03-17 10:45:43', 1);
INSERT INTO `permission` VALUES (245, 48, '审核课程视频', 3, 5, '/course-video/audit', '', '', 'courseVideoAudit', '', 0, 'sys:course:video:audit', '', '2022-03-17 10:46:26', 1, '2022-03-17 10:46:26', 1);
INSERT INTO `permission` VALUES (246, 49, '获取课程视频学生列表', 3, 1, '/course-video/student-list', '', '', 'videoStudentList', '', 0, 'sys:course:video:student:query', '', '2022-03-17 10:47:59', 1, '2022-03-17 10:47:59', 1);

-- ----------------------------
-- Table structure for recharges
-- ----------------------------
DROP TABLE IF EXISTS `recharges`;
CREATE TABLE `recharges`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `action_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '类型 1充值 0消费',
  `pay_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '支付类型 1余额支付 2支付宝 3微信',
  `amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '充值、消费记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人ID',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '基础角色，有所有权限', '2022-03-06 17:10:21', 1, '2022-03-09 13:19:09', 1);
INSERT INTO `role` VALUES (2, '讲师', '基础角色，拥有课程管理等权限', '2022-03-06 17:10:55', 1, '2022-03-09 13:19:01', 1);
INSERT INTO `role` VALUES (3, '学生', '基础角色，无后台权限，只允许登录用户端', '2022-03-06 17:11:07', 1, '2022-03-09 13:19:17', 1);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色权限关联ID',
  `permission_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '权限ID',
  `role_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色ID',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1690, 2, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1691, 5, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1692, 48, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1693, 40, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1694, 41, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1695, 33, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1696, 42, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1697, 34, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1698, 44, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1699, 47, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1700, 241, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1701, 240, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1702, 242, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1703, 243, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1704, 244, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1705, 49, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1706, 246, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1707, 35, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1708, 43, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1709, 7, 2, '2022-03-17 11:12:53', 1);
INSERT INTO `role_permission` VALUES (1710, 1, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1711, 3, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1712, 15, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1713, 16, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1714, 17, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1715, 18, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1716, 19, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1717, 20, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1718, 234, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1719, 4, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1720, 21, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1721, 22, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1722, 8, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1723, 9, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1724, 10, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1725, 11, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1726, 12, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1727, 13, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1728, 14, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1729, 50, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1730, 235, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1731, 236, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1732, 237, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1733, 238, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1734, 239, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1735, 2, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1736, 5, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1737, 40, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1738, 41, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1739, 45, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1740, 33, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1741, 42, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1742, 34, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1743, 44, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1744, 47, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1745, 241, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1746, 48, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1747, 240, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1748, 242, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1749, 243, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1750, 244, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1751, 245, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1752, 49, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1753, 246, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1754, 35, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1755, 43, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1756, 6, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1757, 24, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1758, 25, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1759, 26, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1760, 27, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1761, 28, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1762, 29, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1763, 30, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1764, 36, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1765, 31, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1766, 39, 1, '2022-03-17 13:16:45', 1);
INSERT INTO `role_permission` VALUES (1767, 7, 1, '2022-03-17 13:16:45', 1);

-- ----------------------------
-- Table structure for teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `teacher_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '讲师ID',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_teacher_course`(`teacher_id`, `course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师课程关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teachers
-- ----------------------------
DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `job` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '职位',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teachers
-- ----------------------------
INSERT INTO `teachers` VALUES (4, 1, '管理员', 'http://localhost/image/default.png', '管理员', '目前就职于BAT的大数据部门，超过4年的技术研发经验，熟悉java、python研发，担任过多个系统研发主程，目前专注于分布式系统研发，在高并发、分布式系统有丰富的经验。', '2022-03-13 20:54:43', 1, '2022-03-13 20:54:43', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uid` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'UID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账号 ',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `mobile` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `job` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '职位',
  `learn_hour` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '学习时长',
  `sex` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'unknown' COMMENT '性别 unknown male female',
  `city` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '个性签名',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_phone`(`mobile`) USING BTREE,
  INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1642280631893322', '13630497916', '123456', '13630497916', 'pguangming@163.com', '大的小橘子', 'http://localhost/image/20220312/16470882779286306.jpeg', '学生', 0, 'male', '深圳', '生活就像海洋，只有意志坚强的人才能到达彼岸。', '2022-03-03 16:42:28', 0, '2022-03-17 22:24:00', 1);

-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `cost` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '课程购买价格',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户课程关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_course
-- ----------------------------
INSERT INTO `user_course` VALUES (11, 1, 40, 0.00, '2022-03-18 04:37:56', 1);
INSERT INTO `user_course` VALUES (12, 1, 39, 298.00, '2022-03-18 05:18:14', 1);

-- ----------------------------
-- Table structure for user_course_video
-- ----------------------------
DROP TABLE IF EXISTS `user_course_video`;
CREATE TABLE `user_course_video`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `course_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '课程ID',
  `video_id` int(10) NOT NULL COMMENT '视频ID',
  `learn_length` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前学习时长',
  `cumulative_duration` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '累计学习时长',
  `learn_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '学习状态  1已完成 2学习中',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `updated_at` datetime NOT NULL,
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户课程视频关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_course_video
-- ----------------------------
INSERT INTO `user_course_video` VALUES (4, 1, 40, 10, 221, 400, 1, '2022-03-18 04:37:56', 1, '2022-03-18 05:12:49', 1);
INSERT INTO `user_course_video` VALUES (5, 1, 39, 11, 221, 240, 1, '2022-03-18 05:18:36', 1, '2022-03-18 05:22:36', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户角色关联ID',
  `role_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色ID',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人ID',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `updated_by` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新人ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_role`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2, 1, 1, '2022-03-06 17:21:55', 1, '2022-03-06 17:21:57', 1);
INSERT INTO `user_role` VALUES (16, 2, 7, '2022-03-10 13:08:59', 1, '2022-03-10 13:08:59', 1);
INSERT INTO `user_role` VALUES (18, 3, 8, '2022-03-10 13:38:04', 1, '2022-03-10 13:38:04', 1);
INSERT INTO `user_role` VALUES (19, 2, 9, '2022-03-12 00:33:04', 1, '2022-03-12 00:33:04', 1);
INSERT INTO `user_role` VALUES (21, 3, 10, '2022-03-17 22:25:37', 10, '2022-03-17 22:25:37', 10);

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户账号',
  `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '类型 1用户端 2管理端',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'token',
  `expired_at` datetime NOT NULL COMMENT '过期时间',
  `login_at` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_type`(`user_id`, `type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'token记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES (32, 8, '15382651793', 1, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55So5oi3MTY0NjgwMjE5MDQ2OSIsInN1YiI6IjE1MzgyNjUxNzkzIiwiaWF0IjoxNjQ2ODAyMTkwMDAwLCJ1dWlkIjo4LCJqdGkiOiIxMmFjODk3ZS0xMmIxLTRjOWUtYTZiOS0xZTViOTlhMWE3ZTUifQ.ideKm7Tls3bD_rSO6kLxre6qQ3odsoERA1zfY6c7AGc', '2022-03-09 17:56:13', '2022-03-09 17:26:11');
INSERT INTO `user_token` VALUES (71, 7, '13266853693', 2, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55So5oi3MTY0NjQyMzA1MDM1OCIsInN1YiI6IjEzMjY2ODUzNjkzIiwiaWF0IjoxNjQ2NDIzMDUwMDAwLCJ1dWlkIjo3LCJqdGkiOiJhZjAzOGJjNC03ZjllLTQxZDctYTkwMS0xY2MzYTVmMWMxMTkifQ.GhI8IPwWiW90XN_QhcDBhsCjIl479n2MwZpQDe7720s', '2022-03-13 01:21:51', '2022-03-13 00:50:47');
INSERT INTO `user_token` VALUES (74, 7, '13266853693', 1, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55So5oi3MTY0NjQyMzA1MDM1OCIsInN1YiI6IjEzMjY2ODUzNjkzIiwiaWF0IjoxNjQ2NDIzMDUwMDAwLCJ1dWlkIjo3LCJqdGkiOiI0YmZkYmU1Mi05OGE3LTQ1M2MtODExOS0wZGE1YjhmNDIyMWIifQ.1To6shJywoDyt125JEJ8flzc50bVqcxB7tAvNO423rE', '2022-03-14 21:16:19', '2022-03-14 19:52:59');
INSERT INTO `user_token` VALUES (75, 1, '13630497916', 1, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5aSn55qE5bCP5qmY5a2QIiwic3ViIjoiMTM2MzA0OTc5MTYiLCJpYXQiOjE2NDYyOTY5NDgwMDAsInV1aWQiOjEsImp0aSI6IjJmZDRlZDIzLTk5MmYtNDNjOC1hZjQzLTYxNzE2ZTRlMDlkYyJ9.VSodTCg4tOcQTP2gnomJtHRGKb85TUdv45fzZ3hV--c', '2022-03-18 05:53:18', '2022-03-18 04:36:49');
INSERT INTO `user_token` VALUES (76, 9, '18875973384', 2, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi54yrIiwic3ViIjoiMTg4NzU5NzMzODQiLCJpYXQiOjE2NDcwMTYzODQwMDAsInV1aWQiOjksImp0aSI6IjBlMDA5NzFhLWU1OTUtNGIyMi04ZDJmLTdkYmU1YzBlZTI4OSJ9.RUnxZmkAUveyCBRkAXMouqYoOPm-X_PHtvGzAg2ppdc', '2022-03-17 11:43:05', '2022-03-17 11:07:37');
INSERT INTO `user_token` VALUES (80, 10, 'admin@163.com', 2, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZGRkZCIsInN1YiI6ImFkbWluQDE2My5jb20iLCJpYXQiOjE2NDc1MjcwODQwMDAsInV1aWQiOjEwLCJqdGkiOiI1NjRjOTRkYi1kNzRiLTQyZGUtOTQ0Ny0xM2E5ZjI1ZWE3OGUifQ.SMNU9Wv0oUiNVYRIJPqlgItdGyGTTXC8DBTXec2Nq34', '2022-03-17 22:55:37', '2022-03-17 22:25:16');
INSERT INTO `user_token` VALUES (81, 1, '13630497916', 2, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5aSn55qE5bCP5qmY5a2QIiwic3ViIjoiMTM2MzA0OTc5MTYiLCJpYXQiOjE2NDYyOTY5NDgwMDAsInV1aWQiOjEsImp0aSI6IjkzYmUxYzhmLTlkM2MtNDljMC04ZGJlLTkwMjY4MmI4NjZlNSJ9.khj5eQjzZ6Lj0zbEsxskZI-egckB8Aq6L18QYMcj2qo', '2022-03-18 05:54:06', '2022-03-18 04:27:59');

SET FOREIGN_KEY_CHECKS = 1;
