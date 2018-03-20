package com.hotel.server.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.hotel.api.fasad.IHotel;
import com.hotel.been.User;
import com.hotel.fasad.Hotel;
import com.hotel.utils.TokenGenerator;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {

	private List<String> pathFilter = Arrays
			.asList(new String[] { "GuestServelet", "RoomServlet", "HistoryServlet", "OptionServlet" });
	private IHotel hotel = Hotel.getInstance();

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {
		
	}

	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getRequestURI();
		String path = StringUtils.substringAfterLast(url, "/");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String token = TokenGenerator.generateToken(login, password);
		if (!pathFilter.contains(path)) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("User");	
	if(user!=null) {
		String userToken = user.getToken();
		 if (userToken!=null) {
			chain.doFilter(request, response);
			String action = url+" "+(String) req.getSession().getAttribute("method");
			hotel.writeLog(user, action);
		} 
	}
	}


	@Override
	public void destroy() {
		
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
