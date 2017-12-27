package com.hotel.ui.actions.guest;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class NumberOfGuests implements IAction{

	@Override
	public void execute() {
		Printer.println("Number of guests "+Hotel.getInstance().getNumberOfGuests());
		
	}
	

}
