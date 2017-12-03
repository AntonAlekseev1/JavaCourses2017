package com.hotel.api.been;

import java.io.Serializable;

public class Entity implements Serializable, IEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
