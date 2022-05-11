<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function selectLevel(grade) {
		if (grade == 'standard') {
			document.getElementById('test').innerHTML = "standard";
			location.href="sub_determine_st.sub";
		} else if (grade == 'premium') {
			document.getElementById('test').innerHTML = "premium";
			location.href="sub_determine_pre.sub";
			
		}
	}


</script>
</head>
<body>
	<h1>구독 단계</h1>
	<input type="button" value="standard" onclick="selectLevel(this.value)">	<!-- standard 선택 후 수량으로 이동 -->
	<input type="button" value="premium" onclick="selectLevel(this.value)">
	<div id="test"></div>
	</body>
</html>