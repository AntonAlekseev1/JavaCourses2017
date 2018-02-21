package com.hotel.ui.actions.guest;

import com.hotel.ui.action.IAction;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class NumberOfGuests implements IAction {

	private final String actionName = "getNumberOfGuests";
	private String request;
	private String response;

	@Override
	public void execute() {

		request = actionName;
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);
	}

}
