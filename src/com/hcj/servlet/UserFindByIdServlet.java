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

import com.hcj.bean.Users;

/**
 * Servlet implementation class UserFindByIdServlet
 */
@WebServlet("/UserFindByIdServlet")
public class UserFindByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：根据【用户编号】查询用户信息，推送给浏览器
	 * 步骤：
	 *     1）获得浏览器发送的【主键编号内容】
           2）JDBC查询与【主键编号内容】关联的[数据行]
           3)[数据行]内容转换为对象格式保存
             ResultSet------>Users对象
           4）通过请求转发，将Users对象输送到
                               指定jsp页面上  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    //0.局部变量
		      String userId = null;
		      Connection con = null;
		      PreparedStatement ps = null;
		      ResultSet rs = null;
		      String sql ="select * from users where userId=?";
		      Users user = new Users();
		    //1.获得浏览器发送的【主键编号内容】
		      userId=request.getParameter("ck");
		    //2.JDBC查询与【主键编号内容】关联的[数据行]
		      try{
		        	Class.forName("com.mysql.jdbc.Driver");
			        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");		           
		            ps=con.prepareStatement(sql);
		            ps.setInt(1, Integer.valueOf(userId));
		            rs=ps.executeQuery();//根据主键查询，因此查询只有一行数据
		      //3)[数据行]内容转换为对象格式保存
		            rs.next();
		            user.setUserId(rs.getInt("userId"));
		            user.setUserName(rs.getString("userName"));
		            user.setPassword(rs.getString("password"));
		            user.setUserCode(rs.getString("userCode"));
		            user.setEmail(rs.getString("email"));
		            user.setTel(rs.getString("tel"));
		            user.setFlag(rs.getInt("flag"));
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
		        request.setAttribute("userKey", user);
		        request.getRequestDispatcher("/usermanager/userEdit.jsp").forward(request, response);
	}

}
