package com.hotel.ui.actions.room;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;
import com.ui.api.IConnection;

public class NumberOfFreeRooms implements IAction {
	private final String actionName = "getNumberOfFreeRooms";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {
		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);

	}

}
