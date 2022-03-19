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

 Date: 17/03/2022 13:26:05
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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bills
-- ----------------------------
INSERT INTO `bills` VALUES (1, 1, '202203021646187095609', 3, '从基础到实战 手把手带你掌握新版Webpack4.0', 299.00, 1, '2022-03-04 10:37:40', 0);
INSERT INTO `bills` VALUES (2, 1, '202203021646187095609', 11, 'Python Flask高级编程之从0到1开发《鱼书》精品项目', 399.00, 1, '2022-03-04 10:38:15', 0);
INSERT INTO `bills` VALUES (3, 1, '202203021646187095609', 1, 'TypeScript  系统入门到项目实战 趁早学习提高职场竞争力', 216.00, 1, '2022-03-04 10:38:24', 0);
INSERT INTO `bills` VALUES (4, 7, '20220311646977127474712', 2, '前端要学的测试课 从Jest入门到 TDD/BDD双实战', 299.00, 2, '2022-03-11 14:33:18', 7);
INSERT INTO `bills` VALUES (5, 7, '20220311646980577084666', 16, 'Flutter从入门到进阶 实战携程网App', 348.00, 3, '2022-03-11 14:37:29', 7);
INSERT INTO `bills` VALUES (6, 7, '20220311646981705380240', 7, '下一代前端开发语言 TypeScript从零重构axios', 388.00, 3, '2022-03-11 14:55:11', 7);
INSERT INTO `bills` VALUES (7, 7, '20220311646981921798008', 12, '全面系统Python3.8入门+进阶  (程序员必备第二语言)', 366.00, 1, '2022-03-11 14:58:46', 7);
INSERT INTO `bills` VALUES (8, 1, '20220315647274968579013', 2, '前端要学的测试课 从Jest入门到 TDD/BDD双实战', 299.00, 1, '2022-03-15 00:22:52', 1);
INSERT INTO `bills` VALUES (9, 1, '20220317647487701989049', 75, '测网速', 98.00, 2, '2022-03-17 11:28:26', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

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
INSERT INTO `course` VALUES (1, '460b4e3f37bc102afb4170e553c49478', 'TypeScript  系统入门到项目实战 趁早学习提高职场竞争力', 'Dell老师专为TypeScript小白打造的，全栈式教学TS入门课程', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '1、对Javascript基础知识已经掌握;\n2、对Es6和webpack有简单了解;', '1、使用Vue2.0版本实现响应式编程;\n2、理解Vue编程理念与直接操作Dom的差异;\n3、Vue常用的基础语法;\n4、使用Vue编写TodoList功能;\n5、什么是Vue的组件和实例;\n6、Vue-cli脚手架工具的使用;\n7、但文件组件，全局样式与局部样式;', 2, 266.00, 1, 216.00, 0, 4825, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (2, '2a00d5b93e42b435c001ec0cba7f3426', '前端要学的测试课 从Jest入门到 TDD/BDD双实战', '以Vue/React项目进行自动化测试实战，让你技术水平和架构思维双提升', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 2540, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (3, 'feac552385073649c60d375aa7fc2576', '从基础到实战 手把手带你掌握新版Webpack4.0', '知识点+项目实例+原理讲解 全方位解析Webpack4最新版本', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 6591, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (4, 'd5db97b8b2fcf4ec87d79d7c3a06cdf7', 'React服务器渲染原理解析与实践', '从零开始，带你搭建属于自己的React SSR框架，从根本上解决客户端渲染问题 。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 9981, 2, '不通过', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (5, '34614bfdbc7f85b11b8bf4a36d5c3217', 'React开发简书项目 从零基础入门到实战 ', '主流新技术 React-redux，React-router4，贯穿基础语法及项目实战。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 1873, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (6, '0b19644f9b0ecccb427a80f84c0c3c99', '经典再升级-新版Vue2.6开发去哪儿网App 从零基础入门到实战项目', '基于Vue最新版本，从基础语法到完整项目，一课掌握Vue基础知识点', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 417, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (7, '0a6c3c262e86339fa97d759aeb0bd4f7', '下一代前端开发语言 TypeScript从零重构axios', '掌握TS，学习vue3.0源码必备基础！课程从零开始重构功能完整的JS库，是学习造轮子的不二之选！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 388.00, 1, 348.00, 0, 4453, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (8, '61890bbe456159d0a42297698fa4c584', 'Vue.js2.5+cube-ui重构饿了么App（经典再升级）', '掌握Vue1.0到2.0再到2.5最全版本应用与迭代，打造极致流畅的WebApp', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 198.00, 0, 0.00, 0, 6683, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (9, 'f91f0ccad06461f7b8923575773c5e9b', 'Vue.js源码全方位深入解析 （含Vue3.0源码分析）', '全方位讲解 Vue.js 源码，学精学透 Vue 原理实现，进阶高级工程师', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 488.00, 0, 0.00, 0, 4048, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (10, '20e5dd897baa9c11405f3ea41b6ea37d', 'Python Flask高级编程之RESTFul API前后端分离精讲', 'RESTFul+权限管理+token令牌+扩展flask=提升编程思维', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 148.00, 0, 0.00, 0, 309, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (11, 'b588da32214094540725a85131e9c35d', 'Python Flask高级编程之从0到1开发《鱼书》精品项目', '7月老师深入浅出剖析Flask核心机制，和你一起探讨Python高级编程', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 399.00, 0, 0.00, 0, 6751, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (12, 'e729125ed6500196a470eefe4cd73a3f', '全面系统Python3.8入门+进阶  (程序员必备第二语言)', '语法精讲/配套练习+思考题/原生爬虫实战', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 366.00, 0, 0.00, 0, 9108, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (13, 'c70a675e30cdb7d2641ead00d264be57', 'Spring Cloud微服务实战 打造企业级优惠券系统', '面试、毕设、升职优选：从0开始，Java主流框架，构建电商都在用的优惠券系统', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 9332, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (14, 'f9448c4d8a5ec7e529c4af81bc804263', '基于Spring Cloud微服务架构  广告系统设计与实现（2020新版）', '掌握互联网广告系统，学会为公司创收，你自然就是最抢手的人才。可用于毕设。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 8755, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (15, 'c3b03faf55da2924b18a0296a5385c7b', 'Java分布式后台开发  Spring Boot+Kafka+HBase', '从零到一完整搭建企业级架构的通用卡包工程，让你开发技迈向到百度T4+ 。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 1047, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (16, 'b988d18b92419038f7cf1bf132aaa59a', 'Flutter从入门到进阶 实战携程网App', '从入门到进阶，系统掌握Flutter开发核心技术', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 348.00, 0, 0.00, 0, 6185, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (17, 'ac0a2c9cf3ae53bfca07bdca188f5476', '新版React Native从入门到实战打造高质量上线App（再升级）', '解锁React Native开发应用新姿势，一网打尽React Native新版本热门技术', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 399.00, 0, 0.00, 0, 1212, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (18, '2b5003e763cfef6a016fcecf25c1fa15', '实战企业级项目 践行App重构之路', '真实还原大厂App重构过程，以组件化和插件化为核心，进击高级工程师必备', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 4988, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (19, '3aea9cfa485028c8ab7899a85fb9ea25', '企业级Android应用架构设计与开发', '一门能助你掌握企业级架构设计、功能开发，冲击大厂Android中高级工程师职位的课程', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 4648, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (20, 'f0a01e6bd1e3df50fc1a0c1434a8abe3', 'Gradle3.0自动化项目构建技术精讲+实战', '全面覆盖Gradle核心知识和高级用法，高级工程师必备技能！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 199.00, 0, 0.00, 0, 3047, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (21, '2d5dc1541d747e9f2144d6d6b5ad71eb', '玩转算法系列--图论精讲  面试升职必备（Java版）', '由于图论算法本身的复杂性和抽象性，让同学们头疼不已，这次bobo带你彻底玩转图论，克服对图论问题的恐惧心理', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 348.00, 0, 0.00, 0, 9447, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (22, '74d91afa64f9ec121d789e7fb061d928', '玩转算法系列--数据结构精讲 更适合0算法基础入门到进阶（java版）', '体系完整，细致入微，0基础入门：动态数组/栈/队列/链表/BST/堆/线段树/Trie/并查集/AVL/红黑树…', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 299.00, 0, 0.00, 0, 7856, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (23, '4c094284daaaa50d33d31ff4752c99d3', '结合编程学数学 专为程序员设计的线性代数', '创新设计，通俗易懂。用编程的方式学数学。这一次，bobo老师带你彻底征服线性代数', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 348.00, 0, 0.00, 0, 9218, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (24, '25f6eb63d48d98ce64146f97b4bc7b23', '看的见的算法 7个经典应用诠释算法精髓', '课程重应用、重实践、重思维，真正应用于实际工作开发中，也可作为毕设作品、面试项目。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 248.00, 0, 0.00, 0, 2962, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (25, '304dc4e37b3a9a16e45d22f7dd35cd55', '看的见的算法 Zookeeper源码分析', '“分而治之”逐一攻克Zookeeper框架各个组件的源码', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 388.00, 0, 0.00, 0, 5316, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (26, '907f5e043c32cd691f66f1a7453d7fc3', '学习Hyperledger Fabric实战联盟链', '兼顾区块链应用层和底层  进击区块链工程师', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 5520, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (27, '08e3976ccf13b653265e829f8ce3b66d', '深度学习之目标检测常用算法原理+实践精讲', '从原理（YOLO / Faster RCNN / SSD / 文本检测 / 多任务网络）到场景实战，掌握目标检测核心技术', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 499.00, 0, 0.00, 0, 333, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (28, '7e8037253746c8ef3fd1e4e25fe2361b', '看的见的算法 Zookeeper源码分析', '“分而治之”逐一攻克Zookeeper框架各个组件的源码', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 388.00, 0, 0.00, 0, 6420, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (29, 'ab27c6fa1faf341f7d26ca5b72321aaf', '学习Hyperledger Fabric实战联盟链', '兼顾区块链应用层和底层  进击区块链工程师', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 7743, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (30, 'ba8044f910edf1e58b3b205307fc75d6', '深度学习之目标检测常用算法原理+实践精讲', '从原理（YOLO / Faster RCNN / SSD / 文本检测 / 多任务网络）到场景实战，掌握目标检测核心技术', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 499.00, 0, 0.00, 0, 8596, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (31, 'ad63c02f1fca3158f6107f467eb17ca9', '看的见的算法 Zookeeper源码分析', '“分而治之”逐一攻克Zookeeper框架各个组件的源码', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 388.00, 0, 0.00, 0, 6602, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (32, '78676d013d0a899a869256a387b2dd6b', '学习Hyperledger Fabric实战联盟链', '兼顾区块链应用层和底层  进击区块链工程师', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 1353, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (33, '1d42a10c7a43a77662cab976c1d27493', '深度学习之目标检测常用算法原理+实践精讲', '从原理（YOLO / Faster RCNN / SSD / 文本检测 / 多任务网络）到场景实战，掌握目标检测核心技术', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 499.00, 0, 0.00, 0, 1861, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (34, 'e3a486ba48d96bac40e8ce7342aa2021', '看的见的算法 Zookeeper源码分析', '“分而治之”逐一攻克Zookeeper框架各个组件的源码', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 388.00, 0, 0.00, 0, 7387, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (35, '8d4943c196a8ec68beb16904a1dd37bf', '学习Hyperledger Fabric实战联盟链', '兼顾区块链应用层和底层  进击区块链工程师', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 9588, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (36, '0f353d5d3ba93b50d5d621f247a77b2c', '深度学习之目标检测常用算法原理+实践精讲', '从原理（YOLO / Faster RCNN / SSD / 文本检测 / 多任务网络）到场景实战，掌握目标检测核心技术', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 499.00, 0, 0.00, 0, 3258, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (37, '3b8e4b133bb6c6236f0a39b715ae7ac8', 'SparkSQL极速入门  整合Kudu实现广告业务数据分析', '大数据工程师干货课程 带你从入门到实战掌握SparkSQL', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 388.00, 0, 0.00, 0, 3470, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (38, '399feaf9f78fe961c7cb78216230bf8b', 'Spark进阶 大数据离线与实时项目实战', '大数据生态圈中多个框架(Spark/Hbase/Redis/Hadoop)的整合应用及调优', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 399.00, 0, 0.00, 0, 4103, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (39, '72466ed1e790707985c2342894a1ace2', 'Spark Streaming实时流处理项目实战 ', 'Flume+Kafka+Spark Streaming 构建通用实时流处理平台', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 288.00, 0, 0.00, 0, 8033, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (40, '1ada46557a2d996e7bb5eba91fe03223', '跟着360架构师 学习Shell脚本编程', '30%知识讲解+70%实例操作 掌握Shell脚本编程能力', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 366.00, 0, 0.00, 0, 9178, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (41, '77fd82fda78d917ad11c99a16b7f26fa', '企业级开源四层负载均衡解决方案-LVS', '轻松应对负载均衡，深刻理解网络系统架构，真正解决工作中的实际问题', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 199.00, 0, 0.00, 0, 9129, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (42, 'cefeb44815c62bfb98c327343eb5a0de', '跟着360架构师 学习Shell脚本编程', '30%知识讲解+70%实例操作 掌握Shell脚本编程能力', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 366.00, 0, 0.00, 0, 797, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (43, '77d2d8c177e8b8b7cc2b09244d9db6c4', '企业级开源四层负载均衡解决方案-LVS', '轻松应对负载均衡，深刻理解网络系统架构，真正解决工作中的实际问题', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 199.00, 0, 0.00, 0, 4104, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (44, 'e422ae5d7ca66af3e0b542a0c6a80820', '零基础入门 全角度解读企业主流数据库MySQL8.0', '掌握SQL优化与慢查询优化，具备独当一面的能力，彰显更多个人价值', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 9688, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (45, '1a431790c4b053ebc1eb65edd91c90f6', '零基础入门 全角度解读企业主流数据库MySQL8.0', '掌握SQL优化与慢查询优化，具备独当一面的能力，彰显更多个人价值', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 3363, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (46, '1103311d029e3bd36edb035251c955ee', '中高级开发晋升利器 MySQL面试指南', '9大类常见问题详解，切实提升数据库应用和管理能力，升职加薪必备佳品！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 288.00, 0, 0.00, 0, 4117, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (47, '49c63208a7e8c994928663766e10b5eb', '零基础入门 全角度解读企业主流数据库MySQL8.0', '掌握SQL优化与慢查询优化，具备独当一面的能力，彰显更多个人价值', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 266.00, 0, 0.00, 0, 1263, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (48, '722b4d5b99332be3b7cafa1f231ab48e', '中高级开发晋升利器 MySQL面试指南', '9大类常见问题详解，切实提升数据库应用和管理能力，升职加薪必备佳品！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 288.00, 0, 0.00, 0, 4321, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (49, '5dc8892d1bf76b4e24ae2a8e8845c843', '玩转MongoDB4.0(最新版) 从入门到实践', '30%理论+70%实战，让你用实操去检验真理,一门让你事半功倍的入门进阶课', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 288.00, 0, 0.00, 0, 9240, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (50, '6b7aeda46ea8e988c03b3ad27e152f7c', '高薪设计师必修课 AE移动UI动效设计从入门到实战', '20多个商业实用案例，轻松Get√到AE动效核心技术，让你的作品脱颖而出！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 199.00, 0, 0.00, 0, 8449, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (51, '9dfa8d8a82e3fd5a2008d763f7ffe6e6', '移动端App UI设计入门与实战', '涉及多项实用设计技能训练，成为一名具有“产品思维”的设计师！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 199.00, 0, 0.00, 0, 9328, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (52, '9138bc8fc16258a99fd6ccde34c164a0', '高薪设计师必修课 AE移动UI动效设计从入门到实战', '20多个商业实用案例，轻松Get√到AE动效核心技术，让你的作品脱颖而出！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 199.00, 0, 0.00, 0, 1650, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (53, '9f9bd4d1a4ac50f236ab7026510b2d8a', '移动端App UI设计入门与实战', '涉及多项实用设计技能训练，成为一名具有“产品思维”的设计师！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 2, 199.00, 0, 0.00, 0, 8223, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (54, '6a4ca71e1b4acdc9e96641d5d398ad2f', '初识HTML(5)+CSS(3)-2020升级版', 'HTML(5)+CSS(3)基础教程8小时带领大家步步深入学习标签用法和意义', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 7359, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (55, '606fb08e7f5ec278e789d6efd25148a0', 'JavaScript入门篇', 'JavaScript做为一名Web工程师的必备技术，本教程让您快速入门', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 3261, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (56, 'ca6c8c0724e1d53ca5df274c3f1a2c29', 'JavaScript进阶篇', '本课程从如何插入JS代码开始，带您进入网页动态交互世界', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 2139, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (57, '563ad12577fc0dcdc53e95d18ef9519a', 'vue2.5入门', '快速理解Vue编程理念上手Vue2.0开发。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 2200, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (58, '14e3942d7d5b2943211ff9e46325c68a', 'Vue+Webpack打造todo应用', '用前端最热门框架Vue+最火打包工具Webpack打造todo应用', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 7287, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (59, '1f21801b2312dbfd45482dcce620f282', 'Vue+node.js调试入门', '理论实践相结合学习使用 Inspector 调试 Node.js。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 9053, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (60, '97c396e08171ebfa5180e942aa51cd57', 'Nodejs全栈入门', '基于node+mysql+react全栈实战', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 339, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (61, '8a966de87172ff9727e1256b10a012cc', '使用Prometheus实践基于Spring Boot监控告警体系', '基于Spring Boot2.X使用Prometheus实现监控大盘及微服务告警功能。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 4838, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (62, '12033a2ef3e72149ac57fa5bc81cfba8', '二进制与Java中的基本数据类型', '从认识二进制开始，逐步理解Java是如何存储和处理数据的。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 8040, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (63, 'b8fdece933e871f36b3cb63b58c3456a', '自己动手实现RPC框架', '自己动手实现一个完整的RPC框架，So Easy！', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 9376, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (64, 'f9ef609a333e4bcc33ddd2639cec28b0', 'MUI+个推实现安卓与ios移动端推送', '结合慕信轻聊Netty实战，整合个推到前端与后端，实现安卓与ios移动端推送', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 7310, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (65, '96787be707cd0545b19e2e6a52b91bab', 'Springboot 微信小程序 – 微信登录功能实战', '简单实现在小程序中对使用微信登录的方式来登录小程序应用', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 6547, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (66, 'e1c48d7b4e3718035338a55fa3eb02a2', 'Numpy基础入门', '从基本数组入手全面讲解Numpy中的核心知识', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 6707, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (67, '8e03d307eeb72838848bf5dd9c33ce86', 'Python数据预处理（四）- 特征降维与可视化', '教会你使用Python数据预处理', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '使用Vue2.0版本实现响应式编程;使用Vue2.0版本实现响应式编程', '使用Vue2.0版本实现响应式编程;理解Vue编程理念与直接操作Dom的差异;Vue常用的基础语法;使用Vue编写TodoList功能;什么是Vue的组件和实例;Vue-cli脚手架工具的使用;但文件组件，全局样式与局部样式', 1, 0.00, 0, 0.00, 0, 3328, 1, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (68, '1fe3e52b9295f82d09860103168dfefb', 'MultiDex从基础原理到实践优化', 'Android进阶学习必备，带你从基础用法到实践优化一站式掌握MultiDex。', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 6016, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (69, '30f448e985bc28050e4751a30fcd934b', 'Android网络安全之加解密', '在不安全的网络环境中，如何安全的传输敏感数据', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 9981, 2, '违规不通过', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (70, '2d0f2bb519cfa9cfbf41c315159fbee1', 'Android CMake以及NDK实践基础', 'Android底层开发入门必备，CMake动态库编译和使用，NDK的各种开发技巧。 ', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 7664, 2, '不通过', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (71, '6074260b3cef807f37e5ae1f1a4dc726', 'Android 机器学习中的统计学基础', '机器学习中的常用统计学知识点 ', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 3619, 0, '', '2022-03-04 10:10:08', 1, '2022-03-04 10:10:08', 1);
INSERT INTO `course` VALUES (72, '18ac26e95a24b1303dd56e7bbb21ab62', 'Javascript实现二叉树算法', '感受JS与数据结构的魅力。 ', 'http://localhost/image/20220307/5e1d991809c5318e40000800.png', 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '', '', 1, 0.00, 0, 0.00, 0, 1669, 0, '', '2022-03-04 10:10:08', 1, '2022-03-15 19:54:06', 1);
INSERT INTO `course` VALUES (75, 'e8664d1579a44773afd70bd95c47a5a8', '测网速', '顶顶顶顶顶', 'http://localhost/image/20220317/16474866864968328.png', '', '测试', '啥也学不到', 2, 100.00, 1, 98.00, 0, 1, 1, '', '2022-03-17 11:11:34', 9, '2022-03-17 11:13:44', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程分类关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_category
-- ----------------------------
INSERT INTO `course_category` VALUES (1, 10, 'HTML/CSS', 2, '2022-03-11 10:35:06', 1);
INSERT INTO `course_category` VALUES (2, 10, 'HTML/CSS', 67, '2022-03-11 10:35:06', 1);
INSERT INTO `course_category` VALUES (4, 11, 'JavaScript', 72, '2022-03-15 19:22:07', 1);
INSERT INTO `course_category` VALUES (5, 11, 'JavaScript', 75, '2022-03-17 11:11:34', 9);
INSERT INTO `course_category` VALUES (6, 10, 'HTML/CSS', 75, '2022-03-17 11:11:34', 9);
INSERT INTO `course_category` VALUES (7, 12, 'TypeScript', 75, '2022-03-17 11:11:34', 9);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程视频关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_video
-- ----------------------------
INSERT INTO `course_video` VALUES (1, 67, '4-4 使用 TDD 的方式开发 Header 组件（1）4-4 使用 TDD 的方式开发 Header 组件（1）', 'C:\\nginx-1.16.1\\upload\\video\\test.mp4', 1024, 'http://localhost/image/default.png', 'http://localhost/video/test.mp4', 2, 1, 1, '', '2022-03-12 17:04:22', 1, 1, '2022-03-12 17:04:28');
INSERT INTO `course_video` VALUES (2, 67, '4-4 使用 TDD 的方式开发 Header 组件（2）', 'C:\\nginx-1.16.1\\upload\\video\\80540291c8d136764372dc9b00167755.mp4', 887798, 'http://localhost/image/20220316/16474063789914785.jpg', 'http://localhost/video/80540291c8d136764372dc9b00167755.mp4', 2, 1, 0, '', '2022-03-16 12:53:03', 1, 1, '2022-03-16 12:53:03');
INSERT INTO `course_video` VALUES (3, 67, '4-4 使用 TDD 的方式开发 Header 组件（3）', '1', 1024, 'http://localhost/image/20220316/1647406520410102.jpg', '1', 1500, 1, 0, '', '2022-03-16 12:55:23', 1, 1, '2022-03-16 12:55:23');
INSERT INTO `course_video` VALUES (4, 2, '4-4 使用 TDD 的方式开发 Header 组件（3）', '1', 1, '1', '1', 1400, 1, 1, '', '2022-03-12 17:04:22', 1, 1, '2022-03-12 17:04:28');
INSERT INTO `course_video` VALUES (5, 2, '4-4 使用 TDD 的方式开发 Header 组件（4）', '1', 1, '1', '1', 6000, 1, 1, '', '2022-03-12 17:04:22', 1, 1, '2022-03-12 17:04:28');
INSERT INTO `course_video` VALUES (6, 2, '4-4 使用 TDD 的方式开发 Header 组件（4）', '1', 1, '1', '1', 1460, 1, 1, '', '2022-03-12 17:04:22', 1, 1, '2022-03-12 17:04:28');
INSERT INTO `course_video` VALUES (7, 67, '12345', 'C:\\nginx-1.16.1\\upload\\video\\80540291c8d136764372dc9b00167755.mp4', 887798, 'http://localhost/image/default.png', 'http://localhost/video/80540291c8d136764372dc9b00167755.mp4', 2, 1, 1, '', '2022-03-16 12:33:51', 1, 1, '2022-03-16 12:49:04');
INSERT INTO `course_video` VALUES (9, 75, '测试', 'C:\\nginx-1.16.1\\upload\\video\\80540291c8d136764372dc9b00167755.mp4', 887798, 'http://localhost/image/default.png', 'http://localhost/video/80540291c8d136764372dc9b00167755.mp4', 2, 1, 1, '', '2022-03-17 11:12:08', 9, 1, '2022-03-17 11:20:28');

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
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页推荐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_recommend
-- ----------------------------
INSERT INTO `home_recommend` VALUES (64, 2, 1, '2022-03-10 22:24:15', 1);
INSERT INTO `home_recommend` VALUES (65, 7, 1, '2022-03-10 22:24:15', 1);
INSERT INTO `home_recommend` VALUES (66, 12, 1, '2022-03-10 22:24:15', 1);
INSERT INTO `home_recommend` VALUES (67, 16, 1, '2022-03-10 22:24:15', 1);
INSERT INTO `home_recommend` VALUES (71, 57, 2, '2022-03-10 22:24:30', 1);
INSERT INTO `home_recommend` VALUES (72, 59, 2, '2022-03-10 22:24:30', 1);
INSERT INTO `home_recommend` VALUES (73, 61, 2, '2022-03-10 22:24:30', 1);
INSERT INTO `home_recommend` VALUES (74, 63, 2, '2022-03-10 22:24:30', 1);
INSERT INTO `home_recommend` VALUES (75, 65, 2, '2022-03-10 22:24:30', 1);
INSERT INTO `home_recommend` VALUES (76, 67, 2, '2022-03-10 22:24:30', 1);
INSERT INTO `home_recommend` VALUES (79, 72, 1, '2022-03-15 19:26:26', 1);
INSERT INTO `home_recommend` VALUES (81, 75, 2, '2022-03-17 11:11:34', 9);

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '202203021646187095609', 1, 1, 1, 914.50, '2022-03-05 10:15:38', NULL, '2022-03-04 10:15:31', '2022-03-04 10:15:15', 1, '2022-03-04 10:15:18', 1);
INSERT INTO `orders` VALUES (4, '202203041646198276295', 1, 2, 0, 698.00, '2022-03-04 16:36:42', '2022-03-04 16:36:42', NULL, '2022-03-04 13:16:49', 1, '2022-03-04 13:16:51', 1);
INSERT INTO `orders` VALUES (6, '20220304646405511585791', 1, 2, 0, 399.00, '2022-03-05 10:01:04', '2022-03-04 23:06:32', NULL, '2022-03-04 22:51:52', 1, '2022-03-04 22:51:52', 1);
INSERT INTO `orders` VALUES (7, '20220311646968946633584', 7, 2, 0, 299.00, '2022-03-10 23:22:27', '2022-03-11 11:22:28', NULL, '2022-03-11 11:22:27', 7, '2022-03-11 11:22:27', 7);
INSERT INTO `orders` VALUES (8, '20220311646977127474712', 7, 1, 2, 299.00, '2022-03-12 01:38:47', NULL, '2022-03-11 14:33:18', '2022-03-11 13:38:47', 7, '2022-03-11 14:33:18', 7);
INSERT INTO `orders` VALUES (9, '20220311646980577084666', 7, 1, 3, 348.00, '2022-03-12 02:36:17', NULL, '2022-03-11 14:37:29', '2022-03-11 14:36:17', 7, '2022-03-11 14:37:29', 7);
INSERT INTO `orders` VALUES (10, '20220311646980692107003', 7, 2, 0, 754.00, '2022-03-12 02:38:12', '2022-03-11 14:38:23', NULL, '2022-03-11 14:38:12', 7, '2022-03-11 14:38:12', 7);
INSERT INTO `orders` VALUES (11, '20220311646981705380240', 7, 1, 3, 388.00, '2022-03-12 02:55:05', NULL, '2022-03-11 14:55:11', '2022-03-11 14:55:05', 7, '2022-03-11 14:55:11', 7);
INSERT INTO `orders` VALUES (12, '20220311646981921798008', 7, 1, 1, 366.00, '2022-03-12 02:58:42', NULL, '2022-03-11 14:58:46', '2022-03-11 14:58:42', 7, '2022-03-11 14:58:46', 7);
INSERT INTO `orders` VALUES (13, '20220315647274968579013', 1, 1, 1, 299.00, '2022-03-15 12:22:49', NULL, '2022-03-15 00:22:52', '2022-03-15 00:22:49', 1, '2022-03-15 00:22:52', 1);
INSERT INTO `orders` VALUES (14, '20220317647487701989049', 1, 1, 2, 98.00, '2022-03-17 23:28:22', NULL, '2022-03-17 11:28:26', '2022-03-17 11:28:22', 1, '2022-03-17 11:28:26', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders_detail
-- ----------------------------
INSERT INTO `orders_detail` VALUES (1, 1, 3, 'https://img1.sycdn.imooc.com/szimg/5d31765d08c90cba06000338-360-202.jpg', '从基础到实战 手把手带你掌握新版Webpack4.0', 299.50, 0, 0.00, '2022-03-04 10:26:26', 0);
INSERT INTO `orders_detail` VALUES (2, 1, 11, 'https://img1.sycdn.imooc.com/szimg/5d31765d08c90cba06000338-360-202.jpg', 'Python Flask高级编程之从0到1开发《鱼书》精品项目', 399.00, 0, 0.00, '2022-03-04 10:30:14', 0);
INSERT INTO `orders_detail` VALUES (3, 1, 1, 'https://img1.sycdn.imooc.com/szimg/5d31765d08c90cba06000338-360-202.jpg', 'TypeScript  系统入门到项目实战 趁早学习提高职场竞争力', 266.00, 1, 216.00, '2022-03-04 10:30:49', 0);
INSERT INTO `orders_detail` VALUES (4, 3, 2, 'https://img1.sycdn.imooc.com/szimg/5d31765d08c90cba06000338-360-202.jpg', '前端要学的测试课 从Jest入门到 TDD/BDD双实战', 299.00, 0, 0.00, '2022-03-04 10:32:16', 0);
INSERT INTO `orders_detail` VALUES (5, 4, 2, 'https://img1.sycdn.imooc.com/szimg/5d31765d08c90cba06000338-360-202.jpg', '前端要学的测试课 从Jest入门到 TDD/BDD双实战', 299.00, 0, 0.00, '2022-03-04 10:32:16', 0);
INSERT INTO `orders_detail` VALUES (6, 4, 11, 'https://img1.sycdn.imooc.com/szimg/5d31765d08c90cba06000338-360-202.jpg', 'Python Flask高级编程之从0到1开发《鱼书》精品项目', 399.00, 0, 0.00, '2022-03-04 10:30:14', 0);
INSERT INTO `orders_detail` VALUES (7, 6, 17, 'https://img1.sycdn.imooc.com/szimg/5d31765d08c90cba06000338-360-202.jpg', '新版React Native从入门到实战打造高质量上线App（再升级）', 399.00, 0, 0.00, '2022-03-04 22:51:52', 1);
INSERT INTO `orders_detail` VALUES (8, 7, 2, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '前端要学的测试课 从Jest入门到 TDD/BDD双实战', 299.00, 0, 0.00, '2022-03-11 11:22:27', 7);
INSERT INTO `orders_detail` VALUES (9, 8, 2, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '前端要学的测试课 从Jest入门到 TDD/BDD双实战', 299.00, 0, 0.00, '2022-03-11 13:38:47', 7);
INSERT INTO `orders_detail` VALUES (10, 9, 16, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', 'Flutter从入门到进阶 实战携程网App', 348.00, 0, 0.00, '2022-03-11 14:36:17', 7);
INSERT INTO `orders_detail` VALUES (11, 10, 12, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '全面系统Python3.8入门+进阶  (程序员必备第二语言)', 366.00, 0, 0.00, '2022-03-11 14:38:12', 7);
INSERT INTO `orders_detail` VALUES (12, 10, 7, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '下一代前端开发语言 TypeScript从零重构axios', 388.00, 0, 348.00, '2022-03-11 14:38:12', 7);
INSERT INTO `orders_detail` VALUES (13, 11, 7, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '下一代前端开发语言 TypeScript从零重构axios', 388.00, 0, 348.00, '2022-03-11 14:55:05', 7);
INSERT INTO `orders_detail` VALUES (14, 12, 12, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '全面系统Python3.8入门+进阶  (程序员必备第二语言)', 366.00, 0, 0.00, '2022-03-11 14:58:42', 7);
INSERT INTO `orders_detail` VALUES (15, 13, 2, 'http://localhost/image/20220307/5d31765d08c90cba06000338-360-202.jpg', '前端要学的测试课 从Jest入门到 TDD/BDD双实战', 299.00, 0, 0.00, '2022-03-15 00:22:49', 1);
INSERT INTO `orders_detail` VALUES (16, 14, 75, '', '测网速', 100.00, 1, 98.00, '2022-03-17 11:28:22', 1);

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
-- Records of recharges
-- ----------------------------
INSERT INTO `recharges` VALUES (1, 1, 1, 2, 5000.00, '支付宝充值', '2022-03-04 10:40:40', 0);
INSERT INTO `recharges` VALUES (2, 1, 0, 1, 914.00, '订单支出，订单号：202203021646187095609', '2022-03-04 10:44:29', 0);
INSERT INTO `recharges` VALUES (3, 1, 1, 2, 300.00, '支付宝充值', '2022-03-04 17:08:40', 1);
INSERT INTO `recharges` VALUES (4, 1, 1, 2, 300.00, '支付宝充值', '2022-03-04 17:09:16', 1);
INSERT INTO `recharges` VALUES (5, 1, 1, 3, 4.00, '微信充值', '2022-03-04 17:14:39', 1);
INSERT INTO `recharges` VALUES (6, 1, 1, 2, 500.00, '支付宝充值', '2022-03-06 21:54:26', 1);
INSERT INTO `recharges` VALUES (7, 7, 1, 2, 300.00, '支付宝充值', '2022-03-11 14:36:04', 7);
INSERT INTO `recharges` VALUES (8, 7, 1, 3, 1000.00, '微信充值', '2022-03-11 14:58:30', 7);
INSERT INTO `recharges` VALUES (9, 7, 0, 1, 366.00, '订单支出，订单号：20220311646981921798008', '2022-03-11 14:58:46', 7);
INSERT INTO `recharges` VALUES (10, 1, 0, 1, 299.00, '订单支出，订单号：20220315647274968579013', '2022-03-15 00:22:52', 1);

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
-- Records of teacher_course
-- ----------------------------
INSERT INTO `teacher_course` VALUES (1, 1, 2, '2022-03-11 10:36:12', 1);
INSERT INTO `teacher_course` VALUES (2, 1, 67, '2022-03-12 15:55:48', 0);
INSERT INTO `teacher_course` VALUES (4, 3, 75, '2022-03-17 11:11:34', 9);

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
INSERT INTO `teachers` VALUES (1, 7, '火云邪神', 'http://localhost/image/20220307/1646659466948146.jpg', '高级JAVA开发', '人生无常，大肠包小肠', '2022-03-10 13:08:59', 1, '2022-03-10 15:55:02', 7);
INSERT INTO `teachers` VALUES (2, 8, '', 'http://localhost/image/default.png', '', '', '2022-03-10 13:37:43', 1, '2022-03-10 13:37:43', 1);
INSERT INTO `teachers` VALUES (3, 9, '陈', 'http://localhost/image/20220312/16470901482472908.jpg', '', '', '2022-03-12 00:33:04', 1, '2022-03-12 21:03:14', 1);
INSERT INTO `teachers` VALUES (4, 1, '管理员', 'http://localhost/image/default.png', '管理员', '', '2022-03-13 20:54:43', 1, '2022-03-13 20:54:43', 1);

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
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1642280631893322', '13630497916', '$2a$10$6LEWNfMHeNIfyBL.qXwGn.bcd1EdT8HWRq9LuhrclV8wBy.eMUqCK', '13630497916', 'pguangming@163.com', '大的小橘子', 'http://localhost/image/20220312/16470882779286306.jpeg', '学生', 0, 'male', '深圳', '生活就像海洋，只有意志坚强的人才能到达彼岸。', '2022-03-03 16:42:28', 0, '2022-03-15 00:03:18', 1);
INSERT INTO `user` VALUES (7, '0344101212589765', '13266853693', '$2a$10$6LEWNfMHeNIfyBL.qXwGn.bcd1EdT8HWRq9LuhrclV8wBy.eMUqCK', '13266853693', '1950661299@qq.com', '用户1646423050358', 'http://localhost/image/default.png', '', 0, 'male', '广州', '生活就像海洋，只有意志坚强的人才能到达彼岸', '2022-03-05 03:44:10', 0, '2022-03-11 15:19:53', 7);
INSERT INTO `user` VALUES (8, '1303101698848335', '15382651793', '$2a$10$C60mKvxmNiYnZEsCizepnuHanl.hsKHrjJ8SKAShgirwTV.rl6L.y', '15382651793', '', '用户1646802190469', 'http://localhost/image/default.png', '', 0, 'unknown', '', '', '2022-03-09 13:03:10', 0, '2022-03-09 17:19:11', 8);
INSERT INTO `user` VALUES (9, '2111491161945253', '18875973384', '$2a$10$xqnq2H1VGyB0qhpwrNTF8eaPH8iomcXZFX8xvbzHQSJvhNtYZjJSW', '18875973384', '1091583184@qq.com', '猫', 'http://localhost/image/20220312/16470907065045355.jpg', '学生', 0, 'female', '湛江', '', '2022-03-12 00:33:04', 1, '2022-03-12 21:11:49', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户课程关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_course
-- ----------------------------
INSERT INTO `user_course` VALUES (1, 7, 2, 0.00, '2022-03-11 14:33:18', 7);
INSERT INTO `user_course` VALUES (2, 7, 16, 0.00, '2022-03-11 14:37:29', 7);
INSERT INTO `user_course` VALUES (3, 7, 7, 0.00, '2022-03-11 14:55:11', 7);
INSERT INTO `user_course` VALUES (4, 7, 12, 0.00, '2022-03-11 14:58:46', 7);
INSERT INTO `user_course` VALUES (5, 7, 1, 0.00, '2022-03-12 22:55:19', 0);
INSERT INTO `user_course` VALUES (6, 1, 2, 299.00, '2022-03-15 00:22:52', 1);
INSERT INTO `user_course` VALUES (9, 1, 67, 0.00, '2022-03-15 00:32:40', 1);
INSERT INTO `user_course` VALUES (10, 1, 75, 98.00, '2022-03-17 11:28:26', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户课程视频关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_course_video
-- ----------------------------
INSERT INTO `user_course_video` VALUES (1, 1, 67, 1, 100, 1026, 1, '2022-03-12 23:12:40', 1, '2022-03-12 23:12:43', 1);
INSERT INTO `user_course_video` VALUES (2, 1, 67, 7, 2, 6, 1, '2022-03-16 20:32:05', 1, '2022-03-16 20:59:59', 1);
INSERT INTO `user_course_video` VALUES (3, 1, 75, 9, 2, 2, 1, '2022-03-17 11:28:42', 1, '2022-03-17 11:28:42', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2, 1, 1, '2022-03-06 17:21:55', 1, '2022-03-06 17:21:57', 1);
INSERT INTO `user_role` VALUES (16, 2, 7, '2022-03-10 13:08:59', 1, '2022-03-10 13:08:59', 1);
INSERT INTO `user_role` VALUES (18, 3, 8, '2022-03-10 13:38:04', 1, '2022-03-10 13:38:04', 1);
INSERT INTO `user_role` VALUES (19, 2, 9, '2022-03-12 00:33:04', 1, '2022-03-12 00:33:04', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'token记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES (32, 8, '15382651793', 1, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55So5oi3MTY0NjgwMjE5MDQ2OSIsInN1YiI6IjE1MzgyNjUxNzkzIiwiaWF0IjoxNjQ2ODAyMTkwMDAwLCJ1dWlkIjo4LCJqdGkiOiIxMmFjODk3ZS0xMmIxLTRjOWUtYTZiOS0xZTViOTlhMWE3ZTUifQ.ideKm7Tls3bD_rSO6kLxre6qQ3odsoERA1zfY6c7AGc', '2022-03-09 17:56:13', '2022-03-09 17:26:11');
INSERT INTO `user_token` VALUES (71, 7, '13266853693', 2, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55So5oi3MTY0NjQyMzA1MDM1OCIsInN1YiI6IjEzMjY2ODUzNjkzIiwiaWF0IjoxNjQ2NDIzMDUwMDAwLCJ1dWlkIjo3LCJqdGkiOiJhZjAzOGJjNC03ZjllLTQxZDctYTkwMS0xY2MzYTVmMWMxMTkifQ.GhI8IPwWiW90XN_QhcDBhsCjIl479n2MwZpQDe7720s', '2022-03-13 01:21:51', '2022-03-13 00:50:47');
INSERT INTO `user_token` VALUES (74, 7, '13266853693', 1, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55So5oi3MTY0NjQyMzA1MDM1OCIsInN1YiI6IjEzMjY2ODUzNjkzIiwiaWF0IjoxNjQ2NDIzMDUwMDAwLCJ1dWlkIjo3LCJqdGkiOiI0YmZkYmU1Mi05OGE3LTQ1M2MtODExOS0wZGE1YjhmNDIyMWIifQ.1To6shJywoDyt125JEJ8flzc50bVqcxB7tAvNO423rE', '2022-03-14 21:16:19', '2022-03-14 19:52:59');
INSERT INTO `user_token` VALUES (75, 1, '13630497916', 1, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5aSn55qE5bCP5qmY5a2QIiwic3ViIjoiMTM2MzA0OTc5MTYiLCJpYXQiOjE2NDYyOTY5NDgwMDAsInV1aWQiOjEsImp0aSI6IjQwMDY0NjAyLTIxZTQtNDA2Zi1iZTUxLTBhMjQzNWI5ZmQ5OSJ9.sSKk0nUQ96b5kuMW5hMOCx4lyLCALea5GFeDKoETTUA', '2022-03-17 11:59:14', '2022-03-17 11:19:58');
INSERT INTO `user_token` VALUES (76, 9, '18875973384', 2, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi54yrIiwic3ViIjoiMTg4NzU5NzMzODQiLCJpYXQiOjE2NDcwMTYzODQwMDAsInV1aWQiOjksImp0aSI6IjBlMDA5NzFhLWU1OTUtNGIyMi04ZDJmLTdkYmU1YzBlZTI4OSJ9.RUnxZmkAUveyCBRkAXMouqYoOPm-X_PHtvGzAg2ppdc', '2022-03-17 11:43:05', '2022-03-17 11:07:37');
INSERT INTO `user_token` VALUES (78, 1, '13630497916', 2, 'eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5aSn55qE5bCP5qmY5a2QIiwic3ViIjoiMTM2MzA0OTc5MTYiLCJpYXQiOjE2NDYyOTY5NDgwMDAsInV1aWQiOjEsImp0aSI6IjFlMWQ4NDM4LTFhOTUtNDliZS1iZjE1LTU5ZDAxOWIyYzY5OSJ9.sZCzgnrw611SceKVWQbI4dIfMYMG9TC5Mcz4h6GgrNk', '2022-03-17 13:47:43', '2022-03-17 13:14:39');

SET FOREIGN_KEY_CHECKS = 1;
