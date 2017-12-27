package com.hotel.api.service;

import java.util.Comparator;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.repository.IRoomRepository;

public interface IRoomService {
	
	public void addRoom(IRoom room);
	
	public List<IRoom> getAllRoom();
	
	public IRoomRepository getRooms();
	
	public String chengePriseOfRoom(Integer roomId, Double price);
	
	public Integer getNumberOfRooms();
	
	public Integer getNumberOfFreeRooms();
	
	@SuppressWarnings("rawtypes")
	public void sortRooms( Comparator comparator);
	
	public List<IGuest> getLastGuests(Integer id);
	
	public List<IRoom> getFreeRooms();
	
	public void writeInFile();
	
	public void readFromFile();

}
