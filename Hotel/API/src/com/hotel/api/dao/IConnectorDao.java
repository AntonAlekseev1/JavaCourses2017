package com.hotel.api.dao;

import java.sql.Connection;

public interface IConnectorDao {

	public Connection getConection() throws Exception;

	public void close() throws Exception;

}
