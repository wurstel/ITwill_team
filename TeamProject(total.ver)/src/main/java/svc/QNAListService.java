package svc;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.centerBoardDAO;
import vo.QnaDTO;

public class QNAListService {

	public int getListCount() {
		System.out.println("QNAListService - getListCount");
		
		int listCount = 0;
				
		Connection con = getConnection(); // static import 적용되어 있을 경우
				
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
				
		boardDAO.setConnection(con);
				
		listCount = boardDAO.selectListCount();
				
		close(con);
				
		return listCount;
	}

	public ArrayList<QnaDTO> getArticleList(int pageNum, int listLimit) {
		System.out.println("QNAListService - getArticleList");
		ArrayList<QnaDTO> articleList = null;
		
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		articleList = boardDAO.selectArticleList(pageNum, listLimit);
		
		close(con);
		
		return articleList;

	}

}
