package com.hotel.repository;

import java.util.Arrays;

import com.danco.training.TextFileWorker;
import com.hotel.been.Entity;
import com.hotel.been.Guest;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.IdGenerator;

public class GuestRepository {
	private Guest[] guests;
	private final TextFileWorker textFileWorker = new TextFileWorker("D:\\1\\guests.txt");

	public GuestRepository(Integer size) {
		guests = new Guest[size];
	}

	public Guest[] getGuests() {
		return guests;
	}

	public void setGuests(Guest[] guests) {
		this.guests = guests;
	}

	public void addGuest(Guest guest) {
		if (guests[guests.length - 1] != null) {

			guests = castEntitiesArray(ArrayWorker.resize(guests));
		}
		for (int i = 0; i < guests.length; i++) {
			if (guests[i] == null) {
				guests[i] = guest;
				guest.setId(IdGenerator.generateGuestId());
				break;
			}
		}
	}

	public Guest getGuestById(Integer id) {
		Guest guest = null;
		for (int i = 0; i < guests.length; i++) {
			if (guests[i] != null) {
				if (guests[i].getId() == id) {
					guest = guests[i];
					break;
				}
			}
		}
		return guest;
	}

	public void removeGuest(Guest guest) {
		for (int i = 0; i < guests.length; i++) {
			if (guests[i] == guest) {
				guests[i] = null;
				break;
			}
		}
	}

	public void writeInFile() {
		String[] array = Arrays.copyOf(ArrayWorker.toString(guests), ArrayWorker.getCount(guests));
		textFileWorker.writeToFile(array);
	}

	private Guest[] castEntitiesArray(Entity[] entities) {
		Guest[] guestArray = Arrays.copyOf(entities, entities.length, Guest[].class);
		return guestArray;
	}
}