package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberDTO;

public class MemberLoginProService {

	public boolean loginMember(MemberDTO memberDTO) {
//		System.out.println("MemberLoginProService");
		boolean isMember = false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		System.out.println(memberDTO.getMem_id());
		// MemberDAO 의 isMember() 메서드를 호출하여 회원 등록 작업 수행
		// => 파라미터 : MemberDTO 객체(member)   리턴타입 : boolean(isMember)
		isMember = memberDAO.isMember(memberDTO);
		
		close(con);
		
		
		return isMember;
	}
	
}
