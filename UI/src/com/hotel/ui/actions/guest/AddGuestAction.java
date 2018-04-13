package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class AddGuestAction implements IAction {
<<<<<<< HEAD:UI/src/com/hotel/ui/actions/guest/AddGuestAction.java
	
=======

>>>>>>> lesson11:UI/src/com/hotel/ui/actions/guest/AddGuestAction.java
	private final static Logger logger = Logger.getLogger(AddGuestAction.class);
	private final String actionName = "addGuest";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;
<<<<<<< HEAD:UI/src/com/hotel/ui/actions/guest/AddGuestAction.java
	
=======
>>>>>>> lesson11:UI/src/com/hotel/ui/actions/guest/AddGuestAction.java

	@Override
	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			Printer.print("Enter the name ");
			String name = reader.readLine();
			Printer.print("Enter the last name ");
<<<<<<< HEAD:UI/src/com/hotel/ui/actions/guest/AddGuestAction.java
			String lastName=reader.readLine();
			request = actionName+" "+name+" "+lastName;
=======
			String lastName = reader.readLine();
			request = actionName + " " + name + " " + lastName;
>>>>>>> lesson11:UI/src/com/hotel/ui/actions/guest/AddGuestAction.java
			response = connect.getResponseFromServer(request);
			Printer.println(response);
		} catch (IOException e) {
			Printer.println("Exception in the method addGuest: " + e.getMessage());
			logger.error("Exception in the method addGuest: " + e.getMessage());
		}
	}
}
