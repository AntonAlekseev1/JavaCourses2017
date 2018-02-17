package com.hotel.api.dao;

import java.sql.Connection;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;

public interface IGuestDAO extends IGenericDAO<IGuest> {
	
	public IGuest getByName(Connection connect, String name);

	public List<IGuest> sort(Connection connection, String name) throws Exception;

	public List<IOption> getGuestOptions(Connection connection, Integer id);
	
	

}
