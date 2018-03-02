package com.hotel.api.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hotel.been.History;
import com.hotel.been.Room;

public interface IHistoryService {

	public List<History> getHistory() throws Exception;

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate)
			throws Exception;

	public void evictGuestFromRoom(Integer guestId, Integer roomId) throws Exception;

	public List<Room> getFreeRoomOnDate(Date date) throws Exception;

	public void addHistory(History history) throws Exception;

	public Double getTotalPayment(Integer id) throws Exception;

}
