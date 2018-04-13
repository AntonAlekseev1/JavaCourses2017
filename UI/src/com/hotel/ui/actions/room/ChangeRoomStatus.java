package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.configurations.Configuration;
import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;
import com.hotel.utils.Printer;

public class ChangeRoomStatus implements IAction {
<<<<<<< HEAD
	
=======

>>>>>>> lesson11
	private static final Logger logger = Logger.getLogger(ChangeRoomStatus.class);
	private final String actionName = "changeRoomStatus";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;
<<<<<<< HEAD

	@Override
	public void execute() {
		Configuration.loadConfiguration();
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		if(changeStatus) {
		try {
		Printer.print("Enter the room id ");
		String idStr =reader.readLine();
		Printer.print("Choose the new status of the room\n"+"1-OPEN\n"+"2-CLOSE\n"+"3-SERVICED\n"+"4-REPAIRABLE");
		String nStr = reader.readLine();
		request = actionName+" "+idStr+" "+nStr;
		response = connect.getResponseFromServer(request);
		Printer.println(response);
		
		}catch (IOException e) {
			Printer.println("Incorrect input data: "+ e.getMessage());
			logger.info("Exception in class ChangeRoomStatus"+e.getMessage());
		}
=======
	private String propertiPath = "../Fasad/data/configurations.properties";

	@Override
	public void execute() {
		Configuration.loadConfiguration(propertiPath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		if (changeStatus) {
			try {
				Printer.print("Enter the room id ");
				String idStr = reader.readLine();
				Printer.print("Choose the new status of the room\n" + "1-OPEN\n" + "2-CLOSE\n" + "3-SERVICED\n"
						+ "4-REPAIRABLE");
				String nStr = reader.readLine();
				request = actionName + " " + idStr + " " + nStr;
				response = connect.getResponseFromServer(request);
				Printer.println(response);

			} catch (IOException e) {
				Printer.println("Incorrect input data: " + e.getMessage());
				logger.info("Exception in class ChangeRoomStatus" + e.getMessage());
			}
>>>>>>> lesson11
		} else {
			Printer.println("change of status is prohibited");
		}
	}

}
