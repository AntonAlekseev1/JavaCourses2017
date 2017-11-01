package com.hotel.been;

public class Guest extends Entity {
	private String name;
	private String lastName;

	public Guest(String string) {
		String[] arr = string.split(" ");
		setId(Integer.valueOf(arr[0]));
		this.name = String.valueOf(arr[1]);
		this.lastName = String.valueOf(arr[2]);

	}

	public Guest(String name, String lastName, Integer id) {
		super(id);
		this.name = name;
		this.lastName = lastName;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getId());
		s.append(" ");
		s.append(name);
		s.append(" ");
		s.append(lastName);
		return s.toString();
	}

}
