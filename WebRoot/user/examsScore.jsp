<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="/Exam/style/style.css" rel="stylesheet" type="text/css" />
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


			<div id="main">
				<!--main_top-->
				<div class="main_top">
					<p>
						考试成绩
					</p>
				</div>
				<!--main_bottom-->

				<div class="main_bottom">

					<table width="100%" class="table" cellspacing="1"
						style="background: #BAC2CF; font-size: 14px;">
						<tr
							style="background: #F6F6F6; border: 0; text-align: center; line-height: 25px;">
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								编号
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								用户代码
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								用户名称
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								时间
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								分数
							</td>


						</tr>

                        <c:forEach items="${requestScope.scoreList}" var="user" varStatus="loop">
                        
                          	<tr
							style="background: #FFFFFF; border: 0; text-align: center; line-height: 25px;">
							<td style="color: #666666; font-size: 14px;">
								${loop.count }
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">
								${user.userId }
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">
								${user.userName }
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">
								${user.examTime } 
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">

								${user.userScore }
							</td>
							</tr>
                        </c:forEach>

						</table>
					<div>
						<script type="text/javascript">
							
						</script>


						<form name="spage"
							action="/Exam/ScoreFindServlet"
							>
							<div>
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
							<!-- <input id="skipPage" style="width: 19px"> -->
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
							<!-- <input type="submit"  id="pageSkip" value="确定"> -->
					</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
    
    
