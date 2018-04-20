var prefix = "/purchase/demand"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'demandId',
            title: 'Demand Id'
        },
        {
            field: 'demandDate',
            title: 'Demand Date'
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
            field: 'itemCode',
            title: 'Item Code'
        },
        {
            field: 'itemName',
            title: 'Item Name'
        },
        {
            field: 'price',
            title: 'Price'
        },
        {
            field: 'quantity',
            title: 'Quantity'
        },
        {
            field: 'urgencyDegree',
            title: 'Urgency Degree'
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
				actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.demandId + '\')"><i class="fa fa-edit"></i></a> ');
				actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.demandId + '\')"><i class="fa fa-remove"></i></a>');
				return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
});

/*Demand Management-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("add Demand", url, '800', '600');
}

/*Demand Management-修改*/
function edit(demandId) {
    var url = prefix + '/edit/' + demandId;
    layer_show("edit Demand", url, '800', '600');
}

// 单条删除
function remove(id) {
	$.modalConfirm("Do you want to remove this item？", function(r) {
		_ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("demandId");
	if (rows.length == 0) {
		$.modalMsg("Please select the data you want to remove ", "warning");
		return;
	}
	$.modalConfirm("Do you want to remove " + rows.length + " demands?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}
