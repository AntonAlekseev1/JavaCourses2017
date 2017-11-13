package com.hotel.api.been;

public interface IGuest {
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public String getName();
	
	public String getLastName();
	
	public IHistory getHistory();
	
	public String toString();

	public void setHistory(IHistory history);

}
