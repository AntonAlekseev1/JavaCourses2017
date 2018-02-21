package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class RoomById implements IAction {
	private final String actionName = "getRoomById";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
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
