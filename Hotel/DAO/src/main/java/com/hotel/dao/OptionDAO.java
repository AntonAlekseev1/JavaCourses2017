package com.hotel.dao;

import com.hotel.api.dao.IOptionDAO;
import com.hotel.been.Option;

public class OptionDAO extends AbstractDao<Option> implements IOptionDAO {

	private static OptionDAO instance;

	private OptionDAO() {
		super(Option.class);
	}

	public static OptionDAO getInstance() {

		if (instance == null) {
			instance = new OptionDAO();
		}
		return instance;
	}

}
