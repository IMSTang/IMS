
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


/*
$("#form-quote-add").validate({
    rules:{
        itemCode:{
            required:true,
        },
    },
    submitHandler:function(form){
        add();
    }
});






function add() {
    var quoteId = $("input[name='quoteId']").val();
    var quoteDate = $("input[name='quoteDate']").val();
    var customerId = $("input[name='customerId']").val();
    var customerName = $("input[name='customerName']").val();
    var itemCode = $("input[name='itemCode']").val();
    var itemName = $("input[name='itemName']").val();
    var price = $("input[name='price']").val();
    var quantity = $("input[name='quantity']").val();
    var remark = $("input[name='remark']").val();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/sales/quote/save",
        data : {
            "quoteId": quoteId,
            "quoteDate": quoteDate,
            "customerId": customerId,
            "customerName": customerName,
            "itemCode": itemCode,
            "itemName": itemName,
            "price": price,
            "quantity": quantity,
            "status": 0,
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
*/