$("#form-customer-add").validate({
    rules:{
        customerName:{
            required:true,
            minlength: 1,
            remote: {
                url: "/sales/customer/checkNameUnique",
                type: "post",
                dataType: "text",
                data: {
                    name : function() {
                        return $.trim($("#customerName").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
        },
        // customerName:{
        //     required:true,
        // },
        mainMail:{
            required:true,
            email:true
        },
    },
    messages: {
        "customerName": {
            remote: "customer have been exist"
        }
    },
    submitHandler:function(form){
       add();
    }
});


function add() {
   // var customerId = $("input[name='customerId']").val();
    var customerName = $("input[name='customerName']").val();
    var firstName = $("input[name='firstName']").val();
    var lastName = $("input[name='lastName']").val();
    var middleName = $("input[name='middleName']").val();
    var nameTitle = $("input[name='nameTitle']").val();
    var jobTitle = $("input[name='jobTitle']").val();
    var mainPhone = $("input[name='mainPhone']").val();
    var workPhone = $("input[name='workPhone']").val();
    var mobile = $("input[name='mobile']").val();
    var fax = $("input[name='fax']").val();
    var mainMail = $("input[name='mainMail']").val();
    var ccMail = $("input[name='ccMail']").val();
    var addressShipTo = $("input[name='addressShipTo']").val();
    var addressBillTo = $("input[name='addressBillTo']").val();
    var remark = $("input[name='remark']").val();

    $.ajax({
        cache : true,
        type : "POST",
        url : "/sales/customer/save",
        data : {

          //  "customerId":customerId,
            "customerName":customerName,
            "firstName":firstName,
            "lastName":lastName,
            "middleName":middleName,
            "nameTitle":nameTitle,
            "jobTitle":jobTitle,
            "mainPhone":mainPhone,
            "workPhone":workPhone,
            "mobile":mobile,
            "fax":fax,
            "mainMail":mainMail,
            "ccMail":ccMail,
            "addressShipTo":addressShipTo,
            "addressBillTo":addressBillTo,
            "remark":remark
        },
        async : false,
        error : function(request) {
            $.modalAlert("System ERROR", "error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("add successfully, on refreshing ……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
                    $.parentReload();
                });
            } else {
                $.modalAlert(data.msg, "error");
            }

        }
    });
}
