package svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberLoginProService {

	public boolean loginMember(String id,String pass) {
		boolean isMember = false;
		System.out.println("MemberLoginProService");
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		// MemberDAO 의 isMember() 메서드를 호출하여 회원 등록 작업 수행
		// => 파라미터 : id,pass   리턴타입 : boolean(isMember)
		isMember = memberDAO.isMember(id,pass);
		
		close(con);
		
		
		return isMember;
	}
	
}
