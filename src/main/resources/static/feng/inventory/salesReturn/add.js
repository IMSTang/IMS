$("#form-salesReturn-add").validate({
    rules:{
        quantity:{
            number: true,
            quantityLimit: true,
            min: 0.001,
        },
        warehouse: {
            required: true,
        },
        position: {
            required: true,
        },
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
        returnDate:{
            required:true,
            dateISO:true
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

jQuery.validator.addMethod("quantityLimit", function(value, element) {
    var returnVal = false;
    var outQuantity = $("#outQuantity").val();
    var returnQuantity = $("#quantity").val();
    if(parseFloat(returnQuantity)<=parseFloat(outQuantity)){
        returnVal = true;
    }
    return returnVal;
},"Greater than out quantity.");

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
        cleanStockOutInfo();
        return false;
    },
    select: function (event, ui) {
        $("#customerName").val(ui.item.label);
        $("#customerId").val(ui.item.value);
        cleanStockOutInfo();
        return false;
    }
});


// 3. itemCode auto complete
$("#itemCode").autocomplete({
    minLength: 0,
    source: function (request, response) {
        $.ajax({
            url: "/inventory/outStock/search_by_customer_itemcode",
            type: "get",
            dataType: "json",
            data: {
                "itemCode":  $("input[name='itemCode']").val(),
                "customerId":  $("input[name='customerId']").val()
            },

            success: function (data) {

                response($.map(data, function (item) {

                    return {
                        label: item.poCode +"  -  "+item.itemCode +"  -  "+item.batch +"  -  "+item.production.itemName +"  ( "+item.stockoutDate +" )",
                        value: item.itemCode,
                        itemName: item.production.itemName,
                        batch: item.batch,
                        warehouse: item.warehouse,
                        position: item.position,
                        quantity: item.quantity,
                        poCode: item.poCode,
                        vendorId: item.vendorId,
                        vendorName: item.vendor.vendorName,
                        stockoutSn: item.sn,
                    }
                }));
            }
        });
    },
    focus: function (event, ui) {

        $("#itemCode").val(ui.item.value);
        $("#itemName").val(ui.item.itemName);
        $("#batch").val(ui.item.batch);
        $("#outWarehouse").val(ui.item.warehouse);
        $("#outPosition").val(ui.item.position);
        $("#warehouse").val(ui.item.warehouse);
        $("#position").val(ui.item.position);
        $("#outQuantity").val(ui.item.quantity);
        $("#poCode").val(ui.item.poCode);
        $("#vendorName").val(ui.item.vendorName);
        $("#vendorId").val(ui.item.vendorId);
        $("#stockoutSn").val(ui.item.stockoutSn);
        return false;
    },
    select: function (event, ui) {

        $("#itemCode").val(ui.item.value);
        $("#itemName").val(ui.item.itemName);
        $("#batch").val(ui.item.batch);
        $("#outWarehouse").val(ui.item.warehouse);
        $("#outPosition").val(ui.item.position);
        $("#warehouse").val(ui.item.warehouse);
        $("#position").val(ui.item.position);
        $("#outQuantity").val(ui.item.quantity);
        $("#poCode").val(ui.item.poCode);
        $("#vendorName").val(ui.item.vendorName);
        $("#vendorId").val(ui.item.vendorId);
        $("#stockoutSn").val(ui.item.stockoutSn);
        return false;
    }
}).focus(function () {
    $(this).autocomplete("search");
});

//后续替换 jquery 校验，保持风格一致
$("#quantity").on("blur",function(){
    if(parseFloat($("#quantity").val())>parseFloat($("#StockQuantity").val())){
        alert("The output quantity must not exceed the stock quantity.");
        return  $(this).val("");
    }
});

function cleanStockOutInfo() {
    $("#itemCode").val();
    $("#itemName").val();
    $("#batch").val();
    $("#outWarehouse").val();
    $("#outPosition").val();
    $("#warehouse").val("");
    $("#position").val("");
    $("#outQuantity").val("");
    $("#poCode").val("");
    $("#vendorName").val("");
    $("#vendorId").val("");
    $("#stockoutSn").val("");
}

function add() {

    var itemCode  = $("input[name='itemCode']").val();
    var batch  = $("input[name='batch']").val();
    var warehouse  = $("input[name='warehouse']").val();
    var position  = $("input[name='position']").val();
    var quantity  = $("input[name='quantity']").val();
    var poCode  = $("input[name='poCode']").val();
    // var irradiation  = $("input[name='irradiation']").val();
    // var tpc  = $("input[name='tpc']").val();
    var vendorId  = $("input[name='vendorId']").val();
    var customerId  = $("input[name='customerId']").val();
    var remark  = $("#remark").val();
    var returnDate  = $("input[name='returnDate']").val();
    var stockoutSn  = $("input[name='stockoutSn']").val();


    $.ajax({
        cache : true,
        type : "POST",
        url : "/inventory/salesReturn/save",
        data : {
            "stockoutSn": stockoutSn,
            "itemCode": itemCode,
            "batch": batch,
            "warehouse": warehouse,
            "position": position,
            "quantity": quantity,
            "poCode": poCode,
            "vendorId": vendorId,
            "customerId": customerId,
            "remark": remark,
            "status": 0,
            "returnDate": returnDate

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