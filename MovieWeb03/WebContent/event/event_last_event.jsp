<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지난 이벤트</title>
<link rel="stylesheet" href="../event/css/event.css">
<link rel="stylesheet" href="../event/css/total.css">
<link rel="stylesheet" href="../event/css/event_last_event.css">
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
		<!-- 대한극장은, 클릭한 메뉴 밑에 컬러밑줄이 생긴다. 만들어야함. 
		<ul>
			<li class="subMenuTitle"><a id="subMenu_total" href="event_category_all.do">전체</a></li>
			<li class="subMenuTitle"><a id="subMenu_movie" href="event_category_movie.do">영화</a></li>
			<li class="subMenuTitle"><a id="subMenu_theater" href="event_category_theater.do">극장</a></li>
		</ul>-->
	
	</div>
	<div class="searchPart">
		<span class="search">
				<input type="text" title="검색어를 입력해 주세요." placeholder="검색어를 입력해 주세요." class="input-text">
				<button type="button" class="btn-search-input"></button>
		</span>
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
	
	<div class="pagingDiv">
			<nav class="pagination">
		        <ul>
		          <c:if test="${curpage>BLOCK }">
		           <li class="pagination_key"><a href="../event/event_last_event.do?page=${startPage-1 }">◀</a></li>
		          </c:if> 
		           <c:forEach var="i" begin="${startPage }" end="${endPage }">
		              <c:if test="${curpage==i }">
		                <c:set var="ss" value="class=current"/>
		              </c:if>
		              <c:if test="${curpage!=i }">
		                <c:set var="ss" value=""/>
		              </c:if>
		              <li  class="pagination_number" ${ss }><a href="../event/event_last_event.do?page=${i }">${i }</a></li>
		           </c:forEach>
		           <c:if test="${endPage<totalpage }">
		            <li class="pagination_key"><a href="../event/event_last_event.do?page=${endPage+1 }">▶</a></li>
		            </c:if>
		        </ul>
		      </nav>
		
	</div>
	<!-- 스타일 1. 
	<table class="table">
     <tr>
       <td class="text-center">
         <a href="../event/event_last_event.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-primary">이전</a>
          ${curpage } page / ${totalpage } pages
         <a href="../event/event_last_event.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-primary">다음</a>
       </td>
     </tr>
   </table>	-->
	
	
	
	<a href=#header class="topBtn"></a>

</body>
</html>