package com.hotel.api.dao;

import java.sql.Connection;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;

public interface IGuestDAO extends IGenericDAOold<IGuest> {
	
	public IGuest getByName(Connection connect, String name) throws Exception;

	public List<IOption> getGuestOptions(Connection connection, Integer id) throws Exception;
	
	

}
