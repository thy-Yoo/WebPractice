<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*,com.yjy.dao.*"%>
<% 
	//크롤링파트 
 	//dao_dh.daehanEvent_theater_DataCrawling(); //대한극장-이벤트-극장부분 크롤링
	//dao_dh.daehanEvent_theater_DataCrawling2();//대한극장-이벤트-영화부분 크롤링
	DaehanEventDAO dao_dh = new DaehanEventDAO();
 	ArrayList<DaehanEventVO> list_dh = dao_dh.daehanEventListData();
 	
 	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/cgvevent.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
<div class="content">
		<%for(DaehanEventVO vo:list_dh) {%>
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
		<%} %>
	</div>
</body>
</html>