package com.sist.model;

import java.util.*;//List

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.EventVO;

@Controller
public class EventModel {
	
	   /*모든 이벤트 리스트를 받아오는 용도*/ 
	
	   //header.jsp의 진행중인 이벤트 <a>태그에 해당 주소가 걸려있음. event_category_all.do
	   //event_category_all.do를 하라 => main_jsp에 ../event/evnet_category_all.jsp를 보냄.
	   @RequestMapping("event/event_category_all.do")
	   public String event_category_all(HttpServletRequest request,HttpServletResponse response)
	   { 
		   EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
		   List<EventVO> list = dao.eventAllData();	//페이지를 나누려면 이 함수 + Paging 함수를 써야할듯.
		
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_category_all.jsp");
		   return "../main/main.jsp";
	   }
	
	  
	   
	   /****************************게시판 관련****************************************************/
	   @RequestMapping("event/event_board_list.do")
	   public String event_board_list(HttpServletRequest request,HttpServletResponse response)
	   {
		   request.setAttribute("main_jsp", "../event/event_board_list.jsp");
		   return "../main/main.jsp";
	   }
}


/************************************************************************************************************/
