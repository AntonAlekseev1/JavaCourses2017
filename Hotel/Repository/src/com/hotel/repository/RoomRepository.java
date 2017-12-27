package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;
import com.hotel.api.been.IRoom;
import com.hotel.api.repository.IRoomRepository;
import com.hotel.been.Room;
import com.hotel.utils.FileWorker;
import com.hotel.utils.IdGenerator;

public class RoomRepository implements IRoomRepository {
	private List<IRoom> roomRepository;

	public RoomRepository() {
		roomRepository = new ArrayList<>();

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

	public void addRoom(IRoom room) {
		
		roomRepository.add(room);
		room.setId(IdGenerator.generateRoomId());
		}
	public void readFromFile(String pathToRoom) {
		for(String line: FileWorker.readFrom(pathToRoom)) {
			roomRepository.add(new Room(line));
		}
	}

}