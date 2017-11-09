package com.hotel.service;

import java.util.Calendar;
import java.util.Date;

import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Room;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.HistoryRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.IdGenerator;

public class HistoryService {
	private RoomRepository roomRepository;
	private GuestRepository guestRepository;
	private HistoryRepository historyRepository;

	public HistoryService(GuestRepository guestRepository, RoomRepository roomRepository) {
		historyRepository = new HistoryRepository(ArrayWorker.MIN_SIZE);
		this.guestRepository = guestRepository;
		this.roomRepository = roomRepository;

	}

	public History[] getHistory() {
		return historyRepository.getHistory();
	}

	public void settleGuestInRoom(Integer guestId, Integer roomId, Calendar dateOfArival, Calendar evictDate) {

		Guest guest = guestRepository.getGuestById(guestId);
		Room room = roomRepository.getRoomById(roomId);
		History history=new History(guest, room, dateOfArival.getTime(),
				evictDate.getTime());

		for (int i = 0; i < historyRepository.getHistory().length; i++) {

			if (room.getHistory()[i] == null) {
				if (guest != null && room != null) {
					room.getHistory()[i] = history;
					history.setId(IdGenerator.generateHistoryId());
					room.setIsFree(false);
					guest.setHistory(history);

					historyRepository.getHistory()[i]=history;
					break;
				}
			}
		}
	}
	
	public void evictGuestFromRoom(Integer guestId, Integer roomId) {
		Guest guest = guestRepository.getGuestById(guestId);
		Room room = roomRepository.getRoomById(roomId);
		for(int i=0;i<room.getHistory().length;i++) {
			if(room.getHistory()[i]!=null && room.getHistory()[i].getGuest()==guest) {
				guest.setHistory(null);
				room.setIsFree(true);
			}
		}
	}

	public Room[] getFreeRoomOnDate(Date date) {
		Room[] room = new Room[10];
		for (int i = 0; i < roomRepository.getRooms().length; i++) {
			if (roomRepository.getRooms()[i] != null) {
				for (int j = 0; j < roomRepository.getRooms()[i].getHistory().length; j++) {
					if (roomRepository.getRooms()[i].getHistory()[j] != null) {
						if (roomRepository.getRooms()[i].getHistory()[j].getEvictDate().before(date)) {
							room[i] = roomRepository.getRooms()[i];
						}
					}
				}
			}
		}
		return room;
	}

	public void addHistory(History history) {
		historyRepository.addHistory(history);
	}

	public Double getTotalPayment(Integer id) {

		History history =guestRepository.getGuestById(id).getHistory();
		if (history != null) {
			return history.getTotalPayment();
		}
		return null;
	}
}
