package com.sist.dao;
import java.sql.*;
import javax.sql.*;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.Date; //왜 이건 java.utl.*안에 안속하지..?
import java.text.SimpleDateFormat;
import java.util.*;
//import java.util.ArrayList;
//import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
import org.jsoup.select.*;

import com.sist.vo.EventVO;
import com.sist.vo.EventWinnerVO;

public class EventMainDAO {
	

	private Connection conn; // 오라클 연결 객체
	private PreparedStatement ps; // SQL문장 전송 객체
	private final String URL = "jdbc:oracle:thin:@오라클주소:1521:XE"; // 오라클 서버 주소

	public EventMainDAO() { // 1. 드라이버 등록
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
	/*****************************************************************************************************/
	/*****************************************데이터 크롤링 파트 ************************************************/
	   
	// 목록 삽입에 대한 함수
	public void eventDataInsert(EventVO vo) {
		try {

			getConnection();

			String sql = "INSERT INTO event_main2 VALUES(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getMno());
			ps.setString(2, vo.getCategory());
			ps.setString(3, vo.getPoster());
			ps.setString(4, vo.getTitle());
			ps.setString(5, vo.getTerm());
			ps.setString(6, vo.getState());
			ps.setString(7, vo.getContent());

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
   /********************************************************************************************************/
	
	
	public void eventDataCrawl_theater() { /*이벤트-극장부분 크롤링*/
		EventMainDAO dao = new EventMainDAO();
		
		try {
			int k = 1;
			for(int i=1;i<=20;i++) { //몇년전 게시물들이라 20페이지까지만 긁음.
			
			Document doc = Jsoup.connect("https://www.daehancinema.co.kr/Event/List?TYPE=EVENTTYPE02&PAGE_NO="+i).get();
			//mno==k임을 밑에서 넣어줌. category=="극장"임을 밑에서 넣어줌.
			Elements poster = doc.select("div.img_thumb > a > img");//
			Elements title = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(1)");
			Elements term = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(2)");
			
			String state = null;
			Elements content=null;
			String startdate = null;
			String enddate =null;
			Date startdate_date = null;
			Date enddate_date = null;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			
			//위까지는 일반페이지에서의 데이터크롤링, 아래는 상세페이지에서의 데이터 크롤링
			Elements poster_a = doc.select("div.img_thumb > a");
			String poster_aLink=null;
			//docLink==https://www.daehancinema.co.kr/Event/List?TYPE=EVENTTYPE02&PAGE_NO=1 //1대신 i가 들어감.
			//doc_detailLink==https://www.daehancinema.co.kr/Event/Detail?SEQ=4539 //인데,
			//<a>태그 주소를 보면 <a href="Event/Detail?SEQ=4593">으로 되어있음. 
			
			
		
			for (int j = 0; j < title.size(); j++) {
				try {
					System.out.println("mno:" + k);
					System.out.println("이미지:" + poster.get(j).attr("src"));
					System.out.println("제목:" + title.get(j).text());
					System.out.println("기간:" + term.get(j).text());
					
					poster_aLink=poster_a.get(j).getElementsByAttribute("href").attr("href");
					System.out.println("a링크주소:"+"https://www.daehancinema.co.kr/Event/"+poster_aLink);
					Document doc_detail = Jsoup.connect("https://www.daehancinema.co.kr/Event/"+poster_aLink).get();
					content = doc_detail.select("div.event_area img");
					System.out.println("세부이미지주소:"+content.attr("src"));
					
					
					EventVO vo = new EventVO();
					vo.setMno(k);
					vo.setCategory("극장");
					vo.setPoster(poster.get(j).attr("src"));
					vo.setTitle(title.get(j).text());
					vo.setTerm(term.get(j).text());
					
					
					
					startdate=(vo.getTerm()).substring(0, 10); //문자열임.
					startdate_date = dateFormat.parse(startdate); //Date형임.
					
					//System.out.println("총글자수:"+vo.getTerm().length()); //테스트용.					
					if(vo.getTerm().length()>=23) {
						enddate=(vo.getTerm()).substring(13, 23); //문자열임.
						enddate_date = dateFormat.parse(enddate);//Date형임.
					}else {
						enddate=null;
						System.out.println("끝나는날짜:"+enddate_date);
					}
					
					enddate_date = dateFormat.parse(enddate);//Date형임.
					if((vo.getTerm()).contains("소진 시")) {
						state = "진행중인 이벤트";
					}else if((vo.getTerm()).contains("소진 완료")) {
						state = "지난 이벤트";
					}else {
						state = null;
					}
					
					if(state == null) {
						if((dateCompare(today,startdate_date))&&(dateCompare(enddate_date,today))) {
							//오늘날짜>=시작하는날짜 이고 끝나는날짜>=오늘날짜이면 , true&&true
							state = "진행중인 이벤트";
						}else if(dateCompare(startdate_date,today)) {
							//시작하는날짜>=오늘 이면 아직 안시작한 이벤트이고, 진행중인 이벤트에 들어갈 수 있음.(예정 이벤트 페이지 같은게 없으므로)
							state = "진행중인 이벤트";
						}else {
							state = "지난 이벤트";
						}
					}
					System.out.println("시작하는날:"+startdate);
					System.out.println("끝나는날:"+enddate);
					System.out.println("오늘날짜:"+today);
					System.out.println("이벤트상태:"+state);
					vo.setState(state);
					
					
	
					vo.setContent(content.attr("src"));
					
				
					dao.eventDataInsert(vo);
					System.out.println("=========================================");

					k++;
				} catch (Exception ex) {
				}
			}
			}

		} catch (Exception ex) {
		}
	}
/***************************************************************************************************/

	public void eventDataCrawl_movie() { /*이벤트-영화부분 크롤링*/
		EventMainDAO dao = new EventMainDAO();
		
		try {
			int k = 500;
			for(int i=1;i<=20;i++) { //몇년전 게시물들이라 20페이지까지만 긁음.
			
			Document doc = Jsoup.connect("https://www.daehancinema.co.kr/Event/List?TYPE=EVENTTYPE01&PAGE_NO="+i).get();
			//num
			Elements poster = doc.select("div.img_thumb > a > img");//
			//Elements daehan_event_category = "극장";
			Elements title = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(1)");
			Elements term = doc.select("div.event_link_box > div:nth-child(2) > p:nth-child(2)");
			
			
			String state = null;
			Elements content=null;
			String startdate = null;
			String enddate =null;
			Date startdate_date = null;
			Date enddate_date = null;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			
			//위까지는 일반페이지에서의 데이터크롤링, 아래는 상세페이지에서의 데이터 크롤링
			Elements poster_a = doc.select("div.img_thumb > a");
			String poster_aLink=null;
			//docLink==https://www.daehancinema.co.kr/Event/List?TYPE=EVENTTYPE02&PAGE_NO=1 //1대신 i가 들어감.
			//doc_detailLink==https://www.daehancinema.co.kr/Event/Detail?SEQ=4539 //인데,
			//<a>태그 주소를 보면 <a href="Event/Detail?SEQ=4593">으로 되어있음. 
			
			
		
			for (int j = 0; j < title.size(); j++) {
				try {
					System.out.println("mno:" + k);
					System.out.println("이미지:" + poster.get(j).attr("src"));
					System.out.println("제목:" + title.get(j).text());
					System.out.println("기간:" + term.get(j).text());
					
					poster_aLink=poster_a.get(j).getElementsByAttribute("href").attr("href");
					System.out.println("a링크주소:"+"https://www.daehancinema.co.kr/Event/"+poster_aLink);
					Document doc_detail = Jsoup.connect("https://www.daehancinema.co.kr/Event/"+poster_aLink).get();
					content = doc_detail.select("div.event_area img");
					System.out.println("세부이미지주소:"+content.attr("src"));
					
					
					EventVO vo = new EventVO();
					
					vo.setMno(k);					
					vo.setCategory("영화");					
					vo.setPoster(poster.get(j).attr("src"));					
					vo.setTitle(title.get(j).text());
					vo.setTerm(term.get(j).text());
					
					
					
					startdate=(vo.getTerm()).substring(0, 10); //문자열임.
					startdate_date = dateFormat.parse(startdate); //Date형임.
					
					//System.out.println("총글자수:"+vo.getTerm().length()); //테스트용.					
					if(vo.getTerm().length()>=23) {
						enddate=(vo.getTerm()).substring(13, 23); //문자열임.
						enddate_date = dateFormat.parse(enddate);//Date형임.
					}else {
						enddate=null;
						System.out.println("끝나는날짜:"+enddate_date);
					}
					
					
					
					
					
					if((vo.getTerm()).contains("소진 시")) { //이 두 if문에서 enddate==null 인 경우가 걸러짐. 
						state = "진행중인 이벤트";
					}else if((vo.getTerm()).contains("소진 완료")) {
						state = "지난 이벤트";
					}else {
						state = null; //즉 state==null 이면서 동시에 enddate==null일 수 없음. 
					}
					
					if(state == null) {
						if((dateCompare(today,startdate_date))&&(dateCompare(enddate_date,today))) {
							//오늘날짜>=시작하는날짜 이고 끝나는날짜>=오늘날짜이면 , true&&true
							state = "진행중인 이벤트";
						}else if(dateCompare(startdate_date,today)) {
							//시작하는날짜>=오늘 이면 아직 안시작한 이벤트이고, 진행중인 이벤트에 들어갈 수 있음.(예정 이벤트 페이지 같은게 없으므로)
							state = "진행중인 이벤트";
						}else {
							state = "지난 이벤트";
						}
					}
					
					System.out.println("시작하는날:"+startdate);
					System.out.println("끝나는날:"+enddate);
					System.out.println("오늘날짜:"+today);
					System.out.println("이벤트상태:"+state);
					vo.setState(state);
					
					
	
					vo.setContent(content.attr("src"));
					
				
					dao.eventDataInsert(vo);
					System.out.println("=========================================");

					k++;
				} catch (Exception ex) {
				}
			}
			}

		} catch (Exception ex) {
		}
	}
/*************************State 를 결정해줄 메소드들*******************************************************/

	/** 1. 소진시 까지 인가? 그렇다 => 진행중임 **/ 
	
	public boolean dateCompare(Date d1, Date d2){
		int compare = d1.compareTo(d2);
		if(compare>=0) {
			//d1:21-12-31이고 d2:21-03-30 이여서 d1>=d2이면
			return true; //날짜가 큰걸 true라고 하겠다.
		}else  {
			return false;
		}
	}
	
	
	//받아온 Elements를 String으로 변환하는 메소드를 작성하지말고,
	//db에서 받아온 term(String)의 10글자를 이용하자!! (위에적음)

   
   /**********************************************************************************************************
	
	/*****************************************************************************************************/
	/*****************************************************************************************************/
	
	public List<EventVO> eventMainDataList(String whatCategory, String whatFind) 
	   {
		   List<EventVO> list=new ArrayList<EventVO>();
		   try
		   {
			   getConnection();
			   String sql;
			   System.out.println("여기는DAO:"+whatCategory+","+whatFind);
			   if(whatFind==null) { //검색 데이터가 없을 때.
				   if(whatCategory=="전체") {
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' "
						   		+ "ORDER BY event_term DESC";
				   }else {
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' AND "
						   		+ "event_category = '" + whatCategory + "' "
						   		+ "ORDER BY event_term DESC";
				   }
			   }
			   else { //검색 데이터가 있을 때.
				   if(whatCategory=="전체") {
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' AND "
						   		+ "event_title LIKE '%"+whatFind+"%' ORDER BY event_term DESC";
				   }else {
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' AND "
						   		+ "event_category = '" + whatCategory + "' AND "
						   		+ "event_title LIKE '%"+whatFind+"%' ORDER BY event_term DESC";
				   }
			   }
			   
			   ps=conn.prepareStatement(sql);			
			   
			   // 실행 
			   ResultSet rs=ps.executeQuery();
			   while(rs.next())
			   {
				   EventVO vo=new EventVO();
				   vo.setMno(rs.getInt(1));
				   vo.setCategory(rs.getString(2));
				   vo.setPoster(rs.getString(3));
				   vo.setTitle(rs.getString(4));
				   vo.setTerm(rs.getString(5));
				   vo.setState(rs.getString(6));
				   vo.setContent(rs.getString(7));
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
	
	//지난이벤트 페이지 ***********************************************************************************************8
	// 지난이벤트페이지 페이징 : 이전 1page / totalpage 다음 스타일.
		public List<EventVO> eventLastEventData_Paging(int page, String whatFind){
			
			List<EventVO> list = new ArrayList<EventVO>();
			try {
				getConnection();
				String sql;
				System.out.println("들어온데이터 확인1: "+ whatFind);
				if(whatFind == null) {
					 sql="SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rownum as rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content "
						     +"FROM event_main2 WHERE event_poster IS NOT NULL AND event_state = '지난 이벤트' )) "
						     +"WHERE rnum BETWEEN ? AND ?";
				}else {
					sql="SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rownum as rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content "
						     +"FROM event_main2 WHERE event_poster IS NOT NULL AND event_state = '지난 이벤트' AND "
						     +"event_title LIKE '%" + whatFind + "%' )) "
						     +"WHERE rnum BETWEEN ? AND ?";
				}  	
				   ps=conn.prepareStatement(sql);
				   int rowSize=12;
				   int start=(rowSize*page)-(rowSize-1);
				   int end=rowSize*page;
				   ps.setInt(1, start);
				   ps.setInt(2, end);
				   
				   ResultSet rs=ps.executeQuery();
				   while(rs.next())
				   {
					   EventVO vo=new EventVO();
					   vo.setMno(rs.getInt(1));
					   vo.setCategory(rs.getString(2));
					   vo.setPoster(rs.getString(3));
					   vo.setTitle(rs.getString(4));
					   vo.setTerm(rs.getString(5));
					   vo.setState(rs.getString(6));
					   vo.setContent(rs.getString(7));
					   list.add(vo);
				   }
				   rs.close();
				   
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				disConnection();
			}			
			return list;
		}
		
		public int eventLastEventTotal_Paging(String whatFind) {
			   int total=0;
			   try
			   {
				   getConnection();
				   String sql;
				   System.out.println("들어온데이터 확인2: "+ whatFind);
				   
				   if(whatFind==null) {
					   sql="SELECT CEIL(COUNT(*)/12.0) FROM event_main2 "
					   		+ "WHERE event_poster IS NOT NULL AND "
					   		+ "event_state = '지난 이벤트'";
				   }else {
					   sql="SELECT CEIL(COUNT(*)/12.0) FROM event_main2 "
					   		+ "WHERE event_poster IS NOT NULL AND "
					   		+ "event_state = '지난 이벤트' AND "
					   		+ "event_title LIKE '%" + whatFind + "%'";
				   }
				   ps=conn.prepareStatement(sql);
				   ResultSet rs=ps.executeQuery();
				   rs.next();
				   total=rs.getInt(1);
				   rs.close();
			   }catch(Exception ex)
			   {
				   ex.printStackTrace();
			   }
			   finally
			   {
				   disConnection();
			   }
			   return total;
		   }
		
		// 이벤트 상세보기 페이지 
		   public EventVO eventDetailData(int mno)
		   {
			   EventVO vo=new EventVO();
			   try{
				   getConnection();
				   String sql="SELECT * FROM event_main2 WHERE mno=?";
				   
				   ps=conn.prepareStatement(sql);			
				   ps.setInt(1, mno);
				   ResultSet rs=ps.executeQuery();
				   rs.next();
				   // 실행 
				   
					   vo.setMno(rs.getInt(1));
					   vo.setCategory(rs.getString(2));
					   vo.setPoster(rs.getString(3));
					   vo.setTitle(rs.getString(4));
					   vo.setTerm(rs.getString(5));
					   vo.setState(rs.getString(6));
					   vo.setContent(rs.getString(7));
					   rs.close();
			   }catch(Exception ex)
			   {
				   ex.printStackTrace();
			   }
			   finally
			   {
				   disConnection();
			   }
			   return vo;
		   }
		   
		//*당첨자 페이지 ********************************************************************************************
		   public List<EventWinnerVO> boardListData() 
		   {
			   List<EventWinnerVO> list=new ArrayList<EventWinnerVO>();
			   try
			   {
				   getConnection();
				   String sql="SELECT * FROM event_winner_detail";
				   
				   ps=conn.prepareStatement(sql);			
				   
				   // 실행 
				   ResultSet rs=ps.executeQuery();
				   while(rs.next())
				   {
					   EventWinnerVO vo=new EventWinnerVO();
					   vo.setMno(rs.getInt(1));
					   vo.setCategory(rs.getString(2));
					   vo.setTitle(rs.getString(3));
					   vo.setRelease(rs.getString(4));
					   vo.setContent(rs.getString(5));
					   vo.setEvent_title(rs.getString(6));				   
					   vo.setGift(rs.getString(7));
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
		   
		   //* 이벤트 상세페이지 *******************************************************************
		   public EventWinnerVO eventBoardDetailData(int mno)
		   {
			   EventWinnerVO vo=new EventWinnerVO();
			   try{
				   getConnection();
				   String sql="SELECT * FROM event_winner_detail WHERE mno=?";
				   
				   ps=conn.prepareStatement(sql);			
				   ps.setInt(1, mno);
				   ResultSet rs=ps.executeQuery();
					rs.next();
				   // 실행 				   
					vo.setMno(rs.getInt(1));
					vo.setCategory(rs.getString(2));
					vo.setTitle(rs.getString(3));
					vo.setRelease(rs.getString(4));
					vo.setContent(rs.getString(5));
					vo.setEvent_title(rs.getString(6));				   
					vo.setGift(rs.getString(7));
					rs.close();
			   }catch(Exception ex)
			   {
				   ex.printStackTrace();
			   }
			   finally
			   {
				   disConnection();
			   }
			   return vo;
		   }

}
