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

$("#vendorName").autocomplete({
    minLength: 0,
    source: function (request, response) {
        $.ajax({
            type : "GET",
            url : "/purchase/vendor/search_name",
            data : {
                inputStr : request.term
            },
            cache : false,
            datatype : "JSON",
            success : function(data) {
                response($.map(data, function(item) {
                    return { //lable为下拉列表显示数据源。value为选中放入到文本框的值，这种方式可以自定义显示
                        lable : item.vendorName,
                        value : item.vendorName,
                        id : item.vendorId
                    };
                }));
            },
            error : function() {
                alert("异常");
            }

        });

    },
    focus: function( event, ui ) {
        $("vendorId").val( ui.item.id );
        $("vendorName").val( ui.item.value );
        return false;
    },
    select: function( event, ui ) {
        $("vendorId").val( ui.item.id );
        $("vendorName").val( ui.item.value );
        return false;
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
