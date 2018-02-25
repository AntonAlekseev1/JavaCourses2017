package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.api.IConnection;
import com.ui.util.Printer;

public class SettleGuestInRoom implements IAction {

	private final static Logger logger = Logger.getLogger(SettleGuestInRoom.class);
	private final String actionName = "settleGuestInRoom";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the guest id ");
			String guestIdStr = reader.readLine();
			Printer.print("Enter the room id ");
			String roomIdStr = reader.readLine();
			Printer.print("Enter the date of arival ");
			String dateOfArival = reader.readLine();
			Printer.print("Enter the date of evict ");
			String dateOfEvict = reader.readLine();

			request = actionName + " " + guestIdStr + " " + roomIdStr + " " + dateOfArival + " " + dateOfEvict;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class SettleGuestInRoom: " + e.getMessage());
		}

	}

}
