-- 一级菜单
insert into sys_menu values('200', 'Purchase Management', '0', '40', '#', 'M', '0', '', 'fa fa-credit-card font12',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Purchase Management');
-- 二级菜单
insert into sys_menu values('210',   'Vendor', '200', '1', '/purchase/vendor',        'C', '0', 'purchase:vendor:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Vendor');
insert into sys_menu values('220',   'Inquiry', '200', '2', '/purchase/inquiry',        'C', '0', 'purchase:inquiry:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Inquiry');
insert into sys_menu values('230',   'List of product demand', '200', '3', '/purchase/demand',        'C', '0', 'purchase:demand:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'List of product demand');

-- Vendor 供应商管理按钮
insert into sys_menu values('211', 'QueryVendor', '210', '1',  '#',  'F', '0', 'purchase:vendor:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('212', 'AddVendor', '210', '2',  '#',  'F', '0', 'purchase:vendor:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('213', 'EditVendor', '210', '3',  '#',  'F', '0', 'purchase:vendor:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('214', 'RemoveVendor', '210', '4',  '#',  'F', '0', 'purchase:vendor:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('215', 'SaveVendor', '210', '5',  '#',  'F', '0', 'purchase:vendor:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('216', 'BatchRemove', '210', '6', '#',  'F', '0', 'purchase:vendor:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

-- Inquiry 询价管理按钮
insert into sys_menu values('221', 'QueryInquiry', '220', '1',  '#',  'F', '0', 'purchase:inquiry:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('222', 'AddInquiry', '220', '2',  '#',  'F', '0', 'purchase:inquiry:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('223', 'EditInquiry', '220', '3',  '#',  'F', '0', 'purchase:inquiry:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('224', 'RemoveInquiry', '220', '4',  '#',  'F', '0', 'purchase:inquiry:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('225', 'SaveInquiry', '220', '5',  '#',  'F', '0', 'purchase:inquiry:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('226', 'BatchRemove', '220', '6', '#',  'F', '0', 'purchase:inquiry:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

-- Product Demand 管理按钮
insert into sys_menu values('231', 'QueryDemand', '230', '1',  '#',  'F', '0', 'purchase:demand:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('232', 'AddDemand', '230', '2',  '#',  'F', '0', 'purchase:demand:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('233', 'EditDemand', '230', '3',  '#',  'F', '0', 'purchase:demand:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('234', 'RemoveDemand', '230', '4',  '#',  'F', '0', 'purchase:demand:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('235', 'SaveDemand', '230', '5',  '#',  'F', '0', 'purchase:demand:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('236', 'BatchRemove', '230', '6', '#',  'F', '0', 'purchase:demand:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

-- 将按钮权限赋予role
insert into sys_role_menu values ('1', '200');
insert into sys_role_menu values ('1', '210');
insert into sys_role_menu values ('1', '211');
insert into sys_role_menu values ('1', '212');
insert into sys_role_menu values ('1', '213');
insert into sys_role_menu values ('1', '214');
insert into sys_role_menu values ('1', '215');
insert into sys_role_menu values ('1', '216');
insert into sys_role_menu values ('1', '220');
insert into sys_role_menu values ('1', '221');
insert into sys_role_menu values ('1', '222');
insert into sys_role_menu values ('1', '223');
insert into sys_role_menu values ('1', '224');
insert into sys_role_menu values ('1', '225');
insert into sys_role_menu values ('1', '226');
insert into sys_role_menu values ('1', '231');
insert into sys_role_menu values ('1', '232');
insert into sys_role_menu values ('1', '233');
insert into sys_role_menu values ('1', '234');
insert into sys_role_menu values ('1', '235');
insert into sys_role_menu values ('1', '236');

-- ----------------------------
-- 210、供应商信息表
-- product_category: Amino_acids, Plant_Extracts, Minerals, Orotein_Powder

-- ----------------------------
drop table if exists pur_vendor;
create table pur_vendor
(
    vendor_id       int(11)         not null auto_increment    comment 'Vendor ID',
	vendor_name     varchar(100)    not null                   comment 'Vendor Name',
	product_category  varchar(100)  not null               comment 'product_category',
	first_name     varchar(40)     null                   comment 'First Name',
	last_name      varchar(40)     null                   comment 'Last Name',
	middle_name     varchar(40)     null                   comment 'Middle Name',
	name_title     varchar(10)     null                   comment 'Name title',
	job_title     varchar(50)     null                   comment 'JOB title',
	main_phone     varchar(50)     null                   comment 'Main Phone',
	work_phone     varchar(50)     null                   comment 'Work Phone',
	mobile         varchar(50)     null                   comment 'Mobile',
	fax            varchar(50)     null                   comment 'Fax',
	main_mail     varchar(100)     null                   comment 'Main mail',
	cc_mail     varchar(100)     null                   comment 'CC mail',
	address_ship_from     varchar(200)     null                   comment 'Address ship from',
	address_bill_from     varchar(200)     null                   comment 'Address bill from',
	status        int(1)          default 0                  comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (vendor_id)
) engine=innodb default charset=utf8;


-- ----------------------------
-- 220、询价表
-- ----------------------------
drop table if exists pur_inquiry;
create table pur_inquiry
(
    inquiry_id       int(11)      not null auto_increment    comment 'Inquiry ID',
    inquiry_date     varchar(10) not null                   comment 'Inquiry Date',
	vendor_id        int(11)      not null                   comment 'Vendor Id',
	reminder         int(10)      default 3                  comment 'Reminder',
	status        int(1)          default 0                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (inquiry_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 220、询价子表
-- ----------------------------
drop table if exists pur_inquiry_body;
create table pur_inquiry_body
(
    inquiry_body_id       int(11)      not null auto_increment    comment 'Inquiry body ID',
	inquiry_id     varchar(32)  not null                   comment 'Inquiry uuid',
	item_code     varchar(20)     not null                       comment 'Item Code',
	price     	  float(10,2)     not null                   comment 'Price',
	quantity      float(10,3)     not null                   comment 'Quantity',
	primary key (inquiry_body_id)
) engine=innodb default charset=utf8;


-- ----------------------------
-- 230、国内需备货产品
-- ----------------------------
drop table if exists pur_product_demand;
create table pur_product_demand
(
    demand_id       int(11)      not null auto_increment    comment 'demand ID',
    demand_date     varchar(10) not null               comment 'demand Date',
	vendor_id        int(11)      null                   comment 'Vendor Id',
	item_code     varchar(20)     not null                   comment 'Item Code',
	price     	  float(10,2)     null                   comment 'Price',
	quantity      float(10,3)     not null                   comment 'Quantity',
	urgency_degree  varchar(20)   not null                   comment 'Urgency degree',
	status        int(1)          default 0                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (demand_id)
) engine=innodb default charset=utf8;