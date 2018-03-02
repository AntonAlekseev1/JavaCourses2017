package com.hotel.api.service;

import java.util.List;

import com.hotel.been.Guest;
import com.hotel.been.Option;

public interface IGuestService {
	
	public List<Guest> sortGuests(String name) throws  Exception;
	
	public Guest getGuestById(Integer id) throws Exception;
	
	public void addGuest(Guest guest) throws Exception;
	
	public List<Guest> getGuests() throws Exception;
	
	public Integer getNumberOfGuests() throws Exception;
	
	public void addOptionToGuest(Integer optionId, Integer guestId) throws Exception;
	
	public List<Option> getGuestOptions(Integer id) throws Exception;
	
	public void removeGuest(Integer id) throws Exception;

	String exportGuests(String path) throws Exception;

	String importGuest(String path) throws Exception;

}
