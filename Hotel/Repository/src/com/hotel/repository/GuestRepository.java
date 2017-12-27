package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.repository.IGuestRepository;

public class GuestRepository implements IGuestRepository{
	private List<IGuest> guestRepository;

	private static GuestRepository instance;

	private GuestRepository() {
		guestRepository = new ArrayList<>();
	}
	
	public static GuestRepository getInstance() {
		if(instance==null) {
			instance=new GuestRepository();
		}
		return instance;
	}

	public List<IGuest> getGuests() {
		return guestRepository;
	}
	
	public void setGuests(List<IGuest>guests) {
		guestRepository=guests;
	}

	public synchronized void addGuest(IGuest guest) {
		guest.setId(generateId());
		guestRepository.add(guest);
	}

	public synchronized IGuest getGuestById(Integer id) {
		IGuest guest = null;
		for (int i = 0; i < guestRepository.size(); i++) {
				if ( guestRepository.get(i).getId().equals(id)) {
					guest =  guestRepository.get(i);
					
				}
		}
		return guest;
	}

	public synchronized void removeGuest(Integer id) {
		for (int i = 0; i < guestRepository.size(); i++) {
			if (guestRepository.get(i).getId().equals(id)) {
				guestRepository.remove(i);
				break;
			}
		}
	}
	
	public synchronized Integer generateId() {
		Integer id=0;
		for(int i=0;i< guestRepository.size(); i++) {
			if(guestRepository.get(i).getId()>id) {
				id=guestRepository.get(i).getId();
			}
		}
		return (id+1);
	}
}