package edu.mum.ezstore.validator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import com.egen.exhandle.exception.DomainValidationException;

import edu.mum.ezstore.config.rest.CustomFieldError;

@Component
public class AnnotationValidator {
	@Autowired
	private Validator validator;

	@Autowired
	private MessageSourceAccessor messageAccessor;

	public void doValidate(Object object) {

		System.out.println();
		System.out.println("DOING Validation! Object:" + object.getClass().getName());

		Errors errors = new BeanPropertyBindingResult(object, object.getClass().getName());

		validator.validate(object, errors);

		if (errors.hasErrors()) {
			List<FieldError> fieldErrors = errors.getFieldErrors();
			
			List<CustomFieldError> customerFieldErrors=fieldErrors.stream().map(m->new CustomFieldError(
					m.getField(),m.getRejectedValue(),messageAccessor.getMessage(m)
					)).collect(Collectors.toList());
//			for (FieldError fieldError : fieldErrors) {
//
//				// System.out.println(messageAccessor.getMessage(fieldError));
//			}
			throw new DomainValidationException(customerFieldErrors);
		}

		// return ;
	}

}
