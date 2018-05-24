package com.blackbox.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.blackbox.api.dao.IEventDao;
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
public class EventDao extends AbstractDao<Event> implements IEventDao {

	public EventDao() {
		super(Event.class);
	}

	/**
	 * This method makes the query in the database and returns a list of user events
	 * with id equal to userId
	 * 
	 * @return list of Events
	 */
	@Override
	public List<Event> getUserEvents(Integer userId) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Event> query = builder.createQuery(Event.class);
		Root<User> user = query.from(User.class);
		Join<User, Event> events = user.join("events");
		query.select(events).where(builder.equal(user, userId));

		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * This method makes the query in the database and returns a list of group
	 * events with id equal to groupId
	 * 
	 * @return list of Events
	 */
	@Override
	public List<Event> getGroupEvents(Integer groupId) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Event> query = builder.createQuery(Event.class);
		Root<Group> group = query.from(Group.class);
		Join<Group, Event> events = group.join("events");
		query.select(events).where(builder.equal(group, groupId));

		return entityManager.createQuery(query).getResultList();
	}

}
