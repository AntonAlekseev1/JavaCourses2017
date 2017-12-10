package com.hotel.ui.actions.option;

import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ImportOptions implements IAction {

	public ImportOptions() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {

		Hotel.getInstance().importOptions();
		Printer.println("Import was successful");
	}

}
