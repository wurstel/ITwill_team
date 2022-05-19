package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ProductDTO;

import static db.jdbcUtil.*;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();
	
	private ProductDAO() {}

	public static ProductDAO getInstance() {
		return instance;
	};
	// ---------------------------------------------------------------------------------------
	// 외부(Service 클래스)로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버 변수와 Setter 메서드 정의
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// ---------------------------------------------------------------------------------------
	
	
	public int insertProduct(ProductDTO productDTO) {
		System.out.println("ProductDAO - insertProduct()");
		
		// INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		
		try {
			
			String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productDTO.getPd_code());
			pstmt.setString(2, productDTO.getPd_name());
			pstmt.setString(3, productDTO.getPd_img());
			pstmt.setString(4, productDTO.getPd_price());
			pstmt.setString(5, productDTO.getPd_stock());
			pstmt.setString(6, productDTO.getPd_detail());
			pstmt.setString(7, productDTO.getPd_rdate());
			pstmt.setString(8, productDTO.getPd_state());
			pstmt.setString(9, productDTO.getPd_sales());
			pstmt.setString(10, productDTO.getPd_re_avg());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertArticle()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
		}
		
		// INSERT 작업 결과 리턴
		return insertCount;
	}

	public int selectListCount() {
		System.out.println("ProductDAO - selectListCount()");
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}


	// 게시물 목록을 조회하는 selectProductList() 메서드 정의
	public ArrayList<ProductDTO> selectProductList(int pageNum, int listLimit) {
		ArrayList<ProductDTO> productList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			// 게시물 목록 조회
			// => 답글에 대한 처리
			//    참조글번호(board_re_ref) 기준 내림차순, 
			//    순서번호(board_re_seq) 기준 오름차순 정렬
			// => 조회 시작 게시물 번호(startRow) 부터 목록의 게시물 수(listLimit) 만큼 조회
			String sql = "SELECT * FROM product "
//						+ "ORDER BY pd_rdate DESC"
						+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			// 전체 게시물을 저장할 ArrayList<BoardDTO> 타입 객체 생성
			productList = new ArrayList<ProductDTO>();
			
			// while 문을 사용하여 조회 결과에 대한 반복 작업 수행
			while(rs.next()) {
				// 1개 게시물 정보를 저장할 BoardDTO 객체 생성 후 조회 결과 저장
				ProductDTO productDTO = new ProductDTO();
				productDTO.setPd_code(rs.getString("pd_code"));
				productDTO.setPd_name(rs.getString("pd_name"));
				productDTO.setPd_img(rs.getString("pd_img"));
				productDTO.setPd_price(rs.getString("pd_price"));
				productDTO.setPd_stock(rs.getString("pd_stock"));
				productDTO.setPd_detail(rs.getString("pd_detail"));
				productDTO.setPd_rdate(rs.getString("pd_rdate"));
				productDTO.setPd_state(rs.getString("pd_state"));
				productDTO.setPd_sales(rs.getString("pd_sales"));
				productDTO.setPd_re_avg(rs.getString("pd_re_avg"));
				
				// 1개 게시물 정보를 다시 전체 게시물 정보 저장 객체(articleList)에 추가
				productList.add(productDTO);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectProductList()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}
		
		return productList;
	}

	
	
}







