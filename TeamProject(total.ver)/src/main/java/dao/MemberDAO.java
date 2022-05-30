package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.BasketListDTO;
import vo.MemberDTO;
import vo.Order_checkDTO;

import static db.jdbcUtil.*;

public class MemberDAO {
	// ------------ 싱글톤 디자인 패턴 구현 --------------
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {}

	public static MemberDAO getInstance() {
		return instance;
	}
	// ---------------------------------------------------
	// 외부에서 Connection 객체를 전달받아 멤버변수에 저장
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// ---------------------------------------------------
	// 아이디 중복 판별을 위한 조회 작업을 수행하는 isDuplicateId() 메서드 정의
	public boolean isDuplicateId(String id) {
		boolean isDuplicate = false; // true : 중복, false : 중복 아님
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member WHERE mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 입력받은 아이디가 존재할 경우(= 중복)
				isDuplicate = true;
			}
		} catch (SQLException e) {
			System.out.println("isDuplicateId() 메서드 오류!");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isDuplicate;
	}	
	
	//회원가입
	public int insertMember(MemberDTO memberDTO) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		try {
			String sql ="INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,'normal','0','null')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getMem_id());
			pstmt.setString(2, memberDTO.getMem_password());
			pstmt.setString(3, memberDTO.getMem_name());
			pstmt.setString(4, memberDTO.getMem_birth());
			pstmt.setString(5, memberDTO.getMem_gender());
			pstmt.setString(6, memberDTO.getMem_email());
			pstmt.setString(7, memberDTO.getMem_phoneNum());
			pstmt.setString(8, memberDTO.getMem_postcode());
			pstmt.setString(9, memberDTO.getMem_address());
			insertCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	//회원가입여부확인
	public boolean isMember(String id,String pass) {		
		boolean isMember = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM member WHERE mem_id=? AND mem_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isMember = true;
			}
		} catch (SQLException e) {
			System.out.println("Error! isMember");
//			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isMember;
	}

	
	//회원정보 받아오기
	public MemberDTO getMemberInfo(String id) {			
		MemberDTO memberDTO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE mem_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setMem_id(rs.getString(1));
				memberDTO.setMem_password(rs.getString(2));
				memberDTO.setMem_name(rs.getString(3));
				memberDTO.setMem_birth(rs.getString(4));
				memberDTO.setMem_gender(rs.getString(5));
				memberDTO.setMem_email(rs.getString(6));
				memberDTO.setMem_phoneNum(rs.getString(7));
				memberDTO.setMem_postcode(rs.getString(8));
				memberDTO.setMem_address(rs.getString(9));
				memberDTO.setMem_grade(rs.getString(10));
				memberDTO.setMem_point(rs.getString(11));
				memberDTO.setMem_paymethod(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return memberDTO;
	}

	//회원정보 수정
	public int memInfoEdit(String id, String email, String phoneNum, String address, String postcode) {
		int memInfoEditSuccess = 0;
		
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mem_email=?,mem_phoneNum=?,mem_address=?, mem_postcode=? WHERE mem_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, phoneNum);
			pstmt.setString(3, address);
			pstmt.setString(4, postcode);
			pstmt.setString(5, id);
			memInfoEditSuccess = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return memInfoEditSuccess;
	}

	//주문내역 확인
	public String isInquiry(String id) {

		String isInquiry = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT RESULT, CASE WHEN RESULT=0 THEN '주문내역이 없습니다' END AS COMMENT"
				+ " FROM (SELECT COUNT(*) AS RESULT FROM ORDER_CHECK WHERE order_mem_id=?) A";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("COMMENT") == null) {		//주문내역이 있는경우
					isInquiry = null;
				} else {									//주문내역이 없는경우
					isInquiry = rs.getString("COMMENT");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isInquiry;
	}
	
	//주문내역 불러오기
	public ArrayList<Order_checkDTO> loadInquiry(String id) {
		ArrayList<Order_checkDTO> list = null;
		Order_checkDTO order_checkDTO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM order_check WHERE order_mem_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			list = new ArrayList<Order_checkDTO>();
			while(rs.next()) {
				order_checkDTO = new Order_checkDTO();
				order_checkDTO.setMem_id(rs.getString(1));
				order_checkDTO.setPd_name(rs.getString(2));
				order_checkDTO.setPd_price(rs.getString(3));
				order_checkDTO.setOd_qty(rs.getString(4));
				order_checkDTO.setTotalprice(rs.getString(5));
				order_checkDTO.setOrder_status(rs.getString(6));
				
				list.add(order_checkDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	//장바구니 리스트 불러오기
	public ArrayList<BasketListDTO> basketlist(String id) {
		ArrayList<BasketListDTO> basketlist = null;
		BasketListDTO basketListDTO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM BASKET_LIST WHERE bk_mem_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			basketlist = new ArrayList<BasketListDTO>();
			while(rs.next()) {
				basketListDTO = new BasketListDTO();
				basketListDTO.setPd_name(rs.getString(1));
				basketListDTO.setPd_price(rs.getString(2));
				basketListDTO.setBk_qty(rs.getString(3));
				basketListDTO.setTotalprice(rs.getString(4));
				basketListDTO.setBk_order_num(rs.getString(5));
				basketListDTO.setBk_mem_id(rs.getString(6));
				basketListDTO.setBk_pd_code(rs.getString(7));
				
				basketlist.add(basketListDTO);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return basketlist;
	}

	//회원정보 불러오기
	public ArrayList<MemberDTO> loadMember(int pageNum, int listLimit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> memberList = null;
		MemberDTO memberDTO = null;
		int startRow = (pageNum - 1) * listLimit;
		
		String sql = "SELECT * FROM member LIMIT ?,?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				memberDTO = new MemberDTO(); 
				memberDTO.setMem_id(rs.getString(1));
				memberDTO.setMem_password(rs.getString(2));
				memberDTO.setMem_name(rs.getString(3));
				memberDTO.setMem_birth(rs.getString(4));
				memberDTO.setMem_gender(rs.getString(5));
				memberDTO.setMem_email(rs.getString(6));
				memberDTO.setMem_phoneNum(rs.getString(7));
				memberDTO.setMem_postcode(rs.getString(8));
				memberDTO.setMem_address(rs.getString(9));
				memberDTO.setMem_grade(rs.getString(10));
				memberDTO.setMem_point(rs.getString(11));
				memberDTO.setMem_paymethod(rs.getString(12));
				
				memberList.add(memberDTO);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		
		
		
		return memberList;
	}

	//페이징처리
	public int selectListCount() {
		int listCount = 0;
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}
		
		return listCount;
		
	}

	//장바구니 수량 변경
	public int qtyUpdate(String bk_qty, String bk_mem_id, String bk_order_num, String bk_pd_code) {
		int updateSuccess = 0;
		
		PreparedStatement pstmt = null;
		String sql = "UPDATE BASKET SET bk_qty=? WHERE bk_mem_id=? AND bk_order_num=? AND bk_pd_code=? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bk_qty);
			pstmt.setString(2, bk_mem_id);
			pstmt.setString(3, bk_order_num);
			pstmt.setString(4, bk_pd_code);
			updateSuccess=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateSuccess;
	}
}







