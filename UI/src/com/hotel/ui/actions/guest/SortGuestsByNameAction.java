package com.hotel.ui.actions.guest;

import com.hotel.ui.action.IAction;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class SortGuestsByNameAction implements IAction {
<<<<<<< HEAD
	
	private final String actionName = "sortedGuestByName";
=======

	private final String actionName = "sortedGuests";
>>>>>>> lesson11
	private String request;
	private String response;

	@Override
	public void execute() {
<<<<<<< HEAD
		request = actionName;
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);
		
=======
		request = actionName + " NAME";
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);

>>>>>>> lesson11
	}

}
