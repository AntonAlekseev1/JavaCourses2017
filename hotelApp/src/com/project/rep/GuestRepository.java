package com.project.rep;

import com.danco.training.TextFileWorker;
import com.project.been.Guest;

public class GuestRepository {
	private Guest [] guests=new Guest[20];
//	private Guest guest;
//	private Integer id;

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
