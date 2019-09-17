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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：更新用户信息
	 * 步骤：
	 *           1)获得浏览器发送的更新后的数据
                 2）JDBC将更新后的数据输送到数据库
                 3）请求转发，调用【用户信息】查询向用户浏览器上
                                               展示更新的数据
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.读取来自于浏览器发送的请求参数
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String userId= request.getParameter("userId");
        /*
         *  关于读取【请求参数】时，常见的异常
         */
          //Integer.valueOf(userCode);
        //2.通过【JDBC】将得到的参数插入到数据库中
        Connection con = null;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
            String sql="update users set userCode=?,userName=?,password=?,email=?,tel=? where userId=? ";	        
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, userCode);
            ps.setString(2, userName);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, tel);
            ps.setInt(6, Integer.valueOf(userId));
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
        //3）请求转发，调用【用户信息】查询向用户浏览器上展示更新的数据
          request.getRequestDispatcher("/UserFindServlet").forward(request, response);

	}

}
