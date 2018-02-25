package com.hotel.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.been.IEntity;

public abstract class AbstractDaoOld<T extends IEntity> implements IGenericDAOold<T> {

	private static final Logger logger = Logger.getLogger(AbstractDaoOld.class);

	protected abstract T parseResultSet(ResultSet result) throws Exception;

	protected abstract void parseStatementForCreate(PreparedStatement statement, T entity) throws Exception;

	protected abstract void parseStetementForUpdate(PreparedStatement statement, T entity) throws Exception, Exception;

	protected abstract String getSelectQuery();

	protected abstract String getCreateQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	public List<T> getAll(Connection connection, String name) throws Exception {

		List<T> entityList = new ArrayList<>();
		String sql = getSelectQuery() + " GROUP BY " + name;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				entityList.add(parseResultSet(result));
			}
		} catch (SQLException e) {
			logger.error("Exception in the method getAll: ", e);
			throw new SQLException("Exception in the method getAll");
		}
		return entityList;

	}

	@Override
	public T getById(Connection connection, Integer id) throws Exception {

		String sql = getSelectQuery() + " WHERE id = " + id.toString();
		T entity = null;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet result = statement.executeQuery();
			result.next();
			entity = parseResultSet(result);
		}

		return entity;

	}

	@Override
	public void create(Connection connection, T entity) throws Exception {

		String sql = getCreateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			parseStatementForCreate(statement, entity);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Can't create entity: ", e);
			throw new SQLException("Can't create entity: " + e.getMessage());
		}

	}

	@Override
	public void updute(Connection connection, T entity) throws Exception {
		String sql = getUpdateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			parseStetementForUpdate(statement, entity);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Can't update entity: ", e);
			throw new SQLException("Can't update entity: " + e.getMessage());
		}

	}

	@Override
	public void delete(Connection connection, Integer id) throws SQLException {
		String sql = getDeleteQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Can't delete entity: ", e);
			throw new SQLException("Can't delete entity: " + e.getMessage());
		}

	}

}
