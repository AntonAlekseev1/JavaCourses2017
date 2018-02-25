package com.hotel.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hotel.api.dao.IConnectorDao;
import com.hotel.configurations.Configuration;

public class Connector implements IConnectorDao {

	private static final Logger logger = Logger.getLogger(Connector.class);
	private static Connector instance;
	private Connection connection;
	private String propertiPath = "../DAO/src/main/resources/configurationJDCB.properties";

	private Connector() throws Exception {
		connect();
	}

	private Connection connect() throws Exception {
		Configuration.loadConfiguration(propertiPath);
		String DRIVER = String.valueOf(Configuration.getProperties("DRIVER"));
		String URL = String.valueOf(Configuration.getProperties("URL"));
		String NAME = String.valueOf(Configuration.getProperties("NAME"));
		String PASSWORD = String.valueOf(Configuration.getProperties("PASSWORD"));
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return connection;

	}

	public static Connector getInstance() throws Exception {

		if (instance == null) {
			instance = new Connector();

		}
		return instance;
	}

	@Override
	public Connection getConection() throws Exception {
		if (connection.isClosed() | connection == null) {
			connect();
		}

		return connection;
	}

	@Override
	public void close() throws Exception {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

}
