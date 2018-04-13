package com.hotel.ui.actions.room;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

<<<<<<< HEAD
public class FreeRooms implements IAction{
	
=======
public class FreeRooms implements IAction {

>>>>>>> lesson11
	private final String actionName = "getFreeRooms";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
<<<<<<< HEAD
		
		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);
		
=======

		request = actionName;
		response = connect.getResponseFromServer(request);
		Printer.println(response);

>>>>>>> lesson11
	}

}
