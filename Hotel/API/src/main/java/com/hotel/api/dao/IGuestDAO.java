package com.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;

public interface IGuestDAO extends IGenericDao<IGuest> {

	public List<IOption> getGuestOptions(Session session, Integer id) throws Exception;
	
	

}
