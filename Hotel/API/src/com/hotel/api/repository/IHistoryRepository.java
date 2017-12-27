package com.hotel.api.repository;

import java.util.List;

import com.hotel.api.been.IHistory;

public interface IHistoryRepository {
	
	public List<IHistory> getHistory();
	
	public void setHistory(List<IHistory>histories);
	public IHistory getHistoryById(Integer id);
	
	public void addHistory(IHistory history);

}
