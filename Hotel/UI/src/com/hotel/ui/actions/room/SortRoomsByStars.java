package com.hotel.ui.actions.room;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class SortRoomsByStars implements IAction {

	@Override
	public void execute() {
		Printer.println("   Room list sorted by stars:");
		Printer.printArray(Hotel.getInstance().sortedRoomsByStars());
		
	}

}
