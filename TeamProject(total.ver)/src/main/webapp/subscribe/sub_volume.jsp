<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../inc/header.jsp"/>  <!-- 헤더 들어가는 곳 -->
	
	<h1>sub_volume.jsp</h1>
	
	<div id="subscribe_img"><img src=""></div>  <!-- 1. 고객이 선택한 스탠다드 or 프리미엄 이미지를 그대로 넘겨줌 2. 새로운 이미지를 가져와서 채워놓음 -->
	<p>subscribeBox</p>
	<p>제품 구성 (랜덤박스 일 경우 종류, 힌트 표시)</p>
	<p>수량 선택하기</p>
	<p>수량 선택하면 구독 가격 표시 --> 구독 수 많으면 할인 적용? or 첫 구독 할인 or 구독 결제일마다 포인트 적립?</p>
	
	
	
	<input type="button" value="다음 단계" onclick="location.href='sub_destination.sub'"> <!-- 수량 선택 후 배송지를 입력하는 페이지로 이동  -->
	<jsp:include page="../inc/footer.jsp"/>  <!-- 푸터 들어가는 곳 -->
</body>
</html>