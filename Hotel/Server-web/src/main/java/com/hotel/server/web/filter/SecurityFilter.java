package com.hotel.server.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.hotel.api.fasad.IHotel;
import com.hotel.api.util.ITokenRepository;
import com.hotel.fasad.Hotel;
import com.hotel.utils.TokenRepository;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {

	private IHotel hotel = Hotel.getInstance();
	private ITokenRepository tokenRepository = TokenRepository.getInstance();

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {
		super();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getRequestURI();
		HttpServletRequest req = (HttpServletRequest) request;
		String login = (String) req.getSession().getAttribute("login");
		String token = req.getHeader("token");
		if (token.equals(tokenRepository.getToken(login))) {
			chain.doFilter(request, response);
			String action = url + " " + (String) req.getSession().getAttribute("method");
			hotel.writeLog(login, action);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
