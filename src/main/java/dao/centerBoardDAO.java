package dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.QnaDTO;


public class centerBoardDAO {
	private static centerBoardDAO instance = new centerBoardDAO();
	
	private centerBoardDAO() {}

	public static centerBoardDAO getInstance() {
		return instance;
	};
	// ---------------------------------------------------------------------------------------
	// 외부(Service 클래스)로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버 변수와 Setter 메서드 정의
	Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectListCount() {
		System.out.println("centerBoardDAO - selectListCount()");
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectListCount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	public ArrayList<QnaDTO> selectArticleList(int pageNum, int listLimit) {
		System.out.println("centerBoardDAO - selectArticleList");
		ArrayList<QnaDTO> articleList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			// 게시물 목록 조회
			// => 답글에 대한 처리
			//    참조글번호(board_re_ref) 기준 내림차순, 
			//    순서번호(board_re_seq) 기준 오름차순 정렬
			// => 조회 시작 게시물 번호(startRow) 부터 목록의 게시물 수(listLimit) 만큼 조회
			String sql = "SELECT * FROM qna "
						+ "ORDER BY qna_re_ref DESC, qna_re_seq ASC "
						+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			// 전체 게시물을 저장할 ArrayList<BoardDTO> 타입 객체 생성
			articleList = new ArrayList<QnaDTO>();
			
			// while 문을 사용하여 조회 결과에 대한 반복 작업 수행
			while(rs.next()) {
				// 1개 게시물 정보를 저장할 BoardDTO 객체 생성 후 조회 결과 저장
				QnaDTO article = new QnaDTO();
				article.setQna_idx(rs.getInt("qna_idx"));
				article.setQna_subject(rs.getString("qna_subject"));
				article.setQna_date(rs.getDate("qna_date"));
				
				// 1개 게시물 정보를 다시 전체 게시물 정보 저장 객체(articleList)에 추가
				articleList.add(article);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectArticleList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}
		
		return articleList;
	}

	public int insertArticle(QnaDTO qna) {
		System.out.println("insertArticle");
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 1; // 새 글 번호를 저장할 변수 
		
		try {
			String sql = "SELECT MAX(qna_idx) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			close(pstmt);
			
			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 새 글 번호
			pstmt.setString(2, qna.getQna_subject());
			pstmt.setString(3, qna.getQna_content());
			pstmt.setString(4, qna.getQna_file());
			pstmt.setString(5, qna.getQna_real_file());
			// 답글에 사용될 참조글 번호(board_re_ref)는 새 글이므로 새 글 번호와 동일하게 지정
			pstmt.setInt(6, num); // board_re_ref
			pstmt.setInt(7, 0); // board_re_lev
			pstmt.setInt(8, 0); // board_re_seq
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}
}
