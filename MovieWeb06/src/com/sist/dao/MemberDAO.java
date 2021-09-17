package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.MemberVO;
import com.sist.vo.ZipcodeVO;

import javax.naming.*;
public class MemberDAO {
	// 오라클 연결 객체
	private Connection conn;
	// SQL문장 전송 객체
	private PreparedStatement ps;
	// 오라클 서버 주소
	private final String URL = "jdbc:oracle:thin:@오라클주소:1521:XE";

	// 1. 드라이버 등록
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 2. 오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "오라클id", "오라클pw");
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
  // 기능 => 아이디 중복 체크!
  public int memberidCheck(String id)
  {
	  int count=0;
	  try
	  {
		  getConnection();
		  String sql="SELECT COUNT(*) FROM project_member "
				    +"WHERE id=?";
		  // count=0 (사용가능한 ID), count=1 (사용중인 ID)
		  ps=conn.prepareStatement(sql);
		  ps.setString(1,id);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  count=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return count;
  }
  
  
  // 기능 => 우편번호 검색 
  public List<ZipcodeVO> postfind(String dong)
  {
	  System.out.println("postfind:"+dong);
	  List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
	  try
	  {
		  getConnection();
		  String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') "
				    +"FROM zipcode "
				    +"WHERE dong LIKE '%'||?||'%'";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1,dong);
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  ZipcodeVO vo=new ZipcodeVO();
			  vo.setZipcode(rs.getString(1));
			  vo.setSido(rs.getString(2));
			  vo.setGugun(rs.getString(3));
			  vo.setDong(rs.getString(4));
			  vo.setBunji(rs.getString(5));
			  list.add(vo);
		  }
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return list;
  }
  public int postfindCount(String dong)
  {
	  int count=0;
	  try
	  {
		  getConnection();
		  String sql="SELECT COUNT(*) "
				    +"FROM zipcode "
				    +"WHERE dong LIKE '%'||?||'%'";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1,dong);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  count=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return count;
  }
  // 실제 회원가입 
  public void memberJoinInsert(MemberVO vo)
  {
	  try
	  {
		  getConnection();
		  String sql="INSERT INTO project_member VALUES(?,?,?,?,?,"
				   +"?,?,?,?,?,?,'n')"; // admin(n=일반,y=관리자)
		  ps=conn.prepareStatement(sql);
		  ps.setString(1, vo.getId());
		  ps.setString(2, vo.getPwd());
		  ps.setString(3, vo.getSex());
		  ps.setString(4, vo.getName());
		  ps.setString(5, vo.getBirthday());
		  
		  ps.setString(6, vo.getEmail());
		  ps.setString(7, vo.getPost());
		  ps.setString(8, vo.getAddr1());
		  ps.setString(9, vo.getAddr2());
		  ps.setString(10, vo.getTel());
		  ps.setString(11, vo.getGenre());
		  
		  ps.executeUpdate();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
  }
  // 로그인 
  public String isLogin(String id,String pwd)
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
