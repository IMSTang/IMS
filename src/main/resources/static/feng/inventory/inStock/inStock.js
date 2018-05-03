var prefix = "/inventory/inStock"

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
            field: 'stockInDate',
            title: 'Stock In Date'
        },

        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	actions.push('<div style="width: 120px;">');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Detail" onclick="detail(\'' + row.sn + '\')"><i class="fa fa-search"></i></a> ');
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Stock out" mce_href="#" onclick="stockOut(\'' + row.sn + '\')"><i class="fa fa-share"></i></a> ');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.sn + '\')"><i class="fa fa-remove"></i></a>');
                actions.push('</div>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*新增*/
function add() {
    var url = prefix + '/add';
    layer_show("add Item", url, '980', '600');
}



// 单条删除
function remove(sn) {
	$.modalConfirm("Do you want to remove this stock in record?", function(r) {
		_ajax(prefix + "/remove/" + sn, "", "post", r);
    })
}

/*入库-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;
    layer_show("Stock In Detail", url, '980', '600');
}

function stockOut(sn) {
  //  var url = prefix + '/edit/' + sn;

    var url='/inventory/outStock/edit/'+sn;
    layer_show("edit item", url, '980', '600');
}
