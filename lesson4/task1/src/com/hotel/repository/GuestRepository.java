package com.hotel.repository;

import com.hotel.been.Guest;
import com.hotel.utils.IdGenerator;

public class GuestRepository {
	private Guest [] guests;
	
	public GuestRepository(Integer size) {
		 guests=new Guest[size];
	}

	public Guest [] getGuests() {
		return guests;
	}

	public void setGuests(Guest [] guests) {
		this.guests = guests;
	}
	
	public void addGuest(Guest guest) {
		for(int i=0;i<guests.length;i++) {
			if(guests[i]==null) {
				guests[i]=guest;
				guest.setId(IdGenerator.generateGuestId());
				break;
			}
		}
	}
	public Guest getGuestById(Integer id) {
		Guest guest=null;
		for(int i=0;i<guests.length;i++) {
			if(guests[i]!=null) {
			if(guests[i].getId()==id) {
				 guest=guests[i];
				 break;
			}
			}
		}
		return guest;
	}
	
	public void removeGuest(Guest guest) {
		for(int i=0;i<guests.length;i++) {
			if(guests[i]==guest) {
				guests[i]=null;
				break;
			}
		}	
	}
}
