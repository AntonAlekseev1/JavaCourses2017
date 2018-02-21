package com.hotel.api.service;

import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.dao.IOptionDAO;

public interface IOptionService {

	public IOptionDAO getOptions();

	public List<IOption> getOption() throws Exception;

	public void addOption(IOption option) throws Exception;

	String importOptions(String path) throws Exception;

	String exportOptions(String path) throws Exception;

}
