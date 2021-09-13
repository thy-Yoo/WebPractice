<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../viplounge/css/viplounge.css">
<link rel="stylesheet" href="../viplounge/css/viplounge_table.css">
</head>
<body class="viplounge_body">
	<div class="viplounge_all">
		<div class="viplounge_info">
			<div class="viplounge_grade">svip</div>
			<div class="viplounge_textbox">
				<!-- id,grade,0 자리에 테이블 데이터가 들어갈 것임. -->
				<div class="viplounge_userdata">
					<div class="viplounge_iddata">thy-Yoo</div>
					<div class="viplounge_basictext">님은</div>
					<div class="viplounge_gradedata">SVIP</div>
					<div class="viplounge_basictext">입니다.</div>
				</div>	
				<div class="viplounge_pointdata">
					<div class="viplounge_numberdata">100</div>
					<div class="viplounge_basictext">P</div>
				</div>
			</div>
			<div class="viplounge_link">
				<div class="viplounge_linkCustomerCenter">
					<a href="#">고객센터를 통해 궁금증을 해결하세요.</a>
				</div>
				<div class="viplounge_mycoupons">
					<div class="viplounge_mycoupons_basictext">사용 가능한 나의 쿠폰 </div>
					<div class="viplounge_mycoupons_numberdata">15</div>
					<div class="viplounge_mycoupons_basictext">장</div>
				</div>
				<div class="viplounge_linkMypage">
					<a href="#">My홍대극장</a>
				</div>
			</div>
		</div>
		
		<div class="viplounge_table">
			<!-- 테이블 태그는 나중에 다시 DB랑 비슷하게 맞춰보자 -->
    <div class="viplounge_tablesSection">
        <div class="viplounge_container">
            <div class="viplounge_table">
                <p>VIP BENEFITS</p>
				<div class="dmoain-pricing">
					<div class="table-responsive-sm">
						<table>
							<thead>
								<tr class="domain-head">
									<th scope="col">
										고객등급
									</th>
									<th scope="col">
										SVIP
									</th>
									<th scope="col">
										VVIP
									</th>
									<th scope="col">
										RVIP
									</th>
									<th scope="col">
										VIP
									</th>
									<th scope="col">
										상세설명
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">온라인 쿠폰북</td>
									<td data-label="SVIP">
										영화 무료쿠폰 14매<br>
										매점 무료쿠폰 7매<br>
										매점 할인쿠폰 5매<br>
										포토플레이쿠폰 96매
									</td>
									<td data-label="VVIP">
										영화 무료쿠폰 12매<br>
										매점 무료쿠폰 5매<br>
										매점 할인쿠폰 4매<br>
										포토플레이쿠폰 12매
									</td>
									<td data-label="RVIP">
										영화 무료쿠폰 9매<br>
										매점 무료쿠폰 5매<br>
										매점 할인쿠폰 4매<br>
										포토플레이쿠폰  2매
									</td>
									<td data-label="VIP">
										영화 무료쿠폰 6매<br>
										매점 무료쿠폰 2매<br>
										매점 할인쿠폰 3매
									</td>
									<td data-label="상세설명">
										VIP고객님 취향에 따라<br>
										쿠폰 선택이 가능한<br>
										CGV만의 맞춤형 온라인 쿠폰북
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">포인트 반값 할인</td>
									<td class="viplounge_tdContent" data-label="SVIP">등급 기간 내 30매</td>
									<td class="viplounge_tdContent" data-label="VVIP">등급 기간 내 20매</td>
									<td class="viplounge_tdContent" data-label="RVIP">등급 기간 내 10매</td>
									<td class="viplounge_tdContent" data-label="VIP">등급 기간 내 5매</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										전국 CGV에서<br>
										CJ ONE 포인트로<br>
										2D/3D 일반 영화 관람시<br>
										50% 할인
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">VIP 더블적립</td>
									<td class="viplounge_tdContent" data-label="SVIP">3% / 7%</td>	
									<td class="viplounge_tdContent" data-label="VVIP">3% / 7%</td>
									<td class="viplounge_tdContent" data-label="RVIP">3% / 7%</td>
									<td class="viplounge_tdContent" data-label="VIP">3% / 7%</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										상영일 이전 예매 7%<br>
										상영일 당일 예매 3%<br>
										매주 수요일 영화 관람 시<br>
										추가 포인트 적립									
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">VIP 리필적립</td>
									<td class="viplounge_tdContent" data-label="SVIP">5%</td>
									<td class="viplounge_tdContent" data-label="VVIP">3%</td>
									<td class="viplounge_tdContent" data-label="RVIP">1%</td>
									<td class="viplounge_tdContent" data-label="VIP">-</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										선정기준(12,000P)달성 시<br>
										다음 달초 부터<br>
										영화 관람 추가 포인트 적립 
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">스페셜데이</td>
									<td class="viplounge_tdContent" data-label="SVIP">에이드 2잔 무료</td>
									<td class="viplounge_tdContent" data-label="VVIP">에이드 2잔 무료</td>
									<td class="viplounge_tdContent" data-label="RVIP">에이드 2잔 무료</td>
									<td class="viplounge_tdContent" data-label="VIP">-</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										VIP 고객님이<br>
										직접 설정한 기념일에<br>
										영화 관람 시 혜택 증정 
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="Acount">생일 혜택</td>
									<td class="viplounge_tdContent" data-label="SVIP">생일 콤보 무료</td>
									<td class="viplounge_tdContent" data-label="VVIP">생일 콤보 무료</td>
									<td class="viplounge_tdContent" data-label="RVIP">생일 콤보 무료</td>
									<td class="viplounge_tdContent" data-label="VIP">생일 콤보 무료</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										VIP 고객님의<br>
										법적 생년월일 기준<br>
										혜택 증정
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">스페셜 기프트</td>
									<td class="viplounge_tdContent" data-label="SVIP">
										SVIP 스페셜 기프트 +<br>
										SVIP원데이프리패스카드
									</td>
									<td class="viplounge_tdContent" data-label="VVIP">VVIP원데이프리패스카드</td>
									<td class="viplounge_tdContent" data-label="RVIP">-</td>
									<td class="viplounge_tdContent" data-label="VIP">-</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										VVIP, SVIP 고객님을 위한<br>
										특별한 선물 
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">SVIP 특별관 혜택</td>
									<td class="viplounge_tdContent" data-label="SVIP">특별관 1만원 발권</td>
									<td class="viplounge_tdContent" data-label="VVIP">-</td>
									<td class="viplounge_tdContent" data-label="RVIP">-</td>
									<td class="viplounge_tdContent" data-label="VIP">-</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										SVIP 고객님께 드리는<br>
										특별관 만원 관람 혜택
									</td>
								</tr>
								<tr>
									<td class="viplounge_td1st" data-label="고객등급">VIP 전용이벤트</td>
									<td class="viplounge_tdContent" data-label="SVIP">참여가능</td>
									<td class="viplounge_tdContent" data-label="VVIP">참여가능</td>
									<td class="viplounge_tdContent" data-label="RVIP">참여가능</td>
									<td class="viplounge_tdContent" data-label="VIP">참여가능</td>
									<td class="viplounge_tdContent" data-label="상세설명">
										매 월 VIP 시사회 등<br>
										VIP고객님을 위한<br>
										다채로운 이벤트 진행
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
            </div><!-- end title -->
        </div><!-- end container -->
    </div><!-- end section -->
		</div>
	</div>
</body>
</html>