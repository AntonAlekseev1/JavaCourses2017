package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;
import com.hotel.api.been.IRoom;
import com.hotel.api.repository.IRoomRepository;
import com.hotel.utils.IdGenerator;

public class RoomRepository implements IRoomRepository {
	private List<IRoom> rooms;

	public RoomRepository() {
		rooms = new ArrayList<>();

	}

	public IRoom getRoomById(Integer id) {

		IRoom roomEntity = null;
		for (int i = 0; i < rooms.size(); i++) {
				if (rooms.get(i).getId().equals(id)) {
					roomEntity = rooms.get(i);
					break;
				}
		}
		return roomEntity;
	}

	public List<IRoom> getRooms() {

		return rooms;
	}

	public void addRoom(IRoom room) {
		
		rooms.add(room);
		room.setId(IdGenerator.generateRoomId());
		}

}