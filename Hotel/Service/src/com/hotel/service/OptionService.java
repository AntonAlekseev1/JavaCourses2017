package com.hotel.service;

import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.api.service.IOptionService;
import com.hotel.di.DependecyInjector;
import com.hotel.utils.Connector;

public class OptionService implements IOptionService {

	private static OptionService instance;
	private IOptionDAO optionDao = (IOptionDAO) DependecyInjector.inject(IOptionDAO.class);
	private Connector connect = Connector.getinstance();

	private OptionService() {
		
	}
	
	public static OptionService getInstance() {
		if(instance==null) {
			instance=new OptionService();
		}
		return instance;
	}

	public IOptionDAO getOptions() {
		return optionDao;
	}

	public List<IOption> getOption() throws Exception {
		return optionDao.getAll(connect.getConection());
	}

	public void addOption(IOption option) throws Exception {
		optionDao.create(connect.getConection(), option);
	}

}
