package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.api.service.IRoomService;
import com.hotel.di.DependecyInjector;
import com.hotel.utils.Connector;

public class RoomService implements IRoomService {
	
	private static RoomService instance;
	private IRoomDAO roomDao = (IRoomDAO) DependecyInjector.inject(IRoomDAO.class);
	private Connector connect = Connector.getinstance();
	
	private RoomService() {
		
	}
	
	public static RoomService getInstance() {
		if(instance==null) {
			instance=new RoomService();
		}
		return instance;
	}

	public void addRoom(IRoom room) throws Exception {
		room.setIsFree(true);
		room.setStatus(RoomStatus.OPEN);
		roomDao.create(connect.getConection(), room);
	}
	
	public void remove(Integer id) throws Exception {
		roomDao.delete(connect.getConection(), id);
	}

	public List<IRoom> getAllRoom() throws Exception {
		return roomDao.getAll(connect.getConection());
	}

	public List<IRoom> getFreeRooms() throws Exception {
		List<IRoom> allRooms = getAllRoom();
		List<IRoom> freeRooms = new ArrayList<>();
		for (int i = 0; i < allRooms.size(); i++) {
			if (allRooms.get(i).getIsFree() == true) {
				freeRooms.add(allRooms.get(i));
			}
		}
		return freeRooms;
	}

	public IRoomDAO getRooms() {
		return roomDao;
	}

	public String chengePriseOfRoom(Integer roomId, Double price) throws Exception {
		IRoom room = getRooms().getById(connect.getConection(),roomId);
		if (room != null) {
			room.setPrice(price);
			roomDao.updute(connect.getConection(), room);
		}
		return "Price of room " + room.getId() + " is " + room.getPrice();
	}

	public Integer getNumberOfRooms() throws Exception {

		return roomDao.getAll(connect.getConection()).size();
	}

	public Integer getNumberOfFreeRooms() throws Exception {
		List<IRoom> list = roomDao.getAll(connect.getConection());
		Integer numberOfFreeRooms = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIsFree() == true) {
				numberOfFreeRooms++;
			}
		}
		return numberOfFreeRooms;
	}
    @Override
	public List<IRoom> sortRooms(String name) throws Exception {
		return roomDao.sort(connect.getConection(), name);
	}

	public List<IGuest> getLastGuests(Integer id, Integer num) throws Exception {
		List<IGuest> guests = roomDao.getLastGuests(connect.getConection(), id, num);
		
		return guests;
	}
	
	public IRoom clone(IRoom room) throws Exception {
		IRoom clon =  room.clone();
		
			roomDao.create(connect.getConection(), clon);
		
		return clon;
	}

}