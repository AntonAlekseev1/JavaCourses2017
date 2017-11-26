package com.hotel.api.been;

import java.util.Date;
import java.util.List;

public interface IHistory extends IEntity {
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public Date getDateOfArrival();
	
	public Date getEvictDate();
	
	public IGuest getGuest();
	
	public IRoom getRoom();
	
	public List<IOption> getOptions();
	
	public String toString();


}
