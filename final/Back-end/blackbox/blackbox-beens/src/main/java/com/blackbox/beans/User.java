package com.blackbox.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.blackbox.beans.enums.Role;
import com.blackbox.beans.generic.Entity;

/**
 * This is the entity class extends {@link Entity}
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@javax.persistence.Entity
@Table(name = "users")
public class User extends Entity {
	/** field login */
	@Column(name = "login")
	private String login;
	/** field password */
	@Column(name = "password")
	private String password;
	/** field name */
	@Column(name = "name")
	private String name;
	/** field lastName */
	@Column(name = "last_name")
	private String lastName;
	/** field role of the user */
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	/** field user birthday */
	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "user_frends", joinColumns = { @JoinColumn(name = "frend_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private Set<User> friends;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Event> events;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "group_users", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "group_id") })
	private Set<Group> groups;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "user_dialogs", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "dialog_id") })
	private Set<Dialog> dialogs;

	/**
	 * 
	 * Constructor - creating a new object
	 */
	public User() {

		role = Role.USER;
	}

	/**
	 * 
	 * Function of obtaining the value of the field{@link User#login}
	 * 
	 * @return user login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Function of defining the login field{@link User#login}
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Function of obtaining the value of the field{@link User#password}
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Function of defining the login field{@link User#password}
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Function of defining the user friends{@link User#friends}
	 * 
	 * @return {@link User#friends}
	 */
	public Set<User> getFriends() {
		return friends;
	}

	/**
	 * Function of obtaining the value of the field {@link User#friends}
	 * 
	 * @param {@link
	 * 			User#friends}
	 */
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Dialog> getDialogs() {
		return dialogs;
	}

	public void setDialogs(Set<Dialog> dialogs) {
		this.dialogs = dialogs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append(" ");
		sb.append(name);
		sb.append(" ");
		sb.append(lastName);
		sb.append(" ");
		sb.append(birthday);
		return sb.toString();

	}

}
