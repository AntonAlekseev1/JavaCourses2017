package com.hotel.repository;

import com.hotel.utils.ArrayWorker;
import com.hotel.utils.IdGenerator;

import java.util.Arrays;

import com.hotel.been.Entity;
import com.hotel.been.History;

public class HistoryRepository {
	private History[] historys;

	public HistoryRepository(Integer size) {
		historys = new History[size];

	}

	public History[] getHistory() {
		return historys;
	}

	public History getHistoryById(Integer id) {
		History entity = null;
		for (int i = 0; i < historys.length; i++) {
			if (historys[i] != null) {
				if (historys[i].getId() == id) {

					entity = historys[i];
				}
			}
		}
		return entity;
	}

	public void addHistory(History history) {
		if (historys[historys.length - 1] != null) {

			historys = castEntitiesArray(ArrayWorker.resize(historys));
		}
		for (int i = 0; i < historys.length; i++) {
			if (historys[i] == null) {
				historys[i] = history;
				historys[i].setId(IdGenerator.generateHistoryId());
				break;
			}
		}

	}

	private History[] castEntitiesArray(Entity[] entities) {
		History[] historyArray = Arrays.copyOf(entities, entities.length, History[].class);
		return historyArray;
	}
}
