package com.project.service;

import com.project.been.Room;
import com.project.rep.RoomRepository;

public class RoomService {
	private Integer numberOfRooms = 0;
	private Integer numberOfFreeRooms = 0;
	// private Integer numberOfFreeRoomsForDate;
	private final RoomRepository ROOM = new RoomRepository();

	public void printRoomList() {
		for (int i = 0; i < ROOM.getRooms().length; i++) {
			if (ROOM.getRooms()[i] != null) {
				System.out.println(ROOM.getRooms()[i].toString());
			}
		}

	}

	public void printFreeRoomList() {
		for (int i = 0; i < ROOM.getRooms().length; i++) {
			if (ROOM.getRooms()[i] != null) {
				if (ROOM.getRooms()[i].getIsFree() == true) {
					System.out.println(ROOM.getRooms()[i].toString());

				}
			}

		}

	}

	public RoomRepository getROOM() {
		return ROOM;
	}

	/*
	 * public void addRoom(RoomRepository repository, Room room) {
	 * repository.addRoom(room); numberOfRooms=numberOfRooms+1; }
	 */
	public void addRoom(Room room) {
		ROOM.addRoom(room);
	}

	public Room[] getRoom() {
		return ROOM.getRooms();
	}

	public String getNumberOfRooms() {
		for (int i = 0; i < ROOM.getRooms().length; i++) {
			if (ROOM.getRooms()[i] != null) {
				numberOfRooms++;
			}

		}
		return "Nubber of rooms: " + numberOfRooms;
	}

	public String getNumberOfFreeRooms() {
		for (int i = 0; i < ROOM.getRooms().length; i++) {
			if (ROOM.getRooms()[i] != null) {
				if (ROOM.getRooms()[i].getIsFree() == true) {

					numberOfFreeRooms++;
				}
			}
		}
		return "Nubber of free rooms: " + numberOfFreeRooms;
	}

}
