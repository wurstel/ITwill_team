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
		
		// 3. PrductDAO 클래스로부터 ProductDAO 인스턴스 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
		
		// 4. ProductDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
		
		// 5. ProductDAO 객체의 selectListCount() 메서드를 호출하여 총 게시물 수 조회
		listCount = productDAO.selectListCount();
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return listCount;
	}

	public ArrayList<ProductDTO> getProductList(int pageNum, int listLimit) {
		System.out.println("ProductListService - getProductList()");
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		ArrayList<ProductDTO> productList = null;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. ProductDAO 클래스로부터 ProductDAO 인스턴스 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
		
		// 4. ProductDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
		
		// 5. ProductDAO 객체의 selectProductList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : pageNum, listLimit    리턴타입 : ArrayList<ProductDTO>
		productList = productDAO.selectProductList(pageNum, listLimit);
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return productList;
	}

}











