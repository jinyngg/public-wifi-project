package com.zerobase.mission.wifi;

import java.util.List;

public interface WifiService {
	
	void insertWifi(Wifi wifi);
	void insertWifiList(List<Wifi> wifiList);
	void deleteAll();
	List<Wifi> getWifiList();
}
