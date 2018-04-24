var prefix = "/system/dict"

$(function() {
		var columns = [{
            checkbox: true
        },
        {
            field: 'dictId',
            title: 'Dict Id'
        },
        {
            field: 'dictName',
            title: 'Dict Name'
        },
        {
            field: 'dictType',
            title: 'Dict Type'
        },
        {
            field: 'status',
            title: 'Status',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-success">正常</span>';
                } else if (value == 1) {
                    return '<span class="label label-danger">禁用</span>';
                }
            }
        },
        {
            field: 'remark',
            title: 'Remark'
        },
        {
            field: 'createTime',
            title: 'createTime'
        },
        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit Dict Type" mce_href="#" onclick="edit(\'' + row.dictId + '\')"><i class="fa fa-edit"></i></a> ');
            	actions.push('<a class="btn btn-warning btn-sm" href="#" title="Dict Type Detail" onclick="detail(\'' + row.dictId + '\')"><i class="fa fa-search"></i></a>');
            	return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*字典管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("add Dict", url, '800', '600');
}

/*角色管理-修改*/
function edit(dictId) {
    var url = prefix + '/edit/' + dictId;
    layer_show("edit Dict", url, '800', '600');
}

/*字典列表-详细*/
function detail(dictId) {
	var url = prefix + '/detail/' + dictId;
	createMenuItem(url, "show Dict");
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("dictId");
	if (rows.length == 0) {
		$.modalMsg("Please select the data you want to remove", "warning");
		return;
	}
	$.modalConfirm("Do you want to remove " + rows.length + " dict?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}
