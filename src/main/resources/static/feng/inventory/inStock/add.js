$("#form-inStock-add").validate({
	rules:{
		itemCode:{
			required:true,
		},
        pricePurchase:{
            digits : true,
        },
		priceFobOntario:{
            digits : true,
        },
		quantity:{
            digits : true,
        }
	},
	submitHandler:function(form){
		add();
	}
});


		
function add() {
	
var itemCode  = $("input[name='itemCode']").val(); 
var batch  = $("input[name='batch']").val(); 
var warehouse  = $("input[name='warehouse']").val(); 
var position  = $("input[name='position']").val(); 
var pricePurchase  = $("input[name='pricePurchase']").val(); 
var priceFobOntario  = $("input[name='priceFobOntario']").val(); 
var quantity  = $("input[name='quantity']").val(); 
var irradiation  = $("input[name='irradiation']").val(); 
var tpc  = $("input[name='tpc']").val(); 
var vendorId  = $("input[name='vendorId']").val(); 
var customerId  = $("input[name='customerId']").val(); 
var remark  = $("input[name='remark']").val(); 
var status  = $("input[name='status']").val(); 
var stockInDate  = $("input[name='stockInDate']").val(); 
var createBy  = $("input[name='createBy']").val(); 
var createTime  = $("input[name='createTime']").val(); 
var updateBy  = $("input[name='updateBy']").val(); 
var updateTime  = $("input[name='updateTime']").val(); 

var attachmentName  = $("input[name='attachmentName']").val(); 
var attachment  = $("input[name='attachment']").val(); 
	
	
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inventory/inStock/save",
		data : {
"itemCode": itemCode, 
"batch": batch, 
"warehouse": warehouse, 
"position": position, 
"pricePurchase": pricePurchase, 
"priceFobOntario": priceFobOntario, 
"quantity": quantity, 
"irradiation": irradiation, 
"tpc": tpc, 
"vendorId": vendorId, 
"customerId": customerId, 
"remark": remark, 
"status": status, 
"stockInDate": stockInDate, 
"createBy": createBy, 
"createTime": createTime, 
"updateBy": updateBy, 
"updateTime": updateTime,
"attachmentName": attachmentName, 
"attachment": attachment

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