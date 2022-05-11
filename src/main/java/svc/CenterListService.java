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
		
		// Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// BoardDAO 객체 가져오기 - 공통
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
		
		// BoardDAO 객체에 Connection 객체 전달하기 - 공통
		boardDAO.setConnection(con);
		
		// BoardDAO 객체의 selectListCount 메서드를 호출하여 총 게시물 수 조회
		listCount = boardDAO.selectListCount();
		// Connection 객체 반환 - 공통
		close(con);
		
		return listCount;
	}
	public ArrayList<QnaDTO> getArticleList(int pageNum, int listLimit){
		System.out.println("CenterListService - getArticleList");
		ArrayList<QnaDTO> articleList = null;
		
		// Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// BoardDAO 객체 가져오기 - 공통
		centerBoardDAO boardDAO = centerBoardDAO.getInstance();
				
		// BoardDAO 객체에 Connection 객체 전달하기 - 공통
		boardDAO.setConnection(con);
				
		// BoardDAO 객체의 selectArticleList 메서드를 호출하여 총 게시물 수 조회
		articleList = boardDAO.selectArticleList(pageNum, listLimit);
		
		// Connection 객체 반환 - 공통
		close(con);
		
		return articleList;
	}

}
