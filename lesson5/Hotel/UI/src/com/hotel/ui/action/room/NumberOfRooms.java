package com.hotel.ui.action.room;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class NumberOfRooms implements IAction {

	@Override
	public void execute() {
		Printer.println("Number of rooms "+Hotel.getInstance().getNumberOfRooms());
		
	}

}
