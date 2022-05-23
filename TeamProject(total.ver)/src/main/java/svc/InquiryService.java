package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.Order_checkDTO;

import static db.jdbcUtil.*;

public class InquiryService {

	public String isInquiry(String id) {
		
		String isInquiry = "";
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		isInquiry = memberDAO.isInquiry(id);
		
		close(con);
		
		
		return isInquiry;
	}

	public ArrayList<Order_checkDTO> loadInquiry(String id) {
		ArrayList<Order_checkDTO> list = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		
		list = memberDAO.loadInquiry(id);
		
		
		
		
		close(con);
		
		return list;
	}

}
