<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap 5 Simple Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="css/styles_ad_pr.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
    <style>
        .sidebar {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            z-index: 100;
            padding: 90px 0 0;
            box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
            z-index: 99;
        }

        @media (max-width: 767.98px) {
            .sidebar {
                top: 11.5rem;
                padding: 0;
            }
        }
            
        .navbar {
            box-shadow: inset 0 -1px 0 rgba(0, 0, 0, .1);
        }

        @media (min-width: 767.98px) {
            .navbar {
                top: 0;
                position: sticky;
                z-index: 999;
            }
        }

        .sidebar .nav-link {
            color: #333;
        }

        .sidebar .nav-link.active {
            color: #0d6efd;
        }
    </style>
</head>
<body>
    <jsp:include page="../inc/header.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row">
            <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <div class="position-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                          <a class="nav-link active" aria-current="page" href="main.jsp">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
                            <span class="ml-2">메인페이지</span>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="ProductList.ad">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                            <span class="ml-2">상품관리</span>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>
                            <span class="ml-2">주문관리</span>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="memberManage.ad">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                            <span class="ml-2">회원관리</span>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="CustomerCenter.cu">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bar-chart-2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>
                            <span class="ml-2">고객센터</span>
                          </a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-layers"><polygon points="12 2 2 7 12 12 22 7 12 2"></polygon><polyline points="2 17 12 22 22 17"></polyline><polyline points="2 12 12 17 22 12"></polyline></svg>
                            <span class="ml-2">매출관리</span>
                          </a>
                        </li>
                        <!-- <li class="nav-item">
                            <a class="btn btn-sm btn-secondary ml-3 mt-2" href="https://themesberg.com/blog/bootstrap/simple-bootstrap-5-dashboard-tutorial">
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-book" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M1 2.828v9.923c.918-.35 2.107-.692 3.287-.81 1.094-.111 2.278-.039 3.213.492V2.687c-.654-.689-1.782-.886-3.112-.752-1.234.124-2.503.523-3.388.893zm7.5-.141v9.746c.935-.53 2.12-.603 3.213-.493 1.18.12 2.37.461 3.287.811V2.828c-.885-.37-2.154-.769-3.388-.893-1.33-.134-2.458.063-3.112.752zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
                                </svg>
                                <span class="ml-2">Read tutorial</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-sm btn-warning ml-3 mt-2" href="https://themesberg.com/product/admin-dashboard/volt-bootstrap-5-dashboard">
                                ⚡︎ Volt Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-sm btn-primary ml-3 mt-2" href="https://themesberg.com">
                                By Themesberg ❤️
                            </a>
                        </li> 필요할 때 다시살리기 사이드바 버튼--> 
                      </ul>
                </div>
            </nav>
			<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 py-4">
				<div class="container-fluid px-4">
					<h1 class="mt-4">회원관리</h1>
				</div>
				<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
				<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
				<c:set var="startPage" value="${pageInfo.getStartPage() }" />
				<c:set var="endPage" value="${pageInfo.getEndPage() }" />
				<c:set var="listCount" value="${pageInfo.getListCount() }" />

				<!-- 게시판 리스트 -->
				<section id="listForm">
					<h2>회원 목록</h2>
					<table>
						<tr id="tr_top">
							<td width="100px">아이디</td>
							<td width="100px">회원이름</td>
							<td width="150px">생년월일</td>
							<td width="100px">성별</td>
							<td width="200px">이메일</td>
							<td width="200px">전화번호</td>
							<td width="200px">주소</td>
							<td width="200px">비고</td>
						</tr>
						<!-- 단, 게시물 목록이 하나라도 존재할 경우에만 출력 c:if 태그 사용 -->
						<c:if test="${pageInfo.getListCount() > 0 && not empty memberList }">
							<c:forEach var="member" items="${memberList }">
								<tr class="text-center">
									<td>${member.mem_id }</td>
									<td>${member.mem_name }</td>
									<td>${member.mem_birth }</td>
									<td>${member.mem_gender }</td>
									<td>${member.mem_email }</td>
									<td>${member.mem_phoneNum }</td>
									<td>${member.mem_address }</td>
									<td><button type="button" class="btn btn-outline-danger btn-sm">삭제하기</button></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</section>
				<section id="pageList">
					<c:choose>
						<c:when test="${pageNum > 1}">
							<input type="button" value="이전"
								onclick="location.href='ProductList.ad?page=${pageNum - 1}'">
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
							<input type="button" value="다음"
								onclick="location.href='ProductList.ad?page=${pageNum + 1}'">
						</c:when>
						<c:otherwise>
							<input type="button" value="다음">
						</c:otherwise>
					</c:choose>
				</section>
			</main>
		</div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
    <!-- Github buttons -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <script>
        new Chartist.Line('#traffic-chart', {
            labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat','Sun'],
            series: [
                [23000, 25000, 19000, 34000, 56000, 64000,80000]
            ]
            }, {
            low: 0,
            showArea: true
        });        
    </script>
</body>
</html>