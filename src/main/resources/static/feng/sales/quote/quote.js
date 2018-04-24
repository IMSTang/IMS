var prefix = "/sales/quote"

$(function() {
    var columns = [{
        checkbox: true
    },

        // {
        //     field: 'quoteBodyId',
        //     title: 'Quote Body  Id'
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
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.quoteId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' +  row.quoteBodyId +"','"+row.quoteId + '\')"><i class="fa fa-remove"></i></a>');
                return actions.join('');
            }
        }];
    var url = prefix + "/list";
    $.initTable(columns, url);
});



// /!*inquiry Management-新增*!/
function add() {
    var url = prefix + '/add';
    layer_show("add Quote", url, '800', '600');
}



// 单条删除
function remove(quoteBodyId,quoteId) {

    $.modalConfirm("Do you want to remove this Quote?", function(r) {
        _ajax(prefix + "/remove/" + quoteBodyId+"/"+quoteId, "", "post", r);
    })
}
/*
/!*inquiry Management-修改*!/
function edit(inquiryId) {
    var url = prefix + '/edit/' + inquiryId;
    layer_show("edit Inquiry", url, '800', '600');
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
