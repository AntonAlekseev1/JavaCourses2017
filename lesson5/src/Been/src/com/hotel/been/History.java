package com.hotel.been;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;

public class History extends Entity implements IHistory {
	private IRoom room;
	private IGuest guest;
	private Date dateOfArrival;
	private Date evictDate;
	private List<IOption> options;

	public History() {

	}

	public History(IGuest guest2, IRoom room2, Date dateOfArrival, Date evictDate) {
		this.guest = guest2;
		this.room = room2;
		options = new ArrayList<>();
		this.dateOfArrival = dateOfArrival;
		this.evictDate = evictDate;
	}

	public History(Guest guest, Room room) {
		this.guest = guest;
		this.room = room;
	}

	public History(Guest guest, List<IOption> option) {
		this.guest = guest;
		this.options = option;
	}

	public Date getDateOfArrival() {

		return dateOfArrival;
	}

	public IGuest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public IRoom getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getEvictDate() {
		return evictDate;
	}

	public List<IOption> getOptions() {
		return options;
	}


	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getId());
		s.append(" Guest: ");
		s.append(guest);
		s.append(" settle in Room: ");
		s.append(room);
		s.append(" From: ");
		s.append(dateOfArrival);
		s.append(" To: ");
		s.append(evictDate);

		return s.toString();
	}

}
