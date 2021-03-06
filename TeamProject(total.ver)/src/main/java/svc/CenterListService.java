package svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.centerBoardDAO;
import vo.QnaDTO;

public class CenterListService {
	public int getListCount() {
		System.out.println("CenterListService - getListCount");
		int listCount = 0;
		
		Connection con = getConnection();
		
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		boardDAO.setConnection(con);
		
		listCount = boardDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}
	public ArrayList<QnaDTO> getArticleList(int pageNum, int listLimit){
		System.out.println("CenterListService - getArticleList");
		ArrayList<QnaDTO> articleList = null;
		
		Connection con = getConnection();
				
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
				
		boardDAO.setConnection(con);
				
		articleList = boardDAO.selectArticleList(pageNum, listLimit);
		
		close(con);
		
		return articleList;
	}

}
