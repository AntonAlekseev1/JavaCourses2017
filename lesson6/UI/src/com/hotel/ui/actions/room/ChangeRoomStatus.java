package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ChangeRoomStatus implements IAction {
	
	Logger logger = Logger.getLogger(ChangeRoomStatus.class);

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		if(changeStatus) {
		try {
		Printer.print("Enter the room id ");
		String idStr =reader.readLine();
		Integer id=Integer.valueOf(idStr);
		Printer.print("Choose the new status of the room\n"+"1-OPEN\n"+"2-CLOSE\n"+"3-SERVICED\n"+"4-REPAIRABLE");
		String nStr = reader.readLine();
		Integer n=Integer.valueOf(nStr);
		Hotel.getInstance().changeRoomStatus(id, n);
		}catch (NumberFormatException|IOException e) {
			Printer.println("Incorrect input data: "+ e.getMessage());
			logger.info("Exception in class ChangeRoomStatus"+e.getMessage());
		}
		} else {
			Printer.println("change of status is prohibited");
		}
	}

}
