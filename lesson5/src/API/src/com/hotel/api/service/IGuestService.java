package com.hotel.api.service;

import java.util.Comparator;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.repository.IGuestRepository;

public interface IGuestService {
	
	@SuppressWarnings( "rawtypes" )
	public void sortGuests(Comparator comparator);
	
	public IGuest getGuestById(Integer id);
	
	public void addGuest(IGuest guest);
	
	public List<IGuest> getGuests();
	
	public Integer getNumberOfGuests();
	
	public IGuestRepository getGuest();
	
	public void addOptionToGuest(Integer optionId, Integer guestId);
	
	public List<IOption> getGuestOptions(Integer id);
	
	public void writeInFile();
	
	public String[] readFromFile();
	

}
