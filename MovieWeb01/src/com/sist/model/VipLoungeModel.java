package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class VipLoungeModel {
	
	@RequestMapping("viplounge/viplounge_main.do")
	   public String event_board_list(HttpServletRequest request,HttpServletResponse response)
	   {
		   request.setAttribute("main_jsp", "../viplounge/viplounge_main.jsp");
		   return "../main/main.jsp";
	   }

}
