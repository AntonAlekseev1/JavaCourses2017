package com.hotel.ui.actions.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.Connection;
import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.utils.Printer;

public class ShowOptionByIdAction implements IAction {
	
	private final static Logger logger = Logger.getLogger(ShowAllOptionsAction.class);
	private final String actionName = "getOptionById";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the option id ");
			String idStr=reader.readLine();
			request = actionName+" "+idStr;
			response = connect.getResponseFromServer(request);
			Printer.println(response);
			
		}catch (IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class ShowAllOptionsAction: " + e.getMessage());
		}
		
	}


}
