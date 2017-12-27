package com.hotel.ui.actions.room;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class NumberOfFreeRooms implements IAction {

	@Override
	public void execute() {
		Printer.println("Number of free rooms "+Hotel.getInstance().getNumberOfFreeRooms());
		
	}

}
