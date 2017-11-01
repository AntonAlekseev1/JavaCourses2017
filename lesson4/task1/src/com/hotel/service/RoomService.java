package com.hotel.service;

import com.hotel.been.Room;
import com.hotel.been.RoomStatus;
import com.hotel.repository.RoomRepository;

public class RoomService {
	private Integer numberOfRooms = 0;
	private Integer numberOfFreeRooms = 0;
	private static final Integer MIN_SIZE=5;
	// private Integer numberOfFreeRoomsForDate;
	private  RoomRepository rooms;
	
	public RoomService() {
		
		rooms=new RoomRepository(MIN_SIZE);
		
	}


/*	public Room getRoomById(Integer id) {

		Room roomEntity = null;
		for (int i = 0; i < rooms.getRooms().length; i++) {
			if ( rooms.getRooms()[i] != null) {
				if ( rooms.getRooms()[i].getId() == id) {

					roomEntity =  rooms.getRooms()[i];
					break;
				}
			}
		}
		return roomEntity;
	}*/
//	public Room[] getFreeRoomList() {
//	//	RoomRepository freeRoom=new RoomRepository(MIN_SIZE);
//		for (int i = 0; i < rooms.getRooms().length; i++) {
//			if (rooms.getRooms() != null) {
//				if (rooms.getRooms()[i].getIsFree() == true) {
//					return rooms.getRooms();
//				}
//			}
//
//		}
//		return null;
//	}

//	public RoomRepository getRoom() {
//		return rooms;
//	}

	/*
	 * public void addRoom(RoomRepository repository, Room room) {
	 * repository.addRoom(room); numberOfRooms=numberOfRooms+1; }
	 */
	public void addRoom(Room room) {
		rooms.addRoom(room);
	}

	public Room[] getRoom() {
		return rooms.getRooms();
	}
	public RoomRepository getRooms() {
		return rooms;
	}
	
	public String chengePriseOfRoom(Integer roomId, Double price) {
		Room room= getRooms().getRoomById(roomId);
		if(room!=null) {
			room.setPrice(price);
		}
		return "Price of room "+room.getId()+" is "+room.getPrice();
	}
	
	public String getNumberOfRooms() {
		for (int i = 0; i < rooms.getRooms().length; i++) {
			if (rooms.getRooms()[i] != null) {
				numberOfRooms++;
			}

		}
		return "Nubber of rooms: " + numberOfRooms;
	}

	public String getNumberOfFreeRooms() {
		for (int i = 0; i < rooms.getRooms().length; i++) {
			if (rooms.getRooms()[i] != null) {
				if (rooms.getRooms()[i].getIsFree() == true) {

					numberOfFreeRooms++;
				}
			}
		}
		return "Nubber of free rooms: " + numberOfFreeRooms;
	}

}
