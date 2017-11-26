package com.hotel.ui.actions.guest;

import java.util.List;

import com.hotel.api.been.IGuest;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.CsvWorker;

public class ExportGuest implements IAction{
	
	private static final String PATH_TO_GUESTS_CSV= String.valueOf(Configuration.getProperties("PATH_TO_GUESTS_CSV"));
	
	public ExportGuest() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {
		CsvWorker.Writer writer = new CsvWorker.Writer(PATH_TO_GUESTS_CSV);
		List<IGuest> guests = Hotel.getInstance().getGuests();
		writer.comment("id;name;lastName");
		for(int i=0;i<guests.size();i++) {
			writer.write(guests.get(i));
		}
		
	}

}
