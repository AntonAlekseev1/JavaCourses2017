package com.project.service;

import com.project.been.Guest;
import com.project.been.Room;
import com.project.rep.GuestRepository;
//import com.project.service.IdGenerator;

public class GuestService {
	// private Double totalPayment;
	private Integer numberOfGuests = 0;
	private final GuestRepository GUEST = new GuestRepository();

	public GuestService() {

	}

	public void printGuestList(Guest[] guest) {
		for (int i = 0; i < guest.length; i++) {
			if (guest[i] != null)
				System.out.println(guest[i].toString());
		}

	}

	public void addGuest(Guest guest) {
		GUEST.addGuest(guest);

	}

	public Guest[] getGuest() {
		return GUEST.getGuests();
	}

	public String getNumberOfGuests() {
		for (int i = 0; i < GUEST.getGuests().length; i++) {
			if (GUEST.getGuests()[i] != null) {
				numberOfGuests++;
			}
		}
		return "Number of guests " + numberOfGuests;
	}

	public GuestRepository getGUEST() {
		return GUEST;
	}

	public void settleGuestInRoom(Integer guestId, Room room) {
		if (room.getIsFree() == true) {
			GUEST.getGuestById(guestId).setRoom(room);
			GUEST.getGuestById(guestId).getRoom().setIsFree(false);
		}
	}
}