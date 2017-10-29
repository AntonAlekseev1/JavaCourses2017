package com.project.been;

import java.util.Date;

public class Room {
	private Integer number;
	private Integer copacity;
	private Integer numberOfStars;
	private Double price;
	private Boolean isFree = true;
	private Date settleDate;
	private Date evictDate;

	// private Guest [] guestsOfNumber=new Guest[5];
	public Room() {

	}

	public Room(Integer number, Integer copacity, Integer numberOfStars, Double price, Date settleDate,
			Date evictDate) {
		this.number = number;
		this.copacity = copacity;
		this.numberOfStars = numberOfStars;
		this.price = price;
		this.settleDate = settleDate;
		this.evictDate = evictDate;
	}

	public Room(Integer number, Integer copacity, Integer numberOfStars, Double price) {
		this.number = number;
		this.copacity = copacity;
		this.numberOfStars = numberOfStars;
		this.price = price;

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

	// public Guest [] getGuestsOfNumber() {
	// return guestsOfNumber;
	// }
	// public void setGuestsOfNumber(Guest [] guestsOfNumber) {
	// this.guestsOfNumber = guestsOfNumber;
	// }
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Room: ");
		s.append(number);
		s.append(" copacity-");
		s.append(copacity);
		s.append(" stars-");
		s.append(numberOfStars);
		s.append(" price=");
		s.append(price);
		s.append(" is free-");
		s.append(isFree);
		return s.toString();
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public Date getEvictDate() {
		return evictDate;
	}

	public void setEvictDate(Date evictDate) {
		this.evictDate = evictDate;
	}

}
