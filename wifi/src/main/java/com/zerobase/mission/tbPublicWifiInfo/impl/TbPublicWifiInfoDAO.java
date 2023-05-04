package com.zerobase.mission.tbPublicWifiInfo.impl;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zerobase.mission.tbPublicWifiInfo.TbPublicWifiInfo;
import com.zerobase.mission.wifi.Wifi;
import com.zerobase.mission.wifi.impl.WifiServiceImpl;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TbPublicWifiInfoDAO {
	
	private static OkHttpClient client;
	private static int MAX_DATA_AMOUT = 1000;
	
	// @TODO 하드코딩된 값 수정 필요
	private final static String BASEURL = "http://openapi.seoul.go.kr:8088";
	private final static String KEY = "416f764e766a696e33397361706959";
	private final static String TYPE = "json";
	private final static String SERVICE = "TbPublicWifiInfo";
	
	public TbPublicWifiInfoDAO() {
		client = new OkHttpClient.Builder()
                .build();
	}
	
	private static TbPublicWifiInfoDAO tbPublicWifiInfoDAO;
	WifiServiceImpl wifiservice = new WifiServiceImpl();
	
	/**
	 * 최초 한번 new 연산자를 통하여 메모리에 할당하여 싱글톤으로 tbPublicWifiInfoDAO 생성
	 * @return tbPublicWifiInfoService
	 */
	public static TbPublicWifiInfoDAO getInstance() {
		if(tbPublicWifiInfoDAO == null){ 
			tbPublicWifiInfoDAO = new TbPublicWifiInfoDAO();
		}		
		return tbPublicWifiInfoDAO;
	}
	
	/**
	 * 페이징(데이터 개수 입력) URL 조회
	 * @param startIndex
	 * @param endIndex
	 * @return URL
	 */
	public String getUrl(int startIndex, int endIndex) {
		return BASEURL + "/" + KEY + "/" + TYPE + "/" + SERVICE + "/" + startIndex + "/" + endIndex;
	}
	
	/**
	 * HTTP GET 통신을 위한 빌더 생성
	 * @param HttpUrl
	 * @return builder
	 */
	public Builder getRequestBuilder(String HttpUrl) {
		Request.Builder builder = new Builder().url(HttpUrl).get();
		builder.addHeader("Content-type", "application/json; charset=UTF-8");
		
		return builder;
	}
	
	/**
	 * 페이징(데이터 개수)을 통한 TbPublicWifiInfo 조회
	 * @param startIndex
	 * @param endIndex
	 * @return TbPublicWifiInfo
	 */
	public TbPublicWifiInfo getTbPublicWifiInfo(int startIndex, int endIndex) {
		
		try {
			
			String url = getUrl(startIndex, endIndex);
			Request.Builder builder = getRequestBuilder(url);
			
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			
			if(response.isSuccessful()) {
				ResponseBody responseBody = response.body();
				String tbPublicWifiInfoString = responseBody.string();

				if (responseBody != null) {
					
					Gson gson = new Gson();
					
					JsonObject jsonObject = gson.fromJson(tbPublicWifiInfoString, JsonObject.class);
					JsonElement jsonElement = jsonObject.get("TbPublicWifiInfo"); 
					
					TbPublicWifiInfo tbPublicWifiInfo = gson.fromJson(jsonElement, TbPublicWifiInfo.class);
					return tbPublicWifiInfo;
                }
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		return null;
	}
	
	/**
	 * 서울시 공공와이파이 서비스 위치 정보의 개수 조회
	 * @return listTotalCount
	 */
	public int getListTotalCount() {
		TbPublicWifiInfo tbPublicWifiInfo = getTbPublicWifiInfo(1, 1);
		return tbPublicWifiInfo.getListTotalCount();
	}
	
	
	/**
	 * 서울시 공공와이파이 서비스 위치 정보 리스트 전체 DB 저장
	 */
	public void saveAllWifiList() {
		
//		WifiServiceImpl wifiService = new WifiServiceImpl();
		wifiservice.deleteAll();
		
		int listTotalCount = getListTotalCount();
		
		int divisor = listTotalCount % MAX_DATA_AMOUT == 0 ? listTotalCount / MAX_DATA_AMOUT : (listTotalCount / MAX_DATA_AMOUT) + 1;
		
		int startIndex = 1;
		int endIndex = listTotalCount > MAX_DATA_AMOUT ? startIndex + 999 : listTotalCount;
		
		for(int i = 1; i <= divisor; i++) {
			TbPublicWifiInfo tbPublicWifiInfo = getTbPublicWifiInfo(startIndex, endIndex);
			List<Wifi> wifiList = tbPublicWifiInfo.getWifiList();
			
			wifiservice.insertWifiList(wifiList);
			
			startIndex += MAX_DATA_AMOUT;
			endIndex = (divisor != i) ? endIndex + MAX_DATA_AMOUT : listTotalCount;
		}
		
	}
	
//	/**
//	 * 서울시 공공와이파이 서비스 위치 정보 리스트를 DB에 저장
//	 */
//	public void saveWifiList(List<Wifi> wifiList) {
//		
//		
//		try {
//			
//			for(int i = 0; i < wifiList.size(); i++) {
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		} 
//		
//	}
	
}
