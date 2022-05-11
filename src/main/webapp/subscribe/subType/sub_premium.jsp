<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../../inc/header.jsp"/>  <!-- 헤더 들어가는 곳 -->
	
	<h1>sub_premium.jsp</h1>
	
	<div id="premium_img"><img src=""></div>
	<p>프리미엄 패키지</p>
	<p>상품 구성</p>
	<p>상품 설명</p>
	<p>상품 가격</p>
	
	<p>이런 분들께 추천드려요!</p>
	
	<input type="button" value="구독하기" onclick="location.href='sub_volume.sub'">  <!-- 클릭 시 수량/개수 선택하는 페이지로 이동  -->
	
	<p>결제일과 배송일</p>
	
	<p>프리미엄 패키지와 함께하면 좋은 상품들</p>
	<input type="button" value="스토어 이동" onclick="location.href=''">
	
	<jsp:include page="../../inc/footer.jsp"/>  <!-- 푸터 들어가는 곳 -->
</body>
</html>