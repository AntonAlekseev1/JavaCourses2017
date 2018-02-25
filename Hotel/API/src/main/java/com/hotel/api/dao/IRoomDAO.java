package com.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;

public interface IRoomDAO extends IGenericDao<IRoom> {

	public List<IGuest> getLastGuests(Session session, Integer id, Integer number) throws Exception;

}
