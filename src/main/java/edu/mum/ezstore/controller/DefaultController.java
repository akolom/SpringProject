package edu.mum.ezstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egen.exhandle.exception.AuthorizationException;
import com.egen.exhandle.exception.UnknownResourceException;

@Controller
public class DefaultController {
	@RequestMapping("/**")
    public void unmappedRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        throw new UnknownResourceException();
    }
	
	@RequestMapping("/error/unauthorize")
    public @ResponseBody String unauthorize(String url) {
		throw new AuthorizationException(url);
    }
}
