package com.blackbox.controler.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.blackbox.api.response.IResponse;
import com.blackbox.api.response.ResponseStatus;

@Component
public class ResponseMap implements IResponse {
	
	private Map<ResponseStatus, Object> response;
	

	public Map<ResponseStatus, Object> getResponse() {
		return response;
	}
	
	public void putResponse(ResponseStatus status, Object data) {
		response = new HashMap<>();
		response.put(status, data);
	}
	

}
