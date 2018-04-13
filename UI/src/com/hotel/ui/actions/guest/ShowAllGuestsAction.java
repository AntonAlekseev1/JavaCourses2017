package com.hotel.ui.actions.guest;

import com.hotel.ui.action.IAction;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

<<<<<<< HEAD
public class ShowAllGuestsAction implements IAction{
	
=======
public class ShowAllGuestsAction implements IAction {

>>>>>>> lesson11
	private final String actionName = "getGuests";
	private String request;
	private String response;

	@Override
	public void execute() {
<<<<<<< HEAD
		request = actionName; 
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);	
		
=======
		request = actionName;
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);

>>>>>>> lesson11
	}

}
