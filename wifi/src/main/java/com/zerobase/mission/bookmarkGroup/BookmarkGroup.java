package com.zerobase.mission.bookmarkGroup;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BookmarkGroup {

	private int bookmarkGroupId;		
	private String bookmarkGroupNm;
	private int bookmarkGroupOrder;	
	private Timestamp regDate;
	private Timestamp updDate;
	private String delYN;
}
