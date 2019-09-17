package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcj.bean.Question;

/**
 * Servlet implementation class QuestionFindSameByTitile
 */
@WebServlet("/QuestionFindSameByTitile")
public class QuestionFindSameByTitile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：查询当前重复试题所有记录
	 * 步骤:
	 *     1.获得试题名称
	 *     2.JDBC查询当前试题所有重复内容
	 *     3.通过请求转发，输出到用户浏览器上
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     //0.局部变量声明
		     String title = null;
		     String sql ="select * from question where title=?";
		     Connection con = null;
		     PreparedStatement ps = null;
		     ResultSet rs = null;
		     List questionList = new ArrayList();
		     //1.获得试题名称
		     title = request.getParameter("title");
		     //2.JDBC查询当前试题所有重复内容
		     try{
		    	 Class.forName("com.mysql.jdbc.Driver");
		         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
		         ps= con.prepareStatement(sql);
			     ps.setString(1, title);
			     rs=ps.executeQuery();
			     while(rs.next()){
			    	   Question q=new Question();
			    	   q.setId(rs.getInt("id"));
				       q.setTitle(rs.getString("title"));
				       q.setOptionA(rs.getString("optionA"));
				       q.setOptionB(rs.getString("optionB"));
				       q.setOptionC(rs.getString("optionC"));
				       q.setOptionD(rs.getString("optionD"));
				       q.setAnswer(rs.getString("answer"));
				       q.setAuthor(rs.getString("author"));
				       q.setModifyTime(rs.getDate("modifyTime"));
				       questionList.add(q);
			     }
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
		     //3.通过请求转发，输出到用户浏览器上
		     request.setAttribute("questionList", questionList);
		     request.setAttribute("title", title);
		     RequestDispatcher pack= request.getRequestDispatcher("/exammanager/questionSame_Edit.jsp");
		     pack.forward(request, response);
	}

}
