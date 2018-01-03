package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.ui.Connection;
import com.hotel.ui.action.IAction;
import com.hotel.ui.api.IConnection;
import com.hotel.utils.Printer;

public class Clone implements IAction{
	
	private final static Logger logger = Logger.getLogger(Clone.class);
	private final String actionName = "clone";
	private final IConnection connect = Connection.getInstance();
	private String request;
	private String response;

	@Override
	public void execute()  {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the original room id ");
			String idString=reader.readLine();
			Printer.print("Do you want to modify room clon? Y/N ");
			String answer=reader.readLine();
			Integer copacity=null;
			Integer stars=null;
			Double price=null;
			if(answer.equals("Y")|answer.equals("y")) {
				Printer.println("Enter the room copacity ");
				String copacityStr=reader.readLine();
				Printer.println("Enter the room number of stars ");
				String starsStr=reader.readLine();
				Printer.println("Enter the room price ");
				String priceStr=reader.readLine();
				request = actionName+" "+idString+" "+answer+" "+copacityStr+" "+starsStr+" "+priceStr;
			}else {
			request = actionName+" "+idString+" "+answer+" "+copacity+" "+stars+" "+price;
			}
			response = connect.getResponseFromServer(request);
			Printer.println(response);
		}catch(IOException e) {
			Printer.println("Incorrect input data: "+e.toString());
			logger.error("Exception: "+e.getMessage());
		}
		
	}
	

}
