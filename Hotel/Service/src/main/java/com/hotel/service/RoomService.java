package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.Analyzer;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.api.service.IRoomService;
import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Room;
import com.hotel.been.RoomStatus;
import com.hotel.configurations.Configuration;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.hibernateutil.HibernateUtil;
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
		List<Room> rooms = getAllRooms();
		writer.comment("id;number;copacity;stars;price;isFree;status");
		for (int i = 0; i < rooms.size(); i++) {
			writer.write(rooms.get(i));
		}
		return "data was exported along the way" + path;
	}

	@Override
	public String importRooms(String pathToCsv) throws Exception { // use SaveOrUpdate method
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String path = pathToCsv + Analyzer.getNameOfBeen("Room");
			List<Room> rooms = getAllRooms();
			List<Room> roomsImport = new ArrayList<>();
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
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void addRoom(Room room) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			room.setIsFree(true);
			room.setStatus(RoomStatus.OPEN);
			roomDao.create(session, room);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Room getById(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Room room = roomDao.getById(session, id, Room.class);
			transaction.commit();
			return room;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void remove(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Room room = roomDao.getById(session, id, Room.class);
			roomDao.delete(session, room);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Room> getAllRooms() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Room> list = roomDao.getAll(session, "id", Room.class);
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Room> getFreeRooms() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Room> allRooms = roomDao.getAll(session, "id", Room.class);
			List<Room> freeRooms = new ArrayList<>();
			for (int i = 0; i < allRooms.size(); i++) {
				if (allRooms.get(i).getIsFree() == true) {
					freeRooms.add(allRooms.get(i));
				}
			}
			transaction.commit();
			return freeRooms;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public IRoomDAO getRooms() {
		return roomDao;
	}

	public String chengePriseOfRoom(Integer roomId, Double price) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Room room = roomDao.getById(session, roomId, Room.class);
			if (room != null) {
				room.setPrice(price);
				roomDao.updute(session, room);
			}
			transaction.commit();
			return "Price of room " + room.getId() + " is " + room.getPrice();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Integer getNumberOfRooms() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Integer num = roomDao.getAll(session, "id", Room.class).size();
			transaction.commit();
			return num;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Integer getNumberOfFreeRooms() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Room> list = roomDao.getAll(session, "id", Room.class);
			Integer numberOfFreeRooms = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIsFree() == true) {
					numberOfFreeRooms++;
				}
			}
			transaction.commit();
			return numberOfFreeRooms;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Room> sortRooms(String name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Room> list = roomDao.getAll(session, name, Room.class);
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Guest> getLastGuests(Integer id, Integer num) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		List<Guest> guests = new ArrayList<>();
		Room room = roomDao.getById(session, id, Room.class);
		List<History> history = room.getHistory();
		try {
			int n = 0;
			while (history.size() > n && n <= num) {
				guests.add(history.get(n).getGuest());
				n++;
			}
			transaction.commit();
			return guests;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Room clone(Integer id, Integer number) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Room clon = roomDao.getById(session, id, Room.class).clone();
			clon.setNumber(number);
			roomDao.create(session, clon);
			transaction.commit();
			return clon;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public String changeRoomStatus(Integer id, String n) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
			Room room = roomDao.getById(session, id, Room.class);
			String status = null;
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
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}