package com.hotel.ui.actions.guest;

import com.hotel.ui.action.IAction;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class ExportGuest implements IAction {
<<<<<<< HEAD
	
=======

>>>>>>> lesson11
	private final String actionName = "exportGuests";
	private String reqest;
	private String response;

	@Override
	public void execute() {

		reqest = actionName;
		response = Connection.getInstance().getResponseFromServer(reqest);
		Printer.println(response);
	}

}
