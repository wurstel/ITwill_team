<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/9a9e4a4c63.js" crossorigin="anonymous"></script>
<script src="js/joinFunc.js"></script>
<script src="../js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1>로그인</h1>
	<form action="login_pro.me" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mem_id" required="required" size="20"></td>
			</tr>
			<tr>
				<td>패스워드</td>									<!-- 눈모양 아이콘 추가 -->
				<td><input type="password" name="mem_password" required="required" size="20">
				<i class="fa-solid fa-eye"></i>
				<span>(아이콘 클릭시 비밀번호 확인가능)</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인">
					<input type="button" value="회원가입" onclick="location.href='join_form.me'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>