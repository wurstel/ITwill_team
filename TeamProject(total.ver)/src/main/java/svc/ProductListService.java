package svc;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.ProductDTO;

public class ProductListService {
	public int getListCount() {
		System.out.println("ProductListService - getListCount()");
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		int listCount = 0;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
		
		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectListCount() 메서드를 호출하여 총 게시물 수 조회
		listCount = productDAO.selectListCount();
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return listCount;
	}
	
	public ArrayList<ProductDTO> getArticleList(int pageNum, int listLimit){
		System.out.println("ProductListService - getArticleList");
		ArrayList<ProductDTO> articleList = null;
		
		// Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// BoardDAO 객체 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
				
		// BoardDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
				
		// BoardDAO 객체의 selectArticleList 메서드를 호출하여 총 게시물 수 조회
		articleList = productDAO.selectArticleList(pageNum, listLimit);
		
		// Connection 객체 반환 - 공통
		close(con);
		
		return articleList;
	}
	
	public ArrayList<ProductDTO> getArticleLowList(int pageNum, int listLimit){
		System.out.println("ProductListService - getArticleLowList");
		ArrayList<ProductDTO> articleList = null;
		
		// Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// BoardDAO 객체 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
				
		// BoardDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
				
		// BoardDAO 객체의 selectArticleList 메서드를 호출하여 총 게시물 수 조회
		articleList = productDAO.selectArticleLowList(pageNum, listLimit);
		
		// Connection 객체 반환 - 공통
		close(con);
		
		return articleList;
	}
	
	
	public ArrayList<ProductDTO> getArticleAvgList(int pageNum, int listLimit){
		System.out.println("ProductListService - getArticleLowList");
		ArrayList<ProductDTO> articleList = null;
		
		// Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// BoardDAO 객체 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
				
		// BoardDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
				
		// BoardDAO 객체의 selectArticleList 메서드를 호출하여 총 게시물 수 조회
		articleList = productDAO.selectArticleAvgList(pageNum, listLimit);
		
		// Connection 객체 반환 - 공통
		close(con);
		
		return articleList;
	}
	
	public ArrayList<ProductDTO> getArticleHeiList(int pageNum, int listLimit){
		System.out.println("ProductListService - getArticleLowList");
		ArrayList<ProductDTO> articleList = null;
		
		// Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// BoardDAO 객체 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
				
		// BoardDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
				
		// BoardDAO 객체의 selectArticleList 메서드를 호출하여 총 게시물 수 조회
		articleList = productDAO.selectArticleHeiList(pageNum, listLimit);
		
		// Connection 객체 반환 - 공통
		close(con);
		
		return articleList;
	}
}
