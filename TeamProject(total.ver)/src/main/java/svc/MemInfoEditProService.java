package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.jdbcUtil.*;

public class MemInfoEditProService {

	public int memInfoEditSuccess(String id, String email, String phoneNum, String address, String postcode) {
		System.out.println("MemInfoEditProService");
		int memInfoEditSuccess = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		memInfoEditSuccess = memberDAO.memInfoEdit(id,email,phoneNum,address,postcode);
		
		if(memInfoEditSuccess > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return memInfoEditSuccess;
	}

	
	
	
	
	
}
