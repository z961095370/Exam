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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
     
	/*
	 *  功能:根据得到【用户编号】进行删除，一次只删除一个用户信息
	 *  步骤：
	 *         1.获得来自浏览器发送的【用户编号】
               2.JDBC将编译好的sql命令输送到数据库执行
               3.通过请求转发方式，向tomcat申请调用【用户分页查询Serlvet】 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      //0.局部变量声明
		       String userId = null;
		       String sql ="delete from users where userId=?";
		       Connection con = null;
		       PreparedStatement ps = null;
		      //1.获得来自浏览器发送的【用户编号】
		       userId=request.getParameter("userId");
		      //2.JDBC将编译好的sql命令输送到数据库执行
		       try{
		    	   Class.forName("com.mysql.jdbc.Driver");
			       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			       ps=con.prepareStatement(sql);
			       ps.setInt(1, Integer.valueOf(userId));
			       ps.executeUpdate();
		       }catch(Exception ex){
		    	   ex.printStackTrace();
		       }
		       //通过请求转发方式，向tomcat申请调用【用户分页查询Serlvet】 
		       RequestDispatcher pack= request.getRequestDispatcher("/UserFindServlet");
		       pack.forward(request, response);
	}

}








