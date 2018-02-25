package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.api.IConnection;
import com.ui.util.Printer;

public class AddRoom implements IAction {

	private final static Logger logger = Logger.getLogger(AddRoom.class);
	private final String actionName = "addRoom";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the number ");
			String num = reader.readLine();
			Printer.print("Enter the copaciti ");
			String cop = reader.readLine();
			Printer.print("Enter the number of stars ");
			String stars = reader.readLine();
			Printer.print("Enter the price ");
			String priceString = reader.readLine();
			request = actionName + " " + num + " " + cop + " " + stars + " " + priceString;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (Exception e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class AddRoom: " + e.getMessage());
		}

	}

}
