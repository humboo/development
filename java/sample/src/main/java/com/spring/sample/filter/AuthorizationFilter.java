package com.spring.sample.filter;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationFilter implements Filter {

	private final static Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
	
		Optional<String> authString = Optional.ofNullable(req.getHeader("Authorization"));
		if (authString.isPresent()) {
			String token = authString.get();
			log.info("Authorization Token exists: " + token);
			// validate token
		}
		log.info("auth:" + authString);
		
		// do something with permission
		
		request.setAttribute("permissions", "permissionsFromFilter");
		
		chain.doFilter(request, response);
	}
}
