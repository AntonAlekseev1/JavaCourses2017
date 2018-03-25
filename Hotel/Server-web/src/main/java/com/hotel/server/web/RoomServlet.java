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
 * Servlet implementation class RoomServlet
 */
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GuestServelet.class);
	private final String EXCEPTION = "Exception: ";
	private final String JSON_TYPE = "application/json";
	private IHotel hotel = Hotel.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoomServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().append("All Rooms").println();
			String list = hotel.getAllRooms();
			response.setContentType(JSON_TYPE);
			response.getWriter().println(JsonParser.convertToJson(list));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try {
			json = JsonParser.parseJson(request);
			Integer id = (Integer) JsonParser.getValueFromJson(json, "id");
			Double price = (Double) JsonParser.getValueFromJson(json, "newPrice");
			response.setContentType(JSON_TYPE);
			response.setContentType(JSON_TYPE);
			response.getWriter().println(JsonParser.convertToJson(hotel.chengePriceOfRoom(id, price)));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try {
			json = JsonParser.parseJson(request);
			Integer number = (Integer) JsonParser.getValueFromJson(json, "number");
			Integer copacity = (Integer) JsonParser.getValueFromJson(json, "copacity");
			Integer stars = (Integer) JsonParser.getValueFromJson(json, "stars");
			Double price = (Double) JsonParser.getValueFromJson(json, "price");
			response.setContentType(JSON_TYPE);
			response.getWriter().println(new JSONObject(hotel.addRoom(number, copacity, stars, price)));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try {
			json = JsonParser.parseJson(request);
			Integer id = (Integer) JsonParser.getValueFromJson(json, "id");
			hotel.removeRoom(id);
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

}
