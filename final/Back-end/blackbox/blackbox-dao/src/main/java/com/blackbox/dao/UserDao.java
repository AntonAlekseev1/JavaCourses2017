package com.blackbox.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.blackbox.api.dao.IUserDao;
import com.blackbox.beans.Dialog;
import com.blackbox.beans.Event;
import com.blackbox.beans.Group;
import com.blackbox.beans.User;
import com.blackbox.dao.generic.AbstractDao;

/**
 * Class for working with a database layer, extends {@link AbstractDao}
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {

	public UserDao() {
		super(User.class);
	}

	@Override
	public User getByName(String name) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> user = query.from(User.class);
		query.select(user).where(builder.equal(user.get("name"), name));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * This method makes the query in the database and returns an object of the
	 * class {@link User} whose login is equal to the input login
	 * 
	 * @return {@link User}
	 */
	@Override
	public User getByLogin(String login) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> user = query.from(User.class);
		query.select(user).where(builder.equal(user.get("login"), login));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * This method makes the query in the database and returns a list of the friends
	 * of the user whose "id" field is equal "userId" passed to the method like as
	 * parameter
	 * 
	 * @return List of Users
	 */
	@Override
	public List<User> getFriends(Integer userId) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> frends = builder.createQuery(User.class);
		Root<User> root = frends.from(User.class);
		Join<User, User> users = root.join("friends");
		frends.select(root);
		frends.where(builder.equal(users, userId));

		return entityManager.createQuery(frends).getResultList();

	}

	/**
	 * This method returns a list of users in the dialog, where the "id" of this
	 * dialog equals "dialogId", passed to the method like as parameter
	 * 
	 * @return List of Users
	 */
	@Override
	public List<User> getDialogUsers(Integer dialogId) throws Exception {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> dialogUsers = builder.createQuery(User.class);
		Root<User> root = dialogUsers.from(User.class);
		Join<User, Dialog> dialogs = root.join("dialogs");
		dialogUsers.select(root);
		dialogUsers.where(builder.equal(dialogs, dialogId));
		return entityManager.createQuery(dialogUsers).getResultList();
	}

	@Override
	public User getEventUser(Integer eventId) throws Exception {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<Event> event = query.from(Event.class);
		Join<Event, User> user = event.join("events");
		query.select(user).where(builder.equal(event, eventId));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * This method makes the query in the database and returns a list of users in
	 * the group, where the "id" of this group equals "groupId", passed to the
	 * method like as parameter
	 * 
	 * @return List of Users
	 */
	@Override
	public List<User> getGroupUsers(Integer groupId) throws Exception {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<Group> group = query.from(Group.class);
		Join<Group, User> users = group.join("users");
		query.select(users).where(builder.equal(group, groupId));

		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * This method returns the user that is the group administrator
	 * 
	 * @return {@link User}
	 */
	@Override
	public User getGroupAdmin(Integer groupId) throws Exception {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<Group> group = query.from(Group.class);
		Join<Group, User> user = group.join("admin");
		query.select(user).where(builder.equal(group, groupId));

		return entityManager.createQuery(query).getSingleResult();
	}

}
