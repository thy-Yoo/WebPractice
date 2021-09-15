<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진행중인 이벤트 | 영화</title>
<link rel="stylesheet" href="../event/css/event.css">
<link rel="stylesheet" href="../event/css/total.css">
</head>
<body>
	
	<div class="event_header">
		<div class="event_contentTitle"><a class="event_otherLink" href="event_category_all.do">진행중인 이벤트</a>
		</div>
		<div class="event_otherLinkBox"><a class="event_otherLink" href="event_last_event.do">지난 이벤트</a>
		</div>
		<div class="event_otherLinkBox"><a class="event_otherLink" href="event_board_list.do">당첨자 발표</a>
		</div>
	</div>		
	
	
	<div class="event_subMenu">
		<!-- 대한극장은, 클릭한 메뉴 밑에 컬러밑줄이 생긴다. 만들어야함. -->
		<ul>
			<li class="subMenuTitle"><a id="subMenu_total" href="event_category_all.do">전체</a></li>
			<li class="subMenuTitle"><a id="subMenu_movie" href="event_category_movie.do">영화</a></li>
			<li class="subMenuTitle"><a id="subMenu_theater" href="event_category_theater.do">극장</a></li>
		</ul>
	
	</div>
	<div class="searchPart">
		<form action="event_category_movie.do">
			<span class="search">
				<input type="text" title="검색어를 입력해 주세요." placeholder="검색어를 입력해 주세요." class="input-text" name="eventFindText">
				<input type="submit" class="btn-search-input" value="">
			</span>
		</form>
	</div>	
	<div class="content">
		<c:forEach var="vo" items="${list}">
			<ul class="event_ulli">
				<li class="eventContent">
					<a class="eventDetailLink" href="../event/event_detail.do?mno=${vo.mno}">
							<span class="eventImgBox">
							<img class="eventImg" src="${vo.poster}" alt="img들어갈공간">
							</span>
							<span class="eventText_box">
							<span class="eventTitle">${vo.title}</span>
							<span class="eventDates">${vo.term}</span>
							</span>
					</a>
				</li>
			</ul>
		</c:forEach>
	</div>
	<a href=#header class="topBtn"></a>
</body>
</html>