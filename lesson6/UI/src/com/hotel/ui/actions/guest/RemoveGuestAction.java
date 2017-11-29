package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class RemoveGuestAction implements IAction {
	
	Logger logger = Logger.getLogger(RemoveGuestAction.class);

	@Override
	public void execute() {
BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Printer.print("Enter the guest id ");
			String idStr = reader.readLine();
			Integer id = Integer.valueOf(idStr);
		Hotel.getInstance().remuveGuest(id);
		}catch(IOException e) {
			logger.info("Exception in class RemoveGuestAction"+e.getMessage());
			
			
		}
		
	}

}
