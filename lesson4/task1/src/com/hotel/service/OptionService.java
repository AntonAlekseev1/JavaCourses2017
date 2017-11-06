package com.hotel.service;

import com.hotel.been.Option;
import com.hotel.repository.OptionRepository;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.FileWorker;

public class OptionService {

	private OptionRepository optionRepository;
	private String path = "D:\\1\\options.txt";

	public OptionService() {

		optionRepository = new OptionRepository(ArrayWorker.MIN_SIZE);
	}

	public OptionRepository getOptions() {
		return optionRepository;
	}

	public Option[] getOption() {
		return optionRepository.getOption();
	}

	public void addOption(Option option) {
		optionRepository.addOption(option);
	}

	public void writeInFile() {
		optionRepository.writeInFile();
	}

	public String[] readFromFile() {
		return FileWorker.readFrom(path);
	}

}
