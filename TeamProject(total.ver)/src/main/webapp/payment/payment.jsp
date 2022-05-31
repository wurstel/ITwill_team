<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style>
html, body {
	height: 100%;
}
</style>
<script src="js/paymentAPI.js"></script>
</head>
<body>
<jsp:include page="../inc/header.jsp"/>  <!-- 헤더 들어가는 곳 -->
<div class="container h-100">
	<div class="row d-flex justify-content-center align-items-center h-100">
	        <div class="col-4 text-center">
				<div class="card">
					<img src="./assets/credit-card.png" alt="스탠다드패키지" class="card-img-top" onclick="kgPay()"/>
					<div class="card-body">
						<button class="btn btn-outline-success btn-block" onclick="kgPay()">카드결제</button><br>
					</div>
				</div>
	        </div>
	        <div class="col-4 text-center">
				<div class="card">
					<img src="./assets/payment_icon_yellow_large.png" alt="스탠다드패키지" class="card-img-top" onclick="kakaoPay()"/>
					<div class="card-body">
						<button class="btn btn-outline-warning btn-block" onclick="kakaoPay()">카카오페이</button><br>
					</div>
				</div>
	        </div>
	        <div class="col-4 text-center">
				<div class="card">
					<img src="./assets/logo-toss-blue.png" alt="스탠다드패키지" class="card-img-top" onclick="tossPay()"/>
					<div class="card-body">
						<button class="btn btn-outline-primary btn-block" onclick="tossPay()">토스페이</button>
					</div>
				</div>
	        </div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>