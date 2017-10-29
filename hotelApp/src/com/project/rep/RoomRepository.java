package com.project.rep;

import com.project.been.Room;

public class RoomRepository {
	private Room[] rooms = new Room[10];
	// private Room room;
	// private int number;

	public Room getRoomByNumber(Integer number) {
		// this.number=number;
		Room room = null;
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] != null) {
				if (rooms[i].getNumber() == number) {

					room = rooms[i];
					break;
				}
			}
		}
		return room;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public void addRoom(Room room) {
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] == null) {
				rooms[i] = room;
				break;
			}
		}
	}

}
