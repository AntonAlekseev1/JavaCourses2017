package com.hotel.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.hotel.Analyzer;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.IConnectorDao;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.api.service.IOptionService;
import com.hotel.been.Option;
import com.hotel.di.DependecyInjector;
import com.hotel.utils.CsvWorker;

public class OptionService implements IOptionService {

	private static OptionService instance;
	private IOptionDAO optionDao = (IOptionDAO) DependecyInjector.inject(IOptionDAO.class);
	private IConnectorDao connect = (IConnectorDao) DependecyInjector.inject(IConnectorDao.class);

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
		Connection connection = connect.getConection();
		try {
			connection.setAutoCommit(false);
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
				optionDao.updute(connect.getConection(), optionsImport.get(i));
			}
			for (int i = options.size(); i < reader.read().size(); i++) {
				optionDao.create(connect.getConection(), optionsImport.get(i));
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

	public IOptionDAO getOptions() {
		return optionDao;
	}

	public List<IOption> getOption() throws Exception {
		return optionDao.getAll(connect.getConection(), "id");
	}

	public void addOption(IOption option) throws Exception {
		optionDao.create(connect.getConection(), option);
	}

}
