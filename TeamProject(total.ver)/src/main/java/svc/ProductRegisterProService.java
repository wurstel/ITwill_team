package svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.ProductDAO;
import dao.centerBoardDAO;
import vo.ProductDTO;
import vo.QnaDTO;

public class ProductRegisterProService {

	public boolean registProduct(ProductDTO productDTO) {
		System.out.println("ProductRegisterProService - registProduct");
		
		boolean isRegisterSuccess = false;
		// Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// BoardDAO 객체 가져오기 - 공통
		ProductDAO productDAO = ProductDAO.getInstance();
		
		// BoardDAO 객체에 Connection 객체 전달하기 - 공통
		productDAO.setConnection(con);
				
		int insertCount = productDAO.insertProduct(productDTO);
		// Connection 객체 반환 - 공통
		
		// 6. 리턴받은 작업 수행 결과를 통해 판별 후 처리 작업 수행(트랜잭션 처리)
		if(insertCount > 0) { // 작업 성공 시
			// 트랜잭션 적용을 위해 JdbcUtil 클래스의 commit() 메서드를 호출하여 commit 작업 수행
			commit(con);
			
			// 작업 처리 결과를 성공으로 표시하기 위해 isWriteSuccess 를 true 로 변경
			isRegisterSuccess = true;
		} else { // 작업 실패 시
			// 트랜잭션 취소를 위해 JdbcUtil 클래스의 rollback() 메서드를 호출하여 rollback 작업 수행
			rollback(con);
		}
		close(con);
		return isRegisterSuccess;
	}

}
