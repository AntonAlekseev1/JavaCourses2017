package com.blackbox.beans;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.blackbox.beans.generic.Entity;

/**
 * This is the entity class extends {@link Entity}
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "dialogs")
public class Dialog extends Entity {

	@Column(name = "header")
	private String header;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_dialogs", joinColumns = { @JoinColumn(name = "dialog_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private Set<User> dialogUsers;

	@OneToMany(mappedBy = "dialog", fetch = FetchType.LAZY, cascade = { CascadeType.ALL }) //
	private List<Message> messages;

	public Dialog() {

	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Set<User> getDialogUsers() {
		return dialogUsers;
	}

	public void setDialogUsers(Set<User> dialogUsers) {
		this.dialogUsers = dialogUsers;
	}

	public List<Message> getDialogMessages() {
		return messages;
	}

	public void setDialogMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append(" ");
		sb.append(header);
		return sb.toString();
	}

}
