package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.Connection;
import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.utils.Printer;

public class AddGuestAction implements IAction {
	
	private final static Logger logger = Logger.getLogger(AddGuestAction.class);
	private final String actionName = "AddGuest";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;
	

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Printer.print("Enter the name ");
			String name = reader.readLine();
			Printer.print("Enter the last name ");
			String lastName=reader.readLine();
			request = actionName+" "+name+" "+lastName;
			response = connect.getResponseFromServer(request);
			Printer.println(response);
		} catch (IOException e) {
			Printer.println("Exception in the method addGuest: " + e.getMessage());
			logger.error("Exception in the method addGuest: " + e.getMessage());
		}
	}
}
