package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionDeleteBatchServlet
 */
@WebServlet("/QuestionDeleteBatchServlet")
public class QuestionDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：批处理删除指定的重复试题内容
	 * 步骤：
	 *     1.获得试题编号
	 *     2.jdbc批处理删除试题内容
	 *     3.请求转发，回到重复试题查询
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //0.局部变量声明
	        String questionIdArray[]=null;
	        Connection con = null;
	        PreparedStatement ps=null;
	      //1.获得试题编号
	        questionIdArray= request.getParameterValues("ck");    
	      //2.jdbc，将数组中数据编辑成对应的sql命令，并填压到
            //PS对象中【弹夹】
	        try{
	        	Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
	            con.setAutoCommit(false);//数据库将事务管理权交给当前程序，也就交给con
	            ps=con.prepareStatement("delete from question where id=?");
	            for(int i=0;i<questionIdArray.length;i++){
	            	String id = questionIdArray[i];
	            	ps.setInt(1, Integer.valueOf(id));
	            	ps.addBatch();
	            }
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	      //3.通过监控形式，判断我们输送的这些sql命令在数据库中
        //  是否正常执行，从而来决定是否进行事务提交或则事务回滚. 
	        try{
	          ps.executeBatch();
	          con.commit();
	        }catch(SQLException ex){
	        	ex.printStackTrace();
	        	try {
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	       // 4.通过请求转发，向tomcat申请【UserFindServlet】展示
         // 删除之后剩余的用户信息  
	        RequestDispatcher pack= request.getRequestDispatcher("/QuestionFindSameByTitile");
		    pack.forward(request, response);

	}

}
