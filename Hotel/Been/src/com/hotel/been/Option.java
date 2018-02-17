package com.hotel.been;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;
import com.hotel.api.been.Entity;
import com.hotel.api.been.IOption;

@CsvEntity(filename="Options.csv",entityId=2)
public class Option extends Entity implements IOption {
	
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String name;
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Double price;

	public Option() {
		
	}
	
	public Option(String name, Double price) {

		this.name = name;
		this.price = price;
	}

	public Option(String string) {
		String[] arr = string.split(" ");
		setId(Integer.valueOf(arr[0]));
		this.name = String.valueOf(arr[1]);
		this.price = Double.valueOf(arr[2]);
	}
    @Override
	public String getName() {
		return name;
	}
    @Override
	public void setName(String name) {
		this.name = name;
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
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getId());
		s.append(" ");
		s.append(name);
		s.append(" ");
		s.append(price);
		return s.toString();
	}

}
