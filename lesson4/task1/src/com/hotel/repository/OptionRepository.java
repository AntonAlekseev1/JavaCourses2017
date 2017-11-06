package com.hotel.repository;

import java.util.Arrays;

import com.danco.training.TextFileWorker;
import com.hotel.been.Entity;
import com.hotel.been.Option;
import com.hotel.utils.ArrayWorker;
import com.hotel.utils.IdGenerator;

public class OptionRepository {
	private Option[] options;
	private final TextFileWorker textFileWorker = new TextFileWorker("D:\\1\\options.txt");

	public OptionRepository(Integer size) {
		options = new Option[size];
	}

	public Option[] getOption() {
		return options;
	}

	public void setOption(Option[] option) {
		this.options = option;
	}

	public void addOption(Option option) {
		if (options[options.length - 1] != null) {

			options = castEntitiesArray(ArrayWorker.resize(options));
		}
		for (int i = 0; i < options.length; i++) {
			if (options[i] == null) {
				options[i] = option;
				options[i].setId(IdGenerator.generateOptionId());
				break;
			}
		}
	}

	public Option getOptionById(Integer id) {
		Option option = null;
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null) {
				if (options[i].getId().equals(id)) {
					option = options[i];
					break;
				}
			}
		}
		return option;
	}

	public void removeOption(Option option) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == option) {
				options[i] = null;
				break;
			}
		}
	}

	private Option[] castEntitiesArray(Entity[] entities) {
		Option[] optionArray = Arrays.copyOf(entities, entities.length, Option[].class);
		return optionArray;
	}

	public void writeInFile() {
		String[] array = Arrays.copyOf(ArrayWorker.toString(options), ArrayWorker.getCount(options));
		textFileWorker.writeToFile(array);
	}

}
