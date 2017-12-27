package com.hotel.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;



public class Configuration {
	
	private final static Logger logger = Logger.getLogger(Configuration.class);
	private static Properties properties = new Properties();

	public static void loadConfiguration() {
		try {
			FileInputStream inputStream = new FileInputStream("../Fasad/data/configuratins.properties");
			properties.load(inputStream);
			inputStream.close();	

		} catch (FileNotFoundException e) {
			logger.info("File not found "+e.getMessage());

		} catch (IOException e) {
			
		}
	}

	public static String getProperties(String key) {
		return properties.getProperty(key);

	}

}
