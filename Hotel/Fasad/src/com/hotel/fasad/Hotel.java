package com.hotel.fasad;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.Analyzer;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.api.service.IGuestService;
import com.hotel.api.service.IHistoryService;
import com.hotel.api.service.IOptionService;
import com.hotel.api.service.IRoomService;
import com.hotel.been.Guest;
import com.hotel.been.Option;
import com.hotel.been.Room;
import com.hotel.configurations.Configuration;
import com.hotel.di.DependecyInjector;
import com.hotel.service.OptionService;
import com.hotel.utils.Connector;
import com.hotel.utils.CsvWorker;

public class Hotel {
	private IRoomService roomService;
	private IOptionService optionService;
	private IGuestService guestService;
	private IHistoryService historyService;

	private final static Logger logger = Logger.getLogger(Hotel.class);
	private static Hotel instance;
	private String PATH_TO_CSV;
	private String propertiPath = "../Fasad/data/configurations.properties";
	private Connector connect = Connector.getinstance();

	private Hotel() {

		roomService = (IRoomService) DependecyInjector.inject(IRoomService.class);
		optionService = (IOptionService) DependecyInjector.inject(IOptionService.class);
		guestService = (IGuestService) DependecyInjector.inject(IGuestService.class);
		historyService =  (IHistoryService) DependecyInjector.inject(IHistoryService.class);
		Configuration.loadConfiguration(propertiPath);
		PATH_TO_CSV=String.valueOf(Configuration.getProperties("PATH_TO_CSV"));
		
	}

	public static Hotel getInstance() {
		if (instance == null) {
			instance = new Hotel();
		}
		return instance;
	}
	
	public String exportGuests() {
		try {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Guest");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IGuest> guests = getGuests();
		writer.comment("id;name;lastName");
		for(int i=0;i<guests.size();i++) {
			writer.write(guests.get(i));
		}
		return "data was exported along the way"+path;	
		}catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	
	public String importGuest(){
		try {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Guest");
		List<IGuest> guests =getGuests();
		List<IGuest> guestsImport = new ArrayList<>();
		IGuestDAO guestDao = guestService.getGuest();
		CsvWorker.Reader reader = new CsvWorker.Reader(path);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			guestsImport.add(new Guest(reader.read().get(i)));
		}

		for (int i = 0; i < guests.size(); i++) {	
			guestDao.updute(connect.getConection(),guestsImport.get(i));;
		}
		for (int i = guests.size(); i < reader.read().size(); i++) {
			guestDao.create(connect.getConection(),guestsImport.get(i));
		}
		return "data was imported from"+path;
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
		
	}

	public synchronized String addGuest(String name, String lastName){
		try {
		guestService.addGuest(new Guest(name, lastName));
		return "Guest was added";
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getNumberOfGuests(){
		try {
		return guestService.getNumberOfGuests().toString();
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public List<IGuest> getGuests(){
		try {
		return guestService.getGuests();
		}
		catch(Exception e) {
			logger.error(e);
			return null;
		}
	}

	public IGuest getGuestById(String idStr){
		try {
		Integer id = Integer.valueOf(idStr);
		return guestService.getGuestById(id);
		}
		catch(Exception e) {
			logger.error(e);
			return null;
		}
	}

	public String getGuestOptions(String guestIdStr){
		try {
		Integer guestId = Integer.valueOf(guestIdStr);
		return guestService.getGuestOptions(guestId).toString();
		}
		catch(Exception e) {
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

	public String sortedGuests(String name){
		try {
		return guestService.sortGuests(name).toString();
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	
	public synchronized String remuveGuest(String idStr){
		try {
		Integer id = Integer.valueOf(idStr);
		guestService.removeGuest(id);
		return "Guest was removed";
		}catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getTotalPayment(String idStr){
		try {
		Integer id = Integer.valueOf(idStr);
		return historyService.getTotalPayment(id).toString();
		}catch(Exception e) {
			logger.error(e);
			return e.getMessage();
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
		try {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Room");
		List<IRoom> rooms = getAllRooms();
		List<IRoom> roomsImport = new ArrayList<>();
		IRoomDAO roomDao = roomService.getRooms();
		CsvWorker.Reader reader = new CsvWorker.Reader(path);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			roomsImport.add(new Room(reader.read().get(i)));
		}

		for (int i = 0; i < rooms.size(); i++) {
			roomDao.updute(connect.getConection(),roomsImport.get(i));
		}
		for (int i = rooms.size(); i < reader.read().size(); i++) {
			roomDao.create(connect.getConection(),roomsImport.get(i));
		}
		return "data was imported from "+path;
		}catch(Exception e) {
			return e.getMessage();
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

	public List<IRoom> getAllRooms() {
		try {
			return roomService.getAllRoom();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}

	public IRoom getRoomById(String idStr) {
		try {
		Integer id = Integer.valueOf(idStr);
		return roomService.getRooms().getById(connect.getConection(),id);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public String removeRoom(String idStr) {
		Integer id = Integer.valueOf(idStr);
	    try {
			roomService.remove(id);
			return "Room "+idStr+" was removed";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}

	public List<IRoom> getFreeRooms() {
		try {
			return roomService.getFreeRooms();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}

	public Integer getNumberOfRooms() {
		try {
			return roomService.getNumberOfRooms();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Integer getNumberOfFreeRooms() {
		try {
			return roomService.getNumberOfFreeRooms();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public List<IRoom> sortRooms(String name) {
		
		try {
			return roomService.sortRooms(name);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public synchronized String chengePriceOfRoom(String idStr, String priceStr) {
		Integer id = Integer.valueOf(idStr);
		Double price = Double.valueOf(priceStr);
		 try {
			roomService.chengePriseOfRoom(id, price);
			return "Price was changed";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		} 
	}
	
	public synchronized String changeRoomStatus(String id, String n) {
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		IRoom room=getRoomById(id);
		String status = null;
		try {
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
			roomService.getRooms().updute(connect.getConection(),room);
		}
		return "New status of the room "+status;
		}catch(Exception e) {
			logger.error("Can't chenge status: ",e);
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
			return "bad";
		}
	}
	
	public synchronized String clone(String idStr, String num) {
		try {
			Integer number=Integer.valueOf(num);
		IRoom room = getRoomById(idStr);
		room.setNumber(number);
	    roomService.clone(room);
		return "sucsess";
		}catch(Exception e) {
			logger.error(e);
			return "something bad";
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public synchronized String settleGuestInRoom(String guestIdStr, String roomIdStr, String arivalDayStr, String arivalMonthStr, String arivalYearStr,
			String evictDayStr, String evictMonthStr, String evictYearStr ) {
		try {
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
		}catch(Exception e) {
		    logger.error(e);
			return e.getMessage();
		}
	}

	public  List<IRoom> getFreeRoomsOnDate(String date){
		try {

			List<IRoom> rooms =  historyService.getFreeRoomOnDate(date);		                                                  
			return rooms;
		} catch ( Exception e) {
			logger.error("Exception in the method getFreeRoomsOnDate: ", e);
			return null;
		}
	}

	public synchronized void addHistory(IHistory history){
		try {
		historyService.addHistory(history);
		}
		catch(Exception e) {
			logger.error(e);
		}
	}

	public synchronized String evictGuestFromRoom(String guestIdStr, String roomIdStr){
		try {
		Integer guestId = Integer.valueOf(guestIdStr);
		Integer roomId = Integer.valueOf(roomIdStr);
		historyService.evictGuestFromRoom(guestId, roomId);
		return "the guest was evicted from the room";
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public String getHistory(){
		try {
		return historyService.getHistory().toString();
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	public String exportOptions(){
		try {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Option");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IOption> options = getAllOptions();
		writer.comment("id;name;price");
		for(int i=0;i<options.size();i++) {
			writer.write(options.get(i));
		}
		return "data was exported along the way"+path;
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	
	public String importOptions(){
		try {
		String path=PATH_TO_CSV+Analyzer.getNameOfBeen("Option");
		List<IOption> options = getAllOptions();
		List<IOption> optionsImport = new ArrayList<>();
		IOptionDAO optionDao = OptionService.getInstance().getOptions();
		CsvWorker.Reader reader = new CsvWorker.Reader(path);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			optionsImport.add(new Option(reader.read().get(i)));
		}

		for (int i = 0; i < options.size(); i++) {
			optionDao.updute(connect.getConection(),optionsImport.get(i));
		}
		for (int i = options.size(); i < reader.read().size(); i++) {
			optionDao.create(connect.getConection(),optionsImport.get(i));
		}
		return "data was imported from"+path;
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	
	public String getOptionById(String idStr) {
		try {
		Integer id = Integer.valueOf(idStr);
		return optionService.getOptions().getById(connect.getConection(),id).toString();
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public synchronized String addOption(String name, String priceStr) {
		try {
			Double price = Double.valueOf(priceStr);
		optionService.addOption(new Option(name, price));
		return "the option thas added";
		}
		catch(Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	public List<IOption> getAllOptions(){
		try {
		return optionService.getOption();
		}
		catch(Exception e) {
			logger.error(e);
			return null;
		}
	}

}