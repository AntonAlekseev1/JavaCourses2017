package com.hotel.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.Analyzer;
import com.hotel.api.service.IGuestService;
import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Option;
import com.hotel.dao.GuestDao;
import com.hotel.dao.OptionDAO;
import com.hotel.dao.hibernateutil.HibernateUtil;
import com.hotel.utils.CsvWorker;

public class GuestService implements IGuestService {

	private static GuestService instance;
	private static OptionDAO optionDao = OptionDAO.getInstance();
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
		List<Guest> guests = getGuests();
		writer.comment("id;name;lastName");
		for (int i = 0; i < guests.size(); i++) {
			writer.write(guests.get(i));
		}
		return "data was exported along the way" + path;
	}

	@Override
	public String importGuest(String pathToCsv) throws Exception {// use SaveOrUpdate method

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String path = pathToCsv + Analyzer.getNameOfBeen("Guest");
			CsvWorker.Reader reader = new CsvWorker.Reader(path);
			reader.read();
			for (int i = 0; i < reader.read().size(); i++) {
				Guest guest = new Guest(reader.read().get(i));
				guestDao.saveOrUpdate(session, guest);
			}
			transaction.commit();
			return "data was imported from" + path;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Guest> sortGuests(String name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Guest> list = guestDao.getAll(session, name, Guest.class);
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Guest getGuestById(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Guest guest = guestDao.getById(session, id, Guest.class);
			transaction.commit();
			return guest;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void addGuest(Guest guest) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			guestDao.create(session, guest);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public List<Guest> getGuests() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Guest> list = guestDao.getAll(session, "id", Guest.class);
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Integer getNumberOfGuests() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Integer num = guestDao.getAll(session, "id", Guest.class).size();
			transaction.commit();
			return num;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void addOptionToGuest(Integer optionId, Integer guestId) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Guest guest = guestDao.getById(session, guestId, Guest.class);
			Option option = optionDao.getById(session, optionId, Option.class);
			if (guest.getHistory() != null) {
				List<History> history = guest.getHistory();
				for (int i = 0; i < history.size(); i++) {
					if (history.get(i).getGuest().equals(guest)) {
						guest.getHistory().get(i).getOptions().add(option);
					}

				}
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Option> getGuestOptions(Integer id) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Option> list = guestDao.getGuestOptions(session, id);
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void removeGuest(Integer id) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Guest guest = guestDao.getById(session, id, Guest.class);
			guestDao.delete(session, guest);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}