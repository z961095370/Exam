<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="style/style.css" rel="stylesheet" type="text/css" />
	</head>
<div id="top">
	<!--top_left-->
	<div class="top_left">
		<img src="images/logo.png"
			style="float :right;margin-right: 20px; margin-top: 13px;" />
	</div>
	<!--top_right-->
	<div class="top_right">

		<div style="margin-left: 300px; margin-top: 43px;">
     
			<font color="black" style="font-size: 16px;font-family:'楷体';">您好：&nbsp; </font>
			<font color="black" style="font-size: 16px;font-family:'楷体';">${sessionScope.userKey.userName }</font>
			
		</div>
	</div>
</div>