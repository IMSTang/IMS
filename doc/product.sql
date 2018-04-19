-- 一级菜单
insert into sys_menu values('300', 'Product Management', '0', '6', '#', 'M', '0', '', 'fa fa-cubes',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Product Management');
-- 二级菜单
insert into sys_menu values('310',   'Production', '300', '1', '/product/production',        'C', '0', 'product:production:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Production');
insert into sys_menu values('320',   'Production mapping', '300', '2', '/product/productionMapping',        'C', '0', 'product:productionMapping:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Production Mapping');


insert into sys_menu values('311', 'Query', '310', '1',  '#',  'F', '0', 'product:production:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('312', 'Add', '310', '2',  '#',  'F', '0', 'product:production:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('313', 'Edit', '310', '3',  '#',  'F', '0', 'product:production:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('314', 'Remove', '310', '4',  '#',  'F', '0', 'product:production:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('315', 'Save', '310', '5',  '#',  'F', '0', 'product:production:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('316', 'BatchRemove', '310', '6', '#',  'F', '0', 'product:production:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

insert into sys_menu values('321', 'Query', '310', '1',  '#',  'F', '0', 'product:productionMapping:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('322', 'Add', '310', '2',  '#',  'F', '0', 'product:productionMapping:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('323', 'Edit', '310', '3',  '#',  'F', '0', 'product:productionMapping:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('324', 'Remove', '310', '4',  '#',  'F', '0', 'product:productionMapping:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('325', 'Save', '310', '5',  '#',  'F', '0', 'product:productionMapping:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('326', 'BatchRemove', '310', '6', '#',  'F', '0', 'product:productionMapping:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');



insert into sys_role_menu values ('1', '300');
insert into sys_role_menu values ('1', '310');
insert into sys_role_menu values ('1', '311');
insert into sys_role_menu values ('1', '312');
insert into sys_role_menu values ('1', '313');
insert into sys_role_menu values ('1', '314');
insert into sys_role_menu values ('1', '315');
insert into sys_role_menu values ('1', '316');
insert into sys_role_menu values ('1', '320');
insert into sys_role_menu values ('1', '321');
insert into sys_role_menu values ('1', '322');
insert into sys_role_menu values ('1', '323');
insert into sys_role_menu values ('1', '324');
insert into sys_role_menu values ('1', '325');
insert into sys_role_menu values ('1', '326');


-- ----------------------------
-- 210、供应商信息表
-- ----------------------------
drop table if exists pro_production;
create table pro_production
(
  production_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  item_code       varchar(100)        not null     comment 'item_code',
	item_name       varchar(100)    not null                   comment 'item_name',
	item_name_cn     varchar(100)     null                   comment 'item_name_cn',
	product_category      varchar(100)     null                   comment 'product_category',
	specification    varchar(200)     null                   comment 'specification',
	specification_cn     varchar(200)     null                   comment 'specification cn',
	test_method     varchar(100)     null                   comment 'test_method',
	place_of_origin     varchar(100)     null                   comment 'place_of_origin',
	efficiency     varchar(50)     null                   comment 'efficiency',
	safety_stock        int(10)     null                   comment '最低补货库存量',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
    status     int(1) NOT NULL DEFAULT '0' COMMENT '0正常,1删除',
	primary key (production_id)
) engine=innodb default charset=utf8;

drop table if exists pro_production_mapping;
create table pro_production_mapping
(
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  item_code       varchar(100)        not null     comment 'item_code',
	item_name       varchar(100)    not null                   comment 'item_name',
	new_item_code       varchar(100)        not null     comment 'new item_code',
	new_item_name       varchar(100)    not null                   comment 'item_name',
  rate                 varchar(100)    not null                   comment '换算比例',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
    status     int(1) NOT NULL DEFAULT '0' COMMENT '0正常,1删除',
	primary key (id)

) engine=innodb default charset=utf8;