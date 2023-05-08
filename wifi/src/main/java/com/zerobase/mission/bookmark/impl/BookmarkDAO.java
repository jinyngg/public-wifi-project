package com.zerobase.mission.bookmark.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zerobase.mission.bookmark.Bookmark;
import com.zerobase.mission.bookmarkGroup.BookmarkGroup;
import com.zerobase.mission.common.JDBCUtil;
import com.zerobase.mission.wifi.Wifi;

public class BookmarkDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL DML
	private final String BOOKMARK_INSERT = 
			"INSERT"
			+ " INTO BOOKMARK ("
			+ "BOOKMARK_NO"
			+ ", BOOKMARKGROUP_NO"
			+ ", BOOKMARKGROUP_NM"
			+ ", WIFI_MGR_NO"
			+ ", WIFI_MAIN_NM"
			+ ", REGDATE"
			+ ")"
			+ " VALUES ((SELECT NO FROM (SELECT IFNULL(MAX(BOOKMARK_NO), 0) + 1 AS NO FROM BOOKMARK) a), ?, ?, ?, ?, now())";
	
	private final String BOOKMARK_LIST =
			"SELECT BOOKMARK_NO, BOOKMARKGROUP_NO, BOOKMARKGROUP_NM, WIFI_MGR_NO, WIFI_MAIN_NM, REGDATE, DEL_YN"
			+ " FROM BOOKMARK"
			+ " WHERE DEL_YN = 'N'"
			+ " ORDER BY BOOKMARK_NO ASC";
	
	private final String BOOKMARK_DELETE = 
			"UPDATE BOOKMARK"
			+ " SET DEL_YN = 'Y' "
			+ " WHERE BOOKMARK_NO = ?";
	
	
	private static BookmarkDAO bookmarkDAO;
	
	/**
	 * 최초 한번 new 연산자를 통하여 메모리에 할당하여 싱글톤으로 bookmarkDAO 생성
	 * @return historyDAO
	 */
	public static BookmarkDAO getInstance() {
		if(bookmarkDAO == null){ 
			bookmarkDAO = new BookmarkDAO();
		}		
		return bookmarkDAO;
	}	
	
	/**
	 * 북마크 추가
	 * @param wifi
	 * @param bookmarkGroup
	 */
	public void insertBookmark(int bookmarkGroupId, String bookmarkGroupNm, String wifiMgrNo, String wifiMainNm) {
		
		System.out.println("===> JDBC INSERT(BOOKMARK) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARK_INSERT);
			stmt.setInt(1, bookmarkGroupId);
			stmt.setString(2, bookmarkGroupNm);
			stmt.setString(3, wifiMgrNo);
			stmt.setString(4, wifiMainNm);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 삭제되지 않은 전체 북마크 조회
	 * @return
	 */
	public List<Bookmark> getBookmarkList() {
		
		System.out.println("===> JDBC getBookmarkList() 기능 처리");
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARK_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Bookmark bookmark = new Bookmark();
				bookmark.setBookmarkId(rs.getInt("BOOKMARK_NO"));
				bookmark.setBookmarkGroupId(rs.getInt("BOOKMARKGROUP_NO"));
				bookmark.setBookmarkGroupNm(rs.getString("BOOKMARKGROUP_NM"));
				bookmark.setWifiMgrNo(rs.getString("WIFI_MGR_NO"));
				bookmark.setWifiMainNm(rs.getString("WIFI_MAIN_NM"));;
				bookmark.setRegDate(rs.getTimestamp("REGDATE"));
				bookmark.setDelYN(rs.getString("DEL_YN"));         
				bookmarkList.add(bookmark);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bookmarkList;
	}
	
	/**
	 * 북마크 삭제 플래그 'Y'로 변경
	 * @param bookmarkId
	 */
	public void delete(int bookmarkId) {
		
		System.out.println("===> JDBC DELETE(bookmarkId) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARK_DELETE);
			stmt.setInt(1, bookmarkId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
