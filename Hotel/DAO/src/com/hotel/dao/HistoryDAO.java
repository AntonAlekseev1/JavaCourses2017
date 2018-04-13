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
import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IHistoryDAO;
import com.hotel.been.History;
import com.hotel.been.Room;

public class HistoryDAO extends AbstractDao<IHistory> implements IHistoryDAO {

	private static final Logger logger = Logger.getLogger(HistoryDAO.class);
	private static HistoryDAO instance;

	private HistoryDAO() {

	}

	public static HistoryDAO getInstance() {

		if (instance == null) {
			instance = new HistoryDAO();
		}
		return instance;
	}

	public String parseDate(java.util.Date date) {

		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		return strDate;

	}

	@Override
	public Double getTotalPayment(Connection connect, Integer id) throws Exception {
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
		} catch (Exception e) {
			logger.error("Can't count total payment : ", e);
			throw new Exception("Can't count total payment: " + e.getMessage());
		}
		return sum;
	}

	@Override
	public void addOptionToGoest(Connection connection, Integer idGuest, Integer idOption) throws Exception {
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
			throw new Exception("Can't add option : " + e.getMessage());
		}
	}

	@Override
	public List<IRoom> getFreeRoomsOnDate(Connection connection, String date) throws Exception {
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

		} catch (Exception e) {
			logger.error("It is not possible to find available rooms for this date: ", e);
			throw new Exception("It is not possible to find available rooms for this date: " + e.getMessage());

		}
		return list;

	}

	@Override
	protected IHistory parseResultSet(ResultSet result) throws Exception {
		IHistory history = new History();
		try {
			history.setId(result.getInt("id"));
			history.setGuest(result.getInt("id_guest"));
			history.setRoom(result.getInt("id_room"));
			history.setDateOfArival(result.getDate("date_of_arival"));
			history.setEvictDate(result.getDate("evict_date"));
		} catch (Exception e) {
			logger.error("parse ResultSet: ", e);
			throw new Exception("parse ResultSet: : " + e.getMessage());
		}
		return history;
	}

	@Override
	protected void parseStatementForCreate(PreparedStatement statement, IHistory entity) throws Exception {
		try {
			statement.setInt(1, entity.getRoomId());
			statement.setInt(2, entity.getGuestId());
			statement.setDate(3, java.sql.Date.valueOf(parseDate(entity.getDateOfArrival())));
			statement.setDate(4, java.sql.Date.valueOf(parseDate(entity.getEvictDate())));
		} catch (Exception e) {
			logger.error("parse Statement for create: ", e);
			throw new Exception("parse Statement for create: : " + e.getMessage());
		}
	}

	@Override
	protected void parseStetementForUpdate(PreparedStatement statement, IHistory entity) throws Exception {
		try {
			statement.setInt(1, entity.getRoomId());
			statement.setInt(2, entity.getGuestId());
			statement.setDate(3, (Date) entity.getDateOfArrival());
			statement.setDate(4, (Date) entity.getEvictDate());
		} catch (Exception e) {
			logger.error("parse Statement for update: ", e);
			throw new Exception("parse Statement for update: : " + e.getMessage());
		}
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT*FROM HISTORY";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO HISTORY (id_room, id_guest, date_of_arival, evict_date) VALUES (?,?,?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE HISTORY SET id_room=?, id_guest=?, date_of_arival=?, evict_date=? WHERE id=?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM HISTORY WHERE id=?;";
	}

}
