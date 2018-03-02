package com.hotel.api.service;

import java.util.List;

import com.hotel.api.dao.IRoomDAO;
import com.hotel.been.Guest;
import com.hotel.been.Room;

public interface IRoomService {
	
	public Room clone(Integer id, Integer num) throws Exception;
	
	public void addRoom(Room room) throws Exception;
	
	public List<Room> getAllRooms() throws Exception;
	
	public IRoomDAO getRooms();
	
	public String chengePriseOfRoom(Integer roomId, Double price) throws Exception;
	
	public Integer getNumberOfRooms() throws Exception;
	
	public Integer getNumberOfFreeRooms() throws Exception;
	
	public List<Guest> getLastGuests(Integer id, Integer num) throws Exception;
	
	public List<Room> getFreeRooms() throws Exception;

	public List<Room> sortRooms(String name) throws Exception;
	
	public void remove(Integer id) throws Exception;

	String importRooms(String path) throws Exception;

	String exportRooms(String path) throws Exception;

}
