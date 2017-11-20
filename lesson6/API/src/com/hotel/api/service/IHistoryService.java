package com.hotel.api.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;

public interface IHistoryService {
	
	public List<IHistory> getHistory();
	
	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate);
	
	public void evictGuestFromRoom(Integer guestId, Integer roomId);
	
	public List<IRoom> getFreeRoomOnDate(Date date);
	
	public void addHistory(IHistory history);
	
	public Double getTotalPayment(Integer id);
	
	

}
