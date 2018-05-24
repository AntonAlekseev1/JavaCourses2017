package com.blackbox.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blackbox.api.util.ITokenRepository;

@Repository
public class TokenRepository implements ITokenRepository {

	private static TokenRepository instance;

	private Map<String, String> tokenMap;

	private TokenRepository() {
		tokenMap = new HashMap<>();
	}

	public static TokenRepository getInstance() {
		if (instance == null) {
			instance = new TokenRepository();
		}
		return instance;
	}

	public String getLogin(String token) {
		String login = tokenMap.get(token);
		return login;
	}

	public void putToken(String token, String login) {
		tokenMap.put(token, login);
	}

	public void removeToken(String token) {
		tokenMap.remove(token);
	}

}
