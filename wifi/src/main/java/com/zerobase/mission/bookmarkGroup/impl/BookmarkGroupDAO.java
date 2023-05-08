package com.zerobase.mission.bookmarkGroup.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zerobase.mission.bookmarkGroup.BookmarkGroup;
import com.zerobase.mission.common.JDBCUtil;

public class BookmarkGroupDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL DML
	private final String BOOKMARKGROUP_INSERT = 
			"INSERT"
			+ " INTO BOOKMARKGROUP ("
			+ "BOOKMARK_NO"
			+ ", BOOKMARK_NM"
			+ ", BOOKMARK_ORDER"
			+ ", REGDATE"
			+ ")"
			+ " VALUES ((SELECT NO FROM (SELECT IFNULL(MAX(BOOKMARK_NO), 0) + 1 AS NO FROM BOOKMARKGROUP) a), ?, ?, now())";
	
	private final String BOOKMARKGROUP_LIST =
			"SELECT BOOKMARK_NO, BOOKMARK_NM, BOOKMARK_ORDER, REGDATE, UPDDATE, DEL_YN"
			+ " FROM BOOKMARKGROUP"
			+ " WHERE DEL_YN = 'N'"
			+ " ORDER BY BOOKMARK_ORDER ASC";
	
	private final String BOOKMARKGROUP_DELETE = 
			"UPDATE BOOKMARKGROUP"
			+ " SET DEL_YN = 'Y' "
			+ " WHERE BOOKMARK_NO = ?";
	
	private final String BOOKMARKGROUP_UPDATE_NAME = 
			"UPDATE BOOKMARKGROUP"
			+ " SET BOOKMARK_NM = ?"
			+ ", UPDDATE = now()"
			+ " WHERE BOOKMARK_NO = ?";
	
	private final String VALIDATE_BOOKMARK_ORDER =
			"SELECT BOOKMARK_ORDER"
			+ " FROM BOOKMARKGROUP"
			+ " WHERE BOOKMARK_ORDER = ?";
	
	private final String BOOKMARKGROUP_DETAIL =
			"SELECT BOOKMARK_NO, BOOKMARK_NM, BOOKMARK_ORDER, REGDATE, UPDDATE, DEL_YN"
			+ " FROM BOOKMARKGROUP"
			+ " WHERE BOOKMARK_NO = ?";
	
	private static BookmarkGroupDAO bookmarkGroupDAO;
	
	/**
	 * 최초 한번 new 연산자를 통하여 메모리에 할당하여 싱글톤으로 bookmarkGroupDAO 생성
	 * @return historyDAO
	 */
	public static BookmarkGroupDAO getInstance() {
		if(bookmarkGroupDAO == null){ 
			bookmarkGroupDAO = new BookmarkGroupDAO();
		}		
		return bookmarkGroupDAO;
	}	
	
	/**
	 * 북마크 그룹 추가시 입력한 순서값이 사용 가능한지 확인
	 * @param bookmarkOrder
	 * @return
	 */
	public boolean validateBookmarkOrder(int bookmarkOrder) {
		
		System.out.println("===> JDBC VALIDATE_ORDER(BOOKMARKGROUP) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(VALIDATE_BOOKMARK_ORDER);
			stmt.setInt(1, bookmarkOrder);
			rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return false;
	}

	/**
	 * 북마크 그룹 추가
	 * @param bookmarkNm
	 * @param bookmarkOrder
	 */
	public void insertBookmarkGroup(String bookmarkNm, int bookmarkOrder) {
		
		System.out.println("===> JDBC INSERT(BOOKMARKGROUP) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARKGROUP_INSERT);
			stmt.setString(1, bookmarkNm);
			stmt.setInt(2, bookmarkOrder);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 삭제되지 않은 전체 북마크 그룹 목록 조회
	 * @return
	 */
	public List<BookmarkGroup> getBookmarkGroupList() {
		
		System.out.println("===> JDBC getBookmarkGroupList() 기능 처리");
		List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARKGROUP_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				BookmarkGroup bookmarkGroup = new BookmarkGroup();
				bookmarkGroup.setBookmarkGroupId(rs.getInt("BOOKMARK_NO"));
				bookmarkGroup.setBookmarkGroupNm(rs.getString("BOOKMARK_NM"));
				bookmarkGroup.setBookmarkGroupOrder(rs.getInt("BOOKMARK_ORDER"));
				bookmarkGroup.setRegDate(rs.getTimestamp("REGDATE"));
				bookmarkGroup.setUpdDate(rs.getTimestamp("UPDDATE"));
				bookmarkGroup.setDelYN(rs.getString("DEL_YN"));         
				bookmarkGroupList.add(bookmarkGroup);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bookmarkGroupList;
	}
	
	/**
	 * 북마크 아이디로 북마크 조회
	 * @return
	 */
	public BookmarkGroup getBookmarkGroup(int bookmarkNo) {
		
		System.out.println("===> JDBC getBookmarkGroup() 기능 처리");
		BookmarkGroup bookmarkGroup = new BookmarkGroup();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARKGROUP_DETAIL);
			stmt.setInt(1, bookmarkNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				bookmarkGroup.setBookmarkGroupId(rs.getInt("BOOKMARK_NO"));
				bookmarkGroup.setBookmarkGroupNm(rs.getString("BOOKMARK_NM"));
				bookmarkGroup.setBookmarkGroupOrder(rs.getInt("BOOKMARK_ORDER"));
				bookmarkGroup.setRegDate(rs.getTimestamp("REGDATE"));
				bookmarkGroup.setUpdDate(rs.getTimestamp("UPDDATE"));
				bookmarkGroup.setDelYN(rs.getString("DEL_YN"));   
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bookmarkGroup;
	}
	
	/**
	 * 북마크 그룹 삭제 플래그 'Y'로 변경
	 * @param bookmarkId
	 */
	public void delete(int bookmarkNo) {
		
		System.out.println("===> JDBC DELETE(bookmarkId) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARKGROUP_DELETE);
			stmt.setInt(1, bookmarkNo);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 북마크 그룹 이름 변경
	 * @param bookmarkNm
	 * @param bookmarkNo
	 */
	public void modifyBookmarkGroupName(String bookmarkNm, int bookmarkNo) {
		
		System.out.println("===> JDBC UPDATE(BookmarkGroup) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOOKMARKGROUP_UPDATE_NAME);
			stmt.setString(1, bookmarkNm);
			stmt.setInt(2, bookmarkNo);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
