package com.egen.exhandle.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import static org.springframework.core.GenericTypeResolver.resolveTypeArguments;

public abstract class AbstractRestExceptionHandler<E extends Exception, T> implements RestExceptionHandler<E, T> {

	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);
	private final Class<E> exceptionClass;
	private final HttpStatus status;

	////// Abstract methods //////
	/**
	 * This constructor determines the exception class from the generic class
	 * parameter {@code E}.
	 *
	 * @param status
	 *            HTTP status
	 */
	protected AbstractRestExceptionHandler(HttpStatus status) {
		this.exceptionClass = determineTargetType();
		this.status = status;
		LOG.trace("Determined generic exception type: {}", exceptionClass.getName());
	}

	protected AbstractRestExceptionHandler(Class<E> exceptionClass, HttpStatus status) {
		this.exceptionClass = exceptionClass;
		this.status = status;
	}

	public abstract T createBody(E ex, HttpServletRequest req);

	////// Template methods //////

	public T handleException(E ex, HttpServletRequest req) {

		logException(ex, req);

		T body = createBody(ex, req);

		return body;
	}

	/**
	 * Logs the exception; on ERROR level when status is 5xx, otherwise on INFO
	 * level without stack trace, or DEBUG level with stack trace. The logger
	 * name is {@code edu.mum.ezstore.handler.RestExceptionHandler}.
	 *
	 * @param ex
	 *            The exception to log.
	 * @param req
	 *            The current web request.
	 */
	protected void logException(E ex, HttpServletRequest req) {

		if (LOG.isErrorEnabled() && getStatus().value() >= 500 || LOG.isInfoEnabled()) {
			Marker marker = MarkerFactory.getMarker(ex.getClass().getName());

			String uri = req.getRequestURI();
			if (req.getQueryString() != null) {
				uri += '?' + req.getQueryString();
			}
			String msg = String.format("%s %s ~> %s", req.getMethod(), uri, getStatus());

			if (getStatus().value() >= 500) {
				LOG.error(marker, msg, ex);

			} else if (LOG.isDebugEnabled()) {
				LOG.debug(marker, msg, ex);

			} else {
				LOG.info(marker, msg);
			}
		}
	}

	public Class<E> getExceptionClass() {
		return exceptionClass;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@SuppressWarnings("unchecked")
	private Class<E> determineTargetType() {
		return (Class<E>) resolveTypeArguments(getClass(), AbstractRestExceptionHandler.class)[0];
	}
}
