<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<title>Insert title here</title>
		<link href="/Exam/style/style.css" rel="stylesheet" type="text/css" />
	</head>
	<!-- 去除纵向滚动条  style="overflow:scroll;overflow-y:hidden" -->
	<body style="overflow: scroll; overflow-y: hidden">
		<div>

			<div id="right">
				<!--right_top-->
				<div class="right_top">
					<p>
						试题修改
					</p>
				</div>
				<div class="right_bottom" style="border: 0px solid red;">

<!--					<h1>-->
<!--						<span style="text-align: center; padding-left: 120px;">考试题</span>-->
<!--					</h1>-->
					<!--nr-->
					<!--baise-->
					<div class="baise" >

						<form 
							action="/Exam/QuestionUpdateServlet" method="post">
							<input type="hidden" name="questionId" value="${questionKey.id }"/>
							<table width="10%" class="table" cellspacing="1"
								style="background: #FFFFFF; font-size: 12px;  margin: 0 auto; border: 0;">
								
								
								<tr style="background: #FFFFFF; border: 0;">
									<td nowrap="nowrap" height="30" align="right" width="46%">
										<span style="font-weight: bold; color: #296DB8;">题目 :</span>
									</td>
									<td width="54%" style="text-align: left; padding-left: 10px;">
										<textarea name="title" cols="100">${questionKey.title }</textarea>

									</td>
								</tr>
								
								<tr
									style="background: #FFFFFF; border: 1; margin-top: 10px; height: 25px;">
									<td style="width: ;" align="right">
										<span style="font-weight: bold; color: #296DB8;">A,</span>
									</td>
									<td style="text-align: left; padding-left: 10px;">
										<input id="option1" name="optionA" type="text" maxlength="20"
											style="width: 800px; padding-left: 5px; border: none; border: solid 1px #C4D0CC;"
											value="${questionKey.optionA }" />
									</td>

								</tr>

								<tr
									style="background: #FFFFFF; border: 0; margin-top: 10px; height: 25px;">
									<td style="width: ;" align="right">
										<span style="font-weight: bold; color: #296DB8;">B,</span>
									</td>
									<td style="text-align: left; padding-left: 10px;">
										<input id="option2" name="optionB" type="text" maxlength="20"
											style="width: 800px; padding-left: 5px; border: none; border: solid 1px #C4D0CC;"
											value="${questionKey.optionB }" />
									</td>
								</tr>

								<tr
									style="background: #FFFFFF; border: 0; margin-top: 10px; height: 25px;">
									<td align="right">
										<span style="font-weight: bold; color: #296DB8;">C,</span>
									</td>
									<td style="text-align: left; padding-left: 10px;">
										<input id="option3" name="optionC" type="text" maxlength="20"
											style="width: 800px; padding-left: 5px; border: none; border: solid 1px #C4D0CC;"
											value="${questionKey.optionC }" />
									</td>
								</tr>
								<tr
									style="background: #FFFFFF; border: 0; margin-top: 10px; height: 25px;">

									<td height="20" align="right">
										<span style="font-weight: bold; color: #296DB8;">D,</span>
									</td>
									<td style="text-align: left; padding-left: 10px;">
										<input name="optionD" type="text" id="option4"
											style="width: 800px; padding-left: 5px; border: none; border: solid 1px #C4D0CC;"
											value="${questionKey.optionD }" size="300" maxlength="200" />
									</td>
								</tr>

								<tr
									style="background: #FFFFFF; border: 0; margin-top: 10px; height: 25px;">
									<td height="112" align="right">

									</td>
									<td align="center" valign="middle"
										style="text-align: left; padding-left: 10px;">
										<p>
											<span style="font-weight: bold; color: #296DB8;">请选择正确答案：</span>
										</p>
										<p>
											&nbsp;
										</p>

										<p style="padding-left: 20px">

											<input type="radio" name="answer" value="A"
												id="RadioGroup1_0" ${questionKey.answer eq "A"?"checked":"" } />
											A

											<input type="radio" name="answer" value="B"
												id="RadioGroup1_1" ${questionKey.answer eq "B"?"checked":"" }/>
											B

											<input type="radio" name="answer" value="C"
												id="RadioGroup1_2" ${questionKey.answer eq "C"?"checked":"" }/>
											C

											<input type="radio" name="answer" value="D"
												id="RadioGroup1_3" ${questionKey.answer eq "D"?"checked":"" }/>
											D
											
											 
										</p>

										<p>
											&nbsp;
										</p>
										<p>
									       
											 
											<input  value="确定"  type="submit"
												style="border: none; width: 70px; height: 23px;   font-size: 14px; cursor:hand"
												 />
											<br />
										</p>
                                             
										<div></div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
    