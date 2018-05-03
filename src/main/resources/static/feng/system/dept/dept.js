var prefix = "/system/dept"

window.onload = function() {
	loading();
};

function loading() {
	var columns = [{
			title : 'Dept Id',
			field : 'deptId',
			visible : false,
			align : 'center',
			valign : 'middle',
			width : '10%'
        },
        {
        	field : 'deptName',
			title : 'Dept Name'
        },
        {
        	field : 'orderNum',
			title : 'Order'
        },
        {
        	field : 'status',
			title : 'Status',
			formatter : function(item, index) {
				if (item.status == '0') {
					return '<div style="text-align: center;"><span class="label label-primary">Active</span></div>';
				} else if (item.status == '1') {
					return '<div style="text-align: center;"><span class="label label-danger">Disabled</span></div>';
				}
			}
        },
        {
			title : 'Create Time',
			formatter : function(row, index) {
				return formatDate(row.createTime,"yyyy-MM-dd");
			}
        },
        {
        	title : 'Action',
			align : 'center',
			formatter : function(row, index) {
				if(row.parentId != 0) {
					var actions = [];
					actions.push('<div style="text-align: center;">');
					actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.deptId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
					actions.push('<a class="btn btn-primary btn-sm" href="#" title="Add" mce_href="#" onclick="add(\'' + row.deptId + '\')"><i class="fa fa-plus"></i></a>&nbsp;');
					actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' + row.deptId + '\')"><i class="fa fa-remove"></i></a>');
					actions.push('</div>');
					return actions.join('');
				} else {
					return "";
				}
			}
        }];
	var url = prefix + "/list";
	$.initTreeTable('deptId', 'parentId', columns, url);
}

/*部门管理-新增*/
function add(deptId) {
    var url = prefix + '/add/' + deptId;
    layer_show("add Dept", url, '800', '500');
}

/*部门管理-修改*/
function edit(deptId) {
    var url = prefix + '/edit/' + deptId;
    layer_show("edit Dept", url, '800', '500');
}

/*部门管理-删除*/
function remove(deptId) {
	layer.confirm("Do you want to remove this dept?",{icon: 3, title:'Information'},function(index){
		$.ajax({
			type : 'get',
			url: prefix + "/remove/" + deptId,
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
