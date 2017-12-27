package com.hotel.ui.action;

import com.hotel.ui.Connection;
import com.hotel.ui.api.IConnection;

public class Exit implements IAction {

	private final IConnection connect = Connection.getInstance();
	

	@Override
	public void execute() {
		
			connect.close();
	}

}
