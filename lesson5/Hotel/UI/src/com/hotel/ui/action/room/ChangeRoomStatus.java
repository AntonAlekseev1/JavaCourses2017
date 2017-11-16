package com.hotel.ui.action.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.api.been.IRoom;
import com.hotel.api.been.RoomStatus;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ChangeRoomStatus implements IAction {

	@Override
	public void execute() {
		
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			
		Printer.print("Enter the room id ");
		Integer id=reader.read();
		IRoom room=Hotel.getInstance().getRoonById(id);
		Printer.print("Choose the new status of the room/n"+"1-OPEN/n"+"2-CLOSE/n"+"3-SERVICED/n"+"4-REPAIRABLE");
		Integer n=reader.read();
		switch(n) {
		case(1):
			room.setStatus(RoomStatus.OPEN);
		case(2):
			room.setStatus(RoomStatus.CLOSE);
		case(3):
			room.setStatus(RoomStatus.SERVICED);
		case(4):
			room.setStatus(RoomStatus.REPAIRABLE);
		}
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
