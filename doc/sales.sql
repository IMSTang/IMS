

-- 一级菜单
insert into sys_menu values('100', 'Sales Management', '0', '30', '#', 'M', '0', '', 'fa fa-shopping-basket font12',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Sales Management');

-- 二级菜单
insert into sys_menu values('110',   'Customer', '100', '1', '/sales/customer',        'C', '0', 'sales:customer:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Customer');
insert into sys_menu values('120',   'Quote', '100', '2', '/sales/quote',        'C', '0', 'sales:quote:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Quote');


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
    quote_date     timestamp    not null                   comment 'Quote Date',
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



