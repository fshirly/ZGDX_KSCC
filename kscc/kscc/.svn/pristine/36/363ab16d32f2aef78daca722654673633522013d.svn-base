define(["base"],function(base){
	var treeObj; 
	var ids=[];//提交的参与方id
	var nodesObj=[];//提交的参与方对象
	var belongId;
	var belongName;
	var startTime;
	var endTime;
	var modal;
	var filePath="";
	var fileName="";
	
	$("#liveParticipant").empty();
	
	//获得申请人所属医院
	function affiliatedHospital(){
		$.ajax({
        	url:$.base+"/hospital/getHospitalInfoByUser",
        	type:"GET",
        	async:false,
        	success:function(data){
        		if(data.id){
        			belongId=data.id.toString();
            		belongName=data.hospitalName;
            		ids.push(belongId);
        			nodesObj.push({
        				"hospitalId":belongId,
        			    "hospitalName":data.hospitalName,
        			    "serialnumber":1,
						"ishost":1
        			});
	        		$("#liveParticipant").append("<li att='"+data.id+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+data.hospitalName+"'>"+data.hospitalName+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
        	    }
        	},
        	error:function(e){}
		 });
	  }

		//参与方模态框
		function selectParticipant(){
			$(".addParticipant").off().on("click",function(){
				startTime=$("#startTime").val();//开始时间
				endTime=$("#endTime").val();//结束时间
				var startTimeTmp = startTime.replace(new RegExp("-","gm"),"/");
			    var startTimeHaoMiao = (new Date(startTimeTmp)).getTime();
			    var endTimeTmp = endTime.replace(new RegExp("-","gm"),"/");
			    var endTimeHaoMiao = (new Date(endTimeTmp)).getTime();
				if(startTimeHaoMiao>=endTimeHaoMiao){
					$("#tipMessage4").show();
					return
				}else{
					$("#tipMessage4").hide();
				}
				if(startTime!=""&&endTime!=""){
					modal=base.modal({
						width:630,
						height:420,
						label:"选择参与方",
						url:$.base+"/loginController/toPartModal",
						callback:function(){
							setPartTree();//参与方树
							setParModal();//初始化右边模态框
						},
						buttons:[ 
						            { 
						               label:"确定", 
						               cls:"btn btn-info", 
						               clickEvent:function(){
						            	   savePartiModal();
						               }
						            },
						            { 
						               label:"取消", 
						               cls:"btn btn-info", 
						               clickEvent:function(){ 
						            	   setCancel();
						            	   modal.hide();
						               }
							        }
						         ]
					});
				}else{
					base.alert("开始时间，结束时间不能为空!");
				}
			});
		 }
		
		 function setCancel(){
		   ids=[];
      	   nodesObj=[];
      	   var len=$("#liveParticipant").find("li").length;
      	   for(var i=0;i<len;i++){
      		   var idsLi=$("#liveParticipant li:eq('"+i+"')").attr("att");
      		   ids.push(idsLi);
      		   var serialnumber;
      		   var ishost;
      		   if(i==0){
      			   serialnumber=1;
      			   ishost=1;
      		   }else if(i==1){
      			   serialnumber=2;
      			   ishost=2;
      		   }else{
      			   serialnumber=3;
      			   ishost=2;
      		   }
      		   nodesObj.push({
      			   "hospitalId":idsLi,
           		   "hospitalName":$("#liveParticipant li:eq('"+i+"') .controlParti").text(),
           		   "serialnumber":serialnumber,
                   "ishost":ishost 
      		   });
      	   }
		 }
		 function setParModal(){
			 setCancel();
			 $("#selectResult").empty();
			 $.each(nodesObj,function(i,v){
				if(v.serialnumber==1){
					$("#selectResult").append("<li att='"+v.hospitalId+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
				}else if(v.serialnumber==2){
					$("#selectResult").append("<li att='"+v.hospitalId+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' title='第二主持人'>&#xe61c;</span></li>");
				}else if(v.serialnumber==3){
					$("#selectResult").append("<li att='"+v.hospitalId+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan'>"+v.hospitalName+"</span></li>");
				}
			 });
			 $("#selectNum").text($("#selectResult").find("li").length);
    		 setLi();
		 }
		 function setPartTree(){
			//获得医院列表数据
			$.ajax({
				   type: "GET", 
	               async: true, 
	               url:  $.base + "/hospital/selectHospitalInfo", 
	               dataType: "json",
	               success: function(data){
	            	 setZtree(data);//渲染医院列表
	               }
		      });
		 }
		
		var setting = {
				view: {
					selectedMulti:true,	
				},
				data: {
					simpleData: {
						enable: true,
						pIdKey: "pid"
					}
				}
		  };
		function setZtree(zNodes){
			require(["bootstrap","ztreeCore","ztreeExcheck","ztreeExedit"],function(){
				$.fn.zTree.init($("#treeDemo1"), setting, zNodes);
				treeObj = $.fn.zTree.getZTreeObj("treeDemo1");
				treeObj.expandAll(true);
				setSelect(zNodes);
				searchTree();//模态框医院列表数据搜索
				setSearchEnter();
			});
		}
		
		function searchTree(){
			$("#searchBtnModel").off().on("click",function(){
				setSearchModal();
			});
		}
		
		function setSearchEnter(){
			$("#searchParticipantModal").keydown(function(e){
			    var ev= window.event||e;
			    //13是键盘上面固定的回车键
			    if (ev.keyCode == 13) {
			    	setSearchModal();//执行搜索方法
			    }
			  });
		}
		
		function setSearchModal(){
			$.ajax({
	        	url:$.base+"/hospital/findAllHospitalAndUser",
	        	type:"POST",
	        	contentType:"application/json",
                dateType:"json",
                data:JSON.stringify(
                    {
                        "searchCon":$("#searchParticipantModal").val()
                    }
				),
	        	success:function(data){
	            	 setZtree(data);//渲染医院列表
	        	},
	        	error:function(data){}
			});
		}
		
		//左右选择框
		function setSelect(zNodes){
			//从左往右单选
			$("#LTRSingle").off().on("click",function(){
				var sNodes = treeObj.getSelectedNodes();
				if(sNodes){
					if (sNodes.length > 0) {
						var node = sNodes[0].getParentNode();
					}
					var importId;
					if(node!=null){
						importId=node.id;
					}
					else{
						importId=sNodes[0].id;
					}
					var params={
							"startTime":startTime,
							"endTime":endTime,
							"ids":importId,
					}; 
					scheduleCheck(params,belongId,belongName);
				}
			});
			
			//从右往左单选
			$("#RTLSingle").off().on("click",function(){
				setLi();
				var selectLiId=$("#selectResult li.cur").attr("att");
				if(selectLiId){
					if(selectLiId!=belongId){
						var index=$.inArray(selectLiId,ids);
						ids.splice(index,1);
						nodesObj.splice(index,1);
						
						$("#selectResult").empty();
						$.each(nodesObj,function(i,v){
							if(i==0){
								v.serialnumber=1;
								$("#selectResult").append("<li att='"+v.hospitalId+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan' title='"+v.hospitalName+"'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
							}else if(i==1){
								v.serialnumber=2;
								$("#selectResult").append("<li att='"+v.hospitalId+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan' title='"+v.hospitalName+"'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' title='第二主持人'>&#xe61c;</span></li>");
							}else{
								v.serialnumber=3;
								$("#selectResult").append("<li att='"+v.hospitalId+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan' title='"+v.hospitalName+"'>"+v.hospitalName+"</span></li>");
							}
		            	});
						$("#selectNum").text($("#selectResult").find("li").length);
						setLi();
					}else{
						base.alert("不可移除自身医院！");
					}
				}
			});
		}

		function savePartiModal(){
			if(ids.length==0){
				base.alert("请选择第一、第二主持人！");
			}
			else if(ids.length==1){
				base.alert("请再选择一个主持人");
			}else{
				modal.hide();
                $("#liveParticipant").empty();
                
				$.each(nodesObj,function(i,v){
					if(v.serialnumber==1){
						$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.hospitalName+"'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
					}else if(v.serialnumber==2){
						$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.hospitalName+"'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' title='第二主持人'>&#xe61c;</span></li>");
					}else if(v.serialnumber==3){
						$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.hospitalName+"'>"+v.hospitalName+"</span></li>");
					}
            	});
			}
		}
		
		function setLi(){
			if($("#selectResult li").length>0){
				$("#selectResult li").click(function(){
					$(this).addClass("cur").siblings().removeClass("cur");
				});
			}
		}
		
		//日程校验
		function scheduleCheck(params,belongId,belongName){
			$.ajax({
				type: "POST", 
	            url:  $.base + "/hospital/checkHospital", 
	            async:false,
	            data: params,
	            success: function(data){
	            	if(data[0].nonConformity){
	            		base.alert("直播时间冲突！");
	            	}
	            	var effectiveList=data[0].list;
	            	if(effectiveList){
	            	if(effectiveList.length>0){
	                	 if(ids.length==0){
	                		ids.push(effectiveList[0].id.toString());
	                		nodesObj.push({
	                			"hospitalId":effectiveList[0].id.toString(),
	                			"hospitalName":effectiveList[0].hospitalName,
	                			"serialnumber":1,
								"ishost":1
	                		});
	                		$("#selectResult").append("<li class='partisLimodal' att='"+effectiveList[0].id+"'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan' title='"+effectiveList[0].hospitalName+"'>"+effectiveList[0].hospitalName+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
	                	 }
	                	 else if(ids.length==1){
	                		 if($.inArray(effectiveList[0].id.toString(),ids)==-1){
	 		                		ids.push(effectiveList[0].id.toString());
	 		                		nodesObj.push({
	 		                			"hospitalId":effectiveList[0].id.toString(),
	 		                			"hospitalName":effectiveList[0].hospitalName,
	 		                			"serialnumber":2,
                                        "ishost":2
	 		                		});
	 		                		$("#selectResult").append("<li att='"+effectiveList[0].id+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan' title='"+effectiveList[0].hospitalName+"'>"+effectiveList[0].hospitalName+"</span><span class='iconfont iconfontImg' title='第二主持人'>&#xe61c;</span></li>");
 		                	 }else{
 		                		base.alert("已选择，不可重复添加！");
 		                	 }
	                	 }
	                	 else{
	                		 if($.inArray(effectiveList[0].id.toString(),ids)==-1){
	 		                		ids.push(effectiveList[0].id.toString());
	 		                		nodesObj.push({
	 		                			"hospitalId":effectiveList[0].id.toString(),
	 		                			"hospitalName":effectiveList[0].hospitalName,
	 		                			"serialnumber":3,
                                        "ishost":2
	 		                		});
	 		                		$("#selectResult").append("<li att='"+effectiveList[0].id+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan' title='"+effectiveList[0].hospitalName+"'>"+effectiveList[0].hospitalName+"</span></li>");
	 		                  }else{
	 		                	  base.alert("已选择，不可重复添加！");
	 		                  }
	                	    }
	 	                }
	            	$("#selectNum").text($("#selectResult").find("li").length);
	            	setLi();
	              }
	            }
	       });
		}
		
		//提交
		function submitForm(){
			$("#submitBtn").off().on("click",function(){
			base.form.validate({ 
                 form:$("#createLiveForm"), 
                 passCallback:function(){ 
					var liveName = $("#liveName").val();
					var pattern = new RegExp("[`~!@#$^&*()=|{}' :;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
					if(pattern.test(liveName)) {
						$("#tipMessage5").show();
						return false;
					}else{
						$("#tipMessage5").hide();
					}
					var startTimeTmp=$("#startTime").val();
					var endTimeTmp=$("#endTime").val();
					var startTime = startTimeTmp.replace(new RegExp("-","gm"),"/");
				    var startTimeHaoMiao = (new Date(startTime)).getTime();
				    var endTime = endTimeTmp.replace(new RegExp("-","gm"),"/");
				    var endTimeHaoMiao = (new Date(endTime)).getTime();
					if(startTimeHaoMiao>=endTimeHaoMiao){
						$("#tipMessage4").show();
						return false;
					}else{
						$("#tipMessage4").hide();
					}
					$("#tipMessage1").hide();			
					var phoneStr=$("#phone").val();
					var result=phoneStr.match(/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/);
					if(result==null){ 
						$("#tipMessage1").show();
					}else{
						$("#tipMessage1").hide();
						if(ids.length<2){
							base.confirm({ 
						    	  label:"提示",
						    	  text:"<div style='text-align:center;font-size:13px;'>请保证至少有两个参与方！</div>"
			   				});
						}else{
							//日程校验
							var data={
									"startTime":$("#startTime").val(),
									"endTime":$("#endTime").val(),
									"ids":ids.join(",")
							};
							$.ajax({
								type: "POST", 
					            url:  $.base + "/hospital/checkHospital", 
					            async:false,
					            data: data,
					            success: function(data){
					            	if(data[0].nonConformity){
					            		base.confirm({ 
									    	  label:"提示",
									    	  text:"<div style='text-align:center;font-size:13px;'>"+data[0].nonConformity+"      直播时间冲突！</div>"
						   				});
					            	}
					            	else{
				   					     base.confirm({ 
				   					    	  label:"提示",
				   					    	  text:"<div style='text-align:center;font-size:13px;'>确定提交?</div>",
				   				              confirmCallback:function(){
				   				            	  var createParam={
				   				            		"title":$("#liveName").val(),
				   				            		"departmentId":$("#department").find("option:selected").val(),
				   				            		"startTime":$("#startTime").val(),
				   				            		"endTime":$("#endTime").val(),
				   				            		"linkMan":$("#linkMan").val(),
				   				            		"phone":$("#phone").val(),
				   				            		"email":$("#email").val(),
				   				            		"hospitalWebsite":$("#hospitalURL").val(),
				   				            		"liveIntroduction":$("#liveIntroduction").val(),
				   				            		"participants":nodesObj,
				   				            		"filePath":filePath,
					   								"file_name":fileName,
													"creatorId":$("#createIdEdit").val(),
													"userId":$("#createIdEdit").val()
				   				            	  }
				   				            	var requestTip=base.requestTip({position:"center"});
				   				            	$.ajax({
								                	url:$.base+"/liveBroadCastController/createLiveApplications",
										        	type:"POST",
										        	contentType:"application/json",
										        	data:JSON.stringify(createParam),
										        	success:function(data){
										        		var url="";
								                		if(data.status==='1'){
								                			$("#tipMessage1").hide();
								                			var roleName=$("#userName").text();
								                			if(roleName=="管理员"||roleName=="admin"){
								                				url=$.base+"/loginController/toLiveConferenceKSCC";
								                			}else{
								                				url=$.base+"/loginController/toLiveConferenceHosUser";
								                			}
								                			ids=[];
								                			nodesObj=[];
								                			
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
	                                                                $("#bottomLi li:first").removeClass("active");
	        							                    	    $("#bottomLi li:eq(1)").addClass("active");
							                	                } 
							                	             });
														}
										        	},
									            	error:function(){
									            		requestTip.error();
										            }
										        });
				   				              }
						   				});
					            	}
								 }
							  });
							}
					     }
				     }
				  });
                }); 
              }
		
			function getNowFormatDate() {
			    var date = new Date();
			    var seperator1 = "-";
			    var seperator2 = ":";
			    var month = date.getMonth() + 1;
			    var strDate = date.getDate();
			    if (month >= 1 && month <= 9) {
			        month = "0" + month;
			    }
			    if (strDate >= 0 && strDate <= 9) {
			        strDate = "0" + strDate;
			    }
			    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			            + " " + date.getHours() + seperator2 + date.getMinutes()
			            + seperator2 + date.getSeconds();
			    return currentdate;
		  }
		 function setPage(){
	    	 //时间插件
			 base.form.date({
				 element:$(".date"),
				 isTime:true,
				 theme:"#00479d",
				 dateOption:{ 
					 min:getNowFormatDate(),
					 max: "2099-06-16 23:59", //最大日期
					 format: 'yyyy-MM-dd HH:mm'
				 }
			 });
			 
			//重置
			$("#resetBtn").off().on("click",function(){
				base.confirm({ 
			    	  label:"提示",
			    	  text:"<div style='text-align:center;font-size:13px;'>确定重置?</div>",
		              confirmCallback:function(){
		            	    $("#liveName").val("");//直播名称
			  				$("#department").val("");//科室
			  				$("#startTime").val("");//开始时间
			  				$("#endTime").val("");//结束时间
			  				$("#phone").val("");//手机号码
			  				$("#telePhone").val("");//座机
			  				$("#email").val("");//邮箱
			  				$("#hospitalURL").val("");//医院网址
			  				$("#liveIntroduction").val("");//直播简介
			  				$("#file").val("");//附件
			  				$(".fileNameDiv").html("");
			  				setFileUpload();
			  			    //直播参与方
							ids=[];
							nodesObj=[];
							$("#liveParticipant").empty();
							$("#selectResult").empty();
							if(belongId){
								ids.push(belongId);
								nodesObj.push({
									"hospitalId":belongId,
									"hospitalName":belongName,
									"serialnumber":1,
                                    "ishost":1
								});
								$("#liveParticipant").append("<li att='"+belongId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span>"+belongName+"<span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
								$("#selectResult").append("<li att='"+belongId+"' class='partisLimodal'><span class='iconfont iconfontList'>&#xe61e;</span><span class='selectSpan'>"+belongName+"</span></li>");
				        		$("#selectNum").text($("#selectResult").find("li").length);
							}else{
								$("#selectNum").text("0");
							}
		            	  }
		              });
			});			 
        };
        
    	//所属科室
        function findAllDepartment(){
                $.ajax({
                    url:$.base+"/FbsDepartment/findAllDepartment",
                    type:"POST",
                    contentType:"application/json",
                    dateType:"json",
                    success:function(data){
                        $("#department").html("");
                        $("#department").append("<option value='-1' style='color:#eee'>--请选择--</option>");
                        var html = "";//初始化
                        $.each(data,function(index,item){
    						html+="<option value='"+item.id+"'>"+item.departmentName+"</option>";
    					})
                        $("#department").append(html);
                    },
                    error:function(data){}
                });
        }
        
    /**上传附件**/
    var setFileUpload = function(){
    	$("#file").off().on("change",function() {
    		//校验文件格式和文件名称
        	var arr=$("#file").val().split("\\");
        	var fileArr=arr[arr.length-1];
        	if(!base.form.validateFileSize($("#file"),10240)){
        		base.requestTip({position:"center"}).error("文件不超过10M！");
        		return;
			}else if(!base.form.validateFileExtname($("#file"),"xls,xlsx,doc,docx,txt,pdf")){
				base.requestTip({position:"center"}).error("文件格式不正确");
				return;
			}
        	var modal = base.modal({
				label: "提示",
				context: "<div style='text-align:center;font-size:13px;'>确定上传此附件？</div>",
				width: 250,
				height: 50,
				buttons: [
					{
						label: "确定",
						cls: "btn btn-info",
						clickEvent: function () {
							uploadFile(fileArr);
							modal.hide();
						}
					},{
						label:"取消",
						cls:"btn btn-warning",
						clickEvent:function(){
							modal.hide();
						}
					}
				]
			});
        });
    };
    //上传文件接口
    function uploadFile(fileArr){
    	base.form.fileUpload({
            url:$.base+"/loginController/upload",
            id:"file",
			success:function(data){
            	switch(data.status){
					case "1":
						filePath=data.data.fileUrl;
						fileName=data.data.fileName;
                        base.requestTip({
                            position:"center"
                        }).success("附件上传成功！");
                        $(".fileNameDiv").text(fileArr);
						break;
					default:
                        base.requestTip({
                            position:"center"
                        }).error("附件上传失败！");
						break;
				}

			}
        });
    }
	
	//日程校验
	function scheduleCheckMain(obj){
		var startVal=$("#startTime").val();//开始时间
		var endVal=$("#endTime").val();//结束时间
		var startValTmp = startVal.replace(new RegExp("-","gm"),"/");
	    var startValHaoMiao = (new Date(startValTmp)).getTime();
	    var endValTmp = endVal.replace(new RegExp("-","gm"),"/");
	    var endValHaoMiao = (new Date(endValTmp)).getTime();
		if(startValHaoMiao>=endValHaoMiao){
			$("#tipMessage4").show();
			return
		}else{
			$("#tipMessage4").hide();
		}
		if(startVal!=""&&endVal!=""){
			var params={
					"startTime":startVal,
					"endTime":endVal,
					"ids":obj.data,
			};
			$.ajax({
				type: "POST", 
	            url:  $.base + "/hospital/checkHospital", 
	            async:false,
	            data: params,
	            success: function(data){
	            	if(data[0].nonConformity){
	            		base.alert("直播时间冲突！");
	            		$(".modal").hide();
	            		$(".modal-backdrop").hide();
	            	}
	            	var effectiveList=data[0].list;
	            	var uid=effectiveList[0].id;
	            	var uv=effectiveList[0].hospitalName;
	            	if(effectiveList){
		            	if(effectiveList.length>0){
		            		if($("#liveParticipant").find("li[att='"+uid+"']").length>0){
		            			base.alert("已选择，不可重复添加！");
		            		}else{
		            			var lenLi=$("#liveParticipant").find("li").length;
		            			if(lenLi==0){
		    						$("#liveParticipant").append("<li att='"+uid+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+uv+"'>"+uv+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
		    					    ids.push(uid);
	    			      		    nodesObj.push({
	    			      			   "hospitalId":uid,
	    			           		   "hospitalName":uv,
	    			           		   "serialnumber":1,
	    			                   "ishost":1 
	    			      		    });
		            			}else if(lenLi==1){
		    						$("#liveParticipant").append("<li att='"+uid+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+uv+"'>"+uv+"</span><span class='iconfont iconfontImg' title='第二主持人'>&#xe61c;</span></li>");
		    						ids.push(uid);
	    			      		    nodesObj.push({
	    			      			   "hospitalId":uid,
	    			           		   "hospitalName":uv,
	    			           		   "serialnumber":2,
	    			                   "ishost":2 
	    			      		    });
		            			}else{
		    						$("#liveParticipant").append("<li att='"+uid+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+uv+"'>"+uv+"</span></li>");
		    						ids.push(uid);
	    			      		    nodesObj.push({
	    			      			   "hospitalId":uid,
	    			           		   "hospitalName":uv,
	    			           		   "serialnumber":3,
	    			                   "ishost":2 
	    			      		    });
		            			}
		            		}
		            	}	 
	                }
	            }
	       });
	    }else{
			base.alert("开始时间，结束时间不能为空!");
		}
	}
	
    //模糊查询
    var setUserSearchbar = function(){
    	//获得参与方数据
    	$.ajax({
			type: "GET", 
            async: true, 
            url:  $.base + "/hospital/selectHospitalInfo", 
            dataType: "json",
            success: function(data){
            	var newData=[];
            	$.each(data,function(i,v){
            		if(v.pid=="0"){
            			newData.push({
                			"value":v.name,
                			"data":v.id
                		});
            		}
            	});
            	base.form.autoSelect({
        			container:$("#searchBox"),
        			data:newData,
        			clickCallback:function(data){
        				base.confirm({ 
					    	  label:"提示",
					    	  text:"<div style='text-align:center;font-size:13px;'>确定添加此参与方?</div>",
				              confirmCallback:function(){
				            	 scheduleCheckMain(data);//日程校验并添加选择项到参与方列表
				              }
      				    });
        			}
        		});
            }
	     });
	  };
	
		return {
			run:function(){
				affiliatedHospital();//获得所属医院
				selectParticipant();
				setPage();
				submitForm();//页面提交按钮
                findAllDepartment();
                setFileUpload();
                setUserSearchbar();
			}
		};
});
