package com.sist.model;

import java.util.*;//List

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.EventVO;
import com.sist.vo.EventWinnerVO;

@Controller
public class EventModel {
	
	   /*모든 이벤트 리스트를 받아오는 용도*/ 
	
	   //header.jsp의 진행중인 이벤트 <a>태그에 해당 주소가 걸려있음. event_category_all.do
	   //event_category_all.do를 하라 => main_jsp에 ../event/evnet_category_all.jsp를 보냄.
		
	
		/*************************진행중인이벤트Page-전체Page***************************************/	
	   @RequestMapping("event/event_category_all.do")
	   public String event_category_all(HttpServletRequest request,HttpServletResponse response)
	   { 
		   EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventAllData();	//진행중인 이벤트, all부분
		   /*검색기능*/
		   String findstr = request.getParameter("eventFindText");
		   System.out.println(findstr);
		   if(findstr!=null) {
			   list = dao.eventAllData_Finder(findstr);
		   }   
		
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_category_all.jsp");
		   return "../main/main.jsp";
	   }
	   /*************************진행중인이벤트Page-영화Page***************************************/		
	   @RequestMapping("event/event_category_movie.do")
	   public String event_category_movie(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String findstr = request.getParameter("eventFindText");
		   
		   EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventMovieData(findstr);	//진행중인 이벤트, 영화부분
		   //전체 와는 다른 방식으로 구현해보았다. movie파트가 더 효율적인듯 하다. 
		
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_category_movie.jsp");
		   return "../main/main.jsp";
	   }
	   /*************************진행중인이벤트Page-극장Page***************************************/		
	   @RequestMapping("event/event_category_theater.do")
	   public String event_category_theater(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String findstr = request.getParameter("eventFindText");
		   
		   EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventTheaterData(findstr);	//진행중인 이벤트, 극장부분		
		   
		   
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_category_theater.jsp");
		   return "../main/main.jsp";
	   }
	   /********************************검색 기능 *******************************************/
	  /* @RequestMapping("event/event_category_all_finder.do")
	   public String event_category_all_finder(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String findstr = request.getParameter("eventFindText");
		   System.out.println(findstr);
		   EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventAllData_Finder(findstr);	//진행중인 이벤트, all부분
		   
		  
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_category_all.jsp");
		   return "../main/main.jsp";
	   }*/
	   
	   
	   
	   /************************************지난이벤트Page***************************************/
	   
	   /* 단순 출력 시  
	   @RequestMapping("event/event_last_event.do")
	   public String event_last_event(HttpServletRequest request,HttpServletResponse response)
	   { 
		   EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventLastEventData();	//지난이벤트
		
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_last_event.jsp");
		   return "../main/main.jsp";
	   }
	   */
	   
	   
	   /* 데이터가 많을 경우 페이징 처리 시. 스타일 1
	   @RequestMapping("event/event_last_event.do")
	   public String event_last_event_paging(HttpServletRequest request,HttpServletResponse response)
	   {
		   String page=request.getParameter("page");
		   if(page==null || page=="0") { //첫페이지일경우
				  page="1";
		   }
		// 해당페이지의 데이터를 가지고 온다(DAO)
			  EventDAO dao= new EventDAO();
			  int curpage=Integer.parseInt(page);
			  List<EventVO> list=dao.eventLastEventData_Paging(curpage);
			  int totalpage=dao.eventLastEventTotal_Paging();
			  // list.jsp로 전송 
			  request.setAttribute("curpage", curpage);// 현재 페이지
			  request.setAttribute("totalpage", totalpage);
			  request.setAttribute("list", list);
			  // 화면 출력 => include 대상 
			  request.setAttribute("main_jsp", "../event/event_last_event.jsp");
		   
		   return "../main/main.jsp";
	   }*/
	   
	   
	   /*데이터가 많은 경우 페이징 처리 시. 스타일 2*/
	   @RequestMapping("event/event_last_event.do")
	   public String event_last_event_paging2(HttpServletRequest request,HttpServletResponse response)
	   {
		   String page=request.getParameter("page");
			  if(page==null)
				  page="1";
			  // 현재페이지 
			  int curpage=Integer.parseInt(page);
			  // 페이지에 해당되는 데이터 읽기 
			  EventDAO dao=new EventDAO();
			  List<EventVO> list=dao.eventLastEventData_Paging(curpage);
			  // 총페이지 읽기 
			  int totalpage=dao.eventLastEventTotal_Paging();
			  
			  final int BLOCK=5;
			  /*
			   *     startPage   endPage
			   *      |           |
			   *   < [1][2][3][4][5] >
			   *                        startPage   endPage
			   *                     |  |           |
			   *                     < [6][7][8][9][10] >
			   */
			  int startPage=(((curpage-1)/BLOCK)*BLOCK)+1;
			  //                 1-1/BLOCK => 0*5 => 0  ==>  1
			  //                 6-1/5 => 1*5 => 5 +1 6
			  //                 10/5 ==> 2*5 => 10+1 ===> 11
			  int endPage=(((curpage-1)/BLOCK)*BLOCK)+BLOCK; // ==> 5 ==> 
			  //             6-1/5 => 1*BLOCK => 5+5 => 10
			  //   totalpage=13 ==> endPage => 15
			  
			  if(endPage>totalpage)
				  endPage=totalpage;
			  
			  // list.jsp전송
			  request.setAttribute("curpage", curpage);
			  request.setAttribute("totalpage", totalpage);
			  request.setAttribute("list", list);
			  request.setAttribute("BLOCK", BLOCK);
			  request.setAttribute("startPage", startPage);
			  request.setAttribute("endPage", endPage);
			  
			  request.setAttribute("main_jsp", "../event/event_last_event.jsp");
			  return "../main/main.jsp";
		  
		   
	   }
	   /************************************상세페이지**********************************************/
	  
	   @RequestMapping("event/event_detail.do")
	   public String event_detail(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String mno = request.getParameter("mno");
		   EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
		   EventVO vo = dao.eventDetailData(Integer.parseInt(mno));	//지난이벤트
		
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../event/event_detail.jsp");
		   return "../main/main.jsp";
	   }	   
	  
	   
	   /***************************당첨자발표Page****************************************************/
	   @RequestMapping("event/event_board_list.do")
	   public String event_board_list(HttpServletRequest request,HttpServletResponse response)
	   {
		   EventDAO dao = new EventDAO();
		   List<EventWinnerVO> list = dao.boardListData();
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_board_list.jsp");
		   return "../main/main.jsp";
	   }
	   
	   @RequestMapping("event/event_board_list_detail.do")
	   public String event_board_list_detail(HttpServletRequest request,HttpServletResponse response)
	   {
		   String mno = request.getParameter("mno");
		   EventDAO dao = new EventDAO();
		   EventWinnerVO vo = dao.eventBoardDetailData(Integer.parseInt(mno));
		   
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../event/event_board_list_detail.jsp");
		   return "../main/main.jsp";
	   }
}


/************************************************************************************************************/
