package com.hotel.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.hotel.api.dao.IGuestDAO;
import com.hotel.been.Guest;
import com.hotel.been.Option;

public class GuestDao extends AbstractDao<Guest> implements IGuestDAO {

	private static GuestDao instance;

	private GuestDao() {
		super(Guest.class);
	}

	public static GuestDao getInstance() {
		if (instance == null) {
			instance = new GuestDao();
		}
		return instance;
	}

	@Override
	public List<Option> getGuestOptions(Session session, Integer id) throws Exception {
		try {
			Guest guest = (Guest) getById(session, id);
			Criteria criteria = session.createCriteria(Option.class, "options");
			criteria.createCriteria("histories", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("guest", guest));
			@SuppressWarnings("unchecked")
			List<Option> list = criteria.list();
			return list;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
