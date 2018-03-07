package com.hotel.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.api.service.IHistoryService;
import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Option;
import com.hotel.been.Room;
import com.hotel.dao.GuestDao;
import com.hotel.dao.HistoryDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.hibernateutil.HibernateUtil;

public class HistoryService implements IHistoryService {

	private static HistoryService instance;
	private static RoomDAO roomDao = RoomDAO.getInstance();
	private static GuestDao guestDao = GuestDao.getInstance();
	private HistoryDAO historyDao = HistoryDAO.getInstance();

	private HistoryService(GuestDao guestDAO, RoomDAO roomDAO) {
		HistoryService.guestDao = guestDAO;
		HistoryService.roomDao = roomDAO;

	}

	public static HistoryService getInstance() {
		if (instance == null) {
			instance = new HistoryService(guestDao, roomDao);
		}
		return instance;
	}

	public List<History> getHistory() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<History> list = historyDao.getAll(session, "id", History.class);
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} 
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate)
			throws Exception {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Guest guest = guestDao.getById(session, guestId, Guest.class);
			Room room = roomDao.getById(session, roomId, Room.class);
			History history = new History(guest, room, dateOfArival.getTime(), evictDate.getTime());
			if (guest != null && room != null) {
				room.getHistory().add(history);
				guest.getHistory().add(history);
				historyDao.create(session, history);
				room.setIsFree(false);
				roomDao.updute(session, room);

			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}
	}

	public void evictGuestFromRoom(Integer guestId, Integer roomId) throws Exception {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Guest guest = guestDao.getById(session, guestId, Guest.class);
			Room room = roomDao.getById(session, roomId, Room.class);
			for (int i = 0; i < room.getHistory().size(); i++) {
				if (room.getHistory().get(i) != null && room.getHistory().get(i).getGuest().getId() == guestId) {
					guest.getHistory().remove(i);
					room.getHistory().remove(i);
				}
			}
			List<History> list = historyDao.getAll(session, "id", History.class);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getRoom().getId() == room.getId()) {
					historyDao.delete(session, list.get(i));
				}
			}
			room.setIsFree(true);
			roomDao.updute(session, room);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}
	}

	public void addHistory(History history) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			historyDao.create(session, history);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}
	}

	
	public Double getTotalPayment(Integer id) throws Exception {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Double summ = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			History history = guestDao.getById(session, id, Guest.class).getHistory().get(0);
			if (history != null) {
				Long days = (guestDao.getById(session, id, Guest.class).getHistory().get(0).getEvictDate().getTime()
						- guestDao.getById(session, id, Guest.class).getHistory().get(0).getDateOfArrival().getTime())
						/ 86400000;
				summ = history.getRoom().getPrice() * days;
				List<Option> options = guestDao.getGuestOptions(session, id);
				for (Option option : options) {
					summ += option.getPrice();
				}
			}
			transaction.commit();
			return summ;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} 
	}

	public List<Room> getFreeRoomOnDate(Date date) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Room> rooms = roomDao.getAll(session, "id", Room.class);
			for (int i = 0; i < rooms.size(); i++) {

				int historySize = rooms.get(i).getHistory().size();
				for (int j = 0; j < historySize; j++) {
					if (rooms.get(i).getHistory().get(j).getDateOfArrival().before(date)
							&& rooms.get(i).getHistory().get(j).getEvictDate().after(date)) {
						rooms.remove(rooms.get(i));
					}
				}
			}
			transaction.commit();
			return rooms;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} 
	}

}
