package com.zerobase.mission.tbPublicWifiInfo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @Comment 
 * 실제로 사용되지 않는 데이터
 * "CODE": "INFO-000",
 * "MESSAGE": "정상 처리되었습니다"
 */
@Data
public class Result {
	
	@SerializedName("CODE")
	private String code;
	
	@SerializedName("MESSAGE")
	private String message;

}
