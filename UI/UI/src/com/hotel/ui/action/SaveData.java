package com.hotel.ui.action;

import com.hotel.ui.Connection;
import com.hotel.ui.api.IConnection;
import com.hotel.utils.Printer;

public class SaveData implements IAction{
	
	private final String actionName = "SaveData";
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
