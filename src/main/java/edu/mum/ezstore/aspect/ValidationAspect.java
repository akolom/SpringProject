package edu.mum.ezstore.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.ezstore.validator.AnnotationValidator;

@Aspect
@Component
public class ValidationAspect {

	 	@Autowired
	    private AnnotationValidator annotationValidator;

		@Pointcut("@annotation(edu.mum.ezstore.aspect.annotation.AnnotationValidation) && args(object)")
		public void save(Object object) {}

		@Before("save(object)")
		public void Advice(JoinPoint joinPoint, Object object){
				annotationValidator.doValidate(object);
			}
}
