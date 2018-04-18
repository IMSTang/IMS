$("#form-inquiry-add").validate({
	rules:{
        itemCode:{
			required:true,
		},
	},
	submitHandler:function(form){
		add();
	}
});


function add() {
    var inquiryUUID = $("input[name='inquiryUUID']").val();
    var inquiryDate = $("input[name='inquiryDate']").val();
	var vendorId = $("input[name='vendorId']").val();
    var vendorName = $("input[name='vendorName']").val();
    var itemCode = $("input[name='itemCode']").val();
    var itemName = $("input[name='itemName']").val();
    var price = $("input[name='price']").val();
    var quantity = $("input[name='quantity']").val();
	var remark = $("input[name='remark']").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/purchase/inquiry/save",
		data : {
            "inquiryUUID": inquiryUUID,
            "inquiryDate": inquiryDate,
            "vendorId": vendorId,
            "vendorName": vendorName,
            "itemCode": itemCode,
            "itemName": itemName,
            "price": price,
            "quantity": quantity,
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
