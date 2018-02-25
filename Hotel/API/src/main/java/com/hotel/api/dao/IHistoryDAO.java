package com.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;

public interface IHistoryDAO extends IGenericDao<IHistory> {

	public Double getTotalPayment(Session session, Integer id) throws Exception;

	public void addOptionToGoest(Session session, Integer idGuest, Integer idOption) throws Exception;

	public List<IRoom> getFreeRoomsOnDate(Session session, String date) throws Exception;

}
