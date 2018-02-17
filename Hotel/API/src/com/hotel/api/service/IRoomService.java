package com.hotel.api.service;

import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.dao.IRoomDAO;

public interface IRoomService {
	
	public IRoom clone(IRoom id) throws Exception;
	
	public void addRoom(IRoom room) throws Exception;
	
	public List<IRoom> getAllRoom() throws Exception;
	
	public IRoomDAO getRooms();
	
	public String chengePriseOfRoom(Integer roomId, Double price) throws Exception;
	
	public Integer getNumberOfRooms() throws Exception;
	
	public Integer getNumberOfFreeRooms() throws Exception;
	
	public List<IGuest> getLastGuests(Integer id, Integer num) throws Exception;
	
	public List<IRoom> getFreeRooms() throws Exception;

	public List<IRoom> sortRooms(String name) throws Exception;
	
	public void remove(Integer id) throws Exception;

}
