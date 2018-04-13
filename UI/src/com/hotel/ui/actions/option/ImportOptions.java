package com.hotel.ui.actions.option;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class ImportOptions implements IAction {
<<<<<<< HEAD
	
=======

>>>>>>> lesson11
	private final String actionName = "importOptions";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {

		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);
	}

}
