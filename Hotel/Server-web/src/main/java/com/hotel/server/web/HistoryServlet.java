package com.hotel.server.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.api.fasad.IHotel;
import com.hotel.fasad.Hotel;

/**
 * Servlet implementation class HistoryServlet
 */
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IHotel hotel = Hotel.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append(hotel.getHistory().replaceAll(",", "\n"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String[] arr = date.split(" ");
		String day = arr[0];
		String manth = arr[1];
		String year = arr[2];
		response.getWriter().append(hotel.getFreeRoomsOnDate(day, manth, year).replaceAll(",", "\n"));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guestId = request.getParameter("guestId");
		String roomId = request.getParameter("roomId");
		String arivalDate = request.getParameter("arivalDate");
		String evictDate = request.getParameter("evictDate");
		String[] arival = arivalDate.split(" ");
		String arivalDay = arival[0];
		String arivalMonth = arival[1];
		String arivalYear = arival[2];
		String[] evict = evictDate.split(" ");
		String evictDay = evict[0];
		String evictMonth = evict[1];
		String evictYear = evict[2];
		response.getWriter().append(hotel.settleGuestInRoom(guestId, roomId, arivalDay, arivalMonth, arivalYear,
				evictDay, evictMonth, evictYear));
	}

}
