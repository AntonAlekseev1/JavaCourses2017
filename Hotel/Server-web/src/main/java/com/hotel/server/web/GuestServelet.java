package com.hotel.server.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.hotel.api.fasad.IHotel;
import com.hotel.fasad.Hotel;
import com.hotel.utils.JsonParser;

/**
 * Servlet implementation class GuestServelet
 */
public class GuestServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GuestServelet.class);
	private final String JSON_TYPE = "application/json";
	private final String EXCEPTION = "Exception: ";
	private IHotel hotel = Hotel.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuestServelet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType(JSON_TYPE);
			response.getWriter().println(JsonParser.convertToJson(hotel.getGuests()));
			String login = (String) request.getSession().getAttribute("login");
			hotel.writeLog(login, "getAllGuests");
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try {
			json = JsonParser.parseJson(request);
			String name = (String) JsonParser.getValueFromJson(json, "name");
			String lastName = (String) JsonParser.getValueFromJson(json, "lastName");
			response.setContentType(JSON_TYPE);
			response.getWriter().println(JsonParser.convertToJson(hotel.addGuest(name, lastName)));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try {
			json = JsonParser.parseJson(request);
			Integer id = (Integer) JsonParser.getValueFromJson(json, "id");
			response.setContentType(JSON_TYPE);
			response.getWriter().println(JsonParser.convertToJson(hotel.getTotalPayment(id)));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try {
			json = JsonParser.parseJson(request);
			hotel.remuveGuest((Integer) JsonParser.getValueFromJson(json, "id"));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

}
