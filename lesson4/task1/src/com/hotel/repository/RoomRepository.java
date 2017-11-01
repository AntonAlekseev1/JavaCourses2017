package com.hotel.repository;

import com.hotel.been.Room;
import com.hotel.utils.IdGenerator;

public class RoomRepository {
	private Room[] room;
	
	public RoomRepository() {
		
	}
	public RoomRepository(Integer size) {
		room=new Room[size];
		
	}

	public Room getRoomById(Integer id) {

		Room roomEntity = null;
		for (int i = 0; i < room.length; i++) {
			if (room[i] != null) {
				if (room[i].getId() == id) {

				roomEntity = room[i];
				break;
				}
			}
		}
		return roomEntity;
	}

	public Room[] getRooms() {
		return room;
	}

	public void setRooms(Room[] rooms) {
		this.room = rooms;
	}

	public void addRoom(Room room) {
		for (int i = 0; i < this.room.length; i++) {
			if (this.room[i] == null) {
				this.room[i] = room;
				room.setId(IdGenerator.generateRoomId());
				break;
			}
		}
	}

}
