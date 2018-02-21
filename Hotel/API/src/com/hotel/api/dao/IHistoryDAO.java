package com.hotel.api.dao;

import java.sql.Connection;
import java.util.List;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;

public interface IHistoryDAO extends IGenericDAO<IHistory> {

	public Double getTotalPayment(Connection connect, Integer id) throws Exception;

	public void addOptionToGoest(Connection connection, Integer idGuest, Integer idOption) throws Exception;

	public List<IRoom> getFreeRoomsOnDate(Connection connection, String date) throws Exception;

}
