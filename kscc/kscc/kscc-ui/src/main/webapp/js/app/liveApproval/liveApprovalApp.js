define(["base","datatables.net"],function(base,DataTable){
    var loginUserId='';
	
	function getMenus(){
		$.ajax({
            type:"post",
            async: false,
            url:$.base+"/liveBroadCastController/getUserId",
            success:function (data) {
                loginUserId=data.id;
            }
		});
		setScroll();
	};	
	
		function setTable(searchFlag){
		   $("#tblLiveApproval").DataTable({
				"searching":false,
				"lengthChange":false,
				"autoWidth":false,
				"serverSide":true,
				"paging":true,
				"stateSave":true,
				"ordering":false,
				"language":{"url":$.base+"/js/lib/chinese.json"},
				"ajax":{ 
					"type":"post",
					"url":$.base+"/kscc/liveBroadApprove/liveBroadApproveList",
					"contentType":"application/json",
					"data": function ( d ) { 
						  var startTime = $("#liveStartTime").val();
						  var endTime = $("#liveEndTime").val();
						  var timeBeginLive = $("#applicationStartTime").val();
						  var timeEndLive = $("#applicationEndTime").val();
						  if(startTime !='' && endTime !=''){
							  startTime = startTime+':00';
							  endTime = endTime+':00';
						  }
						  if(timeBeginLive !='' && timeEndLive !=''){
							  timeBeginLive = timeBeginLive+':00';
							  timeEndLive = timeEndLive+':00';
						  }
						  var titleName;
						  if(searchFlag == 1){
                              titleName = $("#liveName").val();
						  }else if(searchFlag == 2){
                              titleName = $("#searchApprovalMessage").val();
                          }
                          var params={ 
                       		   "pageNo": d.start/d.length+1,
                       		   "pageSize": d.length,
                       		   "param":{
                       		   "name":$("#applicant").find("option:selected").val(),//申请方
							   "title": titleName,//直播名称
                    		   "startTime": startTime,//直播开始时间
                    		   "endTime": endTime,//直播结束时间
                    		   "approvalStatus": $("#approvalStatus").find("option:selected").val(),//审批状态
                    		   "timeBeginLive": timeBeginLive,//申请开始时间
                    		   "timeEndLive": timeEndLive,//申请结束时间
                   		}
                     }; 
//                          $.extend(d,params); 
                         return JSON.stringify(params); 
                       }
					
					},
				"columns":[
				           {"title":"序号","data":"id","sWidth":"5%"},
                           {"title":"直播名称", "data":"title","sWidth":"23%"},
				           {"title":"申请方", "data":"name","sWidth":"12%"},
				           {"title":"申请时间", "data":"createdTime","sWidth":"15%"},
				           {"title":"直播开始时间", "data":"startTime","sWidth":"15%"},
				           {"title":"审批状态", "data":"approvalStatus","sWidth":"10%"},
				           {"title":"操作", "data":"operate","sWidth":"20%"}
				           ],
	           "columnDefs":[
							{
							    "render":function(data,type,row,meta){
										return meta.row+1;
								  },
								  "targets":0
							},
							{
								   "render":function(data,type,row,meta){
									   var html="";
									   html="<span class='widthLength widthLengthEx' title='"+row.title+"'>"+row.title+"</span>";
									   return html;
								   },
								   "targets":1
							 },
							 {
								   "render":function(data,type,row,meta){
									   var html="";
									   html="<span class='widthLength' title='"+row.name+"'>"+row.name+"</span>";
									   return html;
								   },
								   "targets":2
							 },
						     {
							   "render":function(data,type,row,meta){
							   	if(data != null && data != ''){
							   		return data.substr(0,16);
								}
							   },
							   "targets":3
						     },
						     {
							   "render":function(data,type,row,meta){
                                   if(data != null && data != ''){
                                       return data.substr(0,16);
                                   }
							   },
							   "targets":4
						    },
							{
							   "render":function(data,type,row,meta){
								   if(data == 0){
						             return "<i class='iconfont' style='color:#00479d;font-weight:600;' title='待审核'>&#xe6ad;</i>";
								   }else if(data == 1){
								   		return "<i class='iconfont' style='color:#2bd844;font-weight:600;' title='已同意'>&#xe67e;</i>";
								   }else if(data == 2){
								   		return "<i class='iconfont' style='color:#d71616;font-weight:600;' title='已拒绝'>&#xe679;</i>";
								   }else if(data == 3){
								   		return "<i class='iconfont' style='color:#e0620d;font-weight:600;' title='已取消'>&#xe647;</i>";
								   }
							   },
							   "targets":5
							},
			              {"render":function(data,type,row,meta){
                                  var html = "";
                                  var agreeStatus="";
                                  var disagreeStatus="";
                                  var editStatus="";
                                  if(row.approvalStatus==1||row.approvalStatus==2||row.approvalStatus==3){
                                      agreeStatus="<button class='btn btn-link agree' rowId='"+row.id+"' user_Id='"+row.userId+"' disabled><i class='iconfont tableIcon' title='同意'>&#xe615;</i></button>";
                                      disagreeStatus="<button class='btn btn-link disagree' rowId='"+row.id+"' user_Id='"+row.userId+"' disabled><i class='iconfont tableIcon' title='拒绝'>&#xe685;</i></button>";
                                      if(row.playStatus == 0){
                                          editStatus="<button class='btn btn-link liveEdit' rowId='"+row.id+"' user_Id='"+row.userId+"'><i class='iconfont tableIcon' title='编辑'>&#xe608;</i></button>";
									  }
								  }else if(row.approvalStatus==0){
                                      //对超时未审批的直播做按钮控制
                                      if(row.isdel == 1){
                                          agreeStatus="<button class='btn btn-link agree' rowId='"+row.id+"' user_Id='"+row.userId+"' disabled><i class='iconfont tableIcon' title='同意'>&#xe615;</i></button>";
                                          disagreeStatus="<button class='btn btn-link disagree' rowId='"+row.id+"' user_Id='"+row.userId+"' disabled><i class='iconfont tableIcon' title='拒绝'>&#xe685;</i></button>";
                                          editStatus="<button class='btn btn-link liveEdit' rowId='"+row.id+"' user_Id='"+row.userId+"' disabled><i class='iconfont tableIcon' title='编辑'>&#xe608;</i></button>";
                                          var editParam ={
                                              "id": row.id,
                                              "approvalStatus":2//审批状态
                                          };
                                          $.ajax({
                                              url:$.base+"/kscc/liveBroadApprove/updateLiveApprove",
                                              type:"POST",
                                              contentType:"application/json",
                                              data:JSON.stringify(editParam),
                                              success:function(data){
                                              },
                                              error:function(data){
                                              }
                                          });
                                      }else{
                                          agreeStatus="<button class='btn btn-link agree' rowId='"+row.id+"' user_Id='"+row.userId+"'><i class='iconfont tableIcon' title='同意'>&#xe615;</i></button>";
                                          disagreeStatus="<button class='btn btn-link disagree' rowId='"+row.id+"' user_Id='"+row.userId+"'><i class='iconfont tableIcon' title='拒绝'>&#xe685;</i></button>";
									  }
								  }
								  //对编辑按钮做操作
							      if(row.playStatus == 1||row.playStatus == 2||row.approvalStatus==2||row.isdel == 1){
                                      editStatus="<button class='btn btn-link liveEdit' rowId='"+row.id+"' user_Id='"+row.userId+"' disabled><i class='iconfont tableIcon' title='编辑'>&#xe608;</i></button>";
								  }else{
                                      editStatus="<button class='btn btn-link liveEdit' rowId='"+row.id+"' user_Id='"+row.userId+"'><i class='iconfont tableIcon' title='编辑'>&#xe608;</i></button>";
								  }

                                  html =  "<div class='clearfix'>" +
	                            	  "<div style='display:inline-block;'><button class='btn btn-link liveView' rowId='"+row.id+"' user_Id='"+row.userId+"'><i class='iconfont tableIcon' title='查看'>&#xe609;</i></button></div>"+
	                            	  "<div style='margin-left:3px;display:inline-block;'>"+editStatus+"</div>"+
	                            	  "<div style='margin-left:3px;display:inline-block;'><button class='btn btn-link download' rowId='"+row.id+"' user_Id='"+row.userId+"'><i class='iconfont tableIcon' title='下载'>&#xe65b;</i></button></div>"+
	                            	  "<div style='margin-left:3px;display:inline-block;'>"+agreeStatus+"</div>"+
	                            	  "<div style='margin-left:3px;display:inline-block;'>"+disagreeStatus+"</div>"+
                            	  "</div>";
				            	  return html;
				              },
				               "targets":6
				            }
			              ],
	              
				"drawCallback":function(setting){
					//直播查看
					$(".liveView").on("click",function(){
						var liveViewId=$(this).attr("rowId");
				        $(this).attr({"data-toggle":"modal","data-target":"#liveViewModal"});
				        $("#searchParticipantView").val("");
				        $.ajax({
			               type: "post",
			               url:  $.base + "/kscc/liveBroadApprove/toViewLiveApprove?id="+liveViewId,
                           contentType:"application/json",
			               success: function(data){
			            	   var searchObj=[];
			            	   var baseParams=data.baseParams;//表单数据
			            	   var value = baseParams.filePath;
                               var fileName = baseParams.file_name;
			            	   $("#liveNameView").text(baseParams.title);//直播名称
			            	   $("#liveNameView").attr("title",baseParams.title);
							   $("#departmentView").text(baseParams.departmentName);
			            	   //时间消除秒和毫秒
							   var startTimeStr = baseParams.startTime.substr(0,16);
							   var endTimeStr = baseParams.endTime.substr(0,16);
			            	   $("#liveStartTimeView").text(startTimeStr);//开始时间
			            	   $("#liveEndTimeView").text(endTimeStr);//开始时间
			            	   $("#linkManView").text(baseParams.linkMan);//联系人
                               $("#phoneView").text(baseParams.phone);//联系方式
                               $("#emailView").text(baseParams.email);//邮箱地址
                               $("#hospitalURLView").text(baseParams.hospitalWebsite);//医院网址
                               $("#hospitalURLView").attr("title",baseParams.hospitalWebsite);
			            	   $("#liveIntroductionView").text(baseParams.liveIntroduction);//直播简介
			            	   $("#liveIntroductionView").attr("title",baseParams.liveIntroduction);
			            	   var aElement=$('#a1')
			            	   if(value){
								   aElement.click(function () {
                                       $('#fileName_bak').val(fileName);
                                       $('#fileUrl_bak').val(value);
                                       $("#fileupdown").submit();
                                   })
                                   aElement.attr('title',fileName);
                                   aElement.html(fileName);
			            	   }else{
                                   aElement.attr('href','#');
                                   aElement.html("无附件");
                                   aElement.attr('title','');
                                   aElement.css({"cursor":"default","color":"rgb(69, 69, 69)"});
			            	   }
			            	   
			            	   var hostParams=data.hostParams;//参与方数据
			            	   $("#liveParticipant").empty();
			            	   $.each(hostParams,function(i,v){
									if(v.serialNumber==1){
										$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.hospitalName+"'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
									}else if(v.serialNumber==2){
										$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.hospitalName+"'>"+v.hospitalName+"</span><span class='iconfont iconfontImg' title='第二主持人'>&#xe61c;</span></li>");
									}else{
										$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.hospitalName+"'>"+v.hospitalName+"</span></li>");
									}
									searchObj.push({
										"id":v.ID,
										"name":v.hospitalName,
                                        "serialNumber":v.serialNumber
									});
			            	   }); 
			            	   searchModal(searchObj);
                              // $("#tblLiveModifyRecord").dataTable().fnDestroy();
						      $("#tLiveView").html("<table id='tblLiveModifyRecord' class='table table-striped kscc-grid'></table>");
			            	  setModifyRecord(liveViewId);
			            	  $('#myTab li:eq(0) a').tab('show');
		            	   }       
				        });
					});
					
					//模态框参与方列表搜索框
					function searchModal(nodesObj){
						$("#searchBtnView").on("click",function(){
							var condition=$("#searchParticipantView").val();
							$("#liveParticipant").empty();
							var container=[];
							$.each(nodesObj,function(i,v){
								if(v.name.indexOf(condition)>-1){
									container.push(v);
								}
							});
							$.each(container,function(i,v){
								if(v.serialNumber==1){
									$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.name+"'>"+v.name+"</span><span class='iconfont iconfontImg' style='color:#ee453b;' title='第一主持人'>&#xe61c;</span></li>");
								}
								else if(v.serialNumber==2){
									$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.name+"'>"+v.name+"</span><span class='iconfont iconfontImg' title='第二主持人'>&#xe61c;</span></li>");
								}
								else{
									$("#liveParticipant").append("<li att='"+v.hospitalId+"' class='participantsLi'><span class='iconfont iconfontList'>&#xe61e;</span><span class='controlParti' title='"+v.name+"'>"+v.name+"</span></li>");
								}
						    });
						});
					}
					
					//申请单修改记录列表
				    function setModifyRecord(liveViewId){
				    	$("#tblLiveModifyRecord").DataTable({
							"searching":false,
							"lengthChange":false,
							"autoWidth":false,
							"serverSide":true,
							"paging":true,
							"ordering":false,
                            "bRetrieve": true,
							"language":{"url":$.base+"/js/lib/chinese.json"},
							"ajax":{ 
								"type":"post",
                                "url":$.base+"/messageContorller/findMessageByStatus",
								//"data":JSON.stringify({id:liveViewId}),
								"contentType":"application/json",
								"data": function ( d ) { 
			                          var params={ 
			                        	"pageNo": d.start/d.length+1,
			                            "pageSize": d.length,
			                            "param":{
				                        	  "id": liveViewId,
											  "status":1,
											  "mtype":1,
                                              "userId":loginUserId
				                          }
			                          }; 
			                          //$.extend(d,params); 
			                         return JSON.stringify(params); 
			                       }
								},
							"columns":[
							           {"title":"序号","data":"id","sWidth":"15%"},
							           {"title":"修改时间", "data":"createdTime","sWidth":"30%"},
							           {"title":"修改记录", "data":"content","sWidth":"55%"}
							           ],
				            "columnDefs":[
											{
											    "render":function(data,type,row,meta){
														return meta.row+1;
												  },
												  "targets":0
											},
											{
												"render":function(data,type,row,meta){
													if(data != null && data != ''){
														return formatDate(data);
													}
												},
												"targets":1
											},
											{
											   "render":function(data,type,row,meta){
												   var html="";
												   html="<span style='display:inline-block;text-align:center;width:270px; white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;' title='"+row.content+"'>"+row.content+"</span>";
												   return html;
											   },
											   "targets":2
											}
										  ]
				        });
				    }
				    
					//直播编辑
					$(".liveEdit").unbind().on("click",function(){
						var liveEditId=$(this).attr("rowId");
						$("#liveIdEdit").val("");
						$("#liveIdEdit").val(liveEditId);
						$("#siteEdit").val("/loginController/toLiveApproval");
						$("#indexEdit").val("0");
						var url=$.base+"/loginController/toEditLiveBroadcast";
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
					  	});

                    /**
                     * 时间戳转化为固定格式日期
                     * @param m
                     * @returns {string}
                     */
                    function add0(m){return m<10?'0'+m:m }
                    function formatDate(now) {
                        var time = new Date(now);
                        var year=time.getFullYear();
                        var month=time.getMonth()+1;
                        var date=time.getDate();
                        var hour=time.getHours();
                        var minute=time.getMinutes();
                        var second=time.getSeconds();
                        return year+'-'+add0(month)+'-'+add0(date)+' '+add0(hour)+':'+add0(minute);
                    }
					//下载
					$(".download").on("click",function(){
						var id=$(this).attr("rowId");
						exportDoc('/kscc/wordExport/exportKsccWord?id='+id);
					});
					
					//下载word
					function exportDoc(path){
						$("#colNames").val(getColumnNames());
						$("#colFields").val(getColumnFields("tblLiveApproval"));
						$("#tt").val("直播详情信息");
						var subtitle = $("#title").next().text() +"," + $("#title").next().next().text();
						$("#subtitle").val(subtitle);
						$("#colSpan").val("2,2");
						$("#fileName").val("直播详情信息");
						$("#export")[0].action = $.base + path;
						$("#export").submit();
					}
			
					function getColumnNames(){
						var colName=[];
						colName.push('序号');//把TITLEPUSH到数组里去
						colName.push('申请方');
						colName.push('申请时间');
						colName.push('直播开始时间');
						colName.push('直播名称');
						colName.push('审批状态');
						return colName;
					}
			
					function getColumnFields(){
						var id;
						var userId;
						var createdTime;
						var startTime;
						var title;
						var approvalStatus;
						var colfield=[];
						var table = $('#tblLiveApproval').DataTable();
						$('#tblLiveApproval tbody').on( 'click', 'tr', function () {
							id = table.row(this).data().id;
							userId = table.row(this).data().userId;
							createdTime = table.row(this).data().createdTime;
							startTime = table.row(this).data().startTime;
							title = table.row(this).data().title;
							approvalStatus = table.row(this).data().approvalStatus;
						} );
						colfield.push(id);//把TITLEPUSH到数组里去
						colfield.push(userId);
						colfield.push(createdTime);
						colfield.push(startTime);
						colfield.push(title);
						colfield.push(approvalStatus);
						return colfield;
					}
					
					//同意
					$(".agree").off().on("click",function(){
						var agreeId=$(this).attr("rowId");
						var user_uuId =$(this).attr("user_Id");
						base.confirm({ 
					    	  label:"提示",
					    	  text:"<div style='text-align:center;font-size:13px;'>确定同意此直播?</div>",
				              confirmCallback:function(){
				            	    var requestTip=base.requestTip({position:"center"});
						        	var editParam ={
											   "id": agreeId,
						                	   "approvalStatus":1,//审批状态
										       "userId":user_uuId
								     		};
						                $.ajax({
						                	url:$.base+"/kscc/liveBroadApprove/updateLiveApprove",
								        	type:"POST",
								        	contentType:"application/json",
								        	data:JSON.stringify(editParam),
								        	success:function(data){
								        		$("#tLive").html("<table id='tblLiveApproval' class='table table-striped kscc-grid'></table>");
					                          	setTable();
								        		$("#agreeModal").modal("hide");
								        		requestTip.success();
								        		setScroll();//设置滚动条
								        	},
								        	beforeSend:function(){
								            	requestTip.wait();
								            },
							            	error:function(){
							            		requestTip.error();
								            }
								        });	
				              }
						});
					});
					//不同意
					$(".disagree").off().on("click",function(){
						var disagreeId=$(this).attr("rowId");
                        var user_nuId =$(this).attr("user_Id")
						base.confirm({ 
					    	  label:"提示",
					    	  text:"<div style='text-align:center;font-size:13px;'>确定拒绝此直播?</div>",
				              confirmCallback:function(){
				            	  var requestTip=base.requestTip({position:"center"});
				            	  var editParam ={ 
										   "id": disagreeId,
					                	   "approvalStatus":2,//审批状态
                                      		"userId":user_nuId
							     		};
				            	  $.ajax({
					            		url:$.base+"/kscc/liveBroadApprove/updateLiveApprove",
							        	type:"POST",
							        	contentType:"application/json",
							        	data:JSON.stringify(editParam),
							        	success:function(data){
							        		$("#tLive").html("<table id='tblLiveApproval' class='table table-striped kscc-grid'></table>");
				                          	setTable();
							        		$("#disagreeModal").modal("hide");
							        		requestTip.success();
							        		setScroll();//设置滚动条
							        	},
							        	beforeSend:function(){
							            	requestTip.wait();
							            },
						            	error:function(){
						            		requestTip.error();
							            }
							    });
				              }
						});
					});
				} 
			});
	    };
		 function setPage(){
			//时间插件
			 base.form.date({
				 element:$(".date"),
				 isTime:true,
				 theme:"#00479d",
				 dateOption:{ 
					// min:getNowFormatDate(),
					 max: "2099-06-16 23:59", //最大日期
					 format: 'yyyy-MM-dd HH:mm'
				 }
			 });
		  	
		  	//搜索
		  	$("#searchBtn").off().on("click",function(){
				$("#tLive").html("<table id='tblLiveApproval' class='table table-striped kscc-grid'></table>");
                var searchStartTimeYne = $("#liveStartTime").val();
                var searchEndTimeYne = $("#liveEndTime").val();
                var searchStartTimeYwo = $("#applicationStartTime").val();
                var searchEndTimeYwo = $("#applicationEndTime").val();
                if(searchStartTimeYne>searchEndTimeYne&&searchEndTimeYne!=""&&searchStartTimeYne!=""&&searchEndTimeYne!=null&&searchStartTimeYne!=null){
                	base.confirm({ 
				    	  label:"提示",
				    	  text:"<div style='text-align:center;font-size:13px;'>直播开始时间不能大于结束时间！</div>",
			              confirmCallback:function(){}
              	    });
                }else if(searchStartTimeYwo>searchEndTimeYwo&&searchEndTimeYwo!=""&&searchStartTimeYwo!=""&&searchEndTimeYwo!=null&&searchStartTimeYwo!=null){
                	base.confirm({ 
				    	  label:"提示",
				    	  text:"<div style='text-align:center;font-size:13px;'>申请开始时间不能大于结束时间！</div>",
			              confirmCallback:function(){}
            	    });
                }
				setTable(1);
			});

             //依据直播名称查询
             $(".fuzzySearchBtn").on("click",function(){
                 $("#tLive").html("<table id='tblLiveApproval' class='table table-striped kscc-grid'></table>");
                 setTable(2);
             });
		  	
			//重置
			$("#resetBtn").on("click",function(){
				$("#applicant").val("");//申请方
				$("#liveName").val("");//直播名称
				$("#liveStartTime").val("");//直播开始时间
				$("#liveEndTime").val("");//直播结束时间
				$("#approvalStatus").val("");//审批状态
				$("#applicationStartTime").val("");//申请开始时间
				$("#applicationEndTime").val("");//申请结束时间
			});	
			
			$(".highSearch").off().on("click",function(){
				$("#searchDiv").slideToggle("slow",function(){
	                setScroll();//设置滚动条
	            });
			    var display =$('#searchDiv').css('display');
			    if(display == "block"){
				    base.form.reset($("#searchFormId"));
				    $("#tLive").html("<table id='tblLiveApproval' class='table table-striped kscc-grid'></table>");
	                setTable();
			    }
			});
        };
		
        function setOption(){
			$.ajax({
                url:$.base+"/hospital/getHospitalInfo",
                type:"POST",
                contentType:"application/json",
                success:function(data){
                   	for(var i=0;i<data.length;i++){
                   		$("#applicant").append("<option value='"+data[i].hospitalName+"'>"+data[i].hospitalName+"</option>")
                   		$("#applicantEdit").append("<option value='"+data[i].hospitalName+"'>"+data[i].hospitalName+"</option>")
					}
                },
			})
		}
        
        //设置滚动条
        function setScroll(){
        	base.scroll({
              container:".middleContent"
            })
        }
        
		return {
			run:function(){
				getMenus();//获得底部菜单
				setTable();
				setPage();
				setOption();
			}
		};
});
