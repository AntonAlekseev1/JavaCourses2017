package com.hotel.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenWorker {
	
	private final static String key = "gufEE35hQ";

	    public static String getToken(String login, String password){
	        if (login == null || password == null)
	            return null;
	        Map<String, Object> tokenData = new HashMap<>();
	            tokenData.put("clientType", "user");
	            tokenData.put("username", login);
	            tokenData.put("token_create_date", new Date().getTime());
	            Calendar calendar = Calendar.getInstance();
	            calendar.add(Calendar.YEAR, 100);
	            tokenData.put("token_expiration_date", calendar.getTime());
	            JwtBuilder jwtBuilder = Jwts.builder();
	            jwtBuilder.setExpiration(calendar.getTime());
	            jwtBuilder.setClaims(tokenData);
	           
	            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
	            return token;
	    }
}
