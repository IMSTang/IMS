$("#form-inStock-add").validate({
	rules:{
        pricePurchase:{
            number: true,
            min: 0.01,
        },
		priceFobOntario:{
            number: true,
            min: 0.01,
        },
        warehouse: {
            required: true,
        },
        position: {
            required: true,
        },
		quantity:{
            number: true,
            min: 0.01,
        },
        vendorName:{
            required:true,
            minlength: 2,
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
	},

    messages:{
        itemCode:{
            required: "Required Item Code",
            remote:"Not found this Item Code"
        },
        vendorName: {
            required: "Required vendor",
            remote: "vendor not exist"
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

// 3. vendor Name auto complete
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

		
function add() {
	
var itemCode  = $("input[name='itemCode']").val(); 
var batch  = $("input[name='batch']").val(); 
var warehouse  = $("#warehouse").val();
var position  = $("input[name='position']").val(); 
var pricePurchase  = $("input[name='pricePurchase']").val(); 
var priceFobOntario  = $("input[name='priceFobOntario']").val(); 
var quantity  = $("input[name='quantity']").val(); 
var irradiation  = $("input[name='irradiation']").val(); 
var tpc  = $("input[name='tpc']").val(); 
var vendorId  = $("input[name='vendorId']").val();
var remark  = $("#remark").val();
var status  = $("input[name='status']").val(); 
var stockInDate  = $("input[name='stockInDate']").val(); 
var createBy  = $("input[name='createBy']").val(); 
var createTime  = $("input[name='createTime']").val(); 
var updateBy  = $("input[name='updateBy']").val(); 
var updateTime  = $("input[name='updateTime']").val(); 

var attachmentName  = $("input[name='attachmentName']").val(); 
var attachment  = $("input[name='attachment']").val();



    var formData = new FormData($("#form-inStock-add")[0]);
    var StockIn = new Object();
    StockIn.itemCode= itemCode;
    StockIn.batch= batch;
    StockIn.warehouse= warehouse;
    StockIn.position= position;
    StockIn.pricePurchase= pricePurchase;
    StockIn.priceFobOntario= priceFobOntario;
    StockIn.quantity= quantity;
    StockIn.irradiation= irradiation;
    StockIn.tpc= tpc;
    StockIn.vendorId= vendorId;
    StockIn.remark= remark;
    StockIn.status= status;
    StockIn.stockInDate= stockInDate;
    StockIn.createBy= createBy;
    StockIn.createTime= createTime;
    StockIn.updateBy= updateBy;
    StockIn.updateTime= updateTime;


    formData.append("StockIn",JSON.stringify(StockIn));
    $.ajax({
        cache : true,
        processData: false,
        contentType: false,
        type : "POST",
        url : "/inventory/inStock/save/",
        data :  formData,
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