package com.sist.model;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import com.sist.dao.EventMainDAO;
import com.sist.vo.EventVO;
import com.sist.vo.EventWinnerVO;

@Controller
public class EventMainModel {
		/*************************기본 페이지***************************************/	
	   @RequestMapping("event/event_main.do")
	   public String event_main(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String selectCategoryData = "전체";//event_category_all.do랑 연결되면  전체  카테고리를 받아올 것이다.
		   String thisPage = "event_category_all.do";
		   String searchFind = null;
		   
		   EventMainDAO dao = new EventMainDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventMainDataList(selectCategoryData, searchFind);	//진행중인 이벤트, all(전체)를 기본으로 출력.
		   
		   request.setAttribute("thisPage",thisPage);
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_main.jsp");
		   return "../main/main.jsp";
	   }
		
	
		/*************************진행중인이벤트Page-전체Page***************************************/	
	   @RequestMapping("event/event_category_all.do")
	   public String event_categoryIsAll(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String selectCategoryData = "전체";//event_category_all.do랑 연결되면  전체  카테고리를 받아올 것이다.
		   String thisPage = "event_category_all.do";
		   String searchFind = null;
		   searchFind = request.getParameter("eventFindText");
		   EventMainDAO dao = new EventMainDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventMainDataList(selectCategoryData, searchFind);	//진행중인 이벤트, all(전체)를 기본으로 출력.
		   
		   System.out.println("선택한 카테고리 : "+selectCategoryData);
		   request.setAttribute("thisPage",thisPage);
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_main.jsp");
		   return "../main/main.jsp";
	   }
	   @RequestMapping("event/event_category_movie.do")
	   public String event_categoryIsMovie(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String selectCategoryData = "영화";//event_category_movie.do가 요청되면  영화  카테고리를 받아올 것이다. 
		   String thisPage = "event_category_movie.do";
		   String searchFind = null; //아무 검색도 하지 않았을 때 searchFind의 기본값은 null로 초기화.
		   searchFind = request.getParameter("eventFindText");
		   
		   EventMainDAO dao = new EventMainDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventMainDataList(selectCategoryData, searchFind);	//진행중인 이벤트, all(전체)를 기본으로 출력.
		   
		   System.out.println("선택한 카테고리 : "+selectCategoryData);
		   request.setAttribute("thisPage",thisPage);
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_main.jsp");
		   return "../main/main.jsp";
	   }
	   @RequestMapping("event/event_category_theater.do")
	   public String event_categoryIsTheater(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String selectCategoryData = "극장";//event_category_movie.do가 요청되면  영화  카테고리를 받아올 것이다.
		   String thisPage = "event_category_theater.do";
		   String searchFind = null;
		   searchFind = request.getParameter("eventFindText");
		   
		   EventMainDAO dao = new EventMainDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventMainDataList(selectCategoryData, searchFind);	//진행중인 이벤트, all(전체)를 기본으로 출력.
		   
		   System.out.println("선택한 카테고리 : "+selectCategoryData);
		   request.setAttribute("thisPage",thisPage);
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_main.jsp");
		   return "../main/main.jsp";
	   }
	   
	   //***************************************************************************************************************
	   @RequestMapping("event/event_last_event.do")
	   public String event_last_event_paging2(HttpServletRequest request,HttpServletResponse response)
	   {
		   String thisPage = "event_last_event.do";
		   String page=request.getParameter("page");
		   String searchFind = null;
		   searchFind = request.getParameter("eventFindText");
		   
			  if(page==null)
				  page="1";
			  // 현재페이지 
			  int curpage=Integer.parseInt(page);
			  // 페이지에 해당되는 데이터 읽기 
			  EventMainDAO dao=new EventMainDAO();
			  List<EventVO> list=dao.eventLastEventData_Paging(curpage, searchFind);
			  // 총페이지 읽기 
			  int totalpage=dao.eventLastEventTotal_Paging(searchFind);
			  
			  final int BLOCK=5;
			  /*
			   *     startPage   endPage
			   *      |           |
			   *   < [1][2][3][4][5] >
			   *                        startPage   endPage
			   *                     |  |           |
			   *                    < [6][7][8][9][10] >
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
			  request.setAttribute("thisPage",thisPage);
			  request.setAttribute("curpage", curpage);
			  request.setAttribute("totalpage", totalpage);
			  request.setAttribute("list", list);
			  request.setAttribute("BLOCK", BLOCK);
			  request.setAttribute("startPage", startPage);
			  request.setAttribute("endPage", endPage);
			  
			  request.setAttribute("main_jsp", "../event/event_last_event.jsp");
			  return "../main/main.jsp";
		  
		   
	   }
	   //************************************상세페이지**********************************************/
	  
	   @RequestMapping("event/event_detail.do")
	   public String event_detail(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String mno = request.getParameter("mno");
		   EventMainDAO dao = new EventMainDAO(); //newInstance형태로 수정해야함.
		   EventVO vo = dao.eventDetailData(Integer.parseInt(mno));	//지난이벤트
		
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../event/event_detail.jsp");
		   return "../main/main.jsp";
	   }	   
	  
	   
	   //***************************당첨자발표Page****************************************************/
	   @RequestMapping("event/event_board_list.do")
	   public String event_board_list(HttpServletRequest request,HttpServletResponse response)
	   {
		   EventMainDAO dao = new EventMainDAO();
		   List<EventWinnerVO> list = dao.boardListData();
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_board_list.jsp");
		   return "../main/main.jsp";
	   }
	   
	   @RequestMapping("event/event_board_list_detail.do")
	   public String event_board_list_detail(HttpServletRequest request,HttpServletResponse response)
	   {
		   String mno = request.getParameter("mno");
		   EventMainDAO dao = new EventMainDAO();
		   EventWinnerVO vo = dao.eventBoardDetailData(Integer.parseInt(mno));
		   
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../event/event_board_list_detail.jsp");
		   return "../main/main.jsp";
	   }
}