<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.yjy.dao.*"%>
<%
	DaehanEventDAO dao_dh_movie = new DaehanEventDAO();
	ArrayList<DaehanEventVO> list_dh_movie = dao_dh_movie.daehanEventListData_movie();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="content">
		<%for(DaehanEventVO vo:list_dh_movie) {%>
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