package com.zerobase.mission.wifi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zerobase.mission.common.JDBCUtil;
import com.zerobase.mission.wifi.Wifi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WifiDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// SQL DML
	private final String WIFI_INSERT = 
			"INSERT"
			+ " INTO WIFI ("
			+ "X_SWIFI_MGR_NO"
			+ ", X_SWIFI_WRDOFC"
			+ ", X_SWIFI_MAIN_NM"
			+ ", X_SWIFI_ADRES1"
			+ ", X_SWIFI_ADRES2"
			+ ", X_SWIFI_INSTL_FLOOR"
			+ ", X_SWIFI_INSTL_TY"
			+ ", X_SWIFI_INSTL_MBY"
			+ ", X_SWIFI_SVC_SE"
			+ ", X_SWIFI_CMCWR"
			+ ", X_SWIFI_CNSTC_YEAR"
			+ ", X_SWIFI_INOUT_DOOR"
			+ ", X_SWIFI_REMARS3"
			+ ", LAT"
			+ ", LNT"
			+ ", WORK_DTTM"
			+ ")"
			+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final String WIFI_DELETE_ALL = 
			"DELETE"
			+ " FROM WIFI";
	
	private final String WIFI_LIST = 
			"SELECT"
			+ " X_SWIFI_MGR_NO"
			+ ", X_SWIFI_WRDOFC"
			+ ", X_SWIFI_MAIN_NM"
			+ ", X_SWIFI_ADRES1"
			+ ", X_SWIFI_ADRES2"
			+ ", X_SWIFI_INSTL_FLOOR"
			+ ", X_SWIFI_INSTL_TY"
			+ ", X_SWIFI_INSTL_MBY"
			+ ", X_SWIFI_SVC_SE"
			+ ", X_SWIFI_CMCWR"
			+ ", X_SWIFI_CNSTC_YEAR"
			+ ", X_SWIFI_INOUT_DOOR"
			+ ", X_SWIFI_REMARS3"
			+ ", LAT"
			+ ", LNT"
			+ ", WORK_DTTM"
			+ " FROM WIFI";
	
	private static WifiDAO wifiDAO;
	
	/**
	 * 최초 한번 new 연산자를 통하여 메모리에 할당하여 싱글톤으로 wifiDAO 생성
	 * @return wifiDAO
	 */
	public static WifiDAO getInstance() {
		if(wifiDAO == null){ 
			wifiDAO = new WifiDAO();
		}		
		return wifiDAO;
	}

	/**
	 * 공공와이파이 서비스 위치 정보 삽입 
	 * @param wifi
	 */
	public void insertWifi(Wifi wifi) {
		
		System.out.println("===> JDBC INSERtWIFI(WIFI) 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(WIFI_INSERT);
			stmt.setString(1, wifi.getX_SWIFI_MGR_NO());
			stmt.setString(2, wifi.getX_SWIFI_WRDOFC());
			stmt.setString(3, wifi.getX_SWIFI_MAIN_NM());
			stmt.setString(4, wifi.getX_SWIFI_ADRES1());
			stmt.setString(5, wifi.getX_SWIFI_ADRES2());
			stmt.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
			stmt.setString(7, wifi.getX_SWIFI_INSTL_TY());
			stmt.setString(8, wifi.getX_SWIFI_INSTL_MBY());
			stmt.setString(9, wifi.getX_SWIFI_SVC_SE());
			stmt.setString(10, wifi.getX_SWIFI_CMCWR());
			stmt.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
			stmt.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
			stmt.setString(13, wifi.getX_SWIFI_REMARS3());
			stmt.setString(14, wifi.getLAT());
			stmt.setString(15, wifi.getLNT());
			stmt.setString(16, wifi.getWORK_DTTM());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 공공와이파이 서비스 위치 정보 리스트 삽입 
	 * @param wifiList
	 */
	public void insertWifiList(List<Wifi> wifiList) {
		
		System.out.println("===> JDBC INSERTWIFILIST(WIFILIST) 기능 처리");
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			for(int i = 0; i < wifiList.size(); i++) {
				Wifi wifi = wifiList.get(i);
				stmt = conn.prepareStatement(WIFI_INSERT);
				stmt.setString(1, wifi.getX_SWIFI_MGR_NO());
				stmt.setString(2, wifi.getX_SWIFI_WRDOFC());
				stmt.setString(3, wifi.getX_SWIFI_MAIN_NM());
				stmt.setString(4, wifi.getX_SWIFI_ADRES1());
				stmt.setString(5, wifi.getX_SWIFI_ADRES2());
				stmt.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
				stmt.setString(7, wifi.getX_SWIFI_INSTL_TY());
				stmt.setString(8, wifi.getX_SWIFI_INSTL_MBY());
				stmt.setString(9, wifi.getX_SWIFI_SVC_SE());
				stmt.setString(10, wifi.getX_SWIFI_CMCWR());
				stmt.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
				stmt.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
				stmt.setString(13, wifi.getX_SWIFI_REMARS3());
				stmt.setString(14, wifi.getLAT());
				stmt.setString(15, wifi.getLNT());
				stmt.setString(16, wifi.getWORK_DTTM());
				stmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	/**
	 * 공공와이파이 서비스 위치 정보 목록 삭제
	 */
	public void deleteAll() {
		
		System.out.println("===> JDBC DELETE ALL() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(WIFI_DELETE_ALL);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/**
	 * 공공와이파이 서비스 위치 정보 목록 조회
	 * @return
	 */
	public List<Wifi> getWifiList() {
		
		System.out.println("===> JDBC getWifiList() 기능 처리");
		List<Wifi> wifiList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(WIFI_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Wifi wifi = new Wifi();
				wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				wifi.setLAT(rs.getString("LAT"));
				wifi.setLNT(rs.getString("LNT"));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
				
				wifiList.add(wifi);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return wifiList; 
	}
	
}
