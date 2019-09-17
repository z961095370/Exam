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
import javax.servlet.http.HttpSession;

import com.hcj.bean.Users;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：登录验证:
	 * 步骤：1读取用户名和密码
	 *     2查询用户是否存在
	 *       2.1 不存在，重定向到登录页面上
	 *       2.2 存在
	 *               保存用户信息到session中
	 *               根据用户的身份判断menu的内容
	 *               跳转到index.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    //0.局部变量生命
		       String userName,password=null;
		       Connection con = null;
		       PreparedStatement ps=null;
		       ResultSet rs =null;
		       String sql="select * from users where userName=? and password=?";
		       Users user  = null;
		    //1.读取用户名和密码
		       request.setCharacterEncoding("utf-8");
		       userName = request.getParameter("userName");
		       password = request.getParameter("password");
		    //2查询用户是否存在
		       try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");		           
		            ps=con.prepareStatement(sql);
		            ps.setString(1, userName);
		            ps.setString(2, password);
		            rs=ps.executeQuery();//根据主键查询，因此查询只有一行数据
		      //3)[数据行]内容转换为对象格式保存
		           if( rs.next()){
		        	   user = new Users();
		        	   user.setUserId(rs.getInt("userId"));
			            user.setUserName(rs.getString("userName"));
			            user.setPassword(rs.getString("password"));
			            user.setUserCode(rs.getString("userCode"));
			            user.setEmail(rs.getString("email"));
			            user.setTel(rs.getString("tel"));
			            user.setFlag(rs.getInt("flag"));
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
		       
		       if(user==null){
		    	   response.sendRedirect("/Exam/login.html");
		       }else{
		    	   HttpSession session = request.getSession();
		    	   session.setAttribute("userKey", user);
		    	   int flag = user.getFlag();
		    	   if(flag==1){//学员
		    		   request.setAttribute("menuKey","menu_student.jsp" );
		    	   }else{
		    		   request.setAttribute("menuKey","menu_teacher.jsp" );
		    	   }
		    	   request.getRequestDispatcher("/index.jsp").forward(request, response);
		       }
	}

}
