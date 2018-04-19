
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





function codeInfomation(codeElement,nameElement,idElement){

    $(codeElement).autocomplete({

        minLength: 0,
        source: function (request, response) {

           var elementValue =  $(codeElement).val();
            $.ajax({
                url: "/product/production/search/"+elementValue+"/code",
                type: "get",
                dataType: "json",
                data: {
                },

                success: function (data) {

                    response($.map(data, function (item) {

                        return {
                            label: item.itemCode,
                            value: item.itemName,
                            PID:item.productionId
                        }
                    }));
                }
            });
        }
        ,
        focus: function (event, ui) {

            $(codeElement).val(ui.item.label);
            $(nameElement).val(ui.item.value);
            $(idElement).val(ui.item.PID);
            return false;
        },
        select: function (event, ui) {
            $(codeElement).val(ui.item.label);
            $(nameElement).val(ui.item.value);
            $(idElement).val(ui.item.PID);
            return false;
        }
    });
}




var Element_index = 1;
/**
 * 默认界面上显示一套商品信息
 */
addElement();

/**
 * 添加一套输入产品信息按钮
 */
$("#add").click(addElement);
function addElement(){
    /**
     * div
     */

    var div = document.createElement('div');
    div.padding="10px";
    div.id="div"+Element_index;


    /**
     * item Name ID
     * */
    var TId=document.createElement("input");
    TId.type="text";
    TId.name="itemId"+Element_index;
    TId.id="itemId"+Element_index;



    /**
     * item Name
     * */
    var TName=document.createElement("input");
    TName.type="text";
    TName.name="itemName"+Element_index;
    TName.id="itemName"+Element_index;


    /**
     *item Code
     */
    var TCode=document.createElement("input");
    TCode.type="text";
    TCode.name="itemCode"+Element_index;
    TCode.id="itemCode"+Element_index;

    TCode.onclick=function () {//绑定点击事件
        codeInfomation(TCode,TName,TId);
    };
    /**
     *
     *
     * 添加name 的点击事件
     *
     *
     *
     */



    /**
     * price
     */
    var TPrice=document.createElement("input");
    TPrice.type="text";
    TPrice.name="price"+Element_index;
    TPrice.id="price"+Element_index;




    /***
     * quantity
     */
    var TQuantity=document.createElement("input");
    TQuantity.type="text";
    TQuantity.name="quantity"+Element_index;
    TQuantity.id="quantity"+Element_index;

    /**
     * -
     */
    var TButton=document.createElement("input");
    TButton.type="button";
    TButton.text=Element_index;
    TButton.value="-";
    TButton.name="delButton";
    TButton.id="delButton";
    TButton.onclick=function () {//绑定点击事件
        // var delId = "div"+Element_index;
        delButton(div.id);
    };

    div.appendChild(TId);
    div.appendChild(TCode);
    div.appendChild(TName);
    div.appendChild(TQuantity);
    div.appendChild(TPrice);
    div.appendChild(TButton);
    document.getElementById("tableDiv").appendChild(div);
    Element_index++;
}

/***
 * 删除一套产品信息按钮
 */

function delButton(divId) {


    var div = document.getElementById(divId);
   var parentDiv =  document.getElementById('tableDiv');

    if( parentDiv.childElementCount>1){
    parentDiv.removeChild(div);
    }

}


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