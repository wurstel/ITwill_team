<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" >
		<div class="container-fluid" >
	  		<a class="navbar-brand" href="./">Store명</a>
	    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    		<span class="navbar-toggler-icon"></span>
	    	</button>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent" >
		    	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        	<li class="nav-item">
		          		<a class="nav-link" href="./storeMain.st">홈(전체상품)</a>
		        	</li>
		        	<li class="nav-item">
		         		<a class="nav-link active" aria-current="page" href="./newProduct.st">신상품</a>
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

	<main class="mt-3" >
		<div class="container" >
			<div class="row mb-2">
				<div class="col-12">
<!-- 					<select class="form-select" id="selectBox" name="list" > -->
<%-- 						<c:set var="select" value="${select }"></c:set> --%>
<!-- <!-- 					js로 페이지 갱신 없이 상품리스트 출력  --> 
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${select eq 'pre'}"> --%>
<!-- 								<option value="pre" selected="selected">최신순</option> -->
<!-- 								<option value="avg" >평점순</option>	 -->
<!-- 								<option value="low" >낮은가격순</option>	 -->
<!-- 								<option value="hei" >높은가격순</option> -->
<%-- 							</c:when> --%>
<%-- 							<c:when test="${select eq 'avg'}"> --%>
<!-- 								<option value="pre">최신순</option> -->
<!-- 								<option value="avg" selected="selected">평점순</option> -->
<!-- 								<option value="low" >낮은가격순</option>	 -->
<!-- 								<option value="hei" >높은가격순</option> -->
<%-- 							</c:when> --%>
<%-- 							<c:when test="${select eq 'low'}"> --%>
<!-- 								<option value="pre">최신순</option> -->
<!-- 								<option value="avg" >평점순</option> -->
<!-- 								<option value="pre" selected="selected">낮은가격순</option> -->
<!-- 								<option value="hei" >높은가격순</option> -->
<%-- 							</c:when> --%>
<%-- 							<c:when test="${select eq 'hei'}"> --%>
<!-- 								<option value="pre">최신순</option> -->
<!-- 								<option value="avg" >평점순</option>	 -->
<!-- 								<option value="low" >낮은가격순</option> -->
<!-- 								<option value="pre" selected="selected">높은가격순</option> -->
<%-- 							</c:when> --%>
<%-- 						</c:choose> --%>
<!-- 					</select> -->
				</div>
			</div>
				<div class="row g-3" >
					<c:if test="${not empty articleList and pageInfo.getListCount() > 0}">
						<c:forEach var="product" items="${articleList }">
							<div class="col-lg-3 col-md-6">
								<div class="card" style="width: 18rem;" onclick="location.href='productDetail.st?pd_code=${product.getPd_code() }'">
									<img src="${product.getPd_img() }" alt="...">
									<div class="card-body">
										<h5 class="card-title">${product.getPd_name() }</h5>
										<p class="card-text"><fmt:formatNumber value="${product.getPd_price() }" pattern="#,###원" /></p>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
	</main>
	
	
	<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />
	
	<section id="pageList">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전" onclick="location.href='newProduct.st?page=${pageNum - 1}&select=${select }'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="newProduct.st?page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${pageNum < maxPage}">
				<input type="button" value="다음" onclick="location.href='newProduct.st?page=${pageNum + 1}&select=${select }'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>