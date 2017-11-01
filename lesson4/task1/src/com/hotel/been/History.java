package com.hotel.been;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class History extends Entity {
	private Room room;
	private Guest guest;
	private Date dateOfArrival;
	private Date evictDate;

	public History(Integer id, Guest guest, Room room, Date dateOfArrival, Date evictDate) {
		super(id);
		this.guest = guest;
		this.room = room;
		this.dateOfArrival = dateOfArrival;
		this.evictDate = evictDate;
	}

	public Date getDateOfArrival() {

		return dateOfArrival;
	}

	public void setDateOfArrival(String date) throws ParseException {

		Date dateOfArrival = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(date);
		this.dateOfArrival = dateOfArrival;
	}

	public Guest getGuest() {
		return guest;
	}

	public Room getRoom() {
		return room;
	}

	public Date getEvictDate() {
		return evictDate;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getId());
		s.append(" ");
		s.append(guest);
		s.append(" ");
		s.append(room);
		s.append(" ");
		s.append(dateOfArrival);
		s.append(" ");
		s.append(evictDate);

		return s.toString();
	}

}
