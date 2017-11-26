package com.hotel.ui.actions.history;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class GetGuestsOfRooms implements IAction{

	@Override
	public void execute() {
		Printer.printArray(Hotel.getInstance().getGuestsRooms());
		
	}

}
