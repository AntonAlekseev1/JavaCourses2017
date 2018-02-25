package com.hotel.been;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hotel.api.been.Entity;
import com.hotel.api.been.IGuest;

@javax.persistence.Entity
@Table(name = "History")
public class History extends Entity {

//	@Column(name = "id_room")
	@ManyToOne
	@JoinColumn(name = "room")
	private Room room;
//	@Column(name = "id_guest")
	@OneToOne
	@JoinColumn(name = "guest")
	private Guest guest;
	@Column(name = "date_of_arival")
	private Date dateOfArrival;
	@Column(name = "evict_date")
	private Date evictDate;
	@Transient
	private List<Option> options;

	public History() {

	}

	public History(Guest guest, Room room, Date dateOfArrival, Date evictDate) {
		this.guest = guest;
		this.room = room;
		this.dateOfArrival = dateOfArrival;
		this.evictDate = evictDate;
		options = new ArrayList<>();
	}

	public History(Guest guest, Room room) {
		this.guest = guest;
		this.room = room;
	}

	public History(Guest guest, List<Option> option) {
		this.guest = guest;
		this.options = option;
		options = new ArrayList<>();
	}

	public Date getDateOfArrival() {
		return dateOfArrival;
	}

	public void setDateOfArival(Date dateOfArival) {
		this.dateOfArrival = dateOfArival;
	}

	public IGuest getGuest() {
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

	public void setEvictDate(Date evictDate) {
		this.evictDate = evictDate;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
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