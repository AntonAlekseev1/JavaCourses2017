package com.hotel.ui.actions.guest;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.util.Printer;

public class NumberOfGuests implements IAction {

	private final String actionName = "getNumberOfGuests";
	private String request;
	private String response;

	public void execute() {

		request = actionName;
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);
	}

}
