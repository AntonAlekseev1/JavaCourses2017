package com.hotel.ui.actions.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;
import com.ui.api.IConnection;

public class AddOptionAction implements IAction {

	private final static Logger logger = Logger.getLogger(AddOptionAction.class);
	private final String actionName = "addOption";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the option name ");
			String name = reader.readLine();
			Printer.print("Enter the price ");
			String priceString = reader.readLine();
			request = actionName + " " + name + " " + priceString;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class SettleGuestInRoom: " + e.getMessage());
		}

	}

}
