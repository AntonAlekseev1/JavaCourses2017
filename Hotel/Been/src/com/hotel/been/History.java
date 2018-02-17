package com.hotel.been;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.api.been.Entity;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;

public class History extends Entity implements IHistory {
	
	private Integer roomId;
	private Integer guestId;
	private Date dateOfArrival;
	private Date evictDate;
	private List<IOption> options;

	public History() {

	}

	public History(Integer guestId, Integer roomId, Date dateOfArrival, Date evictDate) {
		this.guestId = guestId;
		this.roomId = roomId;
		this.dateOfArrival = dateOfArrival;
		this.evictDate = evictDate;
		options = new ArrayList<>();
	}

	public History(Integer guestId, Integer roomId) {
		this.guestId = guestId;
		this.roomId = roomId;
	}

	public History(Integer guestId, List<IOption> option) {
		this.guestId = guestId;
		this.options = option;
		options = new ArrayList<>();
	}
    @Override
	public Date getDateOfArrival() {
		return dateOfArrival;
	}
    @Override
    public void setDateOfArival(Date dateOfArival) {
    	this.dateOfArrival=dateOfArival;
    }
    @Override
	public Integer getGuestId() {
		return guestId;
	}
    @Override
	public void setGuest(Integer guestId) {
		this.guestId = guestId;
	}
    @Override
	public Integer getRoomId() {
		return roomId;
	}
    @Override
	public void setRoom(Integer roomId) {
		this.roomId = roomId;
	}
    @Override
	public Date getEvictDate() {
		return evictDate;
	}
    @Override
    public void setEvictDate(Date evictDate) {
    	this.evictDate = evictDate;
    }
    @Override
	public List<IOption> getOptions() {
		return options;
	}
    @Override
    public void setOptions(List<IOption> options) {
    	this.options = options;
    }

    @Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getId());
		s.append(" Guest: ");
		s.append(guestId);
		s.append(" settle in Room: ");
		s.append(roomId);
		s.append(" From: ");
		s.append(dateOfArrival);
		s.append(" To: ");
		s.append(evictDate);

		return s.toString();
	}

}
