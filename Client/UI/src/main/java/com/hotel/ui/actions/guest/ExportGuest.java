package com.hotel.ui.actions.guest;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;

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
