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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.api.dto.IDto;
import com.blackbox.api.service.IGroupService;
import com.blackbox.api.service.IUserService;
import com.blackbox.beans.Event;
import com.blackbox.beans.Group;
import com.blackbox.beans.User;
import com.blackbox.controler.dto.GroupDto;
import com.blackbox.util.DtoWorker;
import com.blackbox.util.JsonParser;

@RestController
@RequestMapping("/group")
public class GroupControler {

	private static final Logger logger = Logger.getLogger(GroupControler.class);
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	private DozerBeanMapper dozer = new DozerBeanMapper();

	@GetMapping
	public Object getAll() {
		try {
			return DtoWorker.listToDto(groupService.getAllGroups(), GroupDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/{id}")
	public Object getById(@PathVariable Integer id) {
		try {
			return dozer.map(groupService.getGroupById(id), GroupDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/name")
	public String getByName(@RequestHeader String name) {
		try {
			String message = groupService.getGroupByName(name).toString();
			return message;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PutMapping
	public Object create(@RequestBody GroupDto groupDto, @RequestHeader String id) {
		try {
			Group group = dozer.map(groupDto, Group.class);
			User user = userService.getUserById(Integer.valueOf(id));
			groupService.createGroup(group);
			group.setAdmin(user);
			groupService.updateGroup(group);
			groupService.addUserToGroup(Integer.valueOf(id), group.getId());
			return dozer.map(group, GroupDto.class);
		} catch (Exception e) {
			return JsonParser.convertToJson(e.getMessage());
		}
	}

	@PostMapping
	public String update(@RequestBody Group group) {
		try {
			groupService.updateGroup(group);
			return "successful update";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@DeleteMapping("/{id}")
	public String remove(@PathVariable Integer id) {
		try {
			groupService.removeGroup(id);
			return "User was deleted";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/user")
	public List<IDto> getUserGroups(@RequestHeader String id) {
		try {
			return DtoWorker.listToDto(groupService.getUserGroups(Integer.valueOf(id)), GroupDto.class);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@PostMapping("/user")
	public String addUserToGroup(@RequestBody Integer[] request) {
		try {
			Integer userId = request[0];
			Integer groupId = request[1];
			groupService.addUserToGroup(userId, groupId);
			return JsonParser.convertToJson("OK");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@DeleteMapping("/user")
	public String removeUser(@RequestHeader String userId, @RequestHeader String groupId) {
		try {
			groupService.removeUserFromGroup(Integer.valueOf(userId), Integer.valueOf(groupId));
			String message = "";
			return message;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PostMapping(value = "/events/{id}")
	public String addEvent(@RequestBody Event event, @PathVariable Integer id) {
		try {
			groupService.addGroupEvent(event, id);
			String message = "successful creation";
			return message;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@DeleteMapping(value = "/events")
	public String removeEvent(@RequestParam Integer eventId, @RequestParam Integer groupId) {
		try {
			groupService.removeGroupEvent(eventId, groupId);
			;
			String message = "successful removing";
			return message;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
