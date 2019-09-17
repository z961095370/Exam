package com.hcj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcj.bean.Users;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScoreServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userScore from score";
		request.setCharacterEncoding("utf-8");
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int score = rs.getInt(1);
				switch (score / 10) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
					int value = 0;
					String key = "不及格";
					if (map.get(key) == null || "".equals(map.get(key))) {
						value++;
					} else {
						value = map.get(key) + 1;
					}
					map.put(key, value);
					break;
				case 6:
					value = 0;
					key = "及格";
					if (map.get(key) == null || "".equals(map.get(key))) {
						value++;
					} else {
						value = map.get(key) + 1;
					}
					map.put(key, value);
					break;
				case 7:
				case 8:
					value = 0;
					key = "良好";
					if (map.get(key) == null || "".equals(map.get(key))) {
						value++;
					} else {
						value = map.get(key) + 1;
					}
					map.put(key, value);
					break;
				case 9:
				case 10:
					value = 0;
					key = "优秀";
					if (map.get(key) == null || "".equals(map.get(key))) {
						value++;
					} else {
						value = map.get(key) + 1;
					}
					map.put(key, value);
					break;
				}
			}
			request.setAttribute("map", map);
			request.getRequestDispatcher("/user/score_Anaylst.jsp").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
