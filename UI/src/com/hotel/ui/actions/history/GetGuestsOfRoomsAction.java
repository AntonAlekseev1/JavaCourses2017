package com.hotel.ui.actions.history;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

<<<<<<< HEAD
public class GetGuestsOfRoomsAction implements IAction{
	
	private final String actionName = "getGuestsRooms";
=======
public class GetGuestsOfRoomsAction implements IAction {

	private final String actionName = "getHistory";
>>>>>>> lesson11
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);
<<<<<<< HEAD
		
=======

>>>>>>> lesson11
	}

}
