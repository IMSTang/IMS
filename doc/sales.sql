

-- 一级菜单
insert into sys_menu values('100', 'Sales Management', '0', '30', '#', 'M', '0', '', 'fa fa-shopping-basket font12',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Sales Management');

-- 二级菜单
insert into sys_menu values('110',   'Customer', '100', '1', '/sales/customer',        'C', '0', 'sales:customer:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Customer');
insert into sys_menu values('120',   'Quote', '100', '2', '/sales/quote',        'C', '0', 'sales:quote:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Quote');
insert into sys_menu values('130',   'Sample', '100', '2', '/sales/sample',        'C', '0', 'sales:sample:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Sample');


-- 用户按钮
insert into sys_menu values('111', 'add', '110', '2',  '#',  'F', '0', 'sales:customer:add',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('112', 'delete', '110', '3',  '#',  'F', '0', 'sales:customer:batchRemove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('113', 'saveCustomer', '110', '4',  '#',  'F', '0', 'sales:customer:save',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('114', 'queryCustomer', '110', '1',  '#',  'F', '0', 'sales:customer:list',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('115', 'removeCustomer', '110', '5',  '#',  'F', '0', 'sales:customer:remove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('116', 'editCustomer', '110', '6',  '#',  'F', '0', 'sales:customer:edit',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');


insert into sys_menu values('121', 'addQuote', '120', '2',  '#',  'F', '0', 'sales:quote:add',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('122', 'deleteQuote', '120', '3',  '#',  'F', '0', 'sales:quote:batchRemove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('123', 'saveQuote', '120', '4',  '#',  'F', '0', 'sales:quote:save',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('124', 'queryQuote', '120', '1',  '#',  'F', '0', 'sales:quote:list',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('125', 'removeQuote', '120', '5',  '#',  'F', '0', 'sales:quote:remove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('126', 'editQuote', '120', '6',  '#',  'F', '0', 'sales:quote:edit',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');




insert into sys_menu values('131', 'addSample', '130', '2',  '#',  'F', '0', 'sales:sample:add',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('132', 'deleteSample', '130', '3',  '#',  'F', '0', 'sales:sample:batchRemove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('133', 'saveSample', '130', '4',  '#',  'F', '0', 'sales:sample:save',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('134', 'querySample', '130', '1',  '#',  'F', '0', 'sales:sample:list',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('135', 'removeSample', '130', '5',  '#',  'F', '0', 'sales:sample:remove',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('136', 'editSample', '130', '6',  '#',  'F', '0', 'sales:sample:edit',   '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

-- 将按钮权限赋予role
insert into sys_role_menu values ('1', '100');
insert into sys_role_menu values ('1', '110');
insert into sys_role_menu values ('1', '111');
insert into sys_role_menu values ('1', '112');
insert into sys_role_menu values ('1', '113');
insert into sys_role_menu values ('1', '114');
insert into sys_role_menu values ('1', '115');
insert into sys_role_menu values ('1', '116');

insert into sys_role_menu values ('1', '120');
 insert into sys_role_menu values ('1', '121');
insert into sys_role_menu values ('1', '122');
insert into sys_role_menu values ('1', '123');
insert into sys_role_menu values ('1', '124');
insert into sys_role_menu values ('1', '125');
insert into sys_role_menu values ('1', '126');



insert into sys_role_menu values ('1', '130');
insert into sys_role_menu values ('1', '131');
insert into sys_role_menu values ('1', '132');
insert into sys_role_menu values ('1', '133');
insert into sys_role_menu values ('1', '134');
insert into sys_role_menu values ('1', '135');
insert into sys_role_menu values ('1', '136');
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
INSERT INTO `sale_customer` VALUES ('10', 'aaa', '0', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'wewe@qsq', 's', 'ss', 's', 'aa', '2018-04-17 15:52:54', 'admin', '2018-04-17 15:52:54', 'admin');







-- ----------------------------
-- quote table
-- ----------------------------

drop table if exists sale_quote;
create table sale_quote
(
    quote_id       int(11)      not null auto_increment    comment 'Quote ID',
    quote_date     varchar(10) DEFAULT NULL                comment 'Quote Date',
	customer_id        int(11)      not null                   comment 'Customer Id',
	reminder         int(10)      default 3                  comment 'Reminder',
	status        int(1)           default 0                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (quote_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 220、询价子表
-- ----------------------------
drop table if exists sale_quote_body;
create table sale_quote_body
(
    quote_body_id       int(11)      not null auto_increment    comment 'Quote body ID',
	quote_id     varchar(32)  not null                   comment 'Quote uuid',
	item_code     varchar(20)     not null                       comment 'Item Code',
	price     	  float(10,2)     not null                   comment 'Price',
	quantity      float(10,3)     not null                   comment 'Quantity',
	primary key (quote_body_id)
) engine=innodb default charset=utf8;




INSERT INTO `sale_quote` VALUES ('1', '2018-04-19', '3', '21', '0', 'admin', '2018-04-18 16:52:21', '', '2018-04-18 16:52:21', 'ad');
INSERT INTO `sale_quote` VALUES ('2', '2018-04-18', '1', '12', '0', 'admin', '2018-04-18 16:53:28', '', '2018-04-18 16:53:28', '12');
INSERT INTO `sale_quote` VALUES ('19', '2018-04-23', '22', '1', '0', 'admin', '2018-04-23 21:42:42', '', '2018-04-23 21:42:42', 'as');
INSERT INTO `sale_quote` VALUES ('21', '2018-04-24', '1', '3', '1', 'admin', '2018-04-24 13:46:43', '', '2018-04-24 13:46:43', '');
INSERT INTO `sale_quote` VALUES ('22', '2018-04-24', '23', '3', '0', 'admin', '2018-04-24 09:24:57', '', '2018-04-24 09:24:57', '12');
INSERT INTO `sale_quote` VALUES ('23', '2018-04-24', '22', '3', '1', 'admin', '2018-04-24 14:49:08', '', '2018-04-24 14:49:08', '123');
INSERT INTO `sale_quote` VALUES ('24', '2018-04-24', '22', '3', '0', 'admin', '2018-04-24 17:07:47', '', '2018-04-24 17:07:47', 'aaa');


INSERT INTO `sale_quote_body` VALUES ('1', '1', 'ASP101', '12.00', '121.000');
INSERT INTO `sale_quote_body` VALUES ('2', '2', 'EE101', '1212.00', '11.000');
INSERT INTO `sale_quote_body` VALUES ('8', '19', 'ASP101', '1.00', '1.000');
INSERT INTO `sale_quote_body` VALUES ('9', '19', 'EE101', '1.00', '1.000');
INSERT INTO `sale_quote_body` VALUES ('14', '22', 'DeE101', '2.00', '1.000');
INSERT INTO `sale_quote_body` VALUES ('15', '22', 'EE101', '3.00', '2.000');
INSERT INTO `sale_quote_body` VALUES ('18', '24', 'DeE101', '1.00', '2.000');
INSERT INTO `sale_quote_body` VALUES ('19', '24', 'EE(GW41(41)', '4.00', '3.000');





-- ----------------------------
-- sample table
-- ----------------------------

drop table if exists sale_sample;
create table sale_sample
(
    sample_id       int(11)      not null auto_increment    comment 'Sample ID',
    sample_date    varchar(10)  DEFAULT NULL                  comment 'Sample Date',
	customer_id        int(11)      not null                   comment 'Customer Id',
	reminder         int(10)      default 3                  comment 'Reminder',
	status        int(1)           default 0                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (sample_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 220、询价子表
-- ----------------------------
drop table if exists sale_sample_body;
create table sale_sample_body
(
    sample_body_id       int(11)      not null auto_increment    comment 'Sample body ID',
	sample_id     varchar(32)  not null                   comment 'Sample uuid',
	item_code     varchar(20)     not null                       comment 'Item Code',
	price     	  float(10,2)     not null                   comment 'Price',
	quantity      float(10,0)     not null                   comment 'Quantity(g)',
	primary key (sample_body_id)
) engine=innodb default charset=utf8;




INSERT INTO `sale_sample` VALUES ('1', '2018-04-19', '3', '21', '0', 'admin', '2018-04-18 16:52:21', '', '2018-04-18 16:52:21', 'ad');
INSERT INTO `sale_sample` VALUES ('2', '2018-04-18', '1', '12', '0', 'admin', '2018-04-18 16:53:28', '', '2018-04-18 16:53:28', '12');
INSERT INTO `sale_sample` VALUES ('19', '2018-04-23', '22', '1', '0', 'admin', '2018-04-23 21:42:42', '', '2018-04-23 21:42:42', 'as');
INSERT INTO `sale_sample` VALUES ('21', '2018-04-24', '1', '3', '1', 'admin', '2018-04-24 13:46:43', '', '2018-04-24 13:46:43', '');
INSERT INTO `sale_sample` VALUES ('22', '2018-04-24', '23', '3', '0', 'admin', '2018-04-24 09:24:57', '', '2018-04-24 09:24:57', '12');
INSERT INTO `sale_sample` VALUES ('23', '2018-04-24', '22', '3', '1', 'admin', '2018-04-24 14:49:08', '', '2018-04-24 14:49:08', '123');
INSERT INTO `sale_sample` VALUES ('24', '2018-04-24', '22', '3', '0', 'admin', '2018-04-24 17:07:47', '', '2018-04-24 17:07:47', 'aaa');


INSERT INTO `sale_sample_body` VALUES ('1', '1', 'ASP101', '12.00', '121.000');
INSERT INTO `sale_sample_body` VALUES ('2', '2', 'EE101', '1212.00', '11.000');
INSERT INTO `sale_sample_body` VALUES ('8', '19', 'ASP101', '1.00', '1.000');
INSERT INTO `sale_sample_body` VALUES ('9', '19', 'EE101', '1.00', '1.000');
INSERT INTO `sale_sample_body` VALUES ('14', '22', 'DeE101', '2.00', '1.000');
INSERT INTO `sale_sample_body` VALUES ('15', '22', 'EE101', '3.00', '2.000');
INSERT INTO `sale_sample_body` VALUES ('18', '24', 'DeE101', '1.00', '2.000');
INSERT INTO `sale_sample_body` VALUES ('19', '24', 'EE(GW41(41)', '4.00', '3.000');
