package com.zerobase.mission.wifi;

import java.util.List;

public interface WifiService {
	
	void insertWifi(Wifi wifi);
	void insertWifiList(List<Wifi> wifiList);
	void deleteAll();
	List<Wifi> getWifiListByLocation(double lat, double lnt, int limit);
	List<Wifi> getAllWifiList();
	Wifi getWifiByMgrNo(double lat, double lnt, String MgrNo);
}
