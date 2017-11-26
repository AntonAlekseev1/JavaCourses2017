package com.hotel.ui.actions.option;

import java.util.List;

import com.hotel.api.been.IOption;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.CsvWorker;

public class ExportOptions implements IAction{
	
	private static final String PATH_TO_OPTIONS_CSV = String.valueOf(Configuration.getProperties("PATH_TO_OPTIONS_CSV"));

	public ExportOptions() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {
		CsvWorker.Writer writer = new CsvWorker.Writer(PATH_TO_OPTIONS_CSV);
		List<IOption> options = Hotel.getInstance().getAllOptions();
		writer.comment("id;name;price");
		for(int i=0;i<options.size();i++) {
			writer.write(options.get(i));
		}
		
	}

}
