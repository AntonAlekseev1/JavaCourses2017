package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;
import com.ui.api.IConnection;

public class EvictGuestAction implements IAction {

	private final static Logger logger = Logger.getLogger(EvictGuestAction.class);
	private final String actionName = "evictGuestFromRoom";
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
			request = actionName + " " + guestIdStr + " " + roomIdStr;
			response = connect.getResponseFromServer(request);
			Printer.println(response);
		} catch (IOException e) {
			Printer.println("Exception in the class EvictGuestAction: " + e.getMessage());
			logger.error("Exception in the class EvictGuestAction: " + e.getMessage());
		}

	}

}
