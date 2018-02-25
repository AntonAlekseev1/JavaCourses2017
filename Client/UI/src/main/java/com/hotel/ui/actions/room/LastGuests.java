package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.api.IConnection;
import com.ui.util.Printer;

public class LastGuests implements IAction {

	private final String actionName = "getLastGuests";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	Logger logger = Logger.getLogger(LastGuests.class);

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the room id ");
			String id = reader.readLine();
			request = actionName + " " + id;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (Exception e) {

			Printer.println("Incorrect input data: " + e.getMessage());
			logger.info("Exception in class LastGuests" + e.getMessage());
		}

	}

}
