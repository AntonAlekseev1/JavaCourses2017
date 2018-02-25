package com.hotel.fasad;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class Wrapper {
	private final static Logger logger = Logger.getLogger(Wrapper.class);
	private String response = "response";

	public String getResponse(String request) {

		String[] arrey = request.split(" ");
		String[] values = new String[arrey.length - 1];
		for (int i = 1; i < arrey.length; i++) {
			values[i - 1] = arrey[i];
		}
		String name = arrey[0];
		Class<?>[] paramTypes = new Class<?>[values.length];
		for (int i = 0; i < paramTypes.length; i++) {
			paramTypes[i] = String.class;
		}
		Class<?> clazz = Hotel.class;
		try {
			Method method = clazz.getMethod("getInstance");
			Method allMethods = null;
			if (values.length == 0) {
				allMethods = clazz.getMethod(name);
			} else {
				allMethods = clazz.getMethod(name, paramTypes);
			}
			try {
				Object obj = method.invoke(clazz);
				Object var = null;
				if (values.length == 0) {
					var = allMethods.invoke(obj);
				} else {
					var = allMethods.invoke(obj, values);
				}
				if (var != null) {
					response = var.toString();
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("invoke method exception " + e.getMessage());
				response = "Wrapper: invoke method exception " + e.getMessage();
			}
		} catch (NoSuchMethodException e) {
			logger.error("No such method " + e.getMessage());
			response = "Wrapper: No such method " + e.getMessage();
		} catch (SecurityException e) {
			logger.error("Security Exception " + e.getMessage());
			response = "Wrapper: Security Exception " + e.getMessage();
		}

		return response;

	}
}
