package com.hotel.ui.actions.option;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.api.IConnection;
import com.ui.util.Printer;

public class ExportOptions implements IAction {

	private final String actionName = "exportOptions";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {

		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);
	}

}
