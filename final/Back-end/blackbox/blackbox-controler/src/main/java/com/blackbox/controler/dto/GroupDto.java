package com.blackbox.controler.dto;

import java.util.Date;

import com.blackbox.api.dto.IDto;

public class GroupDto implements IDto {
	
	private Integer id;
	
	private String name;
	
	private Date creationDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

}
