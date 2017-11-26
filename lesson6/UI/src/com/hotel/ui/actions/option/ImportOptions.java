package com.hotel.ui.actions.option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.been.Option;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.CsvWorker;

public class ImportOptions implements IAction{
	
	private static final String PATH_TO_OPTIONS_CSV = String.valueOf(Configuration.getProperties("PATH_TO_OPTIONS_CSV"));

	public ImportOptions() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {
		List<IOption> options = Hotel.getInstance().getAllOptions();
		List<IOption> optionsImport = new ArrayList<>();
		CsvWorker.Reader reader = new CsvWorker.Reader(PATH_TO_OPTIONS_CSV);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			optionsImport.add(new Option(reader.read().get(i)));
		}

		for (int i = 0; i < options.size(); i++) {
			Collections.replaceAll(options, options.get(i), optionsImport.get(i));
		}
		for (int i = options.size(); i < reader.read().size(); i++) {
			options.add(optionsImport.get(i));
		}
		
	}

}
