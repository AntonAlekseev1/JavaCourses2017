package com.hotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hotel.configurations.Configuration;

public class Connector {
	
	private static final Logger logger = Logger.getLogger(Connector.class);
	private static Connector instance;
	private Connection connection;
	private String URL;
	private String DRIVER;
	private String NAME;
	private String PASSWORD;
	private String propertiPath = "../DAO/resources/configurationJDCB.properties";
	
	private Connector() {
			connect();
	}
	private Connection connect(){
		Configuration.loadConfiguration(propertiPath);
		DRIVER = String.valueOf(Configuration.getProperties("DRIVER"));
		URL = String.valueOf(Configuration.getProperties("URL"));
		NAME = String.valueOf(Configuration.getProperties("NAME"));
    	PASSWORD = String.valueOf(Configuration.getProperties("PASSWORD"));
			try {
				Class.forName(DRIVER);
				connection=DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch ( SQLException|ClassNotFoundException e) {
			logger.error(e.getMessage());
    	}
		return connection;
		
	}
	
	public static Connector getinstance() {
	
		if(instance==null) {
			instance = new Connector();
		
		}
		return instance;
	}
	
	public Connection getConection() throws SQLException {
		if(connection.isClosed()) {
			connect();
		}
		
		return  connection;
	}
	
	public void close() throws Exception {
		if(connection!=null) {
			connection.close();
			connection = null;
		}
	}

}
