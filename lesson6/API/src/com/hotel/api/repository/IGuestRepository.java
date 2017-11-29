package com.hotel.api.repository;

import java.util.List;

import com.hotel.api.been.IGuest;

public interface IGuestRepository {
	
	public List<IGuest> getGuests();
	
	public void setGuests(List<IGuest>guests);
	
	public void addGuest(IGuest guest);
	
	public IGuest getGuestById(Integer id);
	
	public void removeGuest(Integer id);

}
