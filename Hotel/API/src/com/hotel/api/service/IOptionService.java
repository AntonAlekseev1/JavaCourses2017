package com.hotel.api.service;

import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.dao.IOptionDAO;

public interface IOptionService {
<<<<<<< HEAD
	
	public IOptionRepository getOptions();
	
	public List<IOption> getOption();
	
	public void addOption(IOption option);
	
	public void writeInFile();
	
	public void readFromFile();
=======

	public IOptionDAO getOptions();

	public List<IOption> getOption() throws Exception;

	public void addOption(IOption option) throws Exception;

	String importOptions(String path) throws Exception;

	String exportOptions(String path) throws Exception;
>>>>>>> lesson11

}
