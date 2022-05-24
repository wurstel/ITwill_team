package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;

import static db.jdbcUtil.*;
import vo.BasketListDTO;

public class BasketService {

	public ArrayList<BasketListDTO> basketlist(String id) {
		ArrayList<BasketListDTO> basketlist = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		basketlist = memberDAO.basketlist(id); 
		
		close(con);
		
		
		
		return basketlist;
	}

}
