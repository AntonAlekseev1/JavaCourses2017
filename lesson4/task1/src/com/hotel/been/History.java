package com.hotel.been;

import java.util.Date;

import com.hotel.utils.ArrayWorker;

public class History extends Entity {
	private Room room;
	private Guest guest;
	private Date dateOfArrival;
	private Date evictDate;
	private Option[] options;

	public History() {

	}

	public History(Guest guest, Room room, Date dateOfArrival, Date evictDate) {
		this.guest = guest;
		this.room = room;
		options = new Option[ArrayWorker.MIN_SIZE];
		this.dateOfArrival = dateOfArrival;
		this.evictDate = evictDate;
	}

	public History(Guest guest, Room room) {
		this.guest = guest;
		this.room = room;
	}

	public History(Guest guest, Option[] option) {
		this.guest = guest;
		this.options = option;
	}

	public Date getDateOfArrival() {

		return dateOfArrival;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getEvictDate() {
		return evictDate;
	}

	public Option[] getOptions() {
		return options;
	}

	public void addOption(Option option) {

		for (int i = 0; i < options.length; i++) {
			if (options[i] == null) {
				options[i] = option;
				break;
			}
		}

	}

	public Double getTotalPayment() {
		Double sum = 0.0;
		sum = room.getPrice() * daysOfArrival();
		int optionNumber=ArrayWorker.getCount(options);
		if(optionNumber!=0) {
		for(int i=0;i<optionNumber;i++) {
			if(options[i]!=null) {
			sum+=(options[i].getPrice())*daysOfArrival();
			}
		}
		}
		return sum;

	}

	public Long daysOfArrival() {
		return (evictDate.getTime() - dateOfArrival.getTime()) / 86400000;
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
