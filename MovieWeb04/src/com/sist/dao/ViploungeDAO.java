package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sist.vo.EventVO;
import com.sist.vo.ViploungeVO;

public class ViploungeDAO {
	private Connection conn; // 오라클 연결 객체
	private PreparedStatement ps; // SQL문장 전송 객체
	private final String URL = "jdbc:oracle:thin:@오라클주소:1521:XE"; // 오라클 서버 주소

	public ViploungeDAO() { // 1. 드라이버 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void getConnection() { // 2. 오라클 연결
		try {
			conn = DriverManager.getConnection(URL, "오라클id", "오라클pw");
			// conn hr/happy
		} catch (Exception ex) {
		}
	}
	public void disConnection() { // 3. 오라클 해제
		try {
			if (ps != null)
				ps.close(); // 연결중이면 닫는다
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}
	
	/***********************************************************************************************************/
	
	 public ViploungeVO viploungeUserData(String idstr)
	   {
		   ViploungeVO vo=new ViploungeVO();
		   try{
			   getConnection();
			   String sql="SELECT * FROM membership_benefit WHERE userid=?"; //받아온 id와 userid가 같은 행의 데이터를 뽑아온다.
			   
			   ps=conn.prepareStatement(sql);			
			   ps.setString(1, idstr);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   
			   
			   vo.setMno(rs.getInt(1));
			   vo.setUserid(rs.getString(2));
			   vo.setUsergrade(rs.getString(3));
			   vo.setTotal_point(rs.getInt(4));
			   vo.setTotal_ticketnums(rs.getInt(5));
			   rs.close();
		   }catch(Exception ex) {
			   ex.printStackTrace();
		   }
		   finally {
			   disConnection();
		   }
		   return vo;
	   }
	 
	 
	 public ViploungeVO memberDataUpdate() {
		 
		ViploungeVO vo = new ViploungeVO();
		try {
			getConnection();
			String sql= "INSERT INTO membership_benefit(userid) SELECT id FROM project_member "
					+ "WHERE NOT EXISTS (SELECT userid FROM membership_benefit WHERE membership_benefit.userid = project_member.id)";
			ps=conn.prepareStatement(sql);			
			  
			   ResultSet rs=ps.executeQuery();
			   vo.setUserid(rs.getString(1));
			   rs.next();
			   rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	 }
	
}
