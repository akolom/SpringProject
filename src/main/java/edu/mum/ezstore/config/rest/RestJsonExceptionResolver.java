package edu.mum.ezstore.config.rest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.egen.exhandle.exception.AuthenticationException;
import com.egen.exhandle.exception.AuthorizationException;
import com.egen.exhandle.exception.BusinessException;
import com.egen.exhandle.exception.InvalidArgumentsException;
import com.egen.exhandle.exception.MaximumRequestsLimitException;
import com.egen.exhandle.exception.ObjectAlreadyExistsException;
import com.egen.exhandle.exception.ObjectNotFoundException;
import com.egen.exhandle.exception.ObjectStateChangedSinceLastRequestException;
import com.egen.exhandle.exception.UnknownResourceException;
import com.egen.exhandle.handler.ErrorMessageRestExceptionHandler;
import com.egen.exhandle.handler.RestExceptionHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
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

	static {
		httpErrorCodeMap.put(InvalidArgumentsException.class.getName(), 400);
		httpErrorCodeMap.put(AuthenticationException.class.getName(), 401);
		httpErrorCodeMap.put(AuthorizationException.class.getName(), 403);
		httpErrorCodeMap.put(ObjectNotFoundException.class.getName(), 404);
		httpErrorCodeMap.put(UnknownResourceException.class.getName(), 404);
		httpErrorCodeMap.put(ObjectAlreadyExistsException.class.getName(), 409);
		httpErrorCodeMap.put(ObjectStateChangedSinceLastRequestException.class.getName(), 409);
		httpErrorCodeMap.put(MaximumRequestsLimitException.class.getName(), 429);
		httpErrorCodeMap.put(BusinessException.class.getName(), 500);
	}

	public RestJsonExceptionResolver(MessageSource msg) {
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
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
	// @Override
	// protected ModelAndView doResolveException(HttpServletRequest request,
	// HttpServletResponse response, Object handler,
	// Exception e) {
	// ModelAndView mav = new ModelAndView(this);
	// RestError error = new RestError();
	//
	// if (e != null) {
	// error.setMessageOriginal(e.getLocalizedMessage());
	// // Every JRestError will have a message
	// error.setMessage(e.getLocalizedMessage());
	//
	// // Only BusinessExceptions have possible errorCodes
	// if (e instanceof BusinessException) {
	// error.setErrorCode(((BusinessException) e).getErrorCode());
	// } else if (e instanceof AuthenticationException &&
	// ((AuthenticationException) e).getErrorCode() != 0) {
	// // Differentiate errorCode when login failed, invalid-account,
	// // or not active, this can be set base on project
	// error.setErrorCode(((AuthenticationException) e).getErrorCode());
	// }
	//
	// // Set HTTP error code. If we don't have this Exception type
	// // registered, then we will provide a
	// // diagnostic and error id.
	// Integer httpCode = httpErrorCodeMap.get(e.getClass().getName());
	// if (httpCode == null) {
	// httpCode = 500;
	//
	// StackTraceElement[] trace = null;
	// StringBuilder sb = new StringBuilder();
	//
	// if (e.getCause() == null) {
	// trace = e.getStackTrace();
	// sb.append("Exception - ").append(e.getClass().getName());
	// sb.append("\r\nStack -\r\n");
	// } else {
	// trace = e.getCause().getStackTrace();
	// sb.append("\r\nException Cause -\r\n");
	// }
	//
	// for (int i = 0; i < trace.length; i++) {
	// sb.append("\r\n\tat ").append(trace[i].toString());
	// }
	// error.setDiagnosticOriginal(sb.toString());
	//
	// if (!diagnosticsDisabled) {
	// error.setDiagnostic(sb.toString());
	// }
	//
	// }
	// // check if errorCode is specified, take original message do not
	// // override
	// if (error.getErrorCode() != null && error.getErrorCode() != 0) {
	// error.setMessage(e.getLocalizedMessage());
	// }
	// error.setHttpCode(httpCode);
	// } else {
	// error.setMessage("Unknown error");
	// error.setHttpCode(500);
	// }
	//
	// mav.addObject(KEY_ERROR_OBJECT, error);
	// LOG.error("Error {}", error);
	// return mav;
	// }

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		ModelAndView mav = new ModelAndView(this);
		RestError error = new RestError();
		try {
			error = (RestError) handleException(e, request);
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
		RestError e = (RestError) model.get(KEY_ERROR_OBJECT);

		response.setContentType(getContentType());
		response.setStatus(e.getHttpCode());

		MAPPER.writeValue(response.getOutputStream(), e);
	}

	@Override
	public String getContentType() {
		return "application/json";
	}

	/**
	 * Register a specific Exception to always return the supplied HTTP response
	 * code.
	 *
	 * @param e
	 *            Exception to register
	 * @param httpResponseCode
	 *            The HTTP response code to send back to the client for this
	 *            given Exception
	 */
	public static void registerExceptionWithHTTPCode(Exception e, int httpResponseCode) {
		httpErrorCodeMap.put(e.getClass().getName(), httpResponseCode);
	}

	public static void registerExceptionWithHTTPCode(Class<? extends Exception> clazz, int httpResponseCode) {
		httpErrorCodeMap.put(clazz.getName(), httpResponseCode);
	}

	public void setDiagnosticsDisabled(boolean diagnosticsDisabled) {
		this.diagnosticsDisabled = diagnosticsDisabled;
	}

	private Map<Class<? extends Exception>, RestExceptionHandler> getDefaultHandlers() {

		Map<Class<? extends Exception>, RestExceptionHandler> map = new HashMap<>();


		addHandlerTo(map, HttpMediaTypeNotAcceptableException.class, NOT_ACCEPTABLE);
		addHandlerTo(map, MissingServletRequestParameterException.class, BAD_REQUEST);
		addHandlerTo(map, ServletRequestBindingException.class, BAD_REQUEST);
		addHandlerTo(map, ConversionNotSupportedException.class, INTERNAL_SERVER_ERROR);
		addHandlerTo(map, TypeMismatchException.class, BAD_REQUEST);
		addHandlerTo(map, HttpMessageNotReadableException.class, UNPROCESSABLE_ENTITY);
		addHandlerTo(map, HttpMessageNotWritableException.class, INTERNAL_SERVER_ERROR);
		addHandlerTo(map, MissingServletRequestPartException.class, BAD_REQUEST);
		addHandlerTo(map, UnknownResourceException.class, NOT_FOUND);
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
