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

$("#itemCode").autocomplete({
    minLength: 2,
    max: 10,
    source: function (request, response) {
        var elementType =  "code";
        var elementValue =  $("#itemCode").val();
        $.ajax({
            url: "/product/production/search/"+elementValue+"/"+elementType,
            type: "get",
            dataType: "json",
            data: {   },
            success: function (data) {
                response($.map(data, function (item) {
				 return {
						label: item.itemCode,
						value: item.itemName,
				 }
                }));
            }
        });
    },
    focus: function (event, ui) {
        $("#itemCode").val(ui.item.label);
        $("#itemName").val(ui.item.value);
        return false;
    },
    select: function (event, ui) {
        $("#itemCode").val(ui.item.label);
        $("#itemName").val(ui.item.value);
        return false;
    }
});

$("#itemName").autocomplete({
    minLength: 2,
	max: 10,
    source: function (request, response) {
        var elementType =  "name";
        var elementValue =  $("#itemName").val();
        $.ajax({
            url: "/product/production/search/"+elementValue+"/"+elementType,
            type: "get",
            dataType: "json",
            data: {   },
            success: function (data) {
                response($.map(data, function (item) {
                    return {
                        label: item.itemName,
                        value: item.itemCode,
                    }
                }));
            }
        });
    },
    focus: function (event, ui) {
        $("#itemCode").val(ui.item.value);
        $("#itemName").val(ui.item.label);
        return false;
    },
    select: function (event, ui) {
        $("#itemCode").val(ui.item.value);
        $("#itemName").val(ui.item.label);
        return false;
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