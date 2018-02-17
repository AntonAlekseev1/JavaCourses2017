package com.hotel.service;

import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.api.been.IOption;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.api.dao.IHistoryDAO;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.api.service.IGuestService;
import com.hotel.di.DependecyInjector;
import com.hotel.utils.Connector;

public class GuestService implements IGuestService {

	private static GuestService instance;
	private IGuestDAO guestDao = (IGuestDAO) DependecyInjector.inject(IGuestDAO.class);
	private static IOptionDAO optionDao = (IOptionDAO) DependecyInjector.inject(IOptionDAO.class);
	private IHistoryDAO historyDao = (IHistoryDAO) DependecyInjector.inject(IHistoryDAO.class);
	private Connector connect = Connector.getinstance();

	private GuestService(IOptionDAO optionDao) {
		GuestService.optionDao = optionDao;

	}
	
	public static GuestService getInstance() {
		if(instance==null) {
			instance = new GuestService(optionDao);
		}
		return instance;
	}

	
	public List<IGuest> sortGuests(String name) throws Exception {
		return guestDao.sort(connect.getConection(), name);
	}

	public IGuest getGuestById(Integer id) throws Exception {
		return guestDao.getById(connect.getConection(),id);
	}

	public void addGuest(IGuest guest) throws Exception {
		guestDao.create(connect.getConection(),guest);

	}

	public List<IGuest> getGuests() throws Exception {
		return guestDao.getAll(connect.getConection());
	}

	public Integer getNumberOfGuests() throws Exception {
		
		return guestDao.getAll(connect.getConection()).size();
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
		guestDao.delete(connect.getConection(),id);
	}

}