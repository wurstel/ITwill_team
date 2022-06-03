package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static db.jdbcUtil.*;
import vo.Order_padDTO;
import vo.payInfoDTO;

public class PayDAO {

	private static PayDAO instance = new PayDAO();
	public PayDAO() {}
	
	public static PayDAO getInstance() {
		return instance;
	};
	// ---------------------------------------------------------------------------------------
	// 외부(Service 클래스)로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버 변수와 Setter 메서드 정의
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertOrderPad(Order_padDTO padDTO) {

		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO order_pad VALUES((select order_num from(SELECT ifnull(max(order_num),0)+1 as order_num from order_pad where order_mem_id=?)tmp),"
				+ "?,?,'1',?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, padDTO.getOrder_mem_id());
			pstmt.setString(2, padDTO.getOrder_mem_id());
			pstmt.setString(3, padDTO.getOrder_pd_code());
			pstmt.setString(4, padDTO.getOrder_qty());
			pstmt.setString(5, padDTO.getOrder_postcode());
			pstmt.setString(6, padDTO.getOrder_address());
			pstmt.setString(7, padDTO.getOrder_phoneNum());
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public payInfoDTO getPayInfo(String mem_id, String pd_code) {
		payInfoDTO payInfoDTO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.pd_name,(p.pd_price*o.order_qty) as amount,mem_email,mem_name,mem_phoneNum,order_address,order_postcode"
				+ " FROM member m JOIN order_pad o"
				+ " ON m.mem_id = o.order_mem_id"
				+ " JOIN product p"
				+ " ON o.order_pd_code = p.pd_code"
				+ " WHERE order_mem_id=? AND order_pd_code=? AND order_num=(SELECT MAX(order_num) FROM order_pad WHERE order_mem_id=?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, pd_code);
			pstmt.setString(3, mem_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				payInfoDTO = new payInfoDTO();
				payInfoDTO.setPd_name(rs.getString(1));
				payInfoDTO.setAmount(rs.getString(2));
				payInfoDTO.setMem_email(rs.getString(3));
				payInfoDTO.setMem_name(rs.getString(4));
				payInfoDTO.setMem_phoneNum(rs.getString(5));
				payInfoDTO.setOrder_address(rs.getString(6));
				payInfoDTO.setOrder_postcode(rs.getString(7));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return 	payInfoDTO;
	}
}
