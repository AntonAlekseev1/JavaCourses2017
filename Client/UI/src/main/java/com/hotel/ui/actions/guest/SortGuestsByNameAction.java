package com.hotel.ui.actions.guest;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;

public class SortGuestsByNameAction implements IAction {

	private final String actionName = "sortedGuests";
	private String request;
	private String response;

	public void execute() {
		request = actionName + " name";
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);

	}

}
