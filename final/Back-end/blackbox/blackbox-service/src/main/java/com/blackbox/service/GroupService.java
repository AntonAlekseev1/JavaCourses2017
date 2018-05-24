package com.blackbox.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackbox.api.dao.IEventDao;
import com.blackbox.api.dao.IGroupDao;
import com.blackbox.api.dao.IUserDao;
import com.blackbox.api.service.IGroupService;
import com.blackbox.beans.Event;
import com.blackbox.beans.Group;
import com.blackbox.beans.User;

/**
 * The Class GroupService that manages groups.
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Service("groupService")
@Transactional
public class GroupService implements IGroupService {

	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IEventDao eventDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public List<Group> getAllGroups() throws Exception {

		return groupDao.getAll("id");
	}

	@Override
	public Group getGroupById(Integer id) throws Exception {

		return groupDao.getById(id);
	}

	@Override
	public Group getGroupByName(String name) throws Exception {

		return groupDao.getByName(name);
	}

	@Override
	public void createGroup(Group group) throws Exception {

		groupDao.create(group);
	}

	@Override
	public void updateGroup(Group group) throws Exception {

		groupDao.update(group);
	}

	@Override
	public void removeGroup(Integer id) throws Exception {

		groupDao.delete(groupDao.getById(id));
	}

	@Override
	public void addUserToGroup(Integer userId, Integer groupId) throws Exception {

		Group group = groupDao.getById(groupId);
		Set<User> users = group.getGroupUsers();
		users.add(userDao.getById(userId));
		group.setGroupUsers(users);
	}

	@Override
	public void removeUserFromGroup(Integer userId, Integer groupId) throws Exception {

		Group group = groupDao.getById(groupId);
		Set<User> users = group.getGroupUsers();
		users.remove(userDao.getById(userId));
		group.setGroupUsers(users);
	}

	@Override
	public void addGroupEvent(Event event, Integer groupId) throws Exception {

		Group group = groupDao.getById(groupId);
		Set<Event> events = group.getEvents();
		events.add(event);
		group.setEvents(events);
	}

	@Override
	public void removeGroupEvent(Integer eventId, Integer groupId) throws Exception {

		Group group = groupDao.getById(groupId);
		Set<Event> events = group.getEvents();
		events.remove(eventDao.getById(eventId));
		group.setEvents(events);
	}

	@Override
	public List<Group> getUserGroups(Integer id) throws Exception {
		
		return groupDao.getUserGroups(id);
	}

}
