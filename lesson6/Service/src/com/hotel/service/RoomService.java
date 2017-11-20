package com.hotel.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hotel.api.been.IRoom;
import com.hotel.api.been.IGuest;
import com.hotel.api.repository.IRoomRepository;
import com.hotel.api.service.IRoomService;
import com.hotel.been.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.ExLogger;
import com.hotel.utils.FileWorker;

public class RoomService implements IRoomService {
	private Integer numberOfRooms = 0;
	private Integer numberOfFreeRooms = 0;
	private String path = "../data/rooms.txt";
	private IRoomRepository roomRepository = RoomRepository.getInstance();

	public RoomService() {

		RoomRepository.getInstance();

	}

	public IRoom clone(Integer id) throws CloneNotSupportedException {
		Room clon = (Room) roomRepository.getRoomById(id).clone();
		clon.setId(roomRepository.generateId());
		return clon;
	}

	public void addRoom(IRoom room) {
		roomRepository.addRoom(room);
	}

	public List<IRoom> getAllRoom() {
		return roomRepository.getRooms();
	}

	public List<IRoom> getFreeRooms() {
		List<IRoom> allRooms = getAllRoom();
		List<IRoom> freeRooms = new ArrayList<>();
		for (int i = 0; i < allRooms.size(); i++) {
			if (allRooms.get(i).getIsFree() == true) {
				freeRooms.add(allRooms.get(i));
			}
		}
		return freeRooms;
	}

	public IRoomRepository getRooms() {
		return roomRepository;
	}

	public String chengePriseOfRoom(Integer roomId, Double price) {
		IRoom room = getRooms().getRoomById(roomId);
		if (room != null) {
			room.setPrice(price);
		}
		return "Price of room " + room.getId() + " is " + room.getPrice();
	}

	public Integer getNumberOfRooms() {
		for (int i = 0; i < roomRepository.getRooms().size(); i++) {
			numberOfRooms++;
		}
		return numberOfRooms;
	}

	public Integer getNumberOfFreeRooms() {
		for (int i = 0; i < roomRepository.getRooms().size(); i++) {
			if (roomRepository.getRooms().get(i).getIsFree() == true) {
				numberOfFreeRooms++;
			}
		}
		return numberOfFreeRooms;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortRooms(Comparator comparator) {
		Collections.sort(roomRepository.getRooms(), comparator);
	}

	public List<IGuest> getLastGuests(Integer id,Integer num) {
		List<IGuest> guests = new ArrayList<>();
		IRoom room = roomRepository.getRoomById(id);
		try {
			for (int i = 0; i < num; i++) {
				if (room.getHistory().get(i) != null && room.getHistory().get(i).getGuest() != null) {

					guests.add(room.getHistory().get(i).getGuest());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExLogger.write(e);
		}
		return guests;
	}

	public void writeInFile() {
		FileWorker.writeToFile(path, ArrayWorker.toString(roomRepository.getRooms()));
	}

	public void readFromFile() {
		roomRepository.readFromFile(path);
	}
}