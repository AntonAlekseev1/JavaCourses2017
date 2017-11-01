package com.hotel.service;

import java.util.Calendar;

import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Room;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.HistoryRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.utils.ArrayWorker;

public class HistoryService {
	private RoomRepository roomRepository;
	private GuestRepository guestRepository;
	private HistoryRepository historyRepository;

	public HistoryService(GuestRepository guestRepository, RoomRepository roomRepository) {
		historyRepository = new HistoryRepository(ArrayWorker.MIN_SIZE);
		this.guestRepository = guestRepository;
		this.roomRepository = roomRepository;

	}

	public History[] getGuest() {
		return historyRepository.getHistory();
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {

		Guest guest = guestRepository.getGuestById(guestId);
		Room room = roomRepository.getRoomById(roomId);
		History history;
		if (guest != null && room != null && room.getIsFree()) {
			history = new History(roomId, guest, room, dateOfArival.getTime(), evictDate.getTime());
			room.setIsFree(false);

		}
	}
}
