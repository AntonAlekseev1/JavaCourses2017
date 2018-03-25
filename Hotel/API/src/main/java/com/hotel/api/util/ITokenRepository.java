package com.hotel.api.util;

public interface ITokenRepository {

	public String getToken(String login);

	public void putToken(String login, String token);

	public void removeToken(String login);
	

}
