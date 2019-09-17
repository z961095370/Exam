package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcj.bean.Question;
import com.hcj.bean.Users;

/**
 * Servlet implementation class QuestionFindByIdServlet
 */
@WebServlet("/QuestionFindByIdServlet")
public class QuestionFindByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //0.局部变量
	      String questionId = null;
	      Connection con = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql ="select * from question where id=?";
	      Users user = new Users();
	      Question q= new Question();
	    //1.获得浏览器发送的【主键编号内容】
	      questionId=request.getParameter("questionId");
	    //2.JDBC查询与【主键编号内容】关联的[数据行]
	      try{
	        	Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");		           
	            ps=con.prepareStatement(sql);
	            ps.setInt(1, Integer.valueOf(questionId));
	            rs=ps.executeQuery();//根据主键查询，因此查询只有一行数据
	      //3)[数据行]内容转换为对象格式保存
	            rs.next();
	              
		    	  q.setId(rs.getInt("id"));
		    	  q.setAnswer(rs.getString("answer"));
		    	  q.setTitle(rs.getString("title"));
		    	  q.setOptionA(rs.getString("optionA"));
		    	  q.setOptionB(rs.getString("optionB"));
		    	  q.setOptionC(rs.getString("optionC"));
		    	  q.setOptionD(rs.getString("optionD"));
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
	      //4）通过请求转发，将Users对象输送到
	        request.setAttribute("questionKey", q);
	        request.getRequestDispatcher("/exammanager/examEdit.jsp").forward(request, response);

	}

}
