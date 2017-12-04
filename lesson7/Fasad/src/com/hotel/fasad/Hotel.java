package com.hotel.fasad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
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
import com.hotel.configurations.Configuration;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.OptionRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.serialization.SerealizationMasrter;
import com.hotel.service.GuestService;
import com.hotel.service.HistoryService;
import com.hotel.service.OptionService;
import com.hotel.service.RoomService;
import com.hotel.utils.CsvWorker;

public class Hotel {
	private static IRoomService roomService;
	private static IOptionService optionService;
	private static IGuestService guestService;
	private static IHistoryService historyService;

	private final static Logger logger = Logger.getLogger(Hotel.class);
	private static Hotel instance;

	private Hotel(IRoomService service, IOptionService oService,IGuestService gService,IHistoryService hService) {

	    roomService=service;
	    optionService = oService;
	    guestService = gService;
	    historyService = hService;
		Configuration.loadConfiguration();
		try {
			SerealizationMasrter.demarshaling();
		} catch (ClassNotFoundException |IOException e) {
			logger.error("File to demarshaling not found "+e.getMessage());
		} 
	}

	public static Hotel getInstance() {
		if (instance == null) {
			instance = new Hotel(new RoomService(),new OptionService(),new GuestService(OptionRepository.getInstance()),
					                         new HistoryService(GuestRepository.getInstance(), RoomRepository.getInstance()));
		}
		return instance;
	}
	
	public void exportGuests(String path) {
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IGuest> guests = getGuests();
		writer.comment("id;name;lastName");
		for(int i=0;i<guests.size();i++) {
			writer.write(guests.get(i));
		}	
	}
	
	public void importGuest(String path) {
		List<IGuest> guests =getGuests();
		List<IGuest> guestsImport = new ArrayList<>();
		CsvWorker.Reader reader = new CsvWorker.Reader(path);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			guestsImport.add(new Guest(reader.read().get(i)));
		}

		for (int i = 0; i < guests.size(); i++) {
			Collections.replaceAll(guests, guests.get(i), guestsImport.get(i));
		}
		for (int i = guests.size(); i < reader.read().size(); i++) {
			guests.add(guestsImport.get(i));
		}
		
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
		} catch (Exception e) {
			logger.error("Exception in the method addOptionToGuest", e);
		}
	}

	public List<IGuest> sortedGuestByName() {
		guestService.sortGuests(new SortedByName());
		return guestService.getGuests();

	}
	
	public void remuveGuest(Integer id) {
		guestService.removeGuest(id);
	}

	public Double getTotalPayment(Integer guestId) {
		return historyService.getTotalPayment(guestId);
	}

	// --------------------------------------------------------------------------------------------------------------------
	public void exportRooms(String path) {
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IRoom> rooms = getAllRooms();
		writer.comment("id;copacity;stars;price;isFree;status");
		for (int i = 0; i < rooms.size(); i++) {
			writer.write(rooms.get(i));
		}
	}
	
	public void importRooms(String path) {
		List<IRoom> rooms = getAllRooms();
		List<IRoom> roomsImport = new ArrayList<>();
		CsvWorker.Reader reader = new CsvWorker.Reader(path);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			roomsImport.add(new Room(reader.read().get(i)));
		}

		for (int i = 0; i < rooms.size(); i++) {
			Collections.replaceAll(rooms, rooms.get(i), roomsImport.get(i));
		}
		for (int i = rooms.size(); i < reader.read().size(); i++) {
			rooms.add(roomsImport.get(i));
		}
	}
	
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
	
	public void changeRoomStatus(Integer id, Integer n) {
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		IRoom room=getRoonById(id);
		if(changeStatus) {
			switch(n) {
			case(1):
				room.setStatus(RoomStatus.OPEN);
			       break;
			case(2):
				room.setStatus(RoomStatus.CLOSE);
			        break;
			case(3):
				room.setStatus(RoomStatus.SERVICED);
			        break;
			case(4):
				room.setStatus(RoomStatus.REPAIRABLE);
			        break;
			}
			
		}
	}

	public List<IGuest> getLastGuests(Integer id) {
		try {
			Integer num = Integer.valueOf(Configuration.getProperties("NUMBER_OF_RECORDS"));
			return roomService.getLastGuests(id, num);
		} catch (Exception e) {
			logger.error("Exception in the method getLastGuests", e);
		}
		return null;
	}
	
	public IRoom clone(Integer id, String answer, Integer copacity, Integer stars, Double price) throws CloneNotSupportedException {
		IRoom clon = roomService.clone(id);
		if(answer.equals("Y")|answer.equals("y")) {
				clon.setCopacity(copacity);
				clon.setNumberOfStars(stars);
				clon.setPrice(price);
		}
		getAllRooms().add(clon);
		return clon;

	}

	// ---------------------------------------------------------------------------------------------------
	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {
		historyService.settleGuestInRoom(guestId, roomId, dateOfArival, evictDate);
	}

	public List<IRoom> getFreeRoomsOnDate(Calendar date) {

		try {

			ArrayList<IRoom> rooms = (ArrayList<IRoom>) historyService.getFreeRoomOnDate(date.getTime());
			return rooms;
		} catch ( NoSuchElementException e) {
			logger.error("Exception in the method getFreeRoomsOnDate: ", e);
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
	public void exportOptions(String path) {
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IOption> options = getAllOptions();
		writer.comment("id;name;price");
		for(int i=0;i<options.size();i++) {
			writer.write(options.get(i));
		}
	}
	
	public void importOptions(String path) {
		List<IOption> options = getAllOptions();
		List<IOption> optionsImport = new ArrayList<>();
		CsvWorker.Reader reader = new CsvWorker.Reader(path);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			optionsImport.add(new Option(reader.read().get(i)));
		}

		for (int i = 0; i < options.size(); i++) {
			Collections.replaceAll(options, options.get(i), optionsImport.get(i));
		}
		for (int i = options.size(); i < reader.read().size(); i++) {
			options.add(optionsImport.get(i));
		}
	}
	
	public IOption getOptionById(Integer id) {
		return optionService.getOptions().getOptionById(id);
	}

	public void addOption(String name, Double price) {
		optionService.addOption(new Option(name, price));
	}

	public List<IOption> getAllOptions() {
		return optionService.getOption();
	}

	public void exit() throws FileNotFoundException, IOException {
		SerealizationMasrter.marshaling(getGuests(), getAllRooms(), getAllOptions(), getHistory());
	}

	public void demarshalingFrom() throws ClassNotFoundException, IOException {
		SerealizationMasrter.demarshaling();
	}

}