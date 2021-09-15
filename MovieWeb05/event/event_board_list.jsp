<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		<title>당첨자 발표</title>
		<meta name="keywords" content="HTML5 Admin Template" />
		<meta name="description" content="Porto Admin - Responsive HTML5 Template">
		<meta name="author" content="okler.net">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
	
		
		<!-- Theme CSS -->
		<link rel="stylesheet" href="../event/css/theme.css" />
		<link rel="stylesheet" href="../event/css/total.css"/>
		

	</head>
	<body>
		<section class="event_board_list_body">

		

			<div class="event_board_list_inner-wrapper">
				

				<section role="event_board_list_main" class="event_board_list_content-body">
				
				
					<div class="event_header">
						<div class="event_contentTitle"><a class="event_otherLink" href="event_board_list.do">당첨자 발표</a>
						</div>
						<div class="event_otherLinkBox"><a class="event_otherLink" href="event_last_event.do">지난 이벤트</a>
						</div>
						<div class="event_otherLinkBox"><a class="event_otherLink" href="event_category_all.do">진행중인 이벤트</a>
						</div>
					</div>	
					
					
					<!-- start: page -->
					
					
						<div class="event_board_list_row">
							
							<div class="event_board_list_col-md-6">
								<section class="event_board_list_panel">
									
									<div class="event_board_list_panel-body">
										<div class="event_board_list_table-responsive">
											<table class="event_board_list_tableList">
												<thead>
													<tr>
														<th class="event_board_list_no">no</th>
														<th class="event_board_list_category">구분</th>
														<th class="event_board_list_title">제목</th>
														<th class="event_board_list_release">발표일</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="vo" items="${list}">
													<tr>
														<td class="event_board_list_content" id="event_board_list_table_content_no">
															${vo.mno}</td>
														<td class="event_board_list_content" id="event_board_list_table_content_category">
															${vo.category}</td>
														<td class="event_board_list_content" id="event_board_list_table_content_title">
															<a href="event_board_list_detail.do?mno=${vo.mno}">${vo.title}</a>
														</td>
														<td class="event_board_list_content" id="event_board_list_table_content_release">
															${vo.release}</td>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</section>
							</div>
						</div>
						
						
								
					<!-- end: page -->
				</section>
			</div>

			
		</section>


	</body>
</html>