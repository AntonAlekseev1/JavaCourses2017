package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.utils.IdGenerator;

public class OptionRepository implements IOptionRepository {
	private List<IOption> options;

	public OptionRepository() {
		options = new ArrayList<>();
	}

	public List<IOption> getOption() {
		return options;
	}

	public void addOption(IOption option) {
		
				options.add(option);
				option.setId(IdGenerator.generateOptionId());
	}

	public IOption getOptionById(Integer id) {
		IOption option = null;
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getId().equals(id)) {
					option = options.get(i);
					break;
				}
			}
		return option;
	}

	public void removeOption(IOption option) {
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i) == option) {
				option=options.get(i);
				option=null;
				break;
			}
		}
	}

}
