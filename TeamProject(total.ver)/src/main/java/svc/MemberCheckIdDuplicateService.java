package svc;

import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.MemberDAO;

public class MemberCheckIdDuplicateService {

	public boolean isDuplicateId(String id) {
boolean isDuplicate = false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		// MemberDAO 의 isDuplicateId() 메서드를 호출하여 아이디 중복 판별
		// => 파라미터 : 아이디(id)
		//    리턴타입 : boolean(isDuplicate)
		isDuplicate = memberDAO.isDuplicateId(id);
		
		close(con);
		
		return isDuplicate;

	}

}
