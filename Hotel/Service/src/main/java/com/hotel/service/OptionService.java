package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
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
	public String importOptions(String pathToCsv) throws Exception { // use SaveOrUpdate method
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String path = pathToCsv + Analyzer.getNameOfBeen("Option");
			List<Option> options = getOption();
			List<Option> optionsImport = new ArrayList<>();
			CsvWorker.Reader reader = new CsvWorker.Reader(path);
			reader.read();
			for (int i = 0; i < reader.read().size(); i++) {
				optionsImport.add(new Option(reader.read().get(i)));
			}

			for (int i = 0; i < options.size(); i++) {
				optionDao.updute(session, optionsImport.get(i));
			}
			for (int i = options.size(); i < reader.read().size(); i++) {
				optionDao.create(session, optionsImport.get(i));
			}
			transaction.commit();
			;
			return "data was imported from" + path;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	public List<Option> getOption() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			List<Option> list = optionDao.getAll(session, "id", Option.class);
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	public void addOption(Option option) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			optionDao.create(session, option);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	public Option getById(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Option option = (Option) optionDao.getById(session, id, Option.class);
			transaction.commit();
			return option;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}finally {
			if(session!=null) {
				session.close();
			}
		}

	}

}
