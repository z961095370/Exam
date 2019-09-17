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
		
	    <!-- 导入jquery.js工具包 -->
	    <script type="text/javascript" src="/Exam/js/jquery-1.11.1.js"></script>
	    <script type="text/javascript">
	      
	       
	       $(function(){
	    	   
	    	   //全选与全不选
	    	   $("#ckOper").click(
	    		  function(){
	    			  //1.获得【标题行checkbox】的选中状态
	    			  var flag=$("#ckOper").prop("checked");//true or false
	    			  /*
	    			     1.8版本之后,prop(),attr()
	    			     attr(“属性名”):读取标签中【基本属性的内容】 [name，title,id]
	    			     prop("表单域标签专有属性名"):读取[表单域标签]中的【专有属性】的内容
	    			     [表单域标签]:<input>系列,<select></select> <textarea>
	    			     [表单域标签]中的【专有属性】:  enabled  表示当前标签处于可用状态
	    			                            disabled 表示当前标签是否处于可用状态
	    			                            checked  专用于<input type='checkbox'>
	    			                                         <input type='radio'>
	    			                                                                                                 表示标签是否处于选中状态
	    			                            selected 专用于<option>设置下拉列表选中项                                                                     
	    			  */
	    			  //2.获得【所有数据行checkbox】
	    			 var $ckArray=  $(":checkbox[name='ck']");
	    			  //3.使用【标题行checkbox】的选中状态,影响【所有数据行checkbox】选中状态 
	    			 $ckArray.prop("checked",flag);
	    			 //设置【删除按钮】和【更新按钮】是否可用
	    		       fun1();
	    		  }	   
	    	   );
	    	   //数据行选中状态影响标题行选中状态
	    	   $(":checkbox[name='ck']").click(
	    		   function(){
	    			   //1.获得【所有数据行checkbox】个数
	    			   var ck_Num= $(":checkbox[name='ck']").length;
	    		       //2.获得【所有数据行处于选中状态checkbox】个数
	    		       var ck_checked_Num=$(":checkbox[name='ck']:checked").length;
	    		       //3.判断
	    		       if(ck_Num == ck_checked_Num){//全选
	    		    	   $("#ckOper").prop("checked",true);
	    		       }else{
	    		    	   $("#ckOper").prop("checked",false);
	    		       }
	    		       //设置【删除按钮】和【更新按钮】是否可用
	    		       fun1();
	    		   }	   
	    	   );
	    	   //辅助用户定位关注的数据行内容 
	    	   $("table tr:gt(0)").mouseover(
	    			   
	    	       function (){
	    	    	   $(this).css("background","red");
	    	       }
	    	   );
	    	   
	    	   $("table tr:gt(0)").mouseout(
	    		   function (){
	    			   $(this).css("background","white");
	    		   }	   
	    	   );
	    	   //通过数据行选中状态，决定【删除按钮】和【更新按钮】是否可用
	    	   var fun1 = function(){
	    		                       //1.获得【数据行所有处于选中状态的checkbox】的个数
	    		                       var ck_Checked_Num= $(":checkbox[name='ck']:checked").length;
	    		                       //2.判断
	    		                       if(ck_Checked_Num==1){
	    		                    	   $("#delBtn").prop("disabled",false);//可用
	    		                       }else if(ck_Checked_Num>=2){
	    		                    	   $("#delBtn").prop("disabled",false);//可用
	    		                    	   
	    		                       }else{
	    		                    	   $("#delBtn").prop("disabled",true);//不可用
	    		                       }
	    	                         };
	    	   
	    	   
	       });
	       
	    
	    </script> 
	
	</head>
	<!-- 去除纵向滚动条  style="overflow:scroll;overflow-y:hidden" -->
	<body style="overflow: scroll; overflow-y: hidden">
		<div>

            <form action="/Exam/QuestionDeleteBatchServlet" > 
			<div id="main">
				<!--main_top-->
				<div class="user_manager">
					<p>
						用户管理
						<span style="float: right;margin-right: 20px">						
						<input type="submit" disabled id='delBtn' value="删除"  style="width: 54px; height: 20px; margin-left: 15px;">
						</span>
					</p>
				</div>
				<!--main_bottom-->

				<div class="main_bottom">

					<table width="100%" class="table" cellspacing="1"
						style="background: #BAC2CF; font-size: 14px;">
						<tr
							style="background: #F6F6F6; border: 0; text-align: center; line-height: 25px;">
							<td width="6%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">
							<input type="checkbox" id="ckOper" value="checkbox" /></div></td>
							
							
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								题目名称
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								选项A
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								选项B
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								选项C
							</td>
							
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								选项D
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								正确答案
							</td>
							
						</tr>

						<!-- 显示当前页的数据行内容    start -->
                         <c:forEach items="${requestScope.questionList}" var="q">
                             <tr
							style="background: #F6F6F6; border: 0; text-align: center; line-height: 25px;">
							<td width="6%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">
							<input type="checkbox" name='ck' value="${q.id}" /></div></td>
							
							
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								${q.title}
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								${q.optionA}
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								${q.optionB}
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								${q.optionC}
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								${q.optionD}
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								${q.answer}
							</td>
							
						</tr>
                         
                         </c:forEach>
                         
                        <!-- 显示当前页的数据行内容     end -->

					</table>
				</div>
			</div>
                <input type='hidden' name='title' value="${requestScope.title }"/>
            </form>
		</div>
	</body>
</html>
    