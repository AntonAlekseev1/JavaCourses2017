package com.project.been;

public class Option {
	private String option;
	private Double price;

	public Option() {

	}

	public Option(String option, Double price) {
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
		s.append("Option: ");
		s.append(option);
		s.append(" ");
		s.append("price ");
		s.append(price);
		return s.toString();
	}

}
