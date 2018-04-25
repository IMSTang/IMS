-- 一级菜单
insert into sys_menu values('400', 'Inventory Management', '0', '7', '#', 'M', '0', '', 'fa fa-bars',         'admin', '2018-03-01', 'admin', '2018-03-01', 'Inventory Management');
-- 二级菜单
insert into sys_menu values('410',   'In Stock', '400', '1', '/inventory/inStock',        'C', '0', 'inventory:inStock:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'In Stock');
insert into sys_menu values('420',   'Out Stock', '400', '2', '/inventory/outStock',        'C', '0', 'inventory:outStock:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Out Stock');
insert into sys_menu values('430',   'Inventory', '400', '3', '/inventory/queryinventory',        'C', '0', 'inventory:queryinventory:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'Inventory');
insert into sys_menu values('440',   'Resell Stock', '400', '4', '/inventory/resellStock',        'C', '0', 'inventory:resellStock:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', 'resell Stock');


insert into sys_menu values('411', 'Query', '410', '1',  '#',  'F', '0', 'inventory:inStock:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('412', 'Add', '410', '2',  '#',  'F', '0', 'inventory:inStock:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('413', 'Edit', '410', '3',  '#',  'F', '0', 'inventory:inStock:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('414', 'Remove', '410', '4',  '#',  'F', '0', 'inventory:inStock:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('415', 'Save', '410', '5',  '#',  'F', '0', 'inventory:inStock:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

insert into sys_menu values('421', 'Query', '420', '1',  '#',  'F', '0', 'inventory:outStock:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('422', 'Add', '420', '2',  '#',  'F', '0', 'inventory:outStock:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('423', 'Edit', '420', '3',  '#',  'F', '0', 'inventory:outStock:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('424', 'Remove', '420', '4',  '#',  'F', '0', 'inventory:outStock:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('425', 'Save', '420', '5',  '#',  'F', '0', 'inventory:outStock:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');


insert into sys_menu values('431', 'Query', '430', '1',  '#',  'F', '0', 'inventory:queryinventory:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('432', 'Detail', '430', '1',  '#',  'F', '0', 'inventory:queryinventory:detail',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

insert into sys_menu values('441', 'Query', '440', '1',  '#',  'F', '0', 'inventory:resellStock:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('442', 'Add', '440', '2',  '#',  'F', '0', 'inventory:resellStock:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('443', 'Edit', '440', '3',  '#',  'F', '0', 'inventory:resellStock:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('444', 'Remove', '440', '4',  '#',  'F', '0', 'inventory:resellStock:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('445', 'Save', '440', '5',  '#',  'F', '0', 'inventory:resellStock:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

drop table if exists inv_inventory_in;
create table inv_inventory_in
(
    sn int(11) unsigned NOT NULL AUTO_INCREMENT,
	item_code     varchar(100)    not null                   comment 'Item Code',
  batch     varchar(100)    not null        ,
    warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  price_purchase  double(16,2)  not null        ,
  price_fob_ontario  double(16,2)  not null        ,
  quantity   int(10)  not null ,
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
	item_code     varchar(100)    not null                   comment 'Item Code',
  batch     varchar(100)    not null        ,
  warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  price_purchase  double(16,2)  not null        ,
  price_fob_ontario  double(16,2)  not null        ,
  quantity   int(10)  not null ,
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



drop table if exists inv_inventory_resell;
create table inv_inventory_resell
(
	  sn int(11) unsigned NOT NULL AUTO_INCREMENT,
	item_code     varchar(100)    not null                   comment 'Item Code',
  batch     varchar(100)    not null        ,
  warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  price_purchase  double(16,2)  not null        ,
  price_fob_ontario  double(16,2)  not null        ,
  quantity   int(10)  not null ,
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



drop table if exists inv_inventory;
create table inv_inventory
(
	sn int(11) unsigned NOT NULL AUTO_INCREMENT,
	item_code     varchar(100)    not null                   comment 'Item Code',
  batch     varchar(100)    not null        ,
  warehouse varchar(100)    not null        ,
  position  varchar(100)    not null        ,
  price_purchase  double(16,2)  not null        ,
  price_fob_ontario  double(16,2)  not null        ,
  quantity   int(10)  not null ,
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



drop table if exists inv_attchment;
create table inv_attchment
(
  attachment_id int(11) unsigned NOT NULL AUTO_INCREMENT,
	attachment_name     varchar(100)    not null                   ,
  attachment     varchar(500)    not null        ,
  inventory_sn  int(11)    not null,
  inventory_type varchar(100) not null,
  create_by     varchar(64)     default ''                 comment '创建者',
  create_time   timestamp       default current_timestamp  comment '创建时间',
  update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
  remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (attachment_id)
) engine=innodb default charset=utf8;
