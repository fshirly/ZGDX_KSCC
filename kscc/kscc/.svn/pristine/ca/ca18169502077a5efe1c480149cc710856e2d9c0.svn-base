<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
 request.setCharacterEncoding("UTF-8");
%>
<%
    Object idstr = request.getAttribute("id");
%>
<div class="hospitalAdminDiv">
    <input type="hidden" id="userIdstr" value="<%=idstr %>"/>
      <div class="clearfix addOrEdit">
      </div>
    <div class="editAdminDiv">
      <div class="clearfix mt-10">
          <label class="text-right">登录名：</label>
          <input type="text" class="form-control form-width" name="loginName" id="loginName" style="width:35%;">
      </div>
      <div class="form-group mt-10">
          <label class="text-right">原密码:</label>
          <input type="password" class="form-control form-width" name="password" id="password" style="width:35%;">
      </div>
        <div class="form-group mt-10">
            <label class="text-right">新密码:</label>
            <input type="text" class="form-control form-width" name="newpassword" id="newpassword" style="width:35%;">
        </div>
        <div class="form-group mt-10">
            <label class="text-right">确认新密码:</label>
            <input type="text" class="form-control form-width" name="confirmpassword" id="confirmpassword" style="width:35%;">
        </div>
      <div class="clearfix mt-10">
          <label class="text-right">联系电话：</label>
          <input type="text" class="form-control form-width" name="phone" id="phone" style="width:35%;">
      </div>
      <div class="clearfix mt-10">
          <label class="text-right">邮箱：</label>
          <input type="text" class="form-control form-width" name="email" id="email" style="width:35%;">
      </div>
    </div>
</div>

<script>
    require(["app/accountManage/hospitalAdmin"],function(page){
        page.run();
    })
</script>
