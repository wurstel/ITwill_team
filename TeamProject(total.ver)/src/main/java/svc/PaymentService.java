package svc;

import java.sql.Connection;

import dao.PayDAO;

import static db.jdbcUtil.*;
import vo.Order_padDTO;
import vo.payInfoDTO;

public class PaymentService {

	public int insertOrderPad(Order_padDTO padDTO) {
		int insertCount = 0;
		System.out.println("PaymentService");
		Connection con = getConnection();
		PayDAO payDAO = PayDAO.getInstance();
		payDAO.setConnection(con);
		
		insertCount=payDAO.insertOrderPad(padDTO);
		if(insertCount > 0) {		//주문정보 입력 성공
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return insertCount;
	}

	public payInfoDTO getPayInfo(String mem_id, String pd_code) {
		payInfoDTO payInfoDTO = null;
		System.out.println("PaymentService-getOrderPad");
		
		Connection con = getConnection();
		PayDAO payDAO = PayDAO.getInstance();
		payDAO.setConnection(con);
		
		payInfoDTO = payDAO.getPayInfo(mem_id,pd_code);
		
		
		close(con);
		
		return payInfoDTO;
	}

}
