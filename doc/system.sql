-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id 			int(11) 		not null auto_increment    comment '部门id',
  parent_id 		int(11) 		default 0 			       comment '父部门id',
  dept_name 		varchar(30) 	default '' 				   comment '部门名称',
  order_num 		int(4) 			default 0 			       comment '显示顺序',
  leader            varchar(20)     default ''                 comment '负责人',
  phone             varchar(20)     default ''                 comment '联系电话',
  email             varchar(20)     default ''                 comment '邮箱',
  status 			int(1) 			default 0 				   comment '部门状态:0正常,1停用',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    timestamp       default current_timestamp  comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       timestamp       default current_timestamp  comment '更新时间',
  primary key (dept_id)
) engine=innodb auto_increment=200 default charset=utf8;


-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept values(100,  0,   'JHD CORP', 0, 'JHD', '15011112221', 'jdh@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(101,  100, 'Storage Dept', 1, 'storage1', '15011112222', 'storage1@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(102,  100, 'Sales Dept', 2, 'sale1', '15011112223', 'sale1@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(103,  100, 'Purchase Dept', 3, 'purchase1', '15011112224', 'purchase1@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');

-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id 			int(11) 		not null auto_increment    comment '用户ID',
  dept_id 			int(20) 		default null			   comment '部门ID',
  login_name 		varchar(30) 	default '' 				   comment '登录账号',
  user_name 		varchar(30) 	default '' 				   comment '用户昵称',
  email  			varchar(100) 	default '' 				   comment '用户邮箱',
  phonenumber  		varchar(20) 	default '' 				   comment '手机号码',
  password 			varchar(100) 	default '' 				   comment '密码',
  salt 				varchar(100) 	default '' 				   comment '盐加密',
  user_type         char(1)         default 'N'                comment '类型:Y默认用户,N非默认用户',
  status 			int(1) 			default 0 				   comment '帐号状态:0正常,1禁用',
  refuse_des 		varchar(500) 	default '' 				   comment '拒绝登录描述',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    timestamp       default current_timestamp  comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       timestamp       default current_timestamp  comment '更新时间',
  primary key (user_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user values(1, 106, 'admin', 'JHD_admin', 'JHD_admin@qq.com', '15088888888', '172eee54aa664e9dd0536b063796e54e', '', 'Y', 0, '维护中', 'admin', '2018-03-01', 'admin', '2018-03-01');



-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
    post_id       int(11)         not null auto_increment    comment '岗位ID',
	post_code     varchar(64)     not null                   comment '岗位编码',
	post_name     varchar(100)    not null                   comment '岗位名称',
	post_sort     int(4)          not null                   comment '显示顺序',
	status        int(1)          not null                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   timestamp       default current_timestamp  comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   timestamp       default current_timestamp  comment '更新时间',
    remark 		  varchar(500) 	  default '' 				 comment '备注',
	primary key (post_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post values(1, 'adm',  'Administrator',    1, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_post values(2, 'sto',   'Storage',  2, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_post values(3, 'sale',   'Sales',  3, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_post values(4, 'pur', 'Purchase',  4, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id 			int(10) 		not null auto_increment    comment '角色ID',
  role_name 		varchar(30) 	not null 				   comment '角色名称',
  role_key 		    varchar(100) 	not null 				   comment '角色权限字符串',
  role_sort         int(10)         not null                   comment '显示顺序',
  status 			int(1) 			default 0 				   comment '角色状态:0正常,1禁用',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 		timestamp       default current_timestamp  comment '创建时间',
  update_by 		varchar(64) 	default ''			       comment '更新者',
  update_time 		timestamp       default current_timestamp  comment '更新时间',
  remark 			varchar(500) 	default '' 				   comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', 'Administrator',   'admin',  1,  0, 'admin', '2018-03-01', 'admin', '2018-03-01', '管理员');
insert into sys_role values('2', 'Storage', 'storage', 2,  0, 'admin', '2018-03-01', 'admin', '2018-03-01', 'Storage');
insert into sys_role values('3', 'SalesManager', 'salesManager', 3,  0, 'admin', '2018-03-01', 'admin', '2018-03-01', 'salesManager');
insert into sys_role values('4', 'Purchase', 'purchase', 4,  0, 'admin', '2018-03-01', 'admin', '2018-03-01', 'Purchase');
insert into sys_role values('5', 'Sales', 'sales', 3,  0, 'admin', '2018-03-01', 'admin', '2018-03-01', 'Sales');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id 			int(11) 		not null auto_increment    comment '菜单ID',
  menu_name 		varchar(50) 	not null 				   comment '菜单名称',
  parent_id 		int(11) 		default 0 			       comment '父菜单ID',
  order_num 		int(4) 			default null 			   comment '显示顺序',
  url 				varchar(200) 	default ''				   comment '请求地址',
  menu_type 		char(1) 		default '' 			       comment '类型:M目录,C菜单,F按钮',
  visible 			int(1) 			default 0 				   comment '菜单状态:0显示,1隐藏',
  perms 			varchar(100) 	default '' 				   comment '权限标识',
  icon 				varchar(100) 	default '' 				   comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 		timestamp       default current_timestamp  comment '创建时间',
  update_by 		varchar(64) 	default ''			       comment '更新者',
  update_time 		timestamp       default current_timestamp  comment '更新时间',
  remark 			varchar(500) 	default '' 				   comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=1000 default charset=utf8;

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu values('1', 'System Management', '0', '1', '#', 'M', '0', '', 'fa fa-gear',         'admin', '2018-03-01', 'admin', '2018-03-01', '系统管理目录');

-- 二级菜单
insert into sys_menu values('4',   'Users', '1', '1', '/system/user',        'C', '0', 'system:user:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '用户管理菜单');
insert into sys_menu values('5',   'Roles', '1', '2', '/system/role',        'C', '0', 'system:role:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '角色管理菜单');
insert into sys_menu values('6',   'Menus', '1', '3', '/system/menu',        'C', '0', 'system:menu:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '菜单管理菜单');
insert into sys_menu values('7',   'Department', '1', '4', '/system/dept',        'C', '0', 'system:dept:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '部门管理菜单');
insert into sys_menu values('8',   'Post', '1', '5', '/system/post',        'C', '0', 'system:post:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '岗位管理菜单');
insert into sys_menu values('9',   '参数设置', '1', '6', '/system/config',      'C', '0', 'system:config:view',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '参数设置菜单');
insert into sys_menu values('10',  'DictType', '1', '7', '/system/dict',        'C', '0', 'system:dict:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '字典type菜单');
insert into sys_menu values('16',  'DictData', '1', '7', '/system/dictData',        'C', '0', 'system:dictdata:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '字典data菜单');
insert into sys_menu values('11',  'Operation Logs', '1', '11', '/monitor/operlog',    'C', '0', 'monitor:operlog:view',     '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '操作日志菜单');
insert into sys_menu values('12',  'Login Logs', '1', '12', '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '登录日志菜单');
insert into sys_menu values('13',  'Online Users', '1', '13', '/monitor/online',     'C', '0', 'monitor:online:view',      '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '在线用户菜单');
insert into sys_menu values('14',  '定时任务', '1', '14', '/monitor/job',        'C', '0', 'monitor:job:view',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '定时任务菜单');
insert into sys_menu values('15',  '数据监控', '1', '15', '/monitor/data',       'C', '0', 'monitor:data:view',        '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '数据监控菜单');

-- 用户管理按钮
insert into sys_menu values('17', '用户查询', '4', '1',  '#',  'F', '0', 'system:user:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('18', '用户新增', '4', '2',  '#',  'F', '0', 'system:user:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('19', '用户修改', '4', '3',  '#',  'F', '0', 'system:user:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('20', '用户删除', '4', '4',  '#',  'F', '0', 'system:user:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('21', '用户保存', '4', '5',  '#',  'F', '0', 'system:user:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('22', '批量删除', '4', '6',  '#',  'F', '0', 'system:user:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('23', '重置密码', '4', '7',  '#',  'F', '0', 'system:user:resetPwd',     '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 角色管理按钮
insert into sys_menu values('24', '角色查询', '5', '1',  '#',  'F', '0', 'system:role:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('25', '角色新增', '5', '2',  '#',  'F', '0', 'system:role:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('26', '角色修改', '5', '3',  '#',  'F', '0', 'system:role:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('27', '角色删除', '5', '4',  '#',  'F', '0', 'system:role:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('28', '角色保存', '5', '5',  '#',  'F', '0', 'system:role:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('29', '批量删除', '5', '6',  '#',  'F', '0', 'system:role:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 菜单管理按钮
insert into sys_menu values('30', '菜单查询', '6', '1',  '#',  'F', '0', 'system:menu:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('31', '菜单新增', '6', '2',  '#',  'F', '0', 'system:menu:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('32', '菜单修改', '6', '3',  '#',  'F', '0', 'system:menu:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('33', '菜单删除', '6', '4',  '#',  'F', '0', 'system:menu:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('34', '菜单保存', '6', '5',  '#',  'F', '0', 'system:menu:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 部门管理按钮
insert into sys_menu values('35', '部门查询', '7', '1',  '#',  'F', '0', 'system:dept:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('36', '部门新增', '7', '2',  '#',  'F', '0', 'system:dept:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('37', '部门修改', '7', '3',  '#',  'F', '0', 'system:dept:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('38', '部门删除', '7', '4',  '#',  'F', '0', 'system:dept:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('39', '部门保存', '7', '5',  '#',  'F', '0', 'system:dept:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 岗位管理按钮
insert into sys_menu values('40', '岗位查询', '8', '1',  '#',  'F', '0', 'system:post:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('41', '岗位新增', '8', '2',  '#',  'F', '0', 'system:post:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('42', '岗位修改', '8', '3',  '#',  'F', '0', 'system:post:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('43', '岗位删除', '8', '4',  '#',  'F', '0', 'system:post:remove',       '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('44', '岗位保存', '8', '5',  '#',  'F', '0', 'system:post:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('45', '批量删除', '10', '4', '#',  'F', '0', 'system:post:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 字典管理按钮
insert into sys_menu values('46', '字典查询', '10', '1', '#',  'F', '0', 'system:dict:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('47', '字典新增', '10', '2', '#',  'F', '0', 'system:dict:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('48', '字典修改', '10', '3', '#',  'F', '0', 'system:dict:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('49', '批量删除', '10', '4', '#',  'F', '0', 'system:dict:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('50', '字典保存', '10', '5', '#',  'F', '0', 'system:dict:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

-- 字典Data按钮
insert into sys_menu values('66', '字典查询', '16', '1', '#',  'F', '0', 'system:dictdata:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('67', '字典新增', '16', '2', '#',  'F', '0', 'system:dictdata:add',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('68', '字典修改', '16', '3', '#',  'F', '0', 'system:dictdata:edit',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('69', '批量删除', '16', '4', '#',  'F', '0', 'system:dictdata:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('70', '字典保存', '16', '5', '#',  'F', '0', 'system:dictdata:save',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 操作日志按钮
insert into sys_menu values('51', '操作查询', '11', '1', '#',  'F', '0', 'monitor:operlog:list',            '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('52', '批量删除', '11', '2', '#',  'F', '0', 'monitor:operlog:batchRemove',     '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('53', '详细信息', '11', '3', '#',  'F', '0', 'monitor:operlog:detail',          '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 登录日志按钮
insert into sys_menu values('54', '登录查询', '12', '1', '#',  'F', '0', 'monitor:logininfor:list',         '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('55', '批量删除', '12', '2', '#',  'F', '0', 'monitor:logininfor:batchRemove',  '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 在线用户按钮
insert into sys_menu values('56', '在线查询', '13', '1', '#',  'F', '0', 'monitor:online:list',             '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('57', '批量强退', '13', '2', '#',  'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('58', '单条强退', '13', '3', '#',  'F', '0', 'monitor:online:forceLogout',      '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
-- 定时任务按钮
insert into sys_menu values('59', '任务查询', '14', '1', '#',  'F', '0', 'monitor:job:list',             '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('60', '任务新增', '14', '2', '#',  'F', '0', 'monitor:job:add',              '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('61', '任务修改', '14', '3', '#',  'F', '0', 'monitor:job:edit',             '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('62', '任务删除', '14', '4', '#',  'F', '0', 'monitor:job:remove',           '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('63', '任务保存', '14', '5', '#',  'F', '0', 'monitor:job:save',             '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('64', '状态修改', '14', '6', '#',  'F', '0', 'monitor:job:changeStatus',     '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_menu values('65', '批量删除', '14', '7', '#',  'F', '0', 'monitor:job:batchRemove',      '#', 'admin', '2018-03-01', 'admin', '2018-03-01', '');

-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id 	int(11) not null comment '用户ID',
  role_id 	int(11) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id 	int(11) not null comment '角色ID',
  menu_id 	int(11) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('1', '1');
insert into sys_role_menu values ('1', '4');
insert into sys_role_menu values ('1', '5');
insert into sys_role_menu values ('1', '6');
insert into sys_role_menu values ('1', '7');
insert into sys_role_menu values ('1', '8');
insert into sys_role_menu values ('1', '9');
insert into sys_role_menu values ('1', '10');
insert into sys_role_menu values ('1', '11');
insert into sys_role_menu values ('1', '12');
insert into sys_role_menu values ('1', '13');
insert into sys_role_menu values ('1', '14');
insert into sys_role_menu values ('1', '15');
insert into sys_role_menu values ('1', '16');
insert into sys_role_menu values ('1', '17');
insert into sys_role_menu values ('1', '18');
insert into sys_role_menu values ('1', '19');
insert into sys_role_menu values ('1', '20');
insert into sys_role_menu values ('1', '21');
insert into sys_role_menu values ('1', '22');
insert into sys_role_menu values ('1', '23');
insert into sys_role_menu values ('1', '24');
insert into sys_role_menu values ('1', '25');
insert into sys_role_menu values ('1', '26');
insert into sys_role_menu values ('1', '27');
insert into sys_role_menu values ('1', '28');
insert into sys_role_menu values ('1', '29');
insert into sys_role_menu values ('1', '30');
insert into sys_role_menu values ('1', '31');
insert into sys_role_menu values ('1', '32');
insert into sys_role_menu values ('1', '33');
insert into sys_role_menu values ('1', '34');
insert into sys_role_menu values ('1', '35');
insert into sys_role_menu values ('1', '36');
insert into sys_role_menu values ('1', '37');
insert into sys_role_menu values ('1', '38');
insert into sys_role_menu values ('1', '39');
insert into sys_role_menu values ('1', '40');
insert into sys_role_menu values ('1', '41');
insert into sys_role_menu values ('1', '42');
insert into sys_role_menu values ('1', '43');
insert into sys_role_menu values ('1', '44');
insert into sys_role_menu values ('1', '45');
insert into sys_role_menu values ('1', '46');
insert into sys_role_menu values ('1', '47');
insert into sys_role_menu values ('1', '48');
insert into sys_role_menu values ('1', '49');
insert into sys_role_menu values ('1', '50');
insert into sys_role_menu values ('1', '51');
insert into sys_role_menu values ('1', '52');
insert into sys_role_menu values ('1', '53');
insert into sys_role_menu values ('1', '54');
insert into sys_role_menu values ('1', '55');
insert into sys_role_menu values ('1', '56');
insert into sys_role_menu values ('1', '57');
insert into sys_role_menu values ('1', '58');
insert into sys_role_menu values ('1', '59');
insert into sys_role_menu values ('1', '60');
insert into sys_role_menu values ('1', '61');
insert into sys_role_menu values ('1', '62');
insert into sys_role_menu values ('1', '63');
insert into sys_role_menu values ('1', '64');
insert into sys_role_menu values ('1', '65');

insert into sys_role_menu values ('1', '66');
insert into sys_role_menu values ('1', '67');
insert into sys_role_menu values ('1', '68');
insert into sys_role_menu values ('1', '69');
insert into sys_role_menu values ('1', '70');

-- ----------------------------
-- 8、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
	user_id varchar(64) not null comment '用户ID',
	post_id varchar(64) not null comment '岗位ID',
	primary key (user_id, post_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post values ('1', '1');


-- ----------------------------
-- 9、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id 			int(11) 		not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  action            varchar(100)    default ''                 comment '功能请求',
  method            varchar(100)    default ''                 comment '方法名称',
  channel           varchar(20)     default ''                 comment '来源渠道',
  login_name 	    varchar(50)     default '' 		 	 	   comment '登录账号',
  dept_name 		varchar(50)     default '' 		 	 	   comment '部门名称',
  oper_url 		    varchar(255) 	default '' 				   comment '请求URL',
  oper_ip 			varchar(30) 	default '' 				   comment '主机地址',
  oper_param 		varchar(2000) 	default '' 				   comment '请求参数',
  status 			int(1) 		    default 0				   comment '操作状态 0正常 1异常',
  error_msg 		varchar(2000) 	default '' 				   comment '错误消息',
  oper_time 		timestamp       default current_timestamp  comment '操作时间',
  primary key (oper_id)
) engine=innodb auto_increment=100 default charset=utf8;


-- ----------------------------
-- 10、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
	dict_id          int(11) 		 not null auto_increment    comment '字典主键',
	dict_name        varchar(100)    default ''                 comment '字典名称',
	dict_type        varchar(100)    default ''                 comment '字典类型',
    status 			 int(1) 		 default 0				    comment '状态（0正常 1禁用）',
    create_by        varchar(64)     default ''                 comment '创建者',
    create_time      timestamp       default current_timestamp  comment '创建时间',
    update_by        varchar(64) 	 default ''			        comment '更新者',
	update_time      timestamp       default current_timestamp  comment '更新时间',
    remark 	         varchar(500) 	 default '' 				comment '备注',
	primary key (dict_id),
	unique (dict_type)
) engine=innodb auto_increment=100 default charset=utf8;



-- ----------------------------
-- 11、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
	dict_code        int(11) 		 not null auto_increment    comment '字典编码',
	dict_sort        int(4)          default 0                  comment '字典排序',
	dict_label       varchar(100)    default ''                 comment '字典标签',
	dict_value       varchar(100)    default ''                 comment '字典键值',
	dict_type        varchar(100)    default ''                 comment '字典类型',
    status 			 int(1) 		 default 0				    comment '状态（0正常 1禁用）',
    create_by        varchar(64)     default ''                 comment '创建者',
    create_time      timestamp       default current_timestamp  comment '创建时间',
    update_by        varchar(64) 	 default ''			        comment '更新者',
	update_time      timestamp       default current_timestamp  comment '更新时间',
    remark 	         varchar(500) 	 default '' 				comment '备注',
	primary key (dict_code)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 12、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id 		int(11) 	 not null auto_increment   comment '访问ID',
  login_name 	varchar(50)  default '' 			   comment '登录账号',
  ipaddr 		varchar(50)  default '' 			   comment '登录IP地址',
  browser  		varchar(50)  default '' 			   comment '浏览器类型',
  os      		varchar(50)  default '' 			   comment '操作系统',
  status 		int(1) 		 default 0 			 	   comment '登录状态 0成功 1失败',
  msg      		varchar(255) default '' 			   comment '提示消息',
  login_time 	timestamp    default current_timestamp comment '访问时间',
  primary key (info_id)
) engine=innodb auto_increment=100 default charset=utf8;


-- ----------------------------
-- 13、在线用户记录
-- ----------------------------
drop table if exists sys_user_online;
create table sys_user_online (
  sessionId 	    varchar(50)  default ''              	comment '用户会话id',
  login_name 	    varchar(50)  default '' 		 	 	comment '登录账号',
  dept_name 		varchar(50)  default '' 		 	 	comment '部门名称',
  ipaddr 		    varchar(50)  default '' 			 	comment '登录IP地址',
  browser  		    varchar(50)  default '' 			 	comment '浏览器类型',
  os      		    varchar(50)  default '' 			 	comment '操作系统',
  status      	    varchar(10)  default '' 			 	comment '在线状态on_line在线off_line离线',
  start_timestsamp 	timestamp    default current_timestamp  comment 'session创建时间',
  last_access_time  timestamp    default current_timestamp  comment 'session最后访问时间',
  expire_time 	    int(5) 		 default 0 			 	    comment '超时时间，单位为分钟',
  primary key (sessionId)
) engine=innodb default charset=utf8;


-- ----------------------------
-- 14、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job (
  job_id 		      int(11) 	    not null auto_increment    comment '任务ID',
  job_name            varchar(64)   default ''                 comment '任务名称',
  job_group           varchar(64)   default ''                 comment '任务组名',
  method_name         varchar(500)  default ''                 comment '任务方法',
  params              varchar(200)  default ''                 comment '方法参数',
  cron_expression     varchar(255)  default ''                 comment 'cron执行表达式',
  status              int(1)        default 0                  comment '状态（0正常 1暂停）',
  create_by           varchar(64)   default ''                 comment '创建者',
  create_time         timestamp     default current_timestamp  comment '创建时间',
  update_by           varchar(64)   default ''                 comment '更新者',
  update_time         timestamp     default current_timestamp  comment '更新时间',
  remark              varchar(500)  default ''                 comment '备注信息',
  primary key (job_id, job_name, job_group)
) engine=innodb auto_increment=100 default charset=utf8;


-- ----------------------------
-- 15、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log (
  job_log_id          int(11) 	    not null auto_increment    comment '任务日志ID',
  job_name            varchar(64)   not null                   comment '任务名称',
  job_group           varchar(64)   not null                   comment '任务组名',
  method_name         varchar(500)                             comment '任务方法',
  params              varchar(200)  default ''                 comment '方法参数',
  job_message         varchar(500)                             comment '日志信息',
  is_exception        int(1)        default 0                  comment '是否异常',
  exception_info      text                                     comment '异常信息',
  create_time         timestamp     default current_timestamp  comment '创建时间',
  primary key (job_log_id)
) engine=innodb default charset=utf8;

-- 用户部门表
SELECT * FROM sys_dept;

-- 用户信息表
SELECT * FROM sys_user;

-- 岗位信息表
SELECT * FROM sys_post;

-- 角色信息表
SELECT * FROM sys_role;

-- 菜单信息表
SELECT * FROM sys_menu;

-- 用户和角色关联表  用户N-1角色
SELECT * FROM sys_user_role;

-- 角色和菜单关联表  角色1-N菜单
SELECT * FROM sys_role_menu;

-- 操作日志管理表
SELECT * FROM oper_log;

-- 字典类型表
SELECT * FROM sys_dict_type;

-- 字典数据表
SELECT * FROM sys_dict_data;

-- 系统访问日志情况信息
SELECT * FROM sys_logininfor;

-- 在线用户信息
SELECT * FROM sys_user_online;

-- 定时任务调度表
SELECT * FROM sys_job;

-- 定时任务调度日志表
SELECT * FROM sys_job_log;