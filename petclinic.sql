/*
 Navicat Premium Data Transfer

 Source Server         : Navicat_MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : petclinic

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 01/05/2021 21:54:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointments
-- ----------------------------
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `start` datetime(0) NULL DEFAULT NULL,
  `end` datetime(0) NULL DEFAULT NULL,
  `canceled_at` datetime(0) NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_canceler` int(0) NULL DEFAULT NULL,
  `id_provider` int(0) NULL DEFAULT NULL,
  `id_customer` int(0) NULL DEFAULT NULL,
  `id_invoice` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_canceler`(`id_canceler`) USING BTREE,
  INDEX `id_provider`(`id_provider`) USING BTREE,
  INDEX `id_customer`(`id_customer`) USING BTREE,
  INDEX `id_invoice`(`id_invoice`) USING BTREE,
  CONSTRAINT `appointments_invoices` FOREIGN KEY (`id_invoice`) REFERENCES `invoices` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appointments_users_canceler` FOREIGN KEY (`id_canceler`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appointments_users_customer` FOREIGN KEY (`id_customer`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `appointments_users_provider` FOREIGN KEY (`id_provider`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointments
-- ----------------------------

-- ----------------------------
-- Table structure for invoices
-- ----------------------------
DROP TABLE IF EXISTS `invoices`;
CREATE TABLE `invoices`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `number` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_amount` decimal(10, 2) NULL DEFAULT NULL,
  `issued` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invoices
-- ----------------------------

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `id_author` int(0) NULL DEFAULT NULL,
  `id_appointment` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_author`(`id_author`) USING BTREE,
  INDEX `id_appointment`(`id_appointment`) USING BTREE,
  CONSTRAINT `FK_notes_appointment` FOREIGN KEY (`id_appointment`) REFERENCES `appointments` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_notes_author` FOREIGN KEY (`id_author`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages
-- ----------------------------

-- ----------------------------
-- Table structure for notifications
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_read` tinyint(1) NULL DEFAULT NULL,
  `id_user` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_user`(`id_user`) USING BTREE,
  CONSTRAINT `FK_notification_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notifications
-- ----------------------------

-- ----------------------------
-- Table structure for owners
-- ----------------------------
DROP TABLE IF EXISTS `owners`;
CREATE TABLE `owners`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telephone_UNIQUE`(`telephone`) USING BTREE,
  INDEX `last_name`(`last_name`) USING BTREE,
  INDEX `user_id_foreign_idx`(`user_id`) USING BTREE,
  CONSTRAINT `user_id_foreign_key` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owners
-- ----------------------------
INSERT INTO `owners` VALUES (1, 'abc', 'abc', 'abc', 'abc', '5689542399', NULL);
INSERT INTO `owners` VALUES (5, 'q', 'wq', 'qw', 'qw', 'q', NULL);
INSERT INTO `owners` VALUES (6, 'qer', 'er', 'er', 'wer', '34', NULL);
INSERT INTO `owners` VALUES (10, 'Aquib', 'Haider', 'Taloja Sector -34', 'Kharghar', '9999999999', NULL);

-- ----------------------------
-- Table structure for pets
-- ----------------------------
DROP TABLE IF EXISTS `pets`;
CREATE TABLE `pets`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birth_date` date NULL DEFAULT NULL,
  `owner_id` int(0) UNSIGNED NOT NULL,
  `type_id` int(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `NAME`(`NAME`) USING BTREE,
  INDEX `owner_id`(`owner_id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE,
  CONSTRAINT `pets_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owners` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pets_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pets
-- ----------------------------
INSERT INTO `pets` VALUES (2, 'lil2', '2021-12-13', 10, 2);
INSERT INTO `pets` VALUES (3, 'asd', '2021-12-12', 10, 1);
INSERT INTO `pets` VALUES (4, 'dghfzdfh', '2021-12-30', 10, 1);
INSERT INTO `pets` VALUES (5, 'abc pet', '2012-12-21', 1, 1);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `active` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'Role_ADMIN', 'Y');
INSERT INTO `roles` VALUES (2, 'Role_USER', 'Y');

-- ----------------------------
-- Table structure for specialities
-- ----------------------------
DROP TABLE IF EXISTS `specialities`;
CREATE TABLE `specialities`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `NAME`(`DESCRIPTION`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of specialities
-- ----------------------------
INSERT INTO `specialities` VALUES (1, 'Animal behavior');
INSERT INTO `specialities` VALUES (5, 'Birds');
INSERT INTO `specialities` VALUES (4, 'Canine');
INSERT INTO `specialities` VALUES (3, 'Cardiology');
INSERT INTO `specialities` VALUES (2, 'Dentistry');
INSERT INTO `specialities` VALUES (6, 'Nutrition');
INSERT INTO `specialities` VALUES (7, 'Surgery');

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NAME` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `NAME`(`NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES (2, 'Cat');
INSERT INTO `types` VALUES (1, 'Dog');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `active` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Y',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'USER',
  `reset_password_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UKob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE,
  UNIQUE INDEX `user_name_UNIQUE`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin@gmail.com', 'admin', NULL, NULL, '$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe', 'Y', 'ROLE_ADMIN', NULL);
INSERT INTO `users` VALUES (2, 'vetclinictest@gmail.com', 'user', NULL, NULL, '$2a$10$K2FCWtMvmOA8MWbKItop6OdTROE2P4JFqf2avrNH0I1meum/5CskO', 'Y', 'ROLE_USER', 'RCKuCYEV0Qi7IH1iK8ySB0dXqdvKVD');
INSERT INTO `users` VALUES (3, 'sGD@gmail.com', 'sdg', NULL, NULL, '$2a$10$HypIJ7T3ip9vkvyq1AVeDOzKq81Zs9y0pqcDAk4gHBjMReq.AjsLy', 'Y', 'ADMIN', NULL);
INSERT INTO `users` VALUES (4, 'test@gmail.com', 'test', NULL, NULL, '$2a$10$diTj3ZQfVrUkO.V/wMKax.Zs07MaC/gGOQKsgBxY.RpgjKSjo.ybC', 'Y', 'ADMIN', NULL);
INSERT INTO `users` VALUES (5, 'asd@gmail.com', 'asd', NULL, NULL, '$2a$10$6jgGmDoS1XeXXMHrmv923O9vp058WDgoAzq9F/W.T82mMaeoHsd26', 'Y', 'ADMIN', 'GfUWBLHKTnsL3EkQ0ReOw0GmucmFV4');
INSERT INTO `users` VALUES (6, 'asdfg@gmail.com', 'afdsg', NULL, NULL, '$2a$10$0uhACOKfCabTvliF5QJeQ.Ge8PPiovzxnQMYsFuhDwAwdyAvZcUTG', 'Y', 'ROLE_ADMIN', NULL);

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  `active` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N',
  INDEX `FKt4v0rrweyk393bdgt107vdx0x`(`role_id`) USING BTREE,
  INDEX `FKgd3iendaoyh04b95ykqise6qh`(`user_id`) USING BTREE,
  CONSTRAINT `role_id_foreing_key_sers_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id_foreign_key_users_roles` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1, 'Y');

-- ----------------------------
-- Table structure for vet_specialities
-- ----------------------------
DROP TABLE IF EXISTS `vet_specialities`;
CREATE TABLE `vet_specialities`  (
  `vet_id` int(0) UNSIGNED NOT NULL,
  `speciality_id` int(0) UNSIGNED NOT NULL,
  UNIQUE INDEX `vet_id`(`vet_id`, `speciality_id`) USING BTREE,
  UNIQUE INDEX `unq_vat_speciality`(`speciality_id`, `vet_id`) USING BTREE,
  CONSTRAINT `vet_specialities_ibfk_1` FOREIGN KEY (`vet_id`) REFERENCES `vets` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vet_specialities_ibfk_2` FOREIGN KEY (`speciality_id`) REFERENCES `specialities` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vet_specialities
-- ----------------------------
INSERT INTO `vet_specialities` VALUES (1, 1);
INSERT INTO `vet_specialities` VALUES (1, 2);
INSERT INTO `vet_specialities` VALUES (1, 4);
INSERT INTO `vet_specialities` VALUES (2, 3);
INSERT INTO `vet_specialities` VALUES (2, 5);

-- ----------------------------
-- Table structure for vets
-- ----------------------------
DROP TABLE IF EXISTS `vets`;
CREATE TABLE `vets`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `last_name`(`last_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vets
-- ----------------------------
INSERT INTO `vets` VALUES (1, 'Aquib', 'Haider');
INSERT INTO `vets` VALUES (2, 'Kunal', 'Choramale');

-- ----------------------------
-- Table structure for visits
-- ----------------------------
DROP TABLE IF EXISTS `visits`;
CREATE TABLE `visits`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `visit_date` date NOT NULL,
  `pet_id` int(0) NOT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pet_id`(`pet_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visits
-- ----------------------------
INSERT INTO `visits` VALUES (1, '2020-12-21', 4, 'Visit done');
INSERT INTO `visits` VALUES (2, '2020-12-25', 4, 'afafd');
INSERT INTO `visits` VALUES (4, '2020-12-12', 3, 'sfaf');
INSERT INTO `visits` VALUES (5, '2021-04-30', 5, 'abc pet dog visit done.');

SET FOREIGN_KEY_CHECKS = 1;
