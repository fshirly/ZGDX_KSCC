define(["base"],function(base){

		var loginUserId='';
	    var roleName;
		var roleCode;

		function userId(){
			$.ajax({
	            type:"post",
	            async: false,
	            url:$.base+"/liveBroadCastController/getUserId",
	            success:function (data) {
	            	   loginUserId=data.id;
					   roleName=data.roleName;
					   roleCode=data.roleCode;
                       setCarousel();
	            }
			});
		};	
   
		 function setCarousel(){
			 $.ajax({
	               type: "POST",
	               url:  $.base+"/liveController/getLivingList",
                   dataType:'json',
                   contentType:"application/json",
				   data:roleName=='kscc管理员'?JSON.stringify({}):JSON.stringify({userId:loginUserId}),
	               success: function(data){
	            	   $(".liveManage").empty();
	            	   $("#carouselCircleManage").empty();
	            	   var dataList=data.data;
	            	   var notFinished=[];//未结束直播
	            	   var finished=[];//已结束直播
	            	   $.each(dataList,function(i,v){
	            		   if(v.playStatus=="2"){
	            			   finished.push(v);
	            		   }else{
	            			   notFinished.push(v);
	            		   }
	            	   });
	            	   setBannerArray(notFinished,"1");//未结束先渲染
	            	   setBannerArray(finished,"2");//结束后渲染
	            	   $(".liveManage .item:first").addClass("active");
	            	   var circleLength=$(".liveManage").find(".item").length;
	            	   for (var i=0;i<circleLength;i++){
	            		   $("#carouselCircleManage").append("<li data-target='#myCarouselManage' data-slide-to='"+i+"'></li>");
	            	   }
	            	   $("#carouselCircleManage").children(":first").addClass("active");
	            	   $('#myCarouselManage').carousel({
	                       pause: true,
	                       interval: false
	                   });
	            	   
	            	   setEnter();
	               }
			 });
		 };
		 
		 function setBannerArray(dataArray,flag){
			 var childs=new Array();
    		 var lis="";
    	     var liveIntroduction="";
    	     var enterLiveStr="";
    	     var listFlag="";
			 $.each(dataArray,function(i,v){
      		   var child = "";
      		   var picPath="";//图片变量
      		   if(v.picturePath){
                 	picPath=$.base+"/loginController/showPic?name="+v.picturePath;
               }else{
                 	picPath=$.base+"/images/pages/changePic.png";
               }
      		   $.each(v.participantNames,function(x,y){
      			   if(y.serialNumber==1){
      				   child+="<li att='"+y.id+"' class='controlListLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+y.hospitalName+"'>"+y.hospitalName+"</span><span class='iconfont iconfontImg' style='color:#ee453b;'>&#xe61c;<span style='font-size:12px;margin-left:5px;'>主持人</span></span></li>";
      			   }else{
      				   child+="<li att='"+y.id+"' class='controlListLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+y.hospitalName+"'>"+y.hospitalName+"</span></li>";
      			   }
      		   });
      		   childs.push(child);	
      		   if(v.liveIntroduction==null){
      			   liveIntroduction="";
      		   }
      		   if(flag=="2"){
      			   enterLiveStr="<button class='iconfont noEnterBtn' title='已结束直播，不可进入' disabled>&#xe669;</button>";
      			   listFlag="finished";
      		   }else{
  				   enterLiveStr="<a att='"+v.id+"' confId='"+v.confId+"' class='iconfont enterLive' title='进入直播'><span class='enterDiv'>进入直播</span>&#xe62b;</a>";
  				   listFlag="notFinished";
      		   }
      		   var index=Math.ceil((i+1)/2);
      		   lis+="<li class='carousel_"+listFlag+" carousel_"+listFlag+"_"+index+" controlLi'>"+
      		          "<div class='controlDiv'>"+
      		             "<div class='row clearfix'>"+
          		             "<div class='col-xs-8 col-sm-8 col-md-8'>"+
          		                  "<div class='introImg'><img src='"+picPath+"'></div>"+
          		                  "<div class='introTxt'>"+
	            		                "<h5 class='titleH titleLength titleWidth' style='margin-bottom:0;margin-top:-5px;' title='"+v.title+"'>"+v.title+"</h5>"+
	            		                 "<div class='introductionH'>"+v.liveIntroduction+"</div>"+
	            		              "</div>"+
	            		         "</div>"+
          		             "<div class='col-xs-4 col-sm-4 col-md-4'>"+
          		                  "<h5 class='titleH' style='border-bottom:1px solid #00479d;'>参与方列表</h5>"+
          		                  "<ul class='partiList partiList_"+i+"' style='height:130px;overflow:auto;'>"+childs[i]+"</ul>"+
          		             "</div>"+
          		          "</div>"+enterLiveStr+"</div>"+
  		            "</li>";
      	   });
      	   $(".liveManage").append(lis);
      	   for (var i=0;i<Math.ceil(dataArray.length/2);i++){
      		   $(".liveManage .carousel_"+listFlag+"_"+(i+1)+"").wrapAll("<ul class='carouselUl_"+listFlag+" carouselUl_"+listFlag+"_"+(i+1)+"'></ul>");
      		   $(".liveManage .carouselUl_"+listFlag+"_"+(i+1)+"").wrap("<div class='item item_"+listFlag+"'></div>");
      	   }
      	   setScroll();
		 }
		 
		 function setEnter(){
			 $(".enterLive").unbind().on("click",function(){
				 var id=$(this).attr("att");
				 var confId=$(this).attr("confId")
				 var requestTip=base.requestTip({position:"center"});
				 $.ajax({
		               type: "GET",
		               async:false,
		               url:  $.base+"/liveBroadCastController/getMts/"+confId+"/"+loginUserId+"/"+roleCode,
		               success: function(data){
		            	   if(data.status==='1'){
		            		    var url=$.base+"/loginController/toLiveControlDetail";
								$.ajax({ 
					                type:"GET", 
					                url:url, 
					                error:function(){ 
					                   alert("加载错误！"); 
					                }, 
					                success:function(data){
                                        if(data.indexOf('06a5bb21-b8f0-4dfd-8004-4b4e17d4f81c')!==-1){
                                            window.location.href=$.base+'/loginController/toLogin'
                                            return
                                        }
                                        $(".middle").html(data);
					                } 
					             });
								$("#liveIdControlDetail").val(id);
						   }
						   else{
		            	   	requestTip.error(data.tips);
						   }
		               }
				 });
			 })
		 }
		 
		 function setScroll(){
			var height = parseInt($(".middleContent").height()-30) * 0.5;
			$("#myCarouselManage").height(height*2);
			//$(".controlLi").css({"margin-top":(height-195)/2,"margin-bottom":(height-195)/2,"margin-left":"auto","margin-right":"auto"});
			var notFinishedLen=$(".item_notFinished:last-child").find(".controlLi").length;
			var finishedLen=$(".item_finished:last-child").find(".controlLi").length;
			var notLength=$(".item_notFinished").length;
			var length=$(".item_finished").length;
			for(var i=0;i<notLength-1;i++){
				$(".item_notFinished").eq(i).find(".controlLi").css({"margin-top":(height-195)/2,"margin-bottom":(height-195)/2,"margin-left":"auto","margin-right":"auto"});
			}
			if(notFinishedLen==1){
				$(".item_notFinished:last-child").find(".controlLi").css({"margin-top":(height*2-195)/2,"margin-bottom":(height*2-195)/2,"margin-left":"auto","margin-right":"auto"});
			}else{
				$(".item_notFinished:last-child").find(".controlLi").css({"margin-top":(height-195)/2,"margin-bottom":(height-195)/2,"margin-left":"auto","margin-right":"auto"});
			}
			for(var i=0;i<length-1;i++){
				$(".item_finished").eq(i).find(".controlLi").css({"margin-top":(height-195)/2,"margin-bottom":(height-195)/2,"margin-left":"auto","margin-right":"auto"});
			}
			if(finishedLen==1){
				$(".item_finished:last-child").find(".controlLi").css({"margin-top":(height*2-195)/2,"margin-bottom":(height*2-195)/2,"margin-left":"auto","margin-right":"auto"});
			}else{
				$(".item_finished:last-child").find(".controlLi").css({"margin-top":(height-195)/2,"margin-bottom":(height-195)/2,"margin-left":"auto","margin-right":"auto"});
			}
			base.scroll({
				container:".partiList"
			});
		 }
		 
		return {
			run:function(){
				userId();
			}
		};
});
