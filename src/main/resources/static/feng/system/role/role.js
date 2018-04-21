var prefix = "/system/role"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'roleId',
            title: 'Role Id'
        },
        {
            field: 'roleName',
            title: 'Role Name'
        },
        {
            field: 'roleKey',
            title: 'Role Key'
        },
        {
            field: 'roleSort',
            title: 'Order'
        },
        {
            field: 'status',
            title: 'Status',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-success">Active</span>';
                } else if (value == 1) {
                    return '<span class="label label-danger">Disabled</span>';
                }
            }
        },
        {
            field: 'createTime',
            title: 'Create Time'
        },
        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.roleId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.roleId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*角色管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("add Role", url, '800', '550');
}

/*角色管理-修改*/
function edit(roleId) {
    var url = prefix + '/edit/' + roleId;
    layer_show("edit Role", url, '800', '550');
}

// 单条删除
function remove(id) {
	$.modalConfirm("Do you want to remove this role?", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("roleId");
	if (rows.length == 0) {
		$.modalMsg("Please select the data you want to remove", "warning");
		return;
	}
	$.modalConfirm("Do you want to remove " + rows.length + " roles?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}
