package com.blackbox.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class PasswordEncryptor {

	private static final Logger logger = Logger.getLogger(PasswordEncryptor.class);
	private static final String KEY = "qwdsll7Uyh";

	public static String encryptPassword(String password) {
		String input = (password + KEY);
		String encryptedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			encryptedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error: ", e);
		}
		return encryptedPassword;
	}

}
