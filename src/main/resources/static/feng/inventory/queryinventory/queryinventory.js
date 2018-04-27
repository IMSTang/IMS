var prefix = "/inventory/queryinventory"

$(function() {
		var columns = [{
            checkbox: true
        },
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
            field: 'vendor.vendorName',
            title: 'Vendor Name'
        },
        {
            field: 'updateTime',
            title: 'Update Time'
        },

        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	actions.push('<a class="btn btn-warning btn-sm" href="#" title="Detail" onclick="detail(\'' + row.sn + '\')"><i class="fa fa-search"></i></a>');
            	return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*库存-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;
    layer_show("Inventory Detail", url, '800', '500');
}

