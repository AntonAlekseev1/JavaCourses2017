package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;

public class GuestOptions implements IAction {

	private final static Logger logger = Logger.getLogger(GuestOptions.class);
	private final String actionName = "getGuestOptions";
	private String reqest;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.println("Enter guest number");
			String guestIdStr = reader.readLine();
			reqest = actionName + " " + guestIdStr;
			response = Connection.getInstance().getResponseFromServer(reqest);
			Printer.println(response);
		} catch (IOException e) {
			logger.error("Exception in the class GuestOptions: " + e.getMessage());
		}
	}
}