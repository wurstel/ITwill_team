package svc;

import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.centerBoardDAO;


public class QNADeleteProService {

	public boolean removeArticle(int qna_num) {
		System.out.println("QNADeleteProService - removeArticle");
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		int deleteCount = boardDAO.deleteArticle(qna_num);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}

}
