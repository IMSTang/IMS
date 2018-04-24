$("#form-production-add").validate({
	rules:{
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
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
        safetyStock:{
            digits : true,
        }
	},
	submitHandler:function(form){
		add();
	}
});


		
function add() {
	
var itemCode  = $("input[name='itemCode']").val(); 
var itemName  = $("input[name='itemName']").val(); 
var itemNameCn  = $("input[name='itemNameCn']").val(); 
var productCategory  = $("input[name='productCategory']").val(); 
var specification  = $("input[name='specification']").val(); 
var specificationCn  = $("input[name='specificationCn']").val(); 
var testMethod  = $("input[name='testMethod']").val(); 
var placeOfOrigin  = $("input[name='placeOfOrigin']").val(); 
var efficiency  = $("input[name='efficiency']").val(); 
var safetyStock  = $("input[name='safetyStock']").val();
var createBy  = $("input[name='createBy']").val(); 
var createTime  = $("input[name='createTime']").val(); 
var updateBy  = $("input[name='updateBy']").val(); 
var updateTime  = $("input[name='updateTime']").val(); 
var remark  = $("input[name='remark']").val(); 
	
	
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/product/production/save",
		data : {
            "itemCode": itemCode, 
            "itemName": itemName, 
            "itemNameCn": itemNameCn, 
            "productCategory": productCategory, 
            "specification": specification, 
            "specificationCn": specificationCn, 
            "testMethod": testMethod, 
            "placeOfOrigin": placeOfOrigin, 
            "efficiency": efficiency, 
            "safetyStock": safetyStock,
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
				parent.layer.msg("Added successfully, on refreshing ……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					window.parent.location.reload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}
		}
	});
}