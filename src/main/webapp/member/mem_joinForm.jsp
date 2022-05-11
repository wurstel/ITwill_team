<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function postCodeSearch() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
	            var extraRoadAddr = ''; // 참고 항목 변수
	
	            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                extraRoadAddr += data.bname;
	            }
	            // 건물명이 있고, 공동주택일 경우 추가한다.
	            if(data.buildingName !== '' && data.apartment === 'Y'){
	               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	            }
	            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	            if(extraRoadAddr !== ''){
	                extraRoadAddr = ' (' + extraRoadAddr + ')';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById("postcode").value = data.zonecode;
	            document.getElementById("address").value = roadAddr;
	            
	           
	        }
	    }).open();
	}
</script>
</head>
<body>
	<form action="mem_join.me">
		<table border="1">
			<tr><td>아이디</td>	<td><input type="text" name="id" id="id"></td></tr>
			<tr><td>비밀번호</td>	<td><input type="password" name="pass" id="pass"></td></tr>
			<tr><td>이름</td>		<td><input type="text" name="name" id="name"></td></tr>
			<tr><td>생년월일</td>	<td><input type="date" name="birth" id="birth"></td></tr>
			<tr><td>성별</td>		<td><input type="radio" value="남" name="gender" id="gender">남<input type="radio" value="여" name="gender" id="gender">여</td></tr>
			<tr><td>이메일</td>	<td><input type="text" name="email" id="email">@<input type="text" name="domain" id="domain"></td>
			<td>
				<select onselect="">
					<option value="">선택하세요</option>
					<option value="naver.com">naver.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="nate.com">nate.com</option>
				</select>
			</td>
			</tr>
			<tr><td>전화번호</td>	<td><input type="text"></td></tr>
			<tr><td rowspan="2">주소</td>	<td><input type="text" id="postcode"><input type="button" value="주소검색" onclick="postCodeSearch()"></td></tr>
			<tr><td><input type="text" id="address"><input type="text" id="add_detail" placeholder="상세주소 입력"></td></tr>
		</table>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>