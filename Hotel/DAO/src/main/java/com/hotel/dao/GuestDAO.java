package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.AbstractDaoOld;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.been.Guest;
import com.hotel.been.Option;

public class GuestDAO extends AbstractDaoOld<IGuest> implements IGuestDAO {

	private static final Logger logger = Logger.getLogger(GuestDAO.class);
	private static GuestDAO instance;

	private GuestDAO() {

	}

	public static GuestDAO getInstance() {

		if (instance == null) {
			instance = new GuestDAO();
		}
		return instance;
	}

	@Override
	public IGuest getByName(Connection connect, String name) throws Exception {
		String sql = "SELECT*FROM Guests WHERE name=?;";
		IGuest guest = new Guest();
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {
				statement.setString(1, name);
				ResultSet result = statement.executeQuery();
				result.next();

				guest.setId(result.getInt("id"));
				guest.setName(result.getString("name"));
				guest.setLastName(result.getString("last_name"));
			}
		} catch (SQLException e) {
			logger.error("Can't find guest by name", e);
			throw new Exception("Can't find guest by name: " + e);
		}

		return guest;
	}

	@Override
	public List<IOption> getGuestOptions(Connection connection, Integer id) throws Exception {
		String sql = "select options.id, options.name, options.price\r\n"
				+ "from options_history,options,history,guests\r\n"
				+ "where history.id_guest=guests.id and history.id=options_history.id_history and options_history.id_option= options.id and history.id_guest=?;";
		List<IOption> options = new ArrayList<>();
		try {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					IOption option = new Option();
					option.setId(result.getInt("id"));
					option.setName(result.getString("name"));
					option.setPrice(result.getDouble("price"));
					options.add(option);
				}
			}
		} catch (Exception e) {
			logger.error("Can't find guest services: ", e);
			throw new Exception("Can't find guest services: " + e);
		}

		return options;
	}

	@Override
	protected IGuest parseResultSet(ResultSet result) throws Exception {
		IGuest guest = new Guest();
		guest.setId(result.getInt("id"));
		guest.setName(result.getString("name"));
		guest.setLastName(result.getString("last_name"));
		return guest;
	}

	@Override
	protected void parseStatementForCreate(PreparedStatement statement, IGuest entity) throws Exception {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getLastName());

	}

	@Override
	protected void parseStetementForUpdate(PreparedStatement statement, IGuest entity) throws Exception {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getLastName());
		statement.setInt(3, entity.getId());

	}

	@Override
	protected String getSelectQuery() {

		return "SELECT*FROM Guests";
	}

	@Override
	protected String getCreateQuery() {

		return "INSERT INTO GUESTS (name, last_name) VALUES (?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE GUESTS SET name=?, last_name=? WHERE id=?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM GUESTS WHERE id=?;";
	}

}
