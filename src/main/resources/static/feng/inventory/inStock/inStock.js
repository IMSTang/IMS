var prefix = "/inventory/inStock"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'itemCode',
            title: 'Item Code'
        },
        {
            field: 'batch',
            title: 'Batch'
        },
       
	    {
            field: 'warehouse',
            title: 'Warehouse'
        },
		
		{
            field: 'position',
            title: 'Position'
        },
		
		{
            field: 'quantity',
            title: 'Quantity'
        },
		
        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.sn + '\')"><i class="fa fa-remove"></i></a>');
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



// 单条删除
function remove(productionId) {
	$.modalConfirm("Do you want to remove this production?", function(r) {
		_ajax(prefix + "/remove/" + productionId, "", "post", r);
    })
}


