package com.hotel.server.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.api.fasad.IHotel;
import com.hotel.fasad.Hotel;

/**
 * Servlet implementation class RoomServlet
 */
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IHotel hotel = Hotel.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("All Rooms").println();
		String list = hotel.getAllRooms().replaceAll(",", "\n");
		response.getWriter().println(list);
		request.getSession().setAttribute("method","GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String price = request.getParameter("newPrice");
		response.getWriter().append(hotel.chengePriceOfRoom(id, price));
		request.getSession().setAttribute("method","POST");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = request.getParameter("number");
		String copacity = request.getParameter("copacity");
		String stars = request.getParameter("stars");
		String price = request.getParameter("price");
		response.getWriter().append(hotel.addRoom(number, copacity, stars, price));
		request.getSession().setAttribute("method","PUT");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String st = request.getParameter("del");
		hotel.removeRoom(st);
		request.getSession().setAttribute("method","DELETE");
	}

}
