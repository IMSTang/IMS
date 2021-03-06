-- 一级菜单
insert into sys_menu values('400', 'Inventory Management', '0', '20', '#', 'M', '0', '', 'fa fa-archive font12',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Inventory Management');
-- 二级菜单
insert into sys_menu values('410',   'Stock In', '400', '1', '/inventory/inStock',        'C', '0', 'inventory:inStock:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'In Stock');
insert into sys_menu values('420',   'Stock Out', '400', '2', '/inventory/outStock',        'C', '0', 'inventory:outStock:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Out Stock');
insert into sys_menu values('430',   'Inventory List', '400', '3', '/inventory/queryinventory',        'C', '0', 'inventory:queryinventory:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Inventory');
insert into sys_menu values('440',   'Sell Return', '400', '4', '/inventory/salesReturn',        'C', '0', 'inventory:salesReturn:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'resell Stock');
insert into sys_menu values('450',   'Replenishment', '400', '5', '/inventory/queryproduct',        'C', '0', 'inventory:queryproduct:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'QueryProduct');

insert into sys_menu values('411', 'Query', '410', '1',  '#',  'F', '0', 'inventory:inStock:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('412', 'Add', '410', '2',  '#',  'F', '0', 'inventory:inStock:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- insert into sys_menu values('413', 'Edit', '410', '3',  '#',  'F', '0', 'inventory:inStock:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('414', 'Remove', '410', '4',  '#',  'F', '0', 'inventory:inStock:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('415', 'Save', '410', '5',  '#',  'F', '0', 'inventory:inStock:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('416', 'Detail', '410', '3',  '#',  'F', '0', 'inventory:inStock:detail',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

insert into sys_menu values('421', 'Query', '420', '1',  '#',  'F', '0', 'inventory:outStock:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('422', 'Add', '420', '2',  '#',  'F', '0', 'inventory:outStock:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- insert into sys_menu values('423', 'Edit', '420', '3',  '#',  'F', '0', 'inventory:outStock:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('424', 'Remove', '420', '4',  '#',  'F', '0', 'inventory:outStock:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('425', 'Save', '420', '5',  '#',  'F', '0', 'inventory:outStock:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('426', 'Detail', '420', '3',  '#',  'F', '0', 'inventory:outStock:detail',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');


insert into sys_menu values('431', 'Query', '430', '1',  '#',  'F', '0', 'inventory:queryinventory:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('432', 'Detail', '430', '1',  '#',  'F', '0', 'inventory:queryinventory:detail',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

insert into sys_menu values('451', 'Query', '450', '1',  '#',  'F', '0', 'inventory:queryproduct:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('452', 'BatchDemand', '450', '2', '#',  'F', '0', 'inventory:queryproduct:batchDemand',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');


insert into sys_menu values('441', 'Query', '440', '1',  '#',  'F', '0', 'inventory:salesReturn:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('442', 'Add', '440', '2',  '#',  'F', '0', 'inventory:salesReturn:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- insert into sys_menu values('443', 'Edit', '440', '3',  '#',  'F', '0', 'inventory:salesReturn:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('444', 'Remove', '440', '4',  '#',  'F', '0', 'inventory:salesReturn:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('445', 'Save', '440', '5',  '#',  'F', '0', 'inventory:salesReturn:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('446', 'Detail', '440', '3',  '#',  'F', '0', 'inventory:salesReturn:detail',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

-- 将按钮权限赋予role
insert into sys_role_menu values ('1', '400');
insert into sys_role_menu values ('1', '410');
insert into sys_role_menu values ('1', '411');
insert into sys_role_menu values ('1', '412');
insert into sys_role_menu values ('1', '414');
insert into sys_role_menu values ('1', '415');
insert into sys_role_menu values ('1', '416');
insert into sys_role_menu values ('1', '420');
insert into sys_role_menu values ('1', '421');
insert into sys_role_menu values ('1', '422');
insert into sys_role_menu values ('1', '424');
insert into sys_role_menu values ('1', '425');
insert into sys_role_menu values ('1', '426');
insert into sys_role_menu values ('1', '430');
insert into sys_role_menu values ('1', '431');
insert into sys_role_menu values ('1', '432');
insert into sys_role_menu values ('1', '440');
insert into sys_role_menu values ('1', '441');
insert into sys_role_menu values ('1', '442');
insert into sys_role_menu values ('1', '444');
insert into sys_role_menu values ('1', '445');
insert into sys_role_menu values ('1', '446');


insert into sys_role_menu values ('1', '450');
insert into sys_role_menu values ('1', '451');
insert into sys_role_menu values ('1', '452');



drop table if exists inv_inventory_in;
create table inv_inventory_in
(
    sn int(11) unsigned NOT NULL AUTO_INCREMENT,
	item_code     varchar(20)    not null                   comment 'Item Code',
  batch     varchar(100)    not null        ,
    warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  price_purchase  double(16,2)  not null        ,
  price_fob_ontario  double(16,2)  not null        ,
  quantity   double(16,3)  not null ,
	irradiation     varchar(100)     null  ,
  tpc     varchar(100)     null  ,
  vendor_id     int(11)    not null  ,
  customer_id    int(11)     null  ,
	status        int(1)          not null                   comment '状态（0正常 1停用）',
	stock_in_date  timestamp       default current_timestamp  comment '创建时间',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (sn)
) engine=innodb default charset=utf8;

drop table if exists inv_inventory_out;
create table inv_inventory_out
(
   sn int(11) unsigned NOT NULL AUTO_INCREMENT,
	item_code     varchar(20)    not null                   comment 'Item Code',
  inventory_sn     int(11)    not null  ,
  batch     varchar(100)    not null        ,
  warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  quantity   double(16,3)  not null      ,
  price_sale     double(16,2)  null      ,
  po_code     varchar(20)    null      ,
	irradiation     varchar(100)     null  ,
  tpc     varchar(100)     null  ,
  vendor_id     int(11)    not null  ,
  customer_id    int(11)     null  ,
	status        int(1)          not null                   comment '状态（0正常 1停用）',
	stockout_date  varchar(10)     default ''        comment '出库时间',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (sn)
) engine=innodb default charset=utf8;



drop table if exists inv_sales_return;
create table inv_sales_return
(
	  sn int(11) unsigned NOT NULL AUTO_INCREMENT,
	item_code     varchar(20)    not null                   comment 'Item Code',
  stockout_sn     int(11)    not null  ,
  batch     varchar(100)    not null        ,
  warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  quantity   double(16,3)  not null ,
	irradiation     varchar(100)     null  ,
  tpc     varchar(100)     null  ,
  vendor_id     int(11)    not null  ,
  customer_id    int(11)     null  ,
	status        int(1)          not null                   comment '状态（0正常 1停用）',
	return_date  varchar(10)     default ''        comment '出库时间',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (sn)
) engine=innodb default charset=utf8;



drop table if exists inv_inventory;
create table inv_inventory
(
	sn int(11) unsigned NOT NULL AUTO_INCREMENT,
	item_code     varchar(20)    not null                   comment 'Item Code',
  batch     varchar(100)    not null        ,
  warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  price_purchase  double(16,2)  not null        ,
  price_fob_ontario  double(16,2)  not null        ,
  quantity   double(16,3)  not null ,
	irradiation     varchar(100)     null  ,
  tpc     varchar(100)     null  ,
  vendor_id     int(11)    not null  ,
  customer_id    int(11)     null  ,
	status        int(1)          not null                   comment '状态（0正常 1停用）',
	stock_in_date  timestamp       default current_timestamp  comment '创建时间',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (sn)
) engine=innodb default charset=utf8;



drop table if exists sys_attachment;
create table sys_attachment
(
  attachment_id int(11) unsigned NOT NULL AUTO_INCREMENT,
	attachment_name     varchar(100)    not null        ,
  attachment_uuid     varchar(100)    not null        ,
  main_sn  int(11)    not null,
  main_type varchar(100) not null,
  create_by     varchar(64)     default ''                 comment '创建者',
  create_time   timestamp       default current_timestamp  comment '创建时间',
  update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
  remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (attachment_id)
) engine=innodb default charset=utf8;
