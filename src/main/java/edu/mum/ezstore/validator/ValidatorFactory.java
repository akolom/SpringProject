package edu.mum.ezstore.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.validation.Validator;

public class ValidatorFactory {
	private static ValidatorFactory instance;
    private Map<String,Validator> validatorMap=new HashMap<>();
    private ValidatorFactory() {
    	ApplicationContext context=ApplicationContextHolder.getContext();
    	validatorMap.put("User", context.getBean(UserValidator.class));
    }
    
    static {
        instance = new ValidatorFactory();
    }
    public static ValidatorFactory getInstance() {
        return instance;
    }
    
    public Validator getValidator(String key){
    	if(validatorMap.containsKey(key))
    		return validatorMap.get(key);
    	else return null;
    }
}
