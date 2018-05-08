$("#form-productionMapping-edit").validate({
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
		edit();
	}
});


//1. itemCode auto complete.

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

//2. itemName auto complete .
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


//3. newItemCode auto complete.

$("#newItemCode").autocomplete({
    minLength: 2,
    max: 10,
    source: function (request, response) {
        var elementType =  "code";
        var elementValue =  $("#newItemCode").val();
        $.ajax({
            url: "/product/production/search/"+elementValue+"/"+elementType,
            type: "get",
            dataType: "json",
            data: {   },
            success: function (data) {
                response($.map(data, function (item) {
                    return {
                        label: item.newItemCode,
                        value: item.newItemName,
                    }
                }));
            }
        });
    },
    focus: function (event, ui) {
        $("#newItemCode").val(ui.item.label);
        $("#newItemName").val(ui.item.value);
        return false;
    },
    select: function (event, ui) {
        $("#newItemCode").val(ui.item.label);
        $("#newItemName").val(ui.item.value);
        return false;
    }
});

//4. newItemName auto complete .
$("#newItemName").autocomplete({
    minLength: 2,
    max: 10,
    source: function (request, response) {
        var elementType =  "name";
        var elementValue =  $("#newItemName").val();
        $.ajax({
            url: "/product/production/search/"+elementValue+"/"+elementType,
            type: "get",
            dataType: "json",
            data: {   },
            success: function (data) {
                response($.map(data, function (item) {
                    return {
                        label: item.newItemName,
                        value: item.newItemCode,
                    }
                }));
            }
        });
    },
    focus: function (event, ui) {
        $("#newItemCode").val(ui.item.value);
        $("#newItemName").val(ui.item.label);
        return false;
    },
    select: function (event, ui) {
        $("#newItemCode").val(ui.item.value);
        $("#newItemName").val(ui.item.label);
        return false;
    }
});


function edit() {
    var id  = $("input[name='id']").val();
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
            "id": id,
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
				parent.layer.msg("Updated successfully, on refreshing ……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}
		}
	});
}