package com.hotel.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.hotel.Analyzer;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.dao.IConnectorDao;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.api.service.IRoomService;
import com.hotel.been.Room;
import com.hotel.di.DependecyInjector;
import com.hotel.utils.CsvWorker;

public class RoomService implements IRoomService {

	private static RoomService instance;
	private IRoomDAO roomDao = (IRoomDAO) DependecyInjector.inject(IRoomDAO.class);
	private IConnectorDao connect = (IConnectorDao) DependecyInjector.inject(IConnectorDao.class);

	private RoomService() {

	}

	public static RoomService getInstance() {
		if (instance == null) {
			instance = new RoomService();
		}
		return instance;
	}

	@Override
	public String exportRooms(String pathToCsv) throws Exception {
		String path = pathToCsv + Analyzer.getNameOfBeen("Room");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IRoom> rooms = getAllRooms();
		writer.comment("id;copacity;stars;price;isFree;status");
		for (int i = 0; i < rooms.size(); i++) {
			writer.write(rooms.get(i));
		}
		return "data was exported along the way" + path;
	}

	@Override
	public String importRooms(String pathToCsv) throws Exception {
		Connection connection = connect.getConection();
		try {
			connection.setAutoCommit(false);
			String path = pathToCsv + Analyzer.getNameOfBeen("Room");
			List<IRoom> rooms = getAllRooms();
			List<IRoom> roomsImport = new ArrayList<>();
			IRoomDAO roomDao = getRooms();
			CsvWorker.Reader reader = new CsvWorker.Reader(path);
			reader.read();
			for (int i = 0; i < reader.read().size(); i++) {
				roomsImport.add(new Room(reader.read().get(i)));
			}

			for (int i = 0; i < rooms.size(); i++) {
				roomDao.updute(connect.getConection(), roomsImport.get(i));
			}
			for (int i = rooms.size(); i < reader.read().size(); i++) {
				roomDao.create(connect.getConection(), roomsImport.get(i));
			}
			connection.commit();
			return "data was imported from " + path;
		} catch (Exception e) {
			connection.rollback();
			throw new Exception(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public void addRoom(IRoom room) throws Exception {
		room.setIsFree(true);
		room.setStatus(RoomStatus.OPEN);
		roomDao.create(connect.getConection(), room);
	}

	public void remove(Integer id) throws Exception {
		roomDao.delete(connect.getConection(), id);
	}

	public List<IRoom> getAllRooms() throws Exception {
		return roomDao.getAll(connect.getConection(), "id");
	}

	public List<IRoom> getFreeRooms() throws Exception {
		List<IRoom> allRooms = getAllRooms();
		List<IRoom> freeRooms = new ArrayList<>();
		for (int i = 0; i < allRooms.size(); i++) {
			if (allRooms.get(i).getIsFree() == true) {
				freeRooms.add(allRooms.get(i));
			}
		}
		return freeRooms;
	}

	public IRoomDAO getRooms() {
		return roomDao;
	}

	public String chengePriseOfRoom(Integer roomId, Double price) throws Exception {
		IRoom room = getRooms().getById(connect.getConection(), roomId);
		if (room != null) {
			room.setPrice(price);
			roomDao.updute(connect.getConection(), room);
		}
		return "Price of room " + room.getId() + " is " + room.getPrice();
	}

	public Integer getNumberOfRooms() throws Exception {

		return roomDao.getAll(connect.getConection(), "id").size();
	}

	public Integer getNumberOfFreeRooms() throws Exception {
		List<IRoom> list = roomDao.getAll(connect.getConection(), "id");
		Integer numberOfFreeRooms = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIsFree() == true) {
				numberOfFreeRooms++;
			}
		}
		return numberOfFreeRooms;
	}

	@Override
	public List<IRoom> sortRooms(String name) throws Exception {
		return roomDao.getAll(connect.getConection(), name);
	}

	public List<IGuest> getLastGuests(Integer id, Integer num) throws Exception {
		List<IGuest> guests = roomDao.getLastGuests(connect.getConection(), id, num);

		return guests;
	}

	public IRoom clone(IRoom room) throws Exception {
		IRoom clon = room.clone();

		roomDao.create(connect.getConection(), clon);

		return clon;
	}

}