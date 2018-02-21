package com.hotel.api.service;

import java.util.Calendar;
import java.util.List;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;

public interface IHistoryService {
	
	public List<IHistory> getHistory() throws Exception;
	
	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) throws Exception;
	
	public void evictGuestFromRoom(Integer guestId, Integer roomId) throws Exception;
	
	public List<IRoom> getFreeRoomOnDate(String date) throws Exception;
	
	public void addHistory(IHistory history) throws Exception;
	
	public Double getTotalPayment(Integer id) throws Exception;


}
