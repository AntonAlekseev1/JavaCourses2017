package com.hotel.api.been;

public interface IOption {
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public String getName();
	
	public Double getPrice();
	
	public String toString();

	void setName(String name);

	void setPrice(Double price);

}
