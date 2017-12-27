package com.hotel.ui.actions.guest;

import com.hotel.ui.Connection;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ExportGuest implements IAction {
	
	private final String actionName = "ExportGuest";
	private String reqest;
	private String response;

	@Override
	public void execute() {

		reqest = actionName;
		response = Connection.getInstance().getResponseFromServer(reqest);
		Printer.println(response);
	}

}
