package com.hotel.server.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.hotel.api.fasad.IHotel;
import com.hotel.api.util.ITokenRepository;
import com.hotel.fasad.Hotel;
import com.hotel.utils.JsonParser;
import com.hotel.utils.PasswordEncryptor;
import com.hotel.utils.TokenRepository;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class);
	private final String LOGIN = "Login";
	private final String REGISTREITED = "User was Registraited";
	private IHotel hotel = Hotel.getInstance();
	private ITokenRepository tokenRepository = TokenRepository.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		try {
			String password = PasswordEncryptor.encryptPassword(request.getParameter("password"));
			String login = request.getParameter("login");
			if (login != null && password != null) {
				hotel.register(login, password);
				response.getWriter().println(REGISTREITED);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String login = null;
		String password = null;
		try {
			JSONObject json = JsonParser.parseJson(request);
			login = (String) JsonParser.getValueFromJson(json, "login");
			password = (String) JsonParser.getValueFromJson(json, "password");
			if (login != null && password != null) {
				String token = hotel.signIn(login, password);
				tokenRepository.putToken(login, token);
				if (token != null) {
					request.getSession().setAttribute("login", login);
					hotel.writeLog(login, LOGIN);
					response.getWriter().println(token);
				}
			}
		} catch (Exception e) {
			try {
				response.getWriter().println("Exception: " + e.getMessage());
			} catch (IOException e1) {
				logger.error("I/O Exception ", e1);
			}
		}

	}
}
