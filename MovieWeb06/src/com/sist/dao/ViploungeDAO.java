package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sist.vo.EventVO;
import com.sist.vo.ViploungeVO;

public class ViploungeDAO {
	// 오라클 연결 객체
			private Connection conn;
			// SQL문장 전송 객체
			private PreparedStatement ps;
			// 오라클 서버 주소
			private final String URL = "jdbc:oracle:thin:@litlyoo:1521:XE";

			// 1. 드라이버 등록
			public ViploungeDAO() {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 2. 오라클 연결
			public void getConnection() {
				try {
					conn = DriverManager.getConnection(URL, "ouo", "litl");
					// conn hr/happy
				} catch (Exception ex) {
				}
			}

			// 3. 오라클 해제 => JDBC => DBCP => ORM(MyBatis,JPA,Hibernate...)
			public void disConnection() {
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
			   //rs.next();			   
			   //부적합한 열 인덱스가 뜸. while문으로 감싸면 어떤가?
			   while(rs.next())
			   {
				   vo.setMno(rs.getInt(1));
				   vo.setUserid(rs.getString(2));
				   vo.setUsergrade(rs.getString(3));
				   vo.setTotal_point(rs.getInt(4));
				   vo.setTotal_ticketnums(rs.getInt(5));
			   }
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
			String sql = "INSERT INTO membership_benefit(userid) SELECT id FROM project_member "
					+ "WHERE NOT EXISTS (SELECT userid FROM membership_benefit WHERE membership_benefit.userid = project_member.id)";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			/* 부적합한 열 인덱스라고 함. 웹에서 출력은 되는데 뭔가 문제가 있나봄. 
			 * vo.setUserid(rs.getString(1));
			 * rs.next();*/
			while(rs.next()){
			vo.setUserid(rs.getString(1));
			}
			//rs.next();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	
}
