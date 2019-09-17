package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionAddServelt
 */
@WebServlet("/QuestionAddServelt")
public class QuestionAddServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 *  功能：添加试题
	 *  步骤：
	 *     1.接受浏览器发送的请求参数----接受请求
           2.jdbc插入试题                -----处理请求
           3.将数据库中的数据输送到用户浏览器---响应 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      //0.局部变量
		      String title,optionA,optionB,optionC,optionD,answer=null;
		      Connection con =null;
		      PreparedStatement ps=null;
		      String sql="insert into question(title,optionA,optionB,optionC,optionD,answer) values(?,?,?,?,?,?)";
		      //1.接受浏览器发送的请求参数
		      request.setCharacterEncoding("utf-8");
		      title = request.getParameter("title");
		      optionA = request.getParameter("optionA");
		      optionB = request.getParameter("optionB");
		      optionC = request.getParameter("optionC");
		      optionD = request.getParameter("optionD");
		      answer = request.getParameter("answer");
		      
		      // 2.jdbc插入试题                -----处理请求
		      try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");		            
		            ps=con.prepareStatement(sql);
		            ps.setString(1, title);
		            ps.setString(2, optionA);
		            ps.setString(3, optionB);
		            ps.setString(4, optionC);
		            ps.setString(5, optionD);
		            ps.setString(6, answer);
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
		      //3.将数据库中的数据输送到用户浏览器---响应
		      request.getRequestDispatcher("/QuestionFindServlet").forward(request, response);
		      
	}

}
