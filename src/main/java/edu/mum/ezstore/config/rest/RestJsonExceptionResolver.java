package edu.mum.ezstore.config.rest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.egen.exhandle.exception.AuthenticationException;
import com.egen.exhandle.exception.AuthorizationException;
import com.egen.exhandle.exception.BusinessException;
import com.egen.exhandle.exception.DomainValidationException;
import com.egen.exhandle.exception.InvalidArgumentsException;
import com.egen.exhandle.exception.MaximumRequestsLimitException;
import com.egen.exhandle.exception.ObjectAlreadyExistsException;
import com.egen.exhandle.exception.ObjectNotFoundException;
import com.egen.exhandle.exception.ObjectStateChangedSinceLastRequestException;
import com.egen.exhandle.exception.UnknownResourceException;
import com.egen.exhandle.handler.ConstraintViolationExceptionHandler;
import com.egen.exhandle.handler.DomainValidationExceptionHandler;
import com.egen.exhandle.handler.ErrorMessageRestExceptionHandler;
import com.egen.exhandle.handler.RestExceptionHandler;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.http.HttpStatus.*;

/**
 * Custom Rest/Json Exception handler for returning JSON responses when
 * Exceptions are uncaught.
 */
public class RestJsonExceptionResolver extends AbstractHandlerExceptionResolver implements View {

	private static final Logger LOG = LoggerFactory.getLogger(RestJsonExceptionResolver.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final String KEY_ERROR_OBJECT = "error";
	private static final Map<String, Integer> httpErrorCodeMap = new HashMap<>();
	private Map<Class<? extends Exception>, RestExceptionHandler> handlers = new LinkedHashMap<>();
	private MessageSource messageSource;
	/**
	 * Disable technical diagnostics like stack traces. Default value is FALSE
	 */
	private boolean diagnosticsDisabled;
	

	public RestJsonExceptionResolver(MessageSource msg) {
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		// to map all the children of class
		MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		this.setMessageSource(msg);
		handlers = getDefaultHandlers();
		// initialize handlers
		for (RestExceptionHandler handler : handlers.values()) {
			if (messageSource != null && handler instanceof MessageSourceAware) {
				((MessageSourceAware) handler).setMessageSource(messageSource);
			}
		}
	}

	public void setMessageSource(MessageSource msgSource){
		this.messageSource=msgSource;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		ModelAndView mav = new ModelAndView(this);
		Object error;
		try {
			error = handleException(e, request);
		} catch (NoExceptionHandlerFoundException ex) {
			LOG.warn("No exception handler found to handle exception: {}", e.getClass().getName());
			return null;
		}
		mav.addObject(KEY_ERROR_OBJECT, error);
		LOG.error("Error {}", error);
		return mav;
	}

	/**
	 * @see View#render
	 */
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Object e = model.get(KEY_ERROR_OBJECT);
		RestError er=(RestError) e;
		response.setContentType(getContentType());
		response.setStatus(er.getHttpCode());

		MAPPER.writeValue(response.getOutputStream(), e);
	}

	@Override
	public String getContentType() {
		return "application/json";
	}
	
	public void setDiagnosticsDisabled(boolean diagnosticsDisabled) {
		this.diagnosticsDisabled = diagnosticsDisabled;
	}

	private Map<Class<? extends Exception>, RestExceptionHandler> getDefaultHandlers() {		
		Map<Class<? extends Exception>, RestExceptionHandler> map = new HashMap<>();

		map.put( DomainValidationException.class, new DomainValidationExceptionHandler() );
		map.put( ConstraintViolationException.class, new ConstraintViolationExceptionHandler() );

//		addHandlerTo(map, HttpMediaTypeNotAcceptableException.class, NOT_ACCEPTABLE);
//		addHandlerTo(map, MissingServletRequestParameterException.class, BAD_REQUEST);
//		addHandlerTo(map, ServletRequestBindingException.class, BAD_REQUEST);
//		addHandlerTo(map, ConversionNotSupportedException.class, INTERNAL_SERVER_ERROR);
//		addHandlerTo(map, TypeMismatchException.class, BAD_REQUEST);
//		addHandlerTo(map, HttpMessageNotReadableException.class, UNPROCESSABLE_ENTITY);
//		addHandlerTo(map, HttpMessageNotWritableException.class, INTERNAL_SERVER_ERROR);
//		addHandlerTo(map, MissingServletRequestPartException.class, BAD_REQUEST);
		addHandlerTo(map, UnknownResourceException.class, NOT_FOUND);
		addHandlerTo(map, AuthorizationException.class, FORBIDDEN);
		addHandlerTo(map, Exception.class, INTERNAL_SERVER_ERROR);

		// this class didn't exist before Spring 4.0
		try {
			Class clazz = Class.forName("org.springframework.web.servlet.NoHandlerFoundException");
			addHandlerTo(map, clazz, NOT_FOUND);
		} catch (ClassNotFoundException ex) {
			// ignore
		}
		return map;
	}

	private void addHandlerTo(Map<Class<? extends Exception>, RestExceptionHandler> map, Class exceptionClass,
			HttpStatus status) {
		map.put(exceptionClass, new ErrorMessageRestExceptionHandler(exceptionClass, status));
	}

	protected Object handleException(Exception exception, HttpServletRequest request) {

		RestExceptionHandler<Exception, ?> handler = resolveExceptionHandler(exception.getClass());

		LOG.debug("Handling exception {} with response factory: {}", exception.getClass().getName(), handler);
		return handler.handleException(exception, request);
	}

	@SuppressWarnings("unchecked")
	protected RestExceptionHandler<Exception, ?> resolveExceptionHandler(Class<? extends Exception> exceptionClass) {

		for (Class clazz = exceptionClass; clazz != Throwable.class; clazz = clazz.getSuperclass()) {
			if (handlers.containsKey(clazz)) {
				return handlers.get(clazz);
			}
		}
		throw new NoExceptionHandlerFoundException();
	}
	//////// Inner classes ////////

	public static class NoExceptionHandlerFoundException extends RuntimeException {
	}
}
