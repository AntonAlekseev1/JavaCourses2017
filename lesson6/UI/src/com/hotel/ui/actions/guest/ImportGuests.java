package com.hotel.ui.actions.guest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.been.Guest;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.CsvWorker;

public class ImportGuests implements IAction{
	
	private static final String PATH_TO_GUESTS_CSV= String.valueOf(Configuration.getProperties("PATH_TO_GUESTS_CSV"));
	
	public ImportGuests() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {
		List<IGuest> guests = Hotel.getInstance().getGuests();
		List<IGuest> guestsImport = new ArrayList<>();
		CsvWorker.Reader reader = new CsvWorker.Reader(PATH_TO_GUESTS_CSV);
		reader.read();
		for (int i = 0; i < reader.read().size(); i++) {
			guestsImport.add(new Guest(reader.read().get(i)));
		}

		for (int i = 0; i < guests.size(); i++) {
			Collections.replaceAll(guests, guests.get(i), guestsImport.get(i));
		}
		for (int i = guests.size(); i < reader.read().size(); i++) {
			guests.add(guestsImport.get(i));
		}
		
	}

}
