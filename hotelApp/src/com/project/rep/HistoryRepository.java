package com.project.rep;

import com.project.been.History;

public class HistoryRepository {
	private History[] historys = new History[16];

	public HistoryRepository() {

	}

	public void addHistory(History history) {
		for (int i = 0; i < historys.length; i++) {
			if (i == historys.length - 1) {
				History[] resizeArray = new History[historys.length + 16];
				System.arraycopy(history, 0, resizeArray, 0, historys.length - 1);
				historys = resizeArray;
			}

			if (historys[i] == null) {
				historys[i] = history;
				break;
			}
		}

	}

	public History[] getHistory() {
		return historys;
	}

}
