package com.project.been;

public class Guest extends Entity {
	private String name;
	private String lastName;
	private Integer id;
	private Room room;

	public Guest() {

	}

	public Guest(String name, String lastName, Integer id) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Guest ");
		s.append(name);
		s.append(" ");
		s.append(lastName);
		s.append(" ");
		s.append(id);
		return s.toString();
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
