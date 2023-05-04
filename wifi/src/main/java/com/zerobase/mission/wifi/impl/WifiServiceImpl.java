package com.zerobase.mission.wifi.impl;

import java.util.List;

import com.zerobase.mission.wifi.Wifi;
import com.zerobase.mission.wifi.WifiService;

public class WifiServiceImpl implements WifiService {
	
	private static WifiDAO wifiDAO;
	
	public WifiServiceImpl() {
		wifiDAO = WifiDAO.getInstance();
	}

	@Override
	public void insertWifi(Wifi wifi) {
		wifiDAO.insertWifi(wifi);
	}

	@Override
	public void deleteAll() {
		wifiDAO.deleteAll();
	}

	@Override
	public List<Wifi> getWifiList() {
		return wifiDAO.getWifiList();
	}

	@Override
	public void insertWifiList(List<Wifi> wifiList) {
		wifiDAO.insertWifiList(wifiList);
	}

}
