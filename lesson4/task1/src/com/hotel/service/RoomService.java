package com.hotel.service;

import java.util.Arrays;
import java.util.Comparator;

import com.hotel.been.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.utils.FileWorker;
import com.hotel.utils.Printer;

public class RoomService {
	private Integer numberOfRooms = 0;
	private Integer numberOfFreeRooms = 0;
	private static final Integer MIN_SIZE = 5;
	private String path = "D:\\1\\rooms.txt";
	private RoomRepository rooms;

	public RoomService() {

		rooms = new RoomRepository(MIN_SIZE);

	}

	public void addRoom(Room room) {
		rooms.addRoom(room);
	}

	public Room[] getAllRoom() {
		return rooms.getRooms();
	}

	public RoomRepository getRooms() {
		return rooms;
	}

	public String chengePriseOfRoom(Integer roomId, Double price) {
		Room room = getRooms().getRoomById(roomId);
		if (room != null) {
			room.setPrice(price);
		}
		return "Price of room " + room.getId() + " is " + room.getPrice();
	}

	public Integer getNumberOfRooms() {
		for (int i = 0; i < rooms.getRooms().length; i++) {
			if (rooms.getRooms()[i] != null) {
				numberOfRooms++;
			}

		}
		return  numberOfRooms;
	}

	public Integer getNumberOfFreeRooms() {
		for (int i = 0; i < rooms.getRooms().length; i++) {
			if (rooms.getRooms()[i] != null) {
				if (rooms.getRooms()[i].getIsFree() == true) {

					numberOfFreeRooms++;
				}
			}
		}
		return  numberOfFreeRooms;
	}

	public void sortRooms(Comparator<Room> comparator) {
		Arrays.sort(rooms.getRooms(), comparator);
	}
	
	public void printLastVisiors(Integer id) {
		Room room= rooms.getRoomById(id);
		for(int i=0;i<4;i++) {
			if(room.getHistory()[i]!=null&&room.getHistory()[i].getGuest()!=null) {
				
				Printer.println(room.getHistory()[i].getGuest().toString()+" date of arival "+room.getHistory()[i].getDateOfArrival());
			}
		}
	}

	public void writeInFile() {
		rooms.writeInFile();
	}

	public String[] readFromFile() {
		return FileWorker.readFrom(path);
	}

}