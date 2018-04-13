-- 一级菜单
insert into sys_menu values('200', 'Purchase Management', '0', '3', '#', 'M', '0', '', 'fa fa-bars',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Purchase Management');
-- 二级菜单
insert into sys_menu values('210',   'Vendor', '200', '1', '/purchase/vendor',        'C', '0', 'purchase:vendor:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Vendor');
insert into sys_menu values('220',   'Inquiry', '200', '2', '/purchase/inquiry',        'C', '0', 'purchase:inquiry:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Inquiry');
insert into sys_menu values('230',   'List of product demand', '200', '3', '/purchase/product_demand',        'C', '0', 'purchase:product_demand:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'List of product demand');

-- Vendor 供应商管理按钮
insert into sys_menu values('211', 'QueryVendor', '210', '1',  '#',  'F', '0', 'purchase:vendor:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('212', 'AddVendor', '210', '2',  '#',  'F', '0', 'purchase:vendor:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('213', 'EditVendor', '210', '3',  '#',  'F', '0', 'purchase:vendor:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('214', 'RemoveVendor', '210', '4',  '#',  'F', '0', 'purchase:vendor:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('215', 'SaveVendor', '210', '5',  '#',  'F', '0', 'purchase:vendor:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('216', 'BatchRemove', '210', '6', '#',  'F', '0', 'purchase:vendor:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');



-- ----------------------------
-- 210、供应商信息表
-- ----------------------------
drop table if exists pur_vendor;
create table pur_vendor
(
    vendor_id       int(11)         not null auto_increment    comment 'Vendor ID',
	vendor_name     varchar(100)    not null                   comment 'Vendor Name',
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
	status        int(1)          not null                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (vendor_id)
) engine=innodb default charset=utf8;

