<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CINEMA BOX | 개인 웹 개발 연습</title>
</head>
<body>
	<header><jsp:include page="header.jsp"/></header>
    <jsp:include page="${main_jsp}"/>
    <footer><jsp:include page="footer.jsp"/></footer>

</body>
</html>