package com.hotel.been;

import java.io.Serializable;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;
import com.hotel.api.been.Entity;
import com.hotel.api.been.IOption;

@CsvEntity(filename="Options.csv",entityId=2)
public class Option extends Entity implements IOption, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String option;
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	private Double price;

	public Option(String option, Double price) {

		this.option = option;
		this.price = price;
	}

	public Option(String string) {
		String[] arr = string.split(" ");
		setId(Integer.valueOf(arr[0]));
		this.option = String.valueOf(arr[1]);
		this.price = Double.valueOf(arr[2]);
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getId());
		s.append(" ");
		s.append(option);
		s.append(" ");
		s.append(price);
		return s.toString();
	}

}
