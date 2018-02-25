package com.hotel.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IRoomDAO;

public class RoomDAO extends AbstractDao<IRoom> implements IRoomDAO {

	private static final Logger logger = Logger.getLogger(RoomDAO.class);
	private static RoomDAO instance;

	private RoomDAO() {

	}

	public static RoomDAO getInstance() {

		if (instance == null) {
			instance = new RoomDAO();
		}
		return instance;
	}

	@Override
	public List<IGuest> getLastGuests(Session session, Integer id, Integer number) throws Exception {
		List<IGuest> list = new ArrayList<>();
		try {
			

		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e.getMessage());
		}
		return list;
	}


}
