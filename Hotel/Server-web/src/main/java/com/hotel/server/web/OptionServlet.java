package com.hotel.server.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.hotel.api.fasad.IHotel;
import com.hotel.fasad.Hotel;
import com.hotel.utils.JsonParser;

/**
 * Servlet implementation class OptionServlet
 */
public class OptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OptionServlet.class);
	private final String EXCEPTION = "Exception: ";
	private IHotel hotel = Hotel.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OptionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try (PrintWriter writer = response.getWriter()) {
			writer.println(JsonParser.convertToJson(hotel.getAllOptions().replaceAll(",", "\n")));
			String login = (String) request.getSession().getAttribute("login");
			hotel.writeLog(login, "getAllOptions");
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try (PrintWriter writer = response.getWriter()) {
			JSONObject json = JsonParser.parseJson(request);
			Integer optionId = (Integer) JsonParser.getValueFromJson(json, "optionId");
			Integer guestId = (Integer) JsonParser.getValueFromJson(json, "guestId");
			writer.println(JsonParser.convertToJson(hotel.addOptionToGuest(optionId, guestId)));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		try (PrintWriter writer = response.getWriter()) {
			JSONObject json = JsonParser.parseJson(request);
			String name = (String) JsonParser.getValueFromJson(json, "name");
			Double price = (Double) JsonParser.getValueFromJson(json, "price");
			writer.println(hotel.addOption(name, price));
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

}
