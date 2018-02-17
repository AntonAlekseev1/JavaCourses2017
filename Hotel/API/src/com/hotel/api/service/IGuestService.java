package com.hotel.api.service;

import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.IGuestDAO;

public interface IGuestService {
	
	public List<IGuest> sortGuests(String name) throws  Exception;
	
	public IGuest getGuestById(Integer id) throws Exception;
	
	public void addGuest(IGuest guest) throws Exception;
	
	public List<IGuest> getGuests() throws Exception;
	
	public Integer getNumberOfGuests() throws Exception;
	
	public IGuestDAO getGuest();
	
	public void addOptionToGuest(Integer optionId, Integer guestId) throws Exception;
	
	public List<IOption> getGuestOptions(Integer id) throws Exception;
	
	public void removeGuest(Integer id) throws Exception;

}
