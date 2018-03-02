package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;
import com.ui.api.IAction;

public class GetTotalPaymentAction implements IAction {

	private final static Logger logger = Logger.getLogger(GetTotalPaymentAction.class);
	private final String actionName = "getTotalPayment";
	private String request;
	private String response;

	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			Printer.println("Enter guest id");
			String idStr = reader.readLine();
			request = actionName + " " + idStr;
			response = Connection.getInstance().getResponseFromServer(request);
			Printer.println(response);
		} catch (IOException e) {
			logger.error("Exception in the class GetTotalPayment" + e.getMessage());
			Printer.println("This guest is not settled in any of the rooms: " + e.getMessage());
		}
	}
}
