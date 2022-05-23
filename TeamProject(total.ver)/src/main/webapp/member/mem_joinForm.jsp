<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/9a9e4a4c63.js" crossorigin="anonymous"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="js/joinFunc.js"></script>
<script src="/js/jquery-3.6.0.js"></script>
</head>
<body>
	<form action="mem_join.me" name="fr" id="fr" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" id="id" readonly placeholder="중복확인체크">
					<input type="button" value="ID중복확인" onclick="checkDuplicateId()">				<!-- 아이디 중복방지 -->
					<span id="checkIdResult"></span>
				</td>

			</tr>										
			<tr><td>비밀번호</td>
				<td>
					<div class="main">
						<input type="password" name="pass" id="pass" onkeyup="checkPass(this.value)">
						<i class="fa-solid fa-eye"></i>
						<span>(아이콘 클릭시 비밀번호 확인가능)</span>
					</div>
					<span id="passResult"></span>
				</td>
			</tr>
			<tr><td>이름</td>		<td><input type="text" name="name" id="name"></td></tr>
			<tr><td>생년월일</td>	<td><input type="date" name="birth" id="birth"></td></tr>
			<tr><td>성별</td>		<td><input type="radio" value="남" name="gender" id="gender">남<input type="radio" value="여" name="gender" id="gender">여</td></tr>
			<tr><td>이메일</td>	<td><input type="text" name="email" id="email">@<input type="text" name="domain" id="domain"></td>			<!-- 데이터 넘길떄 합쳐서 -->
			<td>
				<select  name="sDomain" id="sDomain">
					<option value="">선택하세요</option>
					<option value="naver.com">naver.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="nate.com">nate.com</option>
					<option value="daum.net">daum.net</option>
					<option value="msn.com">msn.com</option>
					<option value="outlook.com">outlook.com</option>
				</select>
			</td>
			</tr>
			<tr><td>전화번호</td>	<td><input type="text" name="phone" id="phone" onblur="checkPhone(this.value)"></td></tr>				<!-- 0101xxxxxxxx 이런시고 넘어가게 -->
			<tr><td rowspan="2">주소</td>	<td><input type="text" name="postcode" id="postcode"><input type="button" value="주소검색" onclick="postCodeSearch()"></td></tr>
			<tr><td><input type="text" name="address" id="address"><input type="text" name="add_detail" id="add_detail" placeholder="상세주소 입력"></td></tr>
		</table>
		<input type="submit" value="회원가입">
<%-- 		<c:if test="">				<!--chaptcha 미입력시 --> --%>
<!-- 			<input type="submit" value="회원가입" disabled="disabled"> -->
<%-- 		</c:if> --%>
	</form>
</body>
</html>