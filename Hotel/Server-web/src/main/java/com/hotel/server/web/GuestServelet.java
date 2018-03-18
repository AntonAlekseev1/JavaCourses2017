package com.hotel.server.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.api.fasad.IHotel;
import com.hotel.fasad.Hotel;

/**
 * Servlet implementation class GuestServelet
 */
public class GuestServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IHotel hotel = Hotel.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestServelet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String list = hotel.getGuests();
		response.getWriter().println(list);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String guest = hotel.addGuest(name, lastName);
		response.getWriter().println(guest);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String guest = hotel.getTotalPayment(id);
		resp.getWriter().println(guest);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String st = req.getParameter("del");
		 hotel.remuveGuest(st);
	}

}
