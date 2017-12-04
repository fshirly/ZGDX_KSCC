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
   
<!--选择参与方弹框-->
<div class="modal-body">
	<div class="selectLeft" style="position:relative;">
		<input type="text" class="form-control selectInput" name="searchParticipantModal" id="searchParticipantModal" placeholder="搜索参与方"/>
		<span class="iconfont" id="searchBtnModel" title="搜索">&#xe60a;</span>
		<div style="height:22px;line-height:22px;font-size:12px;color:#333;padding:3px;">医院列表</div>
		<div class="zTreeDemoBackground">
			<ul id="treeDemo1" class="ztree">

			</ul>
		</div>
	</div>
	<div class="selectMiddle">
		<button type="button" id="LTRSingle">></button>
		<button type="button" id="RTLSingle"> < </button>
	</div>
	<div class="selectRight">
		<div style="border-bottom:1px solid #ccc;">已选<span id="selectNum"></span>位</div>
		<ul id="selectResult"></ul>
	</div>
</div>
			