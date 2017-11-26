package com.hotel.ui.actions.room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hotel.api.been.IRoom;
import com.hotel.been.Room;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.CsvWorker;


public class ImportRoom implements IAction {
	
	private static final String PATH_TO_ROOMS_CSV= String.valueOf(Configuration.getProperties("PATH_TO_ROOMS_CSV"));
	
	public ImportRoom() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {
		List<IRoom> rooms = Hotel.getInstance().getAllRooms();
		List<IRoom> roomsImport = new ArrayList<>();
		CsvWorker.Reader reader = new CsvWorker.Reader(PATH_TO_ROOMS_CSV);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			roomsImport.add(new Room(reader.read().get(i)));
		}

		for (int i = 0; i < rooms.size(); i++) {
			Collections.replaceAll(rooms, rooms.get(i), roomsImport.get(i));
		}
		for (int i = rooms.size(); i < reader.read().size(); i++) {
			rooms.add(roomsImport.get(i));
		}
	}

}
