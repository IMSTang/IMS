$("#form-change-password").validate({
    rules:{

        newPWD:{
            required:true,
            rangelength:[6,8]
        },
        verifyPWD:{
            required:true,
            equalTo: "#newPWD",

        },
    },

    submitHandler:function(form){
        changePWD();
    }
});


function changePWD() {

    var newPWD = $("input[name='newPWD']").val();


    $.ajax({
        cache : true,
        type : "POST",
        url : "/system/user/changePWD",
        data : {
            "newPWD":newPWD
        },
        async : false,
        error : function(request) {
            $.modalAlert("System ERROR", "error");
        },
        success : function(data) {
            if (data.code == 0) {
                    layer.msg("change Password successfully, on refreshing ……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
                        $('#form-change-password')[0].reset();
                });
            } else {
                $.modalAlert(data.msg, "error");
            }

        }
    });
}
