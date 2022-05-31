package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.sql.Connection;

import dao.CartDAO;
import vo.CartDTO;

public class ProductCartService {

	public void insertCart(CartDTO cart) {
		int insertCart = 0;
		System.out.println("ProductCartService - getCart()");
		System.out.println(cart);
		
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : board_num    리턴타입 : BoardDTO(article)
		insertCart = cartDAO.insertCart(cart);
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		if(insertCart > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		// 7. 조회 결과 리턴
		
	}

	public String searchBasket(String mem_id, String pd_code) {
		String orderNumIs = null;
		
		Connection con = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(con);
		
		orderNumIs = cartDAO.searchBasket(mem_id,pd_code);
		System.out.println(orderNumIs);
		close(con);
		return orderNumIs;
	}

}
