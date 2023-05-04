package com.zerobase.mission.tbPublicWifiInfo;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zerobase.mission.wifi.Wifi;

import lombok.Data;

@Data
public class TbPublicWifiInfo {
 
	@SerializedName("list_total_count")
	private int listTotalCount;
	
	@SerializedName("RESULT")
	private Result result;
	
	@SerializedName("row")
	private List<Wifi> wifiList;
}
