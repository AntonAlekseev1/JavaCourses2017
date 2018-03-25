package com.hotel.utils;

import java.util.HashMap;
import java.util.Map;

import com.hotel.api.util.ITokenRepository;

public class TokenRepository implements ITokenRepository {
	
	private static TokenRepository instance;
	private Map<String, String>tokenMap;
	
	private TokenRepository() {
		tokenMap = new HashMap<>();
		
	}
	
	public static TokenRepository getInstance() {
		if(instance == null) {
			instance = new TokenRepository();
		}
		return instance;
	}
	@Override
	public String getToken(String login) {
		String token = tokenMap.get(login);
		return token;
	}
	@Override
	public void putToken(String login,String token) {
		tokenMap.put(login, token);
	}
	@Override
	public void removeToken(String login) {
		tokenMap.remove(login);
	}

}
