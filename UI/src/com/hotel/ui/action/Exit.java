package com.hotel.ui.action;

import com.hotel.ui.api.IConnection;
import com.hotel.ui.client.Connection;

public class Exit implements IAction {

	private final IConnection connect = Connection.getInstance();
	

	@Override
	public void execute() {
		
			connect.close();
	}

}
