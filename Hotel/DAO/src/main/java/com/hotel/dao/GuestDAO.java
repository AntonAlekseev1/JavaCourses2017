package com.hotel.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IGuestDAO;

public class GuestDao extends AbstractDao<IGuest> implements IGuestDAO {


	@Override
	public List<IOption> getGuestOptions(Session session, Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
