<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script src="./js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="./js/store.js"></script>
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
</head>
<body>
	<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- 상단 네비게이션 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" >
		<div class="container-fluid" >
	  		<a class="navbar-brand" href="./">Store명</a>
	    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    		<span class="navbar-toggler-icon"></span>
	    	</button>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent" >
		    	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        	<li class="nav-item">
		          		<a class="nav-link active" aria-current="page" href="./storeMain.st">홈(전체상품)</a>
		        	</li>
		        	<li class="nav-item">
		         		<a class="nav-link" href="./newProduct.st">신상품</a>
		        	</li>
		        	<li class="nav-item">
		          		<a class="nav-link" href="./bestProduct.st">인기상품</a>
		       		</li>
		      	</ul>
		    	<form class="d-flex">
		   			<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
		        	<button class="btn btn-outline-success" type="submit">Search</button>
		      	</form>
		    </div>
	 	 </div>
	</nav>

	<main class="mt-3">
			<!-- 팝업창  -->
		<form action="productCart.st" name="fr">
<!-- 		<form action="productCart.st" name="fr"> -->
			<input type="hidden" name ="pd_code"value="${articleDetail.getPd_code()}">
			<input type="hidden" name ="mem_id"value="${sessionScope.sId }">
			<input type="hidden" name ="pd_price"value="${articleDetail.getPd_price()}">
			<input type="hidden" name ="choiceCheck" id="choiceCheck">
			<div class="container" >
				<div class="row">
					<div class="col-md-4">
						<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
							<div class="carousel-indicators">
						    	<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
						    	<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
						    	<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
						  	</div>
						  	<div class="carousel-inner">
						    	<div class="carousel-item active">
						     		<img src="./img/pic1.png" class="d-block w-100" alt="...">
						    	</div>
						   		<div class="carousel-item">
						      		<img src="./img/pic2.png" class="d-block w-100" alt="...">
						    	</div>
						    	<div class="carousel-item">
						      		<img src="./img/pic3.png" class="d-block w-100" alt="...">
						   		 </div>
						  	</div>
						  	<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						    	<span class="visually-hidden">Previous</span>
						  	</button>
						  	<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						  		<span class="carousel-control-next-icon" aria-hidden="true"></span>
						    	<span class="visually-hidden">Next</span>
						  	</button>
						</div>
					</div>
					<div class="col-md-8">
						<div class="card shadow-sm" >
							<div class="card-body">
								<h5 class="card-title" >${articleDetail.getPd_name() }</h5>
								<h5 class="card-title pt-3 pb-3 border-top"><fmt:formatNumber value="${articleDetail.getPd_price() }" pattern="#,###원" /></h5>
								<p class="card-text border-top pt-3">
									<jsp:useBean id="now" class="java.util.Date" />
									<fmt:parseDate var="rdate" value="${articleDetail.getPd_rdate() }" pattern="yyyy-MM-dd" />
									<fmt:formatDate value="${now}" pattern="yyyyMMdd" var="now" />
									<fmt:formatDate value="${rdate}" pattern="yyyyMMdd" var="rdate" />
									<c:if test="${now le rdate + 14}">
										<span class="badge bg-dark">new</span>
									</c:if>
									<span class="badge bg-dark">best</span>
								</p>
								<p class="card-text pb-3">
									배송비 2,500원 | 도서산간(제주도) 배송비 추가 5,000원 | 택배배송 | 5일 이내 출고 (주말,공휴일 제외)
								</p>
								<p class="card-text border-top pb-3">
									<div class="row">
										<div class="col-auto">
											<label class="col-form-label">구매수량</label>
										</div>
										<div class="col-auto">
											<c:choose>
												<c:when test="${articleDetail.getPd_stock() == 0 }">
													<div class="input-group">
														<input type="text" class="form-control" style="width:90px;" value="${articleDetail.getPd_state() }" readonly="readonly">
													</div>
												</c:when>
												<c:otherwise>
													<div class="input-group" >
														<input type="hidden" id="stock" value="${articleDetail.getPd_stock() }" >
														<span type="button" class="input-group-text" onclick="del()">-</span>
	                                       				<input type="text" name="bk_qty" id="bk_qty" value="1" class="form-control" style="width:45px; text-align:center;" onchange="change();">
	                                     				<span type="button" class="input-group-text" onclick="add()">+</span>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</p>	
								<div class="row pt-3 pb-3 border-top">
									<div class="col-6">
										<h3>총 상품 금액</h3>
									</div>
									<div class="col-6" style="text-align: right;">
										<c:choose>
										<c:when test="${articleDetail.getPd_stock() == 0 }">
	                                		<input type="text" name="sum" id="sum" size="7" value="0" readonly><span id="sum">원</span>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="sell_price" value="${articleDetail.getPd_price() }" size="11" readonly>
	                                		<input type="text" name="sum" id="sum" size="7" value="${articleDetail.getPd_price() }" readonly><span id="sum">원</span>
										</c:otherwise>
									</c:choose>
										
									</div>
								</div>
								<div class="d-flex justify-content-between align-items-center">
									<c:choose>
										<c:when test="${articleDetail.getPd_stock() == 0 }">
											<div class="col-6 d-grid p-1">
												<button type="button" class="btn btn-lg btn-dark" onclick="stock()">장바구니담기</button>
											</div>
											<div class="col-6 d-grid p-1">
												<button type="button" class="btn btn-lg btn-danger" onclick="stock()">주문하기</button>
											</div>
										</c:when>
										<c:otherwise>
											<div class="col-6 d-grid p-1">
												<button type="button" value="submit" onclick="choice()" class="btn btn-lg btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">장바구니담기</button>
<!-- 												<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Launch demo modal</button> -->
<!-- 												<button type="submit" class="btn btn-lg btn-dark" >장바구니담기</button> -->
											</div>
											<div class="col-6 d-grid p-1">
												<button type="button" class="btn btn-lg btn-danger">주문하기</button>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal -->
			<c:choose>
				<c:when test="${not empty sessionScope.sId}">
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-80size modal-center">
					    	<div class="modal-content">
					    		<div class="modal-header">
					          		<h5 class="modal-title" id="exampleModalLabel">장바구니에 상품이 정상적으로 담겼습니다.</h5>
					          		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="cartChoice('close')"></button>
					      		</div>
						      	<div class="modal-body">
					      	  		<img src="${articleDetail.getPd_img() }" id="image">
					      	  		<div id="na">상품코드&nbsp;:</div>
					          		<div id="cr">${articleDetail.getPd_code() }</div>
					      	  		<div id="na">상품명&nbsp;&nbsp;&nbsp;&nbsp;:</div>
					          		<div id="cr">${articleDetail.getPd_name() }</div>
					      		</div>
					      		<div class="modal-footer">
					       	  		<input type="button" value="장바구니 이동" class="btn btn-outline-dark" onclick="cartChoice('cart')">
							  		<input type="button" value="쇼핑 계속하기" class="btn btn-outline-dark" onclick="cartChoice('list')">
					      		</div>
					   		</div>
					  	</div>
					</div>
				</c:when>
			</c:choose>
		</form>
	</main>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>