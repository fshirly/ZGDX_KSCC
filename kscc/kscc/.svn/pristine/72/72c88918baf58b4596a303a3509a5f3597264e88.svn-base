define(["base"],function(base){
	var path=$.base;
	
	function getUserId(){
        $.ajax({
            type:"post",
            async: false,
            url:$.base+"/liveBroadCastController/getUserId",
            success:function (data) {
            	var roleName=data.roleName;
            	$("#roleName").val(roleName);
            	$("#createIdEdit").val(data.id);
            	$("#hospitalId").val(data.hospitalId);
            	getMenu(data.id);
            	setQuit();
	        }
        });
    };
	
    function getMenu(id){
    	$.ajax({
            type:"post",
            async: false,
            url:$.base+"/FbsMenuController/findMenuAllByUserId",
            contentType:'application/json',
            data:JSON.stringify({"userId":id}),
            success:function (data) {
            	if(data.status=="1"){
            		$("#topLi").empty();
            		$("#bottomLi").empty();
            		var loginName=data.data.loginName;
                	$("#userName").text(loginName).attr("title",loginName);
                	/*头部固定菜单*/
                	var basicStrs="<li title='主页' class='active'><a att='"+path+"/loginController/toHomePage'><i class='iconfont iconTop' alt='主页'>&#xe66f;</i><span class='topName'>主页</span></a></li>"+
				                  "<li title='账户'><a att='"+path+"/loginController/toAccountManage'><i class='iconfont iconTop' alt='账户'>&#xe614;</i><span class='topName'>账户</span></a></li>"+
					              "<li title='消息'><a att='"+path+"/loginController/toMessageCenter' class='msgA'><span class='msgNum'></span><i class='iconfont iconTop' alt='消息'>&#xe6f4;</i><span class='topName'>消息</span></a></li>"+
					              "<li title='状态'><a att=''><i class='iconfont iconTop' alt='状态'>&#xe633;</i><span class='topName'>状态</span></a></li>";
                	$("#topLi").append(basicStrs);
                	var topArr=[];var bottomArr=[];
                	$.each(data.data.menusList,function(i,v){
                		if(parseInt(v.id)>=10){
                			topArr.push(v);
                		}else if(parseInt(v.id)<=5){
                			bottomArr.push(v);
                		}
                	});
                	/*循环出头部菜单*/
                	$.each(topArr,function(i,v){
                		if(v.hostRole=="1"){
                			var menuTopStr="<li><a att='"+path+v.url+"'><i class='iconfont iconTop'>"+v.icon+"</i><span class='topName'>"+v.name+"</span></a></li>";
                			$("#topLi").append(menuTopStr);
                		}
                	});
                	/*循环出底部菜单*/
                	$.each(bottomArr,function(i,v){
                		if(v.hostRole=="1"){
                			var menuBottomStr="<li><a att='"+path+v.url+"'><i class='iconfont iconBottom'>"+v.icon+"</i><span class='bottomName'>"+v.name+"</span></a></li>";
                			$("#bottomLi").append(menuBottomStr);
                		}
                	});
            	}
            }
    	});
    }
    /*退出*/
	function setQuit(){
		$(".quit").unbind().on("click",function(){
			base.confirm({ 
		    	  label:"提示",
		    	  text:"<div style='text-align:center;font-size:13px;'>确定退出系统吗?</div>",
	              confirmCallback:function(){
	            	  window.location.href=$.base+"/loginController/logout";
	              }
			});
		});
	}
    
    function setLevelMenu(){
    	$("#topLi li,#bottomLi li").off().on("click",function(){
    		var url=$(this).find("a").attr("att");
    		$("#topLi li,#bottomLi li").removeClass("active");
    		$(this).addClass("active");
    		if(url){
    			$.ajax({ 
	                type:"GET", 
	                url:url, 
	                error:function(){ 
	                   alert("加载错误！"); 
	                }, 
	                success:function(data){
						$(".middle").html("");
                        if(data.indexOf('06a5bb21-b8f0-4dfd-8004-4b4e17d4f81c')!==-1){
                            window.location.href=$.base+'/loginController/toLogin'
                            return
                        }
                        $(".middle").html(data);
	                } 
	             });
    		}
    	});
    }
    
    function setBanner(){
    	var itemStrs="";
    	var olStrs="";
    	$.ajax({
            type:"post",
            async: false,
			contentType:'application/json',
            url:$.base+"/liveBroadCastController/selectHomePage",
            success:function (e) {
            	if(e.status=='1'){
            		var pitureList=e.data
                    $(".carouselHome .carousel-indicators").empty();
                    $(".carouselHome .carousel-inner").empty();
                    $.each(pitureList,function(i,v){
                    	if(v.networkUrl){
                            if(v.networkUrl.indexOf("http")!==-1){
                                itemStrs+="<div class='item'>"+
                                    "<a href='"+v.networkUrl+"' target='_blank'><img src='"+$.base+"/loginController/showPic?name="+v.imageUrl+"'></a>"+
                                    "</div>";
                            }
                            else{
                                itemStrs+="<div class='item'>"+
                                    "<a href='https://"+v.networkUrl+"' target='_blank'><img src='"+$.base+"/loginController/showPic?name="+v.imageUrl+"'></a>"+
                                    "</div>";
                            }
						}
						else{
                            itemStrs+="<div class='item'>"+
                                "<img src='"+$.base+"/loginController/showPic?name="+v.imageUrl+"'>"+
                                "</div>";
						}
                        olStrs+="<li data-target='#myCarouselHome' data-slide-to='"+i+"'></li>";
                    });
                    $(".carouselHome .carousel-inner").append(itemStrs);
                    $(".carouselHome .item:first").addClass("active");
                    $(".carouselHome .carousel-indicators").append(olStrs);
                    $(".carouselHome .carousel-indicators").children(":first").addClass("active");
					$('#myCarouselHome').carousel({
						pause: true,
						interval: 5000
					});
				}
            },
            error:function(){}
    	});
    }
    function checkBrowserIsCloseOrNot() {
    	// setInterval(poolFunction,2000)
    }
    var poolFunction=function () {
        $.ajax({
            type:"post",
			contextType:'application/json',
            url:$.base+"/loginController/longLink/"+new Date().getTime()
        })
    }

    //获取消息信息数量
    function getMessageNum() {
        param={
            //"liveId":$("#liveIdControlDetail").val(),
            //"addressee":-1,
            "status":1 //未读信息
        }
        $.ajax({
            type: "post",
            url:  $.base + "/messageContorller/queryExtendCount",
            data:JSON.stringify(param),
            contentType:"application/json",
            success: function(data){
                if(data!=0){
                    $(".msgNum").show();
                    $(".msgNum").text(data)
                }else{
                    $(".msgNum").hide();
                }
            }
        });
    }

	return {
		run:function(){
			getUserId();//获得当前登录人id
			setLevelMenu();
			setBanner();
            checkBrowserIsCloseOrNot()
			/*一分钟刷新一下消息数量*/
            setInterval(getMessageNum,6000)
		}
	};
});
