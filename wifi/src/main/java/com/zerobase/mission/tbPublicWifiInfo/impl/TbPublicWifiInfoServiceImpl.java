package com.zerobase.mission.tbPublicWifiInfo.impl;

import java.util.List;

import com.zerobase.mission.tbPublicWifiInfo.TbPublicWifiInfo;
import com.zerobase.mission.tbPublicWifiInfo.TbPublicWifiInfoService;
import com.zerobase.mission.wifi.Wifi;

import okhttp3.Request.Builder;

public class TbPublicWifiInfoServiceImpl implements TbPublicWifiInfoService {
	
private static TbPublicWifiInfoDAO tbPublicWifiInfoDAO;
	
	public TbPublicWifiInfoServiceImpl() {
		tbPublicWifiInfoDAO = TbPublicWifiInfoDAO.getInstance();
	}

	@Override
	public String getUrl(int startIndex, int endIndex) {
		return tbPublicWifiInfoDAO.getUrl(startIndex, endIndex);
	}

	@Override
	public Builder getRequestBuilder(String HttpUrl) {
		return tbPublicWifiInfoDAO.getRequestBuilder(HttpUrl);
	}

	@Override
	public TbPublicWifiInfo getTbPublicWifiInfo(int startIndex, int endIndex) {
		return tbPublicWifiInfoDAO.getTbPublicWifiInfo(startIndex, endIndex);
	}

	@Override
	public int getListTotalCount() {
		return tbPublicWifiInfoDAO.getListTotalCount();
	}

	@Override
	public void saveAllWifiList() {
		tbPublicWifiInfoDAO.saveAllWifiList();
	}

//	@Override
//	public void saveWifiList(List<Wifi> wifiList) {
//		tbPublicWifiInfoDAO.saveWifiList(wifiList);
//	}

}
