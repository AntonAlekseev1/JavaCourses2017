package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.api.IConnection;
import com.ui.util.Printer;

public class PrintFreeRoomOnDate implements IAction {

	private final static Logger logger = Logger.getLogger(PrintFreeRoomOnDate.class);
	private final String actionName = "getFreeRoomsOnDate";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the date yyy.mm.dd ");
			String dateStr = reader.readLine();
			request = actionName + " " + dateStr;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (IOException e) {
			Printer.println("Incorrect date format: " + e.getMessage());
			logger.error("Exception in the class PrintFreeRoomOnDate: " + e.getMessage());
		}

	}

}
