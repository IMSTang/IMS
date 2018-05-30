var selectedRadioId = '';

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
                                // PID:item.productionId
                            }  }
                        else{
                            return {

                                label: item.itemName,
                                value: item.itemCode,
                                // PID:item.productionId
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
            // $(idElement).val(ui.item.PID);
            selectedRadioId = '';
            checkRadio($(element1)[0].parentElement.id);
            return false;
        },
        select: function (event, ui) {
            $(element1).val(ui.item.label);
            $(element2).val(ui.item.value);
            // $(idElement).val(ui.item.PID);
            selectedRadioId = '';
            checkRadio($(element1)[0].parentElement.id);
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
     * radio
     */
    var TRadio=document.createElement("input");
    TRadio.type="radio";
    TRadio.className = 'col-sm-1';
    TRadio.style.width = '30px';
    TRadio.style.verticalAlign = 'text-bottom';
    TRadio.name="showedItem";
    TRadio.id="radio"+Element_index;

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
    TPrice.style.width = '90px';
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
    TButton.name="delButton";
    TButton.id="delButton";
    TButton.onclick=function () {//绑定点击事件
        // var delId = "div"+Element_index;
        delButton(div.id);
    };

    div.appendChild(TRadio);
    div.appendChild(TId);
    div.appendChild(TCode);
    div.appendChild(TName);
    div.appendChild(TPrice);
    div.appendChild(TQuantity);
    div.appendChild(TButton);
    div.appendChild(clearDiv);
    div.onclick=function () {//绑定点击事件
        checkRadio(div.id);
    };
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

/***
 * 点击一套产品信息，选中它的单选按钮
 */

function checkRadio(divId) {
    var div = document.getElementById(divId);
    //$(divId).find("input[type=radio]").attr("checked", 'checked');
    var radioId = divId.replace("div","radio");
    var itemCodeId = divId.replace("div","itemCode");

    if(selectedRadioId != radioId){
        var input_radio = "input[id='"+radioId+"']";
        $(input_radio).prop("checked", true);
        selectedRadioId = radioId;

        var input_itemCode = "input[id='"+itemCodeId+"']";
        var itemCode = $(input_itemCode).val();
        if(itemCode == null || itemCode ==""){
            selectedRadioId = "";
            return false;
        }
        // ajax get batch quantity from inventory by item_code
        $.ajax({
            cache : true,
            type : "GET",
            url : "/inventory/queryinventory/search_itemcode",
            dataType: "json",
            data: {
                "itemCode": itemCode,
                "currentQuoteId": ""
            },
            async : true,
            error : function(request) {
                $.modalAlert("System ERROR", "Error, please re-open the system");
            },
            success : function(data) {
                if (data.vendorRefPrice != "undefined") {
                    //alert(itemCode);

                    var div = document.createElement('div');
                    div.style.border = '1px solid #ddd';
                    div.style.borderBottom = '0';
                    div.style.height = '32px';
                    div.style.padding = '5px 10px 0 16px';
                    div.style.background = '#f2f2f2';
                    var span=document.createElement('span');
                    span.innerHTML = "For Reference (" + itemCode + ")";
                    div.appendChild(span);

                    var divV = document.createElement('div');
                    divV.style.border = '1px solid #ddd';
                    divV.style.borderBottom = '0';
                    divV.style.height = '32px';
                    divV.style.padding = '5px 10px 0 16px';
                    var spanV=document.createElement('span');
                    spanV.innerHTML = "Vendor Reference Price: " + data.vendorRefPrice + "";
                    divV.appendChild(spanV);

                    var divQ = document.createElement('div');
                    divQ.style.border = '1px solid #ddd';
                    divQ.style.borderBottom = '0';
                    divQ.style.height = '32px';
                    divQ.style.padding = '5px 10px 0 16px';
                    var spanQ=document.createElement('span');
                    spanQ.innerHTML = "Recent Quota Price: " + data.recentQuotaPrice + "";
                    divQ.appendChild(spanQ);

                    var clearDiv =document.createElement("div");
                    clearDiv.className = 'clear';

                    $("#referenceDiv").empty();
                    $("#referenceDiv").append(div);
                    $("#referenceDiv").append(divV);
                    $("#referenceDiv").append(divQ);

                    if(data.inventoryList.length>0){
                        var divI = document.createElement('div');
                        divI.style.border = '1px solid #ddd';
                        divI.style.borderBottom = '0';
                        divI.style.height = '32px';
                        divI.style.padding = '5px 10px 0 16px';
                        divI.style.textAlign = 'center';
                        div.style.verticalAlign = 'center';
                        var spanI=document.createElement('span');
                        spanI.innerHTML = "Inventory";
                        divI.appendChild(spanI);

                        $("#referenceDiv").append(divI);

                        var divT = document.createElement('div');
                        divT.style.border = '1px solid #ddd';
                        divT.style.borderBottom = '0';
                        divT.style.height = '32px';
                        divT.style.padding = '0 10px 0 16px';

                        divT.appendChild(createEl('div',{'class': 'col-sm-4'},{height:'32px', margin:'0px auto', padding: '5px 10px 0px 16px', border:'1px solid #DDD', 'text-align':'center'},'Batch'));
                        divT.appendChild(createEl('div',{'class': 'col-sm-4'},{height:'32px', margin:'0px auto', padding: '5px 10px 0px 16px', border:'1px solid #DDD', 'text-align':'center'},'Price'));
                        divT.appendChild(createEl('div',{'class': 'col-sm-4'},{height:'32px', margin:'0px auto', padding: '5px 10px 0px 16px', border:'1px solid #DDD', 'text-align':'center'},'Quantity(KG)'));

                        $("#referenceDiv").append(divT);

                        var list = data.inventoryList;
                        for(var inv in list){
                            var divR = document.createElement('div');
                            divR.style.border = '1px solid #ddd';
                            divR.style.borderBottom = '0';
                            divR.style.height = '32px';
                            divR.style.padding = '0 10px 0 16px';

                            divR.appendChild(createEl('div',{'class': 'col-sm-4'},{height:'32px', margin:'0 auto', padding: '5px 10px 0px 16px', border:'1px solid #DDD', 'text-align':'center'},list[inv].batch));
                            divR.appendChild(createEl('div',{'class': 'col-sm-4'},{height:'32px', margin:'0 auto', padding: '5px 10px 0px 16px', border:'1px solid #DDD', 'text-align':'center'},list[inv].priceFobOntario));
                            divR.appendChild(createEl('div',{'class': 'col-sm-4'},{height:'32px', margin:'0 auto', padding: '5px 10px 0px 16px', border:'1px solid #DDD', 'text-align':'center'},list[inv].quantity));
                            $("#referenceDiv").append(divR);
                        }

                    }

                    $("#referenceDiv").append(clearDiv);

                    var divFoot = document.createElement('div');
                    divFoot.style.border = '1px solid #ddd';
                    divFoot.style.height = '32px';
                    divFoot.style.padding = '5px 10px 0 16px';
                    divFoot.style.background = '#f2f2f2';
                    var span=document.createElement('span');
                    span.innerHTML = "Today: " + data.Today + "";
                    divFoot.appendChild(span);

                    $("#referenceDiv").append(divFoot);

                } else {
                    $.modalAlert(data.msg, "error");
                }
            }
        });

    }
}

/*
 *  var newElement = createEl('div',
 *     {'class': 'newDivClass', id: 'newDiv', name: 'newDivName'},
 *     {width: '300px', height:'200px', margin:'0 auto', border:'1px solid #DDD'},
 *     '這是存在於在新建立標籤 div 中的文字。');
 */
function createEl(t, a, y, x) {
    var e = document.createElement(t);
    if (a) {
        for (var k in a) {
            if (k == 'class') e.className = a[k];
            else if (k == 'id') e.id = a[k];
            else e.setAttribute(k, a[k]);
        }
    }
    if (y) { for (var k in y) e.style[k] = y[k]; }
    if (x) { e.appendChild(document.createTextNode(x)); }
    return e;
};

$("#form-quote-add").validate({
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
        quoteDate:{
            required:true,
            dateISO:true
        }

    },

    messages: {
        "customerName": {
            remote: "customer not exist"
        }
    },
    submitHandler:function(form){
        add();
    }

});

function add() {

    var  quoteItem= new Object();

    var quoteId = $("input[name='quoteId']").val();
    var quoteDate = $("input[name='quoteDate']").val();
    var customerId = $("input[name='customerId']").val();
    var customerName = $("input[name='customerName']").val();
    var reminder = $("input[name='reminder']").val();
    var remark = $("#remark").val();

    quoteItem.quoteId=quoteId;
    quoteItem.quoteDate=quoteDate;
    quoteItem.customerId=customerId;
    quoteItem.customerName=customerName;
    quoteItem.remark=remark;
    quoteItem.reminder=reminder;

    var parentDiv =  document.getElementById('tableDiv');
    //获取div个数
    var divNum=  parentDiv.getElementsByTagName('div');

    /**
     * 循环取出 quoteBody 的 值
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

            if((inputs[j].id).indexOf("itemName") >=0){
                if(inputs[j].value==""){
                    $.modalAlert("item Name  is empty", "error");
                    return false;
                }
                quoteItem['body['+itemIndex+'].itemName']=inputs[j].value;

            }
            if((inputs[j].id).indexOf("itemCode") >=0){
                if(inputs[j].value==""){
                    $.modalAlert("item Code   is empty", "error");
                    return false;
                }
                quoteItem['body['+itemIndex+'].itemCode']=inputs[j].value;

            }
            if((inputs[j].id).indexOf("price") >=0){
                if(!(f.test(inputs[j].value))){
                    $.modalAlert("price data is Invalid", "error");
                    return false;
                }
                quoteItem['body['+itemIndex+'].price']=inputs[j].value;

            }
            if((inputs[j].id).indexOf("quantity") >=0){
                if(!(I.test(inputs[j].value))){
                    $.modalAlert("quantity data is Invalid", "error");
                    return false;
                }
                quoteItem['body['+itemIndex+'].quantity']=inputs[j].value;

            }

        }

    }

    $.ajax({
        cache : true,
        type : "POST",
        url : "/sales/quote/save",
        dataType: "json",
        data :quoteItem,
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
