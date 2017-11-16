package com.hotel.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.been.Option;
import com.hotel.utils.FileWorker;
import com.hotel.utils.IdGenerator;

public class OptionRepository implements IOptionRepository {
	private List<IOption> optionRepository;

	public OptionRepository() {
		optionRepository = new ArrayList<>();
	}

	public List<IOption> getOption() {
		return optionRepository;
	}

	public void addOption(IOption option) {
		
				optionRepository.add(option);
				option.setId(IdGenerator.generateOptionId());
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

	public void removeOption(IOption option) {
		for (int i = 0; i < optionRepository.size(); i++) {
			if (optionRepository.get(i) == option) {
				option=optionRepository.get(i);
				option=null;
				break;
			}
		}
	}
	
	public void readFromFile(String path) {
		for(String line: FileWorker.readFrom(path)) {
			optionRepository.add(new Option(line));
		}
	}

}
