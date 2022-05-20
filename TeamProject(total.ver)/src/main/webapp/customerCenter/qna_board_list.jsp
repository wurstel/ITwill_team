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
	<table class="table table-hover text-center">
		<thead class="table-light">
			<tr>
				<th scope="col" width=80>글번호</th>
	            <th scope="col">제목</th>
	            <th scope="col" width=100>작성자</th>
	            <th scope="col" width=150>작성일</th>
	            <th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody>
		<!-- JSTL 의 c:forEach 태그를 사용하여 articleList 에서 BoardDTO 객체를 꺼내서 내용 출력 -->
		<!-- 단, 게시물 목록이 하나라도 존재할 경우에만 출력 c:if 태그 사용 -->
		<c:if test="${not empty articleList and pageInfo.getListCount() > 0}">
			<c:forEach var="board" items="${articleList }">
				<tr class="text-center">
					<td scope="row">${board.getQna_num() }</td>
					<td id="subject" scope="row">
						<a href="QNADetail.cu?qna_num=${board.getQna_num() }&page=${pageNum}">
							<!-- 답글에 대한 들여쓰기(공백 추가) 작업 처리 -->
							${board.getQna_title() }
						</a>
					</td>
					<td scope="row">${board.getQna_mem_id() }</td>
					<td scope="row">작성일</td>
					<td scope="row">
                  <button type="button" class="btn btn-outline-danger" onclick="location.href='QNADeleteForm.cu'">삭제</button>
				<!-- 세션 아이디가 관리자 일때 삭제 -->
              </td>  
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	</section>
	<section id="buttonArea">
		<button type="button" onclick="location.href='QNAWriteForm.cu'" class="btn btn-outline-primary">글쓰기</button>
	</section>
	<section id="pageList">
		<div size="200">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<c:choose>
						<c:when test="${pageNum > 1}">
							<a class="page-link" href="QNAList.cu?page=${pageNum - 1}">Previous</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" aria-disabled="true">Previous</a>
						</c:otherwise>
					</c:choose>
				</li>
	            <li class="page-item">
	            	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	            		<c:choose>
	            			<c:when test="${pageNum eq i}">
	            				<a class="page-link" aria-disabled="true">${i }</a>
	            			</c:when>
	            			<c:otherwise>
	            				<a class="page-link" href="QNAList.cu?page=${i }">${i }</a>
	            			</c:otherwise>
	            		</c:choose>
	            	</c:forEach>
	            </li>
	            <li class="page-item">
	            	<c:choose>
	            		<c:when test="${pageNum < maxPage}">
	            			<a class="page-link" href="QNAList.cu?page=${pageNum + 1}">Next</a>
	            		</c:when>
	            		<c:otherwise>
	            			<a class="page-link" aria-disabled="true">Next</a>
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