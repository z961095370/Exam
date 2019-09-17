package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcj.bean.Question;
import com.hcj.bean.Users;

/**
 * Servlet implementation class QuestionFindServlet
 */
@WebServlet("/QuestionFindServlet")
public class QuestionFindServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      //1.读取用户通过浏览器发送的【请求页数】
	      String str_Page=request.getParameter("pageNo");
        int pageNo= str_Page!=null && !"".equals(str_Page)?Integer.valueOf(str_Page):1;
        //2.计算【当前页的起始行位置】
        int startLine = (pageNo-1) * 3;
        // 3.JDBC将输送【分页查询命令】到数据库中查询隶属于当前页的数行
        List list = new ArrayList();
        int totalPage = 0;//总页数
        try{
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
	          PreparedStatement ps= con.prepareStatement("select * from question limit ?,3");
		      ps.setInt(1, startLine);
		      ResultSet rs=ps.executeQuery();
		   //计算总页数
		      PreparedStatement ps2=con.prepareStatement("select count(*) from question");
		      ResultSet rs2=ps2.executeQuery();
		      rs2.next();
		      int totalCount=rs2.getInt(1);
		      totalPage = totalCount%3==0?totalCount/3:totalCount/3+1;
		  //4.将得到数据通过I/O流输送到用户的浏览器			      
		      while(rs.next()){
		    	  Question q= new Question();
		    	  q.setId(rs.getInt("id"));
		    	  q.setAnswer(rs.getString("answer"));
		    	  q.setTitle(rs.getString("title"));
		    	  q.setOptionA(rs.getString("optionA"));
		    	  q.setOptionB(rs.getString("optionB"));
		    	  q.setOptionC(rs.getString("optionC"));
		    	  q.setOptionD(rs.getString("optionD"));
		    	  
		    	  list.add(q);
		      }//user牺牲了
        }catch(Exception ex){
      	  ex.printStackTrace();
        }
        //4.将得到数据通过I/O流输送到用户的浏览器
        request.setAttribute("questionList", list);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("totalPage", totalPage);
        RequestDispatcher pack= request.getRequestDispatcher("/exammanager/exams.jsp");
        pack.forward(request, response);

	}

}
