<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화상세페이지</title>
<link rel="stylesheet" href="../event/css/event.css">
<link rel="stylesheet" href="../event/css/total.css">
</head>
<body>
	<div class="detailPageTitle">진행중인 이벤트</div>
	<div class="detailInfoBox">
		<div class="detailInfoTitle">${vo.title}</div>
		<div class="detailInfoTerm">${vo.term}</div>
	</div>
	<div class="detailInfoContent">
		<img src="${vo.content}">
	</div>
	<a href=#header class="topBtn"></a>
	

</body>
</html>