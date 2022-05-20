<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../js/joinFunc.js"></script>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class="container-fluid px-1 py-5 mx-auto">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-7 col-lg-8 col-md-9 col-11 text-center">
            <h3>배송정보 입력</h3>
            <div class="card">
                <form class="form-card" action="payment.pm">
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex"> <label class="form-control-label px-3">우편번호<span class="text-danger"> *</span></label> <input type="text" id="postcode" name="postcode" readonly> </div>
                        <div class="form-group col-sm-6 flex-column d-flex"> <label class="form-control-label px-3"><span class="text-danger"> &nbsp;</span></label><input type="button" value="주소 검색" onclick="postCodeSearch()"></div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex"> <label class="form-control-label px-3">주소<span class="text-danger"> *</span></label> <input type="text" id="address" name="address" > </div>
                        <div class="form-group col-sm-6 flex-column d-flex"> <label class="form-control-label px-3">상세주소<span class="text-danger"> *</span></label> <input type="text" id="address_detail" name="address_detail" placeholder="상세주소 입력" > </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-sm-6 flex-column d-flex"> <label class="form-control-label px-3">전화번호 <span class="text-danger"> *</span></label> <input type="text" id="phone" name="phone" placeholder="연락받을 전화번호 입력" > </div>
                    </div>
                    <div class="row justify-content-between text-left">
                        <div class="form-group col-12 flex-column d-flex"> <label class="form-control-label px-3">배송시 요청사항<span class="text-danger"> *</span></label>
                        	<select>
                        		<option value="직접 수령 부재시 문 앞">직접 수령 부재시 문 앞</option>
                        		<option value="문 앞">문 앞</option>
                        		<option value="경비실">경비실</option>
                        		<option value="택배함">택배함</option>
                        	</select> 
                       	</div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group col-sm-6"> <button type="submit" class="btn-block btn-primary">배송정보 입력</button> </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<!-- 	<h1>subscribe_destination.jsp</h1> -->
<!-- 	<form action="payment.pm"> 주소 api를 이용	 -->
<!-- 		<table border="1"> -->
<!-- 			<tr><th colspan ="3">배송지 주소 입력</th></tr> -->
<!-- 			<tr><td>우편번호</td><td><input type="text" name="postcode" id="postcode" readonly></td><td><input type="button" value="주소 검색" onclick="postCodeSearch()"></td></tr> -->
<!-- 			<tr><td>주소</td><td><input type="text" name="address" id="address" readonly></td><td><input type="text" placeholder="상세주소 입력"></td></tr> -->
<!-- 			<tr><th colspan ="3"><input type="submit" value="배송지 선택 완료"></th></tr> -->
<!-- 		</table> -->
<!-- 	</form> -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>	
</body>
</html>