package com.blackbox.api.response;

import java.util.Map;

public interface IResponse {
	
	public Map<ResponseStatus, Object> getResponse();
	
	public void putResponse(ResponseStatus status, Object data);

}
