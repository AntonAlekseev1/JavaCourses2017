package com.hotel.been;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hotel.annatation.CsvEntity;
import com.hotel.annatation.CsvProperty;
import com.hotel.annatation.CsvProperty.PropertyType;

@javax.persistence.Entity
@Table(name = "Options")
@CsvEntity(filename = "Options.csv", entityId = 2)
public class Option extends Entity {

	@Column(name = "name")
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE_PROPERTY)
	private String name;
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE_PROPERTY)
	@Column(name = "price")
	private Double price;
	@ManyToMany(mappedBy = "options",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<History> histories;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

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
