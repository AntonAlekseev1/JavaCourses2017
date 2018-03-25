package com.hotel.server.web;

import java.io.IOException;
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
 * Servlet implementation class HistoryServlet
 */
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HistoryServlet.class);
	private final String EXCEPTION = "Exception: ";
	private final String IO_EXCEPTION = "I/O Exception: ";
	private final String JSON_TYPE = "application/json";
	private IHotel hotel = Hotel.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try (PrintWriter writer = response.getWriter()) {
			response.setContentType(JSON_TYPE);
			writer.println(JsonParser.listToJson(hotel.getHistory()));
		} catch (Exception e) {
			try (PrintWriter writer = response.getWriter()) {
				writer.println(JsonParser.convertToJson(EXCEPTION + e.getMessage()));
			} catch (IOException e1) {
				logger.error(IO_EXCEPTION, e1);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try (PrintWriter writer = response.getWriter()) {
			json = JsonParser.parseJson(request);
			String date = (String) JsonParser.getValueFromJson(json, "date");
			String[] arr = date.split(" ");
			String day = arr[0];
			String manth = arr[1];
			String year = arr[2];
			response.setContentType(JSON_TYPE);
			writer.println(JsonParser.convertToJson(hotel.getFreeRoomsOnDate(day, manth, year).replaceAll(",", "\n")));
		} catch (Exception e) {
			try (PrintWriter writer = response.getWriter()) {
				writer.println(JsonParser.convertToJson(EXCEPTION + e.getMessage()));
			} catch (IOException e1) {
				logger.error(IO_EXCEPTION, e1);
			}
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json;
		try (PrintWriter writer = response.getWriter()) {
			json = JsonParser.parseJson(request);
			Integer guestId = (Integer) JsonParser.getValueFromJson(json, "guestId");
			Integer roomId = (Integer) JsonParser.getValueFromJson(json, "roomId");
			String arivalDate = (String) JsonParser.getValueFromJson(json, "arivalDate");
			String evictDate = (String) JsonParser.getValueFromJson(json, "evictDate");
			String[] arival = arivalDate.split(" ");
			String arivalDay = arival[0];
			String arivalMonth = arival[1];
			String arivalYear = arival[2];
			String[] evict = evictDate.split(" ");
			String evictDay = evict[0];
			String evictMonth = evict[1];
			String evictYear = evict[2];
			response.setContentType(JSON_TYPE);
			writer.println(JsonParser.convertToJson(hotel.settleGuestInRoom(guestId, roomId, arivalDay, arivalMonth, arivalYear,
					evictDay, evictMonth, evictYear)));
		} catch (Exception e) {
			try (PrintWriter writer = response.getWriter()) {
				writer.println(JsonParser.convertToJson(EXCEPTION + e.getMessage()));
			} catch (IOException e1) {
				logger.error(IO_EXCEPTION, e1);
			}
		}
	}

}
