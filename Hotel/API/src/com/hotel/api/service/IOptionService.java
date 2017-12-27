package com.hotel.api.service;

import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.repository.IOptionRepository;

public interface IOptionService {
	
	public IOptionRepository getOptions();
	
	public List<IOption> getOption();
	
	public void addOption(IOption option);

}
