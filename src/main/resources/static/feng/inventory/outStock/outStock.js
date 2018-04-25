var prefix = "/inventory/outStock"

$(function() {
	var columns = [
	    /*
	    {
            checkbox: true
        },
        */
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
            field: 'stockInDate',
            title: 'Stock Out Date'
        },

        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="ReSell Stock" mce_href="#" onclick="resellStock(\'' + row.sn + '\')"><i class="fa fa-reply"></i></a> ');

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
function remove(sn) {
	$.modalConfirm("Do you want to remove this stock in record?", function(r) {
		_ajax(prefix + "/remove/" + sn, "", "post", r);
    })
}

function resellStock(sn) {
    var url = prefix + '/edit/' + sn;
    layer_show("edit item", url, '800', '600');
}
