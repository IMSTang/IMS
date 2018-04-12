var prefix = "/sales/customer"

window.onload = function() {
    loading();
};
function loading() {
    var columns = [{
        title : '部门编号',
        field : 'customerId',
        visible : false,
        align : 'center',
        valign : 'middle',
        width : '10%'
    },
        {
            field : 'deptName',
            title : '部门名称'
        },
        {
            field : 'orderNum',
            title : '排序'
        },
        {
            field : 'status',
            title : '状态',
            formatter : function(item, index) {
                if (item.status == '0') {
                    return '<span class="label label-primary">正常</span>';
                } else if (item.status == '1') {
                    return '<span class="label label-danger">禁用</span>';
                }
            }
        },
        {
            title : '创建时间',
            formatter : function(row, index) {
                return formatDate(row.createTime,"yyyy-MM-dd");
            }
        },
        {
            title : '操作',
            align : 'center',
            formatter : function(row, index) {
                if(row.parentId != 0) {
                    var actions = [];
                    actions.push('<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.deptId + '\')"><i class="fa fa-edit"></i></a>&nbsp;');
                    actions.push('<a class="btn btn-primary btn-sm" href="#" title="新增" mce_href="#" onclick="add(\'' + row.deptId + '\')"><i class="fa fa-plus"></i></a>&nbsp;');
                    actions.push('<a class="btn btn-warning btn-sm" href="#" title="删除" mce_href="#" onclick="remove(\'' + row.deptId + '\')"><i class="fa fa-remove"></i></a>');
                    return actions.join('');
                } else {
                    return "";
                }
            }
        }];
    var url = prefix + "/list";
    $.initTreeTable('customerId', 'parentId', columns, url);
}