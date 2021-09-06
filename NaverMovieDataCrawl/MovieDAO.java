package com.yjy.dao;

import java.sql.*; //Connection, PreparedStatements

public class MovieDAO {
	
	// 오라클 연결 객체 
	private Connection conn;
	// SQL문장 전송 객체
	private PreparedStatement ps;
	// 오라클 서버 주소
	//private final String URL = "jdbc:oracle:thin:@litlyoo:1521:XE";
	private final String URL = "jdbc:oracle:thin:@litlyoo:1521:XE";
	// 1. 드라이버 등록
	public MovieDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 2. 오라클 연결
	public void getConnection() {
		try {
			//conn = DriverManager.getConnection(URL, "ouo", "litl");
			conn = DriverManager.getConnection(URL, "ouo", "litl");
			// conn hr/happy
		} catch (Exception ex) {
		}
	}

	// 3. 오라클 해제  => JDBC => DBCP => ORM(MyBatis,JPA,Hibernate...)
	public void disConnection() {
		try {
			if (ps != null)
				ps.close(); 
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}

	// 4. 데이터 등록
	public void naverMovieDataInsert(MovieVO vo) {
		try {
			
			getConnection();
			
			//String sql = "INSERT INTO naverMovie VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "INSERT INTO navermovie_chart_0811 VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNum());
			ps.setString(2, vo.getPoster());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getGenre());
			ps.setString(5, vo.getDirector());
			ps.setString(6, vo.getActor());
			ps.setString(7, vo.getTicketsales());
			ps.setString(8, vo.getGrade());
			ps.setString(9, vo.getRate());
			ps.setString(10, vo.getRunningtime());
			ps.setString(11, vo.getReleasedate());
			ps.setString(12, vo.getKey());
			
			ps.executeUpdate(); 
			
		} catch (Exception ex) {
			ex.printStackTrace(); 
		} finally {
			disConnection(); 
		}
	}

}
