package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.been.Guest;
import com.hotel.been.Room;

public class RoomDAO extends AbstractDao<IRoom> implements IRoomDAO {

	private static final Logger logger = Logger.getLogger(RoomDAO.class);
	private static RoomDAO instance;

	private RoomDAO() {

	}

	public static RoomDAO getInstance() {

		if (instance == null) {
			instance = new RoomDAO();
		}
		return instance;
	}

	@Override
	public List<IGuest> getLastGuests(Connection connect, Integer id, Integer number) throws Exception {
		String sql = "SELECT guests.id, name, last_name "
				+ "FROM  History join Guests on guests.id=History.id WHERE History.id_room = " + id + " limit "
				+ number;
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
		}
		return list;
	}

	@Override
	protected IRoom parseResultSet(ResultSet result) throws Exception {
		IRoom room = new Room();
		room.setId(result.getInt("id"));
		room.setNumber(result.getInt("number"));
		room.setCopacity(result.getInt("copacity"));
		room.setNumberOfStars(result.getInt("stars"));
		room.setPrice(result.getDouble("price"));
		room.setIsFree(result.getBoolean("is_free"));
		room.setStatus(RoomStatus.valueOf(result.getString("status")));
		return room;
	}

	@Override
	protected void parseStatementForCreate(PreparedStatement statement, IRoom entity) throws Exception {
		statement.setInt(1, entity.getNumber());
		statement.setInt(2, entity.getCopacity());
		statement.setInt(3, entity.getNumberOfStars());
		statement.setDouble(4, entity.getPrice());
		statement.setString(5, entity.getStatus().toString());
		statement.setBoolean(6, entity.getIsFree());

	}

	@Override
	protected void parseStetementForUpdate(PreparedStatement statement, IRoom entity) throws Exception, Exception {
		statement.setInt(1, entity.getNumber());
		statement.setInt(2, entity.getCopacity());
		statement.setInt(3, entity.getNumberOfStars());
		statement.setDouble(4, entity.getPrice());
		statement.setString(5, entity.getStatus().toString());
		statement.setBoolean(6, entity.getIsFree());
		statement.setInt(7, entity.getId());

	}

	@Override
	protected String getSelectQuery() {
		return "SELECT*FROM ROOMS";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO ROOMS (number, copacity, stars, price,status, is_free) VALUES (?,?,?,?,?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE ROOMS SET number=?, copacity=?, stars=?, price=?,status=?, is_free=? WHERE id=?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM ROOMS WHERE id=?;";
	}

}
