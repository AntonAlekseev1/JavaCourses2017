package com.blackbox.controler;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.api.service.IUserService;
import com.blackbox.api.util.ITokenRepository;
import com.blackbox.beans.User;
import com.blackbox.controler.dto.UserDto;
import com.blackbox.controler.dto.UserLoginDto;
import com.blackbox.util.JsonParser;
import com.blackbox.util.PasswordEncryptor;
import com.blackbox.util.TokenRepository;
import com.blackbox.util.TokenWorker;

@RestController
@RequestMapping("/login")
public class LoginControler {

	private static final Logger logger = Logger.getLogger(LoginControler.class);
	private ITokenRepository tokenRepository = TokenRepository.getInstance();
	private DozerBeanMapper dozer = new DozerBeanMapper();

	@Autowired
	private IUserService userService;

	@PostMapping
	public String logIn(@RequestBody UserLoginDto loginDto) {
		try {
			String login = loginDto.getLogin();
			String password = loginDto.getPassword();
			User user = userService.getUserByLogin(login);
			String token = null;
			String json = null;
			if (user != null && user.getPassword().equals(PasswordEncryptor.encryptPassword(password))) {
				token = TokenWorker.generateToken(login);
				tokenRepository.putToken(token, login);
				json = JsonParser.convertToJson(token);
				return json;
			}
			return JsonParser.convertToJson("ERROR");
		} catch (Exception e) {
			return JsonParser.convertToJson("ERROR");
		}
	}

	@PutMapping
	public String create(@RequestBody UserDto userDto) {
		try {
			User user = dozer.map(userDto, User.class);
			user.setPassword(PasswordEncryptor.encryptPassword(userDto.getPassword()));
			userService.addUser(user);
			return JsonParser.convertToJson("OK");
		} catch (Exception e) {
			logger.error(e);
			return JsonParser.convertToJson(e.getMessage());
		}
	}

	@DeleteMapping
	public String logout(@RequestHeader String Authorization) {
		try {
			tokenRepository.removeToken(Authorization);
			return JsonParser.convertToJson("goodbye");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
