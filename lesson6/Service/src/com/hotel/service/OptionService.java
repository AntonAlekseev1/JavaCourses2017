package com.hotel.service;

import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.service.IOptionService;
import com.hotel.repository.OptionRepository;

public class OptionService implements IOptionService {

	private IOptionRepository optionRepository = OptionRepository.getInstance();

	public OptionService() {

		OptionRepository.getInstance();
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
