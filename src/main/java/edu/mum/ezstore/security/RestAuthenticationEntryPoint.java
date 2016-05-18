package edu.mum.ezstore.security;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.mum.ezstore.config.rest.RestError;

/**
 * This entry point is called once the request missing their authentication.
 * 
 * @author Sam
 * 
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authEx) throws IOException, ServletException{
    	ObjectMapper MAPPER = new ObjectMapper();
    	MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		RestError error=new RestError();
		error.setHttpCode(HttpStatus.UNAUTHORIZED);
		error.setType(URI.create("http://httpstatus.es/401"));
		error.setTitle("UnAuthentication");
		error.setMessage(authEx.getLocalizedMessage());
		response.setContentType("application/json");
		response.setStatus(error.getHttpCode());

		MAPPER.writeValue(response.getOutputStream(), error);
//    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
 
}