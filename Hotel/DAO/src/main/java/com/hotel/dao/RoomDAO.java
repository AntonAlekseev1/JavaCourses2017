package com.hotel.dao;

import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.been.Room;

public class RoomDAO extends AbstractDao<Room> implements IRoomDAO {

	private static RoomDAO instance;

	private RoomDAO() {

	}

	public static RoomDAO getInstance() {

		if (instance == null) {
			instance = new RoomDAO();
		}
		return instance;
	}

}
