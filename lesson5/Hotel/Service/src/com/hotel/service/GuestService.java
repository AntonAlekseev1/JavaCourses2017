package com.hotel.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.repository.IGuestRepository;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.service.IGuestService;
import com.hotel.repository.GuestRepository;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.ExLogger;
import com.hotel.utils.FileWorker;

public class GuestService implements IGuestService {

	private Integer numberOfGuests = 0;
	private IGuestRepository guestRepository;
	private IOptionRepository optionRepository;
	private String path = "D:\\1\\guests.txt";

	public GuestService(IOptionRepository optionRepository) {
		guestRepository = new GuestRepository();
		this.optionRepository = optionRepository;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortGuests(Comparator comparator) {
		Collections.sort(guestRepository.getGuests(), comparator);
	}

	public IGuest getGuestById(Integer id) {
		return guestRepository.getGuestById(id);
	}

	public void addGuest(IGuest guest) {
		guestRepository.addGuest(guest);

	}

	public List<IGuest> getGuests() {
		return guestRepository.getGuests();
	}

	public Integer getNumberOfGuests() {
		try {
			for (int i = 0; i < guestRepository.getGuests().size(); i++) {
				numberOfGuests++;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			ExLogger.write(e);
		}
		return numberOfGuests;
	}

	public IGuestRepository getGuest() {
		return guestRepository;
	}

	public void addOptionToGuest(Integer optionId, Integer guestId) {
		IGuest guest = guestRepository.getGuestById(guestId);
		IOption option = optionRepository.getOptionById(optionId);
		try {
			if (guest.getHistory() != null) {

				guest.getHistory().getOptions().add(option);

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			ExLogger.write(e);
		}
	}

	public List<IOption> getGuestOptions(Integer id) {
		IGuest guest = guestRepository.getGuestById(id);

		return guest.getHistory().getOptions();
	}

	public void writeInFile() {
		FileWorker.writeToFile(path, ArrayWorker.toString(guestRepository.getGuests()));
	}

	public void readFromFile() {
		guestRepository.readFromFile(path);
	}

}