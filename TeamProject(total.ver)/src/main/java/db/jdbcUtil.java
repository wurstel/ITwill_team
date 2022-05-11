package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class jdbcUtil {

	//db연결작업
	public static Connection getConnection() {
		
/*
 * DataSource객체로부터 미리 생성된 Connection 가져오기
 * 계정명과 패스워드를 getConnection()의 파라미터로 전달도 가능(설정하지 않았을때)
 */
		Connection con = null;
		try {
			//DBCP로부터 Connection 객체 가져오기
			//1.context.xml 파일의 context 태그부분 가져오기
			Context initCtx = new InitialContext();
/*
 * 2.생성된 객체로부터 Resource부분 가져오기
 * ->Context.lookup() 호출,찾아올 리소스 지정(name)
 * ->리턴되는 object타입을 context로 다운캐스팅하여 저장, 파라미터로 java:comp/env
 * */
//		Context envCtx = (Context)initCtx.lookup("java:comp/env");
/*
 * 3.Resource 태그 내의 name속성을 가져오기
 * ->Context.lookup()호출(envCtx)
 */
//		DataSource ds = (DataSource)envCtx.lookup("jdbc/MYSQL");
			DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/MySQL");
			con = ds.getConnection();
			
			con.setAutoCommit(false);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return con;
		
	}	//getconnection() 끝
	
	//close 작업(자원 반환)
	public static void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	//close()끝
	
	
	//commit(),rollback()
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
