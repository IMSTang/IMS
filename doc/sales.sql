

-- 一级菜单
insert into sys_menu values('100', 'Sales Management', '0', '5', '#', 'M', '0', '', 'fa fa-bars',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Sales Management');

-- 二级菜单
insert into sys_menu values('101',   'Customer', '100', '1', '/sales/customer',        'C', '0', 'sales:customer::view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Customer');


-- 用户按钮
insert into sys_menu values('102', 'add', '101', '2',  '#',  'F', '0', 'sales:customer:add',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('103', 'delete', '101', '3',  '#',  'F', '0', 'sales:customer:batchRemove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');



-- 将按钮权限赋予role
insert into sys_role_menu values ('1', '102');
insert into sys_role_menu values ('1', '103');





/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : feng

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-14 19:32:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sale_customer
-- ----------------------------
DROP TABLE IF EXISTS `sale_customer`;
CREATE TABLE `sale_customer` (
  `customer_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(100) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0正常,1删除',
  `first_name` varchar(40) DEFAULT NULL,
  `last_name` varchar(40) DEFAULT NULL,
  `middle_name` varchar(40) DEFAULT NULL,
  `name_title` varchar(10) DEFAULT NULL,
  `job_title` varchar(50) DEFAULT NULL,
  `main_phone` varchar(50) DEFAULT NULL,
  `work_phone` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `main_mail` varchar(100) DEFAULT NULL,
  `cc_mail` varchar(100) DEFAULT NULL,
  `address_ship_to` varchar(200) DEFAULT NULL,
  `address_bill_to` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_customer
-- ----------------------------
INSERT INTO `sale_customer` VALUES ('00000000002', 'jack', '0', 'aa', 'bb', 'cc', 'dd', 'tester', '123456', '123456', '188888222', '1125565', '12122@qq.com', '121454@163.com', 'adsd', 'kkk', 'jjj', 'admin', '2018-04-14 19:24:50', 'admin', '2018-04-14 19:24:50', 'admin');
INSERT INTO `sale_customer` VALUES ('00000000003', 'tom', '1', 'cat', 'tom', 'state', 'tom', 'manager', '1122365', '112555', '1222554', '1222554', '1225555@.123', '232556@qq.com', 'ass', 'aad', 'das', 'admin', '2018-04-14 17:21:28', 'admin', '2018-04-14 17:21:28', 'admin');
