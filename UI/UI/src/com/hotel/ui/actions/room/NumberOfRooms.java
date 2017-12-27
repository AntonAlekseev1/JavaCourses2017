package com.hotel.ui.actions.room;

import com.hotel.ui.Connection;
import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.utils.Printer;

public class NumberOfRooms implements IAction {
	private final String actionName = "NumberOfRooms";
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
