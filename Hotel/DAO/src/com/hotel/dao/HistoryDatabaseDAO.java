package com.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.dao.IHistoryDAO;
import com.hotel.been.History;
import com.hotel.been.Room;

public class HistoryDatabaseDAO implements IHistoryDAO {

	private static final Logger logger = Logger.getLogger(HistoryDatabaseDAO.class);
	private static HistoryDatabaseDAO instance;

	private HistoryDatabaseDAO() {

	}

	public static HistoryDatabaseDAO getInstance() {

		if (instance == null) {
			instance = new HistoryDatabaseDAO();
		}
		return instance;
	}

	@Override
	public List<IHistory> getAll(Connection connect) {
		String sql = "SELECT*FROM HISTORY";
		List<IHistory> list = new ArrayList<>();
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					IHistory history = new History();
					history.setId(result.getInt("id"));
					history.setGuest(result.getInt("id_guest"));
					history.setRoom(result.getInt("id_room"));
					history.setDateOfArival(result.getDate("date_of_arival"));
					history.setEvictDate(result.getDate("evict_date"));

					list.add(history);
				}
			}

		} catch (SQLException e) {
			logger.error("Can't get all histories: ", e);
		} finally {
			try {
				connect.close();
				logger.info("(getAll)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}
		return list;
	}

	@Override
	public IHistory getById(Connection connect, Integer id) {
		String sql = "SELECT*FROM HISTORY WHERE id=?;";
		IHistory history = new History();
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				result.next();

				history.setId(result.getInt("id"));
				history.setGuest(result.getInt("id_guest"));
				history.setRoom(result.getInt("id_room"));
				history.setDateOfArival(result.getDate("date_of_arival"));
				history.setEvictDate(result.getDate("evict_date"));

			}
		} catch (SQLException e) {
			logger.error("Can't find history by id", e);
		} finally {
			try {
				connect.close();
				logger.info("(getById)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

		return history;
	}

	@Override
	public void create(Connection connect, IHistory entity) {
		String sql = "INSERT INTO HISTORY (id_room, id_guest, date_of_arival, evict_date) VALUES (?,?,?,?);";
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {
				statement.setInt(1, entity.getRoomId());
				statement.setInt(2, entity.getGuestId());
				statement.setDate(3, java.sql.Date.valueOf(parseDate(entity.getDateOfArrival())));
				statement.setDate(4, java.sql.Date.valueOf(parseDate(entity.getEvictDate())));
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't create history: ", e);
		} finally {
			try {
				connect.close();
				logger.info("(create)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

	}

	@Override
	public void updute(Connection connect, IHistory entity) {
		String sql = "UPDATE HISTORY SET id_room=?, id_guest=?, date_of_arival=?, evict_date=? WHERE id=?;";
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {

				statement.setInt(1, entity.getRoomId());
				statement.setInt(2, entity.getGuestId());
				statement.setDate(3, (Date) entity.getDateOfArrival());
				statement.setDate(4, (Date) entity.getEvictDate());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't update history: ", e);
		} finally {
			try {
				connect.close();
				logger.info("(update)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

	}

	@Override
	public void delete(Connection connect, Integer id) {
		String sql = "DELETE FROM HISTORY WHERE id=?;";
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {

				statement.setInt(1, id);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't delete history: ", e);
		} finally {
			try {
				connect.close();
				logger.info("(delete)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

	}

	public String parseDate(java.util.Date date) {

		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		return strDate;

	}

	@Override
	public Double getTotalPayment(Connection connect, Integer id) {
		Double sum = null;
		String sql = "SELECT price,date_of_arival, evict_date FROM HISTORY JOIN ROOMS ON HISTORY.id_room=ROOMS.id WHERE id_guest="
				+ id;
		try {
			try (PreparedStatement statement = connect.prepareStatement(sql)) {

				ResultSet result = statement.executeQuery();
				result.next();
				Double price = result.getDouble("price");
				Long time = (result.getDate("evict_date").getTime() - result.getDate("date_of_arival").getTime())
						/ 86400000;
				sum = price * time;
			}
		} catch (SQLException e) {
			logger.error("Can't count total payment : ", e);
		} finally {
			try {
				connect.close();
				logger.info("(payment)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}
		return sum;
	}

	@Override
	public void addOptionToGoest(Connection connection, Integer idGuest, Integer idOption) throws SQLException {
		String sql = "INSERT INTO OPTIONS_HISTORY (id_history, id_option) \r\n"
				+ "value ((SELECT HISTORY.id  FROM HISTORY WHERE id_guest = ?),?);";
		try {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.setInt(1, idGuest);
				statement.setInt(2, idOption);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't add Option: ", e);
		} finally {
			try {
				connection.close();
				logger.info("(addOptionToGoest)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ", e);
			}
		}

	}

	@Override
	public List<IRoom> getFreeRoomsOnDate(Connection connection, String date) {
		List<IRoom> list = new ArrayList<>();
		String sql = "SELECT Rooms.id,number, copacity, stars, price, is_free,status FROM Rooms JOIN History ON Rooms.id=History.id_room WHERE Rooms.id = History.id_room AND is_free = false  AND \"\r\n"
				+ date + "\" NOT BETWEEN date_of_arival and evict_date\r\n"
				+ "  UNION SELECT rooms.id,number, copacity, stars, price, is_free,status FROM Rooms WHERE is_free = true;";
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
			logger.error("Can't get all histories: ", e);
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

}
