package com.zerobase.mission.history.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zerobase.mission.common.JDBCUtil;
import com.zerobase.mission.history.History;

public class HistoryDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL DML
	private final String HISTORY_INSERT = 
			"INSERT"
			+ " INTO HISTORY ("
			+ "HISTORY_NO"
			+ ", LAT"
			+ ", LNT"
			+ ", REGDATE"
			+ ")"
			+ " VALUES ((SELECT NO FROM (SELECT IFNULL(MAX(HISTORY_NO), 0) + 1 AS NO FROM HISTORY) a), ?, ?, now())";
	
	private final String HISTORY_DELETE = 
			"UPDATE HISTORY"
			+ " SET DEL_YN = 'Y' "
			+ " WHERE HISTORY_NO = ?";
	
	private final String HISTORY_LIST = 
			"SELECT HISTORY_NO, LAT, LNT, REGDATE, DEL_YN"
			+ " FROM HISTORY"
			+ " WHERE DEL_YN = 'N'"
			+ " ORDER BY HISTORY_NO DESC";
	
	private static HistoryDAO historyDAO;
	
	/**
	 * 최초 한번 new 연산자를 통하여 메모리에 할당하여 싱글톤으로 historyDAO 생성
	 * @return historyDAO
	 */
	public static HistoryDAO getInstance() {
		if(historyDAO == null){ 
			historyDAO = new HistoryDAO();
		}		
		return historyDAO;
	}	
	
	/**
	 * 위치 히스토리 목록 삽입
	 * @param history
	 */
	public void insertHistory(History history) {
		
		System.out.println("===> JDBC INSERT(HISTORY) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(HISTORY_INSERT);
			stmt.setDouble(1, history.getLat());
			stmt.setDouble(2, history.getLnt());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 위치 히스토리 목록 삭제 플래그 'Y'로 변경
	 * @param historyId
	 */
	public void delete(int HistoryId) {
		
		System.out.println("===> JDBC DELETE(HistoryId) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(HISTORY_DELETE);
			stmt.setInt(1, HistoryId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 삭제되지 않은 전체 위치 히스토리 목록 조회
	 * @return
	 */
	public List<History> getHistoryList() {
		
		System.out.println("===> JDBC getHistoryList() 기능 처리");
		List<History> historyList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(HISTORY_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				History history = new History();
				history.setHistoryId(rs.getInt("HISTORY_NO"));
				history.setLat(rs.getFloat("LAT"));
				history.setLnt(rs.getFloat("LNT"));
				history.setRegDate(rs.getTimestamp("REGDATE"));
				history.setDelYN(rs.getString("DEL_YN"));
				
				historyList.add(history);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return historyList;
	}
}
