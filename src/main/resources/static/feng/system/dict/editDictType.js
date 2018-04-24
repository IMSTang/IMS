
var Element_index = 101;
/**
 * 默认界面上显示一行data
 */
// addElement();

/**
 * 添加一行data
 */
$("#add").click(addElement);
function addElement(){

    /**
     * div
     */
    var div = document.createElement('div');
    div.style.height = '34px';
    div.style.padding= '0 10px 0 16px';
    div.id="div"+Element_index;
    var clearDiv =document.createElement("div");
    clearDiv.className = 'clear';

    /**
     * dictCode
     * */
    var TId=document.createElement("input");
    TId.type="hidden";
    TId.name="dictCode"+Element_index;
    TId.id="dictCode"+Element_index;

    /**
     * dictLabel
     * */
    var TdictLabel=document.createElement("input");
    TdictLabel.type="text";
    TdictLabel.className = 'col-sm-4 outline';
    TdictLabel.style.margin= '0 -1px';
    TdictLabel.name="dictLabel"+Element_index;
    TdictLabel.id="dictLabel"+Element_index;

    /**
     * dictValue
     */
    var TdictValue=document.createElement("input");
    TdictValue.type="text";
    TdictValue.className = 'col-sm-3 outline';
    TdictValue.style.margin= '0 -1px';
    TdictValue.name="dictValue"+Element_index;
    TdictValue.id="dictValue"+Element_index;

    /**
     * dictSort
     */
    var TdictSort=document.createElement("input");
    TdictSort.type="text";
    TdictSort.className = 'col-sm-2 outline';
    TdictSort.style.margin= '0 -1px';
    TdictSort.name="dictSort"+Element_index;
    TdictSort.id="dictSort"+Element_index;

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

    div.appendChild(TId);
    div.appendChild(TdictLabel);
    div.appendChild(TdictValue);
    div.appendChild(TdictSort);
    div.appendChild(TButton);
    div.appendChild(clearDiv);
    document.getElementById("tableDiv").appendChild(div);
    Element_index++;
}

/***
 * 删除一行data
 */
function delButton(divId) {
    var div = document.getElementById(divId);
    var parentDiv =  document.getElementById('tableDiv');
    if( parentDiv.childElementCount>1){
        parentDiv.removeChild(div);
    }
}

$("#form-dict-edit").validate({
	rules:{
		dictName:{
			required:true,
		},
		dictType:{
			required:true,
			minlength: 5,
			remote: {
                url: "/system/dict/checkDictTypeUnique",
                type: "post",
                dataType: "text",
                data: {
                	dictId : function() {
                        return $.trim($("#dictId").val());
                    },
                	dictType : function() {
                        return $.trim($("#dictType").val());
                    }
                },
                dataFilter: function(data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
	},
	messages: {
        "dictType": {
            remote: "The Dict Type already exist"
        }
    },
	submitHandler:function(form){
		update();
	}
});

function update() {
    var  dictItem = new Object();

	var dictId = $("input[name='dictId']").val();
	var dictName = $("input[name='dictName']").val();
	var dictType = $("input[name='dictType']").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var remark = $("input[name='remark']").val();

    dictItem.dictId=dictId;
    dictItem.dictName=dictName;
    dictItem.dictType=dictType;
    dictItem.status=status;
    dictItem.remark=remark;

    var parentDiv =  document.getElementById('tableDiv');
    //获取div个数
    var divNum=  parentDiv.getElementsByTagName('div');

    /**
     * 循环取出 Body 的 值
     */
    var itemIndex=-1;
    for( i=0;i<divNum.length;i++){
        //忽略 clear div
        if(divNum[i].className != "clear"){
            itemIndex++;
        }
        var inputs = divNum[i].getElementsByTagName('input');

        for (j=0;j<inputs.length;j++){
            //不获取按钮的值
            if(inputs[j].value=="Remove"){
                // continue;
            }
            if((inputs[j].id).indexOf("dictCode") >=0){
                dictItem['dictData['+itemIndex+'].dictCode']=inputs[j].value;
            }
            if((inputs[j].id).indexOf("dictLabel") >=0){
                dictItem['dictData['+itemIndex+'].dictLabel']=inputs[j].value;
            }
            if((inputs[j].id).indexOf("dictValue") >=0){
                dictItem['dictData['+itemIndex+'].dictValue']=inputs[j].value;
            }
            if((inputs[j].id).indexOf("dictSort") >=0){
                dictItem['dictData['+itemIndex+'].dictSort']=inputs[j].value;
            }
        }

    }


    $.ajax({
		cache : true,
		type : "POST",
		url : "/system/dict/save",
		data : dictItem,
		async : false,
		error : function(request) {
			$.modalAlert("System ERROR", "error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("Updated successfully, on refreshing ……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					$.parentReload();
				});
			} else {
				$.modalAlert(data.msg, "error");
			}
		}
	});
}