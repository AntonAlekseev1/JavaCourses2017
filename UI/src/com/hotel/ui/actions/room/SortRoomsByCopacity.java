package com.hotel.ui.actions.room;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class SortRoomsByCopacity implements IAction {
<<<<<<< HEAD
	private final String actionName = "sortedRoomsByCopaciti";
=======
	private final String actionName = "sortRooms";
>>>>>>> lesson11
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

		request = actionName + " COPACITY";
		response = connect.getResponseFromServer(request);
		Printer.println(response);

>>>>>>> lesson11
	}

}
