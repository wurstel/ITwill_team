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
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectListCount() {
		System.out.println("BoardDAO - selectListCount()");
		
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
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}
	
	// 최신순으로 기본 정렬
	public ArrayList<ProductDTO> selectArticleList(int pageNum, int listLimit) {
		System.out.println("ProductDAO - selectArticleList");
		ArrayList<ProductDTO> articleList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum - 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM product "
						+ "ORDER BY DATE_FORMAT(pd_rdate, '%y%m%d') DESC "
						+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			articleList = new ArrayList<ProductDTO>();
			
			while(rs.next()) {
				ProductDTO article = new ProductDTO();
				article.setPd_name(rs.getString("pd_name"));
				article.setPd_price(rs.getString("pd_price"));
				article.setPd_img(rs.getString("pd_img"));
				article.setPd_code(rs.getString("pd_code"));
				
				articleList.add(article);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectArticleList()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return articleList;
	}
	
		// 가격낮은순으로 기본 정렬
		public ArrayList<ProductDTO> selectArticleLowList(int pageNum, int listLimit) {
			System.out.println("ProductDAO - selectArticleLowList");
			ArrayList<ProductDTO> articleList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (pageNum - 1) * listLimit;
			
			try {
				String sql = "SELECT * FROM product "
							+ "ORDER BY CONVERT(pd_price, SIGNED INTEGER) ASC "
							+ "LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				articleList = new ArrayList<ProductDTO>();
				
				while(rs.next()) {
					ProductDTO article = new ProductDTO();
					article.setPd_name(rs.getString("pd_name"));
					article.setPd_price(rs.getString("pd_price"));
					article.setPd_img(rs.getString("pd_img"));
					article.setPd_code(rs.getString("pd_code"));
					
					articleList.add(article);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - selectArticleList()");
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return articleList;
		}
		
		// 리뷰 평점순으로 기본 정렬
		public ArrayList<ProductDTO> selectArticleAvgList(int pageNum, int listLimit) {
			System.out.println("ProductDAO - selectArticleLowList");
			ArrayList<ProductDTO> articleList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (pageNum - 1) * listLimit;
			
			try {
				String sql = "SELECT * FROM product "
							+ "ORDER BY CONVERT(pd_re_avg, SIGNED INTEGER) DESC "
							+ "LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				articleList = new ArrayList<ProductDTO>();
				
				while(rs.next()) {
					ProductDTO article = new ProductDTO();
					article.setPd_name(rs.getString("pd_name"));
					article.setPd_price(rs.getString("pd_price"));
					article.setPd_img(rs.getString("pd_img"));
					article.setPd_code(rs.getString("pd_code"));
					
					articleList.add(article);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - selectArticleList()");
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return articleList;
		}
		
		// 가격높은순으로 기본 정렬
		public ArrayList<ProductDTO> selectArticleHeiList(int pageNum, int listLimit) {
			System.out.println("ProductDAO - selectArticleLowList");
			ArrayList<ProductDTO> articleList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (pageNum - 1) * listLimit;
			
			try {
				String sql = "SELECT * FROM product "
							+ "ORDER BY CONVERT(pd_price, SIGNED INTEGER) DESC "
							+ "LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				articleList = new ArrayList<ProductDTO>();
				
				while(rs.next()) {
					ProductDTO article = new ProductDTO();
					article.setPd_name(rs.getString("pd_name"));
					article.setPd_price(rs.getString("pd_price"));
					article.setPd_img(rs.getString("pd_img"));
					article.setPd_code(rs.getString("pd_code"));
					
					articleList.add(article);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - selectArticleList()");
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return articleList;
		}
		
		// 상품 상세정보
		public ProductDTO selectArticleDetail(String pd_code) {
			ProductDTO article = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
//			System.out.println(pd_code);
			
			try {
				String sql = "SELECT * FROM product WHERE pd_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pd_code);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					article = new ProductDTO();
					article.setPd_code(rs.getString("pd_code"));
					article.setPd_name(rs.getString("pd_name"));
					article.setPd_img(rs.getString("pd_img"));
					article.setPd_price(rs.getString("pd_price"));
					article.setPd_stock(rs.getString("pd_stock"));
					article.setPd_detail(rs.getString("pd_detail"));
					article.setPd_rdate(rs.getString("pd_rdate"));
					article.setPd_state(rs.getString("pd_state"));
					article.setPd_sales(rs.getString("pd_sales"));
					article.setPd_re_avg(rs.getString("pd_re_avg"));
					
					System.out.println(article);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - selectArticleDetail()");
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return article;
		}

		public int selectSearchListCount(String search) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT COUNT(pd_code) FROM product WHERE pd_name LIKE ?";
				pstmt = con.prepareStatement(sql);
				// 검색어 생성을 위해서는 검색 키워드 앞뒤로 "%" 문자열 결합 필요
				pstmt.setString(1, "%" + search + "%");
				rs = pstmt.executeQuery();
				
				// 조회된 결과값의 첫번째 값(1번 인덱스)을 listCount 변수에 저장
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - selectListCount()");
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}

		public ArrayList<ProductDTO> selectSearchProductList(int pageNum, int listLimit, String search) {
			ArrayList<ProductDTO> productList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				// 현재 페이지에서 불러올 목록(레코드)의 첫번째(시작) 행번호 계산
				int startRow = (pageNum - 1) * listLimit;
				
				// 3단계. SQL 구문 작성 및 전달
				// 검색어에 해당하는 board 테이블의 모든 레코드 조회(글번호(num) 기준으로 내림차순 정렬)
				String sql = "SELECT * FROM product "
						+ "WHERE pd_name LIKE ? "
						+ "ORDER BY num DESC LIMIT ?,?";
				// => 목록갯수는 파라미터로 전달받은 listLimit 값 사용
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + search + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				
				// 4단계. SQL 구문 실행 및 결과 처리
				rs = pstmt.executeQuery();
				
				// 전체 레코드를 저장할 ArrayList<BoardBean> 객체 생성
				// => 주의! 반복문 시작 전에 미리 생성해야함
				productList = new ArrayList<ProductDTO>();
				
				// 다음레코드가 존재할 동안 반복하면서
				// 1개 레코드 정보를 BoardBean 객체에 저장 후
				// 다시 BoardBean 객체를 전체 레코드 저장하는 ArrayList<BoardBean> 객체에 추가
				while(rs.next()) {
					// 1개 레코드를 저장할 BoardBean 객체 생성
					ProductDTO product = new ProductDTO();
					// BoardBean 객체에 조회된 1개 레코드 정보를 모두 저장
					product.setPd_code(rs.getString("pd_code"));
					product.setPd_name(rs.getString("pd_name"));
					product.setPd_img(rs.getString("pd_img"));
					product.setPd_price(rs.getString("pd_price"));
					product.setPd_stock(rs.getString("pd_stock"));
					product.setPd_detail(rs.getString("pd_detail"));
					product.setPd_rdate(rs.getString("pd_rdate"));
					product.setPd_state(rs.getString("pd_state"));
					product.setPd_sales(rs.getString("pd_sales"));
					product.setPd_re_avg(rs.getString("pd_re_avg"));
					
					// 전체 레코드를 저장하는 ArrayList 객체에 1개 레코드가 저장된 BoardBean 객체 추가
					productList.add(product);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - selectBoardList()");
			} finally {
				close(rs);
				close(pstmt);
			}
//			System.out.println(productList);
			return productList;
		}

		//상품등록
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


}
