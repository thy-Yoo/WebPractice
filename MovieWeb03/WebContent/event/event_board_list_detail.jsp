<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../event/css/theme.css" />
</head>
<body>

<div class="board_detailPageTitle">당첨자 발표</div>
	<div>
		<div class="board_detailTitle"></div>
		<div class="board_detailTerm"></div>
	</div>
	<div class="board_detailContent">
	
		${vo.content}
	</div>
	<a href=#header class="topBtn"></a>


	<div class="beforeBtn"><a href="event_board_list.do">목록</a></div>
</body>
</html>