package com.hotel.fasad;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.fasad.IHotel;
import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.LogEntity;
import com.hotel.been.Option;
import com.hotel.been.Room;
import com.hotel.been.User;
import com.hotel.configurations.Configuration;
import com.hotel.service.GuestService;
import com.hotel.service.HistoryService;
import com.hotel.service.LogEntityService;
import com.hotel.service.OptionService;
import com.hotel.service.RoomService;
import com.hotel.service.UserService;
import com.hotel.utils.PasswordEncryptor;
import com.hotel.utils.TokenWorker;

public class Hotel implements IHotel {
	private RoomService roomService;
	private OptionService optionService;
	private GuestService guestService;
	private HistoryService historyService;
	private UserService userService;
	private LogEntityService logEntityService;

	private final static Logger logger = Logger.getLogger(Hotel.class);
	private static Hotel instance;
	private String PATH_TO_CSV;
	private final String PROPERTIES_PATH = "../Fasad/src/main/resources/configurations.properties";

	private Hotel() {
		roomService = RoomService.getInstance();
		optionService = OptionService.getInstance();
		guestService = GuestService.getInstance();
		historyService = HistoryService.getInstance();
		userService = UserService.getInstance();
		logEntityService = LogEntityService.getInstance();
		Configuration.loadConfiguration(PROPERTIES_PATH);
		PATH_TO_CSV = String.valueOf(Configuration.getProperties("PATH_TO_CSV"));

	}

	public static Hotel getInstance() {
		if (instance == null) {
			instance = new Hotel();
		}
		return instance;
	}

	@Override
	public String exportGuests() {
		try {
			return guestService.exportGuests(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error" + e.getMessage();
		}
	}

	@Override
	public synchronized String importGuest() {
		try {
			return guestService.importGuest(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error: " + e.getMessage();
		}

	}

	@Override
	public synchronized String addGuest(String name, String lastName) {
		try {
			guestService.addGuest(new Guest(name, lastName));
			return "Guest was added";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String getNumberOfGuests() {
		try {
			return guestService.getNumberOfGuests().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String getGuests() {
		try {
			return guestService.getGuests().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String getGuestById(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			return guestService.getGuestById(id).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String getGuestOptions(String guestIdStr) {
		try {
			Integer guestId = Integer.valueOf(guestIdStr);
			return guestService.getGuestOptions(guestId).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String addOptionToGuest(Integer optionId, Integer guestId) {

		try {
			guestService.addOptionToGuest(optionId, guestId);
		} catch (Exception e) {
			logger.error("Exception in the method addOptionToGuest", e);
		}
		return "Option was added to guest";
	}

	@Override
	public String sortedGuests(String name) {
		try {
			return guestService.sortGuests(name).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public synchronized String remuveGuest(Integer id) {
		try {
			guestService.removeGuest(id);
			return "Guest was removed";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public Double getTotalPayment(Integer id) throws Exception {
		try {
			return historyService.getTotalPayment(id);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception("Error: " + e.getMessage());
		}
	}

	// --------------------------------------------------------------------------------------------------------------------
	@Override
	public String exportRooms() {
		try {
			return roomService.exportRooms(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error" + e.getMessage();
		}
	}

	@Override
	public synchronized String importRooms() {
		try {
			return roomService.importRooms(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error: " + e.getMessage();
		}
	}

	@Override
	public synchronized String addRoom(Integer number, Integer copacity, Integer stars, Double price) {
		try {
			roomService.addRoom(new Room(number, copacity, stars, price));
			return "Room was added";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String getAllRooms() {
		try {
			return roomService.getAllRooms().toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}

	}

	@Override
	public String getRoomById(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			return roomService.getById(id).toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public String removeRoom(Integer id) {
		try {
			roomService.remove(id);
			return "Room " + id + " was removed";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public String getFreeRooms() {
		try {
			return roomService.getFreeRooms().toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}

	}

	@Override
	public String getNumberOfRooms() {
		try {
			return roomService.getNumberOfRooms().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String getNumberOfFreeRooms() {
		try {
			return roomService.getNumberOfFreeRooms().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String sortRooms(String name) {

		try {
			return roomService.sortRooms(name).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public synchronized String chengePriceOfRoom(Integer id, Double price) {
		try {
			roomService.chengePriseOfRoom(id, price);
			return "Price was changed";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public synchronized String changeRoomStatus(String idStr, String n) {

		Integer id = Integer.valueOf(idStr);
		try {
			return roomService.changeRoomStatus(id, n);
		} catch (Exception e) {
			logger.error("Can't chenge status: ", e);
			return e.getMessage();
		}
	}

	@Override
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

	@Override
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
	@Override
	public synchronized String settleGuestInRoom(Integer guestId, Integer roomId, String arivalDayStr,
			String arivalMonthStr, String arivalYearStr, String evictDayStr, String evictMonthStr,
			String evictYearStr) {
		try {
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

	@Override
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
			return "No free rooms on this date " + e.getMessage();
		}

	}

	@Override
	public synchronized void addHistory(History history) {
		try {
			historyService.addHistory(history);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
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

	@Override
	public List<History> getHistory() throws Exception {
		try {
			return historyService.getHistory();
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e.getMessage());
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String exportOptions() {
		try {
			return optionService.exportOptions(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public synchronized String importOptions() {
		try {
			return optionService.importOptions(PATH_TO_CSV);
		} catch (Exception e) {
			logger.error(e);
			return "Error: " + e.getMessage();
		}
	}

	@Override
	public String getOptionById(String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			return optionService.getById(id).toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public synchronized String addOption(String name, Double price) {
		try {
			optionService.addOption(new Option(name, price));
			return "the option thas added";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String getAllOptions() {
		try {
			return optionService.getOption().toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public synchronized String register(String login, String password) {
		try {
			userService.addUser(new User(login, password));
			return "registration completed successfully";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@Override
	public String signIn(String login, String password) {
		try {
			User user = null;
			String encryptedPassword = PasswordEncryptor.encryptPassword(password);
			user = userService.getUser(login);
			String passvordFromDB = user.getPassword();
			String token = null;
			if (user != null && passvordFromDB.equals(encryptedPassword)) {
				token = TokenWorker.generateToken(login);
			}
			return token;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public String writeLog(String login, String action) {
		try {
			String date = LocalDateTime.now().toString();
			logEntityService.addLog(new LogEntity(date, login, action));
			return "";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}

	}
}