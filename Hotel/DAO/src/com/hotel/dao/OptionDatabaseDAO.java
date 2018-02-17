package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.been.IOption;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.been.Option;

public class OptionDatabaseDAO implements IOptionDAO{
	
	private static final Logger logger = Logger.getLogger(OptionDatabaseDAO.class);
	private static OptionDatabaseDAO instance;
	
	private OptionDatabaseDAO() {
		
	}
	
	public static OptionDatabaseDAO getInstance() {
		
		if(instance==null) {
			instance=new OptionDatabaseDAO();
		}
		return instance;
	}

	@Override
	public List<IOption> getAll(Connection connection) {
		String sql = "SELECT*FROM OPTIONS";
		List<IOption> list = new ArrayList<>();
		try{
			try( PreparedStatement statement = connection.prepareStatement(sql)){
		ResultSet result = statement.executeQuery();
			while(result.next()) {
				IOption option = new Option();
				option.setId(result.getInt("id"));
				option.setName(result.getString("name"));
				option.setPrice(result.getDouble("price"));
				
				list.add(option);
			}
			}
			
		}
		catch(SQLException e) {
			logger.error("Can't get all options: ", e);
		}
		finally {
			try {
				connection.close();
				logger.info("(getAll)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
		}
		return list;
	}

	@Override
	public IOption getById(Connection connect, Integer id) {
		String sql = "SELECT*FROM OPTIONS WHERE id=?;";
		IOption option =new Option();
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				statement.setInt(1, id);
				ResultSet result = statement.executeQuery();
				result.next();
					
				option.setId(result.getInt("id"));
				option.setName(result.getString("name"));
				option.setPrice(result.getDouble("price"));
			}
		} catch (SQLException e) {
			logger.error("Can't find option by id",e);
		}
		finally {
			try {
				connect.close();
				logger.info("(getById)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
			}
		
		return option;
	}

	@Override
	public void create(Connection connect, IOption entity) {
		String sql="INSERT INTO OPTIONS (name, price) VALUES (?,?);";
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				statement.setString(1, entity.getName());
				statement.setDouble(2, entity.getPrice());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't create option: ",e);
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
	public void updute(Connection connect, IOption entity) {
		String sql="UPDATE OPTIONS SET name=?, price=? WHERE id=?;";
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				
				statement.setString(1, entity.getName());
				statement.setDouble(2, entity.getPrice());
				statement.setInt(3, entity.getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't update option: ",e);
		}
		finally {
			try {
				connect.close();
				logger.info("(update)connection closed");
			} catch (SQLException e) {
				logger.error("Can't close connection: ",e);
			}
		}
		
	}

	@Override
	public void delete(Connection connect, Integer id) {
		String sql="DELETE FROM OPTIONS WHERE id=?;";
		try {
			try(PreparedStatement statement =connect.prepareStatement(sql) ){
				
				statement.setInt(1, id);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Can't delete option: ",e);
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

	
}
