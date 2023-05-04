package com.zerobase.mission.tbPublicWifiInfo;

import java.util.List;

import com.zerobase.mission.wifi.Wifi;

import okhttp3.Request.Builder;

public interface TbPublicWifiInfoService {
	
	String getUrl(int startIndex, int endIndex);
	Builder getRequestBuilder(String HttpUrl);
	
	TbPublicWifiInfo getTbPublicWifiInfo(int startIndex, int endIndex);
	
	int getListTotalCount();
	
	void saveAllWifiList();
}
