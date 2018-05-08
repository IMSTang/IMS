var prefix = "/sales/sample"

$(function() {
    var columns = [{
        checkbox: true
    },

        {
            field: 'sampleId',
            title: 'Sample Id'
        },
        {
            field: 'sampleDate',
            title: 'Sample Date'
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
            title: 'Quantity(g)'
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
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.sampleId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' +  row.sampleBodyId +"','"+row.sampleId + '\')"><i class="fa fa-remove"></i></a>');
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
    layer_show("add Sample", url, '800', '600');
}

// 单条删除
function remove(sampleBodyId,sampleId) {

    $.modalConfirm("Do you want to remove this Sample?", function(r) {
        _ajax(prefix + "/remove/" + sampleBodyId+"/"+sampleId, "", "post", r);
    })
}


// 批量删除
function batchRemove() {

    var sampleBodyId = $.getSelections("sampleBodyId");
    var sampleId = $.getSelections("sampleId");
    if (sampleBodyId.length == 0) {
        $.modalMsg("Please select the data you want to remove ", "warning");
        return;
    }
    $.modalConfirm("Do you want to remove " + sampleBodyId.length + " Sample?", function(r) {
        _ajax(prefix + '/batchRemove', { "sampleBodyId": sampleBodyId ,"sampleId":sampleId}, "post", r);
    });
}

/!*inquiry Management-修改*!/
function edit(sampleId) {
    var url = prefix + '/edit/' + sampleId;
    layer_show("edit Sample", url, '800', '600');
}


