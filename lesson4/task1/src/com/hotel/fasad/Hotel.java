package com.hotel.fasad;

import java.util.Arrays;
import java.util.Calendar;

import com.hotel.been.Entity;
import com.hotel.been.Guest;
import com.hotel.been.Room;
import com.hotel.been.RoomStatus;
import com.hotel.comparator.SortedByCopacity;
import com.hotel.comparator.SortedByName;
import com.hotel.comparator.SortedByStars;
import com.hotel.comparator.SortedRoomByPrice;
import com.hotel.service.*;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.FileWorker;
import com.hotel.utils.Printer;

public class Hotel {
	private RoomService roomService = new RoomService();
	private GuestService guestService = new GuestService();
	private HistoryService historyService = new HistoryService(guestService.getGuest(), roomService.getRooms());

	public void printRoomList() {
		Printer.printArray(roomService.getRoom());
	}

	public void printFreeRoomsList() {
		for (int i = 0; i < roomService.getRoom().length; i++) {
			if (roomService.getRoom()[i] != null) {
				if (roomService.getRoom()[i].getIsFree() == true) {
					Printer.println(roomService.getRoom()[i].toString());

				}
			}
		}
	}

	public void addGuest(Guest guest) {
		guestService.addGuest(guest);
	}

	public void printNumberOfGuests() {
		Printer.println(guestService.getNumberOfGuests());
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {
		historyService.settleGuestInRoom(guestId, roomId, dateOfArival, evictDate);

	}

	public void sortedGuestByName() {
		Arrays.sort(guestService.getGuests(), new SortedByName());
		Printer.println("   Guest list sorted by name:");
		Printer.printArray(guestService.getGuests());

	}

	public void printGuestList() {
		Printer.printArray(guestService.getGuests());
	}

	public void chengeRoomStatus(Integer id, RoomStatus status) {
		roomService.getRooms().getRoomById(id).setStatus(status);

	}

	public void chengePriceOfRoom(Integer id, Double price) {
		roomService.chengePriseOfRoom(id, price);
	}

	public void printRoom(Integer id) {
		Printer.println(roomService.getRooms().getRoomById(id).toString());
	}

	public void printNumberOfRooms() {
		Printer.println(roomService.getNumberOfRooms());
	}

	public void printNumberOfFreeRooms() {
		Printer.println(roomService.getNumberOfFreeRooms());
	}

	public void addRoom(Room room) {
		roomService.addRoom(room);
	}

	public void sortedRoomsByPrice() {
		Arrays.sort(roomService.getRoom(), new SortedRoomByPrice());
		Printer.println("   Room list sorted by price:");
		Printer.printArray(roomService.getRoom());
	}

	public void sortedRoomsByCopaciti() {
		Arrays.sort(roomService.getRoom(), new SortedByCopacity());
		Printer.println("   Room list sorted by copacity:");
		Printer.printArray(roomService.getRoom());
	}

	public void sortedRoomsByStars() {
		Arrays.sort(roomService.getRoom(), new SortedByStars());
		Printer.println("   Room list sorted by stars:");
		Printer.printArray(roomService.getRoom());
	}

	public void writeInFile(String pathToGuests, String pathToRooms) {
		Guest[] customers = guestService.getGuests();
		Room[] rooms = roomService.getRoom();

		FileWorker.writeToFile(pathToGuests, getArray(customers));
		FileWorker.writeToFile(pathToRooms, getArray(rooms));

	}

	public void readFromFile(String pathToCustomers, String pathToRooms) {

		for (String line : FileWorker.readFrom(pathToCustomers)) {
			addGuest(new Guest(line));
		}
		for (String line : FileWorker.readFrom(pathToRooms)) {
			addRoom(new Room(line));
		}

	}

	private String[] getArray(Entity[] array) {
		int count = ArrayWorker.getCount(array);
		String[] str = new String[count];
		for (int i = 0; i < count; i++) {
			str[i] = array[i].toString();
		}
		return str;
	}

}
