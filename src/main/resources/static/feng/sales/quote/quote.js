var prefix = "/sales/quote"

$(function() {
    var columns = [{
        checkbox: true
    },
        // {
        //     field: 'inquiryUUID',
        //     title: 'Inquiry UUID'
        // },
        {
            field: 'quoteId',
            title: 'Quote Id'
        },
        {
            field: 'quoteDate',
            title: 'Quote Date'
        },
        {
            field: 'customerName',
            title: 'Customer Name'
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
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.quoteId + '\')"><i class="fa fa-edit"></i></a> ');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.quoteId + '\')"><i class="fa fa-remove"></i></a>');
                return actions.join('');
            }
        }];
    var url = prefix + "/list";
    $.initTable(columns, url);
});

/*

/!*inquiry Management-新增*!/
function add() {
    var url = prefix + '/add';
    layer_show("add Inquiry", url, '800', '600');
}

/!*inquiry Management-修改*!/
function edit(inquiryId) {
    var url = prefix + '/edit/' + inquiryId;
    layer_show("edit Inquiry", url, '800', '600');
}

// 单条删除
function remove(id) {
    $.modalConfirm("Do you want to remove this inquiry？", function(r) {
        _ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

// 批量删除
function batchRemove() {
    var rows = $.getSelections("inquiryId");
    if (rows.length == 0) {
        $.modalMsg("Please select the data you want to remove ", "warning");
        return;
    }
    $.modalConfirm("Do you want to remove " + rows.length + " Inquiry?", function(r) {
        _ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
    });
}
*/
