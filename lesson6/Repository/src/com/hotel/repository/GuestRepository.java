package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.repository.IGuestRepository;
import com.hotel.been.Guest;
import com.hotel.utils.FileWorker;

public class GuestRepository implements IGuestRepository{
	private List<IGuest> guestRepository;

	private static GuestRepository instance;

	private GuestRepository() {
		guestRepository = new ArrayList<IGuest>();
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
	

	public void addGuest(IGuest guest) {
		guest.setId(generateId());
		guestRepository.add(guest);
	}

	public IGuest getGuestById(Integer id) {
		IGuest guest = null;
		for (int i = 0; i < guestRepository.size(); i++) {
				if ( guestRepository.get(i).getId().equals(id)) {
					guest =  guestRepository.get(i);
					
				}
		}
		return guest;
	}

	public void removeGuest(IGuest guest) {
		for (int i = 0; i < guestRepository.size(); i++) {
			if (guestRepository.get(i) == guest) {
				guest=guestRepository.get(i);
				guest=null;
				break;
			}
		}
	}
	
	public Integer generateId() {
		Integer id=0;
		for(int i=0;i< guestRepository.size(); i++) {
			if(guestRepository.get(i).getId()>id) {
				id=guestRepository.get(i).getId();
			}
		}
		return (id+1);
	}
	public void readFromFile(String path) {
		for(String line: FileWorker.readFrom(path)) {
			guestRepository.add(new Guest(line));
		}
	}

}