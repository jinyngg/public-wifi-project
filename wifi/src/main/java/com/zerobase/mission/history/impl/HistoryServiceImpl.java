package com.zerobase.mission.history.impl;

import java.util.List;

import com.zerobase.mission.history.History;
import com.zerobase.mission.history.HistoryService;

public class HistoryServiceImpl implements HistoryService {
	
	private static HistoryDAO historyDAO;
	
	public HistoryServiceImpl() {
		historyDAO = HistoryDAO.getInstance();
	}

	@Override
	public void insertHistory(History history) {
		historyDAO.insertHistory(history);
	}

	@Override
	public void delete(int historyId) {
		historyDAO.delete(historyId);
	}

	@Override
	public List<History> getHistoryList() {
		return historyDAO.getHistoryList();
	}

	@Override
	public History getlatestHistory() {
		return historyDAO.getlatestHistory();
	}

}
