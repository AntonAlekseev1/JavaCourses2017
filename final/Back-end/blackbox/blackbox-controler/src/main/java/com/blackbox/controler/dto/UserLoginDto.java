package com.blackbox.controler.dto;

import com.blackbox.api.dto.IDto;
import com.blackbox.beans.enums.Role;

public class UserLoginDto implements IDto {

	private Integer id;

	private String login;

	private String password;

	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}