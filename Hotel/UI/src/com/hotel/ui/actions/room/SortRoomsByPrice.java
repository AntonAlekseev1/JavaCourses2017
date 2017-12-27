package com.hotel.ui.actions.room;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class SortRoomsByPrice implements IAction {

	@Override
	public void execute() {
		Printer.println("   Room list sorted by price:");
		Printer.printArray(Hotel.getInstance().sortedRoomsByPrice());
		
	}

}
