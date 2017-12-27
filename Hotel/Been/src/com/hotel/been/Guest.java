package com.hotel.been;

import java.io.Serializable;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;
import com.hotel.api.been.Entity;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;

@CsvEntity(filename="Guests.csv",entityId = 1)
public class Guest extends Entity implements IGuest, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String name;
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String lastName;
	private IHistory history;

	public Guest(String string) {
		String[] arr = string.split(" ");
		setId(Integer.valueOf(arr[0]));
		this.name = String.valueOf(arr[1]);
		this.lastName = String.valueOf(arr[2]);

	}

	public Guest(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public IHistory getHistory() {
		return history;
	}
	
	@Override
	public void setHistory(IHistory history) {
		this.history=history;
	}
	
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getId());
		s.append(" ");
		s.append(name);
		s.append(" ");
		s.append(lastName);
		return s.toString();
	}

	

}
