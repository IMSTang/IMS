$("#form-demand-add").validate({
	rules:{
        demandDate:{
            required:true,
        },
        vendorName:{
            required:true,
        },
        itemCode:{
            required:true,
        },
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
	var demandDate = $("input[name='demandDate']").val();
    var vendorId = $("input[name='vendorId']").val();
	var vendorName = $("input[name='vendorName']").val();
    var itemCode = $("input[name='itemCode']").val();
    var price = $("input[name='price']").val();
    var quantity = $("input[name='quantity']").val();
    var urgencyDegree = $("input[name='urgencyDegree']").val();
	var remark = $("input[name='remark']").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/purchase/demand/save",
		data : {
            "demandDate": demandDate,
            "vendorId": vendorId,
            "vendorName": vendorName,
            "itemCode": itemCode,
            "price": price,
            "quantity": quantity,
            "urgencyDegree": urgencyDegree,
			"status": 0,
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