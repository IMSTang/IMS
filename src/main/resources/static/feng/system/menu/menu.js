var prefix = "/system/menu"

window.onload = function() {
	loading();
};

function loading() {
	var columns = [{
			title : 'Menu Id',
			field : 'menuId',
			visible : false,
			align : 'center',
			valign : 'middle',
			width : '10%'
        },
        {
        	field : 'menuName',
			title : 'Menu Name'
        },
        {
        	field : 'orderNum',
			title : 'Order'
        },
        {
        	field : 'url',
			title : 'url'
        },
        {
			title : 'Menu Type',
			field : 'menuType',
			align : 'center',
			valign : 'middle',
			formatter : function(item, index) {
				if (item.menuType == 'M') {
					return '<div style="text-align: center;"><span class="label label-primary">Floder</span></div>';
				}
				if (item.menuType == 'C') {
					return '<div style="text-align: center;"><span class="label label-success">Menu</span></div>';
				}
				if (item.menuType == 'F') {
					return '<div style="text-align: center;"><span class="label label-warning">Action</span></div>';
				}
			}
		},
        {
        	field : 'perms',
			title : 'Authority Key'
        },
        {
        	title : 'Action',
			align : 'center',
			formatter : function(row, index) {
				var actions = [];
                actions.push('<div style="text-align: center;">');
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.menuId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="Add" mce_href="#" onclick="add(\'' + row.menuId + '\')"><i class="fa fa-plus"></i></a>&nbsp;');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' + row.menuId + '\')"><i class="fa fa-remove"></i></a>');
                actions.push('</div>');
				return actions.join('');
			}
        }];
	var url = prefix + "/list";
	$.initTreeTable('menuId', 'parentId', columns, url, false);
}

/*菜单管理-新增*/
function add(menuId) {
    var url = prefix + '/add/' + menuId;
    layer_show("add Menu", url, '800', '550');
}

/*菜单管理-修改*/
function edit(menuId) {
    var url = prefix + '/edit/' + menuId;
    layer_show("edit Menu", url, '800', '550');
}

/*菜单管理-删除*/
function remove(menuId) {
	layer.confirm("Do you want to remove this menu?",{icon: 3, title:'Information'},function(index){
		$.ajax({
			type : 'get',
			url: prefix + "/remove/" + menuId,
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg, { icon: 1, time: 1000 });
					loading();
				} else {
					layer.alert(r.msg, { icon: 2, title: "Information" });
				}
			}
		});
	});
}
