package com.hotel.repository;

import com.hotel.been.History;

public class HistoryRepository {
	private History[] historys;

	public HistoryRepository(Integer size) {
		historys = new History[size];

	}

	public History[] getHistory() {
		return historys;
	}

}
