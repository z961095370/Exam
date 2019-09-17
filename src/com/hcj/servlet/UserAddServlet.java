package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {
	
	/*
	         功能 ： 添加用户信息
	         步骤 ：
	          1.读取来自于浏览器发送的请求参数
	          2.通过【JDBC】将得到的参数插入到数据库中     
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        //1.读取来自于浏览器发送的请求参数
				request.setCharacterEncoding("utf-8");
				
		        String userCode = request.getParameter("userCode");
		        String userName = request.getParameter("userName");
		        String password = request.getParameter("password");
		        String email = request.getParameter("email");
		        String tel = request.getParameter("tel");
		        String flag =request.getParameter("flag");
		        /*
		         *  关于读取【请求参数】时，常见的异常
		         */
		          //Integer.valueOf(userCode);
		        //2.通过【JDBC】将得到的参数插入到数据库中
		        Connection con = null;
		        try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
		            String sql="insert into users(userCode,userName,password,email,tel,flag) values(?,?,?,?,?,?)";	        
		            PreparedStatement ps=con.prepareStatement(sql);
		            ps.setString(1, userCode);
		            ps.setString(2, userName);
		            ps.setString(3, password);
		            ps.setString(4, email);
		            ps.setString(5, tel);
		            ps.setInt(6, Integer.valueOf(flag));
		            ps.executeUpdate();
		        }catch(Exception ex){
		        	ex.printStackTrace();
		        }finally{
		        	try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        RequestDispatcher pack= request.getRequestDispatcher("/UserFindServlet");
			     pack.forward(request, response);
	}

}








