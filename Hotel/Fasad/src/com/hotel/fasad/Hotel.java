package com.hotel.fasad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.api.service.IGuestService;
import com.hotel.api.service.IHistoryService;
import com.hotel.api.service.IOptionService;
import com.hotel.api.service.IRoomService;
import com.hotel.been.Guest;
import com.hotel.been.Option;
import com.hotel.been.Room;
import com.hotel.comparator.SortedByCopacity;
import com.hotel.comparator.SortedByName;
import com.hotel.comparator.SortedByStars;
import com.hotel.comparator.SortedRoomByPrice;
import com.hotel.service.*;
import com.hotel.utils.Printer;

public class Hotel {
	private IRoomService roomService ;
	private IOptionService optionService;
	private IGuestService guestService;
	private IHistoryService historyService;

	private static Hotel instance;
	
	private Hotel() {
		roomService = new RoomService();
		optionService = new OptionService();
		guestService = new GuestService(optionService.getOptions());
		historyService = new HistoryService(guestService.getGuest(), roomService.getRooms());
		
	}
	
	public static Hotel getInstance() {
		if(instance==null) {
			instance=new Hotel();
		}
		return instance;
	}
	
	public void addGuest(String name, String lastName) {
		guestService.addGuest(new Guest(name, lastName));
	}

	public String getNumberOfGuests() {
		return "Number of guests"+guestService.getNumberOfGuests();
	}
	
	public List<IGuest> getGuests() {
		return guestService.getGuests();
	}
	
	public IGuest getGuestById(Integer id) {
		return guestService.getGuestById(id);
	}
	
	public List<IOption> getGuestOptions(Integer guestId) {
		return guestService.getGuestOptions(guestId);
	}
	
	public void addOptionToGuest(Integer optionId, Integer guestId) {

		guestService.addOptionToGuest(optionId, guestId);
	}
	
	public List<IGuest> sortedGuestByName() {
		guestService.sortGuests(new SortedByName());
		return guestService.getGuests();

	}
	
	public Double getTotalPayment(Integer guestId) {
		Printer.println(guestService.getGuestById(guestId).toString() + " totalPayment "
				+ historyService.getTotalPayment(guestId) + "$");
		return historyService.getTotalPayment(guestId);
	}
	//--------------------------------------------------------------------------------------------------------------------
	public void addRoom(Integer copacity, Integer numberOfStars, Double price) {
		roomService.addRoom(new Room(copacity, numberOfStars, price));
	}
	
	public List<IRoom> getAllRooms() {
		return roomService.getAllRoom();
	}

	public IRoom getRoonById(Integer id) {
		return roomService.getRooms().getRoomById(id);
	}
	
	public List<IRoom> getFreeRooms() {
		return roomService.getFreeRooms();
	}
	
	public Integer getNumberOfRooms() {
		return roomService.getNumberOfRooms();
	}

	public Integer getNumberOfFreeRooms() {
		return roomService.getNumberOfFreeRooms();
	}

	public List<IRoom> sortedRoomsByPrice() {
		roomService.sortRooms(new SortedRoomByPrice());
		return roomService.getAllRoom();
	}

	public List<IRoom> sortedRoomsByCopaciti() {
		roomService.sortRooms(new SortedByCopacity());
		return roomService.getAllRoom();
	}

	public List<IRoom> sortedRoomsByStars() {
		roomService.sortRooms(new SortedByStars());
		return roomService.getAllRoom();
	}
	
/*	public void chengeRoomStatus(Integer id, RoomStatus status) {
		roomService.getRooms().getRoomById(id).setStatus(status);

	}*/

	public void chengePriceOfRoom(Integer id, Double price) {
		roomService.chengePriseOfRoom(id, price);
	}
	
	public List<IGuest> getLastGuests(Integer id) {
		return roomService.getLastGuests(id);
	}
//---------------------------------------------------------------------------------------------------
	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {
		historyService.settleGuestInRoom(guestId, roomId, dateOfArival, evictDate);
	}
	
	public List<IRoom> getFreeRoomsOnDate(Calendar date) {

		ArrayList<IRoom> rooms = (ArrayList<IRoom>) historyService.getFreeRoomOnDate(date.getTime());
		return rooms;
	}

	public List<IHistory> printGuestsRooms() {
		return historyService.getHistory();
	}
	
	public void addHistory(IHistory history) {
		historyService.addHistory(history);
	}
	
	public void evictGuestFromRoom(Integer guestId, Integer roomId) {
		historyService.evictGuestFromRoom(guestId, roomId);
	}
//-----------------------------------------------------------------------------------------------------------------------------
	public IOption getOptionById(Integer id) {
		return optionService.getOptions().getOptionById(id);
	}

	public void addOption(String name, Double price) {
		optionService.addOption(new Option(name, price));
	}

	public List<IOption> getAllOptions() {
		return optionService.getOption();
	}

	public void writeInFile() {

		roomService.writeInFile();
		guestService.writeInFile();
		optionService.writeInFile();

	}

	public void readFromFile() {
		guestService.readFromFile();
		roomService.readFromFile();
		optionService.readFromFile();

	}

}