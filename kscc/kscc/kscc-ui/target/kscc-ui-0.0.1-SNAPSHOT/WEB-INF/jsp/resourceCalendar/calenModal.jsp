<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%--style:IE浏览在输入字的时候，输入框默认带个叉叉，选择时间也会。下面代码是去掉叉叉--%>
<style>
	input::-ms-clear{display:none;}
	.text{position:relative;height:32px; width:200px;}
	.text input{float:left;;height:20px;line-height:20px; width:195px;margin:0;padding:5px 0 5px 5px; border:solid 1px #ccc;}
	.text span{position:absolute;right:0; line-height:32px;height:32px; cursor:pointer;}
</style>
   
<div class="modal-body" style="padding: 15px 5px;">
  <div class="col-xs-7 col-sm-7 col-md-7">
   	<form class="form-inline" id="liveViewform" method="post" action="">
   		<div class="clearfix mt-10">
      <label class="text-right">直播名称：</label>
      <label class="form-control form-label" id="liveNameView"></label>
  </div>
  <div class="clearfix mt-10">
      <label class="text-right">直播科室：</label>
      <label class="form-control form-label" id="departmentView"></label>
  </div>
     <div class="clearfix mt-10">
         <label class="text-right">开始时间：</label>
         <label class="form-control form-label" id="liveStartTimeView"></label>
     </div>
     <div class="clearfix mt-10">
         <label class="text-right">结束时间：</label>
         <label class="form-control form-label" id="liveEndTimeView"></label>
     </div>
     <div class="clearfix mt-10">
         <label class="text-right">联系方式：</label>
         <label class="form-control form-label" id="phoneView"></label>
     </div>
     <div class="clearfix mt-10">
      <label class="text-right">联系邮箱：</label>
      <label class="form-control form-label" id="emailView"></label>
  </div>
     <div class="clearfix mt-10">
      <label class="text-right">医院网址：</label>
      <label class="form-control form-label" id="hospitalURLView"></label>
  </div>
     <div class="clearfix mt-10">
      <label class="text-right">直播简介：</label>
      <label class="form-control form-label" id="liveIntroductionView"></label>
  </div>
  <div class="clearfix mt-10">
      <label class="text-right" style="float:left;">附件：</label>
<a id="a1" style="float:left;"></a>
     </div>
    </form>
   </div>
   <div class="col-xs-5 col-sm-5 col-md-5">
<div class="participantsList mb-10">参与方列表</div>
   <!-- <input type="text" class="form-control" name="searchParticipantView" id="searchParticipantView" placeholder="搜索参与方"/>
<span class="iconfont fuzzySearchBtnModal" id="searchBtnView" style="top:43px;right:20px;color:#00479d;">&#xe60a;</span> -->
      <div class="liveResults mt-10">
	   <ul id="liveParticipantView"></ul>
   </div>
</div>
        </div>
			