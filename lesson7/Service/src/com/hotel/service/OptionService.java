package com.hotel.service;

import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.service.IOptionService;
import com.hotel.di.DependecyInjector;

public class OptionService implements IOptionService {

	private static OptionService instance;
	private IOptionRepository optionRepository = (IOptionRepository) DependecyInjector.getRepository(IOptionRepository.class);

	private OptionService() {
		
	}
	
	public static OptionService getInstance() {
		if(instance==null) {
			instance=new OptionService();
		}
		return instance;
	}

	public IOptionRepository getOptions() {
		return optionRepository;
	}

	public List<IOption> getOption() {
		return optionRepository.getOption();
	}

	public void addOption(IOption option) {
		optionRepository.addOption(option);
	}

}
