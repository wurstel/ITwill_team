package svc;

import static db.jdbcUtil.*;

import java.sql.*;
import java.util.*;

import dao.*;
import vo.*;

public class ReviewListService {
	
	// 리뷰 갯수 판별
	public int getListCount(String re_pd_code) {
		System.out.println("ReviewListService - getListCount");
		int listCount = 0;
		
		Connection con = getConnection(); 
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setCon(con);
		
		listCount = reviewDAO.selectReviewListCount(re_pd_code);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<ReviewDTO> getReviewList(int pageNum, int listLimit, String re_pd_code) {
		System.out.println("ReviewListService - getReviewList()");
		ArrayList<ReviewDTO> articleList = null;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setCon(con);
		
		articleList = reviewDAO.selectReviewHighList(pageNum, listLimit, re_pd_code);
		
		close(con);
		
		return articleList;
	}

}
