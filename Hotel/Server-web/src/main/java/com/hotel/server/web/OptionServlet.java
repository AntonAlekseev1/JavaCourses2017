package com.hotel.server.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.api.fasad.IHotel;
import com.hotel.fasad.Hotel;

/**
 * Servlet implementation class OptionServlet
 */
public class OptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IHotel hotel = Hotel.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(hotel.getAllOptions().replaceAll(",", "\n"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(hotel.addOptionToGuest(request.getParameter("optionId"), request.getParameter("guestId")));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(hotel.addOption(request.getParameter("name"), request.getParameter("price")));
	}

}
