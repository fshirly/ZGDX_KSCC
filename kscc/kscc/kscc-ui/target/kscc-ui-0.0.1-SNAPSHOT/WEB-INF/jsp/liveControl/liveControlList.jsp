<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
 request.setCharacterEncoding("UTF-8");
%>
    <div class="middleContent" style="padding: 0 10px;">
		<div id="myCarouselManage" class="carousel slide">
		    <!-- 轮播（Carousel）指标 -->
		    <ol class="carousel-indicators" id="carouselCircleManage">
		        
		    </ol>   
		    <!-- 轮播（Carousel）项目 -->
		    <div class="carousel-inner liveManage">
		       
		    </div>
		    <!-- 轮播（Carousel）导航 -->
		    <a class="carousel-control left" href="#myCarouselManage" 
		        data-slide="prev">
		    </a>
		    <a class="carousel-control right" href="#myCarouselManage" 
		        data-slide="next">
		    </a>
	    </div>
     </div>
	<script>
		require(["app/liveControl/liveControlListApp"],function(page){
		    page.run();
		})
	</script>
