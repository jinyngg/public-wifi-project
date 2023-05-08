package com.zerobase.mission.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC 연결 관리 UTIL
 */
public class JDBCUtil {
	
	/**
	 * JDBC Connection을 가져오는 함수
	 * @return JDBC connection
	 */
	public static Connection getConnection() {
		try {
			// mySql 호출
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connection Success");
			return DriverManager.getConnection("jdbc:mysql://localhost/WIFI", "root", "root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * JDBC Connection을 종료하는 함수
	 * @param stmt
	 * @param conn
	 */
	public static void close(PreparedStatement stmt, Connection conn) {
		if (stmt != null) {
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("close Success");
				conn = null;
			}
		}
	}
	
	/**
	 * JDBC Connection을 종료하는 함수
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if (rs != null) {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		
		if (stmt != null) {
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
		
		if(rs == null && stmt == null && conn == null) {
			System.out.println("close Success");
		}
	}

}
