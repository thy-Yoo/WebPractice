package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO {
	// 오라클 연결 객체
	private Connection conn;
	// SQL문장 전송 객체
	private PreparedStatement ps;
	// 오라클 서버 주소
	private final String URL = "jdbc:oracle:thin:@litlyoo:1521:XE";

	// 1. 드라이버 등록
	public LoginDAO() {
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
	  
	 public String login(String id, String pwd)
	 {
		 String result="";
		  try
		  {
			  getConnection();
			  String sql="SELECT COUNT(*) "
					    +"FROM project_member "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql); //id가 존재하는지 체크
			  ps.setString(1, id);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  int count=rs.getInt(1);
			  rs.close();
			  
			  if(count==0) //ID가 없는 상태
			  {
				  result="NOID";
			  }
			  else // ID가 있는 상태
			  {
				  sql="SELECT pwd,name,admin FROM project_member "
					 +"WHERE id=?";
				  ps=conn.prepareStatement(sql);
				  ps.setString(1, id);
				  rs=ps.executeQuery();
				  rs.next();
				  String db_pwd=rs.getString(1);
				  String name=rs.getString(2);
				  String admin=rs.getString(3);
				  rs.close();
				  // 비밀번호 확인 
				  if(db_pwd.equals(pwd))//로그인
				  {
					  result=name+"|"+admin;
				  }
				  else
				  {
					  result="NOPWD";
				  }
			  }
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return result;
	 }
}
