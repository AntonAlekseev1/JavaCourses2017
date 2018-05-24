package com.blackbox.controler.dto;

import com.blackbox.api.dto.IDto;

public class DialogDto implements IDto {
	
	private Integer id;
	
	private String header;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	

}
