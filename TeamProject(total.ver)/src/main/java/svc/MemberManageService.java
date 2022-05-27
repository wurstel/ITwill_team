package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;

import static db.jdbcUtil.*;
import vo.MemberDTO;

public class MemberManageService {

	public ArrayList<MemberDTO> loadMember(int pageNum, int listLimit) {
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ArrayList<MemberDTO> memberList = memberDAO.loadMember(pageNum,listLimit); 
		
		close(con);
		
		return memberList;
	}

	public int getListCount() {
		
		int listCount = 0;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectListCount();
		
		close(con);
		
		
		
		
		return listCount;
	}

}
