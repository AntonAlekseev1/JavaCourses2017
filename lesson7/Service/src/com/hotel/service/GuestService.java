package com.hotel.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.repository.IGuestRepository;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.service.IGuestService;
import com.hotel.di.DependecyInjector;

public class GuestService implements IGuestService {

	private static GuestService instance;
	private Integer numberOfGuests = 0;
	private IGuestRepository guestRepository = (IGuestRepository) DependecyInjector.getRepository(IGuestRepository.class);
	private static IOptionRepository optionRepository = (IOptionRepository) DependecyInjector.getRepository(IOptionRepository.class);

	private GuestService(IOptionRepository optionRepository) {
		GuestService.optionRepository = optionRepository;

	}
	
	public static GuestService getInstance() {
		if(instance==null) {
			instance = new GuestService(optionRepository);
		}
		return instance;
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
			for (int i = 0; i < guestRepository.getGuests().size(); i++) {
				numberOfGuests++;
			}
		return numberOfGuests;
	}

	public IGuestRepository getGuest() {
		return guestRepository;
	}

	public void addOptionToGuest(Integer optionId, Integer guestId) {
		IGuest guest = guestRepository.getGuestById(guestId);
		IOption option = optionRepository.getOptionById(optionId);
			if (guest.getHistory() != null) {
				guest.getHistory().getOptions().add(option);

			}
		} 

	public List<IOption> getGuestOptions(Integer id) {
		IGuest guest = guestRepository.getGuestById(id);

		return guest.getHistory().getOptions();
	}
	
	public void removeGuest(Integer id) {
		guestRepository.removeGuest(id);
	}

}