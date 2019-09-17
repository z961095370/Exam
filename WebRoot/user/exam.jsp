<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ page isELIgnored="false" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="/Exam/style/style.css" rel="stylesheet" type="text/css" />
	    <script type="text/javascript" src="/Exam/js/jquery-1.11.1.js"></script>
	    <script type="text/javascript">
	   
	    </script>
	</head>
	<body >
		<div>
			
			<div id="ks">
				<!--ks_top-->
				<div class="ks_top">
					<p>
						考试
						<span id='time' style="color: red; padding-left: 600px"></span>
					</p>

				</div>


				<!--ks_bottom-->
				<div class="ks_bottom">
					<div class="ti"
						>
						<form 
							action="/Exam/PanFenServlet" >

							

							<c:forEach items="${questionList }" var="q">
							
							    <p>
								<span style="font-weight: bold; color: #296DB8;">${q.id }、</span> 
								${q.title}
								</p>
							
							<p>
							<div align="left" id="ispan0"
									style="padding-left: 31px; font-weight: normal;">
									<input  type="radio" name="question${q.id}"
										value="A" >
										<span> A、${q.optionA }</span>
									<br>
																		 
									<input  type="radio" name="question${q.id}"
										value="B" >
										<span> B、${q.optionB }</span>

									
									<br>
									<input  type="radio" name="question${q.id}"
										value="C" >
										<span> C、${q.optionC }</span>
									<br/>
									
									<input  type="radio" name="question${q.id}"
										value="D" >
										<span> D、${q.optionD }</span>
								</div>
							</p>
							</c:forEach>
							
							<input type="submit" value="交     卷" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
    