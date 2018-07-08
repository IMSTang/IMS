var prefix = "/inventory/outStock"

$(function() {
	var columns = [
	    /*
	    {
            checkbox: true
        },
        */
        {
            field: 'sn',
            title: 'Index'
        },
        {
            field: 'itemCode',
            title: 'Item Code'
        },
        {
            field: 'production.itemName',
            title: 'Item Name'
        },
        {
            field: 'production.itemNameCn',
            title: 'Item Name CN'
        },
        {
            field: 'batch',
            title: 'Batch No'
        },
       
	    {
            field: 'warehouse',
            title: 'Location'
        },
		
		{
            field: 'position',
            title: 'Position'
        },
		
		{
            field: 'quantity',
            title: 'Quantity(KG)'
        },
        {
            field: 'customer.customerName',
            title: 'Customer Name'
        },

        {
            field: 'stockoutDate',
            title: 'Stock Out Date'
        },

        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	/*
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="ReSell Stock" mce_href="#" onclick="resellStock(\'' + row.sn + '\')"><i class="fa fa-reply"></i></a> ');
            	*/
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Detail" onclick="detail(\'' + row.sn + '\')"><i class="fa fa-search"></i></a> ');
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
    layer_show("new Stock Out", url, '980', '600');
}

/*出库-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;
    layer_show("Stock Out Detail", url, '980', '600');
}

// 单条删除
function remove(sn) {
	$.modalConfirm("Do you want to remove this stock out record?", function(r) {
		_ajax(prefix + "/remove/" + sn, "", "post", r);
    })
}

function resellStock(sn) {
    var url = prefix + '/edit/' + sn;
    layer_show("edit item", url, '980', '600');
}
