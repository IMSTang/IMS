$("#form-productionMapping-add").validate({
	rules:{
		itemCode:{
			required:true,
		},

        newItemCode:{
            required:true,
        },

        rate:{
            required:true,
            number : true,
        }
	},
	submitHandler:function(form){
		add();
	}
});


		
function add() {

    var itemCode  = $("input[name='itemCode']").val();
    var itemName  = $("input[name='itemName']").val();
    var newItemCode  = $("input[name='newItemCode']").val();
    var newItemName  = $("input[name='newItemName']").val();
    var rate  = $("input[name='rate']").val();
    var createBy  = $("input[name='createBy']").val();
    var createTime  = $("input[name='createTime']").val();
    var updateBy  = $("input[name='updateBy']").val();
    var updateTime  = $("input[name='updateTime']").val();
    var remark  = $("input[name='remark']").val();


    $.ajax({
		cache : true,
		type : "POST",
		url : "/product/productionMapping/save",
		data : {
            "itemCode": itemCode,
            "itemName": itemName,
            "newItemCode": newItemCode,
            "newItemName": newItemName,
            "rate": rate,
            "createBy": createBy,
            "createTime": createTime,
            "updateBy": updateBy,
            "updateTime": updateTime,
            "remark": remark
        },
		async : false,
		error : function(request) {
			$.modalAlert("System ERROR", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("Added successfully, on refreshing ……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}
		}
	});
}