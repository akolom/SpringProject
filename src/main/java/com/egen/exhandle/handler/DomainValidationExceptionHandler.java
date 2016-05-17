package com.egen.exhandle.handler;

import com.egen.exhandle.exception.DomainValidationException;

import edu.mum.ezstore.config.rest.CustomFieldError;
import edu.mum.ezstore.config.rest.RestError;
import edu.mum.ezstore.config.rest.ValidationRestError;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import javax.servlet.http.HttpServletRequest;

public class DomainValidationExceptionHandler extends ErrorMessageRestExceptionHandler<DomainValidationException> {

	public DomainValidationExceptionHandler() {
		super(UNPROCESSABLE_ENTITY); 
		// TODO Auto-generated constructor stub
	}

	@Override
	public ValidationRestError createBody(DomainValidationException ex, HttpServletRequest req) {

		RestError tmpl = super.createBody(ex, req);
		ValidationRestError msg = new ValidationRestError(tmpl);

		for (CustomFieldError violation : ex.getErrors()) {
			msg.addError(violation.getField(),violation.getRejected(),violation.getMessage());
		}
		return msg;
	}
}
