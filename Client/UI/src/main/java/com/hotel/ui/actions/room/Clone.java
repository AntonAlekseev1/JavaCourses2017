package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;
import com.ui.api.IConnection;

public class Clone implements IAction {

	private final static Logger logger = Logger.getLogger(Clone.class);
	private final String actionName = "clone";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the original room id ");
			String idString = reader.readLine();

			Printer.println("Enter the new room number ");
			String numberStr = reader.readLine();
			request = actionName + " " + idString + " " + numberStr;
			response = connect.getResponseFromServer(request);
			Printer.println(response);
		} catch (IOException e) {
			Printer.println("Incorrect input data: " + e.toString());
			logger.error("Exception: " + e.getMessage());
		}

	}

}
