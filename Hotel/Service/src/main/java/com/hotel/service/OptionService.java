package com.hotel.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hotel.Analyzer;
import com.hotel.api.service.IOptionService;
import com.hotel.been.Option;
import com.hotel.dao.OptionDAO;
import com.hotel.dao.hibernateutil.HibernateUtil;
import com.hotel.utils.CsvWorker;

public class OptionService implements IOptionService {

	private static OptionService instance;
	private OptionDAO optionDao = OptionDAO.getInstance();
	private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

	private OptionService() {

	}

	public static OptionService getInstance() {
		if (instance == null) {
			instance = new OptionService();
		}
		return instance;
	}

	@Override
	public String exportOptions(String pathToCsv) throws Exception {
		String path = pathToCsv + Analyzer.getNameOfBeen("Option");
		CsvWorker.Writer writer = new CsvWorker.Writer(path);
		List<Option> options = getOption();
		writer.comment("id;name;price");
		for (int i = 0; i < options.size(); i++) {
			writer.write(options.get(i));
		}
		return "data was exported along the way" + path;

	}

	@Override
	public String importOptions(String pathToCsv) throws Exception { 
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String path = pathToCsv + Analyzer.getNameOfBeen("Option");
			CsvWorker.Reader reader = new CsvWorker.Reader(path);
			reader.read();
			for (int i = 0; i < reader.read().size(); i++) {
				Option option = new Option(reader.read().get(i));
				optionDao.saveOrUpdate(session, option);
			}
			transaction.commit();
			return "data was imported from" + path;
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				}
			throw new Exception(e);
		}
	}

	public List<Option> getOption() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<Option> list = optionDao.getAll(session, "id");
			transaction.commit();
			return list;
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				}
			throw new Exception(e);
		}
	}

	public void addOption(Option option) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			optionDao.create(session, option);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				}
			throw new Exception(e);
		}
	}

	public Option getById(Integer id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Option option = (Option) optionDao.getById(session, id);
			transaction.commit();
			return option;
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				}
			throw new Exception(e);
		}
	}

}
