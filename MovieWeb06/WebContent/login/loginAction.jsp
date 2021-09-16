<%@page import="com.sist.vo.LoginVO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.sist.dao.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="user" class="com.sist.vo.LoginVO" scope="page" />
<jsp:setProperty name="user" property="id" />
<jsp:setProperty name="user" property="pwd" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

</body>
</html>
 