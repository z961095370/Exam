package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionDeleteServlet
 */
@WebServlet("/QuestionDeleteServlet")
public class QuestionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：一次删除一道试题
	 * 步骤：
	 *            1.获得浏览器发送的ID值。-----接收请求
                  2.jdbc输送删除命令   ------处理业务
                     delete from 表名 where 主键字段=【ID值】
                  3.调用【分页查询Servlet】响应   
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      //0.局部变量声明
	       String questionId = null;
	       String sql ="delete from question where id=?";
	       Connection con = null;
	       PreparedStatement ps = null;
	      //1.获得来自浏览器发送的【试题编号】
	       questionId=request.getParameter("questionId");
	      //2.JDBC将编译好的sql命令输送到数据库执行
	       try{
	    	   Class.forName("com.mysql.jdbc.Driver");
		       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
		       ps=con.prepareStatement(sql);
		       ps.setInt(1, Integer.valueOf(questionId));
		       ps.executeUpdate();
	       }catch(Exception ex){
	    	   ex.printStackTrace();
	       }
	       //通过请求转发方式，向tomcat申请调用【试题分页查询Serlvet】 
	       RequestDispatcher pack= request.getRequestDispatcher("/QuestionFindServlet");
	       pack.forward(request, response);

	}

}
