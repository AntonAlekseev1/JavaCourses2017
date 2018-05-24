package com.blackbox.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "groups")
public class Group extends Entity {

	@Column(name = "name")
	private String name;

	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "group_users", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private Set<User> users;

	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private Set<Event> events;

	@ManyToOne(fetch = FetchType.LAZY, cascade= { CascadeType.ALL })
	@JoinColumn(name = "admin")
	private User admin;

	public Group() {

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

	public Set<User> getGroupUsers() {
		return users;
	}

	public void setGroupUsers(Set<User> groupUsers) {
		this.users = groupUsers;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append(" ");
		sb.append(name);
		sb.append(" ");
		sb.append(creationDate);
		return sb.toString();
	}

}
