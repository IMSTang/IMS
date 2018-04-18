

-- 一级菜单
insert into sys_menu values('100', 'Sales Management', '0', '5', '#', 'M', '0', '', 'fa fa-shopping-basket',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Sales Management');

-- 二级菜单
insert into sys_menu values('110',   'Customer', '100', '1', '/sales/customer',        'C', '0', 'sales:customer:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Customer');


-- 用户按钮
insert into sys_menu values('111', 'add', '110', '2',  '#',  'F', '0', 'sales:customer:add',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('112', 'delete', '110', '3',  '#',  'F', '0', 'sales:customer:batchRemove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('113', 'saveCustomer', '110', '4',  '#',  'F', '0', 'sales:customer:save',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('114', 'queryCustomer', '110', '1',  '#',  'F', '0', 'sales:customer:list',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('115', 'removeCustomer', '110', '5',  '#',  'F', '0', 'sales:customer:remove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('116', 'editCustomer', '110', '6',  '#',  'F', '0', 'sales:customer:edit',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');


-- 将按钮权限赋予role
insert into sys_role_menu values ('1', '100');
insert into sys_role_menu values ('1', '110');
insert into sys_role_menu values ('1', '111');
insert into sys_role_menu values ('1', '112');
insert into sys_role_menu values ('1', '113');
insert into sys_role_menu values ('1', '114');
insert into sys_role_menu values ('1', '115');
insert into sys_role_menu values ('1', '116');

/*
Navicat MySQL Data Transfer

Source Server         :  47.97.219.255
Source Server Version : 50639
Source Host           : 47.97.219.255:3306
Source Database       : ims

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-04-18 09:37:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sale_customer
-- ----------------------------
DROP TABLE IF EXISTS `sale_customer`;
CREATE TABLE `sale_customer` (
  `customer_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
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
  `create_by` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_customer
-- ----------------------------
INSERT INTO `sale_customer` VALUES ('1', 'jack', '0', 'aa', 'bbssd', 'cc', 'dd', 'tester', '123456', '123456', '188888222', '1125565', '12122@qq.com', '121454@163.com', 'adsd', 'kkk', 'admin', '2018-04-18 09:36:03', 'admin', '2018-04-18 09:37:09', 'admin111111');
INSERT INTO `sale_customer` VALUES ('3', 'tom', '0', 'cat', 'tom', 'state', 'tom', 'manager', '1122365', '112555', '1222554', '1222554', '1225555@.123', '232556@qq.com', 'ass', 'aad', 'bb', '2018-04-17 15:52:56', 'admin', '2018-04-17 15:52:56', 'my');
INSERT INTO `sale_customer` VALUES ('10', 'sdssd', '0', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'wewe@qsq', 's', 'ss', 's', 'aa', '2018-04-17 15:52:54', 'admin', '2018-04-17 15:52:54', 'admin');
