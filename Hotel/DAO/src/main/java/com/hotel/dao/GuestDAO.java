package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Option;

public class GuestDao extends AbstractDao<Guest> implements IGuestDAO {

	@Override
	public List<Option> getGuestOptions(Session session, Integer id) throws Exception {
		Guest guest = (Guest) getById(session, id, Guest.class);
		List<Option> list = null;
		List<History> history = guest.getHistory();
		for (int i = 0; i < history.size(); i++) {
			if (history.get(i).getGuest().equals(guest)) {
				list = guest.getHistory().get(i).getOptions();
			}
		}
		return list;
	}

}
