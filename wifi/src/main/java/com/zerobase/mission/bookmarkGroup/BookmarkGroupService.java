package com.zerobase.mission.bookmarkGroup;

import java.util.List;

public interface BookmarkGroupService {
	
	boolean validateBookmarkOrder(int bookmarkOrder);
	void insertBookmarkGroup(String bookmarkNm, int bookmarkOrder);
	List<BookmarkGroup> getBookmarkGroupList();
	void delete(int bookmarkNo);
	void modifyBookmarkGroupName(String bookmarkNm, int bookmarkNo);
	BookmarkGroup getBookmarkGroup(int bookmarkNo);
	
}
