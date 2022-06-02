package svc;

import java.sql.*;

import dao.*;
import vo.*;
import static db.jdbcUtil.*;
public class ReviewDetailService {

	public ReviewDTO getReviewList(String re_pd_code) {
		System.out.println("ReviewDetailService - getReview");
		
		ReviewDTO review = null;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setCon(con);
		
//		review = reviewDAO.selectHighScoreReview(re_pd_code);
		
		close(con);
		
		return review;
	}

}
