package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.been.Guest;
import com.hotel.been.Room;

public class RoomDatabaseDAO implements IRoomDAO {

	private static final Logger logger = Logger.getLogger(RoomDatabaseDAO.class);
	private static RoomDatabaseDAO instance;

	private RoomDatabaseDAO() {

	}

	public static RoomDatabaseDAO getInstance() {

		if (instance == null) {
			instance = new RoomDatabaseDAO();
		}
		return instance;
	}

	@Override
	public List<IRoom> getAll(Connection connection) throws Exception {
		String sql = "SELECT*FROM ROOMS";
		List<IRoom> list = new ArrayList<>();
		try {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					IRoom room = new Room();
					room.setId(result.getInt("id"));
					room.setNumber(result.getInt("number"));
					room.setCopacity(result.getInt("copacity"));
					room.setNumberOfStars(result.getInt("stars"));
					room.setPrice(result.getDouble("price"));
					room.setIsFree(result.getBoolean("is_free"));
					room.setStatus(RoomStatus.valueOf(result.getString("status")));
					list.add(room);
				}
			}

		} catch (SQLException e) {
			logger.error("Can't get all guests: ", e);
			throw new Exception();
		} finally {
			try {
				connection.close();
				logger.info("(getAll)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}
		return list;
	}

	@Override
	public IRoom getById(Connection connection, Integer id) {
		String sql = "SELECT*FROM ROOMS WHERE id=?;";
		IRoom room = new Room();
		try {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				result.next();

				room.setId(result.getInt("id"));
				room.setNumber(result.getInt("number"));
				room.setCopacity(result.getInt("copacity"));
				room.setNumberOfStars(result.getInt("stars"));
				room.setPrice(result.getDouble("price"));
				room.setIsFree(result.getBoolean("is_free"));
				room.setStatus(RoomStatus.valueOf(result.getString("status")));

			}
		} catch (SQLException e) {
			logger.error("Can't find room by id", e);
		} finally {
			try {
				connection.close();
				logger.info("(getById)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

		return room;
	}

	@Override
	public void create(Connection connection, IRoom entity) throws Exception {
		String sql = "INSERT INTO ROOMS (number, copacity, stars, price,status, is_free) VALUES (?,?,?,?,?,?);";
		try {
			connection.setAutoCommit(false);
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, entity.getNumber());
				statement.setInt(2, entity.getCopacity());
				statement.setInt(3, entity.getNumberOfStars());
				statement.setDouble(4, entity.getPrice());
				statement.setString(5, entity.getStatus().toString());
				statement.setBoolean(6, entity.getIsFree());
				statement.executeUpdate();
			}
			connection.commit();
		} catch (Exception e) {
			logger.error("Can't create room: ", e);
			throw new Exception();
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
				logger.info("(create)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

	}

	@Override
	public void updute(Connection connection, IRoom entity) {
		String sql = "UPDATE ROOMS SET number=?, copacity=?, stars=?, price=?,status=?, is_free=? WHERE id=?;";
		try {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.setInt(1, entity.getNumber());
				statement.setInt(2, entity.getCopacity());
				statement.setInt(3, entity.getNumberOfStars());
				statement.setDouble(4, entity.getPrice());
				statement.setString(5, entity.getStatus().toString());
				statement.setBoolean(6, entity.getIsFree());
				statement.setInt(7, entity.getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't update room: ", e);
		} finally {
			try {
				connection.close();
				logger.info("(update)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}
	}

	@Override
	public void delete(Connection connection, Integer id) {
		String sql = "DELETE FROM ROOMS WHERE id=?;";
		try {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.setInt(1, id);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't delete room: ", e);
		} finally {
			try {
				connection.close();
				logger.info("(delete)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

	}

	@Override
	public List<IRoom> sort(Connection connection, String name) {
		String sql = "SELECT*FROM ROOMS ORDER BY " + name;
		List<IRoom> list = new ArrayList<>();
		try {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					IRoom room = new Room();
					room.setId(result.getInt("id"));
					room.setNumber(result.getInt("number"));
					room.setCopacity(result.getInt("copacity"));
					room.setNumberOfStars(result.getInt("stars"));
					room.setPrice(result.getDouble("price"));
					room.setIsFree(result.getBoolean("is_free"));
					room.setStatus(RoomStatus.valueOf(result.getString("status")));
					list.add(room);
				}
			}

		} catch (SQLException e) {
			logger.error("Can't get all guests: ", e);
		} finally {
			try {
				connection.close();
				logger.info("(getAll)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}
		return list;
	}

	@Override
	public List<IGuest> getLastGuests(Connection connect, Integer id, Integer number) throws Exception {
		String sql = "SELECT guests.id, name, last_name "
				+ "FROM  History join Guests on guests.id=History.id WHERE History.id_room = " +id+" limit "
				+number;
		List<IGuest> list = new ArrayList<>();
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					IGuest guest = new Guest();
					guest.setId(result.getInt("id"));
					guest.setName(result.getString("name"));
					guest.setLastName(result.getString("last_name"));
					list.add(guest);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e.getMessage());
		} finally {
			try {
				connect.close();
				logger.info("(getLastGuests)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}
		return list;
	}

}
