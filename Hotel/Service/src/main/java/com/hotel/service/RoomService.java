package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.Analyzer;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.api.service.IRoomService;
import com.hotel.been.Room;
import com.hotel.configurations.Configuration;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.connection.HibernateUtil;
import com.hotel.utils.CsvWorker;

public class RoomService implements IRoomService {

	private static RoomService instance;
	private RoomDAO roomDao = RoomDAO.getInstance();

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
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
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
				roomDao.updute(session, roomsImport.get(i));
			}
			for (int i = rooms.size(); i < reader.read().size(); i++) {
				roomDao.create(session, roomsImport.get(i));
			}
			transaction.commit();
			return "data was imported from " + path;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} 
	}

	public void addRoom(IRoom room) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		room.setIsFree(true);
		room.setStatus(RoomStatus.OPEN);
		roomDao.create(session, room);
		transaction.commit();
	}
	
	public IRoom getById(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		IRoom room = roomDao.getById(session, id, IRoom.class);
		return room;
	}

	public void remove(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		IRoom room = getById(id);
		roomDao.delete(session, room);
		transaction.commit();
	}

	public List<IRoom> getAllRooms() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<IRoom> list = roomDao.getAll(session, "id",IRoom.class);
		return list;
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		IRoom room = getById(roomId);
		if (room != null) {
			room.setPrice(price);
			roomDao.updute(session, room);
		}
		transaction.commit();
		return "Price of room " + room.getId() + " is " + room.getPrice();
	}

	public Integer getNumberOfRooms() throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return roomDao.getAll(session, "id", IRoom.class).size();
	}

	public Integer getNumberOfFreeRooms() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<IRoom> list = roomDao.getAll(session, "id",IRoom.class);
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		return roomDao.getAll(session, name, IRoom.class);
	}

	public List<IGuest> getLastGuests(Integer id, Integer num) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<IGuest> guests = roomDao.getLastGuests(session, id, num);

		return guests;
	}

	public IRoom clone(IRoom room) throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		IRoom clon = room.clone();
		roomDao.create(session, clon);
		transaction.commit();

		return clon;
	}
	
	public String changeRoomStatus(Integer id, String n) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		IRoom room = getById(id);
		String status = null;
		try {
			if (changeStatus) {
				switch (n) {
				case ("1"):
					room.setStatus(RoomStatus.OPEN);
					status = "Open";
					break;
				case ("2"):
					room.setStatus(RoomStatus.CLOSE);
					status = "Close";
					break;
				case ("3"):
					room.setStatus(RoomStatus.SERVICED);
					status = "Serviced";
					break;
				case ("4"):
					room.setStatus(RoomStatus.REPAIRABLE);
					status = "Repairable";
					break;
				}
				roomDao.updute(session, room);
				transaction.commit();
			}
			return "New status of the room " + status;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}