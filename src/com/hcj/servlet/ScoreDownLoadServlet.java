package com.hcj.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hcj.bean.Users;

/**
 * Servlet implementation class ScoreDownLoadServlet
 */
@WebServlet("/ScoreDownLoadServlet")
public class ScoreDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：查询当前学员信息，并下载
	 * 步骤：
	 *    1.获得学员的ID编号
	 *    2.查询学员考试成绩
	 *    3.将学员考试成绩生成excel文档
	 *    4.将生成的excel文档下载到学员的客户端
	 *    
	 *  poi工具包开发Excel文档的流程：
	 *   1.在内存中创建一个Excel文档
	 *   2.在Excel文档中创建一个sheet
	 *   3.占用sheet中指定位置的【行对象】
	 *   4.占用[行对象]指定单元格
	 *   5.将数据添加到【单元格】
	 *   6.通过输出流，将内存中Excel文档编译并输出到
	 *     硬盘上  
	 *    
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   //0.局部变量
		    HttpSession session = request.getSession();
		    Users user=(Users) session.getAttribute("userKey");
		    Connection con = null;
		    PreparedStatement ps=null;
		    ResultSet rs = null;
		    String sql="select * from score where userId = ?";
		    HSSFWorkbook excelObj = new HSSFWorkbook();
		    HSSFSheet sheetObj = excelObj.createSheet(user.getUserName()+"成绩一览");
		    int line = 0;
		    ServletContext application=this.getServletContext();
		    String proPath=application.getRealPath("/");
		    OutputStream out = new FileOutputStream(proPath+"score.xls");
		   //1.获得学员的ID编号
		    int userId = user.getUserId();
		   //2.查询学员考试成绩
		    try{
	        	Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");		           
	            ps=con.prepareStatement(sql);
	            ps.setInt(1, Integer.valueOf(userId));
	            rs=ps.executeQuery();
	            while(rs.next()){
	            	HSSFRow row = sheetObj.createRow(line++);
	            	HSSFCell c0=row.createCell(0);
	            	HSSFCell c1=row.createCell(1);
	            	HSSFCell c2=row.createCell(2);
	            	c0.setCellValue(rs.getString("userName"));
	            	c1.setCellValue(rs.getString("userScore"));
	            	c2.setCellValue(rs.getString("examTime"));
	            }
	            excelObj.write(out);
	            out.close();
	        //3.将学员考试成绩生成excel文档    
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
		   // 4.将生成的excel文档下载到学员的客户端
		    response.setContentType("text/plain");
		    response.setHeader("Content-Disposition", "attachment;filename=score.xls");
		    InputStream in = new FileInputStream(proPath+"score.xls");
		    ServletOutputStream out2=response.getOutputStream();
		    byte array[]=new byte[1024];
		    while(in.read(array)!=-1){
		    	out2.write(array);
		    }
		    in.close();
	}

}
