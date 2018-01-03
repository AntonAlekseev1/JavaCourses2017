package com.hotel.ui.actions.guest;

import com.hotel.ui.Connection;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ImportGuests implements IAction {
	
	private final String actionName = "importGuest";
	private String request;
	private String response;

	@Override
	public void execute() {

		request = actionName;
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);
	}

}
