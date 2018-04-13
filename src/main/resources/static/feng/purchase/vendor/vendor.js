var prefix = "/purchase/vendor"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'vendorId',
            title: 'Vendor Id'
        },
        {
            field: 'vendorName',
            title: 'Vendor Name'
        },
        {
            field: 'contacts',
            title: 'Contacts'
        },
        {
            field: 'jobTitle',
            title: 'Job Title'
        },
        {
            field: 'createBy',
            title: 'Create By'
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.vendorId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.vendorId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*Vendor Management-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("add Vendor", url, '800', '600');
}

/*Vendor Management-修改*/
function edit(vendorId) {
    var url = prefix + '/edit/' + vendorId;
    layer_show("edit Vendor", url, '800', '600');
}

// 单条删除
function remove(id) {
	$.modalConfirm("Do you want to remove this Vendor？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("vendorId");
	if (rows.length == 0) {
		$.modalMsg("Please select the data you want to remove ", "warning");
		return;
	}
	$.modalConfirm("Do you want to remove " + rows.length + " vendors?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}
