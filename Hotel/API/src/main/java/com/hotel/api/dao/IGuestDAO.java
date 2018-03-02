package com.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.been.Guest;
import com.hotel.been.Option;

public interface IGuestDAO extends IGenericDao<Guest> {

	public List<Option> getGuestOptions(Session session, Integer id) throws Exception;

}
