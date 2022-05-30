package svc;

import java.sql.Connection;

import dao.MemberDAO;
import static db.jdbcUtil.*;

import vo.MemberDTO;

public class MemberjoinService {

	public int insertMember(MemberDTO memberDTO) {
		int insertMember = 0;
		System.out.println("MemberjoinService");
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		System.out.println(memberDTO);
		insertMember = memberDAO.insertMember(memberDTO);
		
		if(insertMember > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		
		return insertMember;
	}
	
	
}
