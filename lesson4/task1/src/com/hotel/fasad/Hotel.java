package com.hotel.fasad;

import java.util.Arrays;
import java.util.Calendar;

import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Room;
import com.hotel.been.Option;
import com.hotel.been.RoomStatus;
import com.hotel.comparator.SortedByCopacity;
import com.hotel.comparator.SortedByName;
import com.hotel.comparator.SortedByStars;
import com.hotel.comparator.SortedRoomByPrice;
import com.hotel.service.*;
import com.hotel.utils.Printer;

public class Hotel {
	private RoomService roomService = new RoomService();
	private OptionService optionService = new OptionService();
	private GuestService guestService = new GuestService(optionService.getOptions());
	private HistoryService historyService = new HistoryService(guestService.getGuest(), roomService.getRooms());

	public void printRoomList() {
		Printer.printArray(roomService.getAllRoom());
	}

	public void printRoonById(Integer id) {

		Printer.println(roomService.getRooms().getRoomById(id).toString());
	}

	public void printOptionById(Integer id) {
		Printer.println(optionService.getOptions().getOptionById(id).toString());
	}

	public void printFreeRoomsList() {
		for (int i = 0; i < roomService.getAllRoom().length; i++) {
			if (roomService.getAllRoom()[i] != null) {
				if (roomService.getAllRoom()[i].getIsFree() == true) {
					Printer.println(roomService.getAllRoom()[i].toString());

				}
			}
		}
	}

	public void printGuestOptions(Integer guestId) {
		Printer.println("Options of guest "+guestService.getGuestById(guestId).getName());
		Printer.printArray(guestService.getGuestOptions(guestId));
	}

	public void printGuestsRooms() {
		Printer.printArray(historyService.getHistory());

	}

	public void addOption(Option option) {
		optionService.addOption(option);

	}

	public void printOptionList() {
		Printer.println("  Option list:");
		Printer.printArray(optionService.getOption());
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

	public void addOptionToGuest(Integer optionId, Integer guestId) {

	guestService.addOptionToGuest(optionId, guestId);
	}

	public void addHistory(History history) {
		historyService.addHistory(history);
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
		Printer.println("number of rooms "+roomService.getNumberOfRooms());
	}

	public void printNumberOfFreeRooms() {
		Printer.println("number of free rooms "+roomService.getNumberOfFreeRooms());
	}

	public void addRoom(Room room) {
		roomService.addRoom(room);
	}

	public void sortedRoomsByPrice() {
		roomService.sortRooms(new SortedRoomByPrice());
		Printer.println("   Room list sorted by price:");
		Printer.printArray(roomService.getAllRoom());
	}

	public void sortedRoomsByCopaciti() {
		roomService.sortRooms(new SortedByCopacity());
		;
		Printer.println("   Room list sorted by copacity:");
		Printer.printArray(roomService.getAllRoom());
	}

	public void sortedRoomsByStars() {
		roomService.sortRooms(new SortedByStars());
		Printer.println("   Room list sorted by stars:");
		Printer.printArray(roomService.getAllRoom());
	}

	public void getTotalPayment(Integer guestId) {
		Printer.println(guestService.getGuestById(guestId).toString() + " totalPayment "
				+ historyService.getTotalPayment(guestId) + "$");
	}

	public void printFreeRoomsOnDate(Calendar date) {

		Room[] rooms = historyService.getFreeRoomOnDate(date.getTime());
		Printer.printArray(rooms);
	}
	
	public void evictGuestFromRoom(Integer guestId, Integer roomId) {
		historyService.evictGuestFromRoom(guestId, roomId);
	}
	
	public void printLastVisiors(Integer id) {
		Printer.println("last guests of the room ("+roomService.getRooms().getRoomById(id).getId()+")");
		roomService.printLastVisiors(id);
	}

	public void writeInFile() {

		roomService.writeInFile();
		guestService.writeInFile();
		optionService.writeInFile();

	}

	public void readFromFile() {
		Printer.printArray(guestService.readFromFile());
		Printer.printArray(roomService.readFromFile());
		Printer.printArray(optionService.readFromFile());
	}
}