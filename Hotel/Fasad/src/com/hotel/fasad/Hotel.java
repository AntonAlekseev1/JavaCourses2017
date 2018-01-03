package com.hotel.fasad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import com.hotel.Analyzer;
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
import com.hotel.di.DependecyInjector;
import com.hotel.serialization.SerealizationMasrter;
import com.hotel.utils.CsvWorker;

public class Hotel {
	private IRoomService roomService;
	private IOptionService optionService;
	private IGuestService guestService;
	private IHistoryService historyService;

	private final static Logger logger = Logger.getLogger(Hotel.class);
	private static Hotel instance;
	private String PATH_TO_CSV;

	private Hotel() {

		roomService = (IRoomService) DependecyInjector.inject(IRoomService.class);
		optionService = (IOptionService) DependecyInjector.inject(IOptionService.class);
		guestService = (IGuestService) DependecyInjector.inject(IGuestService.class);
		historyService =  (IHistoryService) DependecyInjector.inject(IHistoryService.class);
		Configuration.loadConfiguration();
		PATH_TO_CSV=String.valueOf(Configuration.getProperties("PATH_TO_CSV"));
		try {
			SerealizationMasrter.demarshaling();
		} catch (ClassNotFoundException |IOException e) {
			logger.error("File to demarshaling not found "+e.getMessage());
		} 
	}

	public static Hotel getInstance() {
		if (instance == null) {
			instance = new Hotel();
		}
		return instance;
	}
	
	public String exportGuests() {
		
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Guest");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IGuest> guests = getGuests();
		writer.comment("id;name;lastName");
		for(int i=0;i<guests.size();i++) {
			writer.write(guests.get(i));
		}
		return "data was exported along the way"+path;	
	}
	
	public String importGuest() {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Guest");
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
		return "data was imported from"+path;
		
	}

	public synchronized String addGuest(String name, String lastName) {
		guestService.addGuest(new Guest(name, lastName));
		return "Guest was added";
	}

	public Integer getNumberOfGuests() {
		return guestService.getNumberOfGuests();
	}

	public List<IGuest> getGuests() {
		return guestService.getGuests();
	}

	public IGuest getGuestById(String idStr) {
		
		Integer id = Integer.valueOf(idStr);
		return guestService.getGuestById(id);
	}

	public String getGuestOptions(String guestIdStr) {//List<IOption>
		IGuest guest = getGuestById(guestIdStr);
		if(guest.getHistory()!=null) {
			if(guest.getHistory().getOptions()!=null) {
		Integer guestId = Integer.valueOf(guestIdStr);
		return guestService.getGuestOptions(guestId).toString();
			}
			}else {
				return "This guest does not have any options";
			}
		return null;
	}

	public String addOptionToGuest(String optionIdStr, String guestIdStr) {
		
		Integer optionId = Integer.valueOf(optionIdStr);
		Integer guestId = Integer.valueOf(guestIdStr);
		try {
			guestService.addOptionToGuest(optionId, guestId);
		} catch (Exception e) {
			logger.error("Exception in the method addOptionToGuest", e);
		}
		return "Option was added to guest";
	}

	public List<IGuest> sortedGuestByName() {
		guestService.sortGuests(new SortedByName());
		return guestService.getGuests();

	}
	
	public synchronized String remuveGuest(String idStr) {
		Integer id = Integer.valueOf(idStr);
		guestService.removeGuest(id);
		return "Guest was removed";
	}

	public String getTotalPayment(String idStr) {
		if(getGuestById(idStr).getHistory()!=null) {
		Integer id = Integer.valueOf(idStr);
		return historyService.getTotalPayment(id).toString();
		}else {
			return "This guest is not settled in any of the rooms";
		}
	}

	// --------------------------------------------------------------------------------------------------------------------
	public String exportRooms() {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Room");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IRoom> rooms = getAllRooms();
		writer.comment("id;copacity;stars;price;isFree;status");
		for (int i = 0; i < rooms.size(); i++) {
			writer.write(rooms.get(i));
		}
		return "data was exported along the way"+path;
	}
	
	public String importRooms() {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Room");
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
		return "data was imported from "+path;
	}
	
	public synchronized String addRoom(String copacityStr, String numberOfStarsStr, String priceStr) {
		Integer copacity = Integer.valueOf(copacityStr);
		Integer numberOfStars = Integer.valueOf(numberOfStarsStr);
		Double price = Double.valueOf(priceStr);
		roomService.addRoom(new Room(copacity, numberOfStars, price));
		return "Room was added";
	}

	public List<IRoom> getAllRooms() {
		return roomService.getAllRoom();
	}

	public IRoom getRoomById(String idStr) {
		Integer id = Integer.valueOf(idStr);
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

	public synchronized String chengePriceOfRoom(String idStr, String priceStr) {
		Integer id = Integer.valueOf(idStr);
		Double price = Double.valueOf(priceStr);
		 roomService.chengePriseOfRoom(id, price);
		 return "Price was changed";
	}
	
	public synchronized String changeRoomStatus(String id, String n) {
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		IRoom room=getRoomById(id);
		String status = null;
		if(changeStatus) {
			switch(n) {
			case("1"):
				room.setStatus(RoomStatus.OPEN);
			status = "Open";
			       break;
			case("2"):
				room.setStatus(RoomStatus.CLOSE);
			status = "Close";
			        break;
			case("3"):
				room.setStatus(RoomStatus.SERVICED);
			status = "Serviced";
			        break;
			case("4"):
				room.setStatus(RoomStatus.REPAIRABLE);
			status = "Repairable";
			        break;
			}
			
		}
		return "New status of the room "+status;
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
	
	public synchronized IRoom clone(String idStr, String answer, String copacityStr, String starsStr, String priceStr) throws CloneNotSupportedException {
		Integer id=Integer.valueOf(idStr);
		Integer copacity=Integer.valueOf(copacityStr);
		Integer stars=Integer.valueOf(starsStr);
		Double price=Double.valueOf(priceStr);
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
	public synchronized String settleGuestInRoom(String guestIdStr, String roomIdStr, String arivalDayStr, String arivalMonthStr, String arivalYearStr,
			String evictDayStr, String evictMonthStr, String evictYearStr ) {
		Integer guestId = Integer.valueOf(guestIdStr);
		Integer roomId=Integer.valueOf(roomIdStr);
		Integer arivalDay=Integer.valueOf(arivalDayStr);
		Integer arivalMonth=Integer.valueOf(arivalMonthStr);
		Integer arivalYear=Integer.valueOf(arivalYearStr);
		Integer evictDay=Integer.valueOf(evictDayStr);
		Integer evictMonth=Integer.valueOf(evictMonthStr);
		Integer evictYear=Integer.valueOf(evictYearStr);
		historyService.settleGuestInRoom(guestId, roomId, new GregorianCalendar(arivalYear, arivalMonth, arivalDay),
                new GregorianCalendar(evictYear, evictMonth, evictDay));
		return "the guest was settled in the room";
	}

	public  String getFreeRoomsOnDate(String dayStr, String manthStr, String yearStr) {
		Integer day=Integer.valueOf(dayStr);
		Integer manth=Integer.valueOf(manthStr);
		Integer year=Integer.valueOf(yearStr);

		try {

			ArrayList<IRoom> rooms = (ArrayList<IRoom>) historyService.getFreeRoomOnDate(
					                                                  new GregorianCalendar(year,manth,day).getTime());
			return rooms.toString();
		} catch ( NoSuchElementException e) {
			logger.error("Exception in the method getFreeRoomsOnDate: ", e);
		}
		return "No free rooms on this date";
	}

	public List<IHistory> getGuestsRooms() {
		return historyService.getHistory();
	}

	public synchronized void addHistory(IHistory history) {
		historyService.addHistory(history);
	}

	public synchronized String evictGuestFromRoom(String guestIdStr, String roomIdStr) {
		Integer guestId = Integer.valueOf(guestIdStr);
		Integer roomId = Integer.valueOf(roomIdStr);
		historyService.evictGuestFromRoom(guestId, roomId);
		return "the guest was evicted from the room";
	}

	public List<IHistory> getHistory() {
		return historyService.getHistory();
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	public String exportOptions() {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Option");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IOption> options = getAllOptions();
		writer.comment("id;name;price");
		for(int i=0;i<options.size();i++) {
			writer.write(options.get(i));
		}
		return "data was exported along the way"+path;
	}
	
	public String importOptions() {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Option");
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
		return "data was imported from"+path;
	}
	
	public IOption getOptionById(String idStr) {
		Integer id = Integer.valueOf(idStr);
		return optionService.getOptions().getOptionById(id);
	}

	public synchronized String addOption(String name, Double price) {
		optionService.addOption(new Option(name, price));
		return "the option thas added";
	}

	public List<IOption> getAllOptions() {
		return optionService.getOption();
	}

	public synchronized String save() throws FileNotFoundException, IOException {
		SerealizationMasrter.marshaling(getGuests(), getAllRooms(), getAllOptions(), getHistory());
		return "data saved";
	}

	public void demarshalingFrom() throws ClassNotFoundException, IOException {
		SerealizationMasrter.demarshaling();
	}

}