var prefix = "/inventory/queryinventory"

$(function() {
    var columns = [{
        checkbox: true
    },
        {
            field: 'sn',
            title: 'Index'
        },
        {
            field: 'itemCode',
            title: 'Item Code'
        },
        {
            field: 'production.itemName',
            title: 'Item Name'
        },
        {
            field: 'production.itemNameCn',
            title: 'Item Name CN'
        },
        {
            field: 'batch',
            title: 'Batch No'
        },
        // {
        //     field: 'warehouse',
        //     title: 'Warehouse'
        // },
        // {
        //     field: 'position',
        //     title: 'Position'
        // },
        {
            field: 'quantity',
            title: 'Quantity(KG)'
        },
        {
            field: 'vendor.vendorName',
            title: 'Vendor Name'
        },
        {
            field: 'updateTime',
            title: 'Update Time'
        },

        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="Detail" onclick="detail(\'' + row.sn + '\')"><i class="fa fa-search"></i></a>');
                return actions.join('');
            }
        }];
    var url = prefix + "/list";
    //$.initTable(columns, url);
    $('.bootstrap-table').bootstrapTable({
        method: 'get',                // 请求方式（*）
        dataType: "json",             // 返回格式（*）
        url: url,                    // 请求后台的URL（*）
        pagination: true,             // 是否显示分页（*）
        pageSize: 10,                 // 每页的记录行数（*）
        pageNumber: 1,                // 初始化加载第一页，默认第一页
        pageList: [10, 25, 50],       // 可供选择的每页的行数（*）
        search: true,                 // 是否显示搜索框功能
        singleSelect: false,          // 是否禁止多选
        iconSize: 'outline',          // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
        toolbar: '#tableToolbar',     // 指定工作栏
        sidePagination: "server",     // 启用服务端分页
        showRefresh: true,            // 是否显示刷新按钮
        showColumns: true,            // 是否显示隐藏某列下拉框
        showToggle: true,             // 是否显示详细视图和列表视图的切换按钮
        cache: false,                 // 是否使用缓存
        queryParams: function(params) {

            return {
                // 传递参数查询参数
                pageSize:       params.limit,
                pageNum:        params.offset / params.limit + 1,
                searchValue:    params.search,
                orderByColumn:  params.sort,
                isAsc:          params.order,
                remark:         $("#checkboxInclude0").is(":checked") ? 1 : 0

            };
        },
        columns: columns
    });
});

/*库存-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;
    layer_show("Inventory Detail", url, '800', '500');
}

function includeZero() {
    $('.bootstrap-table').bootstrapTable('refresh');
}