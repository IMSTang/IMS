var prefix = "/inventory/queryproduct"

$(function() {
		var columns = [
		 //    {
        //     checkbox: true
        // },
        {
            field: 'production.productCategory',
            title: 'Category'
        },
        {
            field: 'production.itemCode',
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
            field: 'production.safetyStock',
            title: 'Safety Stock'
        },
        {
            field: 'sumQuantity',
            title: 'Quantity'
        },
        {
            field: 'pricePurchase',
            title: 'Price Purchase'
        },
        {
            field: 'priceFobOntario',
            title: 'Price FOB Ontario'
        },

        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	// actions.push('<a class="btn btn-warning btn-sm" href="#" title="Detail" onclick="detail(\'' + row.sn + '\')"><i class="fa fa-search"></i></a>');
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Add" mce_href="#" onclick="alert(\'add to Replenishment \\n TBD: item code =' + row.production.itemCode + '\')"><i class="fa fa-plus"></i></a>&nbsp;');
            	return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*操作日志-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;
    layer_show("Inventory Detail", url, '800', '500');
}

