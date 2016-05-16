package com.egen.exhandle.exception;

import java.util.ArrayList;
import java.util.List;

import edu.mum.ezstore.config.rest.CustomFieldError;



@SuppressWarnings("serial")
public class DomainValidationException extends RuntimeException {
	
	private List<CustomFieldError> errors=new ArrayList<>();
    public DomainValidationException() {
        super();
    }

	public DomainValidationException(String msg) {
		super(msg);
	}
	public DomainValidationException(List<CustomFieldError> errors) {
		this.setErrors(errors);
	}

	public List<CustomFieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomFieldError> errors) {
		this.errors = errors;
	}
}
