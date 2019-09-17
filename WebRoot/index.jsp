<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>在线考试平台</title>
	</head>

	<frameset rows="80,*,10" cols="*" framespacing="0" frameborder="no"
		border="0">
		<frame src="/Exam/top.jsp" name="topFrame" scrolling="no"
			noresize="noresize" id="topFrame" />
		<frameset cols="14%,*">
			<frame src="/Exam/${menuKey}" name="bottomFrame" scrolling="no"
				noresize="noresize" id="menu" />
			<frame src="/Exam/content.html" name="content" scrolling="no"
				noresize="noresize" id="content" />
		</frameset>	
		<frame src="/Exam/bottom.html" />
	</frameset>
</html>