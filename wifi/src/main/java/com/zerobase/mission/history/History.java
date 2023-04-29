package com.zerobase.mission.history;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class History {

	private int historyId;	// key
	private double lat;		// 경도
	private double lnt;		// 위도
	private Timestamp regDate; 	// 조회일자
	private String delYN;  // 삭제여부
	
	public History() {
		
	}
	
	public History(float lat, float lnt) {
		this.lat = lat;
		this.lnt = lnt;
	}
	
}
