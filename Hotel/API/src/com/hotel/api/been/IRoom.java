package com.hotel.api.been;

import java.util.List;

import com.hotel.api.been.RoomStatus;

<<<<<<< HEAD
public interface IRoom  {
=======
public interface IRoom extends IEntity {
	
	public IRoom clone() throws CloneNotSupportedException;
>>>>>>> lesson11
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public Boolean getIsFree();
	
	public void setIsFree(Boolean isFree);
	
	public Integer getCopacity();
	
	public Integer getNumberOfStars();
	
	public Double getPrice();
	
	public void setPrice(Double price);
	
	public RoomStatus getStatus();
	
	public void setStatus(RoomStatus status);
	
	public List<IHistory> getHistory();
	
	public String toString();

	void setHistory(List<IHistory> history);

	void setNumber(Integer number);

	Integer getNumber();
}
