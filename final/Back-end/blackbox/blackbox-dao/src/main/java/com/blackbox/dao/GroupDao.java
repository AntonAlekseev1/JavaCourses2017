package com.blackbox.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.blackbox.api.dao.IGroupDao;
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
@Repository("groupDao")
public class GroupDao extends AbstractDao<Group> implements IGroupDao {

	public GroupDao() {
		super(Group.class);
	}

	/**
	 * This method makes the query in the database and returns a group whose name is
	 * equal to the name passed to the method like as parameter
	 * 
	 * @return {@link Group}
	 */
	@Override
	public Group getByName(String name) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Group> query = builder.createQuery(Group.class);
		Root<Group> group = query.from(Group.class);
		query.select(group).where(builder.equal(group.get("name"), name));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * This method makes the query in the database and returns a list of user groups
	 * with id equal to userId
	 * 
	 * @return list of groups
	 */
	@Override
	public List<Group> getUserGroups(Integer userId) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Group> query = builder.createQuery(Group.class);
		Root<User> user = query.from(User.class);
		Join<User, Group> groups = user.join("groups");
		query.select(groups).where(builder.equal(user, userId));

		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * This method makes the query in the database and returns the group of which
	 * the event belongs
	 * 
	 * @return {@link Group}
	 */
	@Override
	public Group getEventGroup(Integer eventId) throws Exception {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Group> query = builder.createQuery(Group.class);
		Root<Event> event = query.from(Event.class);
		Join<Event, Group> group = event.join("group");
		query.select(group).where(builder.equal(event, eventId));

		return entityManager.createQuery(query).getSingleResult();
	}

}
