package com.zerobase.mission.bookmark.impl;

import java.util.List;

import com.zerobase.mission.bookmark.Bookmark;
import com.zerobase.mission.bookmark.BookmarkService;
import com.zerobase.mission.bookmarkGroup.BookmarkGroup;
import com.zerobase.mission.wifi.Wifi;

public class BookmarkServiceImpl implements BookmarkService{
	
private static BookmarkDAO bookmarkDAO;
	
	public BookmarkServiceImpl() {
		bookmarkDAO = BookmarkDAO.getInstance();
	}

	@Override
	public void insertBookmark(int bookmarkGroupId, String bookmarkGroupNm, String wifiMgrNo, String wifiMainNm) {
		bookmarkDAO.insertBookmark(bookmarkGroupId, bookmarkGroupNm, wifiMgrNo, wifiMainNm);
	}

	@Override
	public List<Bookmark> getBookmarkList() {
		return bookmarkDAO.getBookmarkList();
	}

	@Override
	public void delete(int bookmarkId) {
		bookmarkDAO.delete(bookmarkId);
	}

}
