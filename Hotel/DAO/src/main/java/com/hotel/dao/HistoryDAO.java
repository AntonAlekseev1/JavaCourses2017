package com.hotel.dao;

import com.hotel.api.dao.IHistoryDAO;
import com.hotel.been.History;

public class HistoryDAO extends AbstractDao<History> implements IHistoryDAO {

	private static HistoryDAO instance;

	private HistoryDAO() {
		super(History.class);
	}

	public static HistoryDAO getInstance() {

		if (instance == null) {
			instance = new HistoryDAO();
		}
		return instance;
	}

}
