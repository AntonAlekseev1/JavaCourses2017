package com.hotel.been;

import javax.persistence.Column;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "Users")
public class User extends Entity {

	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;


	public User() {

	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
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

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getId());
		s.append(" ");
		s.append(login);
		s.append(" ");
		s.append(password);
		return s.toString();
	}



}
