package com.hotel.api.been;

public class Entity implements  IEntity{
	
	private Integer id;

	public Entity() {
	}

	public Entity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
