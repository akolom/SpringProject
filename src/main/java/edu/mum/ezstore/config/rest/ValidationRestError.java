package edu.mum.ezstore.config.rest;

import java.util.ArrayList;
import java.util.List;




//@JsonInclude(Include.NON_EMPTY) //for Jackson 2.x
public class ValidationRestError extends RestError {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CustomFieldError> errors = new ArrayList<>(6);


    public ValidationRestError(RestError orig) {
        super(orig);
    }
    public ValidationRestError addError(String field, Object rejectedValue, String message) {
    	CustomFieldError error = new CustomFieldError(field,rejectedValue,message);

        errors.add(error);
        return this;
    }

    public ValidationRestError addError(String message) {
    	CustomFieldError error = new CustomFieldError();
        error.setMessage(message);

        errors.add(error);
        return this;
    }
}
