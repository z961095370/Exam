<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	$(function() {

		//首页功能
		$("#firstBtn").click(function() {
			$("#pageNo").val(1);

		});

		//尾页功能
		$("#lastBtn").click(function() {
			$("#pageNo").val('${requestScope.totalPage}');
		});
		//下一页
		$("#nextBtn").click(function() {
			var currentPage = ${requestScope.pageNo};
			currentPage++;
			$("#pageNo").val(currentPage);
		});
		//上一页
		$("#preBtn").click(function() {

			var currentPage = ${requestScope.pageNo};
			currentPage--;
			$("#pageNo").val(currentPage);
		});
		//跳转页
		$("#pageSkip").click(function() {
			var currentPage = $("#skipPage").val();
			$("#pageNo").val(currentPage);
		});
	});

	$(function() {

		//全选与全不选
		$("#ckOper").click(function() {
			//1.获得【标题行checkbox】的选中状态
			var flag = $("#ckOper").prop("checked");//true or false
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
			var $ckArray = $(":checkbox[name='ck']");
			//3.使用【标题行checkbox】的选中状态,影响【所有数据行checkbox】选中状态 
			$ckArray.prop("checked", flag);
			//设置【删除按钮】和【更新按钮】是否可用
			fun1();
		});
		//数据行选中状态影响标题行选中状态
		$(":checkbox[name='ck']").click(function() {
			//1.获得【所有数据行checkbox】个数
			var ck_Num = $(":checkbox[name='ck']").length;
			//2.获得【所有数据行处于选中状态checkbox】个数
			var ck_checked_Num = $(":checkbox[name='ck']:checked").length;
			//3.判断
			if (ck_Num == ck_checked_Num) {//全选
				$("#ckOper").prop("checked", true);
			} else {
				$("#ckOper").prop("checked", false);
			}
			//设置【删除按钮】和【更新按钮】是否可用
			fun1();
		});
		//辅助用户定位关注的数据行内容 
		$("table tr:gt(0)").mouseover(

		function() {
			$(this).css("background", "yellow");
		});

		$("table tr:gt(0)").mouseout(function() {
			$(this).css("background", "white");
		});
		//通过数据行选中状态，决定【删除按钮】和【更新按钮】是否可用
		var fun1 = function() {
			//1.获得【数据行所有处于选中状态的checkbox】的个数
			var ck_Checked_Num = $(":checkbox[name='ck']:checked").length;
			//2.判断
			if (ck_Checked_Num == 1) {
				$("#upBtn,#delBtn").prop("disabled", false);//可用
			} else if (ck_Checked_Num >= 2) {
				$("#delBtn").prop("disabled", false);//可用
				$("#upBtn").prop("disabled", true);//不可用
			} else {
				$("#upBtn,#delBtn").prop("disabled", true);//不可用
			}
		};
		//点击删除按钮之后，修改form标签中action属性，重新指定请求地址
		$("#delBtn").click(function() {
			$("form").attr("action", "/Exam/UserDeleteBatchServlet");
		});

		$("#upBtn").click(function() {
			$("form").attr("action", "/Exam/UserFindByIdServlet");
		});

	});
	
</script>

</head>
<!-- 去除纵向滚动条  style="overflow:scroll;overflow-y:hidden" -->
<body style="overflow: scroll; overflow-y: hidden">
	<div>

		<form action="/Exam/UserFindServlet">
			<div id="main">
				<!--main_top-->
				<div class="user_manager">
					<p>
						用户管理 <span style="float: right; margin-right: 20px"> <input
							type="button" value="添加"
							onclick="document.location='/Exam/usermanager/userAdd.html'"
							style="width: 54px; height: 20px; margin-left: 15px;"> <input
							type="submit" disabled id="upBtn" value="修改"
							style="width: 54px; height: 20px; margin-left: 15px;"> <input
							type="submit" disabled id='delBtn' value="删除"
							style="width: 54px; height: 20px; margin-left: 15px;">
						</span>
					</p>
				</div>
				<!--main_bottom-->

				<div class="main_bottom">

					<table width="100%" class="table" cellspacing="1"
						style="background: #BAC2CF; font-size: 14px;">
						<tr
							style="background: #F6F6F6; border: 0; text-align: center; line-height: 25px;">
							<td width="6%" height="26" background="../images/tab_14.gif"
								class="STYLE1"><div align="center" class="STYLE2 STYLE1">
									<input type="checkbox" id="ckOper" value="checkbox" />
								</div></td>

							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								编号</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								用户代码</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								用户名称</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								邮箱</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								电话</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								操作</td>


						</tr>



						<!-- 显示当前页的数据行内容    start -->
						<c:forEach items="${requestScope.userList}" var="user">
							<tr
								style="background: #FFFFFF; border: 0; text-align: center; line-height: 25px;">
								<td height="18" bgcolor="#FFFFFF"><div align="center"
										class="STYLE1">
										<input name="ck" type="checkbox" class="STYLE2"
											value="${user.userId}" />
									</div></td>

								<td style="color: #666666; font-size: 14px;">
									${user.userId}</td>
								<td style="color: #666666; font-size: 14px; text-align: center;">
									${user.userCode }</td>
								<td style="color: #666666; font-size: 14px; text-align: center;">
									${user.userName }</td>
								<td style="color: #666666; font-size: 14px; text-align: center;">
									${user.email }</td>
								<td style="color: #666666; font-size: 14px; text-align: center;">
									${user.tel}</td>
								<td style="color: #666666; font-size: 14px; text-align: center;">
									<!-- 目的:为了删除当前用户，添加的超链接 --> <!-- 作者：老杨 --> <!-- 修改日期:2017-05-15 -->
									<a
									href="${pageContext.request.contextPath}/UserDeleteServlet?userId=${user.userId}&pageNo=${requestScope.pageNo}">删除当前用户</a>
								</td>
							</tr>
						</c:forEach>

						<!-- 显示当前页的数据行内容     end -->

					</table>
					<div>
						<input type="submit" id="firstBtn" value="首页"
							${requestScope.pageNo eq 1?"disabled":"" }> 
						<input type="submit" id="preBtn" value="上一页"
							${requestScope.pageNo eq 1?"disabled":"" }> 
							<input type="submit" id="nextBtn" value="下一页"
							${requestScope.pageNo eq requestScope.totalPage?"disabled":"" }>
						<input type="submit" id="lastBtn" value="尾页"
							${requestScope.pageNo eq requestScope.totalPage?"disabled":"" }>
						<input type="hidden" name="cp"> <span style="color: red">
							${requestScope.pageNo} / ${requestScope.totalPage} </span> 跳转到： 
							<span>
							<label>到第</label>
							<input id="skipPage" style="width: 40px" type="number" min="1" onkeyup="this.value=this.value.replace(/\D/, '');">
							<label>页</label>
							<input type="submit" id="pageSkip" value="确定">
							<!-- <button type="submit" id="pageSkip" >确定</button> -->
							</span>
							<!-- <input id="skipPage" style="width: 19px">
							<input type="submit" id="pageSkip" value="确定"> -->
						<!-- 添加一个hidden标签,作为请求参数，向后台传递用户请求的页数 -->
						<input type='hidden' id="pageNo" name='pageNo' />
						
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
