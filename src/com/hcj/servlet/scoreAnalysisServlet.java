package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcj.bean.Users;
import com.hcj.bean.UserScore;

/**
 * Servlet implementation class scoreAnalysisServlet
 */
@WebServlet("/scoreAnalysisServlet")
public class scoreAnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public scoreAnalysisServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String userId = null;
	      Connection con = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	   
	      try{
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }finally{
	        	try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        request.getRequestDispatcher("/usermanager/userExamScore.jsp").forward(request, response);
}

}
