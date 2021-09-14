package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.ViploungeDAO;
import com.sist.vo.ViploungeVO;

@Controller
public class VipLoungeModel {
	
	@RequestMapping("viplounge/viplounge_main.do")
	   public String event_board_list(HttpServletRequest request,HttpServletResponse response){
		
		HttpSession session = request.getSession();//서버에 생성된 세션이 있다면 받아오고 없다면 새 세션을 생성 후 받아온다. 
		
		//1. login되어있는 상태의 id값을 받아온다.
		String id=(String) session.getAttribute("id");
		//System.out.println("세션에 저장된 id값은:"+id);//개인확인용 메세지. 삭제하지말것.
	
		//2. 세션에서 가져온 id==viplounge의 userid 같은 행에 대해 vo데이터를 가져온다.
		ViploungeDAO dao = new ViploungeDAO(); //newInstance형태로 수정해야함.
		dao.memberDataUpdate();
		
		ViploungeVO vo = dao.viploungeUserData(id);	//id==userid인경우 데이터를 뽑아옴.
		
		request.setAttribute("vo", vo);
		
		
		request.setAttribute("main_jsp", "../viplounge/viplounge_main.jsp");
		return "../main/main.jsp";
	   }

}
