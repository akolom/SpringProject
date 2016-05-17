package edu.mum.ezstore.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.mum.ezstore.controller.TestController;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
	
		@Pointcut("within (edu.mum.ezstore.service..*)")
		public void allEzStoreMethod(){}
		

		@Before("allEzStoreMethod()")
		public void Advice(JoinPoint joinPoint){
			
			String strLog = String.format("CALL: %s::%s", 
					joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName());
			LOG.info(strLog);
		}
}
