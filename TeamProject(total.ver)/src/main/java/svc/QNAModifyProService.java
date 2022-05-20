package svc;

import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.centerBoardDAO;
import vo.QnaDTO;

public class QNAModifyProService {

	public boolean isArticleWriter(int qna_num, String qna_pass) {
		System.out.println("QNAModifyProService - isArticleWriter");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		// BoardDAO 의 isArticleWriter() 메서드를 호출하여 패스워드 판별
		// => 파라미터 : 글번호(board_num), 패스워드(board_pass)
		//    리턴타입 : boolean(isArticleWriter)
		isArticleWriter = boardDAO.isArticleWriter(qna_num, qna_pass);
		
		close(con);
		
		return isArticleWriter;
	}

	public boolean modifyArticle(QnaDTO article) {
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		// BoardDAO 의 updateArticle() 메서드 호출하여 글 수정 작업 수행
		// => 파라미터 : BoardDTO 객체    리턴타입 : int(updateCount)
		int updateCount = boardDAO.updateArticle(article);
		
		// updateCount 가 0보다 크면 commit, 아니면 rollback 작업 수행
		if(updateCount > 0) {
			commit(con);
			// isModifySuccess 를 true 로 변경
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isModifySuccess;
	}

}
