package com.hotel.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hotel.been.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenWorker {
	

	    public static String getToken(String username, String password) throws Exception {
	        if (username == null || password == null)
	            return null;
	        User user = new User("peter","123");
	        user.setId(1);//(User) userDetailsService.loadUserByUsername(username);
	        Map<String, Object> tokenData = new HashMap<>();
	        if (password.equals(user.getPassword())) {
	            tokenData.put("clientType", "user");
	            tokenData.put("userID", user.getId().toString());
	            tokenData.put("username", user.getLogin());
	            tokenData.put("token_create_date", new Date().getTime());
	            Calendar calendar = Calendar.getInstance();
	            calendar.add(Calendar.YEAR, 100);
	            tokenData.put("token_expiration_date", calendar.getTime());
	            JwtBuilder jwtBuilder = Jwts.builder();
	            jwtBuilder.setExpiration(calendar.getTime());
	            jwtBuilder.setClaims(tokenData);
	            String key = "abc123";
	            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
	            return token;
	        } else {
	            throw new Exception("Authentication error");
	        }
	    }

}
