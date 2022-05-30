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

	public int qtyUpdate(String bk_qty, String bk_mem_id, String bk_order_num, String bk_pd_code) {
		int updateSuccess = 0;
		System.out.println("BasketService-qtyupdate");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		updateSuccess = memberDAO.qtyUpdate(bk_qty,bk_mem_id,bk_order_num,bk_pd_code);
		
		if(updateSuccess > 0) {				//업데이트 성공
			commit(con);
		}else {								//업데이트 실패
			rollback(con);
		}
		
		return updateSuccess;
	}

}
