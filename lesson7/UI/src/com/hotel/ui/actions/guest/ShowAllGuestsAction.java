package com.hotel.ui.actions.guest;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ShowAllGuestsAction implements IAction{

	@Override
	public void execute() {
		Printer.printArray(Hotel.getInstance().getGuests());
		
	}

}
