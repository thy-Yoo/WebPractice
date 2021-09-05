package com.yjy.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*; //Context, InitialContext
import javax.sql.DataSource; //DataSource

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DaehanEventDAO {

	// 오라클 연결 객체
	private Connection conn;
	// SQL문장 전송 객체
	private PreparedStatement ps;
	// 오라클 서버 주소
	

	// 1. 드라이버 등록
	public DaehanEventDAO() {
		try {
					
									
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 2. 오라클 연결
	public void getConnection() {
		try {
			
			Context init=new InitialContext();//JNDI => 초기화 
    		// Java Naming Directory Interface => 탐색기 형식 
    		
    		Context c=(Context)init.lookup("java://comp//env");//lookup => 이름으로 객체의 주소를 찾아 온다 
    		// c드라이브 
    		DataSource ds=(DataSource)c.lookup("jdbc/oracle");
    		
    		conn=ds.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
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
	
	//********************************************************************************//
	//********************************************************************************//
	// 4. 대한극장 이벤트 입력
	// 4.1 전체 목록 출력에 대한 함수.
		public ArrayList<DaehanEventVO> daehanEventListData() {
			ArrayList<DaehanEventVO> list = new ArrayList<DaehanEventVO>();
			try {
				// 1. 연결
				getConnection();
				// 2. SQL문장 전송
				String sql = "SELECT * FROM daehanEvent"; // ORDER BY empno ASC";
				ps = conn.prepareStatement(sql);
				// 3. 실행후 결과 읽기
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					DaehanEventVO vo = new DaehanEventVO();
					vo.setNum(rs.getInt(1));
					vo.setCategory(rs.getString(2));
					vo.setPoster(rs.getString(3));
					vo.setTitle(rs.getString(4));
					vo.setDates(rs.getString(5));
					vo.setKey(rs.getString(6));

					list.add(vo);
				}
				rs.close();
			} catch (Exception ex) {
				// 오류확인
				ex.printStackTrace();
			} finally {
				// 닫기
				disConnection();
			}
			return list;
		}
		//*************************************오류생기면 지우기 0820적은 메소드*******//
		//4.2 이벤트 중 영화관련 목록 출력에 대한 함수.
				public ArrayList<DaehanEventVO> daehanEventListData_movie() {
					ArrayList<DaehanEventVO> list = new ArrayList<DaehanEventVO>();
					try {
						// 1. 연결
						getConnection();
						// 2. SQL문장 전송
						String sql = "SELECT * FROM daehanEvent WHERE category='영화'"; 
						ps = conn.prepareStatement(sql);
						// 3. 실행후 결과 읽기
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							DaehanEventVO vo = new DaehanEventVO();
							vo.setNum(rs.getInt(1));
							vo.setCategory(rs.getString(2));
							vo.setPoster(rs.getString(3));
							vo.setTitle(rs.getString(4));
							vo.setDates(rs.getString(5));
							vo.setKey(rs.getString(6));

							list.add(vo);
						}
						rs.close();
					} catch (Exception ex) {
						// 오류확인
						ex.printStackTrace();
					} finally {
						// 닫기
						disConnection();
					}
					return list;
				}
		//4.3 이벤트 중 극장관련 목록 출력에 대한 함수.
				public ArrayList<DaehanEventVO> daehanEventListData_theater() {
					ArrayList<DaehanEventVO> list = new ArrayList<DaehanEventVO>();
					try {
						// 1. 연결
						getConnection();
						// 2. SQL문장 전송
						String sql = "SELECT * FROM daehanEvent WHERE category='극장'"; 
						ps = conn.prepareStatement(sql);
						// 3. 실행후 결과 읽기
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							DaehanEventVO vo = new DaehanEventVO();
							vo.setNum(rs.getInt(1));
							vo.setCategory(rs.getString(2));
							vo.setPoster(rs.getString(3));
							vo.setTitle(rs.getString(4));
							vo.setDates(rs.getString(5));
							vo.setKey(rs.getString(6));

							list.add(vo);
						}
						rs.close();
					} catch (Exception ex) {
						// 오류확인
						ex.printStackTrace();
					} finally {
						// 닫기
						disConnection();
					}
					return list;
				}
		//*************************************************************************//
		//*************************************************************************//
		
		//목록 삽입에 대한 함수
		public void daehanEventDataInsert(DaehanEventVO vo) {
			try {
				
				getConnection();
				
				String sql = "INSERT INTO daehanEvent VALUES(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, vo.getNum());
				ps.setString(2, vo.getCategory());	
				ps.setString(3, vo.getPoster());
				ps.setString(4, vo.getTitle());
				ps.setString(5, vo.getDates());	
				ps.setString(6, vo.getKey());
				
				ps.executeUpdate(); 
				
			} catch (Exception ex) {
				ex.printStackTrace(); 
			} finally {
				disConnection(); 
			}
		}
		
		//6. 데이터 크롤하기
		public void daehanEvent_theater_DataCrawling() { /*이벤트-극장부분 크롤링*/
			DaehanEventDAO dao = new DaehanEventDAO();
			
			try {
				int k = 1;
				for(int i=1;i<=4;i++) { //괄호 아직 안닫음.
				
				Document doc = Jsoup.connect("https://www.daehancinema.co.kr/Event/List?TYPE=EVENTTYPE02&PAGE_NO="+i).get();
				//num
				Elements daehan_event_poster = doc.select("div.img_thumb > a > img");//
				//Elements daehan_event_category = "극장";
				Elements daehan_event_title = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(1)");
				Elements daehan_event_dates = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(2)");
				//key
				
			
				for (int j = 0; j < daehan_event_title.size(); j++) {
					try {
						System.out.println("NUM:" + k);
						System.out.println("이미지:" + daehan_event_poster.get(j).attr("src"));
						System.out.println("제목:" + daehan_event_title.get(j).text());
						System.out.println("날짜:" + daehan_event_dates.get(j).text());
				
						DaehanEventVO vo = new DaehanEventVO();
						vo.setNum(k);
						vo.setCategory("극장");
						vo.setPoster(daehan_event_poster.get(j).attr("src"));
						vo.setTitle(daehan_event_title.get(j).text());
						vo.setDates(daehan_event_dates.get(j).text());
					
						dao.daehanEventDataInsert(vo);
						System.out.println("=========================================");

						k++;
					} catch (Exception ex) {
					}
				}
				}

			} catch (Exception ex) {
			}
		}
		
		
		
		public void daehanEvent_theater_DataCrawling2() { /*이벤트-영화부분 크롤링*/
			DaehanEventDAO dao = new DaehanEventDAO();
			
			try {
				int k = 101;
				for(int i=1;i<=4;i++) { //임시로 4페이지만큼 긁음. 실제론 더 긁어와야함.
				
				Document doc = Jsoup.connect("https://www.daehancinema.co.kr/Event/List?TYPE=EVENTTYPE01&PAGE_NO="+i).get();
				//num
				Elements daehan_event_poster = doc.select("div.img_thumb > a > img");//
				//Elements daehan_event_category = "극장";
				Elements daehan_event_title = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(1)");
				Elements daehan_event_dates = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(2)");
				//key
				
			
				for (int j = 0; j < daehan_event_title.size(); j++) {
					try {
						System.out.println("NUM:" + k);
						System.out.println("이미지:" + daehan_event_poster.get(j).attr("src"));
						System.out.println("제목:" + daehan_event_title.get(j).text());
						System.out.println("날짜:" + daehan_event_dates.get(j).text());
				
						DaehanEventVO vo = new DaehanEventVO();
						vo.setNum(k);
						vo.setCategory("영화");
						vo.setPoster(daehan_event_poster.get(j).attr("src"));
						vo.setTitle(daehan_event_title.get(j).text());
						vo.setDates(daehan_event_dates.get(j).text());
					
						dao.daehanEventDataInsert(vo);
						System.out.println("=========================================");

						k++;
					} catch (Exception ex) {
					}
				}
				}

			} catch (Exception ex) {
			}
		}

}
