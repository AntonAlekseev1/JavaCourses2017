package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IHistory;
import com.hotel.api.repository.IHistoryRepository;
import com.hotel.utils.IdGenerator;

public class HistoryRepository implements IHistoryRepository {
	private List<IHistory> historys;

	public HistoryRepository() {
		historys = new ArrayList<>();

	}

	public List<IHistory> getHistory() {
		return historys;
	}

	public IHistory getHistoryById(Integer id) {
		IHistory entity = null;
		for (int i = 0; i < historys.size(); i++) {
				if (historys.get(i).getId().equals(id)) {
					entity = historys.get(i);
					break;
				}
		}
		return entity;
	}

	public void addHistory(IHistory history) {
		
		historys.add(history);
		history.setId(IdGenerator.generateHistoryId());
			
			}

}
