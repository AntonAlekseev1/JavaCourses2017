package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IRoom;
import com.hotel.api.repository.IRoomRepository;

public class RoomRepository  implements IRoomRepository {
	private List<IRoom> roomRepository = new ArrayList<>();

	private static RoomRepository instance;

	public RoomRepository() {
//		roomRepository;
	}

	public static RoomRepository getInstance() {
		if (instance == null) {
			instance = new RoomRepository();
		}
		return instance;
	}

	public IRoom getRoomById(Integer id) {

		IRoom roomEntity = null;
		for (int i = 0; i < roomRepository.size(); i++) {
			if (roomRepository.get(i).getId().equals(id)) {
				roomEntity = roomRepository.get(i);
				break;
			}
		}
		return roomEntity;
	}

	public List<IRoom> getRooms() {

		return roomRepository;
	}

	public void setRooms(List<IRoom> rooms) {
		roomRepository = rooms;
	}

	public Integer generateId() {
		Integer id = 0;
		for (int i = 0; i < roomRepository.size(); i++) {
			if (roomRepository.get(i).getId() > id) {
				id = roomRepository.get(i).getId();
			}
		}
		return (id + 1);
	}

	public void addRoom(IRoom room) {
		room.setId(generateId());
		roomRepository.add(room);
	}

}