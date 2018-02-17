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
import com.hotel.api.dao.IGuestDAO;
import com.hotel.been.Guest;
import com.hotel.been.Option;

public class GuestDatabaseDAO implements IGuestDAO{
	
	private static final Logger logger = Logger.getLogger(GuestDatabaseDAO.class);
	private static GuestDatabaseDAO instance;
	
	private GuestDatabaseDAO() {
		
	}
	
	public static GuestDatabaseDAO getInstance() {
		
		if(instance==null) {
			instance = new GuestDatabaseDAO();
		}
		return instance;
	}
	@Override
	public List<IGuest> getAll(Connection connect) {
		String sql = "SELECT*FROM Guests";
		List<IGuest> list = new ArrayList<>();
		try{
			try( PreparedStatement statement = connect.prepareStatement(sql)){
		ResultSet result = statement.executeQuery();
			while(result.next()) {
				IGuest guest = new Guest();
				guest.setId(result.getInt("id"));
				guest.setName(result.getString("name"));
				guest.setLastName(result.getString("last_name"));
				list.add(guest);
			}
			}
			
		}
		catch(SQLException e) {
			logger.error("Can't get all guests: ", e);
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
				logger.info("(getAll)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
		}
		return list;
	}

	@Override
	public IGuest getById(Connection connect,Integer id) {
		String sql = "SELECT*FROM Guests WHERE id=?;";
		IGuest guest =new Guest();
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				result.next();
					
				guest.setId(result.getInt("id"));
				guest.setName(result.getString("name"));
				guest.setLastName(result.getString("last_name"));
			}
		} catch (SQLException e) {
			logger.error("Can't find guest by id",e);
		}
		finally {
			try {
				connect.close();
				logger.info("(getById)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
			}
		
		return guest;
	}

	@Override
	public void create(Connection connect,IGuest entity) {
		String sql="INSERT INTO GUESTS (name, last_name) VALUES (?,?);";
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getLastName());
				statement.executeUpdate();
			}
			
		} catch (SQLException e) {
			logger.error("Can't create guest: ",e);
		}
		finally {
			try {
				connect.close();
				logger.info("(create)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
		}
	}

	@Override
	public void updute(Connection connect,IGuest entity) {
		String sql="UPDATE GUESTS SET name=?, last_name=? WHERE id=?;";
		try {
			connect.setAutoCommit(false);
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getLastName());
				statement.setInt(3, entity.getId());
				statement.executeUpdate();
			}
			connect.commit();
		} catch (SQLException e) {
			logger.error("Can't update guest: ",e);
		}
		finally {
			try {
				connect.setAutoCommit(true);
				connect.close();
				logger.info("(update)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
		}
		
	}

	@Override
	public void delete(Connection connect, Integer id) {
		String sql="DELETE FROM GUESTS WHERE id=?;";
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				
				statement.setInt(1, id);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't delete guest: ",e);
		}
		finally {
			try {
				connect.close();
				logger.info("(delete)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
		}
		
	}

	@Override
	public IGuest getByName(Connection connect, String name) {
		String sql = "SELECT*FROM Guests WHERE name=?;";
		IGuest guest =new Guest();
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				statement.setString(1, name);
				ResultSet result = statement.executeQuery();
				result.next();
					
				guest.setId(result.getInt("id"));
				guest.setName(result.getString("name"));
				guest.setLastName(result.getString("last_name"));
			}
		} catch (SQLException e) {
			logger.error("Can't find guest by name",e);
		}
		finally {
			try {
				connect.close();
				logger.info("(getByName)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
			}
		
		return guest;
	}
	@Override
	public List<IGuest> sort(Connection connect, String name) throws Exception {
		String sql = "SELECT*FROM GUESTS ORDER BY "+name;
		List<IGuest> list = new ArrayList<>();
		try{
			try( PreparedStatement statement = connect.prepareStatement(sql)){
		ResultSet result = statement.executeQuery();
			while(result.next()) {
				IGuest guest = new Guest();
				guest.setId(result.getInt("id"));
				guest.setName(result.getString("name"));
				guest.setLastName(result.getString("last_name"));
				list.add(guest);
			}
			}
			
		}
		catch(Exception e) {
			logger.error("Can't get all guests: ", e);
			throw new Exception(e);
		}
		finally {
			try {
				connect.close();
				logger.info("(getAll)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
		}
		return list;
	}
	@Override
	public List<IOption> getGuestOptions(Connection connection, Integer id) {
		String sql = "select options.id, options.name, options.price\r\n" + 
				"from options_history,options,history,guests\r\n" + 
				"where history.id_guest=guests.id and history.id=options_history.id_history and options_history.id_option= options.id and history.id_guest=?;";
		List<IOption> options =new ArrayList<>();
		try {
			try(PreparedStatement statement =connection.prepareStatement(sql) ){
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				while(result.next()) {
				IOption option = new Option();	
				option.setId(result.getInt("id"));
				option.setName(result.getString("name"));
				option.setPrice(result.getDouble("price"));
				options.add(option);
				}
			}
		} catch (SQLException e) {
			logger.error("Can't find history by id",e);
		}
		finally {
			try {
				connection.close();
				logger.info("(getById)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
			}
		
		return options;
	}

}
