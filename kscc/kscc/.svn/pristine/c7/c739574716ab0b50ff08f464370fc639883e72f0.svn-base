/**
 * Created by Administrator on 2017/4/19 0019.
 */
var commonFun=new Common();
function Common(){};
Common.prototype.getMenu=function(){
    $.ajax({
        type: "GET",
        async: true,
        url:  $.base + "/sys/loginUser/menus",
        dataType: "json",
        success: function(data){
            data = data.sort(function(a,b){
                return Number(a.pid)-Number(b.pid);
            });
            $.each(data,function(i,v){
                if(v.pid!="0"){
                    var pnode=$("#urlContent").find("a[mid='"+v.pid+"']");
                    if(pnode.length>0){
                        var pnodeUl = pnode.parent().find("ul");
                        if(pnodeUl.length>0){
                            pnodeUl.append("<li><a mid='"+v.id+"' href='"+$.base+v.url+"'>"+"<span class='glyphicon glyphicon-tags menuIcon'></span>"+v.name+"</a></li>")
                        }else{
                            $(pnode).parent().append("<ul class='dropdown-menu'><li><a mid='"+v.id+"' href='"+$.base+v.url+"'>"+"<span class='glyphicon glyphicon-tags menuIcon'></span>"+v.name+"</a></li></ul>")
                        }
                    }else{
                        var pname = "";
                        $(data).each(function(i1,v1){
                            if(v1.id==v.pid){
                                pname = v1.name;
                                return false;
                            }
                        });
                        $("#urlContent").append("<li class='dropdown'>"+
                            "<a mid='"+v.pid+"' href='#' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'>"+pname+" <span class='caret'></span></a>"+"<ul class='dropdown-menu'>" +
                            "<li><a mid='"+v.id+"' href='"+$.base+v.url+"'>"+"<span class='glyphicon glyphicon-tags menuIcon'></span>"+v.name+"</a></li>"+
                            "</ul></li>");
                    }
                }
            });
            login();
        }
    });
};
Common.prototype._tipMessage = function(message){
    return "<div class='errorTip text-left'><i class='ion-android-cancel'></i>"+message+"</div>"
};
function login(){
    $.ajax({
        url:$.base+"/stiservice/servCenter/casLoginUrl",
        type:"get",
        success:function(data){
            strs=data.split(",");
            $("#urlContent").append("<li><a href='#' style='color:#b9b6b6!important;'><span class='glyphicon glyphicon-user menuIcon'></span>"+strs[1]+"</a></li>");
            $("#urlContent").append("<li><a href='"+$.base+"/sys/security/logout'>退出 </a></li>");
        }
    });
}
//提示弹框
Common.prototype.tipModal=function(message){
    var modal = BaseApp.createModal({
        modalId:"modal",
        title:"提示",
        cls:"modal-dialog",
        size:"sm",
        buttons:[
            {"name":"cancel","title":"确定"}
        ]
    });
    $(modal.modalContainer).html(message);
};
Common.prototype.checkAll=function(all,single,checked){
    if(checked.length==10){
        all.prop("checked",true);
    }else{
        all.prop("checked",false);
    }
    all.off().on("change",function(){
        if(all.is(":checked")){
            single.prop("checked",true);
            single.each(function(index,item){
                checkedIds.push($(item).attr("data-id"));
            })
        }else{
            single.prop("checked",false);
            single.each(function(index,item){ //去掉去选按钮
                if($.inArray($(this).attr("data-id"),checkedIds)!=-1){
                    checkedIds.splice($.inArray($(this).attr("data-id"),checkedIds),1);
                }
            });
        }
    });
    single.off().on("click",function(){
        if($(this).is(":checked")){
            checkedIds.push($(this).attr("data-id"));
        }else{
            if($.inArray($(this).attr("data-id"),checkedIds)!=-1){
                checkedIds.splice($.inArray($(this).attr("data-id"),checkedIds),1);
            }
        }
    });
    //all.off().on("click",function(){
    //    if(all.is(":checked")){
    //        single.prop("checked",true)
    //    }else{
    //        single.prop("checked",false)
    //    }
    //});
};
Common.prototype.backChange=function(all,single){
    var count=0;
    single.each(function(index,item){
        if($.inArray($(item).attr("data-id"),checkedIds)!=-1){
            $(item).prop("checked",true);
            count++;
        }
    });
    if(count==10){
        all.prop("checked",true);
    }else{
        all.prop("checked",false);
    }
};
Common.prototype.noteLength=function(note,len){
    if(len.length>500){
        note.css("border-color","#ff0000");
        note.parent().append(commonFun._tipMessage("描述的内容不能超过500"));
        return false;
    }else{
        note.parent().find(".errorTip").remove();
        note.css("border-color","#ccc");
    }
};
