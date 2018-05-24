package com.blackbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackbox.api.dao.IEventDao;
import com.blackbox.api.service.IEventService;
import com.blackbox.beans.Event;

/**
 * The Class EventService that manages events.
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Service("eventService")
@Transactional
public class EventService implements IEventService {

	@Autowired
	private IEventDao eventDao;

	@Override
	public void createEvent(Event event) throws Exception {

		eventDao.create(event);

	}

	@Override
	public void removeEvent(Integer id) throws Exception {

		eventDao.delete(eventDao.getById(id));
	}

	@Override
	public List<Event> getUserEvents(Integer id) throws Exception {

		return eventDao.getUserEvents(id);
	}

	@Override
	public List<Event> getGroupEvents(Integer id) throws Exception {

		return eventDao.getGroupEvents(id);
	}

}
