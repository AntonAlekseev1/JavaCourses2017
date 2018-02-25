package com.hotel.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.hotel.Analyzer;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.IConnectorDao;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.api.dao.IHistoryDAO;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.api.service.IGuestService;
import com.hotel.been.Guest;
import com.hotel.di.DependecyInjector;
import com.hotel.utils.CsvWorker;

public class GuestService implements IGuestService {

	private static GuestService instance;
	private IGuestDAO guestDao = (IGuestDAO) DependecyInjector.inject(IGuestDAO.class);
	private static IOptionDAO optionDao = (IOptionDAO) DependecyInjector.inject(IOptionDAO.class);
	private IHistoryDAO historyDao = (IHistoryDAO) DependecyInjector.inject(IHistoryDAO.class);
	private IConnectorDao connect = (IConnectorDao) DependecyInjector.inject(IConnectorDao.class);

	private GuestService(IOptionDAO optionDao) {
		GuestService.optionDao = optionDao;

	}

	public static GuestService getInstance() {
		if (instance == null) {
			instance = new GuestService(optionDao);
		}
		return instance;
	}

	@Override
	public String exportGuests(String pathToCsv) throws Exception {
		String path = pathToCsv + Analyzer.getNameOfBeen("Guest");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<IGuest> guests = getGuests();
		writer.comment("id;name;lastName");
		for (int i = 0; i < guests.size(); i++) {
			writer.write(guests.get(i));
		}
		return "data was exported along the way" + path;
	}

	@Override
	public String importGuest(String pathToCsv) throws Exception {

		Connection connection = connect.getConection();
		try {
			connection.setAutoCommit(false);
			String path = pathToCsv + Analyzer.getNameOfBeen("Guest");
			List<IGuest> guests = getGuests();
			List<IGuest> guestsImport = new ArrayList<>();
			IGuestDAO guestDao = getGuest();
			CsvWorker.Reader reader = new CsvWorker.Reader(path);
			reader.read();
			for (int i = 0; i < reader.read().size(); i++) {
				guestsImport.add(new Guest(reader.read().get(i)));
			}

			for (int i = 0; i < guests.size(); i++) {
				guestDao.updute(connection, guestsImport.get(i));
				;
			}
			for (int i = guests.size(); i < reader.read().size(); i++) {
				guestDao.create(connection, guestsImport.get(i));
			}
			connection.commit();
			return "data was imported from" + path;
		} catch (Exception e) {
			connection.rollback();
			throw new Exception(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public List<IGuest> sortGuests(String name) throws Exception {
		return guestDao.getAll(connect.getConection(), name);
	}

	public IGuest getGuestById(Integer id) throws Exception {
		return guestDao.getById(connect.getConection(), id);
	}

	public void addGuest(IGuest guest) throws Exception {
		guestDao.create(connect.getConection(), guest);

	}

	public List<IGuest> getGuests() throws Exception {
		return guestDao.getAll(connect.getConection(), "id");
	}

	public Integer getNumberOfGuests() throws Exception {

		return guestDao.getAll(connect.getConection(), "id").size();
	}

	public IGuestDAO getGuest() {
		return guestDao;
	}

	public void addOptionToGuest(Integer optionId, Integer guestId) throws Exception {
		historyDao.addOptionToGoest(connect.getConection(), guestId, optionId);
	}

	public List<IOption> getGuestOptions(Integer id) throws Exception {

		return guestDao.getGuestOptions(connect.getConection(), id);
	}

	public void removeGuest(Integer id) throws Exception {
		guestDao.delete(connect.getConection(), id);
	}

}