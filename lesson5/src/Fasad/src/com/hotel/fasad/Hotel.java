package com.hotel.fasad;

import java.util.ArrayList;
import java.util.Calendar;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.service.IGuestService;
import com.hotel.api.service.IHistoryService;
import com.hotel.api.service.IOptionService;
import com.hotel.api.service.IRoomService;
import com.hotel.been.Guest;
import com.hotel.comparator.SortedByCopacity;
import com.hotel.comparator.SortedByName;
import com.hotel.comparator.SortedByStars;
import com.hotel.comparator.SortedRoomByPrice;
import com.hotel.service.*;
import com.hotel.utils.Printer;

public class Hotel {
	private IRoomService roomService = new RoomService();
	private IOptionService optionService = new OptionService();
	private IGuestService guestService = new GuestService(optionService.getOptions());
	private IHistoryService historyService = new HistoryService(guestService.getGuest(), roomService.getRooms());

	private static Hotel instance;
	
	public static Hotel getInstance() {
		if(instance==null) {
			instance=new Hotel();
		}
		return instance;
	}
	
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
		for (int i = 0; i < roomService.getAllRoom().size(); i++) {
			if (roomService.getAllRoom().get(i).getIsFree() == true) {
				Printer.println(roomService.getAllRoom().get(i).toString());

			}
		}
	}

	public void printGuestOptions(Integer guestId) {
		Printer.println("Options of guest " + guestService.getGuestById(guestId).getName());
		Printer.printArray(guestService.getGuestOptions(guestId));
	}

	public void printGuestsRooms() {
		Printer.printArray(historyService.getHistory());

	}

	public void addOption(IOption option) {
		optionService.addOption(option);

	}

	public void printOptionList() {
		Printer.println("  Option list:");
		Printer.printArray(optionService.getOption());
	}

	public void addGuest(String name, String lastName) {
		guestService.addGuest(new Guest(name, lastName));
	}

	public String printNumberOfGuests() {
		return "Number of guests"+guestService.getNumberOfGuests();
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {
		historyService.settleGuestInRoom(guestId, roomId, dateOfArival, evictDate);

	}

	public void addOptionToGuest(Integer optionId, Integer guestId) {

		guestService.addOptionToGuest(optionId, guestId);
	}

	public void addHistory(IHistory history) {
		historyService.addHistory(history);
	}

	public void sortedGuestByName() {
		guestService.sortGuests(new SortedByName());
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
		Printer.println("number of rooms " + roomService.getNumberOfRooms());
	}

	public void printNumberOfFreeRooms() {
		Printer.println("number of free rooms " + roomService.getNumberOfFreeRooms());
	}

	public void addRoom(IRoom room) {
		roomService.addRoom(room);
	}

	public void sortedRoomsByPrice() {
		roomService.sortRooms(new SortedRoomByPrice());
		Printer.println("   Room list sorted by price:");
		Printer.printArray(roomService.getAllRoom());
	}

	public void sortedRoomsByCopaciti() {
		roomService.sortRooms(new SortedByCopacity());
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

		ArrayList<IRoom> rooms = (ArrayList<IRoom>) historyService.getFreeRoomOnDate(date.getTime());
		Printer.printArray(rooms);
	}

	public void evictGuestFromRoom(Integer guestId, Integer roomId) {
		historyService.evictGuestFromRoom(guestId, roomId);
	}

	public void printLastVisiors(Integer id) {
		Printer.println("last guests of the room (" + roomService.getRooms().getRoomById(id).getId() + ")");
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