<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*,com.yjy.dao.*"%>
<%
 /*div.subMenu관련 부분. 메뉴를 클릭하면 전체이벤트/영화이벤트/극장이벤트로 나뉜다.*/
	String mode=request.getParameter("mode");
	if(mode==null)
		mode = "1";
	int index = Integer.parseInt(mode);
	String eventlist = "";
	switch(index){
	case 1:
		eventlist="event_category_all.jsp";
		break;
	case 2:
		eventlist="event_category_movie.jsp";
		break;
	case 3:
		eventlist="event_category_theater.jsp";
		break;
	}
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 | CinemaBox</title>
<link rel="stylesheet" href="../css/event.css">


<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>


</head>
<body>
	<header>
		<%@include file ="header.jsp" %>
		<div class="where">
			<div> 홈 > 이벤트 > 진행중인 이벤트 </div>
		</div>
	</header>
	
	<div class="contentTitle">
		진행중인 이벤트
	</div>
	
	
	
	<div class="subMenu">
		<ul>
			<li class="subMenuTitle"><a id="subMenu_total" href="eventmain.jsp?mode=1" >전체</a></li>
			<li class="subMenuTitle"><a id="subMenu_movie" href="eventmain.jsp?mode=2"  >영화</a></li>
			<li class="subMenuTitle"><a id="subMenu_theater" href="eventmain.jsp?mode=3" >극장</a></li>
		</ul>
	
	</div>
	<div class="searchPart">
		<span class="search">
				<input type="text" title="검색어를 입력해 주세요." placeholder="검색어를 입력해 주세요." class="input-text">
				<button type="button" class="btn-search-input"></button>
		</span>
	</div>
	
	<!-- 해결해야 할 부분.
	현재상태 => 해야할 작업.
	1.	데이터 크롤을 임시로 4페이지씩 해둔 상태이다.
		대한극장-이벤트-극장탭 4페이지, 대한극장-이벤트-영화탭 4페이지
		=>이것을 유동적으로 최대페이지로 늘릴것.
	2.	데이터가 많아지면 끊임없이 스크롤을 내릴 수는 없는 일이다.
		=> 페이지 나눠서 출력할 것.
	3.	극장탭에서 크롤링, 이벤트탭에서 크롤링 하다보니 전체탭에 갔을때에는
		극장이벤트n개가 나온 후 영화이벤트n개가 나오는 형태이다.
		=> 전체 페이지에 대해선, 이벤트기간 dates 값을 기준으로 정렬하여 출력하도록 하자.
	4.	css적인 부분에선
		현재 전체/영화/극장 메뉴인데 뭔가좀 허전하다. 스타일에 효과를 주든, 너비를 바꾸든 해서 바꾸어주자.
	-->
	

	<div class="eventlist">
		<jsp:include page="<%=eventlist%>"></jsp:include>
	</div>
	
	<footer>
		<%@include file ="footer.jsp" %>	
	</footer>
	
</body>
</html>