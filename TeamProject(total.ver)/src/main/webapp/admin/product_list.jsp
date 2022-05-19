<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles_ad_pr.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">관리자페이지</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">상품관리</div>
                            <a class="nav-link" href="./ProductRegisterForm.ad">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                상품등록
                            </a>
                            <a class="nav-link" href="./ProductList.ad">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                상품목록
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer"></div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">상품관리</h1>
                    </div>
                    <c:set var="pageNum" value="${pageInfo.getPageNum() }" />
					<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
					<c:set var="startPage" value="${pageInfo.getStartPage() }" />
					<c:set var="endPage" value="${pageInfo.getEndPage() }" />
					<c:set var="listCount" value="${pageInfo.getListCount() }" />

	<!-- 게시판 리스트 -->
					<section id="listForm">
					<h2>상품 목록</h2>
					<table>
						<tr id="tr_top">
							<td width="100px">상품코드</td>
							<td>상품이미지</td>
							<td width="150px">상품명</td>
							<td width="100px">가격</td>
							<td width="200px">상품상세</td>
						</tr>
						<!-- JSTL 의 c:forEach 태그를 사용하여 articleList 에서 BoardDTO 객체를 꺼내서 내용 출력 -->
						<!-- 단, 게시물 목록이 하나라도 존재할 경우에만 출력 c:if 태그 사용 -->
						<c:if test="${not empty productList and pageInfo.getListCount() > 0}">
							<c:forEach var="product" items="${productList }">
								<tr>
									<td>${product.getPd_code() }</td>
									<td>${product.getPd_img() }</td>
									<td>${product.getPd_name() }</td>
									<td>${product.getPd_price() }</td>
									<td>${product.getPd_detail() }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					</section>
					<section id="buttonArea">
						<input type="button" value="상품등록" onclick="location.href='ProductRegisterForm.ad'" />
					</section>
					<section id="pageList">
						<c:choose>
							<c:when test="${pageNum > 1}">
								<input type="button" value="이전" onclick="location.href='ProductList.ad?page=${pageNum - 1}'">
							</c:when>
							<c:otherwise>
								<input type="button" value="이전">
							</c:otherwise>
						</c:choose>
							
						<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
							<c:choose>
								<c:when test="${pageNum eq i}">
									${i }
								</c:when>
								<c:otherwise>
									<a href="BoardList.bo?page=${i }">${i }</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				
						<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
						<c:choose>
							<c:when test="${pageNum < maxPage}">
								<input type="button" value="다음" onclick="location.href='ProductList.ad?page=${pageNum + 1}'">
							</c:when>
							<c:otherwise>
								<input type="button" value="다음">
							</c:otherwise>
						</c:choose>
					</section>					
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts_ad_pr.js"></script>
    </body>
</html>