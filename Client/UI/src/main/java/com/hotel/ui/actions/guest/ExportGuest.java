package com.hotel.ui.actions.guest;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.util.Printer;

public class ExportGuest implements IAction {

	private final String actionName = "exportGuests";
	private String reqest;
	private String response;

	public void execute() {

		reqest = actionName;
		response = Connection.getInstance().getResponseFromServer(reqest);
		Printer.println(response);
	}

}
