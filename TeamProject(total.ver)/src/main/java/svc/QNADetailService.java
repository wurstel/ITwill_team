package svc;


import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.centerBoardDAO;
import vo.QnaDTO;

public class QNADetailService {

	public QnaDTO getArticle(int qna_num) {
		System.out.println("QNADetailService - getArticle");
		QnaDTO article = null;
		
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		article = boardDAO.selectArticle(qna_num);
		
		close(con);
		
		return article;
	}


}
