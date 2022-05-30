package dao;

import static db.jdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.CartDTO;

public class CartDAO {

private static CartDAO instance = new CartDAO();
	
	private CartDAO() {}

	public static CartDAO getInstance() {
		return instance;
	};
	// ---------------------------------------------------------------------------------------
	// 외부(Service 클래스)로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버 변수와 Setter 메서드 정의
	Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}

	//장바구니에 넣기 및 중복해서 넣을시 변경
	public int insertCart(CartDTO cart) {
		int insertCart = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		
			try {
			
			String sql = "SELECT * FROM basket WHERE bk_pd_code=? AND bk_mem_id=? AND bk_order_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart.getBk_pd_code());
			pstmt.setString(2, cart.getBk_mem_id());
			pstmt.setString(3, cart.getBk_order_num());
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				
				sql = "INSERT INTO basket VALUES((select order_num from"+
				"(select CAST(IFNULL(max(bk_order_num),0) +1 AS CHAR) as order_num from basket) tmp),?,?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, cart.getBk_mem_id());
				pstmt2.setString(2, cart.getBk_pd_code());
				pstmt2.setString(3, cart.getBk_qty());
				
				insertCart = pstmt2.executeUpdate();
				
			} else {
				sql = "UPDATE basket SET bk_qty=? WHERE bk_pd_code=? AND bk_mem_id=? AND bk_order_num=?";
				pstmt3 = con.prepareStatement(sql);
				pstmt3.setString(1, cart.getBk_qty());
				pstmt3.setString(2, cart.getBk_pd_code());
				pstmt3.setString(3, cart.getBk_mem_id());
				pstmt3.setString(4, cart.getBk_order_num());
				
				insertCart = pstmt3.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - insertCart()");
		} finally {
			close(rs);
			close(pstmt3);
			close(pstmt2);
			close(pstmt);
		}
//		System.out.println(insertCart);
		return insertCart;
	}

	//등록된 주문번호 조회
	public String searchBasket(String mem_id, String pd_code) {
		String orderNumIs = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT bk_order_Num FROM basket WHERE bk_mem_id=? AND bk_pd_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, pd_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				orderNumIs = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return orderNumIs;
	}

}
