var prefix = "/product/productionMapping"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'itemCode',
            title: 'Item Code'
        },
        {
            field: 'itemName',
            title: 'Item Name'
        },
       
	    {
            field: 'newItemCode',
            title: 'New Item Code'
        },
		
		{
            field: 'newItemName',
            title: 'New Item Name'
        },

        {
            field: 'rate',
            title: 'Rate'
        },

		{
            field: 'remark',
            title: 'Remark'
        },
		
        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*新增*/
function add() {
    var url = prefix + '/add';
    layer_show("add Item", url, '800', '600');
}

/*修改*/
function edit(id) {
    var url = prefix + '/edit/' + id;
    layer_show("edit item", url, '800', '600');
}

// 单条删除
function remove(id) {
	$.modalConfirm("Do you want to remove this production mapping?", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("id");
	if (rows.length == 0) {
		$.modalMsg("Please select the data you want to remove ", "warning");
		return;
	}
	$.modalConfirm("Do you want to remove " + rows.length + " production mappings?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}
