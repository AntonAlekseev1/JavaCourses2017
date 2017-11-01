package com.hotel.been;

public class Option extends Entity {
	private String option;
	private Double price;

	public Option(Integer id) {
		super(id);

	}

	public Option(Integer id, String option, Double price) {
		super(id);
		this.option = option;
		this.price = price;
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
		s.append(option);
		s.append(" ");
		s.append(price);
		return s.toString();
	}

}
