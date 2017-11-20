package com.hotel.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
	private static Properties properties=new Properties();
	
	public static void loadConfiguration() {
		try {
			FileInputStream fiStream = new FileInputStream("../data/configuratins.properties");
				properties.load(fiStream);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String key) {
		return properties.getProperty(key);
		
	}
	

}
