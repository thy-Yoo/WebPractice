<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header.jsp</title>
<link rel="stylesheet" href="../main/css/main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

</head>
<body>

	<div class="topmenu">	
		<a class="myCinema">MY CINEMA</a>
		<a class="membership">멤버십</a>
		<a class="customerCenter">고객센터</a>
		<a class="signup">회원가입</a>
		<a class="login">로그인</a>
			
	</div>
	
	<div class="mainmenu">
		<h1><a href="../main/main.do" class="titleImg" ></a></h1>
		<div class="nav">
			<ul>
				<li><a href="#" class="menu1_movie">영화</a></li>
				<li><a href="#" class="menu2_booking">예매</a></li>
				<li><a href="../event/event_category_all.do" class="menu3_event">이벤트</a></li>
				<li><a href="../viplounge/viplounge_main.do" class="menu4_benefits">혜택</a></li>
			</ul>
		</div>
	</div>
	<!-- 드롭메뉴 만들것. -->
	<!-- 서브메뉴 -->
	<div class="mainmenu_sub">
		<ul class="mainmenu_sub_part1">
			<li class="submenu_moviechart"><a>무비 차트</a></li>
			<li class="submenu_moviefinder"><a>무비파인더</a></li>
		</ul>
		
	</div>
			

</body>
</html>