package com.hotel.ui.actions.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class AddOptionAction implements IAction {
<<<<<<< HEAD:UI/src/com/hotel/ui/actions/option/AddOptionAction.java
	
=======

>>>>>>> lesson11:UI/src/com/hotel/ui/actions/option/AddOptionAction.java
	private final static Logger logger = Logger.getLogger(AddOptionAction.class);
	private final String actionName = "addOption";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the option name ");
			String name = reader.readLine();
			Printer.print("Enter the price ");
<<<<<<< HEAD:UI/src/com/hotel/ui/actions/option/AddOptionAction.java
			String priceString = reader.readLine();	
			request = actionName+" "+name+" "+priceString;
=======
			String priceString = reader.readLine();
			request = actionName + " " + name + " " + priceString;
>>>>>>> lesson11:UI/src/com/hotel/ui/actions/option/AddOptionAction.java
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class SettleGuestInRoom: " + e.getMessage());
		}

	}

}
