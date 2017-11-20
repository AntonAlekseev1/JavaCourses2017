package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ChangeRoomStatus implements IAction {

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		Boolean changeStatus = Boolean.valueOf(Configuration.getProperties("CHANGE_STATUS"));
		if(changeStatus) {
		try {
		Printer.print("Enter the room id ");
		String idStr =reader.readLine();
		Integer id=Integer.valueOf(idStr);
		IRoom room=Hotel.getInstance().getRoonById(id);
		Printer.print("Choose the new status of the room\n"+"1-OPEN\n"+"2-CLOSE\n"+"3-SERVICED\n"+"4-REPAIRABLE");
		String nStr = reader.readLine();
		Integer n=Integer.valueOf(nStr);
		switch(n) {
		case(1):
			room.setStatus(RoomStatus.OPEN);
		       break;
		case(2):
			room.setStatus(RoomStatus.CLOSE);
		        break;
		case(3):
			room.setStatus(RoomStatus.SERVICED);
		        break;
		case(4):
			room.setStatus(RoomStatus.REPAIRABLE);
		        break;
		}
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		} else {
			Printer.println("change of status is off");
		}
	}

}
