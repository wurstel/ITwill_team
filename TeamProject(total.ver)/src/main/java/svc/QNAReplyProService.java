package svc;


import java.sql.Connection;

import static db.jdbcUtil.*;
import dao.centerBoardDAO;
import vo.QnaDTO;

public class QNAReplyProService {

	public boolean replyArticle(QnaDTO article) {
		System.out.println("QNAReplyProService - replyArticle");
		boolean isReplySuccess = false;
		
		Connection con = getConnection();
		
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		// BoardDAO 의 insertReplyArticle() 메서드 호출하여 답글 등록 작업 수행
		// => 파라미터 : BoardDTO 객체    리턴타입 : int(insertCount)
		int insertCount = boardDAO.insertReplyArticle(article);
		
		// insertCount 가 0보다 크면 commit, 아니면 rollback 작업 수행
		if(insertCount > 0) {
			commit(con);
			// isReplySuccess 를 true 로 변경
			isReplySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isReplySuccess;

	}


}
