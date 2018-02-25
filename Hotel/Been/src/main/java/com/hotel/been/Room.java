package com.hotel.been;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;
import com.hotel.api.been.Entity;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;

@javax.persistence.Entity
@Table(name = "Rooms")
@CsvEntity(filename = "Rooms.csv", entityId = 3)
public class Room extends Entity implements IRoom, Cloneable {

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
	@Transient
	private List<IHistory> history = new ArrayList<>();

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

	@Override
	public Boolean getIsFree() {

		return isFree;
	}

	@Override
	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	@Override
	public Integer getNumber() {
		return number;
	}

	@Override
	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public Integer getCopacity() {
		return copacity;
	}

	@Override
	public void setCopacity(Integer copacity) {
		this.copacity = copacity;
	}

	@Override
	public Integer getNumberOfStars() {
		return numberOfStars;
	}

	@Override
	public void setNumberOfStars(Integer numberOfStars) {
		this.numberOfStars = numberOfStars;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public RoomStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	@Override
	public List<IHistory> getHistory() {
		return history;
	}

	@Override
	public void setHistory(List<IHistory> history) {
		this.history = history;
	}

	@Override
	public IRoom clone() throws CloneNotSupportedException {
		IRoom clon = (IRoom) super.clone();
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
