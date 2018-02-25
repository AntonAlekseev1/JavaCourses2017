package com.hotel.ui.action;

import com.hotel.ui.client.Connection;
import com.ui.api.IAction;
import com.ui.api.IConnection;


public class Exit implements IAction {

	private final IConnection connect = Connection.getInstance();
	
	public void execute() {

		connect.close();
	}

}
