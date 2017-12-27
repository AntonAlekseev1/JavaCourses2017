package com.hotel.been;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;

public class Room extends Entity implements IRoom {

	private Integer copacity;
	private Integer numberOfStars;
	private Double price;
	private Boolean isFree = true;
	private RoomStatus status;
	private List<IHistory> history;

	public Room(String string) {

		String[] arr = string.split(" ");
		setId(Integer.valueOf(arr[0]));
		this.copacity = Integer.valueOf(arr[1]);
		this.numberOfStars = Integer.valueOf(arr[2]);
		this.price = Double.valueOf(arr[3]);
		isFree = true;
		status = RoomStatus.OPEN;
	}

	public Room(Integer copacity, Integer numberOfStars, Double price) {

		this.copacity = copacity;
		this.numberOfStars = numberOfStars;
		this.price = price;
		setStatus(RoomStatus.OPEN);
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

	public List<IHistory> getHistory() {
		return history;
	}

	public void setHistory(List<IHistory> history) {
		this.history = history;
	}

}
