package com.blackbox.controler.dto;

import java.util.Date;

import com.blackbox.api.dto.IDto;

public class EventDto implements IDto {

	private Integer id;

	private String name;

	private Date date;

	private String body;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
