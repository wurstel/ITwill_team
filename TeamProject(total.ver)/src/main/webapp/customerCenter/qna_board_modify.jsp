<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.QnaDTO"%>
<%
// request 객체에 저장된 BoardDTO 객체("article") 가져오기
QnaDTO article = (QnaDTO)request.getAttribute("article");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Q&A 게시판 수정</title>
</head>
<body style="padding-top: 5rem;">
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="#">Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">

			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<main role="main" class="container">
		<form name="QNAModifyForm" method="POST" action="./QNAModifyPro.cu">
			<input type="hidden" name="qna_num" value="<%=article.getQna_num()%>">
			<input type="hidden" name="page" value="<%=request.getParameter("page")%>">
			<div class="pt-1">
			<input type="text" name="qna_mem_id" value="<%=article.getQna_mem_id() %>"
				style="border-radius: 5px; width: 100%; padding: 5px;"><br>
			</div>
			<div class="pt-1">
				<input type="password" name="qna_pass" placeholder="비밀번호"
				style="border-radius: 5px; width: 100%; padding: 5px;">
			</div>
			<div class="pt-1">
			<input type="text" name="qna_title" value="<%=article.getQna_title() %>"
				style="border-radius: 5px; width: 100%; padding: 5px;">
			</div>
			<div class="pt-1">
				<textarea id="summernote" name="qna_content"><%=article.getQna_content() %></textarea>
			</div>
			<script>
				$('#summernote').summernote({					
					tabsize : 2,
					height : 300
				});
			</script>
			<div class="pt-1 text-right">
				<button class="btn btn btn-success" type="submit"
					style="width: 10%; padding: 5px;">제출</button>
			</div>
		</form>
	</main>
</body>
</html>