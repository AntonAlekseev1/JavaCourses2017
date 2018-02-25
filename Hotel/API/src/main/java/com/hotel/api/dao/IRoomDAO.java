package com.hotel.api.dao;

import java.sql.Connection;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;

public interface IRoomDAO extends IGenericDAOold<IRoom> {

	public List<IGuest> getLastGuests(Connection connect, Integer id, Integer number) throws Exception;

}
