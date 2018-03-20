package com.hotel.been;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Log_table")
public class LogEntity extends com.hotel.been.Entity {
	@Column(name = "time")
	private String time;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "action")
	private String actionName;
	
	public LogEntity() {
		
	}
	
	public LogEntity(String time, String name, String action) {
		
		this.time = time;
		userName = name;
		actionName = action;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	

}
