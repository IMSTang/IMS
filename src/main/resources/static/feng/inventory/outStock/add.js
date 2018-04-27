$("#form-outStock-add").validate({
    rules:{
        pricePurchase:{
            number: true,
            min: 0.01,
        },
        priceFobOntario:{
            number: true,
            min: 0.01,
        },
        quantity:{
            number: true,
            min: 0.01,
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
    },

    messages:{
        itemCode:{
            required: "Required Item Code",
            remote:"Not found this Item Code"
        }

    },


    submitHandler:function(form){
        add();
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

// 3. customer Name auto complete
$("#customerName").autocomplete({
    minLength: 0,
    source: function (request, response) {
        $.ajax({
            url: "/sales/customer/search_name",
            type: "get",
            dataType: "json",
            data: {"searchValue":  $("input[name='customerName']").val() },

            success: function (data) {

                response($.map(data, function (item) {

                    return {
                        label: item.customerName,
                        value: item.customerId
                    }
                }));
            }
        });
    },
    focus: function (event, ui) {

        $("#customerName").val(ui.item.label);
        $("#customerId").val(ui.item.value);
        return false;
    },
    select: function (event, ui) {

        $("#customerName").val(ui.item.label);
        $("#customerId").val(ui.item.value);
        return false;
    }
});


function add() {

    var itemCode  = $("input[name='itemCode']").val();
    var batch  = $("input[name='batch']").val();
    var warehouse  = $("input[name='warehouse']").val();
    var position  = $("input[name='position']").val();
    var quantity  = $("input[name='quantity']").val();
    var customerId  = $("input[name='customerId']").val();
    var remark  = $("#remark").val();
    var stockOutDate  = $("input[name='stockOutDate']").val();


    $.ajax({
        cache : true,
        type : "POST",
        url : "/inventory/outStock/save",
        data : {
            "itemCode": itemCode,
            "batch": batch,
            "warehouse": warehouse,
            "position": position,
            "quantity": quantity,
            "customerId": customerId,
            "remark": remark,
            "status": 0,
            "stockOutDate": stockOutDate

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