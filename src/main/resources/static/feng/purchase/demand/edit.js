$("#form-demand-edit").validate({
	rules:{
        demandDate:{
            required:true,
            dateISO:true,
        },
        vendorName:{
            required:true,
            minlength: 1,
            remote: {
                url: "/purchase/vendor/checkNameUnique",
                type: "post",
                dataType: "text",
                data: {
                    name : function() {
                        return $.trim($("#vendorName").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return false;
                    else return true;
                }
            }
        },
        itemCode:{
            required:true,
            minlength: 1,
            remote: {
                url: "/product/production/checkItemCodeUnique",
                type: "post",
                dataType: "text",
                data: {
                    name : function() {
                        return $.trim($("#itemCode").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return false;
                    else return true;
                }
            }
        },
        price:{
            required: true,
            number: true,
            min: 0.01,
        },
        quantity:{
            required: true,
            number: true,
            min: 0.01,
        },
        urgencyDegree:{
            required:true,
        },

    },
    //重设提示信息
    messages:{
        vendorName: {
            remote:"Not found this Vendor Name"
        },
        itemCode:{
            required: "Required Item Code",
            remote:"Not found this Item Code"
        }

    },
	submitHandler:function(form){
		edit();
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
            data: {"searchValue":  $("input[name='vendorName']").val() },
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

function edit() {
	var demandId = $("input[name='demandId']").val();
    var demandDate = $("input[name='demandDate']").val();
    var vendorId = $("input[name='vendorId']").val();
    var itemCode = $("input[name='itemCode']").val();
    var price = $("input[name='price']").val();
    var quantity = $("input[name='quantity']").val();
    var urgencyDegree = $("#urgencyDegree").val();
    var remark = $("#remark").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/purchase/demand/save",
		data : {
			"demandId": demandId,
            "demandDate": demandDate,
            "vendorId": vendorId,
            "itemCode": itemCode,
            "price": price,
            "quantity": quantity,
            "urgencyDegree": urgencyDegree,
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