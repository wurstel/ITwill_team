<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<link href="../css/styles_ad_pr.css" rel="stylesheet" />
		<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
	        
		<script type="text/javascript">
// 			$(document).ready(function(){
// 			    // 상품 수정 버튼 클릭이벤트
// 			    $("#editBtn").click(function(){
// 			        var productName = $("#productName").val();
// 			        var productPrice = $("#productPrice").val();
// 			        var productDesc = $("#productDesc").val();
// 			        // 상품 수정 폼 유효성 검사
// 			        if(productName == "") {
// 			            alert("상품명을 입력해주세요");
// 			            productName.foucs();
// 			        } else if (productPrice == "") {
// 			            alert("상품 가격을 입력해주세요");
// 			            productPrice.focus();
// 			        } else if (productDesc == "") {
// 			            alert("상품 설명을 입력해주세요");
// 			            productDesc.focus();
// 			        }
// 			        document.form1.action = "./ProductModifyPro.ad";
// 			        document.form1.submit();
// 			    });
// 	    // 상품 삭제 버튼 클릭이벤트
// 			    $("#deleteBtn").click(function(){
// 			        // 상품 삭제 확인
// 			        if(confirm("상품을 삭제하시겠습니까?")){
// 			            document.form1.action = "./ProductDeletePro.ad";
// 			            document.form1.submit();
// 			        }
// 			    });
// 			    // 상품 목록 버튼 클리이벤트
// 			    $("#listBtn").click(function(){
// 			        location.href = "./ProductList.ad";
// 			    });
// 			});
 		</script>
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
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">상품등록</li>
                        </ol>
                    </div>
                    <div>
						<h2>상품 정보/삭제</h2>
						<form id="form1" name="form1" enctype="multipart/form-data" method="post">
						    <table border="">
						        <tr>
						            <td>상품 이미지</td>
						            <td>
						                <img src="${path}/images/${vo.productUrl}" height="300px" width="310px">
						                <br>
						                <input type="file" id="productPhoto" name="productPhoto">
						            </td>
						        </tr>
						        <tr>
						            <td>상품명</td>
						            <td><input type="text" id="productName" name="productName" value="${vo.productName}"></td>
						        </tr>
						        <tr>
						            <td>가격</td>
						            <td><input type="number" id="productPrice" name="productPrice" value="${vo.productPrice}"></td>
						        </tr>
						        <tr>
						            <td>상품소개</td>
						            <td><textarea id="productDesc" name="productDesc" rows="5" cols="60">${vo.productDesc}</textarea></td>
						        </tr>
						        <tr>
						            <td colspan="2" align="center">
						                <input type="hidden" name="productId" value="${vo.productId}">
						                <input type="button" id="editBtn" value="수정">
						                <input type="button" id="deleteBtn"value="삭제">
						                <input type="button" id="listBtn" value="상품목록">    
						            </td>
						        </tr>
						    </table>
						</form>
                	</div>
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
        <script src="../js/scripts_ad_pr.js"></script>
    </body>
</html>