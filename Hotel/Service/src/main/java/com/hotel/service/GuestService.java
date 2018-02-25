package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.Analyzer;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.api.service.IGuestService;
import com.hotel.been.Guest;
import com.hotel.dao.GuestDao;
import com.hotel.dao.HistoryDAO;
import com.hotel.dao.OptionDAO;
import com.hotel.dao.connection.HibernateUtil;
import com.hotel.utils.CsvWorker;

public class GuestService implements IGuestService {

	private static GuestService instance;
	private static OptionDAO optionDao =  OptionDAO.getInstance();
	private HistoryDAO historyDao = HistoryDAO.getInstance();
	private GuestDao guestDao = new GuestDao();

	private GuestService(OptionDAO optionDao) {
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

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
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
				guestDao.updute(session, guestsImport.get(i));
			}
			for (int i = guests.size(); i < reader.read().size(); i++) {
				guestDao.create(session, guestsImport.get(i));
			}
			transaction.commit();
			return "data was imported from" + path;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
	}
	}

	public List<IGuest> sortGuests(String name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<IGuest> list = guestDao.getAll(session, name,IGuest.class);
		return list;
	}

	public IGuest getGuestById(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		IGuest guest = guestDao.getById(session, id, IGuest.class);
		return guest;
	}

	public void addGuest(IGuest guest) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		guestDao.create(session, guest);
		transaction.commit();

	}

	public List<IGuest> getGuests() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<IGuest> list = guestDao.getAll(session, "id", IGuest.class);
		return list;
	}

	public Integer getNumberOfGuests() throws Exception {

		return getGuests().size();
	}

	public IGuestDAO getGuest() {
		return guestDao;
	}

	public void addOptionToGuest(Integer optionId, Integer guestId) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		historyDao.addOptionToGoest(session, guestId, optionId);
		transaction.commit();
	}

	public List<IOption> getGuestOptions(Integer id) throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		return guestDao.getGuestOptions(session, id);
	}

	public void removeGuest(Integer id) throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		IGuest guest = getGuestById(id);
		guestDao.delete(session, guest);
		transaction.commit();
	}

}