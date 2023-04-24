package com.zerobase.wifi.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
	
	public static Connection getConnection() {
		
		try {
			// mySql 호출
			Class.forName("com.mysql.cj.jdbc.Driver");
			// @TODO 하드코딩 수정 필요
//			return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database, userName, password);
			return DriverManager.getConnection("jdbc:mysql://localhost/WIFI", "root", "root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
