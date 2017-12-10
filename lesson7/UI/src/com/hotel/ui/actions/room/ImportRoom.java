package com.hotel.ui.actions.room;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ImportRoom implements IAction {

	@Override
	public void execute() {

		Hotel.getInstance().importRooms();
		Printer.println("Import was successful");
	}

}
