<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Q&A 게시판</title>
</head>
<body>
	<jsp:include page="../inc/header.jsp"></jsp:include>
	<div class="container">
	<!-- JSTL 의 c:set 태그를 사용하여 PageInfo 객체의 값들을 변수에 저장 -->
	<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />

	<!-- 게시판 리스트 -->
	<section id="listForm">
	<h2 class="text-center" ><a href="CustomerCenter.cu">Q&A 게시판</a></h2>
	<table class="table table-hover table-striped text-center" style="border: 1px solid;">
		<tr id="tr_top">
			<th width = "80">번호</th>
			<th width = "100">작성자</th> <!-- 로그인 sid -->
			<th width = "500">제목</th>
		</tr>
		<!-- JSTL 의 c:forEach 태그를 사용하여 articleList 에서 BoardDTO 객체를 꺼내서 내용 출력 -->
		<!-- 단, 게시물 목록이 하나라도 존재할 경우에만 출력 c:if 태그 사용 -->
		<c:if test="${not empty articleList and pageInfo.getListCount() > 0}">
			<c:forEach var="board" items="${articleList }">
				<tr>
					<td>${board.getQna_num() }</td>
					<td>${board.getQna_mem_id() }</td>
					<td id="subject">
						<a href="QNADetail.cu?qna_num=${board.getQna_num() }&page=${pageNum}">
							<!-- 답글에 대한 들여쓰기(공백 추가) 작업 처리 -->
							${board.getQna_title() }
						</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	</section>
	<section id="buttonArea">
		<input type="button" value="글쓰기" onclick="location.href='QNAWriteForm.cu'" />
	</section>
	<section id="pageList">
		<div>
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<c:choose>
						<c:when test="${pageNum > 1}">
							<a class="page-link" href="QNABoardList.cu?page=${pageNum - 1}">Previous</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="">Previous</a>
						</c:otherwise>
					</c:choose>
				</li>
	            <li class="page-item">
	            	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	            		<c:choose>
	            			<c:when test="${pageNum eq i}">
	            				<a class="page-link" href="#">${i }</a>
	            			</c:when>
	            			<c:otherwise>
	            				<a class="page-link" href="QNABoardList.cu?page=${i }">${i }</a>
	            			</c:otherwise>
	            		</c:choose>
	            	</c:forEach>
	            </li>
	            <li class="page-item">
	            	<c:choose>
	            		<c:when test="${pageNum < maxPage}">
	            			<a class="page-link" href="QNABoardList.cu?page=${pageNum + 1}">Next</a>
	            		</c:when>
	            		<c:otherwise>
	            			<a class="page-link" href="">Next</a>
	            		</c:otherwise>
	            	</c:choose>
	            </li>
			</ul>
		</div>
	</section>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	</div>
</body>