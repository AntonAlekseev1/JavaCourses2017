package com.hotel.ui.actions.room;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.api.IConnection;
import com.ui.util.Printer;

public class ExportRoom implements IAction {

	private final String actionName = "exportRooms";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;


	public void execute() {

		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);

	}
}
