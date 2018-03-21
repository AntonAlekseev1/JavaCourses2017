package com.hotel.server.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.api.fasad.IHotel;
import com.hotel.been.User;
import com.hotel.fasad.Hotel;
import com.hotel.utils.TokenWorker;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IHotel hotel = Hotel.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login!=null && password!=null) {
			String token = TokenWorker.getToken(login, password);
		 hotel.register(login, password, token);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login!=null && password!=null) {
			String token = TokenWorker.getToken(login, password);
			User user = hotel.signIn(login, password);
			user.setToken(token);
			if(user!=null) {
				request.getSession().setAttribute("User", user);
				hotel.writeLog(user, "LOGIN");
				response.getWriter().println("LOGIN");
			}
		}
	}
}
