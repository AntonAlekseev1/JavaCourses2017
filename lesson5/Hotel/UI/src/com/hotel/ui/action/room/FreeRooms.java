package com.hotel.ui.action.room;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class FreeRooms implements IAction{

	@Override
	public void execute() {
		Printer.printArray(Hotel.getInstance().getFreeRooms());
		
	}

}
