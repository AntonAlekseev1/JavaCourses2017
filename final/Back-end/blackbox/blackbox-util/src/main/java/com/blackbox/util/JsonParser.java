package com.blackbox.util;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonParser {

	public static JSONObject parseJson(HttpServletRequest request) throws Exception {
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		return new JSONObject(body);

	}

	public static Object getValueFromJson(JSONObject obj, String name) {
		Object response = obj.get(name);
		return response;
	}

	public static String convertToJson(Object obj) {
		Gson gson = new Gson();
		String response = gson.toJson(obj);
		return response;
	}

}
