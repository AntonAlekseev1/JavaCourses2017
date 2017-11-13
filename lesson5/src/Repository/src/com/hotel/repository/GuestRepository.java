package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.repository.IGuestRepository;
import com.hotel.utils.IdGenerator;

public class GuestRepository implements IGuestRepository{
	private List<IGuest> guests;

	public GuestRepository() {
		guests = new ArrayList<IGuest>();
	}

	public List<IGuest> getGuests() {
		return guests;
	}

	public void addGuest(IGuest guest) {
		guests.add(guest);		
		guest.setId(IdGenerator.generateGuestId());
		
	}

	public IGuest getGuestById(Integer id) {
		IGuest guest = null;
		for (int i = 0; i < guests.size(); i++) {
				if ( guests.get(i).getId().equals(id)) {
					guest =  guests.get(i);
					
				}
		}
		return guest;
	}

	public void removeGuest(IGuest guest) {
		for (int i = 0; i < guests.size(); i++) {
			if (guests.get(i) == guest) {
				guest=guests.get(i);
				guest=null;
				break;
			}
		}
	}


}