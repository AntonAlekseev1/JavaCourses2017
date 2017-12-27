package com.hotel.ui.actions.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ShowOptionByIdAction implements IAction {
	
	final static Logger logger = Logger.getLogger(ShowAllOptionsAction.class);

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the option id ");
			String idStr=reader.readLine();
			Integer id = Integer.valueOf(idStr);
			Printer.println(Hotel.getInstance().getOptionById(id));
			
		}catch (IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class ShowAllOptionsAction: " + e.getMessage());
		}
		
	}


}
