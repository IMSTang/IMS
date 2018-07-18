var prefix = "/product/production"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'productCategory',
            title: 'Category'
        },
        {

            field: 'itemCode',
            title: 'Item Code',
            remoteSort:true,
            sortable : true,
            sortName:'item_code'




        },
        {
            field: 'itemName',
            title: 'Item Name'
        },
       
	    {
            field: 'itemNameCn',
            title: 'Item Name CN'
        },
		
		{
            field: 'specification',
            title: 'specification'

        },

        {
            field: 'safetyStock',
            title: 'Safety Stock(KG)'
        },
		
		{
            field: 'remark',
            title: 'Remark'
        },
        {
            field: 'createTime',
            title: 'CreateTime',
            remoteSort:true,
            sortable : true,
            sortName:'create_time'

        },
		
        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.productionId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.productionId + '\')"><i class="fa fa-remove"></i></a>');
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
function edit(productionId) {
    var url = prefix + '/edit/' + productionId;
    layer_show("edit item", url, '800', '600');
}

// 单条删除
function remove(productionId) {
	$.modalConfirm("Do you want to remove this production?", function(r) {
		_ajax(prefix + "/remove/" + productionId, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("productionId");
	if (rows.length == 0) {
		$.modalMsg("Please select the data you want to remove ", "warning");
		return;
	}
	$.modalConfirm("Do you want to remove " + rows.length + " productions?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}
