package com.hotel.server.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hotel.api.fasad.IHotel;
import com.hotel.api.util.ITokenRepository;
import com.hotel.fasad.Hotel;
import com.hotel.utils.TokenRepository;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LogoutServlet.class);
	private final String EXCEPTION = "Exception: ";
	private final String LOGOUT = "Logout";
	private IHotel hotel = Hotel.getInstance();
	private ITokenRepository tokenRepository = TokenRepository.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			String login = (String) session.getAttribute("login");
			tokenRepository.removeToken(login);
			hotel.writeLog(login, LOGOUT);
			session.invalidate();
			response.getWriter().println(LOGOUT);
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

}
