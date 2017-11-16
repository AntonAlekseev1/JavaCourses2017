package com.hotel.ui.action.option;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class AllOptions implements IAction {

	@Override
	public void execute() {
		Printer.println("  Option list:");
		Printer.printArray(Hotel.getInstance().getAllOptions());
		
	}

}
