package com.hotel.service;

import com.hotel.been.Guest;
import com.hotel.been.Option;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.OptionRepository;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.FileWorker;

public class GuestService {

	private Integer numberOfGuests = 0;
	private GuestRepository guests;
	private OptionRepository optionRepository;
	private String path = "D:\\1\\guests.txt";

	public GuestService(OptionRepository optionRepository) {
		guests = new GuestRepository(ArrayWorker.MIN_SIZE);
		this.optionRepository = optionRepository;

	}

	public Guest getGuestById(Integer id) {
		return guests.getGuestById(id);
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

	public String addOptionToGuest(Integer optionId, Integer guestId) {
		Guest guest = guests.getGuestById(guestId);
		Option option = optionRepository.getOptionById(optionId);
		if (guest.getHistory() != null) {
			for (int i = 0; i < guest.getHistory().getOptions().length; i++) {
				if (guest.getHistory().getOptions()[i].equals(null)) {
					guest.getHistory().getOptions()[i] = option;
					break;
				}
			}
		}
		return "option " + option.getOption() + " add to " + guest.getName();
	}

	public Option[] getGuestOptions(Integer id) {
		Guest guest = guests.getGuestById(id);
		Option[] option = new Option[ArrayWorker.MIN_SIZE];
		if (guest.getHistory() != null)
			for (int i = 0; i < option.length; i++) {
				option[i] = guest.getHistory().getOptions()[i];
			}

		return option;
	}

	public void writeInFile() {
		guests.writeInFile();
	}

	public String[] readFromFile() {
		return FileWorker.readFrom(path);
	}
}