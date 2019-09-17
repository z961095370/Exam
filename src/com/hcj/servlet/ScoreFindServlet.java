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
import javax.servlet.http.HttpSession;

import com.hcj.bean.UserScore;
import com.hcj.bean.Users;

/**
 * Servlet implementation class ScoreFindServlet
 */
@WebServlet("/ScoreFindServlet")
public class ScoreFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：分页查询当前用户的成绩
	 * 步骤：       1.读取用户通过浏览器发送的【请求页数】
               2.计算【当前页的起始行位置】
               3.JDBC将输送【分页查询命令】到数据库中
                                         查询隶属于当前页的数行
               4.将得到数据通过I/O流输送到用户的浏览器   
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      //1.读取用户通过浏览器发送的【请求页数】
	      String str_Page=request.getParameter("pageNo");
          int pageNo= str_Page!=null && !"".equals(str_Page)?Integer.valueOf(str_Page):1;
          HttpSession session = request.getSession();
          Users user=(Users) session.getAttribute("userKey");
        // HttpSession session=request.getSession();
         //Users user=(Users) session.getAttribute("userKey");
        //2.计算【当前页的起始行位置】
        int startLine = (pageNo-1) * 3;
        // 3.JDBC将输送【分页查询命令】到数据库中查询隶属于当前页的数行
        List list = new ArrayList();
        int totalPage = 0;//总页数
        try{
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
	          PreparedStatement ps= con.prepareStatement("select * from score where userName=?  limit ?,3");
		      ps.setString(1, user.getUserName());
	          ps.setInt(2, startLine);
		      ResultSet rs=ps.executeQuery();
		   //计算总页数
		      PreparedStatement ps2=con.prepareStatement("select count(*) from score where userName=? ");
		      ps2.setString(1, user.getUserName());
		      ResultSet rs2=ps2.executeQuery();
		      rs2.next();
		      int totalCount=rs2.getInt(1);
		      totalPage = totalCount%3==0?totalCount/3:totalCount/3+1;
		  //4.将得到数据通过I/O流输送到用户的浏览器			      
		      while(rs.next()){
		    	   UserScore us = new UserScore();
		    	   us.setUserScore(rs.getInt("userScore"));
		    	   us.setExamTime(rs.getDate("examTime"));
		    	   us.setUserName(rs.getString("userName"));
		    	   us.setUserId(rs.getInt("userId"));
		    	  list.add(us);
		      }//user牺牲了
        }catch(Exception ex){
      	  ex.printStackTrace();
        }
        //4.将得到数据通过I/O流输送到用户的浏览器
        request.setAttribute("scoreList", list);
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("totalPage", totalPage);
        RequestDispatcher pack= request.getRequestDispatcher("/user/examsScore.jsp");
        pack.forward(request, response);

	}

	

}
