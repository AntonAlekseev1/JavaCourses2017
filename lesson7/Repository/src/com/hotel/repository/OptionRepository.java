package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.repository.ARepository;
import com.hotel.api.repository.IOptionRepository;

public class OptionRepository extends ARepository implements IOptionRepository {
	private List<IOption> optionRepository;
	private static OptionRepository instance;

	private OptionRepository() {
		optionRepository = new ArrayList<>();
	}

	public static OptionRepository getInstance() {
		if (instance == null) {
			instance = new OptionRepository();
		}
		return instance;
	}

	public List<IOption> getOption() {
		return optionRepository;
	}

	public void setOptions(List<IOption> options) {
		optionRepository=options;	
	}

	public void addOption(IOption option) {
		option.setId(generateId());
		optionRepository.add(option);
	}

	public IOption getOptionById(Integer id) {
		IOption option = null;
		for (int i = 0; i < optionRepository.size(); i++) {
			if (optionRepository.get(i).getId().equals(id)) {
				option = optionRepository.get(i);
				break;
			}
		}
		return option;
	}

	public Integer generateId() {
		Integer id = 0;
		for (int i = 0; i < optionRepository.size(); i++) {
			if (optionRepository.get(i).getId() > id) {
				id = optionRepository.get(i).getId();
			}
		}
		return (id + 1);
	}

	public void removeOption(IOption option) {
		for (int i = 0; i < optionRepository.size(); i++) {
			if (optionRepository.get(i) == option) {
				option = optionRepository.get(i);
				option = null;
				break;
			}
		}
	}

}
