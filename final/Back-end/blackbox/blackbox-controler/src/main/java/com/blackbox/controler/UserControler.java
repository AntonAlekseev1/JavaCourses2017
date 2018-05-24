package com.blackbox.controler;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.api.service.IUserService;
import com.blackbox.api.util.ITokenRepository;
import com.blackbox.beans.User;
import com.blackbox.controler.dto.UserDto;
import com.blackbox.util.DtoWorker;
import com.blackbox.util.JsonParser;
import com.blackbox.util.TokenRepository;

@RestController
@RequestMapping("/user")
public class UserControler {

	private static final Logger logger = Logger.getLogger(UserControler.class);
	@Autowired
	private IUserService userService;
	private DozerBeanMapper dozer = new DozerBeanMapper();
	private ITokenRepository tokenRepository = TokenRepository.getInstance();

	@GetMapping
	public Object getAllUsers() {
		try {
			return DtoWorker.listToDto(userService.getAllUsers(), UserDto.class);
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}

	}

	@GetMapping("/{id}")
	public Object getById(@PathVariable Integer id) {
		try {
			return dozer.map(userService.getUserById(id), UserDto.class);
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@GetMapping("search")
	public Object search(@RequestHeader String name) {
		try {
			return DtoWorker.listToDto(userService.search(name), UserDto.class);
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@GetMapping("login")
	public UserDto getByLogin(@RequestHeader String Authorization) {
		try {
			String login = tokenRepository.getLogin(Authorization);
			User user = userService.getUserByLogin(login);
			return dozer.map(user, UserDto.class);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@PostMapping
	public String update(@RequestBody User user) {
		try {
			userService.updateUser(user);
			return JsonParser.convertToJson("OK");
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@DeleteMapping("/{id}")
	public String remove(@PathVariable Integer id) {
		try {
			userService.removeUser(id);
			return "User was deleted";
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@GetMapping("/friends")
	public Object getFriends(@RequestHeader String id) {
		try {
			return DtoWorker.listToDto(userService.getFriends(Integer.valueOf(id)), UserDto.class);
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}

	@PostMapping("/friends")
	public Object addFriend(@RequestBody Integer[] request) {
		try {
			Integer userId = request[0];
			Integer friendId = request[1];
			userService.addToFriends(userId, friendId);
			userService.addToFriends(friendId, userId);
			return JsonParser.convertToJson("OK");
		} catch (Exception e) {
			logger.error(e);
			return JsonParser.convertToJson(e.getMessage());
		}
	}

	@DeleteMapping("/friends")
	public Object removeFrend(@RequestHeader String userId, @RequestHeader String friendId) {
		try {
			userService.removeFromFriends(Integer.valueOf(userId), Integer.valueOf(friendId));
			userService.removeFromFriends(Integer.valueOf(friendId), Integer.valueOf(userId));
			return JsonParser.convertToJson("OK");
		} catch (Exception e) {
			logger.error(e);
			return JsonParser.convertToJson(e.getMessage());
		}
	}

	@GetMapping("/dialog/{id}")
	public Object getDialogUsers(@PathVariable Integer id) {
		try {
			return DtoWorker.listToDto(userService.getDialogUsers(id), UserDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/group/{id}")
	public Object getGroupUsers(@PathVariable Integer id) {
		try {
			return DtoWorker.listToDto(userService.getGroupUsers(id), UserDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/admin/{id}")
	public Object getGroupAdmin(@PathVariable Integer id) {
		try {
			return dozer.map(userService.getGroupAdmin(id), UserDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
