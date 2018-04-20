$("#form-inquiry-edit").validate({
	rules:{
        vendorName:{
            required:true,
        },
        itemCode:{
            required:true,
        },
        reminder:{
            digits:true,
        },
	},
	submitHandler:function(form){
		edit();
	}
});

function edit() {
	var inquiryId = $("input[name='inquiryId']").val();
    var inquiryDate = $("input[name='inquiryDate']").val();
    var vendorId = $("input[name='vendorId']").val();
    var vendorName = $("input[name='vendorName']").val();
    var reminder = $("input[name='reminder']").val();
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
            "inquiryId": inquiryId,
            "inquiryDate": inquiryDate,
            "vendorId": vendorId,
            "reminder": reminder,
            "itemCode": itemCode,
            "price": price,
            "quantity": quantity,
            "remark": remark
		},
		async : false,
		error : function(request) {
			$.modalAlert("System ERROR", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("Updated successfully, on refreshing ……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}
		}
	});
}