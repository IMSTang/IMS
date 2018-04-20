$("#form-inquiry-add").validate({
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
		add();
	}
});

$("#vendorName").autocomplete({
    minLength: 0,
    source: function (request, response) {
        $.ajax({
            url: "/purchase/vendor/search_name",
            type: "get",
            dataType: "json",
            data: {"inputStr":  $("input[name='vendorName']").val() },

            success: function (data) {

                response($.map(data, function (item) {

                    return {
                        label: item.vendorName,
                        value: item.vendorId
                    }
                }));
            }
        });
    },
    focus: function (event, ui) {

        $("#vendorName").val(ui.item.label);
        $("#vendorId").val(ui.item.value);
        return false;
    },
    select: function (event, ui) {

        $("#vendorName").val(ui.item.label);
        $("#vendorId").val(ui.item.value);
        return false;
    },
    focus: function () {
        $(this).autocomplete("search");
    }
});

function add() {
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
            "inquiryDate": inquiryDate,
            "vendorId": vendorId,
            "reminder": reminder,
            "itemCode": itemCode,
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
