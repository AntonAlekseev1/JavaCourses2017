package com.hotel.been;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;
import com.hotel.api.been.Entity;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;

@javax.persistence.Entity
@Table(name = "Guests")
@CsvEntity(filename = "Guests.csv", entityId = 1)
public class Guest extends Entity implements IGuest {

	@Column(name = "name")
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String name;
	@Column(name = "last_name")
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String lastName;
	@Transient
	private IHistory history;

	public Guest() {

	}

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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public IHistory getHistory() {
		return history;
	}

	@Override
	public void setHistory(IHistory history) {
		this.history = history;
	}

	@Override
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
