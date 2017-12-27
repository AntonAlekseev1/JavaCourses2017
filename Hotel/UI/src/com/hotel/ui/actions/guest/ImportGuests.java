package com.hotel.ui.actions.guest;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ImportGuests implements IAction {

	@Override
	public void execute() {

		Hotel.getInstance().importGuest();
		Printer.println("Import was successful");
	}

}
