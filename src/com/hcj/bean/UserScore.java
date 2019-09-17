package com.hcj.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserScore implements Serializable {
	
	private int userId ;
    private String userName ;
    private int userScore ;
    private Date examTime ;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	public Date getExamTime() {
		return examTime;
	}
	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}
//    public static void main(String[] args) {
//    	Connection con = null;
//		PreparedStatement ps = null;
//    	String sql = "insert into score values(?,?,?,?)";
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3366/examdb", "root", "123");
//			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//			ps = con.prepareStatement(sql);
//			for (int i = 0; i < 1000; i++) {
//				int userId = i;
//				String userName ="张三"+i;
//				int userScore = new Random().nextInt(100);
//				ps.setInt(1, userId);
//				ps.setString(2, userName);
//				ps.setInt(3, userScore);
//				ps.setString(4, date);
//				ps.executeUpdate();
//			}
//			System.out.println("1111");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
