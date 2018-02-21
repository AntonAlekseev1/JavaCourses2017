package com.hotel.service;

import java.util.Calendar;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.api.dao.IConnectorDao;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.api.dao.IHistoryDAO;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.api.service.IHistoryService;
import com.hotel.been.History;
import com.hotel.di.DependecyInjector;

public class HistoryService implements IHistoryService {

	private static HistoryService instance;
	private static IRoomDAO roomDao = (IRoomDAO) DependecyInjector.inject(IRoomDAO.class);
	private static IGuestDAO guestDao = (IGuestDAO) DependecyInjector.inject(IGuestDAO.class);
	private IHistoryDAO historyDao = (IHistoryDAO) DependecyInjector.inject(IHistoryDAO.class);
	private IConnectorDao connect = (IConnectorDao) DependecyInjector.inject(IConnectorDao.class);

	private HistoryService(IGuestDAO IGuestDAO, IRoomDAO IRoomDAO) {
		HistoryService.guestDao = IGuestDAO;
		HistoryService.roomDao = IRoomDAO;

	}

	public static HistoryService getInstance() {
		if (instance == null) {
			instance = new HistoryService(guestDao, roomDao);
		}
		return instance;
	}

	public List<IHistory> getHistory() throws Exception {
		return historyDao.getAll(connect.getConection(), "id");
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate)
			throws Exception {

		IGuest guest = guestDao.getById(connect.getConection(), guestId);
		IRoom room = roomDao.getById(connect.getConection(), roomId);
		IHistory history = new History(guestId, roomId, dateOfArival.getTime(), evictDate.getTime());
		if (guest != null && room != null) {
			room.getHistory().add(history);
			guest.setHistory(history);
			historyDao.create(connect.getConection(), history);
			room.setIsFree(false);
			roomDao.updute(connect.getConection(), room);

		}
	}

	public void evictGuestFromRoom(Integer guestId, Integer roomId) throws Exception {
		IGuest guest = guestDao.getById(connect.getConection(), guestId);
		IRoom room = roomDao.getById(connect.getConection(), roomId);
		for (int i = 0; i < room.getHistory().size(); i++) {
			if (room.getHistory().get(i) != null && room.getHistory().get(i).getGuestId() == guestId) {
				guest.setHistory(null);
				room.getHistory().remove(i);
			}
		}
		List<IHistory> list = historyDao.getAll(connect.getConection(), "id");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoomId() == room.getId()) {
				historyDao.delete(connect.getConection(), list.get(i).getId());
			}
		}
		room.setIsFree(true);
		roomDao.updute(connect.getConection(), room);
	}

	public List<IRoom> getFreeRoomOnDate(String date) throws Exception {

		return historyDao.getFreeRoomsOnDate(connect.getConection(), date);
	}

	public void addHistory(IHistory history) throws Exception {
		historyDao.create(connect.getConection(), history);
	}

	public Double getTotalPayment(Integer id) throws Exception {

		Double summ = historyDao.getTotalPayment(connect.getConection(), id);
		List<IOption> options = guestDao.getGuestOptions(connect.getConection(), id);
		for (IOption option : options) {
			summ += option.getPrice();
		}

		return summ;
	}

}
