package com.hotel.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.api.repository.IGuestRepository;
import com.hotel.api.repository.IRoomRepository;
import com.hotel.api.service.IHistoryService;
import com.hotel.been.History;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.HistoryRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.utils.ArrayWorker;

public class HistoryService implements IHistoryService {
	private IRoomRepository roomRepository=RoomRepository.getInstance();
	private IGuestRepository guestRepository = GuestRepository.getInstance();
	private HistoryRepository historyRepository = HistoryRepository.getInstance();

	public HistoryService(IGuestRepository iGuestRepository, IRoomRepository iRoomRepository) {
		HistoryRepository.getInstance();
		this.guestRepository = iGuestRepository;
		this.roomRepository = iRoomRepository;

	}

	public List<IHistory> getHistory() {
		return historyRepository.getHistory();
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {

		IGuest guest = guestRepository.getGuestById(guestId);
		IRoom room = roomRepository.getRoomById(roomId);
		IHistory history = new History(guest, room, dateOfArival.getTime(), evictDate.getTime());
		if (guest != null && room != null) {
			room.getHistory().add(history);
			guest.setHistory(history);
			historyRepository.addHistory(history);
			room.setIsFree(false);

		}
	}

	public void evictGuestFromRoom(Integer guestId, Integer roomId) {
		IGuest guest = guestRepository.getGuestById(guestId);
		IRoom room = roomRepository.getRoomById(roomId);
		for (int i = 0; i < room.getHistory().size(); i++) {
			if (room.getHistory().get(i) != null && room.getHistory().get(i).getGuest() == guest) {
				guest.setHistory(null);
				room.setIsFree(true);
			}
		}
	}

	public List<IRoom> getFreeRoomOnDate(Date date) {
		List<IRoom> room = new ArrayList<>();
		
		for (int i = 0; i < roomRepository.getRooms().size(); i++) {
			room.add(roomRepository.getRooms().get(i));

			for (int j = 0; j < roomRepository.getRooms().get(i).getHistory().size(); j++) {

				if (roomRepository.getRooms().get(i).getHistory().get(j).getEvictDate().after(date)) {
					room.remove(roomRepository.getRooms().get(i));
				}
			}
		}
		
		ArrayList<IRoom> result = new ArrayList<IRoom>(new HashSet<IRoom>(room));
		return result;
	}

	public void addHistory(IHistory history) {
		historyRepository.addHistory(history);
	}

	public Long daysOfArrival(Integer id) {
		return (guestRepository.getGuestById(id).getHistory().getEvictDate().getTime()
				- guestRepository.getGuestById(id).getHistory().getDateOfArrival().getTime()) / 86400000;
	}

	public Double getTotalPayment(Integer id) {

		Double sum = 0.0;
		List<IOption> options = guestRepository.getGuestById(id).getHistory().getOptions();
		IHistory history = guestRepository.getGuestById(id).getHistory();
		if (history != null) {
			sum = history.getRoom().getPrice() * daysOfArrival(id);
			int optionNumber = ArrayWorker.getCount(options);
			if (optionNumber != 0) {
				for (int i = 0; i < optionNumber; i++) {
					if (options.get(i) != null) {
						sum += (options.get(i).getPrice()) * daysOfArrival(id);
					}
				}
			}
			return sum;
		}
		return null;
	}

}
