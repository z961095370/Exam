<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>     
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="/Exam/style/style.css" rel="stylesheet" type="text/css" />
		<!-- 导入jquery.js工具包 -->
	    <script type="text/javascript" src="/Exam/js/jquery-1.11.1.js"></script>
	    <script type="text/javascript">
	       $(function(){
	    	   
	    	   //首页功能
	    	   $("#firstBtn").click(
	    		   function (){
	    			   $("#pageNo").val(1);
	    			   
	    		   }	   
	    	   );
	    	   
	    	   //尾页功能
	    	   $("#lastBtn").click(
	    		   function (){
	    			  $("#pageNo").val(${requestScope.totalPage}); 
	    		   }	   
	    	   );
	    	   //下一页
	    	   $("#nextBtn").click(
	    		   function(){
	    			   var currentPage = ${requestScope.pageNo};
	    			   currentPage++;
	    			   $("#pageNo").val(currentPage);
	    		   }	   
	    	   );
	    	   //上一页
	    	   $("#preBtn").click(
	    		  function (){
	    			  
	    			  var currentPage = ${requestScope.pageNo};
	    			   currentPage--;
	    			   $("#pageNo").val(currentPage);
	    		  }	   
	    	   );
	    	 //跳转页
	   		$("#pageSkip").click(function() {
	   			var currentPage = $("#skipPage").val();
	   			$("#pageNo").val(currentPage);
	   		});
	        });
		</script>

	</head>
	<body style="overflow: scroll; overflow-y: hidden">
		<div>

			<div id="ks">
				<!--ks_top-->
				<div class="main_top">
					<p>
						试题管理
					</p>
				</div>
				<!--ks_bottom-->
				<div class="ks_bottom">

					<!--ti-->
					<div class="ti">

					 <!-- 输出当前页数据 -->
                     <c:forEach items="${requestScope.questionList}" var="question">
                     
                       
						<p>
							<span style="font-weight: bold; color: #296DB8;">${question.id }、 ${question.title }</span>

						</p>

						<p>
						<div id="ispan3" align="left" style="padding-left: 36px">
								<span name="option" style="font-weight: bold;">A、${question.optionA }</span>
								<span name="option"
									style="font-weight: bold; padding-left: 120px">B、${question.optionB }</span>
								<span name="option"
									style="font-weight: bold; padding-left: 120px">C、${question.optionC }</span>
								<span name="option"
									style="font-weight: bold; padding-left: 120px">D、${question.optionD }</span>
							</div>
						</p>
							
						<p style="padding-left: 36px">
							(答案是:&nbsp;
							<span id="an3" style="font-weight: bold; color: #F80015;">${question.answer }</span>&nbsp;)&nbsp;&nbsp;&nbsp;[

							<a href="/Exam/QuestionFindByIdServlet?questionId=${question.id }"><span
								style="color: #FF8005;">编辑</span>
							</a>&nbsp;&nbsp;
							<a href = "${pageContext.request.contextPath}/QuestionDeleteServlet?questionId=${question.id }" ><span
								style="color: #FF8005;">删除</span>
							</a>]
						</p>
                       
                     
                     </c:forEach>
						


						

					</div>
					
                    <form action="${pageContext.request.contextPath }/QuestionFindServlet">
					<div style="margin-top: 10px">
							<input type="submit" id="firstBtn" value="首页" ${requestScope.pageNo eq 1?"disabled":"" }>
							<input type="submit" id="preBtn" value="上一页" ${requestScope.pageNo eq 1?"disabled":"" }>
							<input type="submit" id="nextBtn" value="下一页" ${requestScope.pageNo eq requestScope.totalPage?"disabled":"" }>
							<input type="submit" id="lastBtn" value="尾页" ${requestScope.pageNo eq requestScope.totalPage?"disabled":"" }>
							<input type="hidden" name="cp">
							<span style="color: red"> ${requestScope.pageNo} / ${requestScope.totalPage} </span> 跳转到：
							<span>
							<label>到第</label>
							<input id="skipPage" style="width: 40px" type="number" min="1" onkeyup="this.value=this.value.replace(/\D/, '');">
							<label>页</label>
							<input type="submit" id="pageSkip" value="确定">
							<!-- <button type="submit" id="pageSkip" >确定</button> -->
							</span>
							<!-- <select name="selPage" onchange="javascript:void(0)">
								<option value="1" selected>
									1
								</option>
								<option value="2">
									2
								</option>
							</select> -->
							<!-- 添加一个hidden标签,作为请求参数，向后台传递用户请求的页数 -->
							<input type='hidden' id="pageNo" name='pageNo'/>
					  </div>
					  </form>
				</div>
			</div>
		</div>
	</body>
</html>
    