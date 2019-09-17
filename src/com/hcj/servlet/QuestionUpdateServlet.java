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
 * Servlet implementation class QuestionUpdateServlet
 */
@WebServlet("/QuestionUpdateServlet")
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：更新试题
	 * 步骤：
	 *     1.获得试题信息
	 *     2.更新试题
	 *     3.请求转发
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //0.局部变量
        Connection con=null;
        PreparedStatement ps=null;
        String sql="update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? where id=?";
	    //1.获得试题信息
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer = request.getParameter("answer");
        String id = request.getParameter("questionId");
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
            ps.setInt(7, Integer.valueOf(id));
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
	       //3.请求转发试题查询内容
	       request.getRequestDispatcher("/QuestionFindServlet").forward(request, response);
	}

}
