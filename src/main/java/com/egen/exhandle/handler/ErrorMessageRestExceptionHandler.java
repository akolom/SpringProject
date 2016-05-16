package com.egen.exhandle.handler;

import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import com.egen.exhandle.interpolators.MessageInterpolator;
import com.egen.exhandle.interpolators.MessageInterpolatorAware;
import com.egen.exhandle.interpolators.NoOpMessageInterpolator;
import com.egen.exhandle.interpolators.SpelMessageInterpolator;

import edu.mum.ezstore.config.rest.RestError;

public class ErrorMessageRestExceptionHandler<E extends Exception> extends AbstractRestExceptionHandler<E, RestError>
		implements MessageSourceAware, MessageInterpolatorAware {
	private static final Logger LOG = LoggerFactory.getLogger(ErrorMessageRestExceptionHandler.class);
	private MessageSource messageSource;
	private MessageInterpolator interpolator = new SpelMessageInterpolator();
	protected static final String DEFAULT_PREFIX = "default", TYPE_KEY = "type", TITLE_KEY = "title",
			DETAIL_KEY = "detail", INSTANCE_KEY = "instance" , MESSAGE_KEY="message";

	/**
	 * @param exceptionClass
	 *            Type of the handled exceptions; it's used as a prefix of key
	 *            to resolve messages (via MessageSource).
	 * @param status
	 *            HTTP status that will be sent to client.
	 */
	public ErrorMessageRestExceptionHandler(Class<E> exceptionClass, HttpStatus status) {
		super(exceptionClass, status);
	}

	/**
	 * @see AbstractRestExceptionHandler#AbstractRestExceptionHandler(HttpStatus)
	 *      AbstractRestExceptionHandler
	 */
	protected ErrorMessageRestExceptionHandler(HttpStatus status) {
		super(status);
	}

	@Override
	public RestError createBody(E ex, HttpServletRequest req) {
		RestError m = new RestError();
        m.setType(URI.create(resolveMessage(TYPE_KEY, ex, req)));
        m.setTitle(resolveMessage(TITLE_KEY, ex, req));
        m.setHttpCode(getStatus());
        m.setMessage(resolveMessage(MESSAGE_KEY, ex, req));
        
		return m;
	}

	protected String resolveMessage(String key, E exception, HttpServletRequest request) {

		String template = getMessage(key, LocaleContextHolder.getLocale());

		Map<String, Object> vars = new HashMap<>(2);
		vars.put("ex", exception);
		vars.put("req", request);

		return interpolateMessage(template, vars);
	}

	protected String interpolateMessage(String messageTemplate, Map<String, Object> variables) {

		LOG.trace("Interpolating message '{}' with variables: {}", messageTemplate, variables);
		return interpolator.interpolate(messageTemplate, variables);
	}

	protected String getMessage(String key, Locale locale) {

		String prefix = getExceptionClass().getName();

		String message = messageSource.getMessage(prefix + "." + key, null, null, locale);
		if (message == null) {
			message = messageSource.getMessage(DEFAULT_PREFIX + "." + key, null, null, locale);
		}
		if (message == null) {
			message = "";
			LOG.debug("No message found for {}.{}, nor {}.{}", prefix, key, DEFAULT_PREFIX, key);
		}
		return message;
	}

	////// Accessors //////
	@Override
	public void setMessageSource(MessageSource messageSource) {
		Assert.notNull(messageSource, "messageSource must not be null");
		this.messageSource = messageSource;
	}

	@Override
	public void setMessageInterpolator(MessageInterpolator messageInterpolator) {
		this.interpolator = interpolator != null ? interpolator : new NoOpMessageInterpolator();
	}
}
