package com.hotel.server.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hotel.api.util.ITokenRepository;
import com.hotel.server.web.HistoryServlet;
import com.hotel.utils.TokenRepository;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {

	private static final Logger logger = Logger.getLogger(HistoryServlet.class);
	private final String EXCEPTION = "Exception: ";
	private ITokenRepository tokenRepository = TokenRepository.getInstance();
	private FilterConfig filterConfig;

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {
		super();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			String login = (String) req.getSession().getAttribute("login");
			String token = req.getHeader("token");
			if (token.equals(tokenRepository.getToken(login))) {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig=null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig = filterConfig;

	}

}
