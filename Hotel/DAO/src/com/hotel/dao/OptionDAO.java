package com.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hotel.api.been.IOption;
import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.been.Option;

public class OptionDAO extends AbstractDao<IOption> implements IOptionDAO {

	private static OptionDAO instance;

	private OptionDAO() {

	}

	public static OptionDAO getInstance() {

		if (instance == null) {
			instance = new OptionDAO();
		}
		return instance;
	}

	@Override
	protected IOption parseResultSet(ResultSet result) throws Exception {
		IOption option = new Option();
		option.setId(result.getInt("id"));
		option.setName(result.getString("name"));
		option.setPrice(result.getDouble("price"));
		return option;
	}

	@Override
	protected void parseStatementForCreate(PreparedStatement statement, IOption entity) throws Exception {
		statement.setString(1, entity.getName());
		statement.setDouble(2, entity.getPrice());

	}

	@Override
	protected void parseStetementForUpdate(PreparedStatement statement, IOption entity) throws Exception {
		statement.setString(1, entity.getName());
		statement.setDouble(2, entity.getPrice());
		statement.setInt(3, entity.getId());

	}

	@Override
	protected String getSelectQuery() {
		return "SELECT*FROM OPTIONS";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO OPTIONS (name, price) VALUES (?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE OPTIONS SET name=?, price=? WHERE id=?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM OPTIONS WHERE id=?;";
	}

}
