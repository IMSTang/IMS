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
            field : 'customer_name',
            title : 'Customer Name'
        },
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
            field : 'first_name',
            title : 'First Name'
        },
        {
            field : 'last_name',
            title : 'Last Name'
        },
        {
            field : 'middle_name',
            title : 'Middle Name'
        },
        {
            field : 'name_title',
            title : 'Name Title'
        },
        {
            field : 'job_title',
            title : 'Job Title'
        },
        {
            field : 'main_phone',
            title : 'Main phone'
        },
        {
            field : 'work_phone',
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
            field : 'main_mail',
            title : 'Main Mail'
        },
        {
            field : 'cc_mail',
            title : 'CC Mail'
        },
        {
            field : 'address_ship_to',
            title : 'Address Ship To'
        },
        {
            field : 'address_bill_to',
            title : 'Address Bill To'
        },
        {
            field : 'description',
            title : 'Description'
        },
        {
            field : 'create_by',
            title : 'Create By'
        },
        {
            field : 'create_time',
            title : 'Create Time'
        },
        {
            field : 'update_by',
            title : 'Update By'
        },
        {
            field : 'update_time',
            title : 'Update Time',
            formatter : function(row, index) {
                return formatDate(row.createTime,"yyyy-MM-dd");
            }
        },
        {
            title : 'Action',
            align : 'center',
            formatter : function(row, index) {
                if(row.parentId != 0) {
                    var actions = [];
                    actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.deptId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
                    actions.push('<a class="btn btn-primary btn-sm" href="#" title="Add" mce_href="#" onclick="add(\'' + row.deptId + '\')"><i class="fa fa-plus"></i></a>&nbsp;');
                    actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' + row.deptId + '\')"><i class="fa fa-remove"></i></a>');
                    return actions.join('');
                } else {
                    return "";
                }
            }
        }];
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