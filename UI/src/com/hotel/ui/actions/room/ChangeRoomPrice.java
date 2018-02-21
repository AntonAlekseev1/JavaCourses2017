package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class ChangeRoomPrice implements IAction {

	private static final Logger logger = Logger.getLogger(ChangeRoomPrice.class);
	private final String actionName = "chengePriceOfRoom";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the room id ");
			String idStr = reader.readLine();
			Printer.print("Enter the new price ");
			String priceStr = reader.readLine();
			request = actionName + " " + idStr + " " + priceStr;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (NumberFormatException | IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class ChangeRoomPrice: " + e.getMessage());
		}

	}

}
