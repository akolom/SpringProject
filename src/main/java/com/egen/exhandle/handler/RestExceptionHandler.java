package com.egen.exhandle.handler;

import javax.servlet.http.HttpServletRequest;

/**
 * Contract for classes generating a {@link ResponseEntity} for an instance of the specified
 * Exception type, used in {@link edu.mum.ezstore.config.rest.RestJsonExceptionResolver
 * RestHandlerExceptionResolver}.
 *
 * @param <E> Type of the handled exception.
 * @param <T> Type of the response message (entity body).
 */
public interface RestExceptionHandler <E extends Exception, T> {
	/**
     * Handles exception and generates {@link ResponseEntity}.
     *
     * @param exception The exception to handle and get data from.
     * @param request The current request.
     * @return A response entity.
     */
    T handleException(E exception, HttpServletRequest request);
}
