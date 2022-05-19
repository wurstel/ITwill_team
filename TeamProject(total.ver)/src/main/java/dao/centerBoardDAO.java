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
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM qna "
						+ "ORDER BY qna_re_ref DESC, qna_re_seq ASC "
						+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			articleList = new ArrayList<QnaDTO>();
			
			while(rs.next()) {
				QnaDTO article = new QnaDTO();
				article.setQna_num(rs.getInt("qna_num"));
				article.setQna_mem_id(rs.getString("qna_mem_id"));
				article.setQna_title(rs.getString("qna_title"));
				
				articleList.add(article);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectArticleList()");
			e.printStackTrace();
		} finally {
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
			String sql = "SELECT MAX(qna_num) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			close(pstmt);
			
			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 새 글 번호
			pstmt.setString(2, qna.getQna_mem_id());
			pstmt.setString(3, qna.getQna_pass());
			pstmt.setString(4, qna.getQna_title());
			pstmt.setString(5, qna.getQna_content());
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

	public QnaDTO selectArticle(int qna_num) {
		System.out.println("selectArticle");
		
		QnaDTO article = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM qna WHERE qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new QnaDTO();
				article.setQna_num(rs.getInt("qna_num"));
				article.setQna_mem_id(rs.getString("qna_mem_id"));
				article.setQna_pass(rs.getString("qna_pass"));
				article.setQna_title(rs.getString("qna_title"));
				article.setQna_content(rs.getString("qna_content"));
				article.setQna_re_ref(rs.getInt("qna_re_ref"));
				article.setQna_re_lev(rs.getInt("qna_re_lev"));
				article.setQna_re_seq(rs.getInt("qna_re_seq"));
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return article;
	}

	public int insertReplyArticle(QnaDTO article) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		
		int num = 1; 
		
		try {
			String sql = "SELECT MAX(qna_num) FROM qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			sql = "UPDATE qna SET qna_re_seq = qna_re_seq+1 "
					+ "WHERE qna_re_ref=? AND qna_re_seq>?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, article.getQna_re_ref()); // 참조글번호
			pstmt2.setInt(2, article.getQna_re_seq()); // 순서번호
			pstmt2.executeUpdate();
			
			sql = "INSERT INTO qna VALUES (?,?,?,?,?,?,?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, num); // 새 글 번호
			pstmt2.setString(2, article.getQna_title());
			pstmt2.setString(3, article.getQna_content());
			pstmt2.setInt(6, article.getQna_re_ref());
			pstmt2.setInt(7, article.getQna_re_lev() + 1);
			pstmt2.setInt(8, article.getQna_re_seq() + 1);
			// -----------------------------------------------------------------------------			
			
			insertCount = pstmt2.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertReplyArticle()");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(pstmt2);
		}
		
		return insertCount;
	}

	public int deleteArticle(int qna_num) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM qna WHERE qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - deleteArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	
	}

	public boolean isArticleWriter(int qna_num, String qna_pass) {
		boolean isArticleWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 글번호와 패스워드가 모두 일치하는 게시물 조회
			String sql = "SELECT * FROM qna WHERE qna_num=? AND qna_pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.setString(2, qna_pass);
			
			rs = pstmt.executeQuery();
		
			// 조회결과(rs.next()) 가 있을 경우 isArticleWriter 를 true 로 변경
			if(rs.next()) {
				isArticleWriter = true;
			}
			
	    } catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - isArticleWriter()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return isArticleWriter;
	}

	public int updateArticle(QnaDTO article) {
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE qna "
					+ "SET qna_mem_id=?,qna_title=?,qna_content=? "
					+ "WHERE qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getQna_mem_id());
			pstmt.setString(2, article.getQna_title());
			pstmt.setString(3, article.getQna_content());
			pstmt.setInt(4, article.getQna_num());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	
}
