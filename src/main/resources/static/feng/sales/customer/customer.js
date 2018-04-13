var prefix = "/sales/customer"

window.onload = function() {
    loading();
};
function loading() {
    var columns = [
        {
            checkbox: true
        },
        {
            field : 'customerId',
            title : 'ID'
        },
        {
            field : 'customerName',
            title : 'Customer Name'
        },
        {
            field : 'first_name',
       {
            field : 'status',
            title : 'Status',
            formatter : function(item, index) {
                if (item.status == '0') {
                    return '<span class="label label-primary">aaa</span>';
                } else if (item.status == '1') {
                    return '<span class="label label-danger">bbb</span>';
                }
            }
        },
        {
            field : 'firstName',
            title : 'First Name'
        },
        {
            field : 'lastName',
            title : 'Last Name'
        },
        {
            field : 'middleName',
            title : 'Middle Name'
        },
        {
            field : 'nameTitle',
            title : 'Name Title'
        },
        {
            field : 'jobTitle',
            title : 'Job Title'
        },
        {
            field : 'mainPhone',
            title : 'Main phone'
        },
        {
            field : 'workPhone',
            title : 'Work Phone'
        },
        {
            field : 'mobile',
            title : 'Mobile'
        },
        {
            field : 'fax',
            title : 'Fax'
        },
        {
            field : 'mainMail',
            title : 'Main Mail'
        },
        {
            field : 'ccMail',
            title : 'CC Mail'
        },
        {
            field : 'addressShipTo',
            title : 'Address Ship To'
        },
        {
            field : 'addressBillTo',
            title : 'Address Bill To'
        },
        {
            field : 'description',
            title : 'Description'
        },
        {
            field : 'createBy',
            title : 'Create By'
        },
        {
            field : 'createTime',
            title : 'Create Time'
        },
        {
            field : 'updateBy',
            title : 'Update By'
        },
       {
            field : 'updateTime',
            title : 'Update Time'

        },

        {
            title : 'Action',
            align : 'center',
            formatter : function(value,row, index) {
                var actions = [];
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.customerId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Add" mce_href="#" onclick="add(\'' + row.customerId + '\')"><i class="fa fa-plus"></i></a>&nbsp;');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' + row.customerId + '\')"><i class="fa fa-remove"></i></a>');
                return actions.join('');
            }
         }
        ];
    var url = prefix + "/list";
    $.initTable(columns, url);
}

function batchRemove() {
    var rows = $.getSelections("roleId");
    if (rows.length == 0) {
        $.modalMsg("请选择要删除的数据", "warning");
        return;
    }
    $.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function(r) {
        _ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
    });
}