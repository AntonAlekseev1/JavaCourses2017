package com.hotel.ui.actions.option;

import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ExportOptions implements IAction {

	public ExportOptions() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {

		Hotel.getInstance().exportOptions();
		Printer.println("Export was successful");
	}

}
