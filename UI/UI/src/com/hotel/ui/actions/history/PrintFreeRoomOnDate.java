package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.Connection;
import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.utils.Printer;

public class PrintFreeRoomOnDate implements IAction{
	
	private final static Logger logger = Logger.getLogger(PrintFreeRoomOnDate.class);
	private final String actionName = "FreeRoomOnDate";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the date dd mm yyyy");
			String dateStr=reader.readLine();
			request = actionName+" "+dateStr;
			response = connect.getResponseFromServer(request);
			Printer.println(response);
			
		}catch (IOException e) {
			Printer.println("Incorrect date format: " + e.getMessage());
			logger.error("Exception in the class PrintFreeRoomOnDate: " + e.getMessage());
		}
		
	}

}
