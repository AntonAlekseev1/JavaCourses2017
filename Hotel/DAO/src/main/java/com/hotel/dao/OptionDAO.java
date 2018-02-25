package com.hotel.dao;

import com.hotel.api.been.IOption;
import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IOptionDAO;

public class OptionDAO extends AbstractDao<IOption> implements IOptionDAO {

	private static OptionDAO instance;

	private OptionDAO() {

	}

	public static OptionDAO getInstance() {

		if (instance == null) {
			instance = new OptionDAO();
		}
		return instance;
	}


}
