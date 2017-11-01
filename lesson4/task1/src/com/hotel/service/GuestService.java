package com.hotel.service;

import com.hotel.been.Guest;
import com.hotel.been.Room;
import com.hotel.repository.GuestRepository;
import com.hotel.utils.ArrayWorker;

public class GuestService {
	// private Double totalPayment;
	private Integer numberOfGuests = 0;
	private  GuestRepository guests;

	public GuestService() {
		guests=new GuestRepository(ArrayWorker.MIN_SIZE);

	}

	public void addGuest(Guest guest) {
		guests.addGuest(guest);

	}

	public Guest[] getGuests() {
		return guests.getGuests();
	}

	public String getNumberOfGuests() {
		for (int i = 0; i < guests.getGuests().length; i++) {
			if (guests.getGuests()[i] != null) {
				numberOfGuests++;
			}
		}
		return "Number of guests " + numberOfGuests;
	}

	public GuestRepository getGuest() {
		return guests;
	}

}