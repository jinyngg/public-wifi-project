package com.zerobase.mission.bookmark;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Bookmark {
	int bookmarkId;
	private Timestamp regDate;
	private String delYN;
	
	int bookmarkGroupId; // bookmarkGroup pk
	String bookmarkGroupNm;
	
	String wifiMgrNo; // wifi pk
	String wifiMainNm;
}
