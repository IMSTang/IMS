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
        roleName:{
            required:true,
        },
        roleKey:{
            required:true,
        },
        roleSort:{
            required:true,
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