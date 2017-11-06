package com.hotel.repository;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;
import com.hotel.been.Entity;
import com.hotel.been.History;
import com.hotel.been.Room;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.IdGenerator;

public class RoomRepository {
	private Room[] room;
	private final TextFileWorker textFileWorker = new TextFileWorker("D:\\1\\rooms.txt");

	public RoomRepository() {

	}

	public RoomRepository(Integer size) {
		room = new Room[size];

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
		if (this.room[this.room.length - 1] != null) {

			this.room = castEntitiesArray(ArrayWorker.resize(this.room));
		}
		for (int i = 0; i < this.room.length; i++) {
			if (this.room[i] == null) {
				this.room[i] = room;
				room.setId(IdGenerator.generateRoomId());
				room.getHistory()[i] = new History(null, null, new GregorianCalendar(0, 0, 0).getTime(),
						new GregorianCalendar(0, 0, 0).getTime());
				break;
			}
		}

	}

	private Room[] castEntitiesArray(Entity[] entities) {
		Room[] roomArray = Arrays.copyOf(entities, entities.length, Room[].class);
		return roomArray;
	}

	public void writeInFile() {
		String[] array = Arrays.copyOf(ArrayWorker.toString(room), ArrayWorker.getCount(room));
		textFileWorker.writeToFile(array);
	}

}