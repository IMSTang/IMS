var prefix = "/system/user"


$(document).ready(function(){
	$('body').layout({ west__size: 185 });
	queryUserList();
	queryDeptTreeDaTa();
});

function queryUserList() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'userId',
            title: 'User ID'
        },
        {
            field: 'loginName',
            title: 'Login Name'
        },
        {
            field: 'userName',
            title: 'User Name'
        },
        {
            field: 'email',
            title: 'Email'
        },
        {
            field: 'phonenumber',
            title: 'Mobile'
        },
        {
            field: 'status',
            title: 'Status',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '0') {
                    return '<span class="label label-success">Active</span>';
                } else if (value == '1') {
                    return '<span class="label label-danger">Disabled</span>';
                }
            }
        },
        {
            field: 'createTime',
            title: 'Create Time'
        },
        {
            title: 'Action',
            align: 'center',
            formatter: function(value, row, index) {

            	if(row.userType == "N") {
            		var actions = [];
                	actions.push('<a class="btn btn-primary btn-sm" href="#" title="Edit" onclick="edit(\'' + row.userId + '\')"><i class="fa fa-edit"></i></a> ');
                	actions.push('<a class="btn btn-warning btn-sm" href="#" title="Remove" onclick="remove(\'' + row.userId + '\')"><i class="fa fa-remove"></i></a> ');
                	actions.push('<a class="btn btn-success btn-sm  href="#" title="Reset Password" onclick="resetPwd(\'' + row.userId + '\')"><i class="fa fa-key"></i></a>');
                	return actions.join('');
				} else {
					return "";
				}
            }
        }];
	var url = prefix + "/list";
	$.initTable(columns, url);
}

function queryDeptTreeDaTa()
{
	// 树结构初始化加载
	var setting = {view:{selectedMulti:false},data:{key:{title:"title"},simpleData:{enable:true}},
		callback:{onClick:function(event, treeId, treeNode){
			tree.expandNode(treeNode);
			var opt = { query : { deptId : treeNode.id, parentId : treeNode.pId, } };
			$('.bootstrap-table').bootstrapTable('refresh', opt);
		}}
	}, tree, loadTree = function(){
		$.get("/system/dept/treeData", function(data) {
		    tree = $.fn.zTree.init($("#tree"), setting, data); //.expandAll(true);
		    // 展开第一级节点
		    var nodes = tree.getNodesByParam("level", 0);
		    for (var i = 0; i < nodes.length; i++) {
		        tree.expandNode(nodes[i], true, false, false);
		    }
		    // 展开第二级节点
		    nodes = tree.getNodesByParam("level", 1);
		    for (var i = 0; i < nodes.length; i++) {
		        tree.expandNode(nodes[i], true, false, false);
		    }
		}, null, null, "Loading, please wait...");
	};loadTree();
	
	$('#btnExpand').click(function() {
		tree.expandAll(true);
	    $(this).hide();
	    $('#btnCollapse').show();
	});
	$('#btnCollapse').click(function() {
		tree.expandAll(false);
	    $(this).hide();
	    $('#btnExpand').show();
	});
	$('#btnRefresh').click(function() {
	    loadTree();
	});
}

/*用户管理-删除*/
function remove(userId) {
	$.modalConfirm("Do you want to remove this user?", function(r) {
		_ajax(prefix + "/remove/" + userId, "", "post", r);
    })
}

/*用户管理-修改*/
function edit(userId) {
    var url = prefix + '/edit/' + userId;
    layer_show("edit User", url, '800', '550');
}

/*用户管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_show("add User", url, '800', '590');
}

/*用户管理-重置密码*/
function resetPwd(userId) {
    var url = prefix + '/resetPwd/' + userId;
    layer_show("Reset Password", url, '800', '300');
}

// 批量强退
function batchRemove() {
	var rows = $.getSelections("userId");
	if (rows.length == 0) {
		$.modalMsg("Please select the data you want to remove", "warning");
		return;
	}
	$.modalConfirm("Do you want to remove " + rows.length + " users?", function(r) {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post", r);
	});
}
