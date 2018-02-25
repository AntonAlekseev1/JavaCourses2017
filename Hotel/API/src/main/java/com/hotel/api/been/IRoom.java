package com.hotel.api.been;

public interface IRoom extends IEntity {
	
	public IRoom clone() throws CloneNotSupportedException;
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public Boolean getIsFree();
	
	public void setIsFree(Boolean isFree);
	
	public Integer getCopacity();
	
	public void setCopacity(Integer copacity);
	
	public Integer getNumberOfStars();
	
	public void setNumberOfStars(Integer numberOfStars);
	
	public Double getPrice();
	
	public void setPrice(Double price);
	
	public RoomStatus getStatus();
	
	public void setStatus(RoomStatus status);
	
	void setNumber(Integer number);

	Integer getNumber();
}
