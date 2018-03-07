package com.hotel.fasad;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Option;
import com.hotel.been.Room;
import com.hotel.configurations.Configuration;
import com.hotel.service.GuestService;
import com.hotel.service.HistoryService;
import com.hotel.service.OptionService;
import com.hotel.service.RoomService;

public class Hotel {
	private RoomService roomService;
	private OptionService optionService;
	private GuestService guestService;
	private HistoryService historyService;

	private final static Logger logger = Logger.getLogger(Hotel.class);
	private static Hotel instance;
	private String PATH_TO_CSV;
	private final String PROPERTIES_PATH = "../Fasad/src/main/resources/configurations.properties";

	private Hotel() {
		roomService = RoomService.getInstance();
		optionService = OptionService.getInstance();
		guestService = GuestService.getInstance();
		historyService = HistoryService.getInstance();
		Configuration.loadConfiguration(PROPERTIES_PATH);
		PATH_TO_CSV = String.valueOf(Configuration.getProperties("PATH_TO_CSV"));

	}

	public static Hotel getInstance() {
		if (instance == null) {
			instance = new Hotel();
		}
		return instance;
	}

	public String exportGuests() {
		try {
			return guestService.exportGuests(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error" + e.getMessage();
		}
	}

	public synchronized String importGuest() {
		try {
			return guestService.importGuest(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error: " + e.getMessage();
		}

	}

	public synchronized String addGuest(String name, String lastName) {
		try {
			guestService.addGuest(new Guest(name, lastName));
			return "Guest was added";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getNumberOfGuests() {
		try {
			return guestService.getNumberOfGuests().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getGuests() {
		try {
			return guestService.getGuests().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getGuestById(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			return guestService.getGuestById(id).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getGuestOptions(String guestIdStr) {
		try {
			Integer guestId = Integer.valueOf(guestIdStr);
			return guestService.getGuestOptions(guestId).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
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

	public String sortedGuests(String name) {
		try {
			return guestService.sortGuests(name).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public synchronized String remuveGuest(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			guestService.removeGuest(id);
			return "Guest was removed";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getTotalPayment(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			return historyService.getTotalPayment(id).toString();
		} catch (Exception e) {
			logger.error(e);
			return "Error: " + e.getMessage();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------
	public String exportRooms() {
		try {
			return roomService.exportRooms(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error" + e.getMessage();
		}
	}

	public synchronized String importRooms() {
		try {
			return roomService.importRooms(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error: " + e.getMessage();
		}
	}

	public synchronized String addRoom(String numberStr, String copacityStr, String numberOfStarsStr, String priceStr) {
		Integer number = Integer.valueOf(numberStr);
		Integer copacity = Integer.valueOf(copacityStr);
		Integer numberOfStars = Integer.valueOf(numberOfStarsStr);
		Double price = Double.valueOf(priceStr);
		try {
			roomService.addRoom(new Room(number, copacity, numberOfStars, price));
			return "Room was added";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String getAllRooms() {
		try {
			return roomService.getAllRooms().toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}

	}

	public String getRoomById(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			return roomService.getById(id).toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}

	public String removeRoom(String idStr) {
		Integer id = Integer.valueOf(idStr);
		try {
			roomService.remove(id);
			return "Room " + idStr + " was removed";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}

	public String getFreeRooms() {
		try {
			return roomService.getFreeRooms().toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}

	}

	public String getNumberOfRooms() {
		try {
			return roomService.getNumberOfRooms().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getNumberOfFreeRooms() {
		try {
			return roomService.getNumberOfFreeRooms().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String sortRooms(String name) {

		try {
			return roomService.sortRooms(name).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public synchronized String chengePriceOfRoom(String idStr, String priceStr) {
		Integer id = Integer.valueOf(idStr);
		Double price = Double.valueOf(priceStr);
		try {
			roomService.chengePriseOfRoom(id, price);
			return "Price was changed";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public synchronized String changeRoomStatus(String idStr, String n) {

		Integer id = Integer.valueOf(idStr);
		try {
			return roomService.changeRoomStatus(id, n);
		} catch (Exception e) {
			logger.error("Can't chenge status: ", e);
			return e.getMessage();
		}
	}

	public String getLastGuests(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			Integer num = Integer.valueOf(Configuration.getProperties("NUMBER_OF_RECORDS"));
			return roomService.getLastGuests(id, num).toString();
		} catch (Exception e) {
			logger.error("Exception in the method getLastGuests", e);
			return e.getMessage();
		}
	}

	public synchronized String clone(String idStr, String num) {
		try {
			Integer number = Integer.valueOf(num);
			Integer id = Integer.valueOf(idStr);
			roomService.clone(id, number);
			return "sucsess";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public synchronized String settleGuestInRoom(String guestIdStr, String roomIdStr, String arivalDayStr,
			String arivalMonthStr, String arivalYearStr, String evictDayStr, String evictMonthStr,
			String evictYearStr) {
		try {
			Integer guestId = Integer.valueOf(guestIdStr);
			Integer roomId = Integer.valueOf(roomIdStr);
			Integer arivalDay = Integer.valueOf(arivalDayStr);
			Integer arivalMonth = Integer.valueOf(arivalMonthStr);
			Integer arivalYear = Integer.valueOf(arivalYearStr);
			Integer evictDay = Integer.valueOf(evictDayStr);
			Integer evictMonth = Integer.valueOf(evictMonthStr);
			Integer evictYear = Integer.valueOf(evictYearStr);
			historyService.settleGuestInRoom(guestId, roomId, new GregorianCalendar(arivalYear, arivalMonth, arivalDay),
					new GregorianCalendar(evictYear, evictMonth, evictDay));
			return "the guest was settled in the room";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getFreeRoomsOnDate(String dayStr, String manthStr, String yearStr) {
		Integer day = Integer.valueOf(dayStr);
		Integer manth = Integer.valueOf(manthStr);
		Integer year = Integer.valueOf(yearStr);

		try {

			List<Room> rooms = (List<Room>) historyService
					.getFreeRoomOnDate(new GregorianCalendar(year, manth, day).getTime());
			return rooms.toString();
		} catch (Exception e) {
			logger.error("Exception in the method getFreeRoomsOnDate: ", e);
			return "No free rooms on this date "+e.getMessage();
		}
		
	}

	public synchronized void addHistory(History history) {
		try {
			historyService.addHistory(history);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public synchronized String evictGuestFromRoom(String guestIdStr, String roomIdStr) {
		try {
			Integer guestId = Integer.valueOf(guestIdStr);
			Integer roomId = Integer.valueOf(roomIdStr);
			historyService.evictGuestFromRoom(guestId, roomId);
			return "the guest was evicted from the room";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getHistory() {
		try {
			return historyService.getHistory().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	public String exportOptions() {
		try {
			return optionService.exportOptions(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public synchronized String importOptions() {
		try {
			return optionService.importOptions(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error: " + e.getMessage();
		}
	}

	public String getOptionById(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			return optionService.getById(id).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public synchronized String addOption(String name, String priceStr) {
		try {
			Double price = Double.valueOf(priceStr);
			optionService.addOption(new Option(name, price));
			return "the option thas added";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getAllOptions() {
		try {
			return optionService.getOption().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

}