package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MemberDTO;

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




}







