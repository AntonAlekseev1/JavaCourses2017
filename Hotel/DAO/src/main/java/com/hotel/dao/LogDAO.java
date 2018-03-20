package com.hotel.dao;

import com.hotel.api.dao.ILogEntityDAO;
import com.hotel.been.LogEntity;

public class LogDAO extends AbstractDao<LogEntity> implements ILogEntityDAO {

	private static LogDAO instance;

	public LogDAO() {
		super(LogEntity.class);
	}

	public static LogDAO getInstance() {
		if (instance == null) {
			instance = new LogDAO();
		}
		return instance;
	}
}
