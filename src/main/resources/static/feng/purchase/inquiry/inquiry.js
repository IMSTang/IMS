var prefix = "/purchase/inquiry"

$(function() {
    var columns = [{
        checkbox: true
    },

        {
            field: 'inquiryId',
            title: 'Inquiry Id'
        },
        {
            field: 'inquiryDate',
            title: 'Inquiry Date'
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
            title: 'Quantity(KG)'
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
                actions.push('<div style="width: 100px;">')
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.inquiryId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' +  row.inquiryBodyId +"','"+row.inquiryId + '\')"><i class="fa fa-remove"></i></a>');
                actions.push('</div>')
                return actions.join('');
            }
        }];
    var url = prefix + "/list";
    $.initTable(columns, url);
});

// /!*inquiry Management-新增*!/
function add() {
    var url = prefix + '/add';
    layer_show("add Inquiry", url, '800', '600');
}

// 单条删除
function remove(inquiryBodyId,inquiryId) {

    $.modalConfirm("Do you want to remove this Inquiry?", function(r) {
        _ajax(prefix + "/remove/" + inquiryBodyId+"/"+inquiryId, "", "post", r);
    })
}


// 批量删除
function batchRemove() {

    var inquiryBodyId = $.getSelections("inquiryBodyId");
    var inquiryId = $.getSelections("inquiryId");
    if (inquiryBodyId.length == 0) {
        $.modalMsg("Please select the data you want to remove ", "warning");
        return;
    }
    $.modalConfirm("Do you want to remove " + inquiryBodyId.length + " Inquiry?", function(r) {
        _ajax(prefix + '/batchRemove', { "inquiryBodyId": inquiryBodyId ,"inquiryId":inquiryId}, "post", r);
    });
}

/!*inquiry Management-修改*!/
function edit(inquiryId) {
    var url = prefix + '/edit/' + inquiryId;
    layer_show("edit Inquiry", url, '800', '600');
}


