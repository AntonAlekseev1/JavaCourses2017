package com.hotel.ui.actions.guest;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class SortGuestsByName implements IAction {

	@Override
	public void execute() {
		Printer.println("   Guest list sorted by name:");
		Printer.printArray(Hotel.getInstance().sortedGuestByName());
		
	}

}
