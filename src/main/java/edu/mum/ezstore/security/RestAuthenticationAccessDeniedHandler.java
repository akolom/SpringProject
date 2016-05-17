package edu.mum.ezstore.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * This entry point is called once the request denied their access to resource.
 * 
 * @author Sam
 * 
 */
@Component
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	 
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException arg2) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    	response.sendRedirect(request.getContextPath() + "/api/error/unauthorize?url="+request.getRequestURI());
    }
}
