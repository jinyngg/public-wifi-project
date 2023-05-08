package com.zerobase.mission.bookmark;

import java.util.List;

import com.zerobase.mission.bookmarkGroup.BookmarkGroup;
import com.zerobase.mission.wifi.Wifi;

public interface BookmarkService {
	
	void insertBookmark(int bookmarkGroupId, String bookmarkGroupNm, String wifiMgrNo, String wifiMainNm);
	List<Bookmark> getBookmarkList();
	void delete(int bookmarkId);

}
