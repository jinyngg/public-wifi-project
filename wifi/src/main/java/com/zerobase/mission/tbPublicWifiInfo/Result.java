package com.zerobase.mission.tbPublicWifiInfo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Result {
	
	@SerializedName("CODE")
	private String code;
	
	@SerializedName("MESSAGE")
	private String message;

}
