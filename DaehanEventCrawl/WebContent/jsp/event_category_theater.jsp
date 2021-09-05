<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.yjy.dao.*"%>
<%
	DaehanEventDAO dao_dh_theater = new DaehanEventDAO();
	ArrayList<DaehanEventVO> list_dh_theater = dao_dh_theater.daehanEventListData_theater();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="content">
		<%for(DaehanEventVO vo:list_dh_theater) {%>
		<ul>
			<li class="eventContent">
		    	<a href="#">
		    	<div class="eventImg_box">
			      <img class="eventImg" src="<%=vo.getPoster()%>"></div>
			    <div class="eventText_box">
				  <span class="eventTitle"><%=vo.getTitle()%></span>
				  <span class="eventDates"><%=vo.getDates()%></span>
				  </div>
				</a>
		   	</li>
		 </ul>
		<%}%>
</div>

</body>
</html>