package edu.mum.ezstore.security;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.mum.ezstore.config.rest.RestError;

@Component("customBasicAuthFilter")
public class CustomBasicAuthFilter extends BasicAuthenticationFilter {

	@Autowired
	public CustomBasicAuthFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) {
		
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		ObjectMapper MAPPER = new ObjectMapper();
    	MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		RestError error=new RestError();
		error.setHttpCode(HttpStatus.UNAUTHORIZED);
		error.setType(URI.create("http://httpstatus.es/401"));
		error.setTitle("UnAuthentication");
		error.setMessage(failed.getLocalizedMessage());
		response.setContentType("application/json");
		response.setStatus(error.getHttpCode());

		MAPPER.writeValue(response.getOutputStream(), error);
//		response.sendRedirect(request.getContextPath() + "/api/error/unauthenticate");
//		throw new com.egen.exhandle.exception.AuthenticationException();
	}
}