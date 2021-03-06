$("#form-vendor-edit").validate({
	rules:{
        vendorName:{
            required:true,
        },
        mainMail:{
            required:true,
            email:true
        },
	},
	submitHandler:function(form){
		edit();
	}
});

function edit() {
	var vendorId = $("input[name='vendorId']").val();
    var productCategory = $("#productCategory").val() + "";
    var vendorName = $("input[name='vendorName']").val();
    var firstName = $("input[name='firstName']").val();
    var lastName = $("input[name='lastName']").val();
    var middleName = $("input[name='middleName']").val();
    var nameTitle = $("input[name='nameTitle']").val();
    var jobTitle = $("input[name='jobTitle']").val();
    var mainPhone = $("input[name='mainPhone']").val();
    var workPhone = $("input[name='workPhone']").val();
    var mobile = $("input[name='mobile']").val();
    var fax = $("input[name='fax']").val();
    var mainMail = $("input[name='mainMail']").val();
    var ccMail = $("input[name='ccMail']").val();
    var addressShipFrom = $("input[name='addressShipFrom']").val();
    var addressBillFrom = $("input[name='addressBillFrom']").val();
    var remark = $("#remark").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/purchase/vendor/save",
		data : {
			"vendorId": vendorId,
            "productCategory": productCategory,
            "vendorName": vendorName,
            "firstName": firstName,
            "lastName": lastName,
            "middleName": middleName,
            "nameTitle": nameTitle,
            "jobTitle": jobTitle,
            "mainPhone": mainPhone,
            "workPhone": workPhone,
            "mobile": mobile,
            "fax": fax,
            "mainMail": mainMail,
            "ccMail": ccMail,
            "addressShipFrom": addressShipFrom,
            "addressBillFrom": addressBillFrom,
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