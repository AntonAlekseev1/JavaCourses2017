package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.action.IAction;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class RemoveGuestAction implements IAction {
	
	private static final Logger logger = Logger.getLogger(RemoveGuestAction.class);
	private final String actionName = "remuveGuest";
	private String request;
	private String response;

	@Override
	public void execute() {
BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Printer.print("Enter the guest id ");
			String idStr = reader.readLine();
			request = actionName+" "+idStr;
			response = Connection.getInstance().getResponseFromServer(request);
			Printer.println(response);
		}catch(IOException e) {
			logger.info("Exception in class RemoveGuestAction"+e.getMessage());
	
		}
		
	}

}
