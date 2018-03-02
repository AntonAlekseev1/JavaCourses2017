package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;
import com.ui.api.IConnection;

public class AddOptionToGuestAction implements IAction {

	private final static Logger logger = Logger.getLogger(AddOptionToGuestAction.class);
	private final String actionName = "addOptionToGuest";
	private final IConnection connect = Connection.getInstance();
	private String reqest;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			Printer.println("Enter option id");
			String idStr = reader.readLine();
			Printer.println("Enter guest id");
			String guestIdStr = reader.readLine();
			reqest = actionName + " " + idStr + " " + guestIdStr;
			response = connect.getResponseFromServer(reqest);
			Printer.println(response);
		} catch (IOException e) {
			Printer.println("Exception in the method addOptionToGuest: " + e.getMessage());
			logger.error("Exception in the method addOptionToGuest: " + e.getMessage());
		}
	}
}
