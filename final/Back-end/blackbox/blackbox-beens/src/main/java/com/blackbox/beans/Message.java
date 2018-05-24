package com.blackbox.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.blackbox.beans.generic.Entity;

/**
 * This is the entity class extends {@link Entity}
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "messages")
public class Message extends Entity {

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "text")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dialog_id")
	private Dialog dialog;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_last_name")
	private String userLastName;

	public Message() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(text);
		sb.append("		");
		sb.append(date);
		return sb.toString();
	}

}
