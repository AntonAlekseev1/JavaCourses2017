package com.hotel.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.hotel.api.dao.IGuestDAO;
import com.hotel.been.Guest;
import com.hotel.been.Option;

public class GuestDao extends AbstractDao<Guest> implements IGuestDAO {
	
	private static GuestDao instance;
	
	private GuestDao() {

	}
	
	public static GuestDao getInstance() {
		if(instance == null) {
			instance = new GuestDao();
		}
		return instance;
	}

	@Override
	public List<Option> getGuestOptions(Session session, Integer id) throws Exception {
		try {
		Guest guest = (Guest) getById(session, id, Guest.class);
		Hibernate.initialize(guest.getHistory().get(0).getOptions());
		List<Option> list = guest.getHistory().get(0).getOptions();
		return list;
		}catch(Exception e) {
			throw new Exception(e);
		}
	}

}
