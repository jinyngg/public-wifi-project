package com.zerobase.mission.history;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class History {

	private int historyId;
	private double lat;
	private double lnt;
	private Timestamp regDate;
	private String delYN;
	
	public History() {
		
	}
	
	public History(float lat, float lnt) {
		this.lat = lat;
		this.lnt = lnt;
	}
	
}
