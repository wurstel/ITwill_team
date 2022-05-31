package svc;

import java.sql.Connection;

import dao.ProductDAO;
import vo.ProductDTO;
import static db.jdbcUtil.*;

public class ProductDetailService {

	public ProductDTO getArticle(String pd_code) {
		System.out.println("ProductDetailService - getArticle()");
//		System.out.println(pd_code);
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		ProductDTO article = null;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
		
		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : board_num    리턴타입 : BoardDTO(article)
		article = productDAO.selectArticleDetail(pd_code);
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return article;
	}

}
