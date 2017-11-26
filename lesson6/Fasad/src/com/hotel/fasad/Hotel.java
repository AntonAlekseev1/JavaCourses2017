package com.hotel.fasad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

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
import com.hotel.configurations.Configuration;
import com.hotel.serialization.SerealizationMasrter;

public class Hotel {
	private IRoomService roomService;
	private IOptionService optionService;
	private IGuestService guestService;
	private IHistoryService historyService;

	final static Logger logger = Logger.getLogger(Hotel.class);
	private static Hotel instance;

	private Hotel() {

		roomService = new RoomService();
		optionService = new OptionService();
		guestService = new GuestService(optionService.getOptions());
		historyService = new HistoryService(guestService.getGuest(), roomService.getRooms());
		Configuration.loadConfiguration();

	}

	public static Hotel getInstance() {
		if (instance == null) {
			instance = new Hotel();
		}
		return instance;
	}

	public IRoom clone(Integer id) throws CloneNotSupportedException {
		return roomService.clone(id);

	}

	public void addGuest(String name, String lastName) {
		guestService.addGuest(new Guest(name, lastName));
	}

	public Integer getNumberOfGuests() {
		return guestService.getNumberOfGuests();
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
		try {
			guestService.addOptionToGuest(optionId, guestId);
		} catch (NullPointerException e) {
			Printer.println("Exception in the method addOptionToGuest: " + e.getMessage());
			logger.error("Exception in the method addOptionToGuest", e);
		}
	}

	public List<IGuest> sortedGuestByName() {
		guestService.sortGuests(new SortedByName());
		return guestService.getGuests();

	}

	public Double getTotalPayment(Integer guestId) {
		return historyService.getTotalPayment(guestId);
	}

	// --------------------------------------------------------------------------------------------------------------------
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

	public void chengePriceOfRoom(Integer id, Double price) {
		roomService.chengePriseOfRoom(id, price);
	}

	public List<IGuest> getLastGuests(Integer id) {
		try {
			Integer num = Integer.valueOf(Configuration.getProperties("NUMBER_OF_RECORDS"));
			return roomService.getLastGuests(id, num);
		} catch (NullPointerException e) {
			logger.error("Exception in the method getLastGuests", e);
		}
		return null;
	}

	// ---------------------------------------------------------------------------------------------------
	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {
		historyService.settleGuestInRoom(guestId, roomId, dateOfArival, evictDate);
	}

	public List<IRoom> getFreeRoomsOnDate(Calendar date) {

		try {

			ArrayList<IRoom> rooms = (ArrayList<IRoom>) historyService.getFreeRoomOnDate(date.getTime());
			return rooms;
		} catch (NullPointerException | NoSuchElementException e) {
			logger.error("Exception in the method getFreeRoomsOnDate: ", e);
			Printer.println("Exception in the method getFreeRoomsOnDate: " + e.getMessage());
		}
		return null;
	}

	public List<IHistory> getGuestsRooms() {
		return historyService.getHistory();
	}

	public void addHistory(IHistory history) {
		historyService.addHistory(history);
	}

	public void evictGuestFromRoom(Integer guestId, Integer roomId) {
		historyService.evictGuestFromRoom(guestId, roomId);
	}

	public List<IHistory> getHistory() {
		return historyService.getHistory();
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	public IOption getOptionById(Integer id) {
		return optionService.getOptions().getOptionById(id);
	}

	public void addOption(String name, Double price) {
		optionService.addOption(new Option(name, price));
	}

	public List<IOption> getAllOptions() {
		return optionService.getOption();
	}

	public void marshalingTo() throws FileNotFoundException, IOException {
		SerealizationMasrter.marshaling(getGuests(), getAllRooms(), getAllOptions(), getHistory());
	}

	public void demarshalingFrom() throws ClassNotFoundException, IOException {
		SerealizationMasrter.demarshaling();
	}

}