package com.hotel.api.been;

import java.util.Date;
import java.util.List;

<<<<<<< HEAD
public interface IHistory {
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public Date getDateOfArrival();
	
	public Date getEvictDate();
	
	public IGuest getGuest();
	
	public IRoom getRoom();
	
	public List<IOption> getOptions();
	
	public String toString();
=======
public interface IHistory extends IEntity {
>>>>>>> lesson11

	Date getDateOfArrival();

	Integer getGuestId();

	void setGuest(Integer guestId);

	Integer getRoomId();

	void setRoom(Integer roomId);

	Date getEvictDate();

	List<IOption> getOptions();

	void setOptions(List<IOption> options);

	void setDateOfArival(Date dateOfArival);

	void setEvictDate(Date evictDate);
}
