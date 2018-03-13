package com.hotel.ui.actions.guest;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;

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