<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- 导入echarts.js -->
<script type="text/javascript" src="/Exam/js/echarts.min.js"></script>
<script type="text/javascript" src="/Exam/js/jquery-1.11.1.js"></script>
</head>
<body>
	<center>
		<!-- 开发一个拥有高度和宽度的DIV标签 -->
		<div id="main" style="width: 500px; height: 500px"></div>
		<script type="text/javascript">
			//1.创建一个饼状图模型对象
			var model = {};
			var data = new Array();
			var i = 0;
			var data2 = new Array();
			<c:forEach items="${map}" var="m">
			var obj ={name : '${m.key}',value : '${m.value}'};
			data2[i] = obj;
			data[i] = '${m.key}';
			i++;
			</c:forEach>
			//2.设置名状图的属性信息
			//2.1 饼状图【标题属性】设置
			model.title = {
				text : '成绩分析图',
				subtext : '哈尔滨',
				x : 'center'
			};
			//2.2 饼状图【解释图层】设置
			model.tooltip = {};
			//2.3 饼状图【图例】设置
			model.legend = {
				orient : 'vertical',
				right : 'right',
				data : data
			};
			//2.4 饼状图【填充数据】设置
			model.series = [ {
				name : '调查报告',
				type : 'pie',
				data : data2
			} ];
			//3.定位DIV,将DIV封装到echarts类型对象中
			var divDom = document.getElementById("main");
			var myChart = echarts.init(divDom);
			//4.通过echarts函数解析模型对象，将模型对象中数据导入到
			//div中，改变div的css属性，从而改变div在浏览器上展示形式
			myChart.setOption(model);
		</script>
	</center>
</body>
</html>