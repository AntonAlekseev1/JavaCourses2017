package com.hotel.ui.actions.guest;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ExportGuest implements IAction {

	@Override
	public void execute() {

		Hotel.getInstance().exportGuests();
		Printer.println("Export was successful");
	}

}
