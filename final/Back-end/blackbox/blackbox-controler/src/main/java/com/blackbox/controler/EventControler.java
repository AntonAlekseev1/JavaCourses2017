package com.blackbox.controler;

import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.api.dto.IDto;
import com.blackbox.api.service.IEventService;
import com.blackbox.api.service.IGroupService;
import com.blackbox.api.service.IUserService;
import com.blackbox.beans.Event;
import com.blackbox.controler.dto.EventDto;
import com.blackbox.util.DtoWorker;
import com.blackbox.util.JsonParser;

@RestController
@RequestMapping("event")
public class EventControler {

	private static final Logger logger = Logger.getLogger(EventControler.class);
	@Autowired
	private IEventService eventService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	private DozerBeanMapper dozer = new DozerBeanMapper();

	@PutMapping("/{id}")
	public Object addEventToUser(@RequestBody EventDto eventDto, @PathVariable Integer id) {
		try {
			Event event = dozer.map(eventDto, Event.class);
			event.setUser(userService.getUserById(id));
			eventService.createEvent(event);
			return JsonParser.convertToJson(eventDto);
		} catch (Exception e) {
			logger.warn(e);
			return JsonParser.convertToJson(e.getMessage());
		}
	}

	@PostMapping("/{id}")
	public Object addEventToGroup(@RequestBody EventDto eventDto, @PathVariable Integer id) {
		try {
			Event event = dozer.map(eventDto, Event.class);
			event.setGroup(groupService.getGroupById(id));
			eventService.createEvent(event);
			return JsonParser.convertToJson(event.toString());
		} catch (Exception e) {
			logger.warn(e);
			return JsonParser.convertToJson(e.getMessage());
		}
	}

	@GetMapping("/group/{id}")
	public Object getGroupEvents(@PathVariable Integer id) {
		try {
			return DtoWorker.listToDto(eventService.getGroupEvents(id), EventDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/user/{id}")
	public List<IDto> getUserEvents(@PathVariable Integer id) {
		try {
			return DtoWorker.listToDto(eventService.getUserEvents(id), EventDto.class);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable Integer id) {
		try {
			eventService.removeEvent(id);
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
