package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class AddRoom implements IAction {
	
	private final static Logger logger = Logger.getLogger(AddRoom.class);
	private final String actionName = "addRoom";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the copaciti ");
			String cop=reader.readLine();
			Printer.print("Enter the number of stars ");
			String stars=reader.readLine();
			Printer.print("Enter the price ");
			String priceString=reader.readLine();
			request = actionName+" "+cop+" "+stars+" "+priceString;
			response = connect.getResponseFromServer(request);
			Printer.println(response);

		} catch (NumberFormatException|IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class AddRoom: " + e.getMessage());
		}
		
	}

}
