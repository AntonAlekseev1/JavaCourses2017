package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.Analyzer;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.api.service.IOptionService;
import com.hotel.been.Option;
import com.hotel.dao.OptionDAO;
import com.hotel.dao.connection.HibernateUtil;
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
		List<IOption> options = getOption();
		writer.comment("id;name;price");
		for (int i = 0; i < options.size(); i++) {
			writer.write(options.get(i));
		}
		return "data was exported along the way" + path;

	}

	@Override
	public String importOptions(String pathToCsv) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String path = pathToCsv + Analyzer.getNameOfBeen("Option");
			List<IOption> options = getOption();
			List<IOption> optionsImport = new ArrayList<>();
			IOptionDAO optionDao = OptionService.getInstance().getOptions();
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
			transaction.commit();;
			return "data was imported from" + path;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		}
	}

	public IOptionDAO getOptions() {
		return optionDao;
	}

	public List<IOption> getOption() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return optionDao.getAll(session, "id", IOption.class);
	}

	public void addOption(IOption option) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		optionDao.create(session, option);
		transaction.commit();
	}
	
	public Option getById(Integer id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Option option = (Option) optionDao.getById(session, id, IOption.class);
		return option;
		
	}

}
