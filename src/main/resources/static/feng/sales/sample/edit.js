var Element_index = 101;

$("#customerName").autocomplete({
    minLength: 2,
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


function autoInfomation(element1,element2,idElement,elementType){

    $(element1).autocomplete({

        minLength: 2,
        source: function (request, response) {

            var elementValue =  $(element1).val();

            $.ajax({
                url: "/product/production/search/"+elementValue+"/"+elementType,
                type: "get",
                dataType: "json",
                data: {
                },

                success: function (data) {

                    response($.map(data, function (item) {
                        if(elementType == "code"){


                            return {

                                label: item.itemCode,
                                value: item.itemName,
                                PID:item.productionId
                            }  }
                        else{
                            return {

                                label: item.itemName,
                                value: item.itemCode,
                                PID:item.productionId
                            }

                        }
                    }));
                }
            });
        }
        ,
        focus: function (event, ui) {

            $(element1).val(ui.item.label);
            $(element2).val(ui.item.value);
            $(idElement).val(ui.item.PID);
            return false;
        },
        select: function (event, ui) {
            $(element1).val(ui.item.label);
            $(element2).val(ui.item.value);
            $(idElement).val(ui.item.PID);
            return false;
        }
    });
}


/**
 * 添加一套输入产品信息按钮
 */
$("#add").click(addElement);
function addElement(){
    /**
     * div
     */

    var div = document.createElement('div');
    div.style.height = '32px';
    div.style.padding= '0 10px 0 16px';
    div.id="div"+Element_index;
    var clearDiv =document.createElement("div");
    clearDiv.className = 'clear';


    /**
     * item Name ID
     * */
    var TId=document.createElement("input");
    TId.type="hidden";
    TId.name="itemId"+Element_index;
    TId.id="itemId"+Element_index;


    /**
     *item Code
     */
    var TCode=document.createElement("input");
    TCode.type="text";
    TCode.className = 'col-sm-2';
    TCode.style.margin= '0 -1px';
    TCode.name="itemCode"+Element_index;
    TCode.id="itemCode"+Element_index;

    /**
     * item Name
     * */
    var TName=document.createElement("input");
    TName.type="text";
    TName.className = 'col-sm-5';
    TName.style.margin= '0 -1px';
    TName.name="itemName"+Element_index;
    TName.id="itemName"+Element_index;



    TCode.onclick=function () {//绑定点击事件
        autoInfomation(TCode,TName,TId,"code");
    };

    TName.onclick=function () {//绑定点击事件
        autoInfomation(TName,TCode,TId,"name");
    };

    /**
     * price
     */
    var TPrice=document.createElement("input");
    TPrice.type="text";
    TPrice.className = 'col-sm-2';
    TPrice.style.margin= '0 -1px';
    TPrice.name="price"+Element_index;
    TPrice.id="price"+Element_index;
    // TPrice.required= "required";

    /***
     * quantity
     */
    var TQuantity=document.createElement("input");
    TQuantity.type="text";
    TQuantity.className = 'col-sm-2';
    TQuantity.style.margin= '0 -1px';
    TQuantity.name="quantity"+Element_index;
    TQuantity.id="quantity"+Element_index;
    // TQuantity.required= "required";
    /**
     * -
     */
    var TButton=document.createElement("input");
    TButton.type="button";
    TButton.className = 'col-sm-1';
    TButton.style.margin= '0 0 0 3px';
    TButton.style.height= '24px';
    TButton.style.padding= '0';
    TButton.text=Element_index;
    TButton.value="Remove";
    TButton.name="del";
    TButton.id="del";
    TButton.onclick=function () {//绑定点击事件
        // var delId = "div"+Element_index;
        delButton(div.id);
    };

    div.appendChild(TId);
    div.appendChild(TCode);
    div.appendChild(TName);
    div.appendChild(TPrice);
    div.appendChild(TQuantity);
    div.appendChild(TButton);
    div.appendChild(clearDiv);
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


$("#form-sample-edit").validate({
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

        itemCode:{
            required:true,
        },
        reminder:{
            required: true,
            number: true,
            min: 0.01,
        },
        sampleDate:{
            required:true,
            dateISO:true
        },
    },


    messages: {
        "customerName": {
            remote: "customer not exist"
        }
    },
    submitHandler:function(form){
        update();
    }

});

function update() {

        var  sampleItem= new Object();

        var sampleId = $("input[name='sampleId']").val();
        var sampleDate = $("input[name='sampleDate']").val();
        var customerId = $("input[name='customerId']").val();
        var customerName = $("input[name='customerName']").val();
        var reminder = $("input[name='reminder']").val();
        var remark = $("#remark").val();

        sampleItem.sampleId=sampleId;
        sampleItem.sampleDate=sampleDate;
        sampleItem.customerId=customerId;
        sampleItem.customerName=customerName;
        sampleItem.remark=remark;
        sampleItem.reminder=reminder;

        var parentDiv =  document.getElementById('tableDiv');
        //获取div个数
        var divNum=  parentDiv.getElementsByTagName('div');

        /**
         * 循环取出 sampleBody 的 值
         */

        var f =/^\d+(\.\d+)?$/;
        var I = /^[0-9]*[1-9][0-9]*$/;
        var itemIndex=-1;

        for(  i=0;i<divNum.length;i++){

            //忽略 clear div
            if(divNum[i].className == ""){
                itemIndex++;
            }



            var inputs = divNum[i].getElementsByTagName('input');
            // var textValue=new Array();

            for (j=0;j<inputs.length;j++){
                //不获取按钮的值
                if(inputs[j].value=="Remove"){

                    // continue;
                }


                if((inputs[j].id).indexOf("itemId") >=0){

                    sampleItem['body['+itemIndex+'].sampleBodyId']=inputs[j].value;

                }

                if((inputs[j].id).indexOf("itemName") >=0){
                    if(inputs[j].value==""){
                        $.modalAlert("item Name  is empty", "error");
                        return false;
                    }
                    sampleItem['body['+itemIndex+'].itemName']=inputs[j].value;

                }
                if((inputs[j].id).indexOf("itemCode") >=0){
                    if(inputs[j].value==""){
                        $.modalAlert("item Code   is empty", "error");
                        return false;
                    }
                    sampleItem['body['+itemIndex+'].itemCode']=inputs[j].value;

                }
                if((inputs[j].id).indexOf("price") >=0){
                    if(!(f.test(inputs[j].value))){
                        $.modalAlert("price data is Invalid", "error");
                        return false;
                    }
                    sampleItem['body['+itemIndex+'].price']=inputs[j].value;

                }
                if((inputs[j].id).indexOf("quantity") >=0){
                    if(!(f.test(inputs[j].value))){
                        $.modalAlert("quantity data is Invalid", "error");
                        return false;
                    }
                    sampleItem['body['+itemIndex+'].quantity']=inputs[j].value;

                }

            }

        }


        $.ajax({
            cache : true,
            type : "POST",
            url : "/sales/sample/save",
            dataType: "json",
            data :sampleItem,
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

