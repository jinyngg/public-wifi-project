package com.zerobase.mission.history;

import java.util.List;

public interface HistoryService {
	
	void insert(History history);
	void delete(int historyId);
	List<History> getHistoryList();

}
