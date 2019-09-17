package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcj.bean.Question;
import com.hcj.bean.Users;

/**
 * Servlet implementation class PanFenServlet
 */
@WebServlet("/PanFenServlet")
public class PanFenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int socre=0;
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("userKey");
		List<Question> questionList = (List<Question>) session.getAttribute("questionList");
	    for(Question q:questionList){
	    	  String userAnswer=   request.getParameter("question"+q.getId());
	    	  if(q.getAnswer().equalsIgnoreCase(userAnswer)){
	    		  socre+=25;
	    	  }
	    }
	    
	    //System.out.println(socre);
	    
	    Connection con = null;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
            String sql="insert into score (userId,userName,userScore,examTime) values(?,?,?,now())";	        
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setInt(3, socre);
            ps.executeUpdate();
            System.out.println(socre);
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
        
        RequestDispatcher pack= request.getRequestDispatcher("/ScoreFindServlet");
        pack.forward(request, response);
        
	}

}
