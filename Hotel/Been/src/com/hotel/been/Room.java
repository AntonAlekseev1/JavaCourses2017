package com.hotel.been;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;
import com.hotel.api.been.Entity;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;

@CsvEntity(filename="Rooms.csv", entityId=3)
public class Room extends Entity implements IRoom, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Integer copacity;
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Integer numberOfStars;
	@CsvProperty(columnNumber = 4, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Double price;
	@CsvProperty(columnNumber = 5, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Boolean isFree = true;
	@CsvProperty(columnNumber = 6, propertyType = PropertyType.SIMPLE_PROPERTY)
	private RoomStatus status;
	private List<IHistory> history=new ArrayList<>();

	public Room(String string) {

		String[] arr = string.split(" ");
		setId(Integer.valueOf(arr[0]));
		this.copacity = Integer.valueOf(arr[1]);
		this.numberOfStars = Integer.valueOf(arr[2]);
		this.price = Double.valueOf(arr[3]);
		this.isFree = Boolean.valueOf(arr[4]);
		this.status = RoomStatus.valueOf(arr[5]);
	}

	public Room(Integer copacity, Integer numberOfStars, Double price) {

		this.copacity = copacity;
		this.numberOfStars = numberOfStars;
		this.price = price;
		history=new ArrayList<>();

	}

	public Boolean getIsFree() {

		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
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
	
	public List<IHistory> getHistory() {
		return history;
	}

	public void setHistory(List<IHistory> history) {
		this.history = history;
	}
	
	@Override
    public IRoom clone() throws CloneNotSupportedException {
		IRoom clon = (IRoom) super.clone();
		clon.setId(null);
		clon.setIsFree(null);
        return clon;
    }

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getId());
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
