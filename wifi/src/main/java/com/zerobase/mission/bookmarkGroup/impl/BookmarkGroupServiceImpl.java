package com.zerobase.mission.bookmarkGroup.impl;

import java.util.List;

import com.zerobase.mission.bookmarkGroup.BookmarkGroup;
import com.zerobase.mission.bookmarkGroup.BookmarkGroupService;

public class BookmarkGroupServiceImpl implements BookmarkGroupService {

	private static BookmarkGroupDAO bookmarkGroupDAO;
	
	public BookmarkGroupServiceImpl() {
		bookmarkGroupDAO = BookmarkGroupDAO.getInstance();
	}
	
	@Override
	public boolean validateBookmarkOrder(int bookmarkOrder) {
		return bookmarkGroupDAO.validateBookmarkOrder(bookmarkOrder);
	}

	@Override
	public void insertBookmarkGroup(String bookmarkNm, int bookmarkOrder) {
		bookmarkGroupDAO.insertBookmarkGroup(bookmarkNm, bookmarkOrder);
	}

	@Override
	public List<BookmarkGroup> getBookmarkGroupList() {
		return bookmarkGroupDAO.getBookmarkGroupList();
	}

	@Override
	public void delete(int bookmarkNo) {
		bookmarkGroupDAO.delete(bookmarkNo);
	}

	@Override
	public void modifyBookmarkGroupName(String bookmarkNm, int bookmarkNo) {
		bookmarkGroupDAO.modifyBookmarkGroupName(bookmarkNm, bookmarkNo);
	}

	@Override
	public BookmarkGroup getBookmarkGroup(int bookmarkNo) {
		return bookmarkGroupDAO.getBookmarkGroup(bookmarkNo);
	}

}
