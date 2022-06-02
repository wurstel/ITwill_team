package dao;

import vo.*;

import static db.jdbcUtil.*;

import java.sql.*;
import java.util.*;

public class ReviewDAO {
	private static ReviewDAO instance = new ReviewDAO();
	
	private ReviewDAO() {}
	
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}
	
	// 리뷰 갯수 카운트
	public int selectReviewListCount(String re_pd_code) {
		System.out.println("ReviewDAO - selectReviewListCount()");
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="SELECT COUNT(*) FROM review WHERE re_pd_code=(SELECT pd_code FROM product WHERE pd_code=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, re_pd_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectReviewListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}
		
		
		return listCount;
	}
	
	// 리뷰 평점순 나열
	public ArrayList<ReviewDTO> selectReviewHighList(int pageNum, int listLimit, String re_pd_code) {
		System.out.println("ReviewDAO - selectReviewHighList()");
		ArrayList<ReviewDTO> articleList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM review WHERE re_pd_code=(SELECT pd_code FROM product WHERE pd_code=?) ORDER BY CONVERT(re_score, SIGNED INTEGER) DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, re_pd_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			articleList = new ArrayList<ReviewDTO>();
			
			while(rs.next()) {
				ReviewDTO article = new ReviewDTO();
				article.setRe_num(rs.getString("re_num"));
				article.setRe_mem_id(rs.getString("re_mem_id"));
				article.setRe_img(rs.getString("re_img"));
				article.setRe_title(rs.getString("re_title"));
				article.setRe_comment(rs.getString("re_comment"));
				article.setRe_score(rs.getString("re_score"));
				articleList.add(article);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectReviewHighList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}
		
		return articleList;
	}
	
	// 리뷰 최근순 나열
	public ArrayList<ReviewDTO> selectReviewRecentList(int pageNum, int listLimit, String re_pd_code) {
		System.out.println("ReviewDAO - selectReviewHighList()");
		ArrayList<ReviewDTO> articleList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM review WHERE re_pd_code=(SELECT pd_code FROM product WHERE pd_code=?) ORDER BY DATE_FORMAT(re_date, '%y%m%d') DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, re_pd_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			
			rs = pstmt.executeQuery();
			
			articleList = new ArrayList<ReviewDTO>();
			
			while(rs.next()) {
				ReviewDTO article = new ReviewDTO();
				article.setRe_num(rs.getString("re_num"));
				article.setRe_mem_id(rs.getString("re_mem_id"));
				article.setRe_img(rs.getString("re_img"));
				article.setRe_title(rs.getString("re_title"));
				article.setRe_comment(rs.getString("re_comment"));
				article.setRe_score(rs.getString("re_score"));
				articleList.add(article);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectReviewHighList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}
		
		return articleList;
	}

	


}
