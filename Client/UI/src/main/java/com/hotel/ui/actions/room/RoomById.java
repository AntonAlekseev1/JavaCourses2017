package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;
import com.ui.api.IConnection;

public class RoomById implements IAction {
	private final String actionName = "getRoomById";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the room id ");
			String idStr = reader.readLine();
			request = actionName + " " + idStr;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (IOException e) {
			Printer.println("Exception in the class RoomById: " + e.getMessage());
		}

	}

}