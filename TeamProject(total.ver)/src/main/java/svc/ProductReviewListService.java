package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.ReviewDTO;

public class ProductReviewListService {
	// 리뷰 갯수 판별
	public int getListCount(String re_pd_code) {
		System.out.println("ProductReviewListService - getListCount");
		int listCount = 0;

		Connection con = getConnection();

		ReviewDAO reviewDAO = ReviewDAO.getInstance();

		reviewDAO.setCon(con);

		listCount = reviewDAO.selectReviewListCount(re_pd_code);

		close(con);

		return listCount;
	}
	
	// 평점 높은순
	public ArrayList<ReviewDTO> getReviewHighList(int pageNum, int listLimit, String re_pd_code) {
		System.out.println("ProductReviewListService - getReviewHighList()");
		ArrayList<ReviewDTO> articleList = null;

		Connection con = getConnection();

		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setCon(con);

		articleList = reviewDAO.selectReviewHighList(pageNum, listLimit, re_pd_code);

		close(con);

		return articleList;
	}

	// 최신 등록순
	public ArrayList<ReviewDTO> getReviewRecentList(int pageNum, int listLimit, String re_pd_code) {
		System.out.println("ProductReviewListService - getReviewRecentList()");
		ArrayList<ReviewDTO> articleList = null;

		Connection con = getConnection();

		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setCon(con);

		articleList = reviewDAO.selectReviewRecentList(pageNum, listLimit, re_pd_code);

		close(con);

		return articleList;
	}

}
