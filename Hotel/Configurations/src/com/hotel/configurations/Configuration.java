package com.hotel.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {

	private final static Logger logger = Logger.getLogger(Configuration.class);
	private static Properties properties = new Properties();

	public static void loadConfiguration(String propertyPath) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(propertyPath);
			properties.load(inputStream);

		} catch (FileNotFoundException e) {
			logger.info("File not found " + e.getMessage());

		} catch (IOException e) {
			logger.info("Exception I/O stream " + e.getMessage());
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.info("Exception I/O stream " + e.getMessage());
			}
		}
	}

	public static String getProperties(String key) {
		return properties.getProperty(key);

	}

}
