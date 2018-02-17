package com.hotel.ui.actions.guest;

import com.hotel.ui.action.IAction;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class ShowAllGuestsAction implements IAction{
	
	private final String actionName = "getGuests";
	private String request;
	private String response;

	@Override
	public void execute() {
		request = actionName; 
		response = Connection.getInstance().getResponseFromServer(request);
		Printer.println(response);	
		
	}

}
