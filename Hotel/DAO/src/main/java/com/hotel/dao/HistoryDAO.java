package com.hotel.dao;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.hotel.api.been.IHistory;
import com.hotel.api.been.IRoom;
import com.hotel.api.dao.AbstractDao;
import com.hotel.api.dao.IHistoryDAO;

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
	public Double getTotalPayment(Session session, Integer id) throws Exception {
		Double sum = null;
		
		try {
			
		} catch (Exception e) {
			logger.error("Can't count total payment : ", e);
			throw new Exception("Can't count total payment: " + e.getMessage());
		}
		return sum;
	}

	@Override
	public void addOptionToGoest(Session session, Integer idGuest, Integer idOption) throws Exception {
		
		try {
			
		} catch (Exception e) {
			logger.error("Can't add Option: ", e);
			throw new Exception("Can't add option : " + e.getMessage());
		}
	}

	@Override
	public List<IRoom> getFreeRoomsOnDate(Session session, String date) throws Exception {
		List<IRoom> list = new ArrayList<>();
		try {
		} catch (Exception e) {
			logger.error("It is not possible to find available rooms for this date: ", e);
			throw new Exception("It is not possible to find available rooms for this date: " + e.getMessage());

		}
		return list;

	}

}
