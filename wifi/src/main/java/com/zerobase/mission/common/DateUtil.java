package com.zerobase.mission.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	final static String COMMON_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	
	/**
	 * 
	 * @param timestamp
	 * @return format String 
	 */
	public static String timestampToString(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(COMMON_DATE_FORMAT);
		String result = sdf.format(timestamp);
		
		return result;
	}

}
