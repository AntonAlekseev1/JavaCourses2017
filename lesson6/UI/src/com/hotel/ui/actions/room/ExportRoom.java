package com.hotel.ui.actions.room;

import java.util.List;

import com.hotel.api.been.IRoom;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.CsvWorker;

public class ExportRoom implements IAction {
	
	private static final String PATH_TO_ROOMS_CSV= String.valueOf(Configuration.getProperties("PATH_TO_ROOMS_CSV"));
	
	public ExportRoom() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {
		CsvWorker.Writer writer = new CsvWorker.Writer(PATH_TO_ROOMS_CSV);
		List<IRoom> rooms = Hotel.getInstance().getAllRooms();
		writer.comment("id;copacity;stars;price;isFree;status");
		for (int i = 0; i < rooms.size(); i++) {
			writer.write(rooms.get(i));
		}
	}
}
