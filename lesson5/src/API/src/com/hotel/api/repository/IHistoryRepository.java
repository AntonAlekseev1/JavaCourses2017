package com.hotel.api.repository;

import java.util.List;

import com.hotel.api.been.IHistory;

public interface IHistoryRepository {
	
	public List<IHistory> getHistory();
	
	public IHistory getHistoryById(Integer id);
	
	public void addHistory(IHistory history);

}
