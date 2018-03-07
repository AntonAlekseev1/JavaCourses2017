package com.hotel.been;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;

@javax.persistence.Entity
@Table(name = "Guests")
@CsvEntity(filename = "Guests.csv", entityId = 1)
public class Guest extends Entity {

	@Column(name = "name")
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String name;
	@Column(name = "last_name")
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String lastName;
	@OneToMany(mappedBy = "guest",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<History> history;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
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
