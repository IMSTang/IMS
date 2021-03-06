$("#form-outStock-add").validate({
    rules:{
        customerName:{
            required:true,
            minlength: 2,
            remote: {
                url: "/sales/customer/checkNameExist",
                type: "post",
                dataType: "text",
                data: {
                    name : function() {
                        return $.trim($("#customerName").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return false;
                    else return true;
                }
            }
        },
        quantity:{
            number: true,
            quantityLimit: true,
            min: 0.001,
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
        batch:{
            required:true,
            minlength: 1,
            remote: {
                url: "/inventory/queryinventory/checkItemBatchUnique",
                type: "post",
                dataType: "json",
                data: {
                    "itemCode" : function() {
                        return $.trim($("#itemCode").val());
                    },
                    "batch" : function() {
                        return $.trim($("#batch").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return false;
                    else return true;
                }
            }
        },
        stockoutDate:{
            required:true,
            dateISO:true
        },
        priceSale:{
            required: true,
            number: true,
            min: 0,
        },
        poCode:{
            required:true,
        }
    },
    messages:{
        itemCode:{
            required: "Required Item Code",
            remote:"Not found this Item Code"
        },
        "customerName": {
            required: "Required customer",
            remote: "customer not exist"
        }

    },
    onkeyup:false,

    submitHandler:function(form){
        add();
    }
});

// //后续替换 jquery 校验，保持风格一致
// $("#quantity").on("blur",function(){
//     if(parseFloat($("#quantity").val())>parseFloat($("#StockQuantity").val())){
//         alert("The output quantity must not exceed the stock quantity.");
//         return  $(this).val("");
//     }
// });
//校验出库数量 不能大于 库存量
jQuery.validator.addMethod("quantityLimit", function(value, element) {
    var returnVal = false;
    var stockQuantity = $("#StockQuantity").val();
    var quantity = $("#quantity").val();
    if(parseFloat(quantity)<=parseFloat(stockQuantity)){
        returnVal = true;
    }
    return returnVal;
},"Greater than stock quantity.");

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
        cleanBatch();
        return false;
    },
    select: function (event, ui) {
        $("#itemCode").val(ui.item.label);
        $("#itemName").val(ui.item.value);
        cleanBatch();
        return false;
    }
}).focus(function () {
    $(this).autocomplete("search");
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
        cleanBatch();
        return false;
    },
    select: function (event, ui) {
        $("#itemCode").val(ui.item.value);
        $("#itemName").val(ui.item.label);
        cleanBatch();
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


// 3. Batch auto complete
$("#batch").autocomplete({
    minLength: 0,
    source: function (request, response) {
        $.ajax({
            url: "/inventory/queryinventory/search_batch",
            type: "get",
            dataType: "json",
            data: {
                "itemCode":  $("input[name='itemCode']").val(),
                "batchValue":  $("input[name='batch']").val()
            },

            success: function (data) {

                response($.map(data, function (item) {

                    return {
                        label: item.batch +"  -  "+item.warehouse +"  -  "+item.position,
                        value: item.batch,
                        warehouse: item.warehouse,
                        position: item.position,
                        quantity: item.quantity,
                        vendorId: item.vendorId,
                        vendorName: item.vendor.vendorName,
                        inventorySn: item.sn,
                    }
                }));
            }
        });
    },
    focus: function (event, ui) {

        $("#batch").val(ui.item.value);
        $("#warehouse").val(ui.item.warehouse);
        $("#position").val(ui.item.position);
        $("#StockQuantity").val(ui.item.quantity);
        $("#vendorName").val(ui.item.vendorName);
        $("#vendorId").val(ui.item.vendorId);
        $("#inventorySn").val(ui.item.inventorySn);
        return false;
    },
    select: function (event, ui) {

        $("#batch").val(ui.item.value);
        $("#warehouse").val(ui.item.warehouse);
        $("#position").val(ui.item.position);
        $("#StockQuantity").val(ui.item.quantity);
        $("#vendorName").val(ui.item.vendorName);
        $("#vendorId").val(ui.item.vendorId);
        $("#inventorySn").val(ui.item.inventorySn);
        return false;
    }
}).focus(function () {
    $(this).autocomplete("search");
});


function cleanBatch() {
    $("#batch").val("");
    $("#warehouse").val("");
    $("#position").val("");
    $("#StockQuantity").val("");
    $("#vendorName").val("");
    $("#vendorId").val("");
    $("#inventorySn").val("");
}

function add() {

    var itemCode  = $("input[name='itemCode']").val();
    var batch  = $("input[name='batch']").val();
    var warehouse  = $("input[name='warehouse']").val();
    var position  = $("input[name='position']").val();
    var quantity  = $("input[name='quantity']").val();
    var priceSale  = $("input[name='priceSale']").val();
    var poCode  = $("input[name='poCode']").val();
    var irradiation  = $("input[name='irradiation']").val();
    var tpc  = $("input[name='tpc']").val();
    var vendorId  = $("input[name='vendorId']").val();
    var customerId  = $("input[name='customerId']").val();
    var remark  = $("#remark").val();
    var stockoutDate  = $("input[name='stockoutDate']").val();
    var inventorySn  = $("input[name='inventorySn']").val();


    $.ajax({
        cache : true,
        type : "POST",
        url : "/inventory/outStock/save",
        data : {
            "inventorySn": inventorySn,
            "itemCode": itemCode,
            "batch": batch,
            "warehouse": warehouse,
            "position": position,
            "quantity": quantity,
            "priceSale": priceSale,
            "poCode": poCode,
            "irradiation": irradiation,
            "tpc": tpc,
            "vendorId": vendorId,
            "customerId": customerId,
            "remark": remark,
            "status": 0,
            "stockoutDate": stockoutDate

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