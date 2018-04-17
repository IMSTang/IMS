var prefix = "/sales/customer"

window.onload = function() {
    loading();
};
function loading() {

    var columns = [
        {
            checkbox: true
        },
        // {
        //     field : 'customerId',
        //     title : 'Customer ID'
        // },
        {
            field : 'customerName',
            title : 'Customer Name'
        },
        {
            field: 'firstName',
            title: 'First Name'
        },
       // {
       //      field : 'status',
       //      title : 'Status',
       //      formatter : function(item, index) {
       //          if (item.status == '0') {
       //              return '<span class="label label-primary">aaa</span>';
       //          } else if (item.status == '1') {
       //              return '<span class="label label-danger">bbb</span>';
       //          }
       //      }
       //  },

        {
            field : 'lastName',
            title : 'Last Name'
        },
        // {
        //     field : 'middleName',
        //     title : 'Middle Name'
        // },
        {
            field : 'nameTitle',
            title : 'Name Title'
        },
        // {
        //     field : 'jobTitle',
        //     title : 'Job Title'
        // },
        {
            field : 'mainPhone',
            title : 'Main phone'
        },
        // {
        //     field : 'workPhone',
        //     title : 'Work Phone'
        // },
        // {
        //     field : 'mobile',
        //     title : 'Mobile'
        // },
        // {
        //     field : 'fax',
        //     title : 'Fax'
        // },
        {
            field : 'mainMail',
            title : 'Main Mail'
        },
        // {
        //     field : 'ccMail',
        //     title : 'CC Mail'
        // },
        {
            field : 'addressShipTo',
            title : 'Address Ship To'
        },
        // {
        //     field : 'addressBillTo',
        //     title : 'Address Bill To'
        // },

       //  {
       //      field : 'createBy',
       //      title : 'Create By'
       //  },
       //  {
       //      field : 'createTime',
       //      title : 'Create Time'
       //  },
       //  {
       //      field : 'updateBy',
       //      title : 'Update By'
       //  },
       // {
       //      field : 'updateTime',
       //      title : 'Update Time'
       //
       //  },

        {
            title : 'Action',
            align : 'center',
            formatter : function(value,row, index) {
                var actions = [];
                actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" mce_href="#" onclick="edit(\'' + row.customerId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" mce_href="#" onclick="remove(\'' + row.customerId + '\')"><i class="fa fa-remove"></i></a>');
                return actions.join('');
            }
         }
        ];
    var url = prefix + "/list";
    $.initTable(columns, url);
}


/*
* add a customer
* */
function add() {
    var url = prefix + '/add';
    layer_show("add customer", url, '800', '590');
}


// delete customer
function remove(id) {
    $.modalConfirm("Do you want to remove this customer？", function(r) {
        _ajax(prefix + "/remove/" + id, "", "post", r);
    })
}

function batchRemove() {
    var rows = $.getSelections("customerId");
    if (rows.length == 0) {
        $.modalMsg("Please select the data you want to remove", "warning");
        return;
    }
    $.modalConfirm("Do you want to remove " + rows.length + "customer?", function(r) {
        _ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
    });
}