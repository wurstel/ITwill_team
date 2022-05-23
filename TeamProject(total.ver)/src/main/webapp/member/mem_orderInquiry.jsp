<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<style>
  /* 사이드바 래퍼 스타일 */
  
  #page-wrapper {
    padding-left: 250px;
  }
  
  #sidebar-wrapper {
    position: fixed;
    width: 250px;
    height: 100%;
    margin-left: -250px;
    background: #000;
    overflow-x: hidden;
    overflow-y: auto;
  }
  
  #page-content-wrapper {
    width: 100%;
    padding: 20px;
  }
  /* 사이드바 스타일 */
  
  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }
  
  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }
  
  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999;
  }
  
  .sidebar-nav li a:hover {
    color: #fff;
    background: rgba(255, 255, 255, 0.2);
  }
  
  .sidebar-nav > .sidebar-brand {
    font-size: 1.3em;
    line-height: 3em;
  }

</style>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div id="page-wrapper">
  <!-- 사이드바 -->
  <div id="sidebar-wrapper">
    <ul class="sidebar-nav">
      <li class="sidebar-brand">
        <a href="mypage.me">마이페이지</a>
      </li>
      <li><a href="memInfoEdit.me">회원정보변경</a></li>
      <li><a href="inquiry.me">주문조회</a></li>
      <li><a href="basket.me">장바구니</a></li>
      <li class="sidebar-brand">
        <a href="#">멤버쉽</a>
      </li>
      <li><a href="#">멤버쉽조회</a></li>
      <li><a href="#">멤버쉽수정</a></li>
    </ul>
  </div>
  <!-- /사이드바 -->

  <!-- 본문 -->
<div id="page-content-wrapper">
    <div class="container">
      <h1 class="display-5">주문조회</h1>
    </div>
    <div class="container">
		<table class="table">
			<c:choose>
			<c:when test="${empty comment }">
				<thead>
					<tr>
						<th scope="col">상품</th>
						<th scope="col">가격</th>
						<th scope="col">수량</th>
						<th scope="col">주문금액</th>
						<th scope="col">비고</th>
					</tr>
				</thead>
				<tbody>
				<!--  주문목록  -->
					<c:forEach items="${list }" var="oc">
				    <tr>
				      <td>${oc.pd_name }</td>
				      <td>${oc.pd_price }</td>
				      <td>${oc.od_qty }</td>
				      <td>${oc.totalprice }</td>
				      <td>${oc.order_status }</td>
				    </tr>
					</c:forEach>
				<!-- /주문목록  -->    
				</tbody>
			</c:when>
			<c:otherwise>
				<thead>
					<tr>
						<th scope="col">상품</th>
						<th scope="col">가격</th>
						<th scope="col">수량</th>
						<th scope="col">주문금액</th>
						<th scope="col">비고</th>
					</tr>
				</thead>
				<tbody>
					<tr><td colspan="5" class="text-center">${comment }</td></tr>
				</tbody>
			</c:otherwise>
			</c:choose>
		</table>   	
    </div>
</div>
  <!-- /본문 -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>