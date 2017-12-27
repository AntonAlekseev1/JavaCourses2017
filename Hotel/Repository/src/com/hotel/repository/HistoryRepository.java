package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IHistory;
import com.hotel.api.repository.IHistoryRepository;

public class HistoryRepository implements IHistoryRepository {
	private List<IHistory> historyRepository;
	private static HistoryRepository instance;

	private HistoryRepository() {
		historyRepository = new ArrayList<>();
	}

	public static HistoryRepository getInstance() {
		if (instance == null) {
			instance = new HistoryRepository();
		}
		return instance;
	}

	public List<IHistory> getHistory() {
		return historyRepository;
	}
	
	public void setHistory(List<IHistory> histories) {
		historyRepository=histories;	
	}

	public IHistory getHistoryById(Integer id) {
		IHistory entity = null;
		for (int i = 0; i < historyRepository.size(); i++) {
			if (historyRepository.get(i).getId().equals(id)) {
				entity = historyRepository.get(i);
				break;
			}
		}
		return entity;
	}

	public void addHistory(IHistory history) {
		history.setId(generateId());
		historyRepository.add(history);

	}

	public Integer generateId() {
		Integer id = 0;
		for (int i = 0; i < historyRepository.size(); i++) {
			if (historyRepository.get(i).getId() > id) {
				id = historyRepository.get(i).getId();
			}
		}
		return (id + 1);
	}

}
