package com.hotel.been;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;

@javax.persistence.Entity
@Table(name = "Rooms")
@CsvEntity(filename = "Rooms.csv", entityId = 3)
public class Room extends Entity implements Cloneable {

	@Column(name = "number")
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Integer number;
	@Column(name = "copacity")
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Integer copacity;
	@Column(name = "stars")
	@CsvProperty(columnNumber = 4, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Integer numberOfStars;
	@Column(name = "price")
	@CsvProperty(columnNumber = 5, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Double price;
	@Column(name = "is_free")
	@CsvProperty(columnNumber = 6, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Boolean isFree = true;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@CsvProperty(columnNumber = 7, propertyType = PropertyType.SIMPLE_PROPERTY)
	private RoomStatus status;
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<History> history = new ArrayList<>();

	public Room() {

	}

	public Room(String string) {

		String[] arr = string.split(" ");
		setId(Integer.valueOf(arr[0]));
		this.number = Integer.valueOf(arr[1]);
		this.copacity = Integer.valueOf(arr[2]);
		this.numberOfStars = Integer.valueOf(arr[3]);
		this.price = Double.valueOf(arr[4]);
		this.isFree = Boolean.valueOf(arr[5]);
		this.status = RoomStatus.valueOf(arr[6]);
	}

	public Room(Integer number, Integer copacity, Integer numberOfStars, Double price) {

		this.number = number;
		this.copacity = copacity;
		this.numberOfStars = numberOfStars;
		this.price = price;
		history = new ArrayList<>();

	}

	public Boolean getIsFree() {

		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCopacity() {
		return copacity;
	}

	public void setCopacity(Integer copacity) {
		this.copacity = copacity;
	}

	public Integer getNumberOfStars() {
		return numberOfStars;
	}

	public void setNumberOfStars(Integer numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	public Room clone() throws CloneNotSupportedException {
		Room clon = (Room) super.clone();
		clon.setHistory(null);
		clon.setId(null);
		clon.setIsFree(true);
		return clon;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getId());
		s.append(" ");
		s.append(number);
		s.append(" ");
		s.append(copacity);
		s.append(" ");
		s.append(numberOfStars);
		s.append(" ");
		s.append(price);
		s.append(" ");
		s.append(isFree);
		s.append(" ");
		s.append(status);
		return s.toString();
	}

}
