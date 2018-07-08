var prefix = "/inventory/queryproduct"

$(function() {
		var columns = [
		    {
            checkbox: true
        },
        {
            field: 'productCategory',
            title: 'Category'
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
            field: 'itemNameCn',
            title: 'Item Name CN'
        },
        {
            field: 'safetyStock',
            title: 'Safety Stock'
        },
        {
            field: 'sumInventory.sumQuantity',
            title: 'Quantity'
        },
        {
            field: 'sumInventory.pricePurchase',
            title: 'Cost'
        },
        {
            field: 'sumInventory.priceFobOntario',
            title: 'Suggestion Sell Quote'
        },
        {
            field: 'latestDemandDate',
            title: 'Latest Demand Date'
        },

        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
            	var actions = [];
            	// actions.push('<a class="btn btn-warning btn-sm" href="#" title="Detail" onclick="detail(\'' + row.sn + '\')"><i class="fa fa-search"></i></a>');
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Add" mce_href="#" onclick="addDemand(\'' + row.itemCode + '\')"><i class="fa fa-plus"></i></a>&nbsp;');
            	return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*增加备货需求*/
function addDemand(item_code) {
    var rows = new Array(1);
    rows[0] = item_code;
    $.modalConfirm("Do you want to add the production to <BR>demand list?", function(r) {
        _ajax(prefix + '/batchDemand', { "arrayItemCode": rows }, "post", r);
    });
}

// 批量增加备货需求
function batchDemand() {
    var rows = $.getSelections("itemCode");

    if (rows.length == 0) {
        $.modalMsg("Please select the production to add to <BR>demand list", "warning");
        return;
    }
    $.modalConfirm("Do you want to add " + rows.length + " items to <BR>demand list?", function(r) {
        _ajax(prefix + '/batchDemand', { "arrayItemCode": rows }, "post", r);
    });
}
