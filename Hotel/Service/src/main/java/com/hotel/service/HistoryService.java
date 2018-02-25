package com.hotel.service;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.api.service.IHistoryService;
import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Room;
import com.hotel.dao.GuestDao;
import com.hotel.dao.HistoryDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.connection.HibernateUtil;

public class HistoryService implements IHistoryService {

	private static HistoryService instance;
	private static RoomDAO roomDao = RoomDAO.getInstance();
	private static GuestDao guestDao = new GuestDao();
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

	public List<IHistory> getHistory() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return historyDao.getAll(session, "id",IHistory.class);
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate)
			throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		IGuest guest = guestDao.getById(session, guestId, IGuest.class);
		IRoom room = roomDao.getById(session, roomId, IRoom.class);
		History history = new History((Guest) guest,(Room) room,dateOfArival.getTime(), evictDate.getTime());
		if (guest != null && room != null) {
			((IHistoryService) room).getHistory().add((IHistory) history);
			((Guest) guest).setHistory(history);
			historyDao.create(session, (IHistory) history);
			room.setIsFree(false);
			roomDao.updute(session, room);

		}
		transaction.commit();
	}

	public void evictGuestFromRoom(Integer guestId, Integer roomId) throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Guest guest = (Guest) guestDao.getById(session, guestId, IGuest.class);
		Room room = (Room) roomDao.getById(session, roomId, IRoom.class);
		for (int i = 0; i < room.getHistory().size(); i++) {
			if (room.getHistory().get(i) != null && room.getHistory().get(i).getGuest().getId() == guestId) {
				guest.setHistory(null);
				room.getHistory().remove(i);
			}
		}
		List<IHistory> list = getHistory();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoom().getId() == room.getId()) {
				historyDao.delete(session, list.get(i));
			}
		}
		room.setIsFree(true);
		roomDao.updute(session, room);
		transaction.commit();
	}

	public List<IRoom> getFreeRoomOnDate(String date) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return historyDao.getFreeRoomsOnDate(session, date);
	}

	public void addHistory(IHistory history) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		historyDao.create(session, history);
		transaction.commit();
	}

	public Double getTotalPayment(Integer id) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Double summ = historyDao.getTotalPayment(session, id);
		List<IOption> options = guestDao.getGuestOptions(session, id);
		for (IOption option : options) {
			summ += option.getPrice();
		}

		return summ;
	}

}
