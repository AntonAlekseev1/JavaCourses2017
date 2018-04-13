package com.hotel.api.service;

import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.dao.IRoomDAO;

public interface IRoomService {
	
<<<<<<< HEAD
	public void addRoom(IRoom room);
=======
	public IRoom clone(IRoom id) throws Exception;
	
	public void addRoom(IRoom room) throws Exception;
>>>>>>> lesson11
	
	public List<IRoom> getAllRooms() throws Exception;
	
	public IRoomDAO getRooms();
	
	public String chengePriseOfRoom(Integer roomId, Double price) throws Exception;
	
	public Integer getNumberOfRooms() throws Exception;
	
	public Integer getNumberOfFreeRooms() throws Exception;
	
	public List<IGuest> getLastGuests(Integer id, Integer num) throws Exception;
	
<<<<<<< HEAD
	public List<IGuest> getLastGuests(Integer id);
	
	public List<IRoom> getFreeRooms();
	
	public void writeInFile();
	
	public void readFromFile();
=======
	public List<IRoom> getFreeRooms() throws Exception;

	public List<IRoom> sortRooms(String name) throws Exception;
	
	public void remove(Integer id) throws Exception;

	String importRooms(String path) throws Exception;

	String exportRooms(String path) throws Exception;
>>>>>>> lesson11

}
