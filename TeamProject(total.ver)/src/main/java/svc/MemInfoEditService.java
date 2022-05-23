package svc;

import java.sql.Connection;

import dao.MemberDAO;
import static db.jdbcUtil.*;
import vo.MemberDTO;

public class MemInfoEditService {

	public boolean isRemainedSession(String id) {
		System.out.println("MemInfoEditService");
		boolean isRegisterdMember = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		//중복 판별이지만 세션이 종료되어 로그인이 유지되었는지를 확인하기위해서 사용
		isRegisterdMember = memberDAO.isDuplicateId(id);	
		
		close(con);
		
		return isRegisterdMember;
		
	}

	public MemberDTO isMemberInfo(String id) {
		MemberDTO memberDTO = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		memberDTO = memberDAO.getMemberInfo(id);
		
		close(con);
		
		return memberDTO;
	}
	
	
}
