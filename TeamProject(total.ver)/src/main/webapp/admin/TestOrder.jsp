<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jQuery -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript">
	var IMP = window.IMP; // 생략 가능
	IMP.init("imp74403648"); // 예: imp00000000 가맹점 식별코드
	
	function kakaoPay() {
	  
    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
        pg: 'kakaopay', // 결제할 pg사
        pay_method: 'card',
        merchant_uid: 'merchant_'+new Date().getTime(), // 주문번호
        name: '파댕이', // 상품명
        amount: 100, // 가격
        buyer_email: 'lhj6346@gmail.com',
        buyer_name: '이현진',
        buyer_tel: '010-5771-6061',
        buyer_addr: '부산시 강서구',
        buyer_postcode: '46723'
    }, function (rsp) { // callback
    	console.log(rsp);
        if (rsp.success) {
        	var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
            // 결제 성공 시 로직,
            
        } else {
        	 var msg = '결제에 실패하였습니다.';
	         msg += '에러내용 : ' + rsp.error_msg;
            // 결제 실패 시 로직,
        }
        alert(msg);
    });
  }
	function tossPay() {
		  
	    // IMP.request_pay(param, callback) 결제창 호출
	    IMP.request_pay({ // param
	        pg: 'tosspay', // 결제할 pg사
	        pay_method: 'card',
	        merchant_uid: 'merchant_'+new Date().getTime(), // 주문번호
	        name: '파댕이', // 상품명
	        amount: 100, // 가격
	        buyer_email: 'lhj6346@gmail.com',
	        buyer_name: '이현진',
	        buyer_tel: '010-5771-6061',
	        buyer_addr: '부산시 강서구',
	        buyer_postcode: '46723'
	    }, function (rsp) { // callback
	    	console.log(rsp);
	        if (rsp.success) {
	        	var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
	            // 결제 성공 시 로직,
	            
	        } else {
	        	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
	            // 결제 실패 시 로직,
	        }
	        alert(msg);
	    });
	  }
	function kgPay() {
		  
	    // IMP.request_pay(param, callback) 결제창 호출
	    IMP.request_pay({ // param
	        pg: 'html5_inicis', // 결제할 pg사
	        pay_method: 'card',
	        merchant_uid: 'merchant_'+new Date().getTime(), // 주문번호
	        name: '파댕이', // 상품명
	        amount: 100, // 가격
	        buyer_email: 'lhj6346@gmail.com',
	        buyer_name: '이현진',
	        buyer_tel: '010-5771-6061',
	        buyer_addr: '부산시 강서구',
	        buyer_postcode: '46723'
	    }, function (rsp) { // callback
	    	console.log(rsp);
	        if (rsp.success) {
	        	var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
	            // 결제 성공 시 로직,
	            
	        } else {
	        	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
	            // 결제 실패 시 로직,
	        }
	        alert(msg);
	    });
	  }
</script>
<button onclick="kakaoPay()">카카오페이</button>
<button onclick="tossPay()">토스페이</button>
<button onclick="kgPay()">KG이니시스</button>
</body>
</html>