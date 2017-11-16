package com.hotel.ui.action.room;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class SortRoomsByCopacity implements IAction {

	@Override
	public void execute() {
		Printer.println("   Room list sorted by copacity:");
		Printer.printArray(Hotel.getInstance().sortedRoomsByCopaciti());
		
	}

}
