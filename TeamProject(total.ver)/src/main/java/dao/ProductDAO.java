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
