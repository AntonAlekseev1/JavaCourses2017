package com.hotel.ui.actions.option;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class ExportOptions implements IAction {
<<<<<<< HEAD
	
=======

>>>>>>> lesson11
	private final String actionName = "exportOptions";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
<<<<<<< HEAD
		
=======

>>>>>>> lesson11
		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);
	}

}
