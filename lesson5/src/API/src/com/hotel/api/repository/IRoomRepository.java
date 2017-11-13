package com.hotel.api.repository;

import java.util.List;

import com.hotel.api.been.IRoom;

public interface IRoomRepository {
	
	public List<IRoom> getRooms();
	
	public IRoom getRoomById(Integer id);
	
	public void addRoom(IRoom room);
	
	

}
