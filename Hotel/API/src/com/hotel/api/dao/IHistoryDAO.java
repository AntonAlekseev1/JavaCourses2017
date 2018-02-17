package com.hotel.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;

public interface IHistoryDAO extends IGenericDAO<IHistory> {

public Double getTotalPayment(Connection connect, Integer id);

public void addOptionToGoest(Connection connection, Integer idGuest, Integer idOption) throws SQLException;

public List<IRoom> getFreeRoomsOnDate(Connection connection, String date);
	
}
